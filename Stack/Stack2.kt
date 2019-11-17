class Stack2 {
    private var data: IntArray
    private var top = -1
    private var minCapacity: Int = 0
    private var capacity: Int = 0

    val isEmpty: Boolean
        get() = top == -1

    constructor(size: Int = 1000) {
        data = IntArray(size)
        minCapacity = size
        capacity = minCapacity
    }

    /* Other methods */
    fun size(): Int {
        return top + 1
    }

    @Throws(IllegalStateException::class)
    fun push(value: Int) {
        if (size() == capacity) {
            println("Size dubbelled")
            val newData = IntArray(capacity * 2)
            System.arraycopy(data, 0, newData, 0, capacity)
            data = newData
            capacity = capacity * 2
        }
        top++
        data[top] = value
    }

    @Throws(IllegalStateException::class)
    fun top(): Int {
        if (isEmpty) {
            throw IllegalStateException("StackEmptyException")
        }
        return data[top]
    }

    @Throws(IllegalStateException::class)
    fun pop(): Int {
        if (isEmpty) {
            throw IllegalStateException("StackEmptyException")
        }

        val topVal = data[top]
        top--
        if (size() == capacity / 2 && capacity > minCapacity) {
            println("Size halfed")
            capacity = capacity / 2
            val newData = IntArray(capacity)
            System.arraycopy(data, 0, newData, 0, capacity)
            data = newData
        }
        return topVal
    }

    fun print() {
        for (i in top downTo -1 + 1) {
            print(" " + data[i])
        }
    }
}

fun main(args: Array<String>) {
    val s = Stack2(10)
    for (i in 1..100) {
        s.push(i)
    }
    s.print()
    for (i in 1..100) {
        s.pop()
    }
    s.print()
}