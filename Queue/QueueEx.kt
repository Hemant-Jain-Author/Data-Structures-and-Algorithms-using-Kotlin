import java.util.ArrayDeque
import java.util.Stack

fun CircularTour(arr: Array<IntArray>, n: Int): Int {
    for (i in 0 until n) {
        var total = 0
        var found = true
        for (j in 0 until n) {
            total += arr[(i + j) % n][0] - arr[(i + j) % n][1]
            if (total < 0) {
                found = false
                break
            }
        }
        if (found) return i
    }
    return -1
}

fun CircularTour2(arr: Array<IntArray>, n: Int): Int {
    val que: java.util.ArrayDeque<Int> = java.util.ArrayDeque<Int>()
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
    return if (petrol >= 0) que.remove() else -1
}

// Testing code
fun main1() {
    val tour = arrayOf(intArrayOf(8, 6), intArrayOf(1, 4), intArrayOf(7, 6))
    println("Circular Tour : " + CircularTour(tour, 3))
    println("Circular Tour : " + CircularTour2(tour, 3))
}

/*
Circular Tour : 2
Circular Tour : 2
*/

fun convertXY(src: Int, dst: Int): Int {
    val que: ArrayDeque<Int> = ArrayDeque<Int>()
    val arr = IntArray(100)
    var steps = 0
    var index = 0
    var value: Int
    que.add(src)
    while (que.size != 0) {
        value = que.remove()
        arr[index++] = value
        if (value == dst) {
            return steps
        }
        steps++
        if (value < dst) que.add(value * 2) else que.add(value - 1)
    }
    return -1
}

// Testing code
fun main2() {
    println("Steps count :: " + convertXY(2, 7))
}

/*
Steps count :: 3
*/
fun maxSlidingWindows(arr: IntArray, size: Int, k: Int) {
    for (i in 0 until size - k + 1) {
        var max = arr[i]
        for (j in 1 until k) {
            max = java.lang.Math.max(max, arr[i + j])
        }
        print("$max ")
    }
    println()
}

fun maxSlidingWindows2(arr: IntArray, size: Int, k: Int) {
    val que: ArrayDeque<Int> = ArrayDeque<Int>()
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
            print(arr[que.peek()].toString() + " ")
    }
    println()
}

// Testing code
fun main3() {
    val arr = intArrayOf(11, 2, 75, 92, 59, 90, 55)
    maxSlidingWindows(arr, 7, 3)
    maxSlidingWindows2(arr, 7, 3)
}

/*
75 92 92 92 90 
75 92 92 92 90 	
*/

fun minOfMaxSlidingWindows(arr: IntArray, size: Int, k: Int): Int {
    val que: ArrayDeque<Int> = ArrayDeque<Int>()
    var minVal = 999999
    for (i in 0 until size) {
        // Remove out of range elements
        if (que.size > 0 && que.peek() <= i - k) 
            que.remove()
        // Remove smaller values at left.
        while (que.size > 0 && arr[que.peekLast()] <= arr[i]) 
            que.removeLast()
        que.add(i)
        // window of size k
        if (i >= k - 1 && minVal > arr[que.peek()]) 
            minVal = arr[que.peek()]
    }
    println("Min of max is :: $minVal")
    return minVal
}

// Testing code
fun main4() {
    val arr = intArrayOf(11, 2, 75, 92, 59, 90, 55)
    minOfMaxSlidingWindows(arr, 7, 3)
}

/*
Min of max is :: 75
*/

fun maxOfMinSlidingWindows(arr: IntArray, size: Int, k: Int) {
    val que: ArrayDeque<Int> = ArrayDeque<Int>()
    var maxVal = -999999
    for (i in 0 until size) {
        // Remove out of range elements
        if (que.size > 0 && que.peek() <= i - k) 
            que.remove()
        // Remove smaller values at left.
        while (que.size > 0 && arr[que.peekLast()] >= arr[i]) 
            que.removeLast()
        que.add(i)
        // window of size k
        if (i >= k - 1 && maxVal < arr[que.peek()]) 
            maxVal = arr[que.peek()]
    }
    println("Max of min is :: $maxVal")
}

// Testing code
fun main5() {
    val arr = intArrayOf(11, 2, 75, 92, 59, 90, 55)
    maxOfMinSlidingWindows(arr, 7, 3)
}

/*
Max of min is :: 59
*/

fun firstNegSlidingWindows(arr: IntArray, size: Int, k: Int) {
    val que: ArrayDeque<Int> = ArrayDeque<Int>()
    for (i in 0 until size) {
        // Remove out of range elements
        if (que.size > 0 && que.peek() <= i - k) 
            que.remove()
        if (arr[i] < 0) 
            que.add(i)
        // window of size k
        if (i >= k - 1) {
            if (que.size > 0) 
                print(arr[que.peek()].toString() + " ") 
            else 
                print("NAN")
        }
    }
}

