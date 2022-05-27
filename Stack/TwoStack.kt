class TwoStack {
    private val MAX_SIZE = 50
    var top1: Int
    var top2: Int
    var data: IntArray

    init {
        top1 = -1
        top2 = MAX_SIZE
        data = IntArray(MAX_SIZE)
    }

    fun push1(value: Int) {
        if (top1 < top2 - 1) {
            data[++top1] = value
        } else {
            print("Stack is Full!")
        }
    }

    fun push2(value: Int) {
        if (top1 < top2 - 1) {
            data[--top2] = value
        } else {
            print("Stack is Full!")
        }
    }

    fun pop1(): Int {
        if (top1 >= 0) {
            return data[top1--]
        } else {
            print("Stack Empty!")
        }
        return -999
    }

    fun pop2(): Int {
        if (top2 < MAX_SIZE) {
            return data[top2++]
        } else {
            print("Stack Empty!")
        }
        return -999
    }
}

fun main() {
    val st = TwoStack()
    st.push1(1)
    st.push1(2)
    st.push1(3)
    st.push2(4)
    st.push2(5)
    st.push2(6)
    println("stk1 pop: " + st.pop1())
    println("stk1 pop: " + st.pop1())
    println("stk2 pop: " + st.pop2())
    println("stk2 pop: " + st.pop2())
}