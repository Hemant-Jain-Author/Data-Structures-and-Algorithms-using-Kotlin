class Trie {
    var root: Node? = null
    private val CharCount = 26


    inner class Node() {
        var isLastChar: Boolean = false
        var child: Array<Node?>

        init {
            child = arrayOfNulls(CharCount)
            for (i in 0 until CharCount) {
                child[i] = null
            }
            isLastChar = false
        }
    }

    init {
        root = Node()// first node with dummy value.
    }

    fun add(str: String?): Node? {
        return if (str == null) {
            root
        } else add(root, str.lowercase(), 0)
    }

    fun add(curr: Node?, str: String, index: Int): Node {
        if (curr == null) {
            return Node()
        }

        if (str.length == index) {
            curr.isLastChar = true
        } else {
            curr.child[str[index] - 'a'] = add(curr.child[str[index] - 'a'], str, index + 1)
        }
        return curr
    }

    fun remove(st: String?) {
        var str: String? = st ?: return
        str = str!!.lowercase()
        remove(root, str, 0)
    }

    fun remove(curr: Node?, str: String, index: Int) {
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

    fun find(st: String?): Boolean {
        var str: String? = st ?: return false
        str = str!!.lowercase()
        return find(root, str, 0)
    }

    fun find(curr: Node?, str: String, index: Int): Boolean {
        if (curr == null) {
            return false
        }
        return if (str.length == index) {
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