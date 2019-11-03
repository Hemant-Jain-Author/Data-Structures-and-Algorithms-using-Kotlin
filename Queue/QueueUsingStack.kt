import java.util.Stack

class QueueUsingStack {

    private val stk1: Stack<Int>
    private val stk2: Stack<Int>

    init {
        stk1 = Stack<Int>()
        stk2 = Stack<Int>()
    }

    internal fun add(value: Int) {
        stk1.push(value)
    }

    internal fun remove(): Int {
        var value: Int
        if (stk2.isEmpty() == false) {
            return stk2.pop()
        }

        while (stk1.isEmpty() == false) {
            value = stk1.pop()
            stk2.push(value)
        }
        return stk2.pop()
    }
}
fun main(args: Array<String>) {
    val que = QueueUsingStack()
    que.add(1)
    que.add(11)
    que.add(111)
    println(que.remove())
    println(que.remove())
}
