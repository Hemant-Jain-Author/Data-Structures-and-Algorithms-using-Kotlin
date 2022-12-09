fun greater(value1: Int, value2: Int): Boolean {
    return value1 > value2
}

fun SelectionSort(arr: IntArray) { // sorted array created in reverse order.
    val size = arr.size
    var i = 0
    while (i < size - 1) {
        var max = 0
        var j = 1
        while (j < size - i) {
            if (arr[j] > arr[max]) {
                max = j
            }
            j++
        }
        var temp = arr[size - 1 - i]
        arr[size - 1 - i] = arr[max]
        arr[max] = temp
        i++
    }
}

fun SelectionSort2(arr: IntArray) { // sorted array created in forward direction    
    val size = arr.size
    var i = 0
    while (i < size - 1) {
        var min = i
        var j = i + 1
        while (j < size) {
            if (arr[j] < arr[min]) {
                min = j
            }
            j++
        }
        var temp = arr[i]
        arr[i] = arr[min]
        arr[min] = temp
        i++
    }
}

// Testing code.
fun main() {
    val array = intArrayOf(9, 1, 8, 2, 7, 3, 6, 4, 5)
    SelectionSort(array)
    for (i in array.indices) {
        print(array[i].toString() + " ")
    }
    println()
    val array2 = intArrayOf(9, 1, 8, 2, 7, 3, 6, 4, 5)
    SelectionSort2(array2)
    for (i in array2.indices) {
        print(array2[i].toString() + " ")
    }
}

/*
1 2 3 4 5 6 7 8 9 
1 2 3 4 5 6 7 8 9 
*/