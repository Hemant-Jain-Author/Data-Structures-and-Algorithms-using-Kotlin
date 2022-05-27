class Stack constructor(s: Int = 1000) {
    private var capacity: Int
    private var data: IntArray
    private var top = -1
    private val minCapacity : Int // for dynamic stack

    init {
        data = IntArray(s)
        capacity = s
        minCapacity = s // for dynamic stack
    }

    /* Other methods */
    fun size(): Int {
        return top + 1
    }

    val isEmpty: Boolean
        get() = top == -1

    @Throws(IllegalStateException::class)
    fun push(value: Int) {
        if (top + 1 == capacity) {
            throw IllegalStateException("StackOverflowException")
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

    fun pop(): Int {
        if (isEmpty) {
            throw IllegalStateException("StackEmptyException")
        }
        val topVal = data[top]
        top--
        return topVal
    }

    fun print() {
        for (i in top downTo 0) {
            print(data[i].toString() + " ")
        }
        println()
    }

    fun push2(value: Int) {
        if (top + 1 == capacity) {
            println("Size doubled.")
            val newData = IntArray(capacity * 2)
            java.lang.System.arraycopy(data, 0, newData, 0, capacity)
            data = newData
            capacity = capacity * 2
        }
        top++
        data[top] = value
    }

    fun pop2(): Int {
        if (isEmpty) {
            throw IllegalStateException("StackEmptyException")
        }
        val topVal = data[top]
        top--
        if (top + 1 < capacity / 2 && capacity > minCapacity) {
            println("Size halved.")
            capacity = capacity / 2
            val newData = IntArray(capacity)
            java.lang.System.arraycopy(data, 0, newData, 0, capacity)
            data = newData
        }
        return topVal
    }
}

fun main1() {
    val s = Stack()
    s.push(1)
    s.push(2)
    s.push(3)
    s.print()
    println(s.pop())
    println(s.pop())
}

/*
3 2 1 
3
2
*/

fun main2() {
    val s = Stack(5)
    for (i in 0..11) {
        s.push2(i)
    }
    for (i in 0..11) {
        print(s.pop2().toString() + " ")
    }
}

/*
Size doubled.
Size doubled.
11 10 Size halved.
9 8 7 6 5 Size halved.
4 3 2 1 0 
*/

fun main() {
    main1();
    main2()
}