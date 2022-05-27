fun min(x: Int, y: Int, z: Int): Int {
    return Math.min(Math.min(x, y), z)
}

fun editDist(str1: String, str2: String): Int {
    val m = str1.length
    val n = str2.length
    return editDist(str1, str2, m, n)
}

fun editDist(str1: String, str2: String, m: Int, n: Int): Int {
    if (m == 0 || n == 0) // If any one string is empty, then empty the other string.
        return m + n

    // If last characters of both strings are same, ignore last characters.
    return if (str1[m - 1] == str2[n - 1]) editDist(str1, str2, m - 1, n - 1) else 1 + min(
        editDist(str1, str2, m, n - 1),  // Insert
        editDist(str1, str2, m - 1, n),  // Remove
        editDist(str1, str2, m - 1, n - 1)
    )

    // If last characters are not same, consider all three operations:
    // (1) Insert last char of second into first.
    // (2) Remove last char of first.
    // (3) Replace last char of first with second.
    // Replace
}

fun editDistDP(str1: String, str2: String): Int {
    val m = str1.length
    val n = str2.length
    val dp = Array(m + 1) { IntArray(n + 1) }

    // Fill dp[][] in bottom up manner.
    for (i in 0..m) {
        for (j in 0..n) {
            // If any one string is empty, then empty the other string.
            if (i == 0 || j == 0) dp[i][j] = i + j else if (str1[i - 1] == str2[j - 1]) dp[i][j] =
                dp[i - 1][j - 1] else dp[i][j] = 1 + min(
                dp[i][j - 1],  // Insert
                dp[i - 1][j],  // Remove
                dp[i - 1][j - 1]
            ) // Replace
        }
    }
    return dp[m][n]
}

// Testing code.
fun main() {
    val str1 = "sunday"
    val str2 = "saturday"
    println(editDist(str1, str2))
    println(editDistDP(str1, str2))
}
