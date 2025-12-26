package dev.haas.vakya.common

import org.tensorflow.lite.Interpreter
import kotlin.math.sqrt

actual class SentenceScorer(
    modelBuffer: java.nio.ByteBuffer
) {

    private val interpreter = Interpreter(modelBuffer)

    actual fun summarize(text: String): String {

        val sentences = text.split(".")
            .map { it.trim() }
            .filter { it.isNotEmpty() }

        if (sentences.size <= 2) return text

        // Compute sentence embeddings
        val embeddings = sentences.map { sentence ->
            embed(sentence)
        }

        // Document embedding = average of sentence embeddings
        val documentEmbedding = averageEmbedding(embeddings)

        // Score sentences by cosine similarity
        val scored = sentences.mapIndexed { index, sentence ->
            val score = cosineSimilarity(
                embeddings[index],
                documentEmbedding
            )
            sentence to score
        }

        return scored
            .sortedByDescending { it.second }
            .take(3)
            .joinToString(". ") { it.first }
    }

    // ---- Embedding ----

    private fun embed(text: String): FloatArray {
        val input = arrayOf(text)
        val output = Array(1) { FloatArray(512) }

        interpreter.run(input, output)
        return output[0]
    }

    // ---- Math helpers ----

    private fun cosineSimilarity(a: FloatArray, b: FloatArray): Float {
        var dot = 0f
        var normA = 0f
        var normB = 0f

        for (i in a.indices) {
            dot += a[i] * b[i]
            normA += a[i] * a[i]
            normB += b[i] * b[i]
        }

        return dot / (sqrt(normA) * sqrt(normB) + 1e-8f)
    }

    private fun averageEmbedding(vectors: List<FloatArray>): FloatArray {
        val avg = FloatArray(vectors[0].size)

        for (vec in vectors) {
            for (i in vec.indices) {
                avg[i] += vec[i]
            }
        }

        for (i in avg.indices) {
            avg[i] /= vectors.size
        }

        return avg
    }
}
