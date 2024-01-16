class StackLL {
    private class Node(val value: Int, var next: Node?)

	private var head: Node? = null
    private var length = 0

    fun size(): Int {
        return length
    }

    val isEmpty: Boolean
        get() = length == 0

    fun peek(): Int {
        if (isEmpty) {
            throw IllegalStateException("StackEmptyException")
        }
        return head!!.value
    }

    fun push(value: Int) {
        head = Node(value, head)
        length++
    }

    fun pop(): Int {
        if (isEmpty) {
            throw IllegalStateException("StackEmptyException")
        }
        val value = head!!.value
        head = head!!.next
        length--
        return value
    }

    fun print() {
        var temp = head
        while (temp != null) {
            print(temp.value.toString() + " ")
            temp = temp.next
        }
        println()
    }
}

/* Testing code */
fun main() {
    val s = StackLL()
    s.push(1)
    s.push(2)
    s.push(3)
    s.print()
    println(s.pop())
    println(s.pop())
}

/*
3 2 1 
3
2
*/