fun lis(arr: IntArray): Int {
    val n = arr.size
    val lis = IntArray(n)
    var max = 0

    // Populating LIS values in bottom up manner.
    for (i in 0 until n) {
        lis[i] = 1 // Initialize LIS values for all indexes as 1.
        for (j in 0 until i) {
            if (arr[j] < arr[i] && lis[i] < lis[j] + 1) lis[i] = lis[j] + 1
        }
        if (max < lis[i]) // Max LIS values.
            max = lis[i]
    }
    return max
}

// Testing code.
fun main() {
    val arr = intArrayOf(10, 12, 9, 23, 25, 55, 49, 70)
    println("Length of lis is " + lis(arr))
}