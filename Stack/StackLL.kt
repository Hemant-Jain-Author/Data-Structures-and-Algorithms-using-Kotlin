class StackLL {
    private var head: Node? = null
    private var size = 0

    val isEmpty: Boolean
        get() = size == 0

    private class Node(var value: Int, var next: Node?)

    fun size(): Int {
        return size
    }

    @Throws(IllegalStateException::class)
    fun peek(): Int {
        if (isEmpty) {
            throw IllegalStateException("StackEmptyException")
        }
        return head!!.value
    }

    fun push(value: Int) {
        head = Node(value, head)
        size++
    }

    @Throws(IllegalStateException::class)
    fun pop(): Int {
        if (isEmpty) {
            throw IllegalStateException("StackEmptyException")
        }
        val value = head!!.value
        head = head!!.next
        size--
        return value
    }

    fun insertAtBottom(value: Int) {
        if (isEmpty) {
            push(value)
        } else {
            val temp = pop()
            insertAtBottom(value)
            push(temp)
        }
    }

    fun print() {
        var temp = head
        while (temp != null) {
            print(temp.value.toString() + " ")
            temp = temp.next
        }
    }
}

fun main(args: Array<String>) {
    val s = StackLL()
    s.push(1)
    s.push(2)
    s.push(3)
    s.print()
    println(s.pop())
    println(s.pop())
    s.print()
}