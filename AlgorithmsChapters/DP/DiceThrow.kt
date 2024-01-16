fun findWays(n: Int, m: Int, V: Int): Int {
    val dp = Array(n + 1) { IntArray(V + 1) }

    // Table entries for only one dice.
    var l = 1
    while (l <= m && l <= V) {
        dp[1][l] = 1
        l++
    }

    // i is number of dice, j is Value, k value of dice.
    for (i in 2..n) {
        for (j in 1..V) {
            var k = 1
            while (k <= j && k <= m) {
                dp[i][j] += dp[i - 1][j - k]
                k++
            }
        }
    }
    return dp[n][V]
}

fun main() {
    print(findWays(4, 6, 6).toString() + " ")
}

/*
10 
*/