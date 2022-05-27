import java.util.LinkedList

fun main() {
    val ll = LinkedList<Int>()
    ll.addFirst(2) // 8 is added to the list
    ll.addLast(10) // 9 is added to last of the list.
    ll.addFirst(1) // 7 is added to first of the list.
    ll.addLast(11) // 20 is added to last of the list
    println("Contents of Linked List: $ll")
    ll.removeFirst()
    ll.removeLast()
    println("Contents of Linked List: $ll")
}
