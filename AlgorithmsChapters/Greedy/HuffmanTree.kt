import java.util.PriorityQueue
import java.util.Collections

class HuffmanTree internal constructor(arr: CharArray, freq: IntArray) {
    var root: Node? = null

    inner class Node(var c: Char, var freq: Int, var left: Node?, var right: Node?) :
        Comparable<Node> {
        override operator fun compareTo(other: Node): Int {
            return freq - other.freq
        }
    }

    init {
        val n = arr.size
        val que: PriorityQueue<Node> = PriorityQueue<Node>(n)
        for (i in 0 until n) {
            val node: Node = Node(arr[i], freq[i], null, null)
            que.add(node)
        }
        while (que.size > 1) {
            val lt: Node = que.peek()
            que.poll()
            val rt: Node = que.peek()
            que.poll()
            val nd: Node = Node('+', lt.freq + rt.freq, lt, rt)
            que.add(nd)
        }
        root = que.peek()
    }

    fun print(root: Node?, s: String) {
        if (root!!.left == null && root.right == null && root.c != '+') {
            println(root.c.toString() + " = " + s)
            return
        }
        print(root.left, s + "0")
        print(root.right, s + "1")
    }

    fun print() {
        println("Char = Huffman code")
        print(root, "")
    }
}
    
// Testing code.
fun main() {
    val ar = charArrayOf('A', 'B', 'C', 'D', 'E')
    val fr = intArrayOf(30, 25, 21, 14, 10)
    val hf = HuffmanTree(ar, fr)
    hf.print()
}