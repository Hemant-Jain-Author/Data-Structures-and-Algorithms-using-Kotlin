class QueueLL {
    private var tail: Node? = null
    private var size = 0

    val isEmpty: Boolean
        get() = size == 0

    private class Node(var value: Int, var next: Node?)

    fun size(): Int {
        return size
    }

    @Throws(IllegalStateException::class)
    fun peek(): Int {
        if (isEmpty)
            throw IllegalStateException("StackEmptyException")
        val value: Int
        if (tail === tail!!.next)
            value = tail!!.value
        else
            value = tail!!.next!!.value

        return value
    }

    fun add(value: Int) {
        val temp = Node(value, null)

        if (tail == null) {
            tail = temp
            tail!!.next = tail
        } else {
            temp.next = tail!!.next
            tail!!.next = temp
            tail = temp
        }
        size++
    }

    @Throws(IllegalStateException::class)
    fun remove(): Int {
        if (isEmpty)
            throw IllegalStateException("StackEmptyException")

        var value : Int
        if (tail === tail!!.next) {
            value = tail!!.value
            tail = null
        } else {
            value = tail!!.next!!.value
            tail!!.next = tail!!.next!!.next
        }
        size--
        return value
    }
}
fun main(args: Array<String>) {
    val q = QueueLL()
    q.add(1)
    q.add(2)
    q.add(3)
    for (i in 0..2)
        println(q.remove())
}
