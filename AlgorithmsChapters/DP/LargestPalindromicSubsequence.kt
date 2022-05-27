fun largestPalindromicSubseq(str: String): Int {
    val n = str.length
    val dp = Array(n) { IntArray(n) }
    for (i in 0 until n)  // each char is itself palindromic with length 1
        dp[i][i] = 1
    for (l in 1 until n) {
        var i = 0
        var j: Int = l
        while (j < n) {
            if (str[i] == str[j]) dp[i][j] = dp[i + 1][j - 1] + 2 else dp[i][j] = Math.max(
                dp[i + 1][j],
                dp[i][j - 1]
            )
            i++
            j++
        }
    }
    return dp[0][n - 1]
}

// Testing code.
fun main() {
    val str = "ABCAUCBCxxCBA"
    println("Largest Palindromic Subseq: " + largestPalindromicSubseq(str))
}