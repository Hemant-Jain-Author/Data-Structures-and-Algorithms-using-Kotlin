class DoublyLinkedList {
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
            next = null
            prev = null
        }
    }

    /* Other methods */
    fun size(): Int {
        return size
    }

    @Throws(IllegalStateException::class)
    fun peek(): Int {
        if (isEmpty)
            throw IllegalStateException("EmptyListException")
        return head!!.value
    }

    fun addHead(value: Int) {
        val newNode = Node(value, null, null)
        if (size == 0) {
            head = newNode
            tail = head
        } else {
            head!!.prev = newNode
            newNode.next = head
            head = newNode
        }
        size++
    }

    fun addTail(value: Int) {
        val newNode = Node(value, null, null)
        if (size == 0) {
            tail = newNode
            head = tail
        } else {
            newNode.prev = tail
            tail!!.next = newNode
            tail = newNode
        }
        size++
    }

    @Throws(IllegalStateException::class)
    fun removeHead(): Int {
        if (isEmpty)
            throw IllegalStateException("EmptyListException")
        val value = head!!.value
        head = head!!.next

        if (head == null)
            tail = null
        else
            head!!.prev = null

        size--
        return value
    }

    fun removeNode(key: Int): Boolean {
        var curr: Node? = head ?: // empty list
        return false

        if (curr!!.value == key)
        // head is the node with value key.
        {
            head = head!!.next
            size--
            if (head != null)
                head!!.prev = null
            else
                tail = null // only one element in list.
            return true
        }

        while (curr!!.next != null) {
            if (curr.next!!.value == key) {
                curr.next = curr.next!!.next
                if (curr.next == null)
                // last element case.
                    tail = curr
                else
                    curr.next = curr
                size--
                return true
            }
            curr = curr.next
        }
        return false
    }

    fun isPresent(key: Int): Boolean {
        var temp = head
        while (temp != null) {
            if (temp.value == key)
                return true
            temp = temp.next
        }
        return false
    }

    fun deleteList() {
        head = null
        tail = null
        size = 0
    }

    fun print() {
        var temp = head
        while (temp != null) {
            print(temp.value.toString() + " ")
            temp = temp.next
        }
        println()
    }

    // SORTED INSERT DECREASING
    fun sortedInsert(value: Int) {
        val temp = Node(value)

        var curr = head
        if (curr == null)
        // first element
        {
            head = temp
            tail = temp
        }

        if (head!!.value <= value)
        // at the begining
        {
            temp.next = head
            head!!.prev = temp
            head = temp
        }

        while (curr!!.next != null && curr.next!!.value > value)
        // treversal
        {
            curr = curr.next
        }

        if (curr.next == null)
        // at the end
        {
            tail = temp
            temp.prev = curr
            curr.next = temp
        } else
        /// all other
        {
            temp.next = curr.next
            temp.prev = curr
            curr.next = temp
            temp.next!!.prev = temp
        }
    }

    /*
     * Reverse a doubly linked List iteratively
     */

    fun reverseList() {
        var curr = head
        var tempNode: Node?
        while (curr != null) {
            tempNode = curr.next
            curr.next = curr.prev
            curr.prev = tempNode

            if (curr.prev == null) {
                tail = head
                head = curr
                return
            }

            curr = curr.prev
        }
        return
    }

    /* Remove Duplicate */
    fun removeDuplicate() {
        var curr = head
        var deleteMe: Node?
        while (curr != null) {
            if (curr.next != null && curr.value == curr.next!!.value) {
                deleteMe = curr.next
                curr.next = deleteMe!!.next
                curr.next!!.prev = curr
                if (deleteMe === tail) {
                    tail = curr
                }
            } else {
                curr = curr.next
            }
        }
    }

    fun copyListReversed(): DoublyLinkedList {
        val dll = DoublyLinkedList()
        var curr = head

        while (curr != null) {
            dll.addHead(curr.value)
            curr = curr.next
        }
        return dll
    }

    fun copyList(): DoublyLinkedList {
        val dll = DoublyLinkedList()
        var curr = head

        while (curr != null) {
            dll.addTail(curr.value)
            curr = curr.next
        }
        return dll
    }
}

fun main(args: Array<String>) {
    val ll = DoublyLinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.addHead(4)
    ll.addHead(5)
    ll.addHead(6)
    ll.print()
    ll.removeHead()
    ll.deleteList()
    ll.print()
    ll.addHead(11)
    ll.addHead(21)
    ll.addHead(31)
    ll.addHead(41)
    ll.addHead(51)
    ll.addHead(61)
    ll.print()
}