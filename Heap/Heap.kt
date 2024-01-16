class Heap {
    private var size : Int // Number of elements in Heap
    private var arr : IntArray // The Heap array
    var isMinHeap: Boolean

    constructor(isMin: Boolean) {
        arr = IntArray(32) // Initial capacity is 32
        size = 0
        isMinHeap = isMin
    }

    constructor(array: IntArray, isMin: Boolean) {
        size = array.size
        arr = array
        isMinHeap = isMin
        // Build Heap operation over array
        for (i in size / 2 downTo 0) {
            percolateDown(i)
        }
    }

    fun compare(arr: IntArray, first: Int, second: Int): Boolean {
        if (isMinHeap) 
            return arr[first] - arr[second] > 0 // Min heap compare
        else 
            return arr[first] - arr[second] < 0 // Max heap compare
    }

    fun isEmpty(): Boolean {
        return size == 0
    }

    fun length(): Int {
        return size
    }

    fun peek(): Int {
        if (isEmpty()) {
            throw IllegalStateException()
        }
        return arr[0]
    }

    // Other Methods.
    private fun percolateDown(parent: Int) {
        val lChild = 2 * parent + 1
        val rChild = lChild + 1
        var child = -1
        if (lChild < size) {
            child = lChild
        }
        if (rChild < size && compare(arr, lChild, rChild)) {
            child = rChild
        }
        if (child != -1 && compare(arr, parent, child)) {
            val temp = arr[parent]
            arr[parent] = arr[child]
            arr[child] = temp
            percolateDown(child)
        }
    }

    private fun percolateUp(child: Int) {
        val parent = (child - 1) / 2
        if (parent >= 0 && compare(arr, parent, child)) {
            val temp = arr[child]
            arr[child] = arr[parent]
            arr[parent] = temp
            percolateUp(parent)
        }
    }

    fun add(value: Int) {
        if (size == arr.size) {
            doubleSize()
        }
        arr[size++] = value
        percolateUp(size - 1)
    }

    private fun doubleSize() {
        val old = arr
        arr = IntArray(arr.size * 2)
        System.arraycopy(old, 0, arr, 0, size)
    }

    fun remove(): Int {
        if (isEmpty()) {
            throw IllegalStateException()
        }
        val value = arr[0]
        arr[0] = arr[size - 1]
        size--
        percolateDown(0)
        return value
    }

    fun print() {
        print("Heap : ")
        for (i in 0 until size) {
            print(arr[i].toString() + " ")
        }
        println()
    }

    fun delete(value: Int): Boolean {
        for (i in 0 until size) {
            if (arr[i] == value) {
                arr[i] = arr[size - 1]
                size -= 1
                percolateUp(i)
                percolateDown(i)
                return true
            }
        }
        return false
    }
}

// Testing code.
fun main1() {
    val hp = Heap(true)
    hp.add(1)
    hp.add(6)
    hp.add(5)
    hp.add(7)
    hp.add(3)
    hp.add(4)
    hp.add(2)
    hp.print()
    while (!hp.isEmpty()) 
        print(hp.remove().toString() + " ")
    println()
}

/*
1 3 2 7 6 5 4 
1 2 3 4 5 6 7 
*/

fun heapSort(array: IntArray, inc: Boolean) {
    // Create max heap for increasing order sorting.
    val hp = Heap(array, !inc)
    for (i in array.indices) {
        array[array.size - i - 1] = hp.remove()
    }
}

// Testing Code.
fun main2() {
    val a2 = intArrayOf(1, 9, 6, 7, 8, 2, 4, 5, 3)
    heapSort(a2, true)
    for (i in a2.indices) {
        print(a2[i].toString() + " ")
    }
    println()
    val a3 = intArrayOf(1, 9, 6, 7, 8, 2, 4, 5, 3)
    heapSort(a3, false)
    for (i in a3.indices) {
        print(a3[i].toString() + " ")
    }
}

/*
1 2 3 4 5 6 7 8 9 
9 8 7 6 5 4 3 2 1
*/

// Testing code
fun main() {
    main1()
    main2();
}