import java.util.Arrays
import java.util.HashMap

fun linearSearchUnsorted(arr: IntArray, size: Int, value: Int): Boolean {
    for (i in 0 until size) {
        if (value == arr[i]) {
            return true
        }
    }
    return false
}

fun linearSearchSorted(arr: IntArray, size: Int, value: Int): Boolean {
    for (i in 0 until size) {
        if (value == arr[i]) {
            return true
        } else if (value < arr[i]) {
            return false
        }
    }
    return false
}

// Binary Search Algorithm - Iterative Way
fun binarySearch(arr: IntArray, size: Int, value: Int): Boolean {
    var low = 0
    var high = size - 1
    var mid: Int
    while (low <= high) {
        mid = (low + high) / 2
        if (arr[mid] == value) {
            return true
        } else if (arr[mid] < value) {
            low = mid + 1
        } else {
            high = mid - 1
        }
    }
    return false
}

// Binary Search Algorithm - Recursive Way
fun binarySearchRec(arr: IntArray, size: Int, value: Int): Boolean {
    val low = 0
    val high = size - 1
    return binarySearchRecUtil(arr, low, high, value)
}

fun binarySearchRecUtil(arr: IntArray, low: Int, high: Int, value: Int): Boolean {
    if (low > high) {
        return false
    }
    val mid = (low + high) / 2
    return if (arr[mid] == value) {
        true
    } else if (arr[mid] < value) {
        binarySearchRecUtil(arr, mid + 1, high, value)
    } else {
        binarySearchRecUtil(arr, low, mid - 1, value)
    }
}

fun binarySearch(arr: IntArray, start: Int, end: Int, key: Int, isInc: Boolean): Int {
    val mid: Int
    if (end < start) {
        return -1
    }
    mid = (start + end) / 2
    if (key == arr[mid]) {
        return mid
    }
    return if (isInc != false && key < arr[mid] || isInc == false && key > arr[mid]) {
        binarySearch(arr, start, mid - 1, key, isInc)
    } else {
        binarySearch(arr, mid + 1, end, key, isInc)
    }
}

fun fibonacciSearch(arr: IntArray, size: Int, value: Int): Boolean {
    /* Initialize fibonacci numbers */
    var fibNMn2 = 0
    var fibNMn1 = 1
    var fibN = fibNMn2 + fibNMn1
    while (fibN < size) {
        fibNMn2 = fibNMn1
        fibNMn1 = fibN
        fibN = fibNMn2 + fibNMn1
    }
    var low = 0
    while (fibN > 1) { // fibonacci series start with 0, 1, 1, 2
        val i: Int = java.lang.Math.min(low + fibNMn2, size - 1)
        if (arr[i] == value) return true else if (arr[i] < value) {
            fibN = fibNMn1
            fibNMn1 = fibNMn2
            fibNMn2 = fibN - fibNMn1
            low = i
        } else { // for feb2 <= 1, these will be invalid.
            fibN = fibNMn2
            fibNMn1 = fibNMn1 - fibNMn2
            fibNMn2 = fibN - fibNMn1
        }
    }
    return if (arr[low + fibNMn2] == value) true else false
}

fun main1() {
    val first = intArrayOf(1, 3, 5, 7, 9, 25, 30)
    println(linearSearchUnsorted(first, 7, 8))
    println(linearSearchSorted(first, 7, 8))
    println(binarySearch(first, 7, 8))
    println(binarySearchRec(first, 7, 8))
    println(linearSearchUnsorted(first, 7, 25))
    println(linearSearchSorted(first, 7, 25))
    println(binarySearch(first, 7, 25))
    println(binarySearchRec(first, 7, 25))
    println(fibonacciSearch(first, 7, 8))
    println(fibonacciSearch(first, 7, 25))
}

/*
* false 
* false 
* false 
* false
* 
* true 
* true 
* true 
* true
*/

fun swap(arr: IntArray, first: Int, second: Int) {
    val temp = arr[first]
    arr[first] = arr[second]
    arr[second] = temp
}

fun firstRepeated(arr: IntArray, size: Int): Int {
    for (i in 0 until size) {
        for (j in i + 1 until size) {
            if (arr[i] == arr[j]) {
                return arr[i]
            }
        }
    }
    return 0
}

fun firstRepeated2(arr: IntArray, size: Int): Int {
    val hm: HashMap<Int, Int> = HashMap<Int, Int>()
    for (i in 0 until size) {
        if (hm.containsKey(arr[i])) hm.put(arr[i], 2) else hm.put(arr[i], 1)
    }
    for (i in 0 until size) {
        if (hm.get(arr[i]) == 2) {
            return arr[i]
        }
    }
    return 0
}

fun main2() {
    val first = intArrayOf(1, 3, 5, 3, 9, 1, 30)
    println(firstRepeated(first, first.size))
    println(firstRepeated2(first, first.size))
}

/*
* 1
*/

fun printRepeating(arr: IntArray, size: Int) {
    print("Repeating elements are ")
    for (i in 0 until size) {
        for (j in i + 1 until size) {
            if (arr[i] == arr[j]) {
                print(" " + arr[i])
            }
        }
    }
    println()
}

fun printRepeating2(arr: IntArray, size: Int) {
    Arrays.sort(arr)
    print("Repeating elements are ")
    for (i in 1 until size) {
        if (arr[i] == arr[i - 1]) {
            print(" " + arr[i])
        }
    }
    println()
}

fun printRepeating3(arr: IntArray, size: Int) {
    val hs: HashSet<Int> = HashSet<Int>()
    print("Repeating elements are ")
    for (i in 0 until size) {
        if (hs.contains(arr[i])) {
            print(" " + arr[i])
        } else {
            hs.add(arr[i])
        }
    }
    println()
}

fun printRepeating4(arr: IntArray, size: Int, range: Int) {
    val count = IntArray(range)
    var i: Int
    i = 0
    while (i < size) {
        count[i] = 0
        i++
    }
    print("Repeating elements are ")
    i = 0
    while (i < size) {
        if (count[arr[i]] == 1) {
            print(" " + arr[i])
        } else {
            count[arr[i]]++
        }
        i++
    }
    println()
}

fun main3() {
    val first = intArrayOf(1, 3, 5, 3, 9, 1, 30)
    printRepeating(first, first.size)
    printRepeating2(first, first.size)
    printRepeating3(first, first.size)
    printRepeating4(first, first.size, 50)
}

/*
* Repeating elements are 1 3 
* Repeating elements are 1 3 
* Repeating elements are 1 3 
* Repeating elements are 1 3
*/

fun removeDuplicates(array: IntArray, size: Int): IntArray {
    var j = 0
    Arrays.sort(array)
    for (i in 1 until size) {
        if (array[i] != array[j]) {
            j++
            array[j] = array[i]
        }
    }
    return Arrays.copyOf(array, j + 1)
}

fun removeDuplicates2(arr: IntArray, size: Int): IntArray {
    val hm: HashMap<Int, Int> = HashMap<Int, Int>()
    var j = 0
    for (i in 0 until size) {
        if (!hm.containsKey(arr[i])) {
            arr[j] = arr[i]
            j++
            hm.put(arr[i], 1)
        }
    }
    return Arrays.copyOf(arr, j)
}

fun main4() {
    val first = intArrayOf(1, 3, 5, 3, 9, 1, 30)
    val ret = removeDuplicates(first, first.size)
    for (i in ret.indices) {
        print(ret[i].toString() + " ")
    }
    println()
    val first2 = intArrayOf(1, 3, 5, 3, 9, 1, 30)
    val ret2 = removeDuplicates2(first2, first2.size)
    for (i in ret2.indices) {
        print(ret2[i].toString() + " ")
    }
    println()
}

/*
* 1 3 5 9 30
*/

fun findMissingNumber(arr: IntArray, size: Int): Int {
    var i: Int
    var j: Int
    var found : Int
    i = 1
    while (i <= size) {
        found = 0
        j = 0
        while (j < size) {
            if (arr[j] == i) {
                found = 1
                break
            }
            j++
        }
        if (found == 0) {
            return i
        }
        i++
    }
    return Int.MAX_VALUE
}

fun findMissingNumber2(arr: IntArray, size: Int): Int {
    Arrays.sort(arr)
    for (i in 0 until size) {
        if (arr[i] != i + 1) return i + 1
    }
    return size
}

fun findMissingNumber3(arr: IntArray, size: Int): Int {
    val hm: HashMap<Int, Int> = HashMap<Int, Int>()
    for (i in 0 until size) hm.put(arr[i], 1)
    for (i in 1..size) if (!hm.containsKey(i)) return i
    return Int.MAX_VALUE
}

fun findMissingNumber4(arr: IntArray, size: Int): Int {
    val count = IntArray(size + 1)
    Arrays.fill(count, -1)
    for (i in 0 until size) count[arr[i] - 1] = 1
    for (i in 0..size) if (count[i] == -1) return i + 1
    return Int.MAX_VALUE
}

fun findMissingNumber5(arr: IntArray, size: Int): Int {
    var sum = 0
    // Element value range is from 1 to size+1.
    for (i in 1 until size + 2) sum += i
    for (i in 0 until size) sum -= arr[i]
    return sum
}

