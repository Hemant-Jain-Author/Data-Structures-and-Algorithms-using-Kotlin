class QuickSort(array:IntArray) {
    private val arr:IntArray
    init{
        arr = array
    }
    private fun swap(arr:IntArray, first:Int, second:Int) {
        val temp = arr[first]
        arr[first] = arr[second]
        arr[second] = temp
    }
    private fun quickSortUtil(arr:IntArray, lowerIn:Int, upperIn:Int) {
        var lower = lowerIn
        var upper = upperIn

        if (upper <= lower)
            return

        val pivot = arr[lower]
        var start = lower
        var stop = upper
        while (lower < upper)
        {
            while (arr[lower] <= pivot && lower < upper)
            {
                lower++
            }
            while (arr[upper] > pivot && lower <= upper)
            {
                upper--
            }
            if (lower < upper)
            {
                swap(arr, upper, lower)
            }
        }
        swap(arr, upper, start) // upper is the pivot position
        quickSortUtil(arr, start, upper - 1) // pivot -1 is the upper for left sub array.
        quickSortUtil(arr, upper + 1, stop) // pivot + 1 is the lower for right sub array.
    }
    fun sort() {
        val size = arr.size
        quickSortUtil(arr, 0, size - 1)
    }
}

fun main(args : Array<String>){
    val array = intArrayOf(3, 4, 2, 1, 6, 5, 7, 8, 1, 1)
    val m = QuickSort(array)
    m.sort()
    for (i in array.indices)
    {
        print((array[i]).toString() + " ")
    }
}