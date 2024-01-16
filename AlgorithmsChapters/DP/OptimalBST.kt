fun optBstCost(freq: IntArray, i: Int, j: Int): Int {
    if (i > j) return 0
    if (j == i) // one element in this subarray
        return freq[i]
    var min = Int.MAX_VALUE
    for (r in i..j) {
        min = Math.min(min, optBstCost(freq, i, r - 1) + optBstCost(freq, r + 1, j))
    }
    return min + sum(freq, i, j)
}

fun optBstCost(freq: IntArray): Int {
    val n = freq.size
    return optBstCost(freq, 0, n - 1)
}

fun optBstCostTD(freq: IntArray): Int {
    val n = freq.size
    val cost = Array(n) { IntArray(n){Int.MAX_VALUE} }
    for (i in 0 until n) cost[i][i] = freq[i]
    return optBstCostTD(freq, cost, 0, n - 1)
}

fun optBstCostTD(freq: IntArray, cost: Array<IntArray>, i: Int, j: Int): Int {
    if (i > j) return 0
    if (cost[i][j] != Int.MAX_VALUE) return cost[i][j]
    val s = sum(freq, i, j)
    for (r in i..j) {
        cost[i][j] = Math.min(
            cost[i][j],
            optBstCostTD(freq, cost, i, r - 1) + optBstCostTD(freq, cost, r + 1, j) + s
        )
    }
    return cost[i][j]
}

fun sum(freq: IntArray, i: Int, j: Int): Int {
    var s = 0
    for (k in i..j) s += freq[k]
    return s
}

fun sumInit(freq: IntArray, n: Int): IntArray {
    val sum = IntArray(n)
    sum[0] = freq[0]
    for (i in 1 until n) sum[i] = sum[i - 1] + freq[i]
    return sum
}

fun sumRange(sum: IntArray, i: Int, j: Int): Int {
    return if (i == 0) sum[j] else sum[j] - sum[i - 1]
}

fun optBstCostBU(freq: IntArray): Int {
    val n = freq.size
    val cost = Array(n) { IntArray(n){Int.MAX_VALUE} }
    for (i in 0 until n) cost[i][i] = freq[i]
    for (l in 1 until n) { // l is length of range.
        var i = 0
        var j: Int = i + l
        while (j < n) {
            var sm = sum(freq, i, j)
            for (r in i..j) {
                cost[i][j] = Math.min(
                    cost[i][j],
                    sm + (if (r - 1 >= i) cost[i][r - 1] else 0) + if (r + 1 <= j) cost[r + 1][j] else 0
                )
            }
            i++
            j++
        }
    }
    return cost[0][n - 1]
}

fun optBstCostBU2(freq: IntArray): Int {
    val n = freq.size
    val cost = Array(n) { IntArray(n){Int.MAX_VALUE} }
    val sumArr = sumInit(freq, n)
    for (i in 0 until n) cost[i][i] = freq[i]
    for (l in 1 until n) { // l is length of range.
        var i = 0
        var j: Int = i + l
        while (j < n) {
            var sm = sumRange(sumArr, i, j)
            for (r in i..j) {
                cost[i][j] = Math.min(
                    cost[i][j],
                    sm + (if (r - 1 >= i) cost[i][r - 1] else 0) + if (r + 1 <= j) cost[r + 1][j] else 0
                )
            }
            i++
            j++
        }
    }
    return cost[0][n - 1]
}

// Testing code.
fun main() {
    val freq = intArrayOf(30, 10, 40)
    println("OBST cost:" + optBstCost(freq))
    println("OBST cost:" + optBstCostTD(freq))
    println("OBST cost:" + optBstCostBU(freq))
    println("OBST cost:" + optBstCostBU2(freq))
}

/*
OBST cost:130
OBST cost:130
OBST cost:130
OBST cost:130
*/