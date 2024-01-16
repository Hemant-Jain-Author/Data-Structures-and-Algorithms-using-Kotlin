import java.util.ArrayDeque

class Stack<T> {

    private val deque = ArrayDeque<T>()
    val isEmpty: Boolean
        get() = deque.isEmpty()

    fun push(obj: T) {
        deque.push(obj)
    }

    fun pop(): T {
        return deque.pop()
    }

    fun top(): T? {
        return deque.peek()
    }

    fun size(): Int {
        return deque.size
    }
}
