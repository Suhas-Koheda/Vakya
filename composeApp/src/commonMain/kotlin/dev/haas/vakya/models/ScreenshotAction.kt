package dev.haas.vakya.models

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenshotAction {

    @Serializable
    object Summarize : ScreenshotAction()

    @Serializable
    object ExtractLinks : ScreenshotAction()

    @Serializable
    object ExtractQuestions : ScreenshotAction()

    @Serializable
    object ExtractTodos : ScreenshotAction()

    @Serializable
    data class CreateNote(
        val preferredType: NoteType? = null
    ) : ScreenshotAction()
}