fun findMissingNumber6(arr: IntArray, size: Int): Int {
    for (i in 0 until size) {
        // len(arr)+1 value should be ignored.
        if (arr[i] != size + 1 && arr[i] != size * 3 + 1) {
            // 1 should not become (len(arr)+1) so multiplied by 2
            arr[(arr[i] - 1) % size] += size * 2
        }
    }
    for (i in 0 until size) if (arr[i] < size * 2) return i + 1
    return Int.MAX_VALUE
}

fun findMissingNumber7(arr: IntArray, size: Int): Int {
    var i: Int
    var xorSum = 0
    // Element value range is from 1 to size+1.
    i = 1
    while (i < size + 2) {
        xorSum = xorSum xor i
        i++
    }
    // loop through the array and get the XOR of elements
    i = 0
    while (i < size) {
        xorSum = xorSum xor arr[i]
        i++
    }
    return xorSum
}

fun findMissingNumber8(arr: IntArray, size: Int): Int {
    val st: HashSet<Int> = HashSet<Int>()
    var i = 0
    while (i < size) {
        st.add(arr[i])
        i += 1
    }
    i = 1
    while (i <= size) {
        if (st.contains(i) == false) return i
        i += 1
    }
    println("NoNumberMissing")
    return -1
}

fun main5() {
    val first = intArrayOf(1, 5, 4, 3, 2, 7, 8, 9)
    println(findMissingNumber(first, first.size))
    println(findMissingNumber2(first, first.size))
    println(findMissingNumber3(first, first.size))
    println(findMissingNumber4(first, first.size))
    println(findMissingNumber5(first, first.size))
    println(findMissingNumber7(first, first.size))
    println(findMissingNumber8(first, first.size))
    println(findMissingNumber6(first, first.size))
}

/*
* 6 6 6
*/

fun missingValues(arr: IntArray, size: Int) {
    var max = arr[0]
    var min = arr[0]
    for (i in 1 until size) {
        if (max < arr[i]) max = arr[i]
        if (min > arr[i]) min = arr[i]
    }
    var found: Boolean
    for (i in min + 1 until max) {
        found = false
        for (j in 0 until size) {
            if (arr[j] == i) {
                found = true
                break
            }
        }
        if (!found) print(i.toString() + " ")
    }
    println()
}

fun missingValues2(arr: IntArray, size: Int) {
    Arrays.sort(arr)
    var value = arr[0]
    var i = 0
    while (i < size) {
        if (value == arr[i]) {
            value += 1
            i += 1
        } else {
            print("$value ")
            value += 1
        }
    }
    println()
}

fun missingValues3(arr: IntArray, size: Int) {
    val ht: HashSet<Int> = HashSet<Int>()
    var minVal = 999999
    var maxVal = -999999
    for (i in 0 until size) {
        ht.add(arr[i])
        if (minVal > arr[i]) minVal = arr[i]
        if (maxVal < arr[i]) maxVal = arr[i]
    }
    for (i in minVal until maxVal + 1) {
        if (ht.contains(i) == false) {
            print(i.toString() + " ")
        }
    }
    println()
}

fun main6() {
    val arr = intArrayOf(11, 14, 13, 17, 21, 18, 19, 23, 24)
    val size = arr.size
    missingValues(arr, size)
    missingValues2(arr, size)
    missingValues3(arr, size)
}

/*
* 12 15 16 20 22 
* 12 15 16 20 22
*/

fun oddCount(arr: IntArray, size: Int) {
    var xorSum = 0
    for (i in 0 until size) {
        xorSum = xorSum xor arr[i]
    }
    println("Odd values: $xorSum")
}

fun oddCount2(arr: IntArray, size: Int) {
    val hm: HashMap<Int, Int> = HashMap<Int, Int>()
    for (i in 0 until size) {
        if (hm.containsKey(arr[i])) hm.remove(arr[i]) else hm.put(arr[i], 1)
    }
    print("Odd values: ")
    for (key in hm.keys) print("$key ")
    println()
    println("Odd count is :: " + hm.size)
}

fun oddCount3(arr: IntArray, size: Int) {
    var xorSum = 0
    var first = 0
    var second = 0
    val setBit: Int
    /*
    * xor of all elements in arr[] even occurrence will cancel each other. sum will
    * contain sum of two odd elements.
    */for (i in 0 until size) xorSum = xorSum xor arr[i]

    /* Rightmost set bit. */setBit = xorSum and (xorSum - 1).inv()

    /*
    * Dividing elements in two group: Elements having setBit bit as 1. Elements
    * having setBit bit as 0. Even elements cancelled themselves if group and we
    * get our numbers.
    */for (i in 0 until size) {
        if (arr[i] and setBit != 0) first = first xor arr[i] else second = second xor arr[i]
    }
    println("Odd values: $first $second")
}

fun main7() {
    val arr = intArrayOf(10, 25, 30, 10, 15, 25, 15)
    val size = arr.size
    oddCount(arr, size)
    oddCount2(arr, size)
    val arr2 = intArrayOf(10, 25, 30, 10, 15, 25, 15, 40)
    val size2 = arr2.size
    oddCount3(arr2, size2)
}

/*
Odd values: 30
Odd values: 30 
Odd count is :: 1
Odd values: 30 40
    */

fun sumDistinct(arr: IntArray, size: Int) {
    var sum = 0
    Arrays.sort(arr)
    for (i in 0 until size - 1) {
        if (arr[i] != arr[i + 1]) sum += arr[i]
    }
    sum += arr[size - 1]
    println("sum : $sum")
}

fun main8() {
    val arr = intArrayOf(1, 2, 3, 1, 1, 4, 5, 6)
    val size = arr.size
    sumDistinct(arr, size)
}

/*
* sum : 21
*/

fun minAbsSumPair(arr: IntArray, size: Int) {
    var l: Int
    var r: Int
    var minSum: Int
    var sum: Int
    var minFirst: Int
    var minSecond: Int
    // Array should have at least two elements
    if (size < 2) {
        println("Invalid Input")
        return
    }
    // Initialisation of values
    minFirst = 0
    minSecond = 1
    minSum = java.lang.Math.abs(arr[0] + arr[1])
    l = 0
    while (l < size - 1) {
        r = l + 1
        while (r < size) {
            sum = java.lang.Math.abs(arr[l] + arr[r])
            if (sum < minSum) {
                minSum = sum
                minFirst = l
                minSecond = r
            }
            r++
        }
        l++
    }
    println("Minimum sum elements are : " + arr[minFirst] + " , " + arr[minSecond])
}

fun minAbsSumPair2(arr: IntArray, size: Int) {
    var l: Int
    var r: Int
    var minSum: Int
    var sum: Int
    var minFirst: Int
    var minSecond: Int
    // Array should have at least two elements
    if (size < 2) {
        println("Invalid Input")
        return
    }
    Arrays.sort(arr)

    // Initialisation of values
    minFirst = 0
    minSecond = size - 1
    minSum = java.lang.Math.abs(arr[minFirst] + arr[minSecond])
    l = 0
    r = size - 1
    while (l < r) {
        sum = arr[l] + arr[r]
        if (java.lang.Math.abs(sum) < minSum) {
            minSum = java.lang.Math.abs(sum)
            minFirst = l
            minSecond = r
        }
        if (sum < 0) {
            l++
        } else if (sum > 0) {
            r--
        } else {
            break
        }
    }
    println("Minimum sum elements are : " + arr[minFirst] + " , " + arr[minSecond])
}

fun main9() {
    val first = intArrayOf(1, 5, -10, 3, 2, -6, 8, 9, 6)
    minAbsSumPair2(first, first.size)
    minAbsSumPair(first, first.size)
}

/*
* Minimum sum elements are : -6 , 6 
* Minimum sum elements are : -6 , 6
*/

fun findPair(arr: IntArray, size: Int, value: Int): Boolean {
    for (i in 0 until size) {
        for (j in i + 1 until size) {
            if (arr[i] + arr[j] == value) {
                println("The pair is : " + arr[i] + ", " + arr[j])
                return true
            }
        }
    }
    return false
}

fun findPair2(arr: IntArray, size: Int, value: Int): Boolean {
    var first = 0
    var second = size - 1
    var curr: Int
    Arrays.sort(arr)
    while (first < second) {
        curr = arr[first] + arr[second]
        if (curr == value) {
            println("The pair is " + arr[first] + ", " + arr[second])
            return true
        } else if (curr < value) {
            first++
        } else {
            second--
        }
    }
    return false
}

fun findPair3(arr: IntArray, size: Int, value: Int): Boolean {
    val hs: HashSet<Int> = HashSet<Int>()
    for (i in 0 until size) {
        if (hs.contains(value - arr[i])) {
            println("The pair is : " + arr[i] + ", " + (value - arr[i]))
            return true
        }
        hs.add(arr[i])
    }
    return false
}

fun findPair4(arr: IntArray, size: Int, range: Int, value: Int): Boolean {
    val count = IntArray(range + 1)
    Arrays.fill(count, 0)
    for (i in 0 until size) {
        if (count[value - arr[i]] > 0) {
            println("The pair is : " + arr[i] + ", " + (value - arr[i]))
            return true
        }
        count[arr[i]] += 1
    }
    return false
}

