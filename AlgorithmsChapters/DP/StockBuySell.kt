fun maxProfit(arr: IntArray): Int {
    var buyProfit = -arr[0] // Buy stock profit
    var sellProfit = 0 // Sell stock profit
    val n = arr.size
    for (i in 1 until n) {
        val newBuyProfit = if (sellProfit - arr[i] > buyProfit) sellProfit - arr[i] else buyProfit
        val newSellProfit = if (buyProfit + arr[i] > sellProfit) buyProfit + arr[i] else sellProfit
        buyProfit = newBuyProfit
        sellProfit = newSellProfit
    }
    return sellProfit
}

fun maxProfitTC(arr: IntArray, t: Int): Int {
    var buyProfit = -arr[0]
    var sellProfit = 0
    val n = arr.size
    for (i in 1 until n) {
        val newBuyProfit = if (sellProfit - arr[i] > buyProfit) sellProfit - arr[i] else buyProfit
        val newSellProfit = if (buyProfit + arr[i] - t > sellProfit) buyProfit + arr[i] - t else sellProfit
        buyProfit = newBuyProfit
        sellProfit = newSellProfit
    }
    return sellProfit
}

fun maxProfit2(arr: IntArray): Int {
    val n = arr.size
    val dp = Array(n) { IntArray(2) }
    dp[0][0] = -arr[0] // Buy stock profit
    dp[0][1] = 0 // Sell stock profit
    for (i in 1 until n) {
        dp[i][0] = if (dp[i - 1][1] - arr[i] > dp[i - 1][0]) dp[i - 1][1] - arr[i] else dp[i - 1][0]
        dp[i][1] = if (dp[i - 1][0] + arr[i] > dp[i - 1][1]) dp[i - 1][0] + arr[i] else dp[i - 1][1]
    }
    return dp[n - 1][1]
}

fun maxProfitTC2(arr: IntArray, t: Int): Int {
    val n = arr.size
    val dp = Array(n) { IntArray(2) }
    dp[0][0] = -arr[0]
    dp[0][1] = 0
    for (i in 1 until n) {
        dp[i][0] = if (dp[i - 1][1] - arr[i] > dp[i - 1][0]) dp[i - 1][1] - arr[i] else dp[i - 1][0]
        dp[i][1] = if (dp[i - 1][0] + arr[i] - t > dp[i - 1][1]) dp[i - 1][0] + arr[i] - t else dp[i - 1][1]
    }
    return dp[n - 1][1]
}

// Testing code.
fun main() {
    val arr = intArrayOf(10, 12, 9, 23, 25, 55, 49, 70)
    println("Total profit: " + maxProfit(arr))
    println("Total profit: " + maxProfit2(arr))
    println("Total profit: " + maxProfitTC(arr, 2))
    println("Total profit: " + maxProfitTC2(arr, 2))
}

/*
Total profit: 69
Total profit: 69
Total profit: 63
Total profit: 63 
*/