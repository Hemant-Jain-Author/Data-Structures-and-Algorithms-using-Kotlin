fun stairUniqueWaysBU(n: Int): Int {
    if (n <= 2) return n
    var first = 1
    var second = 2
    var temp = 0
    for (i in 3..n) {
        temp = first + second
        first = second
        second = temp
    }
    return temp
}

fun stairUniqueWaysBU2(n: Int): Int {
    if (n < 2) return n
    val ways = IntArray(n)
    ways[0] = 1
    ways[1] = 2
    for (i in 2 until n) ways[i] = ways[i - 1] + ways[i - 2]
    return ways[n - 1]
}

// Testing code.
fun main() {
    println("Unique way to reach top:: " + stairUniqueWaysBU(4))
    println("Unique way to reach top:: " + stairUniqueWaysBU2(4))
}