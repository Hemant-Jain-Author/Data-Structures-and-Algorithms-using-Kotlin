fun CountSort(arr: IntArray, lowerRange: Int, upperRange: Int) {
    val size = arr.size
    val range = upperRange - lowerRange
    val count = IntArray(range)
    var i = 0
    while (i < size) {
        count[arr[i] - lowerRange]++
        i++
    }
    
    i = 0
    var j = 0
    while (i < range) {
        while (count[i] > 0) {
            arr[j++] = i + lowerRange
            count[i]--
        }
        i++
    }
}

// Testing code
fun main() {
    val array = intArrayOf(23, 24, 22, 21, 26, 25, 27, 28, 21, 21)
    CountSort(array, 20, 30)
    for (i in array.indices) {
        print(array[i].toString() + " ")
    }
}
