import com.sun.jdi.IntegerType

fun main() {

    val input = readInput("Day01")
    val day01 = Day01()
    day01.part1(input).println()
    day01.part2(input).println()
}

class Day01
{
    fun part1(input: List<String>): Int {
        return input.sumOf {
                line : String -> line.first { it.isDigit() }.digitToInt() * 10 + line.last { it.isDigit() }.digitToInt() }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { line : String -> getNumber(line, StartFrom.BEGIN) * 10 + getNumber(line, StartFrom.END)};
    }

    enum class StartFrom {BEGIN, END}
    val words = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    fun getNumber(line: String, startFrom: StartFrom): Int
    {
        val indices = when (startFrom)
        {
            StartFrom.BEGIN -> line.indices
            StartFrom.END -> line.lastIndex downTo 0
        }
        for (index in indices)
        {
            line[index].digitToIntOrNull()?.let { return it }
            for((wordIndex, word) in words.withIndex())
            {
                if(line.substring(index).startsWith(word))
                    return wordIndex + 1
            }
        }
        return 0
    }
}


