fun quickSort(arr: IntArray, start: Int, stop: Int) {
    if (stop <= start) return

    val pivot = arr[start]
    var lower = start 
    var upper = stop

    while (lower < upper) {
        while (arr[lower] <= pivot && lower < upper) {
            lower++
        }
        while (arr[upper] > pivot && lower <= upper) {
            upper--
        }
        if (lower < upper) {
            swap(arr, upper, lower)
        }
    }
    swap(arr, upper, start) // upper is the pivot position
    quickSort(arr, start, upper - 1) // pivot -1 is the upper for left sub array.
    quickSort(arr, upper + 1, stop) // pivot + 1 is the lower for right sub array
}

fun quickSort(arr: IntArray) {
    quickSort(arr, 0, arr.size - 1)
}

fun swap(arr: IntArray, first: Int, second: Int) {
    val temp = arr[first]
    arr[first] = arr[second]
    arr[second] = temp
}

fun main() {
    val array = intArrayOf(3, 4, 2, 1, 6, 5, 7, 8, 10, 9)
    quickSort(array)
    for (i in array.indices) {
        print(array[i].toString() + " ")
    }
}

// 1 2 3 4 5 6 7 8 9 10 