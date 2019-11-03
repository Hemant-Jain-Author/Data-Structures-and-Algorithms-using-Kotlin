class Trie {
    var root: Node? = null
    private val CharCount = 26


    inner class Node(c: Char) {
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
        root = Node(' ')// first node with dummy value.
    }

    fun add(str: String?): Node? {
        return if (str == null) {
            root
        } else add(root, str.toLowerCase(), 0)
    }

    fun add(curr: Node?, str: String, index: Int): Node {
        var curr = curr
        if (curr == null) {
            curr = Node(str[index - 1])
        }

        if (str.length == index) {
            curr.isLastChar = true
        } else {
            curr.child[str[index] - 'a'] = add(curr.child!![str[index] - 'a'], str, index + 1)
        }
        return curr
    }

    fun remove(str: String?) {
        var str: String? = str ?: return
        str = str!!.toLowerCase()
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
        remove(curr.child!![str[index] - 'a'], str, index + 1)
    }

    fun find(str: String?): Boolean {
        var str: String? = str ?: return false
        str = str!!.toLowerCase()
        return find(root, str, 0)
    }

    fun find(curr: Node?, str: String, index: Int): Boolean {
        if (curr == null) {
            return false
        }
        return if (str.length == index) {
            curr.isLastChar
        } else find(curr.child!![str[index] - 'a'], str, index + 1)
    }
}

fun main(args: Array<String>) {
    val t = Trie()
    val a = "hemant"
    val b = "heman"
    val c = "hemantjain"
    val d = "jain"
    t.add(a)
    t.add(d)
    println(t.find(a))
    t.remove(a)
    t.remove(d)
    println(t.find(a))
    println(t.find(c))
    println(t.find(d))
}
