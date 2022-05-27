fun quickSelect(arr: IntArray, low: Int, up: Int, k: Int) {
    var lower = low
    var upper = up
    if (upper <= lower) return

    val pivot = arr[lower]
    val start = lower
    val stop = upper

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
    if (k < upper) quickSelect(arr, start, upper - 1, k) // pivot -1 is the upper for left sub array.
    if (k > upper) quickSelect(arr, upper + 1, stop, k) // pivot + 1 is the lower for right sub array.
}

fun swap(arr: IntArray, first: Int, second: Int) {
    val temp = arr[first]
    arr[first] = arr[second]
    arr[second] = temp
}

fun QuickSelect(arr: IntArray, k: Int): Int {
    quickSelect(arr, 0, arr.size - 1, k - 1)
    return arr[k - 1]
}

fun main() {
    val array = intArrayOf(3, 4, 2, 1, 6, 5, 7, 8)
    print("value at index 5 is : " + QuickSelect(array, 5))
}
