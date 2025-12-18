import kotlin.io.path.Path
import kotlin.io.path.exists
import kotlin.io.path.name

@Suppress("kotlin:S3776")  // Disable cognitive complexity check due to solution structure
fun main() {
    val dayNum = "00"
    fun part1(input: List<String>): Int {
        return -1
    }

    fun part2(input: List<String>): Int {
        return -1
    }

    // Local tests, with simple data

    // Test with test data file
    val testFile = Path("Day${dayNum}_test.txt")
    if (testFile.exists()) {
        val testInput = readInput(testFile.name)
        check(part1(testInput) == 0)
        check(part2(testInput) == 0)
    }

    val inputFile = Path("Day${dayNum}.txt")
    if (inputFile.exists()) {
        val input = readInput(inputFile.name)
        println("Part 1 solution:")
        part1(input).println()
        println("Part 2 solution:")
        part2(input).println()
    }
}