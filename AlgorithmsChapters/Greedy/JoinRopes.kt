import java.util.PriorityQueue
import java.util.Collections

fun joinRopes(ropes: IntArray, size: Int): Int {
    ropes.sort()
    var i = 0
    var j = size - 1
    while (i < j) {
        val temp = ropes[i]
        ropes[i] = ropes[j]
        ropes[j] = temp
        i++
        j--
    }
    var total = 0
    var index: Int
    var length = size
    while (length >= 2) {
        var value = ropes[length - 1] + ropes[length - 2]
        total += value
        index = length - 2
        while (index > 0 && ropes[index - 1] < value) {
            ropes[index] = ropes[index - 1]
            index -= 1
        }
        ropes[index] = value
        length--
    }
    println("Total : $total")
    return total
}

fun joinRopes2(ropes: IntArray, size: Int): Int {
    val pq: PriorityQueue<Int> = PriorityQueue<Int>()
    var i = 0
    while (i < size) {
        pq.add(ropes[i])
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
    val ropes = intArrayOf(4, 3, 2, 6)
    joinRopes(ropes, ropes.size)
    val rope2 = intArrayOf(4, 3, 2, 6)
    joinRopes2(rope2, rope2.size)
} 
/*
* Total : 29 
* Total : 29
*/