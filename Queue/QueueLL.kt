class QueueLL {
    private var tail: Node? = null
    private var size = 0

    val isEmpty: Boolean
        get() = size == 0

    private class Node(val value: Int, var next: Node?)

    fun size(): Int {
        return size
    }

    @Throws(IllegalStateException::class)
    fun peek(): Int {
        if (isEmpty) throw IllegalStateException("StackEmptyException")
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
        if (size == 0) throw IllegalStateException("StackEmptyException")
        
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

    fun print() {
        if (size == 0) {
            print("Queue is empty.")
            return
        }
        var temp = tail!!.next
        print("Queue is : ")
        for (i in 0 until size) {
            print(temp!!.value.toString() + " ")
            temp = temp.next
        }
        println()
    }
}

fun main() {
    val que = QueueLL()
    que.add(1)
    que.add(2)
    que.add(3)
    println("isEmpty : " + que.isEmpty)
    println("size : " + que.size())
    println("Queue remove : " + que.remove())
    println("Queue remove : " + que.remove())
}