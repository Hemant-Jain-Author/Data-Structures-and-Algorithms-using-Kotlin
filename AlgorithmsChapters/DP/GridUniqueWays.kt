fun gridUniqueWays(m: Int, n: Int): Int {
    val dp = Array(m) { IntArray(n) }
    dp[0][0] = 1

    // Initialize first column.
    for (i in 1 until m) {
        dp[i][0] = dp[i - 1][0]
    }
    // Initialize first row.
    for (j in 1 until n) {
        dp[0][j] = dp[0][j - 1]
    }
    for (i in 1 until m) {
        for (j in 1 until n) dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
    }
    return dp[m - 1][n - 1]
}

// Diagonal movement allowed.
fun gridUnique3Ways(m: Int, n: Int): Int {
    val dp = Array(m) { IntArray(n) }
    dp[0][0] = 1

    // Initialize first column.
    for (i in 1 until m) {
        dp[i][0] = dp[i - 1][0]
    }
    // Initialize first row.
    for (j in 1 until n) {
        dp[0][j] = dp[0][j - 1]
    }
    for (i in 1 until m) {
        for (j in 1 until n) dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j] + dp[i][j - 1]
    }
    return dp[m - 1][n - 1]
}

// Testing code.
fun main() {
    println(gridUniqueWays(3, 3))
    println(gridUnique3Ways(3, 3))
}
