class LinkedList {

    private var head: Node? = null
    private var size = 0

    val isEmpty: Boolean
        get() = size == 0


    class Node {
        var value: Int = 0
        var next: Node? = null

        constructor(v: Int, nxt: Node?) {
            value = v
            next = nxt
        }

        constructor(v: Int) {
            value = v
            next = null
        }
    }

    // Other Methods.
    fun size(): Int {
        return size
    }

    // Other Methods.

    @Throws(IllegalStateException::class)
    fun peek(): Int {
        if (isEmpty)
            throw IllegalStateException("EmptyListException")
        return head!!.value
    }

    fun addHead(value: Int) {
        head = Node(value, head)
        size++
    }

    fun addTail(value: Int) {
        val newNode = Node(value, null)
        var curr = head

        if (head == null) {
            head = newNode
        }

        while (curr!!.next != null) {
            curr = curr.next
        }
        curr.next = newNode
    }

    @Throws(IllegalStateException::class)
    fun removeHead(): Int {
        if (isEmpty)
            throw IllegalStateException("EmptyListException")
        val value = head!!.value
        head = head!!.next
        size--
        return value
    }

    fun searchList(data: Int): Boolean {
        var temp = head
        while (temp != null) {
            if (temp.value == data)
                return true
            temp = temp.next
        }
        return false
    }

    fun deleteNode(delValue: Int): Boolean {
        var temp = head

        if (isEmpty)
            return false

        if (delValue == head!!.value) {
            head = head!!.next
            size--
            return true
        }

        while (temp!!.next != null) {
            if (temp.next!!.value == delValue) {
                temp.next = temp.next!!.next
                size--
                return true
            }
            temp = temp.next
        }
        return false
    }

    fun deleteNodes(delValue: Int) {
        var currNode = head
        var nextNode: Node?

        while (currNode != null && currNode.value == delValue)
        /* first node */ {
            head = currNode.next
            currNode = head
        }

        while (currNode != null) {
            nextNode = currNode.next
            if (nextNode != null && nextNode.value == delValue) {
                currNode.next = nextNode.next
            } else {
                currNode = nextNode
            }
        }
    }

    fun reverseRecurseUtil(currentNode: Node?, nextNode: Node?): Node? {
        val ret: Node?
        if (currentNode == null)
            return null
        if (currentNode.next == null) {
            currentNode.next = nextNode
            return currentNode
        }

        ret = reverseRecurseUtil(currentNode.next, currentNode)
        currentNode.next = nextNode
        return ret
    }

    fun reverseRecurse() {
        head = reverseRecurseUtil(head, null)
    }

    fun reverse() {
        var curr = head
        var prev: Node? = null
        var next: Node? = null
        while (curr != null) {
            next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        }
        head = prev
    }

    fun copyListReversed(): LinkedList {
        var tempNode: Node? = null
        var tempNode2: Node? = null
        var curr = head
        while (curr != null) {
            tempNode2 = Node(curr.value, tempNode)
            curr = curr.next
            tempNode = tempNode2
        }
        val ll2 = LinkedList()
        ll2.head = tempNode
        return ll2
    }

    fun copyList(): LinkedList {
        var headNode: Node? = null
        var tailNode: Node? = null
        var tempNode: Node?
        var curr: Node? = head
        val ll2 = LinkedList()

        if (head == null)
            return ll2

        headNode = Node(curr!!.value, null)
        tailNode = headNode
        curr = curr.next

        while (curr != null) {
            tempNode = Node(curr.value, null)
            tailNode!!.next = tempNode
            tailNode = tempNode
            curr = curr.next
        }
        ll2.head = headNode
        return ll2
    }

    fun compareList(ll: LinkedList): Boolean {
        return compareList(head, ll.head)
    }

    fun compareList(head1: Node?, head2: Node?): Boolean {
        return if (head1 == null && head2 == null)
            true
        else if (head1 == null || head2 == null || head1.value != head2.value)
            false
        else
            compareList(head1.next, head2.next)
    }

    fun compareList2(ll2: LinkedList): Boolean {
        var head1 = head
        var head2 = ll2.head

        while (head1 != null && head2 != null) {
            if (head1.value != head2.value)
                return false
            head1 = head1.next
            head2 = head2.next
        }

        return if (head1 == null && head2 == null) true else false
    }

    fun findLength(): Int {
        var curr = head
        var count = 0
        while (curr != null) {
            count++
            curr = curr.next
        }
        return count
    }

