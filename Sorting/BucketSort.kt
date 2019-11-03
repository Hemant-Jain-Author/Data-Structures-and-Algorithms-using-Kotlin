fun BucketSort(array: IntArray, lowerRange: Int, upperRange: Int) {
    var i: Int
    var j: Int
    val size = array.size
    val range = upperRange - lowerRange
    val count = IntArray(range)

    i = 0
    while (i < size) {
        count[array[i] - lowerRange]++
        i++
    }

    j = 0
    i = 0
    while (i < range) {
        while (count[i] > 0) {
            array[j++] = i + lowerRange
            count[i]--
        }
        i++
    }
}
fun main(args: Array<String>) {
    val array = intArrayOf(23, 24, 22, 21, 26, 25, 27, 28, 21, 21)
    BucketSort(array, 20, 30)
    for (i in array.indices) {
        print(array[i].toString() + " ")
    }
}
