class HashTableSC {
    inner class Node( val key: Int, val value: Int, var next: Node?)

    var listArray: Array<Node?>
    private val tableSize = 512

    init {
        listArray = arrayOfNulls(tableSize)
    }

    private fun computeHash(key: Int): Int { // division method
        return key % tableSize
    }

    fun add(key: Int, value: Int) {
        val index = computeHash(key)
        listArray[index] = Node(key, value, listArray[index])
    }

    fun add(value: Int) {
        add(value, value)
    }

    fun remove(key: Int): Boolean {
        val index = computeHash(key)
        var nextNode: Node?
        var head: Node? = listArray[index]
        if (head != null && head.key == key) {
            listArray[index] = head.next
            return true
        }
        while (head != null) {
            nextNode = head.next
            if (nextNode != null && nextNode.key == key) {
                head.next = nextNode.next
                return true
            } else {
                head = nextNode
            }
        }
        return false
    }

    fun print() {
        print("Hash Table contains ::")
        for (i in 0 until tableSize) {
            var head: Node? = listArray[i]
            while (head != null) {
                print("(" + head.key + "=>" + head.value + ") ")
                head = head.next
            }
        }
        println()
    }

    fun find(key: Int): Boolean {
        val index = computeHash(key)
        var head = listArray[index]
        while (head != null) {
            if (head.key == key) {
                return true
            }
            head = head.next
        }
        return false
    }

    fun get(key: Int): Int {
        val index = computeHash(key)
        var head = listArray[index]
        while (head != null) {
            if (head.key == key) {
                return head.value
            }
            head = head.next
        }
        return -1
    }
}

// Testing code
fun main() {
    val ht = HashTableSC()
    ht.add(1, 10)
    ht.add(2, 20)
    ht.add(3, 30)
    ht.print()
    println("Find key 2 : " + ht.find(2))
    println("Value at  key 2 : " + ht.get(2))
    ht.remove(2)
    println("Find key 2 : " + ht.find(2))
}
    