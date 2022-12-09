class LinkedList {
    
    class Node(var value: Int, var next: Node? = null )

    private var head: Node? = null
    private var size = 0

    val isEmpty: Boolean
        get() = size == 0

    fun size(): Int {
        return size
    }

    // Other Methods.

    fun peek(): Int {
        if (isEmpty) throw IllegalStateException("EmptyListException")
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

    fun removeHead(): Int {
        if (isEmpty) throw IllegalStateException("EmptyListException")

        val value = head!!.value
        head = head!!.next
        size--
        return value
    }

    fun search(data: Int): Boolean {
        var temp = head
        while (temp != null) {
            if (temp.value == data) return true
            temp = temp.next
        }
        return false
    }

    fun deleteNode(delValue: Int): Boolean {
        if (isEmpty) throw IllegalStateException("EmptyListException")

        if (delValue == head!!.value) {
            head = head!!.next
            size--
            return true
        }

        var temp = head    
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

    fun deleteNodes(delValue: Int): Boolean {
        var currNode = head
        var nextNode: Node?
        var found = false
        while (currNode != null && currNode.value == delValue) { /* first node */
            head = currNode.next
            currNode = head
            found = true
        }
        while (currNode != null) {
            nextNode = currNode.next
            if (nextNode != null && nextNode.value == delValue) {
                currNode.next = nextNode.next
                found = true
            } else {
                currNode = nextNode
            }
        }
        return found
    }

    fun reverseRecurseUtil(currentNode: Node?, nextNode: Node?): Node? {
        if (currentNode == null) return null

        if (currentNode.next == null) {
            currentNode.next = nextNode
            return currentNode
        }
        val ret = reverseRecurseUtil(currentNode.next, currentNode)
        currentNode.next = nextNode
        return ret
    }

    fun reverseRecurse() {
        head = reverseRecurseUtil(head, null)
    }

    fun reverse() {
        var curr = head
        var prev: Node? = null
        var next: Node?
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
        var tempNode2: Node?
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

    fun copyList(): LinkedList? {
        val ll2 = LinkedList()
        if (head == null)
            return ll2
            
        var curr: Node? = head        
        var headNode: Node? = Node(curr!!.value, null)
        var tailNode: Node? = headNode
        curr = curr.next
        
        while (curr != null) {
            var tempNode = Node(curr.value, null)
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
        if (head1 == null && head2 == null) 
            return true 
        else if (head1 == null || head2 == null || head1.value != head2.value) 
            return false 
        else 
            return compareList(head1.next, head2.next)
    }

    fun compareList2(ll2: LinkedList?): Boolean {
        var head1 = head
        var head2 = ll2!!.head
        while (head1 != null && head2 != null) {
            if (head1.value != head2.value) 
                return false
            head1 = head1.next
            head2 = head2.next
        }
        return if (head1 == null && head2 == null) true 
        else false
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

    fun nthNodeFromBeginning(index: Int): Int {
        if (index > size() || index < 1) 
            return Int.MAX_VALUE
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
            return Int.MAX_VALUE
        }
        startIndex = size - index + 1
        return nthNodeFromBeginning(startIndex)
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
            return Int.MAX_VALUE
        while (forward != null) {
            forward = forward.next
            curr = curr!!.next
        }
        return curr!!.value
    }

    fun findIntersection(lst2: LinkedList): Node? {
        val head2 = lst2.head
        var l1 = 0
        var l2 = 0
        var tempHead = head
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
        tempHead = head
        tempHead2 = head2
        if (l1 < l2) {
            val temp = tempHead
            tempHead = tempHead2
            tempHead2 = temp
            diff = l2 - l1
        } else {
            diff = l1 - l2
        }
        while (diff > 0) {
            tempHead = tempHead!!.next
            diff--
        }
        while (tempHead !== tempHead2) {
            tempHead = tempHead!!.next
            tempHead2 = tempHead2!!.next
        }
        return tempHead
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
        println()
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

    fun bubbleSort() {
        var curr: Node?
        var end: Node? = null
        var temp: Int
        if (head == null || head!!.next == null) {
            return
        }
        var flag = true
        while (flag) {
            flag = false
            curr = head
            while (curr!!.next !== end) {
                if (curr!!.value > curr.next!!.value) {
                    flag = true
                    temp = curr.value
                    curr.value = curr.next!!.value
                    curr.next!!.value = temp
                }
                curr = curr.next
            }
            end = curr
        }
    }

    fun selectionSort() {
        var curr: Node?
        var end: Node? = null
        var maxNode: Node?
        var temp: Int
        var max: Int
        if (head == null || head!!.next == null) {
            return
        }
        while (head !== end) {
            curr = head
            max = curr!!.value
            maxNode = curr
            while (curr!!.next !== end) {
                if (max < curr!!.next!!.value) {
                    maxNode = curr.next
                    max = curr.next!!.value
                }
                curr = curr.next
            }
            end = curr
            if (curr!!.value < max) {
                temp = curr.value
                curr.value = max
                maxNode!!.value = temp
            }
        }
    }

    fun insertionSort() {
        var curr: Node?
        var stop: Node?
        var temp: Int
        if (head == null || head!!.next == null) {
            return
        }
        stop = head!!.next
        while (stop != null) {
            curr = head
            while (curr !== stop) {
                if (curr!!.value > stop.value) {
                    temp = curr.value
                    curr.value = stop.value
                    stop.value = temp
                }
                curr = curr.next
            }
            stop = stop.next
        }
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
        var curr = head
        val hs: HashSet<Node> = HashSet<Node>()
        while (curr != null) {
            if (hs.contains(curr)) {
                println("loop found")
                return true
            }
            hs.add(curr)
            curr = curr.next
        }
        println("loop not found")
        return false
    }

    fun loopDetect2(): Boolean {
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
            println("loop not found")
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
                println("circular list loop found")
                return 2
            }
            slowPtr = slowPtr!!.next
            fastPtr = fastPtr.next!!.next
            if (slowPtr === fastPtr) {
                println("loop found")
                return 1
            }
        }
        println("loop not found")
        return 0
    }

    fun loopPointDetect(): Node? {
        var slowPtr: Node?
        var fastPtr: Node?
        fastPtr = head
        slowPtr = fastPtr
        while (fastPtr!!.next != null && fastPtr.next!!.next != null) {
            slowPtr = slowPtr!!.next
            fastPtr = fastPtr.next!!.next
            if (slowPtr === fastPtr) {
                return slowPtr
            }
        }
        return null
    }

    fun removeLoop() {
        val loopPoint : Node? = loopPointDetect() ?: return
        var firstPtr = head
        if (loopPoint === head) {
            while (firstPtr!!.next !== head) firstPtr = firstPtr!!.next
            firstPtr!!.next = null
            return
        }
        var secondPtr : Node?= loopPoint
        while (firstPtr!!.next !== secondPtr!!.next) {
            firstPtr = firstPtr!!.next
            secondPtr = secondPtr!!.next
        }
        secondPtr!!.next = null
    }
}

// Testing Code.
fun main1() {
    val ll = LinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.print()
    println("Size : " + ll.size())
    println("Size : " + ll.findLength())
    println("Is empty : " + ll.isEmpty)
    println("Peek : " + ll.peek())
    ll.addTail(4)
    ll.print()
}

/*
3 2 1 
Size : 3
Size : 3
Is empty : false
Peek : 3
3 2 1 4
*/

// Testing Code.
fun main2() {
    val ll = LinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.print()
    println("search : " + ll.search(2))
    ll.removeHead()
    ll.print()
}

/*
3 2 1 
search : true
2 1 
*/

// Testing Code.
fun main3() {
    val ll = LinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(1)
    ll.addHead(3)
    ll.print()
    println("deleteNode : " + ll.deleteNode(2))
    ll.print()
    println("deleteNodes : " + ll.deleteNodes(1))
    ll.print()
}

/*
3 1 2 1 2 1 
deleteNode : true
3 1 1 2 1 
deleteNodes : true
3 2 
*/

// Testing Code.
fun main4() {
    val ll = LinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.print()
    ll.reverse()
    ll.print()
    ll.reverseRecurse()
    ll.print()
    val l2 = ll.copyList()
    l2!!.print()
    val l3 = ll.copyListReversed()
    l3.print()
}

/*
3 2 1 
1 2 3 
3 2 1 
3 2 1 
1 2 3 
*/

// Testing Code.
fun main5() {
    val ll = LinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.print()
    val l2 = ll.copyList()
    l2!!.print()
    val l3 = ll.copyListReversed()
    l3.print()
    println("compareList : " + ll.compareList(l2))
    println("compareList : " + ll.compareList2(l2))
    println("compareList : " + ll.compareList(l3))
    println("compareList : " + ll.compareList2(l3))
}

/*
3 2 1 
3 2 1 
1 2 3 
compareList : true
compareList : true
compareList : false
compareList : false
*/

// Testing Code.
fun main6() {
    val ll = LinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.addHead(4)
    ll.print()
    println(ll.nthNodeFromBeginning(2))
    println(ll.nthNodeFromEnd(2))
    println(ll.nthNodeFromEnd2(2))
}

/*
3 2 1 
2
2
2
*/

// Testing Code.
fun main7() {
    val ll = LinkedList()
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
fun main8() {
    val ll = LinkedList()
    ll.addHead(1)
    ll.addHead(2)
    ll.addHead(3)
    ll.print()
    ll.makeLoop()
    ll.loopDetect()
    ll.loopDetect2()
    ll.loopTypeDetect()
    ll.removeLoop()
    ll.loopDetect2()
}

/*
3 2 1 
loop found
circular list loop found
loop not found
*/
/* 
fun main9() {
    val ll = LinkedList()
    ll.addHead(1)
    ll.addHead(2)
    val ll2 = LinkedList()
    ll2.addHead(3)
    ll2.head!!.next = ll.head
    ll.addHead(4)
    ll2.addHead(5)
    ll.print()
    ll2.print()
    val nd = ll.findIntersection(ll2)
    if (nd != null) println("Intersection:: " + nd.value)
}
*/
/*
4 2 1 
5 3 2 1 
Intersection:: 2
*/

// Testing Code.
fun main10() {
    val ll = LinkedList()
    ll.addHead(1)
    ll.addHead(10)
    ll.addHead(9)
    ll.addHead(7)
    ll.addHead(2)
    ll.addHead(3)
    ll.addHead(5)
    ll.addHead(4)
    ll.addHead(6)
    ll.addHead(8)
    ll.print()
    ll.insertionSort()
    ll.print()
    ll.reverse()
    ll.print()
    ll.bubbleSort();
    ll.print()
    ll.reverse()
    ll.print()
    ll.selectionSort();
    ll.print()
}

// Testing code
fun main() {
    main1()
    main2()
    main3()
    main4()
    main5()
    main6()
    main7()
    main8()
    //main9()
    main10()
}
