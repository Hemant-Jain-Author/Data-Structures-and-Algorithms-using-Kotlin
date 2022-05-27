class BinaryIndexTree internal constructor(arr: IntArray) {
    var BIT: IntArray
    var size: Int

    init {
        size = arr.size
        BIT = IntArray(size + 1)
        // Populating bit. 
        for (i in 0 until size) update(i, arr[i])
    }

    operator fun set(arr: IntArray, index: Int, value: Int) {
        val diff = value - arr[index]
        arr[index] = value

        // Difference is propagated.
        update(index, diff)
    }

    private fun update(indx: Int, value: Int) {
        // Index in bit is 1 more than the input array.
        var index = indx
        index = index + 1

        // Traverse to ancestors of nodes.
        while (index <= size) {
            // Add val to current node of Binary Index Tree.
            BIT[index] += value

            // Next element which need to store val.
            index += index and -index
        }
    }

    // Range sum in the range start to end.
    fun rangeSum(start: Int, end: Int): Int {
        // Check for error conditions.
        if (start > end || start < 0 || end > size-1) {
            println("Invalid Input.")
            return -1
        }
        return prefixSum(end) - prefixSum(start-1)
    }

    // Prefix sum in the range 0 to index.
    fun prefixSum(indx: Int): Int {
        var index = indx
        var sum = 0
        index = index + 1

        // Traverse ancestors of Binary Index Tree nodes.
        while (index > 0) {
            // Add current element to sum.
            sum += BIT[index]

            // Parent index calculation.
            index -= index and -index
        }
        return sum
    }
}

// Main function
fun main() {
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    val tree = BinaryIndexTree(arr)
    println("Sum of elements in range(0, 5): " + tree.prefixSum(5))
    println("Sum of elements in range(2, 5): " + tree.rangeSum(2, 5))

    // Set fourth element to 10.
    tree[arr, 3] = 10

    // Find sum after the value is updated
    println("Sum of elements in range(0, 5): " + tree.prefixSum(5))
}