fun matrixChainMulBruteForce(p: IntArray, i: Int, j: Int): Int {
    if (i == j) return 0
    var min = Int.MAX_VALUE

    // place parenthesis at different places between
    // first and last matrix, recursively calculate
    // count of multiplications for each parenthesis
    // placement and return the minimum count
    for (k in i until j) {
        val count =
            matrixChainMulBruteForce(p, i, k) + matrixChainMulBruteForce(p, k + 1, j) + p[i - 1] * p[k] * p[j]
        if (count < min) min = count
    }

    // Return minimum count
    return min
}

fun matrixChainMulBruteForce(p: IntArray, n: Int): Int {
    val i = 1
    val j = n - 1
    return matrixChainMulBruteForce(p, i, j)
}

fun matrixChainMulTD(p: IntArray, n: Int): Int {
    val dp = Array(n) { IntArray(n){Int.MAX_VALUE} }
    for (i in 1 until n) dp[i][i] = 0
    return matrixChainMulTD(dp, p, 1, n - 1)
}

fun matrixChainMulTD(dp: Array<IntArray>, p: IntArray, i: Int, j: Int): Int {
    // Base Case
    if (dp[i][j] != Int.MAX_VALUE) {
        return dp[i][j]
    }
    for (k in i until j) {
        dp[i][j] = Math.min(
            dp[i][j],
            matrixChainMulTD(dp, p, i, k) + matrixChainMulTD(dp, p, k + 1, j) + p[i - 1] * p[k] * p[j]
        )
    }
    return dp[i][j]
}

fun matrixChainMulBU(p: IntArray, n: Int): Int {
    val dp = Array(n) { IntArray(n){Int.MAX_VALUE} }
    for (i in 1 until n) dp[i][i] = 0
    for (l in 1 until n) { // l is length of range.
        var i = 1
        var j: Int = i + l
        while (j < n) {
            for (k in i until j) {
                dp[i][j] = Math.min(dp[i][j], dp[i][k] + p[i - 1] * p[k] * p[j] + dp[k + 1][j])
            }
            i++
            j++
        }
    }
    return dp[1][n - 1]
}

fun PrintOptPar(n: Int, pos: Array<IntArray>, i: Int, j: Int) {
    if (i == j) print("M" + pos[i][i] + " ") else {
        print("( ")
        PrintOptPar(n, pos, i, pos[i][j])
        PrintOptPar(n, pos, pos[i][j] + 1, j)
        print(") ")
    }
}

fun PrintOptimalParenthesis(n: Int, pos: Array<IntArray>) {
    print("OptimalParenthesis : ")
    PrintOptPar(n, pos, 1, n - 1)
    println("")
}

fun matrixChainMulBU2(p: IntArray, n: Int): Int {
    val dp = Array(n) { IntArray(n){Int.MAX_VALUE} }
    val pos = Array(n) { IntArray(n) }
    for (i in 1 until n) {
        dp[i][i] = 0
        pos[i][i] = i
    }
    for (l in 1 until n) { // l is length of range.
        var i = 1
        var j: Int = i + l
        while (j < n) {
            for (k in i until j) {
                dp[i][j] = Math.min(dp[i][j], dp[i][k] + p[i - 1] * p[k] * p[j] + dp[k + 1][j])
                pos[i][j] = k
            }
            i++
            j++
        }
    }
    PrintOptimalParenthesis(n, pos)
    return dp[1][n - 1]
}

// Testing code.
fun main() {
    val arr = intArrayOf(1, 2, 3, 4)
    val n = arr.size
    println("Matrix Chain Multiplication is: " + matrixChainMulBruteForce(arr, n))
    println("Matrix Chain Multiplication is: " + matrixChainMulTD(arr, n))
    println("Matrix Chain Multiplication is: " + matrixChainMulBU(arr, n))
    println("Matrix Chain Multiplication is: " + matrixChainMulBU2(arr, n))
}