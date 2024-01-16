import java.util.Stack

class QueueUsingStack {
    private val stk1: Stack<Int>
    private val stk2: Stack<Int>

    init {
        stk1 = Stack<Int>()
        stk2 = Stack<Int>()
    }

    fun add(value: Int) {
        stk1.push(value)
    }

    fun remove(): Int {
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

// Testing code
fun main() {
    val que = QueueUsingStack()
    que.add(1)
    que.add(2)
    que.add(3)
    println("Queue remove : " + que.remove())
    println("Queue remove : " + que.remove())
}