// Testing code
fun main6() {
    val arr = intArrayOf(3, -2, -6, 10, -14, 50, 14, 21)
    firstNegSlidingWindows(arr, 8, 3)
}

/*
-2 -2 -6 -14 -14 NAN
*/

fun rottenFruitUtil(arr: Array<IntArray>, maxCol: Int, maxRow: Int, currCol: Int, currRow: Int, 
                    traversed: Array<IntArray>, day: Int) {
    val dir = arrayOf(intArrayOf(-1, 0), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(0, 1))
    var x: Int
    var y: Int
    for (i in 0..3) {
        x = currCol + dir[i][0]
        y = currRow + dir[i][1]
        if (x >= 0 && x < maxCol && y >= 0 && y < maxRow && traversed[x][y] > day + 1 && arr[x][y] == 1) {
            traversed[x][y] = day + 1
            rottenFruitUtil(arr, maxCol, maxRow, x, y, traversed, day + 1)
        }
    }
}

fun rottenFruit(arr: Array<IntArray>, maxCol: Int, maxRow: Int): Int {
    val traversed = Array(maxCol) { IntArray(maxRow) }
    for (i in 0 until maxCol) {
        for (j in 0 until maxRow) {
            traversed[i][j] = Int.MAX_VALUE
        }
    }
    for (i in 0 until maxCol) {
        for (j in 0 until maxRow) {
            if (arr[i][j] == 2) {
                traversed[i][j] = 0
                rottenFruitUtil(arr, maxCol, maxRow, i, j, traversed, 0)
            }
        }
    }
    var maxDay = 0
    for (i in 0 until maxCol) {
        for (j in 0 until maxRow) {
            if (arr[i][j] == 1) {
                if (traversed[i][j] == Int.MAX_VALUE) return -1
                if (maxDay < traversed[i][j]) maxDay = traversed[i][j]
            }
        }
    }
    return maxDay
}

class Fruit internal constructor(var x: Int, var y: Int, var day: Int)

fun rottenFruit2(arr: Array<IntArray>, maxCol: Int, maxRow: Int): Int {
    val traversed = Array(maxCol) {
        BooleanArray(maxRow)
    }
    val dir = arrayOf(intArrayOf(-1, 0), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(0, 1))
    val que: ArrayDeque<Fruit> = ArrayDeque<Fruit>()
    for (i in 0 until maxCol) {
        for (j in 0 until maxRow) {
            traversed[i][j] = false
            if (arr[i][j] == 2) {
                que.add(Fruit(i, j, 0))
                traversed[i][j] = true
            }
        }
    }
    var max = 0
    var x: Int
    var y: Int
    var day: Int
    var temp: Fruit
    while (!que.isEmpty()) {
        temp = que.peek()
        que.pop()
        for (i in 0..3) {
            x = temp.x + dir[i][0]
            y = temp.y + dir[i][1]
            day = temp.day + 1
            if (x >= 0 && x < maxCol && y >= 0 && y < maxRow && arr[x][y] != 0 && traversed[x][y] == false) {
                que.add(Fruit(x, y, day))
                max = java.lang.Math.max(max, day)
                traversed[x][y] = true
            }
        }
    }
    for (i in 0 until maxCol) {
        for (j in 0 until maxRow) {
            if (arr[i][j] == 1 && traversed[i][j] == false) {
                return -1
            }
        }
    }
    return max
}

// Testing code
fun main7() {
    val arr = arrayOf(
        intArrayOf(1, 0, 1, 1, 0),
        intArrayOf(2, 1, 0, 1, 0),
        intArrayOf(0, 0, 0, 2, 1),
        intArrayOf(0, 2, 0, 0, 1),
        intArrayOf(1, 1, 0, 0, 1)
    )
    println("rottenFruit : " + rottenFruit(arr, 5, 5))
    println("rottenFruit : " + rottenFruit2(arr, 5, 5))
}

// 3

fun stepsOfKnightUtil(size: Int, currCol: Int, currRow: Int, traversed: Array<IntArray>, dist: Int) {
    val dir = arrayOf(
        intArrayOf(-2, -1),
        intArrayOf(-2, 1),
        intArrayOf(2, -1),
        intArrayOf(2, 1),
        intArrayOf(-1, -2),
        intArrayOf(1, -2),
        intArrayOf(-1, 2),
        intArrayOf(1, 2)
    )
    var x: Int
    var y: Int
    for (i in 0..7) {
        x = currCol + dir[i][0]
        y = currRow + dir[i][1]
        if (x >= 0 && x < size && y >= 0 && y < size && traversed[x][y] > dist + 1) {
            traversed[x][y] = dist + 1
            stepsOfKnightUtil(size, x, y, traversed, dist + 1)
        }
    }
}

