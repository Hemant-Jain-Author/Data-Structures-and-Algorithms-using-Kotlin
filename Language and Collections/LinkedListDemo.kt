import java.util.LinkedList

fun main() {
    val ll = LinkedList<Int>()
    ll.addFirst(2) // 2 is added to the list.
    ll.addLast(10) // 10 is added to last of the list.
    ll.addFirst(1) // 1 is added to first of the list.
    ll.addLast(11) // 11 is added to last of the list.
	
    println("Contents of Linked List: $ll")

	ll.removeFirst()
    ll.removeLast()
    println("Contents of Linked List: $ll")
}

/*
Contents of Linked List: [1, 2, 10, 11]
Contents of Linked List: [2, 10]
*/