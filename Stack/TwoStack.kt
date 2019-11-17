class TwoStack {
    private val MAX_SIZE = 50
    internal var top1: Int = 0
    internal var top2: Int = 0
    internal var data: IntArray

    init {
        top1 = -1
        top2 = MAX_SIZE
        data = IntArray(MAX_SIZE)
    }

    fun StackPush1(value: Int) {
        if (top1 < top2 - 1) {
            data[++top1] = value
        } else {
            print("Stack is Full!")
        }
    }

    fun StackPush2(value: Int) {
        if (top1 < top2 - 1) {
            data[--top2] = value
        } else {
            print("Stack is Full!")
        }
    }

    fun StackPop1(): Int {
        if (top1 >= 0) {
            return data[top1--]
        } else {
            print("Stack is Empty!")
        }
        return Int.MIN_VALUE
    }

    fun StackPop2(): Int {
        if (top2 < MAX_SIZE) {
            return data[top2++]
        } else {
            print("Stack is Empty!")
        }
        return Int.MIN_VALUE
    }
}

fun main(args: Array<String>) {
    val st = TwoStack()
    for (i in 0..9) {
        st.StackPush1(i)
    }
    for (j in 0..9) {
        st.StackPush2(j + 10)
    }
    for (i in 0..9) {
        println("stack one pop value is : " + st.StackPop1())
        println("stack two pop value is : " + st.StackPop2())
    }
}