fun stepsOfKnight(size: Int, srcX: Int, srcY: Int, dstX: Int, dstY: Int): Int {
    val traversed = Array(size) { IntArray(size) }
    for (i in 0 until size) {
        for (j in 0 until size) {
            traversed[i][j] = Int.MAX_VALUE
        }
    }
    traversed[srcX - 1][srcY - 1] = 0
    stepsOfKnightUtil(size, srcX - 1, srcY - 1, traversed, 0)
    return traversed[dstX - 1][dstY - 1]
}
    
class Knight internal constructor(var x: Int, var y: Int, var cost: Int)

fun stepsOfKnight2(size: Int, srcX: Int, srcY: Int, dstX: Int, dstY: Int): Int {
    val traversed = Array(size) { IntArray(size) }
    val dir = arrayOf(intArrayOf(-2, -1),
        intArrayOf(-2, 1), intArrayOf(2, -1),
        intArrayOf(2, 1), intArrayOf(-1, -2),
        intArrayOf(1, -2),intArrayOf(-1, 2),
        intArrayOf(1, 2))
    val que: ArrayDeque<Knight> = ArrayDeque<Knight>()
    for (i in 0 until size) {
        for (j in 0 until size) {
            traversed[i][j] = Int.MAX_VALUE
        }
    }
    que.add(Knight(srcX - 1, srcY - 1, 0))
    traversed[srcX - 1][srcY - 1] = 0
    var x: Int
    var y: Int
    var cost: Int
    var temp: Knight
    while (!que.isEmpty()) {
        temp = que.peek()
        que.pop()
        for (i in 0..7) {
            x = temp.x + dir[i][0]
            y = temp.y + dir[i][1]
            cost = temp.cost + 1
            if (x >= 0 && x < size && y >= 0 && y < size && traversed[x][y] > cost) {
                que.add(Knight(x, y, cost))
                traversed[x][y] = cost
            }
        }
    }
    return traversed[dstX - 1][dstY - 1]
}

// Testing code
fun main8() {
    println(stepsOfKnight(20, 10, 10, 20, 20))
    println(stepsOfKnight2(20, 10, 10, 20, 20))
}

/*
8
8
*/

fun distNearestFillUtil( arr: Array<IntArray>?, maxCol: Int, maxRow: Int, currCol: Int, currRow: Int,
    traversed: Array<IntArray>, dist: Int ) { // Range check
    var x: Int
    var y: Int
    val dir = arrayOf(intArrayOf(-1, 0), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(0, 1))
    for (i in 0..3) {
        x = currCol + dir[i][0]
        y = currRow + dir[i][1]
        if (x >= 0 && x < maxCol && y >= 0 && y < maxRow && traversed[x][y] > dist + 1) {
            traversed[x][y] = dist + 1
            distNearestFillUtil(arr, maxCol, maxRow, x, y, traversed, dist + 1)
        }
    }
}

fun distNearestFill(arr: Array<IntArray>, maxCol: Int, maxRow: Int) {
    val traversed = Array(maxCol) { IntArray(maxRow) }
    for (i in 0 until maxCol) {
        for (j in 0 until maxRow) {
            traversed[i][j] = Int.MAX_VALUE
        }
    }
    for (i in 0 until maxCol) {
        for (j in 0 until maxRow) {
            if (arr[i][j] == 1) {
                traversed[i][j] = 0
                distNearestFillUtil(arr, maxCol, maxRow, i, j, traversed, 0)
            }
        }
    }
    for (i in 0 until maxCol) {
        for (j in 0 until maxRow) {
            print(traversed[i][j].toString() + " ")
        }
        println()
    }
}

class Node internal constructor(var x: Int, var y: Int, var dist: Int)

fun distNearestFill2(arr: Array<IntArray>, maxCol: Int, maxRow: Int) {
    val traversed = Array(maxCol) { IntArray(maxRow) }
    val dir = arrayOf(intArrayOf(-1, 0), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(0, 1))
    val que: ArrayDeque<Node> = ArrayDeque<Node>()
    for (i in 0 until maxCol) {
        for (j in 0 until maxRow) {
            traversed[i][j] = Int.MAX_VALUE
            if (arr[i][j] == 1) {
                que.add(Node(i, j, 0))
                traversed[i][j] = 0
            }
        }
    }
    var x: Int
    var y: Int
    var dist: Int
    var temp: Node
    while (!que.isEmpty()) {
        temp = que.peek()
        que.pop()
        for (i in 0..3) {
            x = temp.x + dir[i][0]
            y = temp.y + dir[i][1]
            dist = temp.dist + 1
            if (x >= 0 && x < maxCol && y >= 0 && y < maxRow && traversed[x][y] > dist) {
                que.add(Node(x, y, dist))
                traversed[x][y] = dist
            }
        }
    }
    for (i in 0 until maxCol) {
        for (j in 0 until maxRow) {
            print(traversed[i][j].toString() + " ")
        }
        println()
    }
}

