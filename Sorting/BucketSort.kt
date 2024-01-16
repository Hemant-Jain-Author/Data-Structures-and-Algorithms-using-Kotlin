import java.util.Arrays
import java.util.Collections

// Allowed values from 0 to maxValue.
fun bucketSort(arr: IntArray, maxValue: Int) {
    val numBucket = 5
    bucketSortUtil(arr, maxValue, numBucket)
}

fun bucketSortUtil(arr: IntArray, maxValue: Int, numBucket: Int) {
    val length = arr.size
    if (length == 0) return
    val bucket: ArrayList<ArrayList<Int>> = ArrayList<ArrayList<Int>>(numBucket)

    // Create empty buckets
    for (i in 0 until numBucket) 
        bucket.add(ArrayList<Int>())
    
    val div: Int = Math.ceil(maxValue.toDouble() / numBucket).toInt()

    // Add elements into the buckets
    for (i in 0 until length) {
        if (arr[i] < 0 || arr[i] > maxValue) {
            println("Value out of range.")
            return
        }
        var bucketIndex = arr[i] / div

        // Maximum value will be assigned to last bucket.
        if (bucketIndex >= numBucket) 
            bucketIndex = numBucket - 1
        bucket.get(bucketIndex).add(arr[i])
    }

    // Sort the elements of each bucket.
    for (i in 0 until numBucket) {
        Collections.sort<Int>(bucket.get(i))
    }

    // Populate output from the sorted subarray.
    var index = 0
    var count: Int
    for (i in 0 until numBucket) {
        val temp: ArrayList<Int> = bucket.get(i)
        count = temp.size
        for (j in 0 until count) {
            arr[index++] = temp.get(j)
        }
    }
}

// Testing code
fun main() {
    val array = intArrayOf(1, 34, 7, 99, 5, 23, 45, 88, 77, 19, 91, 100)
    val maxValue = 100
    bucketSort(array, maxValue)
    for (i in array.indices) {
        print(array[i].toString() + " ")
    }
}