import java.util.PriorityQueue
import java.util.Collections

fun main() {
    //val pq = PriorityQueue<Int>()
    val pq = PriorityQueue<Int>(Collections.reverseOrder());
    val arr = intArrayOf(1, 2, 10, 8, 7, 3, 4, 6, 5, 9)
    for (i in arr) {
        pq.add(i)
    }

    println("Heap : $pq")

    while (pq.isEmpty() == false) {
        print(" " + pq.remove())
    }
}

/*
Heap : [1, 2, 3, 5, 7, 10, 4, 8, 6, 9]
Dequeue elements of Priority Queue : 1 2 3 4 5 6 7 8 9 10
*/