    fun nthNodeFromBegining(index: Int): Int {
        if (index > size() || index < 1)
            return Integer.MAX_VALUE
        var count = 0
        var curr = head
        while (curr != null && count < index - 1) {
            count++
            curr = curr.next
        }
        return curr!!.value
    }

    fun nthNodeFromEnd(index: Int): Int {
        val size = findLength()
        val startIndex: Int
        if (size != 0 && size < index) {
            return Integer.MAX_VALUE
        }
        startIndex = size - index + 1
        return nthNodeFromBegining(startIndex)
    }

    fun nthNodeFromEnd2(index: Int): Int {
        var count = 1
        var forward = head
        var curr = head
        while (forward != null && count <= index) {
            count++
            forward = forward.next
        }

        if (forward == null)
            return Integer.MAX_VALUE

        while (forward != null) {
            forward = forward.next
            curr = curr!!.next
        }
        return curr!!.value
    }

    fun findIntersection(lst2: LinkedList): Node? {
        var head2 = lst2.head
        var l1 = 0
        var l2 = 0
        var tempHead = this.head
        var tempHead2 = head2
        while (tempHead != null) {
            l1++
            tempHead = tempHead.next
        }
        while (tempHead2 != null) {
            l2++
            tempHead2 = tempHead2.next
        }

        var diff: Int
        if (l1 < 12) {
            val temp = head
            head = head2
            head2 = temp
            diff = l2 - l1
        } else {
            diff = l1 - l2
        }

        while (diff > 0) {
            head = head!!.next
            diff--
        }
        while (head !== head2) {
            head = head!!.next
            head2 = head2!!.next
        }
        return head
    }

    fun deleteList() {
        head = null
        size = 0
    }

    fun print() {
        var temp = head
        while (temp != null) {
            print(temp.value.toString() + " ")
            temp = temp.next
        }
    }

    fun sortedInsert(value: Int) {
        val newNode = Node(value, null)
        var curr = head

        if (curr == null || curr.value > value) {
            newNode.next = head
            head = newNode
            return
        }
        while (curr!!.next != null && curr.next!!.value < value) {
            curr = curr.next
        }

        newNode.next = curr.next
        curr.next = newNode
    }

    fun removeDuplicate() {
        var curr = head
        while (curr != null) {
            if (curr.next != null && curr.value == curr.next!!.value) {
                curr.next = curr.next!!.next
            } else {
                curr = curr.next
            }
        }
    }

    fun makeLoop() {
        var temp = head
        while (temp != null) {
            if (temp.next == null) {
                temp.next = head
                return
            }
            temp = temp.next
        }
    }

    fun loopDetect(): Boolean {
        var slowPtr: Node?
        var fastPtr: Node?
        fastPtr = head
        slowPtr = fastPtr

        while (fastPtr!!.next != null && fastPtr.next!!.next != null) {
            slowPtr = slowPtr!!.next
            fastPtr = fastPtr.next!!.next
            if (slowPtr === fastPtr) {
                println("loop found")
                return true
            }
        }
        println("loop not found")
        return false
    }

    fun reverseListLoopDetect(): Boolean {
        val tempHead = head
        reverse()
        if (tempHead === head) {
            reverse()
            println("loop found")
            return true
        } else {
            reverse()
            println("Loop not found")
            return false
        }
    }

    fun loopTypeDetect(): Int {
        var slowPtr: Node?
        var fastPtr: Node?
        fastPtr = head
        slowPtr = fastPtr

        while (fastPtr!!.next != null && fastPtr.next!!.next != null) {
            if (head === fastPtr.next || head === fastPtr.next!!.next) {
                println("Circular list loop found")
                return 2
            }
            slowPtr = slowPtr!!.next
            fastPtr = fastPtr.next!!.next
            if (slowPtr === fastPtr) {
                println("Loop found")

                return 1
            }
        }
        println("Loop not found")
        return 0
    }
}
fun main(args: Array<String>) {
    val ll = LinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.print()
    println("---")
    val ll2 = LinkedList()
    ll2.addHead(1)
    ll2.addHead(2)
    ll2.addHead(3)
    ll2.print()
    println("---")

    val l2 = ll.copyList()
    l2.print();
    println("---")
    val l3 = ll.copyListReversed();
    l3.print()
    println()
    println(ll.nthNodeFromBegining(2))
    println(ll.nthNodeFromEnd(2))
    println(ll.nthNodeFromEnd2(2))

}