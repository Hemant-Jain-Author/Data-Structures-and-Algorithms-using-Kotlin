fun greater(value1: Int, value2: Int): Boolean {
    return value1 > value2
}

fun shellSort(arr: IntArray) {
    val n = arr.size
    // Gap starts with n/2 and half in each iteration.
    var gap = n / 2

    while (gap > 0) {
        // Do a gapped insertion sort.
        var i = gap
        while (i < n) {
            val curr = arr[i]

            // Shift elements of already sorted list
            // to find right position for curr value.
            var j: Int
            j = i
            while (j >= gap && greater(arr[j - gap], curr)) {
                arr[j] = arr[j - gap]
                j -= gap
            }
            // Put current value in its correct location
            arr[j] = curr
            i += 1
        }
        gap /= 2
    }
}

fun main() {
    val array = intArrayOf(36, 32, 11, 6, 19, 31, 17, 3)
    shellSort(array)
    for (i in array.indices) {
        print(array[i].toString() + " ")
    }
}

/*
3 6 11 17 19 31 32 36 
*/