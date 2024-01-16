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

fun swap(arr: IntArray, first: Int, second: Int) {
    val temp = arr[first]
    arr[first] = arr[second]
    arr[second] = temp
}

fun printArray(arr: IntArray) {
    for (i in arr) print("$i ")
    println()
}

fun main() {
    val nuts = intArrayOf(1, 2, 6, 5, 4, 3)
    val bolts = intArrayOf(6, 4, 5, 1, 3, 2)
    makePairs(nuts, bolts)

    println(power(5, 2))
}

/* 
Matched nuts and bolts are : 
1 2 3 4 5 6 
1 2 3 4 5 6
*/



fun power(x : Int, n : Int) : Int{
    var value : Int;
    if (n == 0) {
        return 1;
    } else if (n % 2 == 0) {
        value = power(x, n / 2);
        return (value * value);
    } else {
        value = power(x, n / 2);
        return (x * value * value);
    }
}