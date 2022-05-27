import java.util.Arrays

fun minCoins(coins: IntArray, n: Int, valueIn: Int): Int { // Greedy may be wrong.
    var value = valueIn
    if (value <= 0) return 0
    var count = 0
    Arrays.sort(coins)
    var i = n - 1
    while (i >= 0 && value > 0) {
        if (coins[i] <= value) {
            count++
            value -= coins[i]
        } else {
            i--
        }
    }
    return if (value == 0) count else -1
}

fun minCoins2(coins: IntArray, n: Int, value: Int): Int { // Brute force.
    if (value == 0) return 0
    var count = Int.MAX_VALUE
    for (i in 0 until n) {
        if (coins[i] <= value) {
            val subCount = minCoins2(coins, n, value - coins[i])
            if (subCount >= 0) count = Math.min(count, subCount + 1)
        }
    }
    return if (count != Int.MAX_VALUE) count else -1
}

fun minCoinsTD(coins: IntArray, n: Int, value: Int): Int {
    val count = IntArray(value + 1)
    Arrays.fill(count, Int.MAX_VALUE)
    count[0] = 0 // zero val need zero coins.
    return minCoinsTD(count, coins, n, value)
}

fun minCoinsTD(count: IntArray, coins: IntArray, n: Int, value: Int): Int {
    // Base Case
    if (count[value] != Int.MAX_VALUE) {
        return count[value]
    }

    // Recursion
    for (i in 0 until n) { // For all possible coins
        if (coins[i] <= value) { // check validity of a sub-problem
            val subCount = minCoinsTD(count, coins, n, value - coins[i])
            if (subCount != Int.MAX_VALUE && count[value] > subCount + 1) count[value] = subCount + 1
        }
    }
    return count[value]
}

fun minCoinsBU(coins: IntArray, n: Int, value: Int): Int { // DP bottom up approach.
    val count = IntArray(value + 1)
    Arrays.fill(count, Int.MAX_VALUE)
    count[0] = 0 // Base value.
    for (i in 1..value) {
        for (j in 0 until n) {
            // For all coins smaller than or equal to i.
            if (coins[j] <= i && count[i - coins[j]] != Int.MAX_VALUE && count[i] > count[i - coins[j]] + 1) {
                count[i] = count[i - coins[j]] + 1
            }
        }
    }
    return if (count[value] != Int.MAX_VALUE) count[value] else -1
}

fun printCoinsUtil(cvalue: IntArray, value: Int) {
    if (value > 0) {
        printCoinsUtil(cvalue, value - cvalue[value])
        print(cvalue[value].toString() + " ")
    }
}

fun printCoins(cvalue: IntArray, value: Int) {
    print("Coins are : ")
    printCoinsUtil(cvalue, value)
    println()
}

fun minCoinsBU2(coins: IntArray, n: Int, value: Int): Int { // DP bottom up approach.
    val count = IntArray(value + 1)
    val cvalue = IntArray(value + 1)
    Arrays.fill(count, Int.MAX_VALUE)
    Arrays.fill(cvalue, Int.MAX_VALUE)
    count[0] = 0 // Base value.
    for (i in 1..value) {
        for (j in 0 until n) {
            // For all coins smaller than or equal to i.
            if (coins[j] <= i && count[i - coins[j]] != Int.MAX_VALUE && count[i] > count[i - coins[j]] + 1) {
                count[i] = count[i - coins[j]] + 1
                cvalue[i] = coins[j]
            }
        }
    }
    if (count[value] == Int.MAX_VALUE) return -1
    printCoins(cvalue, value)
    return count[value]
}

fun main() {
    val coins = intArrayOf(5, 6)
    val value = 16
    val n = coins.size
    println("Count is : " + minCoins(coins, n, value))
    println("Count is : " + minCoins2(coins, n, value))
    println("Count is : " + minCoinsTD(coins, n, value))
    println("Count is : " + minCoinsBU(coins, n, value))
    println("Count is : " + minCoinsBU2(coins, n, value))
}

/*
Count is : -1
Count is : 3
Count is : 3
Count is : 3
Coins are : 6 5 5 
Count is : 3
*/
