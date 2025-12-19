import kotlin.io.path.Path
import kotlin.io.path.exists

@Suppress("kotlin:S3776")  // Disable cognitive complexity check due to solution structure
fun main() {
    val dayNum = "02"
    val dataFileName = "Day${dayNum}"
    val testDataFileName = "Day${dayNum}_test"

    fun part1(input: List<String>): Long {
        // Input is two numbers, separated by a hyphen
        val invalidIds = MutableList(0){-1L}
        for (line in input) {
            val start = line.split("-")[0].toLong()
            val end = line.split("-")[1].toLong()
            (start..end).forEach {
                val numStr = it.toString()
                // only even length can be invalid
                if (numStr.length % 2 == 0) {
                    val midPoint = numStr.length / 2
                    val first = numStr.substring(0, midPoint)
                    val second = numStr.substring(midPoint)
                    if (first == second) {
                        invalidIds.add(it)
                    }
                }
            }
        }
        return invalidIds.sum()
    }

    fun part2(input: List<String>): Long {
        // Input is two numbers, separated by a hyphen
        val invalidIds = MutableList(0){-1L}
        val invalidRegex = Regex("""(.{1,})\1+""")
        for (line in input) {
            val start = line.split("-")[0].toLong()
            val end = line.split("-")[1].toLong()
            println("Lengths, Start: ${start.toString().length}, End: ${end.toString().length}")
            (start..end).forEach { identifier ->
                val numStr = identifier.toString()
                if (invalidRegex.matches(numStr)) invalidIds.add(identifier)
            }
        }
        return invalidIds.toSet().sum()
    }

    // Local tests, with simple data
    check(part1(listOf("11-22")) == 33L)
    check(part1(listOf("99-115")) == 99L)
    check(part1(listOf("998-1012")) == 1010L)
    check(part1(listOf("1188511880-1188511890")) == 1188511885L)
    check(part1(listOf("222220-222224")) == 222222L)
    check(part1(listOf("1698522-1698528")) == 0L)
    check(part1(listOf("446443-446449")) == 446446L)
    check(part1(listOf("38593856-38593862")) == 38593859L)
    check(part1(listOf("11-22", "99-115", "998-1012", "1188511880-1188511890", "222220-222224", "1698522-1698528", "446443-446449", "38593856-38593862")) == 1227775554L)

    check(part2(listOf("11-22")) == 33L)
    check(part2(listOf("99-115")) == 210L)
    check(part2(listOf("998-1012")) == 2009L)
    check(part2(listOf("1188511880-1188511890")) == 1188511885L)
    check(part2(listOf("222220-222224")) == 222222L)
    check(part2(listOf("1698522-1698528")) == 0L)
    check(part2(listOf("446443-446449")) == 446446L)
    check(part2(listOf("38593856-38593862")) == 38593859L)
    check(part2(listOf("565653-565659")) == 565656L)
    check(part2(listOf("824824821-824824827")) == 824824824L)
    check(part2(listOf("2121212118-2121212124")) == 2121212121L)
    check(part2(listOf("11-22", "99-115", "998-1012", "1188511880-1188511890", "222220-222224", "1698522-1698528", "446443-446449", "38593856-38593862", "565653-565659", "824824821-824824827", "2121212118-2121212124")) == 4174379265L)

    // Test with test data file
    val testFile = Path("src/${testDataFileName}.txt")
    if (testFile.exists()) {
        val testInput = readInput(testDataFileName)
        check(part1(testInput) == 0L)
        check(part2(testInput) == 0L)
    }

    val inputFile = Path("src/${dataFileName}.txt")
    if (inputFile.exists()) {
        val input = readInput(dataFileName, separator = ",")
        println("Part 1 solution:")
        part1(input).println()
        println("Part 2 solution:")
        part2(input).println() // 26202168596, 26202168596
    }
}