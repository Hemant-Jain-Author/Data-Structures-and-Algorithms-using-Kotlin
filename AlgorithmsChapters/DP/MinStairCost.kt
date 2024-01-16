fun minStairCost(cost: IntArray, n: Int): Int {
    // base case
    if (n == 1) return cost[0]
    val dp = IntArray(n)
    dp[0] = cost[0]
    dp[1] = cost[1]
    for (i in 2 until n) {
        dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i]
    }
    return Math.min(dp[n - 2], dp[n - 1])
}

// Testing code.
fun main() {
    val a = intArrayOf(1, 5, 6, 3, 4, 7, 9, 1, 2, 11)
    val n = a.size
    print("minStairCost : " + minStairCost(a, n))
}

/*
minStairCost : 18
*/