object Bob {
    fun hey(input: String): String {
        val state = computeState(input)
        val inputType = classifyInput(state)
        return respond(inputType)
    }

    private fun computeState(input: String) =
        State(
            silence = input.isBlank(),
            yelling = with(input) { any { it.isLetter() } && none { it.isLowerCase() } },
            question = input.lastOrNull { !it.isWhitespace() } == '?'
        )

    private fun classifyInput(state: State) =
        when {
            state.silence -> InputType.SILENCE
            state.yelling && state.question -> InputType.YELLING_QUESTION
            state.question -> InputType.QUESTION
            state.yelling -> InputType.YELLING
            else -> InputType.REGULAR
        }

    private fun respond(inputType: InputType) =
        when (inputType) {
            InputType.SILENCE -> "Fine. Be that way!"
            InputType.YELLING_QUESTION -> "Calm down, I know what I'm doing!"
            InputType.QUESTION -> "Sure."
            InputType.YELLING -> "Whoa, chill out!"
            InputType.REGULAR -> "Whatever."
        }

    private data class State(val silence: Boolean, val yelling: Boolean, val question: Boolean)

    private enum class InputType {
        SILENCE,
        YELLING_QUESTION,
        QUESTION,
        YELLING,
        REGULAR,
    }
}
