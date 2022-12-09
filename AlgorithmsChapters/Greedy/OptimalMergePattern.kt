import java.util.PriorityQueue
import java.util.Collections

fun merge(lists: IntArray, size: Int): Int {
    val pq: PriorityQueue<Int> = PriorityQueue<Int>()
    var i = 0
    while (i < size) {
        pq.add(lists[i])
        i++
    }
    var total = 0
    while (pq.size > 1) {
        var value = pq.remove()
        value += pq.remove()
        pq.add(value)
        total += value
    }
    println("Total : $total")
    return total
}

// Testing code.
fun main() {
    val lists = intArrayOf(4, 3, 2, 6)
    merge(lists, lists.size)
}

/*
Total : 29
*/