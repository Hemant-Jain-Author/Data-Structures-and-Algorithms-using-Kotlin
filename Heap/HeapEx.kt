import java.util.PriorityQueue
import java.util.Collections
import java.util.Arrays

fun demo() {
    val pq: PriorityQueue<Int> = PriorityQueue<Int>()
    // PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
    val arr = intArrayOf(1, 2, 10, 8, 7, 3, 4, 6, 5, 9)
    for (i in arr) {
        pq.add(i)
    }
    println("Printing Priority Queue Heap : $pq")
    print("Dequeue elements of Priority Queue ::")
    while (pq.isEmpty() == false) {
        print(" " + pq.remove())
    }
}

fun kthSmallest(arr: IntArray, k: Int): Int {
    Arrays.sort(arr)
    return arr[k - 1]
}

fun kthSmallest2(arr: IntArray, size: Int, k: Int): Int {
    val pq: PriorityQueue<Int> = PriorityQueue<Int>()
    for (i in 0 until size) pq.add(arr[i])
    for (i in 0 until k - 1) pq.remove()
    return pq.peek()
}

fun kthSmallest3(arr: IntArray, size: Int, k: Int): Int {
    val pq: PriorityQueue<Int> = PriorityQueue<Int>(Collections.reverseOrder<Int>())
    for (i in 0 until size) {
        if (i < k) pq.add(arr[i]) else {
            if (pq.peek() > arr[i]) {
                pq.add(arr[i])
                pq.remove()
            }
        }
    }
    return pq.peek()
}

fun kthLargest(arr: IntArray, size: Int, k: Int): Int {
    var value = 0
    val pq: PriorityQueue<Int> = PriorityQueue<Int>(Collections.reverseOrder<Int>())
    for (i in 0 until size) pq.add(arr[i])
    for (i in 0 until k) value = pq.remove()
    return value
}

fun isMinHeap(arr: IntArray, size: Int): Boolean {
    var lchild: Int
    var rchild: Int
    // last element index size - 1
    for (parent in 0 until size / 2 + 1) {
        lchild = parent * 2 + 1
        rchild = parent * 2 + 2
        // heap property check.
        if (lchild < size && arr[parent] > arr[lchild] || rchild < size && arr[parent] > arr[rchild]) return false
    }
    return true
}

fun isMaxHeap(arr: IntArray, size: Int): Boolean {
    var lchild: Int
    var rchild: Int
    // last element index size - 1
    for (parent in 0 until size / 2 + 1) {
        lchild = parent * 2 + 1
        rchild = lchild + 1
        // heap property check.
        if (lchild < size && arr[parent] < arr[lchild] || rchild < size && arr[parent] < arr[rchild]) return false
    }
    return true
}

fun main1() {
    val arr = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    println("Kth Smallest :: " + kthSmallest(arr, 3))
    val arr2 = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    println("Kth Smallest :: " + kthSmallest2(arr2, arr2.size, 3))
}

/*
Kth Smallest :: 5
Kth Smallest :: 5
*/
fun main2() {
    val arr3 = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    println("isMaxHeap :: " + isMaxHeap(arr3, arr3.size))
    val arr4 = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
    println("isMinHeap :: " + isMinHeap(arr4, arr4.size))
}

/*
isMaxHeap :: true
isMinHeap :: true     
*/
fun kSmallestProduct(arr: IntArray, k: Int): Int {
    Arrays.sort(arr)
    var product = 1
    for (i in 0 until k) product *= arr[i]
    return product
}

fun swap(arr: IntArray, i: Int, j: Int) {
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}

fun quickSelectUtil(arr: IntArray, low: Int, up: Int, k: Int) {
    var lower = low
    var upper = up
    if (upper <= lower) return
    val pivot = arr[lower]
    val start = lower
    val stop = upper
    while (lower < upper) {
        while (lower < upper && arr[lower] <= pivot) {
            lower++
        }
        while (lower <= upper && arr[upper] > pivot) {
            upper--
        }
        if (lower < upper) {
            swap(arr, upper, lower)
        }
    }
    swap(arr, upper, start) // upper is the pivot position
    if (k < upper) quickSelectUtil(arr, start, upper - 1, k) // pivot -1 is the upper for left sub array.
    if (k > upper) quickSelectUtil(arr, upper + 1, stop, k) // pivot + 1 is the lower for right sub array.
}

