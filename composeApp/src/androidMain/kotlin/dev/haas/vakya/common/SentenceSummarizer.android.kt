package dev.haas.vakya.common

import com.google.mediapipe.tasks.genai.llminference.LlmInference
import dev.haas.vakya.AppContextHolder

actual object SentenceSummarizer {

    private const val MODEL_PATH =
        "/data/local/tmp/llm/gemma3-1b-it-int4.task"

    private const val MAX_INPUT_CHARS = 1200

    actual fun summarize(text: String): String {
        val safeText =
            if (text.length > MAX_INPUT_CHARS) {
                text.take(MAX_INPUT_CHARS)
            } else text

        val prompt = """
Summarize the following text in 3 bullet points.
Keep it under 60 words.

Text:
$safeText
""".trimIndent()

        val options = LlmInference.LlmInferenceOptions.builder()
            .setModelPath(MODEL_PATH)
            .setMaxTokens(1024)
            .setMaxTopK(40)
            .build()

        val llm = LlmInference.createFromOptions(
            AppContextHolder.context,
            options
        )

        return llm.use { llm ->
            llm.generateResponse(prompt)
        }
    }
}