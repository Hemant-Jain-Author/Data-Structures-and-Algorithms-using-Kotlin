import java.util.PriorityQueue
import java.util.Collections
import java.util.Comparator

class less : Comparator<Int> {
    override fun compare(a: Int, b: Int?): Int {
        return a.compareTo(b!!)
    }
}

class more : Comparator<Int> {
    override fun compare(a: Int?, b: Int): Int {
        return b.compareTo(a!!)
    }
}

fun main(args: Array<String>) {

    val pq = PriorityQueue<Int>()
    //PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
    val arr = intArrayOf(1, 2, 10, 8, 7, 3, 4, 6, 5, 9)

    for (i in arr) {
        pq.add(i)
    }
    println("Printing Priority Queue Heap : $pq")

    print("Dequeue elements of Priority Queue ::")
    while (pq.isEmpty() == false) {
        print(" " + pq.remove())
    }
}
