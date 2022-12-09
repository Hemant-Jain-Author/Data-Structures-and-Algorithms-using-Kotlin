fun min(x: Int, y: Int, z: Int): Int {
    return Math.min(Math.min(x, y), z)
}

fun minCost(cost: Array<IntArray>, m: Int, n: Int): Int {
    if (m == 0 || n == 0) return 99999
    return if (m == 1 && n == 1) cost[0][0] else cost[m - 1][n - 1] + min(
        minCost(cost, m - 1, n - 1),
        minCost(cost, m - 1, n),
        minCost(cost, m, n - 1)
    )
}

fun minCostBU(cost: Array<IntArray>, m: Int, n: Int): Int {
    val tc = Array(m) { IntArray(n) }
    tc[0][0] = cost[0][0]

    // Initialize first column.
    for (i in 1 until m) {
        tc[i][0] = tc[i - 1][0] + cost[i][0]
    }
    // Initialize first row.
    for (j in 1 until n) {
        tc[0][j] = tc[0][j - 1] + cost[0][j]
    }
    for (i in 1 until m) {
        for (j in 1 until n) {
            tc[i][j] = cost[i][j] + min(tc[i - 1][j - 1], tc[i - 1][j], tc[i][j - 1])
        }
    }
    return tc[m - 1][n - 1]
}

// Testing code.
fun main() {
    val cost = arrayOf(intArrayOf(1, 3, 4), intArrayOf(4, 7, 5), intArrayOf(1, 5, 3))
    println(minCost(cost, 3, 3))
    println(minCostBU(cost, 3, 3))
}

/* 
11
11
*/