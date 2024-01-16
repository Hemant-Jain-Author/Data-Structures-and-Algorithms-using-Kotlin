
fun minCostBstTD(arr: IntArray): Int {
    val n = arr.size
    val dp = Array(n) { IntArray(n){Int.MAX_VALUE} }
    val max = Array(n) { IntArray(n){Int.MIN_VALUE} }
    for (i in 0 until n) max[i][i] = arr[i]
    return minCostBstTD(dp, max, 0, n - 1, arr)
}

fun minCostBstTD(dp: Array<IntArray>, max: Array<IntArray>, i: Int, j: Int, arr: IntArray?): Int {
    if (j <= i) return 0
    if (dp[i][j] != Int.MAX_VALUE) return dp[i][j]
    for (k in i until j) {
        dp[i][j] = Math.min( dp[i][j],
            minCostBstTD(dp, max, i, k, arr) +
            minCostBstTD(dp, max, k + 1, j, arr) +
            maxVal(max,i,k) * maxVal(max, k + 1, j))
    }
    return dp[i][j]
}

fun maxVal(max: Array<IntArray>, i: Int, j: Int): Int {
    if (max[i][j] != Int.MIN_VALUE) return max[i][j]
    for (k in i until j) {
        max[i][j] =
            java.lang.Integer.max(max[i][j], java.lang.Integer.max(maxVal(max, i, k), maxVal(max, k + 1, j)))
    }
    return max[i][j]
}


fun minCostBstBU(arr: IntArray): Int {
    val n = arr.size
    val dp = Array(n) { IntArray(n) }
    val max = Array(n) { IntArray(n) }
    for (i in 0 until n) max[i][i] = arr[i]
    for (l in 1 until n) { // l is length of range.
        var i = 0
        var j: Int = i + l
        while (j < n) {
            dp[i][j] = Int.MAX_VALUE
            for (k in i until j) {
                dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + max[i][k] * max[k + 1][j])
                max[i][j] = Math.max(max[i][k], max[k + 1][j])
            }
            i++
            j++
        }
    }
    return dp[0][n - 1]
}

// Testing code.
fun main() {
    val arr = intArrayOf(6, 2, 4)
    println("Total cost: " + minCostBstTD(arr))
    println("Total cost: " + minCostBstBU(arr))
}