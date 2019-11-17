class StringTree {
    var root: Node? = null

    inner class Node {
        var value: String? = null
        var count: Int = 0
        var lChild: Node? = null
        var rChild: Node? = null
    }


    fun print(curr: Node? = root) /* pre order */ {
        if (curr != null) {
            print(" value is ::" + curr.value!!)
            println(" count is :: " + curr.count)
            print(curr.lChild)
            print(curr.rChild)
        }
    }

    fun add(value: String) {
        root = add(value, root)
    }

    fun add(value: String, curr: Node?): Node {
        var curr = curr
        if (curr == null) {
            curr = Node()
            curr.value = value
            curr.rChild = null
            curr.lChild = curr.rChild
            curr.count = 1
        } else {
            val compare = curr.value!!.compareTo(value)
            if (compare == 0)
                curr.count++
            else if (compare == 1)
                curr.lChild = add(value, curr.lChild)
            else
                curr.rChild = add(value, curr.rChild)
        }
        return curr
    }

    fun find(value: String): Boolean {
        val ret = find(root, value)
        println("Find $value Return $ret")
        return ret
    }

    fun find(curr: Node?, value: String): Boolean {
        if (curr == null)
            return false
        val compare = curr.value!!.compareTo(value)
        return if (compare == 0)
            true
        else {
            if (compare == 1)
                find(curr.lChild, value)
            else
                find(curr.rChild, value)
        }
    }

    fun frequency(value: String): Int {
        return frequency(root, value)
    }

    fun frequency(curr: Node?, value: String): Int {
        if (curr == null)
            return 0

        val compare = curr.value!!.compareTo(value)
        return if (compare == 0)
            curr.count
        else {
            if (compare > 0)
                frequency(curr.lChild, value)
            else
                frequency(curr.rChild, value)
        }
    }

    fun freeTree() {
        root = null
    }
}

fun main(args: Array<String>) {
    val tt = StringTree()
    tt.add("banana")
    tt.add("apple")
    tt.add("mango")
    tt.add("banana")
    tt.add("apple")
    tt.add("mango")
    println("Search results for apple, banana, grapes and mango :")
    tt.find("apple")
    tt.find("banana")
    tt.find("banan")
    tt.find("applkhjkhkj")
    tt.find("grapes")
    tt.find("mango")
    tt.print()
    println("frequency(apple) returned :: " + tt.frequency("apple"))
    println("frequency(banana) returned :: " + tt.frequency("banana"))
    println("frequency(mango) returned :: " + tt.frequency("mango"))
    println("frequency(hemant) returned :: " + tt.frequency("hemant"))
}