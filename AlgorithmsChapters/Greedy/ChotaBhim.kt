import java.util.PriorityQueue
import java.util.Collections

fun chotaBhim(cups: IntArray): Int {
    val size = cups.size
    var time = 60
    cups.sortDescending()
    var total = 0
    var index: Int
    var temp: Int
    while (time > 0) {
        total += cups[0]
        cups[0] = Math.ceil(cups[0] / 2.0).toInt()
        index = 0
        temp = cups[0]
        while (index < size - 1 && temp < cups[index + 1]) {
            cups[index] = cups[index + 1]
            index += 1
        }
        cups[index] = temp
        time -= 1
    }
    println("Total : $total")
    return total
}

fun chotaBhim2(cups: IntArray): Int {
    val size = cups.size
    var time = 60
    val pq: PriorityQueue<Int> = PriorityQueue<Int>(Collections.reverseOrder<Int>())
    var i = 0
    while (i < size) {
        pq.add(cups[i])
        i++
    }
    var total = 0
    var value: Int
    while (time > 0) {
        value = pq.remove()
        total += value
        value = Math.ceil(value / 2.0).toInt()
        pq.add(value)
        time -= 1
    }
    println("Total : $total")
    return total
}

// Testing code.
fun main() {
    val cups = intArrayOf(2, 1, 7, 4, 2)
    chotaBhim(cups)
    val cups2 = intArrayOf(2, 1, 7, 4, 2)
    chotaBhim2(cups2)
} 
/*
* Total : 76 
* Total : 76
*/