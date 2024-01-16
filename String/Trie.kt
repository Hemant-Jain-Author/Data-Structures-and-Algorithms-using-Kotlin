class Trie {
    inner class Node() {
        var isLastChar : Boolean = false
        var child: Array<Node?>  = arrayOfNulls(CharCount)
    }

	var root: Node? = null
    private val CharCount = 26

    init {
        root = Node() // first node with dummy value.
    }

    fun add(str: String?): Node? {
        return if (str == null) {
            root
        } else add(root, str.lowercase(), 0)
    }

    private fun add(nd: Node?, str: String, index: Int): Node {
        var curr = nd
        if (curr == null) {
            curr =  Node()
        }

        if (str.length == index) {
            curr.isLastChar = true
        } else {
            curr.child[str[index] - 'a'] = add(curr.child[str[index] - 'a'], str, index + 1)
        }
        return curr
    }

    fun remove(str: String?) {
        if (str != null) {
            remove(root, str.lowercase(), 0)
        }
    }

    private fun remove(curr: Node?, str: String, index: Int) {
        if (curr == null) {
            return
        }
        if (str.length == index) {
            if (curr.isLastChar) {
                curr.isLastChar = false
            }
            return
        }
        remove(curr.child[str[index] - 'a'], str, index + 1)
    }

    fun find(str: String?): Boolean {
        return if (str == null) {
            false
        } else find(root, str.lowercase(), 0)
    }

    private fun find(curr: Node?, str: String, index: Int): Boolean {
        return if (curr == null) {
            false
        } else if (str.length == index) {
            curr.isLastChar
        } else find(curr.child[str[index] - 'a'], str, index + 1)
    }
}

fun main() {
    val tt = Trie()
    tt.add("banana");
    tt.add("apple");
    tt.add("mango");
    println("Apple Found : " + tt.find("apple"));
    println("Banana Found : " + tt.find("banana"));
    println("Grapes Found : " + tt.find("grapes"));
}