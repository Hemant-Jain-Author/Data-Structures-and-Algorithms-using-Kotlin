import java.util.PriorityQueue
import java.util.Arrays


@JvmStatic
fun demo(args: Array<String>) {

    val pq = PriorityQueue<Int>()
    // PriorityQueue<Integer> pq = new
    // PriorityQueue<Integer>(Collections.reverseOrder());
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

fun KthSmallest(arr: IntArray, size: Int, k: Int): Int {
    Arrays.sort(arr)
    return arr[k - 1]
}

fun KthSmallest2(arr: IntArray, size: Int, k: Int): Int {
    var i = 0
    var value = 0
    val pq = PriorityQueue<Int>()
    i = 0
    while (i < size) {
        pq.add(arr[i])
        i++
    }
    i = 0
    while (i < size && i < k) {
        value = pq.remove()
        i += 1
    }
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
        if (lchild < size && arr[parent] > arr[lchild] || rchild < size && arr[parent] > arr[rchild])
            return false
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
        if (lchild < size && arr[parent] < arr[lchild] || rchild < size && arr[parent] < arr[rchild])
            return false
    }
    return true
}

@JvmStatic
fun main2(args: Array<String>) {
    val arr = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    println("Kth Smallest :: " + KthSmallest(arr, arr.size, 3))
    val arr2 = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    println("Kth Smallest :: " + KthSmallest2(arr2, arr2.size, 3))
    val arr3 = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    println("isMaxHeap :: " + isMaxHeap(arr3, arr3.size))
    val arr4 = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    Arrays.sort(arr4)
    println("isMinHeap :: " + isMinHeap(arr4, arr4.size))
}

fun KSmallestProduct(arr: IntArray, size: Int, k: Int): Int {
    Arrays.sort(arr)// , size, 1);
    var product = 1
    for (i in 0 until k)
        product *= arr[i]
    return product
}

fun swap(arr: IntArray, i: Int, j: Int) {
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}

fun QuickSelectUtil(arr: IntArray, lower: Int, upper: Int, k: Int) {
    var lower = lower
    var upper = upper
    if (upper <= lower)
        return

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
    if (k < upper)
        QuickSelectUtil(arr, start, upper - 1, k) // pivot -1 is the upper for left sub array.
    if (k > upper)
        QuickSelectUtil(arr, upper + 1, stop, k) // pivot + 1 is the lower for right sub array.
}

fun KSmallestProduct3(arr: IntArray, size: Int, k: Int): Int {
    QuickSelectUtil(arr, 0, size - 1, k)
    var product = 1
    for (i in 0 until k)
        product *= arr[i]
    return product
}

