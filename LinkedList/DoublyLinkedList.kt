class DoublyLinkedList {
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

    fun print() {
        var temp = head
        while (temp != null) {
            print(temp.value.toString() + " ")
            temp = temp.next
        }
        println("")
    }

    fun peek(): Int {
        if (isEmpty) throw IllegalStateException("EmptyListException")
        return head!!.value
    }

    fun addHead(value: Int) {
        var newNode = Node(value, null, null)
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

    fun removeHead(): Int {
        if (isEmpty) throw IllegalStateException("EmptyListException")

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
        if (isEmpty) throw IllegalStateException("EmptyListException") // empty list
        
        var curr : Node? = head
        if (curr!!.value == key) { // head is the node with value key.
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
                if (curr.next == null) // last element case.
                    tail = curr 
                else 
                    curr.next!!.prev = curr
                size--
                return true
            }
            curr = curr.next
        }
        return false
    }

    fun search(key: Int): Boolean {
        var temp = head
        while (temp != null) {
            if (temp.value == key) return true
            temp = temp.next
        }
        return false
    }

    fun deleteList() {
        head = null
        tail = null
        size = 0
    }

    // Sorted insert increasing
    fun sortedInsert(value: Int) {
        val temp = Node(value, null, null)
        var curr = head
        if (curr == null) // first element
        {
            head = temp
            tail = temp
            return
        }
        if (head!!.value > value) // at the beginning
        {
            temp.next = head
            head!!.prev = temp
            head = temp
            return
        }
        while (curr!!.next != null && curr.next!!.value < value) // traversal
        {
            curr = curr.next
        }
        if (curr.next == null) { // at the end
            tail = temp
            temp.prev = curr
            curr.next = temp
        } else { // all other
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

    fun removeDuplicate() {
        var curr = head
        while (curr != null) {
            if (curr.next != null && curr.value == curr.next!!.value) {
                curr.next = curr.next!!.next
                if (curr.next != null) curr.next!!.prev = curr else tail = curr
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

// Testing code.
fun main1() {
    val ll = DoublyLinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.print()
    println("size : " + ll.size())
    println("isEmpty : " + ll.isEmpty)
    ll.removeHead()
    ll.print()
    println(ll.search(2))
}

/*
3 2 1 
size : 3
isEmpty : false
2 1 
true
*/

// Testing Code.
fun main2() {
    val ll = DoublyLinkedList()
    ll.sortedInsert(1)
    ll.sortedInsert(2)
    ll.sortedInsert(3)
    ll.print()
    ll.sortedInsert(1)
    ll.sortedInsert(2)
    ll.sortedInsert(3)
    ll.print()
    ll.removeDuplicate()
    ll.print()
}

/*
1 2 3 
1 1 2 2 3 3 
1 2 3 
*/

// Testing Code.
fun main3() {
    val ll = DoublyLinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.print()
    val l2 = ll.copyList()
    l2.print()
    val l3 = ll.copyListReversed()
    l3.print()
}

/*
3 2 1 
3 2 1 
1 2 3
*/

// Testing Code.
fun main4() {
    val ll = DoublyLinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.print()
    ll.removeNode(2)
    ll.print()
}

/*
3 2 1 
3 1 
*/

// Testing Code.
fun main5() {
    val ll = DoublyLinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.print()
    ll.reverseList()
    ll.print()
}

/*
3 2 1
1 2 3
*/

// Testing code
fun main() {
    main1()
    main2()
    main3()
    main4()
    main5()
}