fun main10() {
    val first = intArrayOf(1, 5, 4, 3, 2, 7, 8, 9, 6)
    findPair(first, first.size, 8)
    findPair2(first, first.size, 8)
    findPair3(first, first.size, 8)
    findPair4(first, first.size, 9, 8)
}

/*
* The pair is : 1, 7 
* The pair is 1, 7 
* The pair is : 5, 3  
* The pair is : 5, 3
*/

fun findPairTwoLists(arr1: IntArray, size1: Int, arr2: IntArray, size2: Int, value: Int): Boolean {
    for (i in 0 until size1) {
        for (j in 0 until size2) {
            if (arr1[i] + arr2[j] == value) {
                println("The pair is : " + arr1[i] + ", " + arr2[j])
                return true
            }
        }
    }
    return false
}

fun findPairTwoLists2(arr1: IntArray, size1: Int, arr2: IntArray, size2: Int, value: Int): Boolean {
    Arrays.sort(arr2)
    for (i in 0 until size1) {
        if (binarySearch(arr2, size2, value - arr1[i])) {
            println("The pair is " + arr1[i] + ", " + (value - arr1[i]))
            return true
        }
    }
    return false
}

fun findPairTwoLists3(arr1: IntArray, size1: Int, arr2: IntArray, size2: Int, value: Int): Boolean {
    var first = 0
    var second = size2 - 1
    Arrays.sort(arr1)
    Arrays.sort(arr2)
    while (first < size1 && second >= 0) {
        var curr = arr1[first] + arr2[second]
        if (curr == value) {
            println("The pair is " + arr1[first] + ", " + arr2[second])
            return true
        } else if (curr < value) {
            first++
        } else {
            second--
        }
    }
    return false
}

fun findPairTwoLists4(arr1: IntArray, size1: Int, arr2: IntArray, size2: Int, value: Int): Boolean {
    val hs: HashSet<Int> = HashSet<Int>()
    for (i in 0 until size2) hs.add(arr2[i])
    for (i in 0 until size1) {
        if (hs.contains(value - arr1[i])) {
            println("The pair is : " + arr1[i] + ", " + (value - arr1[i]))
            return true
        }
    }
    return false
}

fun findPairTwoLists5(arr1: IntArray, size1: Int, arr2: IntArray, size2: Int, range: Int, value: Int): Boolean {
    val count = IntArray(range + 1)
    Arrays.fill(count, 0)
    for (i in 0 until size2) count[arr2[i]] = 1
    for (i in 0 until size1) {
        if (count[value - arr1[i]] != 0) {
            println("The pair is : " + arr1[i] + ", " + (value - arr1[i]))
            return true
        }
    }
    return false
}

fun main10A() {
    val first = intArrayOf(1, 5, 4, 3, 2, 7, 8, 9, 6)
    val second = intArrayOf(1, 5, 4, 3, 2, 7, 8, 9, 6)
    println(findPairTwoLists(first, first.size, second, second.size, 8))
    println(findPairTwoLists2(first, first.size, second, second.size, 8))
    println(findPairTwoLists3(first, first.size, second, second.size, 8))
    println(findPairTwoLists4(first, first.size, second, second.size, 8))
    println(findPairTwoLists5(first, first.size, second, second.size, 9, 8))
}

/*
* The pair is : 1, 7 true 
* The pair is 1, 7 true 
* The pair is 1, 7 true 
* The pair is : 1, 7 true 
* The pair is : 1, 7 true
*/

fun findDifference(arr: IntArray, size: Int, value: Int): Boolean {
    for (i in 0 until size) {
        for (j in i + 1 until size) {
            if (java.lang.Math.abs(arr[i] - arr[j]) == value) {
                println("The pair is:: " + arr[i] + " & " + arr[j])
                return true
            }
        }
    }
    return false
}

fun findDifference2(arr: IntArray, size: Int, value: Int): Boolean {
    var first = 0
    var second = 0
    var diff: Int
    Arrays.sort(arr)
    while (first < size && second < size) {
        diff = java.lang.Math.abs(arr[first] - arr[second])
        if (diff == value) {
            println("The pair is::" + arr[first] + " & " + arr[second])
            return true
        } else if (diff > value) first += 1 else second += 1
    }
    return false
}

fun main11() {
    val first = intArrayOf(1, 5, 4, 3, 2, 7, 8, 9, 6)
    println(findDifference(first, first.size, 6))
    println(findDifference2(first, first.size, 6))
}

/*
* The pair is:: 1 & 7 true 
* The pair is::1 & 7 true
*/

fun findMinDiff(arr: IntArray, size: Int): Int {
    var diff = Int.MAX_VALUE
    for (i in 0 until size) {
        for (j in i + 1 until size) {
            val value: Int = java.lang.Math.abs(arr[i] - arr[j])
            if (diff > value) diff = value
        }
    }
    return diff
}

fun findMinDiff2(arr: IntArray, size: Int): Int {
    Arrays.sort(arr)
    var diff = Int.MAX_VALUE
    for (i in 0 until size - 1) {
        if (arr[i + 1] - arr[i] < diff) diff = arr[i + 1] - arr[i]
    }
    return diff
}

fun main12() {
    val second = intArrayOf(1, 6, 4, 19, 17, 20)
    println("findMinDiff : " + findMinDiff(second, second.size))
    println("findMinDiff : " + findMinDiff2(second, second.size))
}

/*
* findMinDiff : 1
*/

fun minDiffPair(arr1: IntArray, size1: Int, arr2: IntArray, size2: Int): Int {
    var diff = Int.MAX_VALUE
    var first = 0
    var second = 0
    for (i in 0 until size1) {
        for (j in 0 until size2) {
            val value: Int = java.lang.Math.abs(arr1[i] - arr2[j])
            if (diff > value) {
                diff = value
                first = arr1[i]
                second = arr2[j]
            }
        }
    }
    println("The pair is :: $first & $second")
    println("Minimum difference is :: $diff")
    return diff
}

fun minDiffPair2(arr1: IntArray, size1: Int, arr2: IntArray, size2: Int): Int {
    var minDiff = Int.MAX_VALUE
    var i = 0
    var j = 0
    var first = 0
    var second = 0
    var diff: Int
    Arrays.sort(arr1)
    Arrays.sort(arr2)
    while (i < size1 && j < size2) {
        diff = java.lang.Math.abs(arr1[i] - arr2[j])
        if (minDiff > diff) {
            minDiff = diff
            first = arr1[i]
            second = arr2[j]
        }
        if (arr1[i] < arr2[j]) i += 1 else j += 1
    }
    println("The pair is :: $first & $second")
    println("Minimum difference is :: $minDiff")
    return minDiff
}

fun main13() {
    val first = intArrayOf(1, 5, 4, 3, 2, 7, 8, 9, 6)
    val second = intArrayOf(6, 4, 19, 17, 20)
    minDiffPair(first, first.size, second, second.size)
    minDiffPair(first, first.size, second, second.size)
}

/*
The pair is :: 4 & 4
Minimum difference is :: 0
*/

fun closestPair(arr: IntArray, size: Int, value: Int) {
    var diff = 999999
    var first = -1
    var second = -1
    var curr: Int
    for (i in 0 until size) {
        for (j in i + 1 until size) {
            curr = java.lang.Math.abs(value - (arr[i] + arr[j]))
            if (curr < diff) {
                diff = curr
                first = arr[i]
                second = arr[j]
            }
        }
    }
    println("closest pair is :: $first $second")
}

fun closestPair2(arr: IntArray, size: Int, value: Int) {
    var first = 0
    var second = 0
    var start = 0
    var stop = size - 1
    var diff: Int
    var curr: Int
    Arrays.sort(arr)
    diff = 9999999
    run {
        while (start < stop) {
            curr = value - (arr[start] + arr[stop])
            if (java.lang.Math.abs(curr) < diff) {
                diff = java.lang.Math.abs(curr)
                first = arr[start]
                second = arr[stop]
            }
            if (curr == 0) {
                break
            } else if (curr > 0) {
                start += 1
            } else {
                stop -= 1
            }
        }
    }
    println("closest pair is :: $first $second")
}

fun main14() {
    val first = intArrayOf(10, 20, 3, 4, 50, 80)
    closestPair(first, first.size, 47)
    closestPair2(first, first.size, 47)
}

/*
* closest pair is :: 3 50 
* closest pair is :: 3 50
*/

fun sumPairRestArray(arr: IntArray, size: Int): Boolean {
    var total: Int
    var low: Int
    var high: Int
    var curr: Int
    val value: Int
    Arrays.sort(arr)
    total = 0
    for (i in 0 until size) total += arr[i]
    value = total / 2
    low = 0
    high = size - 1
    while (low < high) {
        curr = arr[low] + arr[high]
        if (curr == value) {
            println("Pair is :: " + arr[low] + " " + arr[high])
            return true
        } else if (curr < value) low += 1 else high -= 1
    }
    return false
}

fun main15() {
    val first = intArrayOf(1, 2, 4, 8, 16, 15)
    println(sumPairRestArray(first, first.size))
}

