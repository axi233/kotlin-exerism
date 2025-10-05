object CollatzCalculator {
    fun computeStepCount(start: Int): Int {
        require(start > 0) { "Start value must be positive" }
        return generateSequence(start) { calculateNext(it) }.indexOf(1)
    }

    private fun calculateNext(number: Int) =
        when {
            (number and 1 == 0) -> number / 2
            else -> number * 3 + 1
        }
}
