import java.util.Collections
import java.util.PriorityQueue

class MedianHeap {
    internal var minHeap: PriorityQueue<Int>
    internal var maxHeap: PriorityQueue<Int>

    init {
        minHeap = PriorityQueue<Int>()
        maxHeap = PriorityQueue<Int>(Collections.reverseOrder())
    }

    fun getMedian() : Int{
        if (maxHeap.size == 0 && minHeap.size == 0)
            return Integer.MAX_VALUE

        if (maxHeap.size == minHeap.size)
            return (maxHeap.peek() + minHeap.peek()) / 2
        else if (maxHeap.size > minHeap.size)
            return maxHeap.peek()
        else
            return minHeap.peek()
    }

    // Other Methods.
    fun insert(valueIn: Int) {
        var value = valueIn
        if (maxHeap.size == 0 || maxHeap.peek() >= value) {
            maxHeap.add(value)
        } else {
            minHeap.add(value)
        }

        // size balancing
        if (maxHeap.size > minHeap.size + 1) {
            value = maxHeap.remove()
            minHeap.add(value)
        }

        if (minHeap.size > maxHeap.size + 1) {
            value = minHeap.remove()
            maxHeap.add(value)
        }
    }
}

fun main() {
    val arr = intArrayOf(1, 9, 2, 8, 3, 7, 4, 6, 5, 1)
    val hp = MedianHeap()

    for (i in arr) {
        hp.insert(i)
        println("Median after insertion of " + i + " is  " + hp.getMedian())
    }
}
