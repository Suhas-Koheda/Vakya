package dev.haas.vakya.common

actual class SentenceScorer {

    actual fun summarize(text: String): String {
        // TODO: Implement CoreML / heuristic summarizer
        return text
            .split(".")
            .take(2)
            .joinToString(". ")
    }
}
