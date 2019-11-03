import java.util.ArrayDeque

fun CircularTour(arr: Array<IntArray>, n: Int): Int {
    val que = ArrayDeque<Int>()
    var nextPump = 0
    var prevPump: Int
    var count = 0
    var petrol = 0

    while (que.size != n) {
        while (petrol >= 0 && que.size != n) {
            que.add(nextPump)
            petrol += arr[nextPump][0] - arr[nextPump][1]
            nextPump = (nextPump + 1) % n
        }
        while (petrol < 0 && que.size > 0) {
            prevPump = que.remove()
            petrol -= arr[prevPump][0] - arr[prevPump][1]
        }
        count += 1
        if (count == n)
            return -1
    }
    return if (petrol >= 0)
        que.remove()
    else
        -1
}

fun main1() {
    // Testing code
    val tour = arrayOf(intArrayOf(8, 6), intArrayOf(1, 4), intArrayOf(7, 6))
    println("Circular Tour : " + CircularTour(tour, 3))
}

fun convertXY(src: Int, dst: Int): Int {
    val que = ArrayDeque<Int>()
    val arr = IntArray(100)
    var steps = 0
    var index = 0
    var value: Int

    que.add(src)
    while (que.size != 0) {
        value = que.remove()
        arr[index++] = value

        if (value == dst) {
            for (i in 0 until index)
                print(arr[i])
            println("\nSteps countr :: $steps")

            return steps
        }
        steps++
        if (value < dst)
            que.add(value * 2)
        else
            que.add(value - 1)
    }
    return -1
}

fun main2() {
    convertXY(2, 7)
}

fun maxSlidingWindows(arr: IntArray, size: Int, k: Int) {
    val que = ArrayDeque<Int>()
    for (i in 0 until size) {
        // Remove out of range elements
        if (que.size > 0 && que.peek() <= i - k)
            que.remove()
        // Remove smaller values at left.
        while (que.size > 0 && arr[que.peekLast()] <= arr[i])
            que.removeLast()

        que.add(i)
        // Largest value in window of size k is at index que[0]
        // It is displayed to the screen.
        if (i >= k - 1)
            print(" " + arr[que.peek()] )
    }
}

fun main3() {
    val arr = intArrayOf(11, 2, 75, 92, 59, 90, 55)
    val k = 3
    maxSlidingWindows(arr, 7, k)
    println()
    // Output 75, 92, 92, 92, 90
}

fun minOfMaxSlidingWindows(arr: IntArray, size: Int, k: Int): Int {
    val que = ArrayDeque<Int>()
    var minVal = 999999
    for (i in 0 until size) {
        // Remove out of range elements
        if (que.size > 0 && que.peek() <= i - k)
            que.remove()
        // Remove smaller values at left.
        while (que.size > 0 && arr[que.peekLast()] <= arr[i])
            que.remove()
        que.add(i)
        // window of size k
        if (i >= k - 1 && minVal > arr[que.peek()])
            minVal = arr[que.peek()]
    }
    println("Min of max is :: $minVal")
    return minVal
}

fun main4() {
    val arr = intArrayOf(11, 2, 75, 92, 59, 90, 55)
    val k = 3
    minOfMaxSlidingWindows(arr, 7, k)
    // Output 75
}

fun maxOfMinSlidingWindows(arr: IntArray, size: Int, k: Int) {
    val que = ArrayDeque<Int>()
    var maxVal = -999999
    for (i in 0 until size) {
        // Remove out of range elements
        if (que.size > 0 && que.peek() <= i - k)
            que.remove()
        // Remove smaller values at left.
        while (que.size > 0 && arr[que.peekLast()] >= arr[i])
            que.remove()
        que.add(i)
        // window of size k
        if (i >= k - 1 && maxVal < arr[que.peek()])
            maxVal = arr[que.peek()]
    }
    println("Max of min is :: $maxVal")
}

fun main5() {
    val arr = intArrayOf(11, 2, 75, 92, 59, 90, 55)
    val k = 3
    maxOfMinSlidingWindows(arr, 7, k)
    // Output 59, as minimum values in sliding windows are [2, 2, 59, 59, 55]
}

fun firstNegSlidingWindows(arr: IntArray, size: Int, k: Int) {
    val que = ArrayDeque<Int>()

    for (i in 0 until size) {
        // Remove out of range elements
        if (que.size > 0 && que.peek() <= i - k)
            que.remove()
        if (arr[i] < 0)
            que.add(i)
        // window of size k
        if (i >= k - 1) {
            if (que.size > 0)
                print(arr[que.peek()])
            else
                print("NAN")
        }
    }
}

fun main6() {
    val arr = intArrayOf(3, -2, -6, 10, -14, 50, 14, 21)
    val k = 3
    firstNegSlidingWindows(arr, 8, k)
    // Output [-2, -2, -6, -14, -14, NAN]
}

fun main(args: Array<String>) {
    main1()
    main2()
    main3()
    main4()
    main5()
    main6()
}
