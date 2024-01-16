fun LCSubSeq(st1: String, st2: String): Int {
    val X: CharArray = st1.toCharArray()
    val Y: CharArray = st2.toCharArray()
    val m = st1.length
    val n = st2.length
    val dp = Array(m + 1) { IntArray(n + 1) } // Dynamic programming array.
    val p = Array(m + 1) { IntArray(n + 1) } // For printing the substring.

    // Fill dp array in bottom up fashion.
    for (i in 1..m) {
        for (j in 1..n) {
            if (X[i - 1] == Y[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1
                p[i][j] = 0
            } else {
                dp[i][j] = if (dp[i - 1][j] > dp[i][j - 1]) dp[i - 1][j] else dp[i][j - 1]
                p[i][j] = if (dp[i - 1][j] > dp[i][j - 1]) 1 else 2
            }
        }
    }
    PrintLCS(p, X, m, n)
    return dp[m][n]
}

fun PrintLCS(p: Array<IntArray>, X: CharArray, i: Int, j: Int) {
    if (i == 0 || j == 0) return
    if (p[i][j] == 0) {
        PrintLCS(p, X, i - 1, j - 1)
        print(X[i - 1])
    } else if (p[i][j] == 1) PrintLCS(p, X, i - 1, j) else PrintLCS(p, X, i, j - 1)
}

// Testing code.
fun main() {
    val X = "carpenter"
    val Y = "sharpener"
    println(LCSubSeq(X, Y))
}