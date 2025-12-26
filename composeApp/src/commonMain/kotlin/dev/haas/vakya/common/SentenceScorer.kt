package dev.haas.vakya.common

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class SentenceScorer {
    fun summarize(text:String):String
}