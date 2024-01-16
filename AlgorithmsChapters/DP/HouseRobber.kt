fun maxRobbery(house: IntArray): Int {
    val n = house.size
    val dp = IntArray(n)
    dp[0] = house[0]
    dp[1] = house[1]
    dp[2] = dp[0] + house[2]
    for (i in 3 until n) {
        dp[i] = Math.max(dp[i - 2], dp[i - 3]) + house[i]
    }
    return Math.max(dp[n - 1], dp[n - 2])
}

fun maxRobbery2(house: IntArray): Int {
    val n = house.size
    val dp = Array(n) { IntArray(2) }
    dp[0][1] = house[0]
    dp[0][0] = 0
    for (i in 1 until n) {
        dp[i][1] = Math.max(dp[i - 1][0] + house[i], dp[i - 1][1])
        dp[i][0] = dp[i - 1][1]
    }
    return Math.max(dp[n - 1][1], dp[n - 1][0])
}

// Testing code.
fun main() {
    val arr = intArrayOf(10, 12, 9, 23, 25, 55, 49, 70)
    println("Total cash: " + maxRobbery(arr))
    println("Total cash: " + maxRobbery2(arr))
}

/* 
Total cash: 160
Total cash: 160
*/