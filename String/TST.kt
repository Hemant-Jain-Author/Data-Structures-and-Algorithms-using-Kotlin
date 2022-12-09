class TST {
    var root: Node? = null

    class Node {
        var isLastChar: Boolean
        var left: Node?
        var equal: Node?
        var right: Node?
        var data : Char

        constructor(dta: Char){
            isLastChar = false
            right = null
            equal = null
            left = null
            data = dta
        }
    }

    fun add(word: String) {
        root = add(root, word.lowercase(), 0)
    }

    private fun add(cur: Node?, word: String, index: Int): Node {
        var curr = cur
        if (curr == null)
            curr = Node(word[index])

        if (word[index] < curr.data)
            curr.left = add(curr.left, word, index)
        else if (word[index] > curr.data)
            curr.right = add(curr.right, word, index)
        else {
            if (index < word.length - 1)
                curr.equal = add(curr.equal, word, index + 1)
            else
                curr.isLastChar = true
        }
        return curr
    }

    private fun find(curr: Node?, word: String, index: Int): Boolean {
        if (curr == null)
            return false
        if (word[index] < curr.data)
            return find(curr.left, word, index)
        else if (word[index] > curr.data)
            return find(curr.right, word, index)
        else {
            if (index == word.length - 1) 
                return curr.isLastChar 
            else 
                return find(curr.equal, word, index + 1)
        }
    }

    fun find(word: String): Boolean {
        return find(root, word.lowercase(), 0)
    }
}

fun main() {
    val tt = TST()
    tt.add("banana");
    tt.add("apple");
    tt.add("mango");
    println("Apple Found : " + tt.find("apple"));
    println("Banana Found : " + tt.find("banana"));
    println("Grapes Found : " + tt.find("grapes"));
}