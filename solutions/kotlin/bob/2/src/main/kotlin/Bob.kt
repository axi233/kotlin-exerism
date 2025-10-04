object Bob {
    fun hey(input: String): String {
        val (silence, yelling, question) =
            State(
                silence = input.isBlank(),
                yelling = with(input) { any { it.isLetter() } && none { it.isLowerCase() } },
                question = input.trimEnd().endsWith("?")
            )

        val inputType = when {
            silence -> InputType.SILENCE
            yelling && question -> InputType.YELLING_QUESTION
            question -> InputType.QUESTION
            yelling -> InputType.YELLING
            else -> InputType.REGULAR
        }

        return inputType.response
    }

    private data class State(val silence: Boolean, val yelling: Boolean, val question: Boolean)

    private enum class InputType(val response: String) {
        SILENCE("Fine. Be that way!"),
        YELLING_QUESTION("Calm down, I know what I'm doing!"),
        QUESTION("Sure."),
        YELLING("Whoa, chill out!"),
        REGULAR("Whatever."),
    }
}