// Testing code
fun main9() {
    val arr = arrayOf(
        intArrayOf(1, 0, 1, 1, 0),
        intArrayOf(1, 1, 0, 1, 0),
        intArrayOf(0, 0, 0, 0, 1),
        intArrayOf(0, 0, 0, 0, 1),
        intArrayOf(0, 0, 0, 0, 1)
    )
    distNearestFill(arr, 5, 5)
    distNearestFill2(arr, 5, 5)
}

/*
0 1 0 0 1 
0 0 1 0 1 
1 1 2 1 0 
2 2 2 1 0 
3 3 2 1 0 
*/
fun findLargestIslandUtil(arr: Array<IntArray>, maxCol: Int, maxRow: Int, currCol: Int, currRow: Int,
                        traversed: Array<BooleanArray>): Int {
    val dir = arrayOf(
        intArrayOf(-1, -1),
        intArrayOf(-1, 0),
        intArrayOf(-1, 1),
        intArrayOf(0, -1),
        intArrayOf(0, 1),
        intArrayOf(1, -1),
        intArrayOf(1, 0),
        intArrayOf(1, 1)
    )
    var x: Int
    var y: Int
    var sum = 1
    for (i in 0..7) {
        x = currCol + dir[i][0]
        y = currRow + dir[i][1]
        if (x >= 0 && x < maxCol && y >= 0 && y < maxRow && traversed[x][y] == false && arr[x][y] == 1) {
            traversed[x][y] = true
            sum += findLargestIslandUtil(arr, maxCol, maxRow, x, y, traversed)
        }
    }
    return sum
}

fun findLargestIsland(arr: Array<IntArray>, maxCol: Int, maxRow: Int): Int {
    var maxVal = 0
    val traversed = Array(maxCol) {
        BooleanArray(
            maxRow
        )
    }
    for (i in 0 until maxCol) {
        for (j in 0 until maxRow) {
            traversed[i][j] = false
        }
    }
    for (i in 0 until maxCol) {
        for (j in 0 until maxRow) {
            if (arr[i][j] == 1) {
                traversed[i][j] = true
                var currVal = findLargestIslandUtil(arr, maxCol, maxRow, i, j, traversed)
                if (currVal > maxVal) 
                    maxVal = currVal
            }
        }
    }
    return maxVal
}

// Testing code
fun main10() {
    val arr = arrayOf(
        intArrayOf(1, 0, 1, 1, 0),
        intArrayOf(1, 0, 0, 1, 0),
        intArrayOf(0, 1, 1, 1, 1),
        intArrayOf(0, 1, 0, 0, 0),
        intArrayOf(1, 1, 0, 0, 1)
    )
    println("Largest Island : " + findLargestIsland(arr, 5, 5))
}

// Largest Island : 12

fun reverseStack(stk: Stack<Int>) {
    val que: ArrayDeque<Int> = ArrayDeque<Int>()
    while (!stk.isEmpty()) {
        que.add(stk.peek())
        stk.pop()
    }
    while (!que.isEmpty()) {
        stk.push(que.peek())
        que.remove()
    }
}

fun reverseQueue(que: ArrayDeque<Int>) {
    val stk: Stack<Int> = Stack<Int>()
    while (!que.isEmpty()) {
        stk.add(que.peek())
        que.remove()
    }
    while (!stk.isEmpty()) {
        que.add(stk.peek())
        stk.pop()
    }
}

// Testing code
fun main11() {
    val stk: Stack<Int> = Stack<Int>()
    for (i in 0..4) stk.push(i)
    println(stk)
    reverseStack(stk)
    println(stk)
    val que: ArrayDeque<Int> = ArrayDeque<Int>()
    for (i in 0..4) que.add(i)
    println(que)
    reverseQueue(que)
    println(que)
}

fun josephus(n: Int, k: Int): Int {
    val que: ArrayDeque<Int> = ArrayDeque<Int>()
    for (i in 0 until n) que.add(i + 1)
    while (que.size > 1) {
        for (i in 0 until k - 1) {
            que.add(que.peek())
            que.remove()
        }
        que.remove() // Kth person executed.
    }
    return que.peek()
}

// Testing code
fun main12() {
    println("Position : " + josephus(11, 5))
}

/*
Position : 8
*/

// Testing code
fun main() {
    main1()
    main2()
    main3()
    main4()
    main5()
    main6()
    main7()
    main8()
    main9()
    main10()
    main11()
    main12()
}