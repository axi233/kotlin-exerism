object Bob {
    fun hey(input: String): String {
        return classifyInput(input).response
    }

    private fun classifyInput(input: String): InputType {
        val trimmedInput = input.replace(" ", "")

        val stateMap = mapOf(
            InputType.SILENCE to isSilence(trimmedInput),
            InputType.YELLING to isYelling(trimmedInput),
            InputType.QUESTION to isQuestion(trimmedInput),
        )

        return when {
            stateMap[InputType.QUESTION] == true && stateMap[InputType.YELLING] == true -> InputType.YELLING_QUESTION
            else -> stateMap.entries.firstOrNull { it.value }?.key ?: InputType.REGULAR
        }
    }

    private fun isQuestion(input: String) = input.endsWith("?")
    private fun isYelling(input: String) =
        input.filter { it.isLetter() }.let { it.isNotEmpty() && it.all { c -> c.isUpperCase() } }
    private fun isSilence(input: String) = input.isBlank()

    private enum class InputType(val response: String) {
        QUESTION("Sure."),
        YELLING("Whoa, chill out!"),
        YELLING_QUESTION("Calm down, I know what I'm doing!"),
        SILENCE("Fine. Be that way!"),
        REGULAR("Whatever."),
    }
}
