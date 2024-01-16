class StringTree {
    class Node(var value: String? = null, var lChild: Node? = null, var rChild: Node? = null )

    var root: Node? = null
	
    fun print(curr: Node? = root) /* pre order */ {
        if (curr != null) {
            print("Value is :: " + curr.value!!)
            print(curr.lChild)
            print(curr.rChild)
        }
    }

    fun add(value: String) {
        root = add(root, value.lowercase())
    }

    private fun add(cur: Node?, value: String): Node {
        var curr = cur
        if (curr == null)
            return Node(value)

        val compare = curr.value!!.compareTo(value)
        if (compare == 1)
            curr.lChild = add(curr.lChild, value)
        else if (compare == -1)
            curr.rChild = add(curr.rChild, value)
        return curr
    }

    fun find(value: String): Boolean {
        return find(root, value.lowercase())
    }

    private fun find(curr: Node?, value: String): Boolean {
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

/*
Apple Found : true
Banana Found : true
Grapes Found : false
*/
