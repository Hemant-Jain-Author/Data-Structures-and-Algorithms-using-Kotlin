fun maxCost01Knapsack(wt: IntArray, cost: IntArray, capacity: Int): Int {
    val n = wt.size
    return maxCost01KnapsackUtil(wt, cost, n, capacity)
}

fun maxCost01KnapsackUtil(wt: IntArray, cost: IntArray, n: Int, capacity: Int): Int {
    // Base Case
    if (n == 0 || capacity == 0) return 0

    // Return the maximum of two cases:
    // (1) nth item is included
    // (2) nth item is not included
    var first = 0
    if (wt[n - 1] <= capacity) first = cost[n - 1] + maxCost01KnapsackUtil(wt, cost, n - 1, capacity - wt[n - 1])
    val second = maxCost01KnapsackUtil(wt, cost, n - 1, capacity)
    return Math.max(first, second)
}

fun maxCost01KnapsackTD(wt: IntArray, cost: IntArray, capacity: Int): Int {
    val n = wt.size
    val dp = Array(capacity + 1) { IntArray(n + 1) }
    return maxCost01KnapsackTD(dp, wt, cost, n, capacity)
}

fun maxCost01KnapsackTD(dp: Array<IntArray>, wt: IntArray, cost: IntArray, i: Int, w: Int): Int {
    if (w == 0 || i == 0) return 0
    if (dp[w][i] != 0) return dp[w][i]

    // Their are two cases:
    // (1) ith item is included
    // (2) ith item is not included
    var first = 0
    if (wt[i - 1] <= w) first = maxCost01KnapsackTD(dp, wt, cost, i - 1, w - wt[i - 1]) + cost[i - 1]
    val second = maxCost01KnapsackTD(dp, wt, cost, i - 1, w)
    return Math.max(first, second).also { dp[w][i] = it }
}

fun maxCost01KnapsackBU(wt: IntArray, cost: IntArray, capacity: Int): Int {
    val n = wt.size
    val dp = Array(capacity + 1) { IntArray(n + 1) }

    // Build table dp[][] in bottom up approach.
    // Weights considered against capacity.
    for (w in 1..capacity) {
        for (i in 1..n) {
            // Their are two cases:
            // (1) ith item is included
            // (2) ith item is not included
            var first = 0
            if (wt[i - 1] <= w) first = dp[w - wt[i - 1]][i - 1] + cost[i - 1]
            val second = dp[w][i - 1]
            dp[w][i] = Math.max(first, second)
        }
    }
    printItems(dp, wt, cost, n, capacity)
    return dp[capacity][n] // Number of weights considered and final capacity.
}

fun printItems(dp: Array<IntArray>, wt: IntArray, cost: IntArray, n: Int, capa: Int) {
    var capacity = capa
    var totalCost = dp[capacity][n]
    print("Selected items are:")
    for (i in n - 1 downTo 1) {
        if (totalCost != dp[capacity][i - 1]) {
            print(" (wt:" + wt[i] + ", cost:" + cost[i] + ")")
            capacity -= wt[i]
            totalCost -= cost[i]
        }
    }
}

fun KS01UnboundBU(wt: IntArray, cost: IntArray, capacity: Int): Int {
    val n = wt.size
    val dp = IntArray(capacity + 1)

    // Build table dp[] in bottom up approach.
    // Weights considered against capacity.
    for (w in 1..capacity) {
        for (i in 1..n) {
            // Their are two cases:
            // (1) ith item is included
            // (2) ith item is not included
            if (wt[i - 1] <= w) dp[w] = Math.max(dp[w], dp[w - wt[i - 1]] + cost[i - 1])
        }
    }
    // printItems(dp, wt, cost, n, capacity)
    return dp[capacity] // Number of weights considered and final capacity.
}

// Testing code.
fun main() {
    val wt = intArrayOf(10, 40, 20, 30)
    val cost = intArrayOf(60, 40, 90, 120)
    val capacity = 50
    var maxCost = KS01UnboundBU(wt, cost, capacity).toDouble()
    println("Maximum cost obtained = $maxCost")
    maxCost = maxCost01Knapsack(wt, cost, capacity).toDouble()
    println("Maximum cost obtained = $maxCost")
    maxCost = maxCost01KnapsackBU(wt, cost, capacity).toDouble()
    println("Maximum cost obtained = $maxCost")
    maxCost = maxCost01KnapsackTD(wt, cost, capacity).toDouble()
    println("Maximum cost obtained = $maxCost")
}

/*
Maximum cost obtained = 300.0
Maximum cost obtained = 210.0
Selected items are: (wt:30, cost:120) (wt:20, cost:90)Maximum cost obtained = 210.0
Maximum cost obtained = 210.0
*/