/*
Pair is :: 8 15
true	 
*/

fun zeroSumTriplets(arr: IntArray, size: Int) {
    for (i in 0 until size - 2) {
        for (j in i + 1 until size - 1) {
            for (k in j + 1 until size) {
                if (arr[i] + arr[j] + arr[k] == 0) println("Triplet:: " + arr[i] + " " + arr[j] + " " + arr[k])
            }
        }
    }
}

fun zeroSumTriplets2(arr: IntArray, size: Int) {
    var start: Int
    var stop: Int
    Arrays.sort(arr)
    for (i in 0 until size - 2) {
        start = i + 1
        stop = size - 1
        while (start < stop) {
            if (arr[i] + arr[start] + arr[stop] == 0) {
                println("Triplet :: " + arr[i] + " " + arr[start] + " " + arr[stop])
                start += 1
                stop -= 1
            } else if (arr[i] + arr[start] + arr[stop] > 0) stop -= 1 else start += 1
        }
    }
}

fun main16() {
    val first = intArrayOf(0, -1, 2, -3, 1)
    zeroSumTriplets(first, first.size)
    zeroSumTriplets2(first, first.size)
}

/*
* Triplet:: 0 -1 1 
* Triplet:: 2 -3 1 
* Triplet :: -3 1 2 
* Triplet :: -1 0 1
*/

fun findTriplet(arr: IntArray, size: Int, value: Int) {
    for (i in 0 until size - 2) for (j in i + 1 until size - 1) for (k in j + 1 until size) {
        if (arr[i] + arr[j] + arr[k] == value) println("Triplet :: " + arr[i] + " " + arr[j] + " " + arr[k])
    }
}

fun findTriplet2(arr: IntArray, size: Int, value: Int) {
    var start: Int
    var stop: Int
    Arrays.sort(arr)
    for (i in 0 until size - 2) {
        start = i + 1
        stop = size - 1
        while (start < stop) {
            if (arr[i] + arr[start] + arr[stop] == value) {
                println("Triplet ::" + arr[i] + " " + arr[start] + " " + arr[stop])
                start += 1
                stop -= 1
            } else if (arr[i] + arr[start] + arr[stop] > value) stop -= 1 else start += 1
        }
    }
}

fun main17() {
    val first = intArrayOf(1, 5, 15, 6, 9, 8)
    findTriplet(first, first.size, 22)
    findTriplet2(first, first.size, 22)
}

/*
* Triplet :: 1 15 6 
* Triplet :: 5 9 8 
* Triplet ::1 6 15 
* Triplet ::5 8 9
*/

fun abcTriplet(arr: IntArray, size: Int) {
    for (i in 0 until size - 1) for (j in i + 1 until size) for (k in 0 until size) {
        if (k != i && k != j && arr[i] + arr[j] == arr[k]) println("abcTriplet:: " + arr[i] + " " + arr[j] + " " + arr[k])
    }
}

fun abcTriplet2(arr: IntArray, size: Int) {
    var start: Int
    var stop: Int
    Arrays.sort(arr)
    for (i in 0 until size) {
        start = 0
        stop = size - 1
        while (start < stop) {
            if (arr[i] == arr[start] + arr[stop]) {
                println("abcTriplet:: " + arr[start] + " " + arr[stop] + " " + arr[i])
                start += 1
                stop -= 1
            } else if (arr[i] < arr[start] + arr[stop]) stop -= 1 else start += 1
        }
    }
}

fun main18() {
    val first = intArrayOf(1, 5, 15, 6, 9, 8)
    abcTriplet(first, first.size)
    abcTriplet2(first, first.size)
}

/*
* abcTriplet:: 1 5 6 
* abcTriplet:: 1 8 9 
* abcTriplet:: 6 9 15
*/

fun smallerThenTripletCount(arr: IntArray, size: Int, value: Int) {
    var count = 0
    for (i in 0 until size - 1) for (j in i + 1 until size) for (k in j + 1 until size) if (arr[i] + arr[j] + arr[k] < value) count += 1
    println("smallerThenTripletCount:: $count")
}

fun smallerThenTripletCount2(arr: IntArray, size: Int, value: Int) {
    var start: Int
    var stop: Int
    var count = 0
    Arrays.sort(arr)
    for (i in 0 until size - 2) {
        start = i + 1
        stop = size - 1
        while (start < stop) {
            if (arr[i] + arr[start] + arr[stop] >= value) stop -= 1 else {
                count += stop - start
                start += 1
            }
        }
    }
    println("smallerThenTripletCount:: $count")
}

fun main19() {
    val first = intArrayOf(-2, -1, 0, 1)
    smallerThenTripletCount(first, first.size, 2)
    smallerThenTripletCount(first, first.size, 2)
}

/*
* 4 
* 4
*/

fun apTriplets(arr: IntArray, size: Int) {
    var i: Int
    var j: Int
    var k: Int
    i = 1
    while (i < size - 1) {
        j = i - 1
        k = i + 1
        while (j >= 0 && k < size) {
            if (arr[j] + arr[k] == 2 * arr[i]) {
                println("AP Triplet:: " + arr[j] + " " + arr[i] + " " + arr[k])
                k += 1
                j -= 1
            } else if (arr[j] + arr[k] < 2 * arr[i]) k += 1 else j -= 1
        }
        i++
    }
}

fun main20() {
    val arr = intArrayOf(2, 4, 10, 12, 14, 18, 36)
    apTriplets(arr, arr.size)
}

/*
* AP Triplet:: 2 10 18 
* AP Triplet:: 10 12 14 
* AP Triplet:: 10 14 18
*/

fun gpTriplets(arr: IntArray, size: Int) {
    var i: Int
    var j: Int
    var k: Int
    i = 1
    while (i < size - 1) {
        j = i - 1
        k = i + 1
        while (j >= 0 && k < size) {
            if (arr[j] * arr[k] == arr[i] * arr[i]) {
                println("GP Triplet:: " + arr[j] + " " + arr[i] + " " + arr[k])
                k += 1
                j -= 1
            } else if (arr[j] + arr[k] < 2 * arr[i]) k += 1 else j -= 1
        }
        i++
    }
}

fun main21() {
    val arr = intArrayOf(1, 2, 4, 8, 16)
    gpTriplets(arr, arr.size)
}

/*
* GP Triplet:: 1 2 4 
* GP Triplet:: 2 4 8 
* GP Triplet:: 1 4 16 
* GP Triplet:: 4 8 16
*/

fun numberOfTriangles(arr: IntArray, size: Int): Int {
    var i: Int
    var j: Int
    var k: Int
    var count = 0
    i = 0
    while (i < size - 2) {
        j = i + 1
        while (j < size - 1) {
            k = j + 1
            while (k < size) {
                if (arr[i] + arr[j] > arr[k]) count += 1
                k++
            }
            j++
        }
        i++
    }
    return count
}

fun numberOfTriangles2(arr: IntArray, size: Int): Int {
    var i: Int
    var j: Int
    var k: Int
    var count = 0
    Arrays.sort(arr)
    i = 0
    while (i < size - 2) {
        k = i + 2
        j = i + 1
        while (j < size - 1) {

            /*
            * if sum of arr[i] & arr[j] is greater arr[k] then sum of arr[i] & arr[j + 1]
            * is also greater than arr[k] this improvement make algo O(n2)
            */while (k < size && arr[i] + arr[j] > arr[k]) k += 1
            count += k - j - 1
            j++
        }
        i++
    }
    return count
}

fun main22() {
    val arr = intArrayOf(1, 2, 3, 4, 5)
    println(numberOfTriangles(arr, arr.size))
    println(numberOfTriangles2(arr, arr.size))
}

/*
* 3 
* 3
*/

fun getMax(arr: IntArray, size: Int): Int {
    var max = arr[0]
    var maxCount = 1
    for (i in 0 until size) {
        var count = 1
        for (j in i + 1 until size) {
            if (arr[i] == arr[j]) {
                count++
            }
        }
        if (count > maxCount) {
            max = arr[i]
            maxCount = count
        }
    }
    return max
}

fun getMax2(arr: IntArray, size: Int): Int {
    var max = arr[0]
    var maxCount = 1
    var curr = arr[0]
    var currCount = 1
    Arrays.sort(arr) // Sort(arr,size);
    for (i in 1 until size) {
        if (arr[i] == arr[i - 1]) {
            currCount++
        } else {
            currCount = 1
            curr = arr[i]
        }
        if (currCount > maxCount) {
            maxCount = currCount
            max = curr
        }
    }
    return max
}

fun getMax3(arr: IntArray, size: Int, range: Int): Int {
    var max = arr[0]
    var maxCount = 1
    val count = IntArray(range)
    for (i in 0 until size) {
        count[arr[i]]++
        if (count[arr[i]] > maxCount) {
            maxCount = count[arr[i]]
            max = arr[i]
        }
    }
    return max
}

fun main23() {
    val first = intArrayOf(1, 30, 5, 13, 9, 31, 5)
    println(getMax(first, first.size))
    println(getMax2(first, first.size))
    println(getMax3(first, first.size, 50))
}

/*
* 5 
* 5 
* 5
*/

