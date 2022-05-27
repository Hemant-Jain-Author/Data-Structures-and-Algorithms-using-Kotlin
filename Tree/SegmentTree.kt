class SegmentTree(input: IntArray) {
    var segArr: IntArray
    var size: Int

    init {
        size = input.size
        // Height of segment tree.
        val x: Int = Math.ceil(Math.log(size.toDouble()) / Math.log(2.0)).toInt()
        //Maximum size of segment tree
        val max_size: Int = 2 * Math.pow(2.0, x.toDouble()).toInt() - 1
        // Allocate memory for segment tree
        segArr = IntArray(max_size)
        constructST(input, 0, size - 1, 0)
    }

    fun constructST(input: IntArray, start: Int, end: Int, index: Int): Int {
        // Store it in current node of the segment tree and return
        if (start == end) {
            segArr[index] = input[start]
            return input[start]
        }

        // If there are more than one elements, 
        // then traverse left and right subtrees 
        // and store the sum of values in current node.
        val mid = (start + end) / 2
        segArr[index] = constructST(input, start, mid, index * 2 + 1) + constructST(input, mid + 1, end, index * 2 + 2)
        return segArr[index]
    }

    fun getSum(start: Int, end: Int): Int {
        // Check for error conditions.
        if (start > end || start < 0 || end > size - 1) {
            println("Invalid Input.")
            return -1
        }
        return getSumUtil(0, size - 1, start, end, 0)
    }

    fun getSumUtil(segStart: Int, segEnd: Int, queryStart: Int, queryEnd: Int, index: Int): Int {
        if (queryStart <= segStart && segEnd <= queryEnd) // complete overlapping case.
            return segArr[index]
        if (segEnd < queryStart || queryEnd < segStart) // no overlapping case.
            return 0

        // Segment tree is partly overlaps with the query range.
        val mid = (segStart + segEnd) / 2
        return (getSumUtil(segStart, mid, queryStart, queryEnd, 2 * index + 1)
                + getSumUtil(mid + 1, segEnd, queryStart, queryEnd, 2 * index + 2))
    }

    operator fun set(arr: IntArray, ind: Int, `val`: Int) {
        // Check for error conditions.
        if (ind < 0 || ind > size - 1) {
            println("Invalid Input.")
            return
        }
        arr[ind] = `val`

        // Set new value in segment tree
        setUtil(0, size - 1, ind, `val`, 0)
    }

    // Always diff will be returned.
    fun setUtil(segStart: Int, segEnd: Int, ind: Int, `val`: Int, index: Int): Int {
        // set index lies outside the range of current segment.
        // So diff to its parent node will be zero.
        if (ind < segStart || ind > segEnd) return 0

        // If the input index is in range of this node, then set the
        // value of the node and its children
        if (segStart == segEnd) {
            return if (segStart == ind) { // Index that need to be set.
                val diff = `val` - segArr[index]
                segArr[index] = `val`
                diff
            } else {
                0
            }
        }
        val mid = (segStart + segEnd) / 2
        val diff =
            setUtil(segStart, mid, ind, `val`, 2 * index + 1) + setUtil(mid + 1, segEnd, ind, `val`, 2 * index + 2)

        // Current node value is set with diff. 
        segArr[index] = segArr[index] + diff

        // Value of diff is propagated to the parent node.
        return diff
    }
}

fun main() {
    val arr = intArrayOf(1, 2, 4, 8, 16, 32, 64)
    val tree = SegmentTree(arr)
    println("Sum of values in the range(0, 3): " + tree.getSum(1, 3))
    println("Sum of values of all the elements: " + tree.getSum(0, arr.size - 1))
    tree[arr, 1] = 10
    println("Sum of values in the range(0, 3): " + tree.getSum(1, 3))
    println("Sum of values of all the elements: " + tree.getSum(0, arr.size - 1))
}