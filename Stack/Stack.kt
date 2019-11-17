class Stack {
    private var capacity : Int
    private var data: IntArray
    private var top = -1

    val isEmpty: Boolean
        get() = top == -1

    constructor(size: Int = 1000) {
        data = IntArray(size)
        capacity = size
    }
    /* Other methods */

    fun size(): Int {
        return top + 1
    }

    @Throws(IllegalStateException::class)
    fun push(value: Int) {
        if (size() == data.size) {
            throw IllegalStateException("StackOvarflowException")
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
        return topVal
    }

    fun print() {
        print("Stack contains :")
        for (i in top downTo -1 + 1) {
            print(" " + data[i])
        }
        println("")
    }
}

fun main(args: Array<String>) {
    val s = Stack()
    s.push(1)
    s.push(2)
    s.push(3)
    s.print()
    println(s.pop())
    println(s.pop())
    s.print()
}