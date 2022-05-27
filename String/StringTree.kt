class StringTree {
    var root: Node? = null

    class Node(var value: String? = null, var lChild: Node? = null, var rChild: Node? = null )

    fun print(curr: Node? = root) /* pre order */ {
        if (curr != null) {
            print("Value is :: " + curr.value!!)
            print(curr.lChild)
            print(curr.rChild)
        }
    }

    fun add(value: String) {
        root = add(value, root)
    }

    fun add(value: String, cur: Node?): Node {
        var curr = cur
        if (curr == null)
            return Node(value)

        val compare = curr.value!!.compareTo(value)
        if (compare == 1)
            curr.lChild = add(value, curr.lChild)
        else if (compare == -1)
            curr.rChild = add(value, curr.rChild)
        return curr
    }

    fun find(value: String): Boolean {
        val ret = find(root, value)
        return ret
    }

    fun find(curr: Node?, value: String): Boolean {
        if (curr == null)
            return false
        val compare = curr.value!!.compareTo(value)
        if (compare == 0)
            return true
        else {
            if (compare == 1)
                return find(curr.lChild, value)
            else
                return find(curr.rChild, value)
        }
    }

    fun freeTree() {
        root = null
    }
}

fun main() {
    val tt = StringTree()
    tt.add("banana");
    tt.add("apple");
    tt.add("mango");
    println("Apple Found : " + tt.find("apple"));
    println("Banana Found : " + tt.find("banana"));
    println("Grapes Found : " + tt.find("grapes"));
}