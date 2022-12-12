fun less(value1: Int, value2: Int): Boolean {
    return value1 < value2
}

fun greater(value1: Int, value2: Int): Boolean {
    return value1 > value2
}

fun bubbleSort(arr: IntArray) {
    val size = arr.size
    var i = 0
    while (i < size - 1) {
        var j = 0
        while (j < size - i - 1) {
            if (greater(arr[j], arr[j + 1])) {
                /* Swapping */
                var temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
            }
            j++
        }
        i++
    }
}

fun bubbleSort2(arr: IntArray) {
    val size = arr.size
    var swapped = 1
    var i = 0
    while (i < size - 1 && swapped == 1) {
        swapped = 0
        var j = 0
        while (j < size - i - 1) {
            if (greater(arr[j], arr[j + 1])) {
                var temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
                swapped = 1
            }
            j++
        }
        i++
    }
}

// Testing code
fun main() {
    val array = intArrayOf(9, 1, 8, 2, 7, 3, 6, 4, 5)
    bubbleSort(array)
    for (i in array.indices) {
        print(array[i].toString() + " ")
    }
    println()
    val array2 = intArrayOf(9, 1, 8, 2, 7, 3, 6, 4, 5)
    bubbleSort2(array2)
    for (i in array2.indices) {
        print(array2[i].toString() + " ")
    }
}