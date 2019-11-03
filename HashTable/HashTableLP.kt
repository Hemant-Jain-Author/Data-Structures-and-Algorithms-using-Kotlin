class HashTableLP(private val tableSize: Int) {
    internal var Arr: IntArray
    internal var Flag: IntArray

    init {
        Arr = IntArray(tableSize + 1)
        Flag = IntArray(tableSize + 1, { EMPTY_VALUE})
    }

    companion object {
        private val EMPTY_VALUE = -1
        private val DELETED_VALUE = -2
        private val FILLED_VALUE = 0
    }

    /* Other Methods */

    internal fun computeHash(key: Int): Int {
        return key % tableSize
    }

    internal fun resolverFun(index: Int): Int {
        return index
    }

    internal fun resolverFun2(index: Int): Int {
        return index * index
    }

    internal fun add(value: Int): Boolean {
        var hashValue = computeHash(value)
        for (i in 0 until tableSize) {
            if (Flag!![hashValue] == EMPTY_VALUE || Flag!![hashValue] == DELETED_VALUE) {
                Arr[hashValue] = value
                Flag[hashValue] = FILLED_VALUE
                return true
            }
            hashValue += resolverFun(i)
            hashValue %= tableSize
        }
        return false
    }

    internal fun find(value: Int): Boolean {
        var hashValue = computeHash(value)
        for (i in 0 until tableSize) {
            if (Flag!![hashValue] == EMPTY_VALUE) {
                return false
            }

            if (Flag!![hashValue] == FILLED_VALUE && Arr!![hashValue] == value) {
                return true
            }

            hashValue += resolverFun(i)
            hashValue %= tableSize
        }
        return false
    }

    internal fun remove(value: Int): Boolean {
        var hashValue = computeHash(value)
        for (i in 0 until tableSize) {
            if (Flag!![hashValue] == EMPTY_VALUE) {
                return false
            }

            if (Flag!![hashValue] == FILLED_VALUE && Arr!![hashValue] == value) {
                Flag[hashValue] = DELETED_VALUE
                return true
            }
            hashValue += resolverFun(i)
            hashValue %= tableSize
        }
        return false
    }

    internal fun print() {
        for (i in 0 until tableSize) {
            if (Flag!![i] == FILLED_VALUE) {
                println("Node at index [" + i + " ] :: " + Arr!![i])
            }
        }
    }
}

fun main(args: Array<String>) {
    val ht = HashTableLP(1000)
    ht.add(1)
    ht.add(2)
    ht.add(3)
    ht.print()
    println(ht.remove(1))
    println(ht.remove(4))
    ht.print()
}