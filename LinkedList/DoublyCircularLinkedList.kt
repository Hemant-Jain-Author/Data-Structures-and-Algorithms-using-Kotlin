class DoublyCircularLinkedList {
    private var head: Node? = null
    private var tail: Node? = null
    private var size = 0

    val isEmpty: Boolean
        get() = size == 0

    private class Node {
        var value: Int = 0
        var next: Node? = null
        var prev: Node? = null

        constructor(v: Int, nxt: Node?, prv: Node?) {
            value = v
            next = nxt
            prev = prv
        }

        constructor(v: Int) {
            value = v
            next = this
            prev = this
        }
    }
    /* Other methods */

    fun size(): Int {
        return size
    }

    @Throws(IllegalStateException::class)
    fun peekHead(): Int {
        if (isEmpty)
            throw IllegalStateException("EmptyListException")
        return head!!.value
    }

    fun addHead(value: Int) {
        val newNode = Node(value, null, null)
        if (size == 0) {
            head = newNode
            tail = head
            newNode.next = newNode
            newNode.prev = newNode
        } else {
            newNode.next = head
            newNode.prev = head!!.prev
            head!!.prev = newNode
            newNode.prev!!.next = newNode
            head = newNode
        }
        size++
    }

    fun addTail(value: Int) {
        val newNode = Node(value, null, null)
        if (size == 0) {
            tail = newNode
            head = tail
            newNode.next = newNode
            newNode.prev = newNode
        } else {
            newNode.next = tail!!.next
            newNode.prev = tail
            tail!!.next = newNode
            newNode.next!!.prev = newNode
            tail = newNode
        }
        size++
    }

    fun removeHead(): Int {
        if (size == 0)
            throw IllegalStateException("EmptyListException")

        val value = head!!.value
        size--

        if (size == 0) {
            head = null
            tail = null
            return value
        }

        val next = head!!.next
        next!!.prev = tail
        tail!!.next = next
        head = next
        return value
    }

    fun removeTail(): Int {
        if (size == 0)
            throw IllegalStateException("EmptyListException")

        val value = tail!!.value
        size--

        if (size == 0) {
            head = null
            tail = null
            return value
        }

        val prev = tail!!.prev
        prev!!.next = head
        head!!.prev = prev
        tail = prev
        return value
    }

    fun isPresent(key: Int): Boolean {
        var temp = head
        if (head == null)
            return false

        do {
            if (temp!!.value == key)
                return true
            temp = temp.next
        } while (temp !== head)

        return false
    }

    fun deleteList() {
        head = null
        tail = null
        size = 0
    }

    fun print() {
        if (isEmpty) {
            return
        }
        var temp = head
        while (temp !== tail) {
            print(temp!!.value.toString() + " ")
            temp = temp.next
        }
        print(temp!!.value)
    }
}

fun main(args: Array<String>) {
    val ll = DoublyCircularLinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.print()
}
