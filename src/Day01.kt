fun main() {
    fun part1(input: List<String>): Int {
        fun rotate(move: String, currentPos: Int): Int {
            println("move: $move, pos: $currentPos")
            // Get direction and size of move
            val direction = if (move[0] == 'R') 1 else -1
            // Only need last two digits as each 100 is a full rotation
            val amountStr = move.substring(1)
            val amount = if (amountStr.length <=2) amountStr.toInt() else amountStr.substring(amountStr.length - 2).toInt()

            println("From move '${move}', direction: ${direction}, amount: ${amount}")
            val newPos = currentPos + (direction * amount)
            return when {
                newPos in 0..99 -> newPos
                newPos > 99 -> newPos - 100
                else -> newPos + 100
            }
        }

        val startPos = 50
        val positions = MutableList(input.size){-1}
        input.forEachIndexed { index, s ->
            val currentPos = if (index == 0) startPos else positions[index - 1]
            positions[index] = rotate(s, currentPos)
        }

        return positions.count { it == 0 }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("R50")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 0)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
