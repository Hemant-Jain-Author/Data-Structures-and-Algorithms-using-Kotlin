class HashTableSC {

    private val tableSize: Int
    internal var listArray: Array<Node?>

    inner class Node( var value: Int,  var next: Node?)

    init {
        tableSize = 512
        listArray = arrayOfNulls(tableSize)
    }

    private fun computeHash(key: Int)// division method
            : Int {
        return key % tableSize
    }

    fun add(value: Int) {
        val index = computeHash(value)
        listArray[index] = Node(value, listArray[index])
    }

    fun remove(value: Int): Boolean {
        val index = computeHash(value)
        var nextNode: Node?
        var head: Node? = listArray[index]
        if (head != null && head.value == value) {
            listArray[index] = head.next
            return true
        }
        while (head != null) {
            nextNode = head.next
            if (nextNode != null && nextNode.value == value) {
                head.next = nextNode.next
                return true
            } else {
                head = nextNode
            }
        }
        return false
    }

    fun print() {
        for (i in 0 until tableSize) {
            println("printing for index value :: " + i + "List of value printing :: ")
            var head: Node? = listArray!![i]
            while (head != null) {
                println(head.value)
                head = head.next
            }
        }
    }

    fun find(value: Int): Boolean {
        val index = computeHash(value)
        var head: Node? = listArray!![index]
        while (head != null) {
            if (head.value == value) {
                return true
            }
            head = head.next
        }
        return false
    }
}

fun main(args: Array<String>) {
    val ht = HashTableSC()

    for (i in 100..109) {
        ht.add(i)
    }
    println("search 100 :: " + ht.find(100))
    println("remove 100 :: " + ht.remove(100))
    println("search 100 :: " + ht.find(100))
    println("remove 100 :: " + ht.remove(100))
}