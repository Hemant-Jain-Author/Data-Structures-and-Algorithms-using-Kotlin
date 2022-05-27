fun largestPalinSubstr(str: String): Int {
    val n = str.length
    val dp = Array(n) { IntArray(n) }
    for (i in 0 until n) dp[i][i] = 1
    var max = 1
    var start = 0
    for (l in 1 until n) {
        var i = 0
        var j: Int = i + l
        while (j < n) {
            if (str[i] == str[j] && dp[i + 1][j - 1] == j - i - 1) {
                dp[i][j] = dp[i + 1][j - 1] + 2
                if (dp[i][j] > max) {
                    max = dp[i][j] // Keeping track of max length and
                    start = i // starting position of sub-string.
                }
            } else {
                dp[i][j] = 0
            }
            i++
            j++
        }
    }
    println("Max Length Palindromic Substrings : " + str.substring(start, start + max))
    return max
}

// Testing code.
fun main() {
    val str = "ABCAUCBCxxCBA"
    println("Max Palindromic Substrings len: " + largestPalinSubstr(str))
}
