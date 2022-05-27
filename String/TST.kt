class TST {
    var root: Node? = null

    class Node {
        var isLastChar: Boolean = false
        var left: Node? = null
        var equal: Node? = null
        var right: Node? = null
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
        root = add(root, word, 0)
    }

    private fun add(cur: Node?, word: String, wordIndex: Int): Node {
        var curr = cur
        if (curr == null)
            curr = Node(word[wordIndex])

        if (word[wordIndex] < curr.data)
            curr.left = add(curr.left, word, wordIndex)
        else if (word[wordIndex] > curr.data)
            curr.right = add(curr.right, word, wordIndex)
        else {
            if (wordIndex < word.length - 1)
                curr.equal = add(curr.equal, word, wordIndex + 1)
            else
                curr.isLastChar = true
        }
        return curr
    }

    private fun find(curr: Node?, word: String, wordIndex: Int): Boolean {
        if (curr == null)
            return false
        if (word[wordIndex] < curr.data)
            return find(curr.left, word, wordIndex)
        else if (word[wordIndex] > curr.data)
            return find(curr.right, word, wordIndex)
        else {
            if (wordIndex == word.length - 1) 
                return curr.isLastChar 
            else 
                return find(curr.equal, word, wordIndex + 1)
        }
    }

    fun find(word: String): Boolean {
        return find(root, word, 0)
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