class rangeMaxST(input: IntArray) {
    var segArr: IntArray
    var n: Int

    init {
        n = input.size // Height of segment tree.
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
        segArr[index] = max(constructST(input, start, mid, index * 2 + 1),
                            constructST(input, mid + 1, end, index * 2 + 2)
        )
        return segArr[index]
    }

    fun max(first: Int, second: Int): Int {
        return if (first > second) first else second
    }

    fun getMax(start: Int, end: Int): Int {
        // Check for error conditions.
        if (start > end || start < 0 || end > n - 1) {
            println("Invalid Input.")
            return Int.MIN_VALUE
        }
        return getMaxUtil(0, n - 1, start, end, 0)
    }

    fun getMaxUtil(segStart: Int, segEnd: Int, queryStart: Int, queryEnd: Int, index: Int): Int {
        if (queryStart <= segStart && segEnd <= queryEnd) // complete overlapping case.
            return segArr[index]
        if (segEnd < queryStart || queryEnd < segStart) // no overlapping case.
            return Int.MIN_VALUE

        // Segment tree is partly overlaps with the query range.
        val mid = (segStart + segEnd) / 2
        return max(getMaxUtil(segStart, mid, queryStart, queryEnd, 2 * index + 1),
            getMaxUtil(mid + 1, segEnd, queryStart, queryEnd, 2 * index + 2)
        )
    }

    fun update(ind: Int, valu: Int) {
        // Check for error conditions.
        if (ind < 0 || ind > n - 1) {
            println("Invalid Input.")
            return
        }

        // Update the values in segment tree
        updateUtil(0, n - 1, ind, valu, 0)
    }

    // Always min inside valid range will be returned.
    fun updateUtil(segStart: Int, segEnd: Int, ind: Int, valu: Int, index: Int): Int {
        // Update index lies outside the range of current segment.
        // So minimum will not change.
        if (ind < segStart || ind > segEnd) return segArr[index]

        // If the input index is in range of this node, then update the
        // value of the node and its children
        if (segStart == segEnd) {
            return if (segStart == ind) { // Index value need to be updated.
                segArr[index] = valu
                valu
            } else {
                segArr[index] // index value is not changed.
            }
        }
        val mid = (segStart + segEnd) / 2

        // Current node value is updated with min. 
        segArr[index] = max(
            updateUtil(segStart, mid, ind, valu, 2 * index + 1),
            updateUtil(mid + 1, segEnd, ind, valu, 2 * index + 2))

        // Value of diff is propagated to the parent node.
        return segArr[index]
    }
}

// Testing Code.
fun main() {
    val arr = intArrayOf(1, 8, 2, 7, 3, 6, 4, 5)
    val tree = rangeMaxST(arr)
    println("Max value in the range(1, 5): " + tree.getMax(1, 5))
    println("Max value in the range(2, 7): " + tree.getMax(2, 7))
    println("Max value of all the elements: " + tree.getMax(0, arr.size - 1))
    tree.update(2, 9)
    println("Max value in the range(1, 5): " + tree.getMax(1, 5))
    println("Max value of all the elements: " + tree.getMax(0, arr.size - 1))
}

/*
Max value in the range(1, 5): 8
Max value in the range(2, 7): 7
Max value of all the elements: 8
Max value in the range(1, 5): 9
Max value of all the elements: 9
*/