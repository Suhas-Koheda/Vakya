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

        val prompt = """Extract the following from the text:

1. A concise summary (don't add any conversational fluff like "Here's a summary:").
2. Any links (URLs).
3. Any questions.
4. Any to-do items or action items.

Format the output as follows, with each section on a new line and sections separated by '---':

SUMMARY:
[Your summary here]
---
LINKS:
[Link 1]
[Link 2]
---
QUESTIONS:
[Question 1]
[Question 2]
---
TODOS:
[Todo 1]
[Todo 2]

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