// Towers Of Hanoi problem.
fun tohUtil(num: Int, from: Char, to: Char, temp: Char) {
    if (num < 1) {
        return
    }
    tohUtil(num - 1, from, temp, to)
    println("Move disk $num from peg $from to peg $to")
    tohUtil(num - 1, temp, to, from)
}

fun toh(num: Int) {
    println("The sequence of moves involved in the Tower of Hanoi are :")
    tohUtil(num, 'A', 'C', 'B')
}

fun main() {
    toh(3)
}