fun getMajority(arr: IntArray, size: Int): Int {
    var max = 0
    var count = 0
    var maxCount = 0
    for (i in 0 until size) {
        for (j in i + 1 until size) {
            if (arr[i] == arr[j]) {
                count++
            }
        }
        if (count > maxCount) {
            max = arr[i]
            maxCount = count
        }
    }
    return if (maxCount > size / 2) {
        max
    } else {
        0
    }
}

fun getMajority2(arr: IntArray, size: Int): Int {
    val majIndex = size / 2
    val candidate: Int
    Arrays.sort(arr)
    candidate = arr[majIndex]
    var count = 0
    for (i in 0 until size) {
        if (arr[i] == candidate) {
            count++
        }
    }
    return if (count > size / 2) {
        arr[majIndex]
    } else {
        Int.MIN_VALUE
    }
}

fun getMajority3(arr: IntArray, size: Int): Int {
    var majIndex = 0
    var count = 1
    var i: Int
    val candidate: Int
    i = 1
    while (i < size) {
        if (arr[majIndex] == arr[i]) {
            count++
        } else {
            count--
        }
        if (count == 0) {
            majIndex = i
            count = 1
        }
        i++
    }
    candidate = arr[majIndex]
    count = 0
    i = 0
    while (i < size) {
        if (arr[i] == candidate) {
            count++
        }
        i++
    }
    return if (count > size / 2) {
        arr[majIndex]
    } else {
        0
    }
}

fun main24() {
    val first = intArrayOf(1, 5, 5, 13, 5, 31, 5)
    println(getMajority(first, first.size))
    println(getMajority2(first, first.size))
    println(getMajority3(first, first.size))
}

/*
* 5 
* 5 
* 5
*/

fun getMedian(arr: IntArray, size: Int): Int {
    Arrays.sort(arr)
    return arr[size / 2]
}

fun getMedian2(arr: IntArray, size: Int): Int {
    quickSelectUtil(arr, 0, size - 1, size / 2)
    return arr[size / 2]
}

fun main25() {
    val first = intArrayOf(1, 5, 6, 6, 6, 6, 6, 6, 7, 8, 10, 13, 20, 30)
    println(getMedian(first, first.size))
    println(getMedian(first, first.size))
}

/*
* 6
*/

fun searchBitonicArrayMax(arr: IntArray, size: Int): Int {
    for (i in 0 until size - 2) {
        if (arr[i] > arr[i + 1]) return arr[i]
    }
    println("error not a bitonic array")
    return 0
}

fun searchBitonicArrayMax2(arr: IntArray, size: Int): Int {
    var start = 0
    var end = size - 1
    var mid = (start + end) / 2
    var maximaFound = 0
    if (size < 3) {
        println("error")
        return 0
    }
    while (start <= end) {
        mid = (start + end) / 2
        if (arr[mid - 1] < arr[mid] && arr[mid + 1] < arr[mid]) // maxima
        {
            maximaFound = 1
            break
        } else if (arr[mid - 1] < arr[mid] && arr[mid] < arr[mid + 1]) // increasing
        {
            start = mid + 1
        } else if (arr[mid - 1] > arr[mid] && arr[mid] > arr[mid + 1]) // decreasing
        {
            end = mid - 1
        } else {
            break
        }
    }
    if (maximaFound == 0) {
        println("error not a bitonic array")
        return 0
    }
    return arr[mid]
}

fun searchBitonicArray(arr: IntArray, size: Int, key: Int): Int {
    val max = findMaxBitonicArray(arr, size)
    val k = binarySearch(arr, 0, max, key, true)
    return if (k != -1) {
        k
    } else {
        binarySearch(arr, max + 1, size - 1, key, false)
    }
}

fun findMaxBitonicArray(arr: IntArray, size: Int): Int {
    var start = 0
    var end = size - 1
    var mid: Int
    if (size < 3) {
        println("error")
        return -1
    }
    while (start <= end) {
        mid = (start + end) / 2
        if (arr[mid - 1] < arr[mid] && arr[mid + 1] < arr[mid]) // maxima
        {
            return mid
        } else if (arr[mid - 1] < arr[mid] && arr[mid] < arr[mid + 1]) // increasing
        {
            start = mid + 1
        } else if (arr[mid - 1] > arr[mid] && arr[mid] > arr[mid + 1]) // decreasing
        {
            end = mid - 1
        } else {
            break
        }
    }
    println("error")
    return -1
}

fun main26() {
    val first = intArrayOf(1, 5, 10, 13, 20, 30, 8, 7, 6)
    println(searchBitonicArrayMax(first, first.size))
    println(searchBitonicArrayMax2(first, first.size))
    println(searchBitonicArray(first, first.size, 7))
}

/*
30
30
7
*/

fun findKeyCount(arr: IntArray, size: Int, key: Int): Int {
    var count = 0
    for (i in 0 until size) {
        if (arr[i] == key) {
            count++
        }
    }
    return count
}

fun findFirstIndex(arr: IntArray, start: Int, end: Int, key: Int): Int {
    val mid: Int
    if (end < start) {
        return -1
    }
    mid = (start + end) / 2
    if (key == arr[mid] && (mid == start || arr[mid - 1] != key)) {
        return mid
    }
    return if (key <= arr[mid]) {
        findFirstIndex(arr, start, mid - 1, key)
    } else {
        findFirstIndex(arr, mid + 1, end, key)
    }
}

fun findLastIndex(arr: IntArray, start: Int, end: Int, key: Int): Int {
    if (end < start) {
        return -1
    }
    val mid = (start + end) / 2
    if (key == arr[mid] && (mid == end || arr[mid + 1] != key)) {
        return mid
    }
    return if (key < arr[mid]) {
        findLastIndex(arr, start, mid - 1, key)
    } else {
        findLastIndex(arr, mid + 1, end, key)
    }
}

fun findKeyCount2(arr: IntArray, size: Int, key: Int): Int {
    val firstIndex: Int
    val lastIndex: Int
    firstIndex = findFirstIndex(arr, 0, size - 1, key)
    lastIndex = findLastIndex(arr, 0, size - 1, key)
    return lastIndex - firstIndex + 1
}

fun main27() {
    val first = intArrayOf(1, 5, 10, 13, 20, 30, 8, 7, 6)
    println(findKeyCount(first, first.size, 6))
    println(findKeyCount2(first, first.size, 6))
}

/*
* 1 
* 1
*/

/* Using binary search method. */

fun firstIndex(arr: IntArray, size: Int, low: Int, high: Int, value: Int): Int {
    var mid = 0
    if (high >= low) mid = (low + high) / 2

    /*
    * Find first occurrence of value, either it should be the first element of the
    * array or the value before it is smaller than it.
    */return if ((mid == 0 || arr[mid - 1] < value) && arr[mid] == value) mid else if (arr[mid] < value) firstIndex(
        arr,
        size,
        mid + 1,
        high,
        value
    ) else firstIndex(arr, size, low, mid - 1, value)
}

fun isMajority2(arr: IntArray, size: Int): Boolean {
    val majority = arr[size / 2]
    val i = firstIndex(arr, size, 0, size - 1, majority)
    /*
    * we are using majority element form array so we will get some valid index
    * always.
    */
    return if (i + size / 2 <= size - 1 && arr[i + size / 2] == majority) true else false
}

fun isMajority(arr: IntArray, size: Int): Boolean {
    var count = 0
    val mid = arr[size / 2]
    for (i in 0 until size) if (arr[i] == mid) count += 1
    return if (count > size / 2) true else false
}

fun main28() {
    val arr = intArrayOf(3, 3, 3, 3, 4, 5, 10)
    println(isMajority(arr, arr.size))
    println(isMajority2(arr, arr.size))
}

/*
* true
*/

fun maxProfit(stocks: IntArray, size: Int): Int {
    var maxProfit = 0
    var buy = 0
    var sell = 0
    for (i in 0 until size - 1) {
        for (j in i + 1 until size) {
            if (maxProfit < stocks[j] - stocks[i]) {
                maxProfit = stocks[j] - stocks[i]
                buy = i
                sell = j
            }
        }
    }
    println("Purchase day is " + buy + " at price " + stocks[buy])
    println("Sell day is " + sell + " at price " + stocks[sell])
    return maxProfit
}

fun maxProfit2(stocks: IntArray, size: Int): Int {
    var buy = 0
    var sell = 0
    var curMin = 0
    var maxProfit = 0
    for (i in 0 until size) {
        if (stocks[i] < stocks[curMin]) {
            curMin = i
        }
        var currProfit = stocks[i] - stocks[curMin]
        if (currProfit > maxProfit) {
            buy = curMin
            sell = i
            maxProfit = currProfit
        }
    }
    println("Purchase day is " + buy + " at price " + stocks[buy])
    println("Sell day is " + sell + " at price " + stocks[sell])
    return maxProfit
}

fun main29() {
    val first = intArrayOf(10, 150, 6, 67, 61, 16, 86, 6, 67, 78, 150, 3, 28, 143)
    println(maxProfit(first, first.size))
    println(maxProfit2(first, first.size))
}

/*
* Purchase day is- 2 at price 6 
* Sell day is- 10 at price 150 
* 144
*/

