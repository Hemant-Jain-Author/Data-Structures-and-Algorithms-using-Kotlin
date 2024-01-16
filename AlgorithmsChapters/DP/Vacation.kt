// days are must travel days, costs are cost of tickets.
fun minCostTravel(days: IntArray, costs: IntArray): Int {
    val n = days.size
    val max = days[n - 1]
    val dp = IntArray(max + 1)
    var j = 0
    for (i in 1..max) {
        if (days[j] == i) { // That days is definitely travelled.
            j++
            dp[i] = dp[i - 1] + costs[0]
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 7)] + costs[1])
            dp[i] = Math.min(dp[i], dp[Math.max(0, i - 30)] + costs[2])
        } else dp[i] = dp[i - 1] // day may be ignored.
    }
    return dp[max]
}

// Testing code.
fun main() {
    val days = intArrayOf(1, 3, 5, 7, 12, 20, 30)
    val costs = intArrayOf(2, 7, 20)
    println("Min cost is:" + minCostTravel(days, costs))
}

/*
Min cost is:13
*/