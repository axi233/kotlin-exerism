object CollatzCalculator {
    fun computeStepCount(start: Int): Int {
        if (start <= 0)
            throw IllegalArgumentException()

        return generateSequence(start) { calculateNext(it) } .takeWhile { it != 1 }.count()
    }

    private fun calculateNext(number: Int) =
        when {
            (number and 1 == 0) -> number / 2
            else -> number * 3 + 1
        }
}