fun findMedian(arrFirst: IntArray, sizeFirst: Int, arrSecond: IntArray, sizeSecond: Int): Int {
    val medianIndex = (sizeFirst + sizeSecond + (sizeFirst + sizeSecond) % 2) / 2 // ceiling
    // function.
    var i = 0
    var j = 0
    var count = 0
    while (count < medianIndex - 1) {
        if (i < sizeFirst - 1 && arrFirst[i] < arrSecond[j]) {
            i++
        } else {
            j++
        }
        count++
    }
    return if (arrFirst[i] < arrSecond[j]) {
        arrFirst[i]
    } else {
        arrSecond[j]
    }
}

fun main30() {
    val first = intArrayOf(1, 5, 6, 6, 6, 6, 6, 6, 7, 8, 10, 13, 20, 30)
    val second = intArrayOf(1, 5, 6, 6, 6, 6, 6, 6, 7, 8, 10, 13, 20, 30)
    println(findMedian(first, first.size, second, second.size))
}

/*
* 6
*/

fun search01(arr: IntArray, size: Int): Int {
    for (i in 0 until size) if (arr[i] == 1) return i
    return -1
}

fun binarySearch01(arr: IntArray, size: Int): Int {
    return if (size == 1 && arr[0] == 1) {
        0
    } else binarySearch01Util(arr, 0, size - 1)
}

fun binarySearch01Util(arr: IntArray, start: Int, end: Int): Int {
    if (end < start) {
        return -1
    }
    val mid = (start + end) / 2
    if (1 == arr[mid] && 0 == arr[mid - 1]) {
        return mid
    }
    return if (0 == arr[mid]) {
        binarySearch01Util(arr, mid + 1, end)
    } else {
        binarySearch01Util(arr, start, mid - 1)
    }
}

fun main31() {
    val first = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1)
    println(search01(first, first.size))
    println(binarySearch01(first, first.size))
}

/*
* 8
*/

fun rotationMax(arr: IntArray, size: Int): Int {
    for (i in 0 until size - 1) if (arr[i] > arr[i + 1]) return arr[i]
    return -1
}

fun rotationMaxUtil(arr: IntArray, start: Int, end: Int): Int {
    if (end <= start) {
        return arr[start]
    }
    val mid = (start + end) / 2
    if (arr[mid] > arr[mid + 1]) return arr[mid]
    return if (arr[start] <= arr[mid]) /* increasing part. */ rotationMaxUtil(
        arr,
        mid + 1,
        end
    ) else rotationMaxUtil(arr, start, mid - 1)
}

fun rotationMax2(arr: IntArray, size: Int): Int {
    return rotationMaxUtil(arr, 0, size - 1)
}

fun main32() {
    val first = intArrayOf(34, 56, 77, 1, 5, 6, 6, 8, 10, 20, 30, 34)
    println(rotationMax(first, first.size))
    println(rotationMax2(first, first.size))
}

/*
77
77
*/

fun findRotationMax(arr: IntArray, size: Int): Int {
    for (i in 0 until size - 1) if (arr[i] > arr[i + 1]) return i
    return -1
}

fun findRotationMaxUtil(arr: IntArray, start: Int, end: Int): Int {
    /* single element case. */
    if (end <= start) return start
    val mid = (start + end) / 2
    if (arr[mid] > arr[mid + 1]) return mid
    return if (arr[start] <= arr[mid]) /* increasing part. */ findRotationMaxUtil(
        arr,
        mid + 1,
        end
    ) else findRotationMaxUtil(arr, start, mid - 1)
}

fun findRotationMax2(arr: IntArray, size: Int): Int {
    return findRotationMaxUtil(arr, 0, size - 1)
}

fun main33() {
    val first = intArrayOf(34, 56, 77, 1, 5, 6, 6, 8, 10, 20, 30, 34)
    println(findRotationMax(first, first.size))
    println(findRotationMax2(first, first.size))
}

/*
* 2
*/

fun countRotation(arr: IntArray, size: Int): Int {
    val maxIndex = findRotationMaxUtil(arr, 0, size - 1)
    return (maxIndex + 1) % size
}

fun main34() {
    val first = intArrayOf(34, 56, 77, 1, 5, 6, 6, 8, 10, 20, 30, 34)
    println(countRotation(first, first.size))
}

/*
* 3
*/

fun searchRotateArray(arr: IntArray, size: Int, key: Int): Int {
    for (i in 0 until size - 1) if (arr[i] == key) return i
    return -1
}

fun binarySearchRotateArrayUtil(arr: IntArray, start: Int, end: Int, key: Int): Int {
    if (end < start) {
        return -1
    }
    val mid = (start + end) / 2
    if (key == arr[mid]) {
        return mid
    }
    return if (arr[mid] > arr[start]) {
        if (arr[start] <= key && key < arr[mid]) {
            binarySearchRotateArrayUtil(arr, start, mid - 1, key)
        } else {
            binarySearchRotateArrayUtil(arr, mid + 1, end, key)
        }
    } else {
        if (arr[mid] < key && key <= arr[end]) {
            binarySearchRotateArrayUtil(arr, mid + 1, end, key)
        } else {
            binarySearchRotateArrayUtil(arr, start, mid - 1, key)
        }
    }
}

fun binarySearchRotateArray(arr: IntArray, size: Int, key: Int): Int {
    return binarySearchRotateArrayUtil(arr, 0, size - 1, key)
}

fun main35() {
    val first = intArrayOf(34, 56, 77, 1, 5, 6, 6, 6, 6, 6, 6, 7, 8, 10, 13, 20, 30)
    println(searchRotateArray(first, first.size, 20))
    println(binarySearchRotateArray(first, first.size, 20))
    println(countRotation(first, first.size))
    println(first[findRotationMax(first, first.size)])
}

/*
* 15 
* 3 
* 7
* 7
*/

fun minAbsDiffAdjCircular(arr: IntArray, size: Int): Int {
    var diff = 9999999
    if (size < 2) return -1
    for (i in 0 until size) diff = java.lang.Math.min(diff, java.lang.Math.abs(arr[i] - arr[(i + 1) % size]))
    return diff
}

// Testing Code
fun main36() {
    val arr = intArrayOf(5, 29, 18, 51, 11)
    println(minAbsDiffAdjCircular(arr, arr.size))
}

/*
* 6
*/

fun swapch(arr: CharArray, first: Int, second: Int) {
    val temp = arr[first]
    arr[first] = arr[second]
    arr[second] = temp
}

fun transformArrayAB1(arr: CharArray, size: Int) {
    val N = size / 2
    var i: Int
    var j: Int
    i = 1
    while (i < N) {
        j = 0
        while (j < i) {
            swapch(arr, N - i + 2 * j, N - i + 2 * j + 1)
            j++
        }
        i++
    }
}

//Testing Code
fun main37() {
    val str: CharArray = "aaaabbbb".toCharArray()
    transformArrayAB1(str, str.size)
    println(str)
}

/*
* abababab
*/

fun checkPermutation(array1: CharArray, size1: Int, array2: CharArray, size2: Int): Boolean {
    if (size1 != size2) {
        return false
    }
    Arrays.sort(array1)
    Arrays.sort(array2)
    for (i in 0 until size1) {
        if (array1[i] != array2[i]) {
            return false
        }
    }
    return true
}

fun checkPermutation2(arr1: CharArray, size1: Int, arr2: CharArray, size2: Int): Boolean {
    if (size1 != size2) return false
    val hm: HashMap<Char, Int> = HashMap<Char, Int>()
    for (i in 0 until size1) {
        if (hm.containsKey(arr1[i])) {
            hm.put(arr1[i], hm.get(arr1[i])!! + 1)
        } else hm.put(arr1[i], 1)
    }
    for (i in 0 until size2) {
        if (hm.containsKey(arr2[i]) && hm.get(arr2[i]) != 0) {
            hm.put(arr2[i], hm.get(arr2[i])!! - 1)
        } else return false
    }
    return true
}

fun checkPermutation3(array1: CharArray, size1: Int, array2: CharArray, size2: Int): Boolean {
    if (size1 != size2) {
        return false
    }
    val count = IntArray(256)
    for (i in 0 until size1) {
        count[array1[i].code]++
        count[array2[i].code]--
    }
    for (i in 0 until size1) {
        if (count[i] != 0) {
            return false
        }
    }
    return true
}

//Testing Code
fun main38() {
    val str1: CharArray = "aaaabbbb".toCharArray()
    val str2: CharArray = "bbaaaabb".toCharArray()
    println(checkPermutation(str1, str1.size, str2, str2.size))
    println(checkPermutation2(str1, str1.size, str2, str2.size))
    println(checkPermutation3(str1, str1.size, str2, str2.size))
}

/*
* true
* true
* true
*/

fun findElementIn2DArray(arr: Array<IntArray>, r: Int, c: Int, value: Int): Boolean {
    var row = 0
    var column = c - 1
    while (row < r && column >= 0) {
        if (arr[row][column] == value) {
            return true
        } else if (arr[row][column] > value) {
            column--
        } else {
            row++
        }
    }
    return false
}