fun KSmallestProduct2(arr: IntArray, size: Int, k: Int): Int {
    val pq = PriorityQueue<Int>()
    var i = 0
    var product = 1
    i = 0
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

@JvmStatic
fun main3(args: Array<String>) {
    val arr = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    println("Kth Smallest product:: " + KSmallestProduct(arr, 8, 3))
    val arr2 = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    println("Kth Smallest product:: " + KSmallestProduct2(arr2, 8, 3))
    val arr3 = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    println("Kth Smallest product:: " + KSmallestProduct3(arr3, 8, 3))
}

fun PrintLargerHalf(arr: IntArray, size: Int) {
    Arrays.sort(arr)// , size, 1);
    for (i in size / 2 until size)
        print(arr[i])
    println()
}

fun PrintLargerHalf2(arr: IntArray, size: Int) {
    val product = 1
    val pq = PriorityQueue<Int>()
    for (i in 0 until size) {
        pq.add(arr[i])
    }

    for (i in 0 until size / 2)
        pq.remove()
    println(pq)
}

fun PrintLargerHalf3(arr: IntArray, size: Int) {
    QuickSelectUtil(arr, 0, size - 1, size / 2)
    for (i in size / 2 until size)
        print(arr[i])
    println()
}

@JvmStatic
fun main4(args: Array<String>) {
    val arr = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    PrintLargerHalf(arr, 8)
    val arr2 = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    PrintLargerHalf2(arr2, 8)
    val arr3 = intArrayOf(8, 7, 6, 5, 7, 5, 2, 1)
    PrintLargerHalf3(arr3, 8)
}

fun sortK(arr: IntArray, size: Int, k: Int) {
    val pq = PriorityQueue<Int>()
    var i = 0
    i = 0
    while (i < size) {
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
    while (pq.size > 0)
        output[index++] = pq.remove()

    i = k
    while (i < size) {
        arr[i] = output[i]
        i++
    }
    println(output)
}

// Testing Code
@JvmStatic
fun main5(args: Array<String>) {
    val k = 3
    val arr = intArrayOf(1, 5, 4, 10, 50, 9)
    val size = arr.size
    sortK(arr, size, k)
}

fun ChotaBhim(cups: IntArray, size: Int): Int {
    var time = 60
    Arrays.sort(cups)
    var total = 0
    var index: Int
    var temp: Int
    while (time > 0) {
        total += cups[0]
        cups[0] = Math.ceil(cups[0] / 2.0).toInt()
        index = 0
        temp = cups[0]
        while (index < size - 1 && temp < cups[index + 1]) {
            cups[index] = cups[index + 1]
            index += 1
        }
        cups[index] = temp
        time -= 1
    }
    println("Total %d $total")
    return total
}

fun ChotaBhim2(cups: IntArray, size: Int): Int {
    var time = 60
    Arrays.sort(cups)
    var total = 0
    var i: Int
    var temp: Int
    while (time > 0) {
        total += cups[0]
        cups[0] = Math.ceil(cups[0] / 2.0).toInt()
        i = 0
        // Insert into proper location.
        while (i < size - 1) {
            if (cups[i] > cups[i + 1])
                break
            temp = cups[i]
            cups[i] = cups[i + 1]
            cups[i + 1] = temp
            i += 1
        }
        time -= 1
    }
    println("Total : $total")
    return total
}

fun ChotaBhim3(cups: IntArray, size: Int): Int {
    var time = 60
    val pq = PriorityQueue<Int>()
    var i = 0
    i = 0
    while (i < size) {
        pq.add(cups[i])
        i++
    }

    var total = 0
    var value: Int
    while (time > 0) {
        value = pq.remove()
        total += value
        value = Math.ceil(value / 2.0).toInt()
        pq.add(value)
        time -= 1
    }
    println("Total : $total")
    return total
}

fun JoinRopes(ropes: IntArray, size: Int): Int {
    Arrays.sort(ropes)
    println(ropes)
    var total = 0
    var value = 0
    val temp: Int
    var index: Int
    var length = size

    while (length >= 2) {
        value = ropes[length - 1] + ropes[length - 2]
        total += value
        index = length - 2

        while (index > 0 && ropes[index - 1] < value) {
            ropes[index] = ropes[index - 1]
            index -= 1
        }
        ropes[index] = value
        length--
    }
    println("Total : $total")
    return total
}

fun JoinRopes2(ropes: IntArray, size: Int): Int {
    val pq = PriorityQueue<Int>()
    var i = 0
    i = 0
    while (i < size) {
        pq.add(ropes[i])
        i++
    }

    var total = 0
    var value = 0
    while (pq.size > 1) {
        value = pq.remove()
        value += pq.remove()
        pq.add(value)
        total += value
    }
    println("Total : %d $total")
    return total
}

@JvmStatic
fun main6(args: Array<String>) {
    val cups = intArrayOf(2, 1, 7, 4, 2)
    ChotaBhim(cups, cups.size)
    val cups2 = intArrayOf(2, 1, 7, 4, 2)
    ChotaBhim2(cups2, cups.size)
    val cups3 = intArrayOf(2, 1, 7, 4, 2)
    ChotaBhim3(cups3, cups.size)

    val ropes = intArrayOf(2, 1, 7, 4, 2)
    JoinRopes(ropes, ropes.size)
    val rope2 = intArrayOf(2, 1, 7, 4, 2)
    JoinRopes2(rope2, rope2.size)
}

/*
* public static int kthAbsDiff(int[] arr, int size, int k) { Sort(arr, size,1);
* int diff[1000]; int index = 0; for (int i = 0; i < size - 1; i++) { for (int
* j = i + 1; j < size; j++) diff[index++] = abs(arr[i] - arr[j]); } Sort(diff,
* index); return diff[k - 1]; }
*
* int kthAbsDiff(int[] arr, int size, int k) { Sort(arr, size, 1); Heap hp; int
* value = 0;
*
* for (int i = k + 1; i < size - 1; i++) HeapAdd(&hp,(abs(arr[i] - arr[i + 1]),
* i, i + 1)); heapify(hp);
*
* for (int i = 0; i < k; i++) { tp = heapq.heappop(hp); value = tp[0]; src =
* tp[1]; dst = tp[2]; if (dst + 1 < size) heapq.heappush(hp, (abs(arr[src] -
* arr[dst + 1]), src, dst + 1)); } return value; }
*
* public static void main7(String[] args) { int arr[] = { 1, 2, 3, 4 };
* System.out.println("", kthAbsDiff(arr, 4, 5)); return 0; }
*/
fun kthLargestStream(k: Int): Int {
    val pq = PriorityQueue<Int>()
    var size = 0
    val data = 0
    while (true) {
        println("Enter data: ")
        // data = System.in.read();

        if (size < k - 1)
            pq.add(data)
        else {
            if (size == k - 1)
                pq.add(data)
            else if (pq.peek() < data) {
                pq.add(data)
                pq.remove()
            }
            println("Kth larges element is :: " + pq.peek())
        }
        size += 1
    }
}

@JvmStatic
fun main(args: Array<String>) {
    kthLargestStream(3)
}
/*
* public static int minJumps(int[] arr, int size) { int *jumps = (int
* *)malloc(sizeof(int) * size); //all jumps to maxint. int steps, j; jumps[0] =
* 0;
*
* for (int i = 0; i < size; i++) { steps = arr[i]; // error checks can be added
* hear. j = i + 1; while (j <= i + steps && j < size) { jumps[j] =
* min(jumps[j], jumps[i] + 1); j += 1; } System.out.println("%s", jumps); }
* return jumps[size - 1]; } public static void main2(String[] args) { int arr[]
* = {1, 4, 3, 7, 6, 1, 0, 3, 5, 1, 10}; System.out.println("%d", minJumps(arr,
* sizeof(arr) / sizeof(int))); return 0; }
*/
