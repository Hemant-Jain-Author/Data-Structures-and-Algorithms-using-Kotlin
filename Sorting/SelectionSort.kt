
fun selectionSort(arr: IntArray) { // sorted array created in reverse order.
    val size = arr.size
    for (i in 0 until size - 1) {
        var max = 0
        for (j in 1 until (size - i)) {
            if (arr[j] > arr[max]) {
                max = j
            }
        }
        var temp = arr[size - 1 - i]
        arr[size - 1 - i] = arr[max]
        arr[max] = temp
    }
}

fun selectionSort2(arr: IntArray) { // sorted array created in forward direction    
    val size = arr.size
    for (i in 0 until size - 1) {
        var min = i
        for (j in i+1 until size) {
            if (arr[j] < arr[min]) {
                min = j
            }
        }
        var temp = arr[i]
        arr[i] = arr[min]
        arr[min] = temp
    }
}

// Testing code.
fun main() {
    val array = intArrayOf(9, 1, 8, 2, 7, 3, 6, 4, 5)
    selectionSort(array)
    for (i in array.indices) {
        print(array[i].toString() + " ")
    }
    println()
    val array2 = intArrayOf(9, 1, 8, 2, 7, 3, 6, 4, 5)
    selectionSort2(array2)
    for (i in array2.indices) {
        print(array2[i].toString() + " ")
    }
}

/*
1 2 3 4 5 6 7 8 9 
1 2 3 4 5 6 7 8 9 
*/