fun isAP(arr: IntArray, size: Int): Boolean {
    if (size <= 1) return true
    Arrays.sort(arr)
    val diff = arr[1] - arr[0]
    for (i in 2 until size) {
        if (arr[i] - arr[i - 1] != diff) return false
    }
    return true
}

fun isAP2(arr: IntArray, size: Int): Boolean {
    var first = 9999999
    var second = 9999999
    var value: Int
    val hs: HashSet<Int> = HashSet<Int>()
    for (i in 0 until size) {
        if (arr[i] < first) {
            second = first
            first = arr[i]
        } else if (arr[i] < second) second = arr[i]
    }
    val diff = second - first
    for (i in 0 until size) {
        if (hs.contains(arr[i])) return false
        hs.add(arr[i])
    }
    for (i in 0 until size) {
        value = first + i * diff
        if (!hs.contains(value)) return false
    }
    return true
}

fun isAP3(arr: IntArray, size: Int): Boolean {
    var first = 9999999
    var second = 9999999
    val count = IntArray(size)
    for (i in 0 until size) {
        if (arr[i] < first) {
            second = first
            first = arr[i]
        } else if (arr[i] < second) second = arr[i]
    }
    val diff = second - first
    for (i in 0 until size) {
        var index = (arr[i] - first) / diff
        if (index > size - 1 || count[index] != 0) return false
        count[index] = 1
    }
    for (i in 0 until size) {
        if (count[i] != 1) return false
    }
    return true
}

//Testing Code
fun main39() {
    val arr = intArrayOf(20, 25, 15, 5, 0, 10, 35, 30)
    println(isAP(arr, arr.size))
    println(isAP2(arr, arr.size))
    println(isAP3(arr, arr.size))
}

/*
* true 
* true 
* true
*/

fun findBalancedPoint(arr: IntArray, size: Int): Int {
    var first = 0
    var second = 0
    for (i in 1 until size) second += arr[i]
    for (i in 0 until size) {
        if (first == second) {
            return i
        }
        if (i < size - 1) {
            first += arr[i]
            second -= arr[i + 1]
        }
    }
    return -1
}

// Testing Code
fun main40() {
    val arr = intArrayOf(-7, 1, 5, 2, -4, 3, 0)
    println(findBalancedPoint(arr, arr.size))
}

/*
* 3
*/

fun findFloor(arr: IntArray, size: Int, value: Int): Int {
    var start = 0
    var stop = size - 1
    var mid: Int
    while (start <= stop) {
        mid = (start + stop) / 2
        /*
        * search value is equal to arr[mid] value.. search value is greater than mid
        * index value and less than mid+1 index value. value is greater than
        * arr[size-1] then floor is arr[size-1]
        */if (arr[mid] == value || arr[mid] < value && (mid == size - 1 || arr[mid + 1] > value)) return arr[mid] else if (arr[mid] < value) start =
            mid + 1 else stop = mid - 1
    }
    return -1
}

fun findCeil(arr: IntArray, size: Int, value: Int): Int {
    var start = 0
    var stop = size - 1
    var mid: Int
    while (start <= stop) {
        mid = (start + stop) / 2
        /*
        * search value is equal to arr[mid] value.. search value is less than mid index
        * value and greater than mid-1 index value. value is less than arr[0] then ceil
        * is arr[0]
        */if (arr[mid] == value || arr[mid] > value && (mid == 0 || arr[mid - 1] < value)) return arr[mid] else if (arr[mid] < value) start =
            mid + 1 else stop = mid - 1
    }
    return -1
}

//Testing Code
fun main41() {
    val arr = intArrayOf(2, 4, 8, 16)
    println("Floor :  " + findFloor(arr, arr.size, 5))
    println("Ceil :  " + findCeil(arr, arr.size, 5))
}

/*
Floor :  4
Ceil :  8
*/

fun closestNumber(arr: IntArray, size: Int, num: Int): Int {
    var start = 0
    var stop = size - 1
    var output = -1
    var minDist = Int.MAX_VALUE
    var mid: Int
    while (start <= stop) {
        mid = (start + stop) / 2
        if (minDist > java.lang.Math.abs(arr[mid] - num)) {
            minDist = java.lang.Math.abs(arr[mid] - num)
            output = arr[mid]
        }
        if (arr[mid] == num) break else if (arr[mid] > num) stop = mid - 1 else start = mid + 1
    }
    return output
}

//Testing Code
fun main42() {
    val arr = intArrayOf(2, 4, 8, 16)
    println(closestNumber(arr, arr.size, 9))
}

/*
* 8
*/

fun duplicateKDistance(arr: IntArray, size: Int, k: Int): Boolean {
    val hm: HashMap<Int, Int> = HashMap<Int, Int>()
    for (i in 0 until size) {
        if (hm.containsKey(arr[i]) && i - hm.get(arr[i])!! <= k) {
            println("Value:" + arr[i] + " Index: " + hm.get(arr[i]) + " & " + i)
            return true
        } else hm.put(arr[i], i)
    }
    return false
}

// Testing Code
fun main43() {
    val arr = intArrayOf(1, 2, 3, 1, 4, 5)
    duplicateKDistance(arr, arr.size, 3)
}

/*
* Value:1 Index: 0 & 3
*/

fun frequencyCounts(arr: IntArray, size: Int) {
    val hm: HashMap<Int, Int> = HashMap<Int, Int>()
    for (i in 0 until size) {
        if (hm.containsKey(arr[i])) {
            hm.put(arr[i], hm.get(arr[i])!! + 1)
        } else {
            hm.put(arr[i], 1)
        }
    }
    for (key in hm.keys) print("(" + key + " : " + hm.get(key) + ") ")
    println()
}

fun frequencyCounts2(arr: IntArray, size: Int) {
    Arrays.sort(arr)
    var count = 1
    for (i in 1 until size) {
        if (arr[i] == arr[i - 1]) count++ else {
            print("(" + arr[i - 1] + " : " + count + ") ")
            count = 1
        }
    }
    print("(" + arr[size - 1] + " : " + count + ") ")
    println()
}

fun frequencyCounts3(arr: IntArray, size: Int) {
    val aux = IntArray(size + 1)
    for (i in 0 until size) {
        aux[arr[i]] += 1
    }
    for (i in 0 until size + 1) {
        if (aux[i] > 0) {
            print("(" + i + " : " + aux[i] + ") ")
        }
    }
    println()
}

fun frequencyCounts4(arr: IntArray, size: Int) {
    var index: Int
    for (i in 0 until size) {
        while (arr[i] > 0) {
            index = arr[i] - 1
            if (arr[index] > 0) {
                arr[i] = arr[index]
                arr[index] = -1
            } else {
                arr[index] -= 1
                arr[i] = 0
            }
        }
    }
    for (i in 0 until size) if (arr[i] != 0) print("(" + (i + 1) + " : " + java.lang.Math.abs(arr[i]) + ") ")
    println()
}

//Testing Code
fun main44() {
    val arr = intArrayOf(1, 2, 2, 2, 1)
    frequencyCounts(arr, arr.size)
    frequencyCounts2(arr, arr.size)
    frequencyCounts3(arr, arr.size)
    frequencyCounts4(arr, arr.size)
}

/*
* (1 : 2) (2 : 3) 
* (1 : 2) (2 : 3) 
* (1 : 2) (2 : 3) 
* (1 : 2) (2 : 3)
*/

fun kLargestElements(arrIn: IntArray, size: Int, k: Int) {
    val arr = IntArray(size)
    for (i in 0 until size) arr[i] = arrIn[i]
    Arrays.sort(arr)
    for (i in 0 until size) {
        if (arrIn[i] >= arr[size - k]) {
            print(arrIn[i].toString() + " ")
        }
    }
    println()
}

