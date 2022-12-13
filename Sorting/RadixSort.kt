fun radixSort(arr: IntArray) {
    val n = arr.size
    val m = getMax(arr, n)

    // Counting sort for every digit.
    // The dividend passed is used to calculate current working digit.
    var div = 1
    while (m / div > 0) {
        countSort(arr, n, div)
        div *= 10
    }
}

fun getMax(arr: IntArray, n: Int): Int {
    var max = arr[0]
    for (i in 1 until n) if (max < arr[i]) max = arr[i]
    return max
}

fun countSort(arr: IntArray, n: Int, dividend: Int) {
    val temp: IntArray = arr.clone()
    val count = IntArray(10)

    // Store count of occurrences in count array.
    // (number / dividend) % 10 is used to find the working digit.
    for (i in 0 until n) count[temp[i] / dividend % 10]++

    // Change count[i] so that count[i] contains 
    // number of elements till index i in output.
    for (i in 1..9) count[i] += count[i - 1]

    // Copy content to input arr.
    for (i in n - 1 downTo 0) {
        val index = temp[i] / dividend % 10
        arr[count[index] - 1] = temp[i]
        count[index]--
    }
}

// Testing code.
fun main() {
    val array = intArrayOf(100, 49, 65, 91, 702, 29, 4, 55)
    radixSort(array)
    for (i in array.indices) {
        print(array[i].toString() + " ")
    }
}

// 4 29 49 55 65 91 100 702