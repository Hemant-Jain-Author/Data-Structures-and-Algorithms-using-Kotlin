class Queue {
    private var count: Int = 0
    private val capacity = 100
    private val data: IntArray
    internal var front = 0
    internal var back = 0

    internal val isEmpty: Boolean
        get() = count == 0

    internal val size: Int
        get() = count

    init {
        count = 0
        data = IntArray(100)
    }

    fun add(value: Int): Boolean {
        if (size >= capacity) {
            println("Queue is full.")
            return false
        } else {
            count++
            data[back] = value
            back = ++back % (capacity - 1)
        }
        return true
    }

    fun remove(): Int {
        val value: Int
        if (size <= 0) {
            println("Queue is empty.")
            return -999
        } else {
            count--
            value = data[front]
            front = ++front % (capacity - 1)
        }
        return value
    }
}

fun main(args: Array<String>) {
    val que = Queue()

    for (i in 0..19) {
        que.add(i)
    }
    for (i in 0..21) {
        println(que.remove())
    }
}
