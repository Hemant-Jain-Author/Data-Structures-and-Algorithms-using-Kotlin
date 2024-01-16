
fun mergeSort(arr: IntArray) {
    val size = arr.size
    val tempArray = IntArray(size)
    mergeSortUtil(arr, tempArray, 0, size - 1)
}

fun mergeSortUtil(arr: IntArray, tempArray: IntArray, lowerIndex: Int, upperIndex: Int) {
    if (lowerIndex >= upperIndex) {
        return
    }
    val middleIndex = (lowerIndex + upperIndex) / 2
    mergeSortUtil(arr, tempArray, lowerIndex, middleIndex)
    mergeSortUtil(arr, tempArray, middleIndex + 1, upperIndex)
    merge(arr, tempArray, lowerIndex, middleIndex, upperIndex)
}

fun merge(arr: IntArray, tempArray: IntArray, lowerIndex: Int, middleIndex: Int, upperIndex: Int) {
    var lowerStart = lowerIndex
    var upperStart = middleIndex + 1
    var count = lowerIndex
    while (lowerStart <= middleIndex && upperStart <= upperIndex) {
        if (arr[lowerStart] < arr[upperStart]) {
            tempArray[count++] = arr[lowerStart++]
        } else {
            tempArray[count++] = arr[upperStart++]
        }
    }
    while (lowerStart <= middleIndex) {
        tempArray[count++] = arr[lowerStart++]
    }
    while (upperStart <= upperIndex) {
        tempArray[count++] = arr[upperStart++]
    }
    for (i in lowerIndex..upperIndex) {
        arr[i] = tempArray[i]
    }
}

// Testing code
fun main() {
    val array = intArrayOf(3, 4, 2, 1, 6, 5, 7, 8)
    mergeSort(array)
    for (i in array.indices) {
        print(array[i].toString() + " ")
    }
}
