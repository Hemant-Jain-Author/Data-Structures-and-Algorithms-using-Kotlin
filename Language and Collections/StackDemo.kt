import java.util.Stack

fun main() {
    val stack = Stack<Int>()
    stack.push(1)
    stack.push(2)
    stack.push(3)

    println("Stack : $stack")
    println("Stack size : " + stack.size)
    println("Stack pop : " + stack.pop())
    println("Stack top : " + stack.peek())
    println("Stack isEmpty : " + stack.isEmpty())
}

/*
Stack : [1, 2, 3]
Stack size : 3
Stack pop : 3
Stack top : 2
Stack isEmpty : false
*/

fun main() {
    val stack = ArrayDeque<Int>()
    stack.add(1)
    stack.add(2)
    stack.add(3)

    println("Stack : $stack")
    println("Stack size : " + stack.size)
    println("Stack pop : " + stack.removeLast())
    println("Stack top : " + stack.last())
    println("Stack isEmpty : " + stack.isEmpty())
}
