
private fun mergeSrtUtil(arr: IntArray, tempArray: IntArray, lowerIndex: Int, upperIndex: Int) {
    if (lowerIndex >= upperIndex) {
        return
    }
    val middleIndex = (lowerIndex + upperIndex) / 2
    mergeSrtUtil(arr, tempArray, lowerIndex, middleIndex)
    mergeSrtUtil(arr, tempArray, middleIndex + 1, upperIndex)

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

fun MergeSort(arr: IntArray) {
    val size = arr.size
    val tempArray = IntArray(size)
    mergeSrtUtil(arr, tempArray, 0, size - 1)
}

fun main(args: Array<String>) {
    val array = intArrayOf(3, 4, 2, 1, 6, 5, 7, 8, 1, 1)
    MergeSort(array)
    for (i in array.indices) {
        print(array[i].toString() + " ")
    }
}