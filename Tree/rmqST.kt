class rmqST(input: IntArray) {
    var segArr: IntArray
    var n: Int

    init {
        n = input.size
        // Height of segment tree.
        val x: Int = Math.ceil(Math.log(n.toDouble()) / Math.log(2.0)).toInt()
        //Maximum size of segment tree
        val max_size: Int = 2 * Math.pow(2.0, x.toDouble()).toInt() - 1
        // Allocate memory for segment tree
        segArr = IntArray(max_size)
        constructST(input, 0, n - 1, 0)
    }

    fun constructST(input: IntArray, start: Int, end: Int, index: Int): Int {
        // Store it in current node of the segment tree and return
        if (start == end) {
            segArr[index] = input[start]
            return input[start]
        }

        // If there are more than one elements, 
        // then traverse left and right subtrees 
        // and store the minimum of values in current node.
        val mid = (start + end) / 2
        segArr[index] = min(
            constructST(input, start, mid, index * 2 + 1),
            constructST(input, mid + 1, end, index * 2 + 2)
        )
        return segArr[index]
    }

    fun min(first: Int, second: Int): Int {
        return if (first < second) first else second
    }

    fun getMin(start: Int, end: Int): Int {
        // Check for error conditions.
        if (start > end || start < 0 || end > n - 1) {
            println("Invalid Input.")
            return Int.MAX_VALUE
        }
        return getMinUtil(0, n - 1, start, end, 0)
    }

    fun getMinUtil(segStart: Int, segEnd: Int, queryStart: Int, queryEnd: Int, index: Int): Int {
        if (queryStart <= segStart && segEnd <= queryEnd) // complete overlapping case.
            return segArr[index]
        if (segEnd < queryStart || queryEnd < segStart) // no overlapping case.
            return Int.MAX_VALUE

        // Segment tree is partly overlaps with the query range.
        val mid = (segStart + segEnd) / 2
        return min(
            getMinUtil(segStart, mid, queryStart, queryEnd, 2 * index + 1),
            getMinUtil(mid + 1, segEnd, queryStart, queryEnd, 2 * index + 2)
        )
    }

    fun update(ind: Int, `val`: Int) {
        // Check for error conditions.
        if (ind < 0 || ind > n - 1) {
            println("Invalid Input.")
            return
        }

        // Update the values in segment tree
        updateUtil(0, n - 1, ind, `val`, 0)
    }

    // Always min inside valid range will be returned.
    fun updateUtil(segStart: Int, segEnd: Int, ind: Int, `val`: Int, index: Int): Int {
        // Update index lies outside the range of current segment.
        // So minimum will not change.
        if (ind < segStart || ind > segEnd) return segArr[index]

        // If the input index is in range of this node, then update the
        // value of the node and its children
        if (segStart == segEnd) {
            return if (segStart == ind) { // Index value need to be updated.
                segArr[index] = `val`
                `val`
            } else {
                segArr[index] // index value is not changed.
            }
        }
        val mid = (segStart + segEnd) / 2

        // Current node value is updated with min. 
        segArr[index] = min(
            updateUtil(segStart, mid, ind, `val`, 2 * index + 1),
            updateUtil(mid + 1, segEnd, ind, `val`, 2 * index + 2)
        )

        // Value of diff is propagated to the parent node.
        return segArr[index]
    }
}

fun main() {
    val arr = intArrayOf(2, 3, 1, 7, 12, 5)
    val tree = rmqST(arr)
    println("Min value in the range(1, 5): " + tree.getMin(1, 5))
    println("Min value of all the elements: " + tree.getMin(0, arr.size - 1))
    tree.update(2, -1)
    println("Min value in the range(1, 5): " + tree.getMin(1, 5))
    println("Min value of all the elements: " + tree.getMin(0, arr.size - 1))
    tree.update(5, -2)
    println("Min value in the range(0, 4): " + tree.getMin(0, 4))
    println("Min value of all the elements: " + tree.getMin(0, arr.size - 1))
}

/*
Min value in the range(1, 5): 1
Min value of all the elements: 1
Min value in the range(1, 5): -1
Min value of all the elements: -1
Min value in the range(0, 4): -1
Min value of all the elements: -2
*/