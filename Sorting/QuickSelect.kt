fun quickSelect(arr: IntArray, start: Int, stop: Int, k: Int) {
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
    if (k < upper) quickSelect(arr, start, upper-1, k) // pivot -1 is the upper for left sub array.
    if (k > upper) quickSelect(arr, upper+1, stop, k) // pivot + 1 is the lower for right sub array.
}

fun swap(arr: IntArray, first: Int, second: Int) {
    val temp = arr[first]
    arr[first] = arr[second]
    arr[second] = temp
}

fun quickSelect(arr: IntArray, k: Int): Int {
    quickSelect(arr, 0, arr.size - 1, k - 1)
    return arr[k - 1]
}

fun main() {
    val array = intArrayOf(3, 4, 2, 1, 6, 5, 7, 8)
    print("value at index 5 is : " + quickSelect(array, 5))
}

// value at index 5 is : 5

