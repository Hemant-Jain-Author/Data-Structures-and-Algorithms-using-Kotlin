class Queue @JvmOverloads constructor(private val capacity: Int = 1000) {
    private val data: IntArray
    private var size = 0
    var front = 0
    var back = 0

    val isEmpty: Boolean
        get() = size == 0

    fun size(): Int {
        return size
    }

    init {
        data = IntArray(capacity)
    }

    fun add(value: Int): Boolean {
        if (size >= capacity) {
            println("Queue is full.")
            return false
        } else {
            size++
            data[back] = value
            back = ++back % capacity
        }
        return true
    }

    fun remove(): Int {
        val value: Int
        if (size <= 0) {
            println("Queue is empty.")
            return Int.MIN_VALUE
        } else {
            size--
            value = data[front]
            front = ++front % capacity
        }
        return value
    }

    fun print() {
        if (size == 0) {
            print("Queue is empty.")
            return
        }
        var temp = front
        print("Queue is : ")
        for (s in 0 until size) {
            print(data[temp].toString() + " ")
            temp = (temp + 1) % capacity
        }
        println()
    }
}

// Testing code
fun main() {
    val que = Queue()
    que.add(1)
    que.add(2)
    que.add(3)
    que.print()
    println("isEmpty : " + que.isEmpty)
    println("size : " + que.size())
    println("Queue remove : " + que.remove())
    println("Queue remove : " + que.remove())
}