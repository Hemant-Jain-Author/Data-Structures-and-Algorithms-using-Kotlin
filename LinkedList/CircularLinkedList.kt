class CircularLinkedList {
    private var tail: Node? = null
    private var size = 0

    val isEmpty: Boolean
        get() = size == 0
     
    private class Node(val value: Int, var next: Node?)

    fun size(): Int {
        return size
    }

    /* Other methods */

    fun peek(): Int {
        if (isEmpty) throw IllegalStateException("EmptyListException")
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

    fun removeHead(): Int {
        if (isEmpty) throw IllegalStateException("EmptyListException")

        val value = tail!!.next!!.value
        if (tail === tail!!.next) 
            tail = null 
        else 
            tail!!.next = tail!!.next!!.next
        size--
        return value
    }

    fun removeNode(key: Int): Boolean {
        if (isEmpty) throw IllegalStateException("EmptyListException")

        var curr = tail!!.next
        val head = tail!!.next

        if (curr!!.value == key) { // head and single node case.
            if (curr === curr.next) // single node case
                tail = null 
            else  // head case
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
        if (tail == null) {
            return cl
        }
        var curr = tail!!.next
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
        if (tail == null) {
            return cl
        }
        var curr = tail!!.next
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

    fun search(data: Int): Boolean {
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
            println("Empty List.")
            return
        }
        var temp = tail!!.next
        while (temp !== tail) {
            print(temp!!.value.toString() + " ")
            temp = temp.next
        }
        println(temp!!.value)
    }
}

// Testing Code.
fun main1() {
    val ll = CircularLinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.print()
    println(ll.size())
    println(ll.isEmpty)
    println(ll.peek())
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
    val ll = CircularLinkedList()
    ll.addTail(1)
    ll.addTail(2)
    ll.addTail(3)
    ll.print()
}

/*
1 2 3
*/

// Testing Code.
fun main3() {
    val ll = CircularLinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.print()
    ll.removeHead()
    ll.print()
    ll.removeNode(2)
    ll.print()
    ll.deleteList()
    ll.print()
}

/*
3 2 1
2 1
1
Empty List.
*/

// Testing Code.
fun main4() {
    val ll = CircularLinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.print()
    val ll2 = ll.copyList()
    ll2.print()
    val ll3 = ll.copyListReversed()
    ll3.print()
}

/*
3 2 1
3 2 1
1 2 3
*/

// Testing Code.
fun main5() {
    val ll = CircularLinkedList()
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
fun main() {
    main1()
    main2()
    main3()
    main4()
    main5()
}