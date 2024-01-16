class DoublyCircularLinkedList {
    private class Node(val value: Int, var next: Node?, var prev: Node?)

	private var head: Node? = null
    private var tail: Node? = null
    private var size = 0

    val isEmpty: Boolean
        get() = size == 0
    
    fun size(): Int {
        return size
    }
    /* Other methods */
}
    fun peekHead(): Int {
        if (isEmpty) throw IllegalStateException("EmptyListException")
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
        if (size == 0) throw IllegalStateException("EmptyListException")

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
        if (size == 0) throw IllegalStateException("EmptyListException")
        
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

    fun search(key: Int): Boolean {
        var temp = head
        if (head == null) return false
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
            println("Empty List.")
            return
        }
        var temp = head
        while (temp !== tail) {
            print(temp!!.value.toString() + " ")
            temp = temp.next
        }
        println(temp!!.value)
    }
}

// Testing Code.
fun main1() {
    val ll = DoublyCircularLinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.print()
    println(ll.size())
    println(ll.isEmpty)
    println(ll.peekHead())
    println(ll.search(3))
}

/*
3 2 1
3
false
3
true
*/

// Testing Code.
fun main2() {
    val ll = DoublyCircularLinkedList()
    ll.addTail(1)
    ll.addTail(2)
    ll.addTail(3)
    ll.print()
    ll.removeHead()
    ll.print()
    ll.removeTail()
    ll.print()
    ll.deleteList()
    ll.print()
}

/*
1 2 3
2 3
2
Empty List.
*/

// Testing Code.
fun main3() {
    val ll = DoublyCircularLinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.print()
    ll.removeHead()
    ll.print()
}

/*
3 2 1
2 1
*/

// Testing Code.
fun main4() {
    val ll = DoublyCircularLinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.print()
    ll.removeTail()
    ll.print()
}

/*
3 2 1
3 2
*/

// Testing Code.
fun main() {
    main1()
    main2()
    main3()
    main4()
}