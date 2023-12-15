fun main() {

    val input = readInput("Day09")
    val day09 = Day09()
    //day09.part1(input).println()
    day09.part1_Olaf(input).println()
    day09.part2(input).println()
}

class Day09
{
    fun part1(input: List<String>): Int {

        val r = input.map { line ->
            val stepsList = mutableListOf(line.split(" ").map { s -> s.toInt() })
            do
            {
                val diffs = stepsList.last().zipWithNext { a, b -> b - a }
                stepsList.add(diffs)
            }
            while(diffs.any { i -> i != 0 })

            var lastAddedNumber = 0;
            stepsList.reversed().forEach {
                lastAddedNumber += it.last()
                it.addLast(lastAddedNumber)
            }
            lastAddedNumber
        }
        return r.sum();
    }

    fun part1_Olaf(input: List<String>): Int {
        val histories = input.map { it.split(' ').map(String::toInt) }
        return histories.sumOf { it.predictNext() }
    }

    private fun List<Int>.predictNext(): Int {
        val lastValues = generateSequence(this) { v ->
            v.zipWithNext().map { (a, b) -> b - a }
        }.takeWhile { v -> v.any { it != 0 } }.map { it.last() }
        return lastValues.sum()
    }

    private fun List<Int>.predictPrevious(): Int {
        val lastValues = generateSequence(this) { v ->
            v.zipWithNext().map { (a, b) -> b - a }
        }.takeWhile { v -> v.any { it != 0 } }.map { it.first() }
        return lastValues.toList().reversed().reduce { acc, i -> i - acc }
    }


    fun part2(input: List<String>): Int {
        val histories = input.map { it.split(' ').map(String::toInt) }
        return histories.sumOf { it.predictPrevious() }
    }

}