fun quickSelectUtil(arr: IntArray, low: Int, up: Int, k: Int) {
    var lower = low
    var upper = up
    if (upper <= lower) return
    val pivot = arr[lower]
    val start = lower
    val stop = upper
    while (lower < upper) {
        while (arr[lower] <= pivot && lower < upper) {
            lower++
        }
        while (arr[upper] > pivot && lower <= upper) {
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

fun kLargestElements2(arrIn: IntArray, size: Int, k: Int) {
    val arr = IntArray(size)
    for (i in 0 until size) arr[i] = arrIn[i]
    quickSelectUtil(arr, 0, size - 1, size - k)
    for (i in 0 until size) {
        if (arrIn[i] >= arr[size - k]) {
            print(arrIn[i].toString() + " ")
        }
    }
    println()
}

//Testing Code
fun main45() {
    val arr = intArrayOf(10, 50, 30, 60, 15)
    kLargestElements(arr, arr.size, 2)
    kLargestElements2(arr, arr.size, 2)
}

/*
* 50 60 
* 50 60
*/

/* linear search method */

fun fixPoint(arr: IntArray, size: Int): Int {
    for (i in 0 until size) {
        if (arr[i] == i) return i
    } /* fix point not found so return invalid index */
    return -1
}

/* Binary search method */

fun fixPoint2(arr: IntArray, size: Int): Int {
    var low = 0
    var high = size - 1
    var mid: Int
    while (low <= high) {
        mid = (low + high) / 2
        if (arr[mid] == mid) return mid else if (arr[mid] < mid) low = mid + 1 else high = mid - 1
    }
    /* fix point not found so return invalid index */return -1
}

//Testing Code
fun main46() {
    val arr = intArrayOf(-10, -2, 0, 3, 11, 12, 35, 51, 200)
    println(fixPoint(arr, arr.size))
    println(fixPoint2(arr, arr.size))
}

/*
* 3 
* 3
*/

fun subArraySums(arr: IntArray, size: Int, value: Int) {
    var start = 0
    var end = 0
    var sum = 0
    while (start < size && end < size) {
        if (sum < value) {
            sum += arr[end]
            end += 1
        } else {
            sum -= arr[start]
            start += 1
        }
        if (sum == value) {
            print("(" + start + " to " + (end - 1) + ") ")
        }
    }
}

//Testing Code
fun main47() {
    val arr = intArrayOf(15, 5, 5, 20, 10, 5, 5, 20, 10, 10)
    subArraySums(arr, arr.size, 20)
    println()
}

/*
(0 to 1) (3 to 3) (4 to 6) (7 to 7) (8 to 9) 
*/

fun maxConSub(arr: IntArray, size: Int): Int {
    var currMax = 0
    var maximum = 0
    for (i in 0 until size) {
        currMax += arr[i]
        if (currMax < 0) currMax = 0
        if (maximum < currMax) maximum = currMax
    }
    println(maximum)
    return maximum
}

fun maxConSubArr(A: IntArray, sizeA: Int, B: IntArray, sizeB: Int): Int {
    var currMax = 0
    var maximum = 0
    val hs: HashSet<Int> = HashSet<Int>()
    for (i in 0 until sizeB) hs.add(B[i])
    for (i in 0 until sizeA) if (hs.contains(A[i])) currMax = 0 else {
        currMax = currMax + A[i]
        if (currMax < 0) currMax = 0
        if (maximum < currMax) maximum = currMax
    }
    println(maximum)
    return maximum
}

fun maxConSubArr2(A: IntArray, sizeA: Int, B: IntArray, sizeB: Int): Int {
    Arrays.sort(B)
    var currMax = 0
    var maximum = 0
    for (i in 0 until sizeA) {
        if (binarySearch(B, sizeB, A[i])) currMax = 0 else {
            currMax = currMax + A[i]
            if (currMax < 0) currMax = 0
            if (maximum < currMax) maximum = currMax
        }
    }
    println(maximum)
    return maximum
}

//Testing Code
fun main48() {
    val arr = intArrayOf(1, 2, -3, 4, 5, -10, 6, 7)
    maxConSub(arr, arr.size)
    val arr2 = intArrayOf(1, 2, 3, 4, 5, -10, 6, 7, 3)
    val arr3 = intArrayOf(1, 3)
    maxConSubArr(arr2, arr2.size, arr3, arr3.size)
    maxConSubArr2(arr2, arr2.size, arr3, arr3.size)
}

/*
* 13 
* 13 
* 13
*/

fun rainWater(arr: IntArray, size: Int): Int {
    val leftHigh = IntArray(size)
    val rightHigh = IntArray(size)
    var max = arr[0]
    leftHigh[0] = arr[0]
    for (i in 1 until size) {
        if (max < arr[i]) max = arr[i]
        leftHigh[i] = max
    }
    max = arr[size - 1]
    rightHigh[size - 1] = arr[size - 1]
    for (i in size - 2 downTo 0) {
        if (max < arr[i]) max = arr[i]
        rightHigh[i] = max
    }
    var water = 0
    for (i in 0 until size) water += java.lang.Math.min(leftHigh[i], rightHigh[i]) - arr[i]
    println("Water : $water")
    return water
}

fun rainWater2(arr: IntArray, size: Int): Int {
    var water = 0
    var leftMax = 0
    var rightMax = 0
    var left = 0
    var right = size - 1
    while (left <= right) {
        if (arr[left] < arr[right]) {
            if (arr[left] > leftMax) leftMax = arr[left] else water += leftMax - arr[left]
            left += 1
        } else {
            if (arr[right] > rightMax) rightMax = arr[right] else water += rightMax - arr[right]
            right -= 1
        }
    }
    println("Water : $water")
    return water
}

//Testing Code
fun main49() {
    val arr = intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
    rainWater(arr, arr.size)
    rainWater2(arr, arr.size)
}

/*
* Water : 6 
* Water : 6
*/

fun separateEvenAndOdd(arr: IntArray, size: Int) {
    var left = 0
    var right = size - 1
    while (left < right) {
        if (arr[left] % 2 == 0) {
            left++
        } else if (arr[right] % 2 == 1) {
            right--
        } else {
            swap(arr, left, right)
            left++
            right--
        }
    }
}

//Testing Code
fun main50() {
    val first = intArrayOf(1, 5, 6, 6, 6, 6, 6, 6, 7, 8, 10, 13, 20, 30)
    separateEvenAndOdd(first, first.size)
    for (`val` in first) {
        print("$`val` ")
    }
}

/*
* 30 20 6 6 6 6 6 6 10 8 7 13 5 1
*/

fun arrayIndexMaxDiff(arr: IntArray, size: Int): Int {
    var maxDiff = -1
    var j: Int
    for (i in 0 until size) {
        j = size - 1
        while (i < j) {
            if (arr[i] <= arr[j]) {
                maxDiff = java.lang.Math.max(maxDiff, j - i)
                break
            }
            j -= 1
        }
    }
    return maxDiff
}

fun arrayIndexMaxDiff2(arr: IntArray, size: Int): Int {
    val rightMax = IntArray(size)
    rightMax[size - 1] = arr[size - 1]
    for (i in size - 2 downTo 0) rightMax[i] = java.lang.Math.max(rightMax[i + 1], arr[i])
    var maxDiff = -1
    var i = 0
    var j = 1
    while (i < size && j < size) {
        if (arr[i] <= rightMax[j]) {
            if (i < j) maxDiff = java.lang.Math.max(maxDiff, j - i)
            j = j + 1
        } else {
            i = i + 1
        }
    }
    return maxDiff
}

/* Testing code */

fun main51() {
    val arr = intArrayOf(33, 9, 10, 3, 2, 60, 30, 33, 1)
    println("arrayIndexMaxDiff : " + arrayIndexMaxDiff(arr, arr.size))
    println("arrayIndexMaxDiff : " + arrayIndexMaxDiff2(arr, arr.size))
}

/*
arrayIndexMaxDiff : 7
arrayIndexMaxDiff : 7
*/

fun maxPathSum(arr1: IntArray, size1: Int, arr2: IntArray, size2: Int): Int {
    var i = 0
    var j = 0
    var result = 0
    var sum1 = 0
    var sum2 = 0
    while (i < size1 && j < size2) {
        if (arr1[i] < arr2[j]) {
            sum1 += arr1[i]
            i += 1
        } else if (arr1[i] > arr2[j]) {
            sum2 += arr2[j]
            j += 1
        } else {
            result += java.lang.Math.max(sum1, sum2)
            result = result + arr1[i]
            sum1 = 0
            sum2 = 0
            i += 1
            j += 1
        }
    }
    while (i < size1) {
        sum1 += arr1[i]
        i += 1
    }
    while (j < size2) {
        sum2 += arr2[j]
        j += 1
    }
    result += java.lang.Math.max(sum1, sum2)
    return result
}

/* Testing code */

fun main52() {
    val arr1 = intArrayOf(12, 13, 18, 20, 22, 26, 70)
    val arr2 = intArrayOf(11, 15, 18, 19, 20, 26, 30, 31)
    println("Max Path Sum:: " + maxPathSum(arr1, arr1.size, arr2, arr2.size))
}

/*
Max Path Sum:: 201
*/

fun maxSubArraySum(a: IntArray, size: Int): Int {
    var maxSoFar = 0
    var maxEndingHere = 0
    for (i in 0 until size) {
        maxEndingHere = maxEndingHere + a[i]
        if (maxEndingHere < 0) {
            maxEndingHere = 0
        }
        if (maxSoFar < maxEndingHere) {
            maxSoFar = maxEndingHere
        }
    }
    return maxSoFar
}

/* Testing code */

fun main53() {
    val arr = intArrayOf(1, -2, 3, 4, -4, 6, -4, 3, 2)
    println("Max sub array sum :" + maxSubArraySum(arr, 9))
}

/*
Max sub array sum :10
*/

// Testing code
fun main() {
    main1();
    main2();
    main3();
    main4();
    main5();
    main6();
    main7();
    main8();
    main9();
    main10();
    main10A();
    main11();
    main12();
    main13();
    main14();
    main15();
    main16();
    main17();
    main18();
    main19();
    main20(); 
    main21(); 
    main22(); 
    main23(); 
    main24(); 
    main25();
    main26();
    main27(); 
    main28(); 
    main29(); 
    main30(); 
    main31();
    main32();
    main33(); 
    main34(); 
    main35(); 
    main36(); 
    main37(); 
    main38(); 
    main39();
    main40();
    main41(); 
    main42();
    main43();
    main44();
    main45();
    main46();
    main47()
    main48()
    main49()
    main50()
    main51()
    main52()
    main53()
}