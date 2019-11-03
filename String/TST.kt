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
            equal = right
            left = equal
            data = dta
        }
    }

    fun add(word: String) {
        root = add(root, word, 0)
    }

    private fun add(curr: Node?, word: String, wordIndex: Int): Node {
        var curr = curr
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
        return if (word[wordIndex] < curr.data)
            find(curr.left, word, wordIndex)
        else if (word[wordIndex] > curr.data)
            find(curr.right, word, wordIndex)
        else {
            if (wordIndex == word.length - 1) curr.isLastChar else find(curr.equal, word, wordIndex + 1)
        }
    }

    fun find(word: String): Boolean {
        val ret = find(root, word, 0)
        print("$word :: ")
        if (ret)
            println(" Found ")
        else
            println("Not Found ")
        return ret
    }
}


fun main(args: Array<String>) {
    val tt = TST()
    tt.add("banana")
    tt.add("apple")
    tt.add("mango")
    println("\nSearch results for apple, banana, grapes and mango :")
    tt.find("apple")
    tt.find("banana")
    tt.find("mango")
    tt.find("grapes")
}