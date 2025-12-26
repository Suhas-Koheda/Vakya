package dev.haas.vakya.common

class SentenceScorer {

    fun summarize(text: String): String {
        val lines = text
            .lines()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .filterNot { isUiNoise(it) }

        if (lines.size <= 3) {
            return lines.joinToString("\n")
        }

        val scored = lines.mapIndexed { index, line ->
            line to scoreLine(line, index)
        }

        return scored
            .sortedByDescending { it.second }
            .take(3)
            .map { it.first }
            .joinToString("\n")
            .truncateWords(60)
    }

    // ---------------- scoring ----------------

    private fun scoreLine(line: String, index: Int): Int {
        var score = 0

        // Prefer early lines
        score += maxOf(0, 5 - index)

        // Action words
        if (containsActionVerb(line)) score += 5

        // Dates / times
        if (containsDate(line)) score += 4

        // Bullet / list items
        if (isBullet(line)) score += 3

        // Medium length (not too short, not too long)
        val len = line.length
        if (len in 15..120) score += 2

        return score
    }

    // ---------------- heuristics ----------------

    private fun isUiNoise(line: String): Boolean =
        line.length < 5 ||
        line.contains("battery", ignoreCase = true) ||
        line.contains("wifi", ignoreCase = true) ||
        line.contains("screenshot", ignoreCase = true) ||
        line.contains("notification", ignoreCase = true)

    private fun isBullet(line: String): Boolean =
        line.startsWith("-") ||
        line.startsWith("•") ||
        line.startsWith("*")

    private fun containsActionVerb(line: String): Boolean {
        val verbs = listOf(
            "buy", "call", "finish", "send", "meet", "submit",
            "prepare", "schedule", "review", "complete", "pay"
        )
        return verbs.any { line.contains(it, ignoreCase = true) }
    }

    private fun containsDate(line: String): Boolean {
        val patterns = listOf(
            "\\b(today|tomorrow)\\b",
            "\\b(mon|tue|wed|thu|fri|sat|sun)\\b",
            "\\b\\d{1,2}/\\d{1,2}/\\d{2,4}\\b",
            "\\b\\d{1,2}[:.]\\d{2}\\b"
        )
        return patterns.any { Regex(it, RegexOption.IGNORE_CASE).containsMatchIn(line) }
    }

    // ---------------- utils ----------------

    private fun String.truncateWords(maxWords: Int): String {
        val words = split(Regex("\\s+"))
        return if (words.size <= maxWords) this
        else words.take(maxWords).joinToString(" ") + "…"
    }
}
