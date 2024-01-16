class HashTableLP(private val tableSize: Int) {
    var Key: IntArray
    var Value: IntArray
    var Flag: IntArray

    companion object {
        private val EMPTY_VALUE = -1
        private val DELETED_VALUE = -2
        private val FILLED_VALUE = 0
    }
	
    init {
        Key = IntArray(tableSize + 1)
        Value = IntArray(tableSize + 1)
        Flag = IntArray(tableSize + 1){EMPTY_VALUE}
    }

    /* Other Methods */
	fun computeHash(key: Int): Int {
	    return key % tableSize
	}
	
	fun resolverFun(index: Int): Int {
	    return index
	}
	
	fun resolverFun2(index: Int): Int {
	    return index * index
	}
	
	fun add(key: Int, value: Int): Boolean {
	    var hashValue = computeHash(key)
	    for (i in 0 until tableSize) {
	        if (Flag[hashValue] == EMPTY_VALUE || Flag[hashValue] == DELETED_VALUE) {
	            Key[hashValue] = key
	            Value[hashValue] = value
	            Flag[hashValue] = FILLED_VALUE
	            return true
	        }
	        hashValue += resolverFun(i)
	        hashValue %= tableSize
	    }
	    return false
	}
	
	fun add(value: Int): Boolean {
	    return add(value, value)
	}
	
	fun find(key: Int): Boolean {
	    var hashValue = computeHash(key)
	    for (i in 0 until tableSize) {
	        if (Flag[hashValue] == EMPTY_VALUE) {
	            return false
	        }
	        if (Flag[hashValue] == FILLED_VALUE && Key[hashValue] == key) {
	            return true
	        }
	        hashValue += resolverFun(i)
	        hashValue %= tableSize
	    }
	    return false
	}
	
	fun get(key: Int): Int {
	    var hashValue = computeHash(key)
	    for (i in 0 until tableSize) {
	        if (Flag[hashValue] == EMPTY_VALUE) {
	            return -1
	        }
	        if (Flag[hashValue] == FILLED_VALUE && Key[hashValue] == key) {
	            return Value[hashValue]
	        }
	        hashValue += resolverFun(i)
	        hashValue %= tableSize
	    }
	    return -1
	}
	
	fun remove(key: Int): Boolean {
	    var hashValue = computeHash(key)
	    for (i in 0 until tableSize) {
	        if (Flag[hashValue] == EMPTY_VALUE) {
	            return false
	        }
	        if (Flag[hashValue] == FILLED_VALUE && Key[hashValue] == key) {
	            Flag[hashValue] = DELETED_VALUE
	            return true
	        }
	        hashValue += resolverFun(i)
	        hashValue %= tableSize
	    }
	    return false
	}
	
	fun print() {
	    print("Hash Table contains ::")
	    for (i in 0 until tableSize) {
	        if (Flag[i] == FILLED_VALUE) {
	            print("(" + Key[i] + "=>" + Value[i] + ") ")
	        }
	    }
	    println()
	}
}

// Testing code
fun main() {
    val ht = HashTableLP(1000)
    ht.add(1, 10)
    ht.add(2, 20)
    ht.add(3, 30)
    ht.print()
    println("Find key 2 : " + ht.find(2))
    println("Value at key 2 : " + ht.get(2))
    ht.remove(2)
    println("Find key 2 : " + ht.find(2))
}