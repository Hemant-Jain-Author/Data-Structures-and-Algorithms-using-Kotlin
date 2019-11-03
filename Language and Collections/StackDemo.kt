import java.util.*

fun main(args: Array<String>) {
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