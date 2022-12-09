fun makePairs(nuts: IntArray, bolts: IntArray) {
    makePairs(nuts, bolts, 0, nuts.size - 1)
    println("Matched nuts and bolts are : ")
    printArray(nuts)
    printArray(bolts)
}

// Quick sort kind of approach.
fun makePairs(nuts: IntArray, bolts: IntArray, low: Int, high: Int) {
    if (low < high) {
        // Choose first element of bolts array as pivot to partition nuts.
        val pivot = partition(nuts, low, high, bolts[low])

        // Using nuts[pivot] as pivot to partition bolts.
        partition(bolts, low, high, nuts[pivot])

        // Recursively lower and upper half of nuts and bolts are matched.
        makePairs(nuts, bolts, low, pivot - 1)
        makePairs(nuts, bolts, pivot + 1, high)
    }
}

fun swap(arr: IntArray, first: Int, second: Int) {
    val temp = arr[first]
    arr[first] = arr[second]
    arr[second] = temp
}

// Partition method similar to quick sort algorithm.
fun partition(arr: IntArray, low: Int, high: Int, pivot: Int): Int {
    var i = low
    var j = low
    while (j < high) {
        if (arr[j] < pivot) {
            swap(arr, i, j)
            i++
        } else if (arr[j] == pivot) {
            swap(arr, high, j)
            j--
        }
        j++
    }
    swap(arr, i, high)
    return i
}

fun printArray(arr: IntArray) {
    for (i in arr) print("$i ")
    println()
}

fun main() {
    val nuts = intArrayOf(1, 2, 6, 5, 4, 3)
    val bolts = intArrayOf(6, 4, 5, 1, 3, 2)
    makePairs(nuts, bolts)
}

/* 
Matched nuts and bolts are : 
1 2 3 4 5 6 
1 2 3 4 5 6
*/