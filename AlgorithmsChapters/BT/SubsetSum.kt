fun printSubset(flags: BooleanArray, arr: IntArray, size: Int) {
    for (i in 0 until size) {
        if (flags[i]) print(arr[i].toString() + " ")
    }
    println()
}

fun subsetSum(arr: IntArray, n: Int, target: Int) {
    val flags = BooleanArray(n)
    subsetSum(arr, n, flags, 0, 0, target)
}

fun subsetSum(arr: IntArray, n: Int, flags: BooleanArray, sum: Int, curr: Int, target: Int) {
    if (target == sum) {
        printSubset(flags, arr, n) // Solution found.
        return
    }
    if (curr >= n || sum > target) { // constraint check and Backtracking.
        return
    }

    // Current element included.
    flags[curr] = true
    subsetSum(arr, n, flags, sum + arr[curr], curr + 1, target)
    // Current element excluded.
    flags[curr] = false
    subsetSum(arr, n, flags, sum, curr + 1, target)
}

fun main() {
    val arr = intArrayOf(15, 22, 14, 26, 32, 9, 16, 8)
    val target = 53
    val n = arr.size
    subsetSum(arr, n, target)
}

/* 
15 22 16 
15 14 16 8 
22 14 9 8 
*/