fun lbs(arr: IntArray): Int {
    val n = arr.size
    val lis = IntArray(n){1} // Initialize LIS values for all indexes as 1.
    val lds = IntArray(n){1} // Initialize LDS values for all indexes as 1.
    var max = 0

    // Populating LIS values in bottom up manner.
    for (i in 0 until n) {
        for (j in 0 until i) {
            if (arr[j] < arr[i] && lis[i] < lis[j] + 1) lis[i] = lis[j] + 1
        }
    }

    // Populating LDS values in bottom up manner.
    for (i in n - 1 downTo 1) {
        for (j in n - 1 downTo i + 1) {
            if (arr[j] < arr[i] && lds[i] < lds[j] + 1) lds[i] = lds[j] + 1
        }
    }
    for (i in 0 until n) {
        max = Math.max(max, lis[i] + lds[i] - 1)
    }
    return max
}

// Testing code.
fun main() {
    val arr = intArrayOf(1, 6, 3, 11, 1, 9, 5, 12, 3, 14, 6, 17, 3, 19, 2, 19)
    println("Length of lbs is " + lbs(arr))
}

/*
Length of lbs is 8
*/