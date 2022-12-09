fun fibonacci(n: Int): Int {
    return if (n < 2) {
        n
    } else fibonacci(n - 1) + fibonacci(n - 2)
}

fun fibonacciBU(n: Int): Int {
    if (n < 2) return n
    val dp = IntArray(n + 1)
    dp[0] = 0
    dp[1] = 1
    for (i in 2..n) {
        dp[i] = dp[i - 2] + dp[i - 1]
    }
    return dp[n]
}

fun fibonacciBU2(n: Int): Int {
    if (n < 2) return n
    var first = 0
    var second = 1
    var temp = 0
    for (i in 2..n) {
        temp = first + second
        first = second
        second = temp
    }
    return temp
}

fun fibonacciTD(n: Int): Int {
    val dp = IntArray(n + 1)
    fibonacciTD(n, dp)
    return dp[n]
}

fun fibonacciTD(n: Int, dp: IntArray): Int {
    if (n < 2) return n.also { dp[n] = it }
    if (dp[n] != 0) return dp[n]
    dp[n] = fibonacciTD(n - 1, dp) + fibonacciTD(n - 2, dp)
    return dp[n]
}

fun main() {
    println(fibonacci(10))
    println(fibonacciBU(10))
    println(fibonacciBU2(10))
    println(fibonacciTD(10))
}

/*
55
55
55
55
*/