fun kSmallestProduct3(arr: IntArray, size: Int, k: Int): Int {
    quickSelectUtil(arr, 0, size - 1, k)
    var product = 1
    for (i in 0 until k) product *= arr[i]
    return product
}

fun kSmallestProduct2(arr: IntArray, size: Int, k: Int): Int {
    val pq: PriorityQueue<Int> = PriorityQueue<Int>()
    var i = 0
    var product = 1
    while (i < size) {
        pq.add(arr[i])
        i++
    }
    i = 0
    while (i < size && i < k) {
        product *= pq.remove()
        i += 1
    }
    return product
}

fun kSmallestProduct4(arr: IntArray, size: Int, k: Int): Int {
    val pq: PriorityQueue<Int> = PriorityQueue<Int>(Collections.reverseOrder<Int>())
    for (i in 0 until size) {
        if (i < k) pq.add(arr[i]) else {
            if (pq.peek() > arr[i]) {
                pq.add(arr[i])
                pq.remove()
            }
        }
    }
    var product = 1
    for (i in 0 until k) {
        product *= pq.remove()
    }
    return product
}

fun main3() {
    val arr = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    println("Kth Smallest product:: " + kSmallestProduct(arr, 3))
    val arr2 = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    println("Kth Smallest product:: " + kSmallestProduct2(arr2, 8, 3))
    val arr3 = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    println("Kth Smallest product:: " + kSmallestProduct3(arr3, 8, 3))
    val arr4 = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    println("Kth Smallest product:: " + kSmallestProduct4(arr4, 8, 3))
}

/*
* Kth Smallest product:: 10 
* Kth Smallest product:: 10 
* Kth Smallest product:: 10
* Kth Smallest product:: 10
*/
fun printLargerHalf(arr: IntArray, size: Int) {
    Arrays.sort(arr)
    for (i in size / 2 until size) print(arr[i].toString() + " ")
    println()
}

fun printLargerHalf2(arr: IntArray, size: Int) {
    val pq: PriorityQueue<Int> = PriorityQueue<Int>()
    for (i in 0 until size) {
        pq.add(arr[i])
    }
    for (i in 0 until size / 2) pq.remove()
    println(pq)
}

fun printLargerHalf3(arr: IntArray, size: Int) {
    quickSelectUtil(arr, 0, size - 1, size / 2)
    for (i in size / 2 until size) print(arr[i].toString() + " ")
    println()
}

fun main4() {
    val arr = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    printLargerHalf(arr, 8)
    val arr2 = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    printLargerHalf2(arr2, 8)
    val arr3 = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    printLargerHalf3(arr3, 8)
}

/*
* 6 7 7 8 
* [6, 7, 7, 8] 
* 6 7 7 8
*/
fun sortK(arr: IntArray, size: Int, k: Int) {
    val pq: PriorityQueue<Int> = PriorityQueue<Int>()
    var i = 0
    while (i < k) {
        pq.add(arr[i])
        i++
    }
    val output = IntArray(size)
    var index = 0
    i = k
    while (i < size) {
        output[index++] = pq.remove()
        pq.add(arr[i])
        i++
    }
    while (pq.size > 0) output[index++] = pq.remove()
    i = 0
    while (i < size) {
        arr[i] = output[i]
        i++
    }
}

// Testing Code
fun main5() {
    val k = 3
    val arr = intArrayOf(1, 5, 4, 10, 50, 9)
    val size = arr.size
    sortK(arr, size, k)
    for (i in 0 until size) print(arr[i].toString() + " ")
}

/*
1 4 5 9 10 50 
*/
// Testing code
fun main() {
    main1()
    main2()
    main3()
    main4()
    main5()
}