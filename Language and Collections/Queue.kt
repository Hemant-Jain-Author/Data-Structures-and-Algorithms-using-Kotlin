import java.util.ArrayDeque

class Queue<T> {
    private val deque = ArrayDeque<T>()

    val isEmpty: Boolean
        get() = deque.isEmpty()

    fun enqueue(obj: T) {
        deque.add(obj)
    }

    fun dequeue(): T {
        return deque.remove()
    }

    fun peek(): T? {
        return deque.peekFirst()
    }

    fun size(): Int {
        return deque.size
    }
}