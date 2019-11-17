class CircularLinkedList {
    private var tail: Node? = null
    private var size = 0

    val isEmpty: Boolean
        get() = size == 0

    class Node(var value: Int, var next: Node?)

    fun size(): Int {
        return size
    }

    @Throws(IllegalStateException::class)
    fun peek(): Int {
        if (isEmpty)
            throw IllegalStateException("EmptyListException")
        return tail!!.next!!.value
    }

    fun addTail(value: Int) {
        val temp = Node(value, null)
        if (isEmpty) {
            tail = temp
            temp.next = temp
        } else {
            temp.next = tail!!.next
            tail!!.next = temp
            tail = temp
        }
        size++
    }

    fun addHead(value: Int) {
        val temp = Node(value, null)
        if (isEmpty) {
            tail = temp
            temp.next = temp
        } else {
            temp.next = tail!!.next
            tail!!.next = temp
        }
        size++
    }

    @Throws(IllegalStateException::class)
    fun removeHead(): Int {
        if (isEmpty) {
            throw IllegalStateException("EmptyListException")
        }
        val value = tail!!.next!!.value
        if (tail === tail!!.next)
            tail = null
        else
            tail!!.next = tail!!.next!!.next

        size--
        return value
    }

    fun removeNode(key: Int): Boolean {
        if (isEmpty) {
            return false
        }
        var curr = tail!!.next
        val head = tail!!.next

        if (curr!!.value == key)
        // head and single node case.
        {
            if (curr === curr.next)
            // single node case
                tail = null
            else
            // head case
                tail!!.next = tail!!.next!!.next
            return true
        }

        var prev = curr
        curr = curr.next

        while (curr !== head) {
            if (curr!!.value == key) {
                if (curr === tail)
                    tail = prev
                prev!!.next = curr.next
                return true
            }
            prev = curr
            curr = curr.next
        }

        return false
    }

    fun copyListReversed(): CircularLinkedList {
        val cl = CircularLinkedList()
        var curr: Node? = tail!!.next
        val head = curr

        if (curr != null) {
            cl.addHead(curr.value)
            curr = curr.next
        }
        while (curr !== head) {
            cl.addHead(curr!!.value)
            curr = curr.next
        }
        return cl
    }

    fun copyList(): CircularLinkedList {
        val cl = CircularLinkedList()
        var curr: Node? = tail!!.next
        val head = curr

        if (curr != null) {
            cl.addTail(curr.value)
            curr = curr.next
        }
        while (curr !== head) {
            cl.addTail(curr!!.value)
            curr = curr.next
        }
        return cl
    }

    fun searchList(data: Int): Boolean {
        var temp = tail
        for (i in 0 until size) {
            if (temp!!.value == data)
                return true
            temp = temp.next
        }
        return false
    }

    fun deleteList() {
        tail = null
        size = 0
    }

    fun print() {
        if (isEmpty) {
            return
        }
        var temp = tail!!.next
        while (temp !== tail) {
            print(temp!!.value.toString() + " ")
            temp = temp.next
        }
        print(temp!!.value)
    }
}

fun main(args: Array<String>) {
    val ll = CircularLinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.print()
}