import java.util.Arrays
import java.util.HashMap
import java.util.HashSet
import java.util.ArrayList


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
fun Binarysearch(arr: IntArray, size: Int, value: Int): Boolean {
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

fun BinarySearchRec(arr: IntArray, size: Int, value: Int): Boolean {
    val low = 0
    val high = size - 1
    return BinarySearchRecUtil(arr, low, high, value)
}

// Binary Search Algorithm - Recursive Way
fun BinarySearchRecUtil(arr: IntArray, low: Int, high: Int, value: Int): Boolean {
    if (low > high) {
        return false
    }
    val mid = (low + high) / 2
    return if (arr[mid] == value) {
        true
    } else if (arr[mid] < value) {
        BinarySearchRecUtil(arr, mid + 1, high, value)
    } else {
        BinarySearchRecUtil(arr, low, mid - 1, value)
    }
}

fun BinarySearch(arr: IntArray, start: Int, end: Int, key: Int, isInc: Boolean): Int {
    val mid: Int
    if (end < start) {
        return -1
    }
    mid = (start + end) / 2
    if (key == arr[mid]) {
        return mid
    }
    return if (isInc != false && key < arr[mid] || isInc == false && key > arr[mid]) {
        BinarySearch(arr, start, mid - 1, key, isInc)
    } else {
        BinarySearch(arr, mid + 1, end, key, isInc)
    }
}


fun main1() {
    val first = intArrayOf(1, 3, 5, 7, 9, 25, 30)
    println("Search : " + linearSearchUnsorted(first, 7, 8))
    println("Search : " + linearSearchSorted(first, 7, 8))
    println("Search : " + Binarysearch(first, 7, 8))
    println("Search : " + BinarySearchRec(first, 7, 8))
    println("Search : " + linearSearchUnsorted(first, 7, 25))
    println("Search : " + linearSearchSorted(first, 7, 25))
    println("Search : " + Binarysearch(first, 7, 25))
    println("Search : " + BinarySearchRec(first, 7, 25))

}

fun swap(arr: IntArray, first: Int, second: Int) {
    val temp = arr[first]
    arr[first] = arr[second]
    arr[second] = temp
}

fun FirstRepeated(arr: IntArray, size: Int): Int {
    for (i in 0 until size) {
        for (j in i + 1 until size) {
            if (arr[i] == arr[j]) {
                return arr[i]
            }
        }
    }
    return 0
}


fun main2() {
    val first = intArrayOf(34, 56, 77, 1, 5, 6, 6, 6, 6, 6, 6, 7, 8, 10, 34, 20, 30)
    println("FirstRepeated : " + FirstRepeated(first, first.size))
}

fun printRepeating(arr: IntArray, size: Int) {
    print(" \nRepeating elements are ")
    for (i in 0 until size) {
        for (j in i + 1 until size) {
            if (arr[i] == arr[j]) {
                print(" " + arr[i])
            }
        }
    }
}

fun printRepeating2(arr: IntArray, size: Int) {
    Arrays.sort(arr)
    print(" \nRepeating elements are ")

    for (i in 1 until size) {
        if (arr[i] == arr[i - 1]) {
            print(" " + arr[i])
        }
    }
}

fun printRepeating3(arr: IntArray, size: Int) {
    val hs = HashSet<Int>()
    print(" \nRepeating elements are ")
    for (i in 0 until size) {
        if (hs.contains(arr[i])) {
            print(" " + arr[i])
        } else {
            hs.add(arr[i])
        }
    }
}

fun printRepeating4(arr: IntArray, size: Int, range: Int) {
    val count = IntArray(range)
    var i: Int
    i = 0
    while (i < size) {
        count[i] = 0
        i++
    }
    print(" \nRepeating elements are ")
    i = 0
    while (i < size) {
        if (count[arr[i]] == 1) {
            print(" " + arr[i])
        } else {
            count[arr[i]]++
        }
        i++
    }
}


fun main3() {
    val first = intArrayOf(1, 3, 5, 3, 9, 1, 30)
    printRepeating(first, first.size)
    printRepeating2(first, first.size)
    printRepeating3(first, first.size)
    printRepeating4(first, first.size, 50)
}

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


fun main4() {
    val first = intArrayOf(1, 3, 5, 3, 9, 1, 30)
    val ret = removeDuplicates(first, first.size)
    for (i in ret.indices) {
        print(" " + ret[i])
    }
}

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
    return Integer.MAX_VALUE
}

fun findMissingNumber2(arr: IntArray, size: Int, range: Int): Int {
    var i: Int
    var xorSum = 0
    // get the XOR of all the numbers from 1 to range
    i = 1
    while (i <= range) {
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

fun findMissingNumber3(arr: IntArray, size: Int, upperRange: Int): Int {
    val st = HashSet<Int>()

    var i = 0
    while (i < size) {
        st.add(arr[i])
        i += 1
    }
    i = 1
    while (i <= upperRange) {
        if (st.contains(i) == false)
            return i
        i += 1
    }
    println("NoNumberMissing")
    return -1
}


fun main5() {
    val first = intArrayOf(1, 3, 5, 4, 6, 8, 7)
    println("findMissingNumber : " + findMissingNumber(first, first.size))
    println("findMissingNumber2 : " + findMissingNumber2(first, first.size, 8))
    println("findMissingNumber3 : " + findMissingNumber3(first, first.size, 8))
}

fun MissingValues(arr: IntArray, size: Int) {
    Arrays.sort(arr)
    var value = arr[0]
    var i = 0
    while (i < size) {
        if (value == arr[i]) {
            value += 1
            i += 1
        } else {
            println("Missing value : " + value)
            value += 1
        }
    }
}

fun MissingValues2(arr: IntArray, size: Int) {
    val ht = HashSet<Int>()
    var minVal = 999999
    var maxVal = -999999

    for (i in 0 until size) {
        ht.add(arr[i])
        if (minVal > arr[i])
            minVal = arr[i]
        if (maxVal < arr[i])
            maxVal = arr[i]
    }
    for (i in minVal until maxVal + 1) {
        if (ht.contains(i) == false) {
            println("Missing value2 : " + i)
        }
    }
}


fun main6() {
    val arr = intArrayOf(1, 9, 2, 8, 3, 7, 4, 6)
    val size = arr.size
    MissingValues(arr, size)
    MissingValues2(arr, size)
}

fun OddCount(arr: IntArray, size: Int) {
    val ctr = HashMap<Int, Int>()
    var count = 0

    for (i in 0 until size) {
        if (ctr.containsKey(arr[i]))
            ctr.put(arr[i], ctr.getOrDefault(arr[i], 0) + 1)
        else
            ctr.put(arr[i], 1)
    }
    for (i in 0 until size) {
        if (ctr.containsKey(arr[i]) && ctr.getOrDefault(arr[i], 0) % 2 == 1) {
            println(arr[i])
            count++
            ctr.remove(arr[i])
        }
    }
    println("Odd count is :: $count")
}

fun OddCount2(arr: IntArray, size: Int) {
    var xorSum = 0
    var first = 0
    var second = 0
    val setBit: Int
    /*
     * xor of all elements in arr[] even occurrence will cancel each other. sum will
     * contain sum of two odd elements.
     */
    for (i in 0 until size)
        xorSum = xorSum xor arr[i]

    /* Rightmost set bit. */
    setBit = xorSum and (xorSum - 1).inv()

    /*
     * Dividing elements in two group: Elements having setBit bit as 1. Elements
     * having setBit bit as 0. Even elements cancelled themselves if group and we
     * get our numbers.
     */
    for (i in 0 until size) {
        if (arr[i] and setBit != 0)
            first = first xor arr[i]
        else
            second = second xor arr[i]
    }
    println(first + second)
}

fun SumDistinct(arr: IntArray, size: Int) {
    var sum = 0
    Arrays.sort(arr)
    for (i in 0 until size - 1) {
        if (arr[i] != arr[i + 1])
            sum += arr[i]
    }
    sum += arr[size - 1]
    println(sum)
}

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
    // Initialization of values
    minFirst = 0
    minSecond = 1
    minSum = Math.abs(arr[0] + arr[1])
    l = 0
    while (l < size - 1) {
        r = l + 1
        while (r < size) {
            sum = Math.abs(arr[l] + arr[r])
            if (sum < minSum) {
                minSum = sum
                minFirst = l
                minSecond = r
            }
            r++
        }
        l++
    }
    println(" Minimum sum elements are : " + arr[minFirst] + " , " + arr[minSecond])
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
    Arrays.sort(arr)// Arrays.sort(arr);

    // Initialization of values
    minFirst = 0
    minSecond = size - 1
    minSum = Math.abs(arr[minFirst] + arr[minSecond])
    l = 0
    r = size - 1
    while (l < r) {
        sum = arr[l] + arr[r]
        if (Math.abs(sum) < minSum) {
            minSum = Math.abs(sum)/// just corrected......hemant
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
    println(" Minimum sum pair : " + arr[minFirst] + " , " + arr[minSecond])
}


fun main7() {
    val first = intArrayOf(1, 5, -10, 3, 2, -6, 8, 9, 6)
    minAbsSumPair2(first, first.size)
    minAbsSumPair(first, first.size)

}

fun FindPair(arr: IntArray, size: Int, value: Int): Boolean {
    for (i in 0 until size) {
        for (j in i + 1 until size) {
            if (arr[i] + arr[j] == value) {
                println("The pair is : " + arr[i] + "," + arr[j])
                return true
            }
        }
    }
    return false
}

fun FindPair2(arr: IntArray, size: Int, value: Int): Boolean {
    var first = 0
    var second = size - 1
    var curr: Int
    Arrays.sort(arr)// Arrays.sort(arr);
    while (first < second) {
        curr = arr[first] + arr[second]
        if (curr == value) {
            println("The pair is " + arr[first] + "," + arr[second])
            return true
        } else if (curr < value) {
            first++
        } else {
            second--
        }
    }
    return false
}

fun FindPair3(arr: IntArray, size: Int, value: Int): Boolean {
    val hs = HashSet<Int>()
    for (i in 0 until size) {
        if (hs.contains(value - arr[i])) {
            println("The pair is : " + arr[i] + " , " + (value - arr[i]))
            return true
        }
        hs.add(arr[i])
    }
    return false
}


fun main8() {
    val first = intArrayOf(1, 5, 4, 3, 2, 7, 8, 9, 6)
    println("FindPair : " + FindPair(first, first.size, 8))
    println("FindPair2 : " + FindPair2(first, first.size, 8))
    println("FindPair3 : " + FindPair3(first, first.size, 8))

}

fun FindDifference(arr: IntArray, size: Int, value: Int): Boolean {
    for (i in 0 until size) {
        for (j in i + 1 until size) {
            if (Math.abs(arr[i] - arr[j]) == value) {
                println("The pair is:: " + arr[i] + " & " + arr[j])
                return true
            }
        }
    }
    return false
}

fun FindDifference2(arr: IntArray, size: Int, value: Int): Boolean {
    var first = 0
    var second = 0
    var diff: Int
    Arrays.sort(arr)
    while (first < size && second < size) {
        diff = Math.abs(arr[first] - arr[second])
        if (diff == value) {
            println("The pair is::" + arr[first] + " & " + arr[second])
            return true
        } else if (diff > value)
            first += 1
        else
            second += 1
    }
    return false
}

fun findMinDiff(arr: IntArray, size: Int): Int {
    Arrays.sort(arr)
    var diff = 9999999

    for (i in 0 until size - 1) {
        if (arr[i + 1] - arr[i] < diff)
            diff = arr[i + 1] - arr[i]
    }
    return diff
}

fun MinDiffPair(arr1: IntArray, size1: Int, arr2: IntArray, size2: Int): Int {
    var minDiff = 9999999
    var first = 0
    var second = 0
    var out1 = 0
    var out2 = 0
    var diff: Int
    Arrays.sort(arr1)
    Arrays.sort(arr2)
    while (first < size1 && second < size2) {
        diff = Math.abs(arr1[first] - arr2[second])
        if (minDiff > diff) {
            minDiff = diff
            out1 = arr1[first]
            out2 = arr2[second]
        }
        if (arr1[first] < arr2[second])
            first += 1
        else
            second += 1
    }
    println("The pair is :: $out1 and $out2")
    println("Minimum difference is :: $minDiff")
    return minDiff
}


fun main9() {
    val first = intArrayOf(1, 5, 4, 3, 2, 7, 8, 9, 6)
    println("FindDifference : " + FindDifference(first, first.size, 6))
    println("FindDifference2 : " + FindDifference2(first, first.size, 6))
    println("findMinDiff : " + findMinDiff(first, first.size))
    println("MinDiffPair : " + MinDiffPair(first, first.size, first, first.size))
}

fun ClosestPair(arr: IntArray, size: Int, value: Int) {
    var diff = 999999
    var first = -1
    var second = -1
    var curr: Int
    for (i in 0 until size) {
        for (j in i + 1 until size) {
            curr = Math.abs(value - (arr[i] + arr[j]))
            if (curr < diff) {
                diff = curr
                first = arr[i]
                second = arr[j]
            }
        }
    }
    println("closest pair is ::$first and $second")
}

fun ClosestPair2(arr: IntArray, size: Int, value: Int) {
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
            if (Math.abs(curr) < diff) {
                diff = Math.abs(curr)
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
    println("closest pair is :: $first and $second")
}


fun main10() {
    val first = intArrayOf(1, 5, 4, 3, 2, 7, 8, 9, 6)
    ClosestPair(first, first.size, 6)
    ClosestPair2(first, first.size, 6)
}

fun SumPairRestArray(arr: IntArray, size: Int): Boolean {
    var total: Int
    var low: Int
    var high: Int
    var curr: Int
    val value: Int
    Arrays.sort(arr)
    total = 0
    for (i in 0 until size)
        total += arr[i]
    value = total / 2
    low = 0
    high = size - 1
    while (low < high) {
        curr = arr[low] + arr[high]
        if (curr == value) {
            println("Pair is :: " + arr[low] + arr[high])
            return true
        } else if (curr < value)
            low += 1
        else
            high -= 1
    }
    return false
}

fun ZeroSumTriplets(arr: IntArray, size: Int) {
    for (i in 0 until size - 2) {
        for (j in i + 1 until size - 1) {
            for (k in j + 1 until size) {
                if (arr[i] + arr[j] + arr[k] == 0)
                    println("Triplet :: " + arr[i] + arr[j] + arr[k])
            }
        }
    }
}

fun ZeroSumTriplets2(arr: IntArray, size: Int) {
    var start: Int
    var stop: Int
    Arrays.sort(arr)
    for (i in 0 until size - 2) {
        start = i + 1
        stop = size - 1

        while (start < stop) {
            if (arr[i] + arr[start] + arr[stop] == 0) {
                println("Triplet :: " + arr[i] + arr[start] + arr[stop])
                start += 1
                stop -= 1
            } else if (arr[i] + arr[start] + arr[stop] > 0)
                stop -= 1
            else
                start += 1
        }
    }
}

fun findTriplet(arr: IntArray, size: Int, value: Int) {
    for (i in 0 until size - 2)
        for (j in i + 1 until size - 1)
            for (k in j + 1 until size) {
                if (arr[i] + arr[j] + arr[k] == value)
                    println("Triplet :: " + arr[i] + arr[j] + arr[k])
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
                println("Triplet ::" + arr[i] + arr[start] + arr[stop])
                start += 1
                stop -= 1
            } else if (arr[i] + arr[start] + arr[stop] > value)
                stop -= 1
            else
                start += 1
        }
    }
}

fun ABCTriplet(arr: IntArray, size: Int) {
    var start: Int
    var stop: Int
    Arrays.sort(arr)
    for (i in 0 until size - 1) {
        start = 0
        stop = size - 1
        while (start < stop) {
            if (arr[i] == arr[start] + arr[stop]) {
                println("Triplet ::%d, %d, %d" + arr[i] + arr[start] + arr[stop])
                start += 1
                stop -= 1
            } else if (arr[i] < arr[start] + arr[stop])
                stop -= 1
            else
                start += 1
        }
    }
}

fun SmallerThenTripletCount(arr: IntArray, size: Int, value: Int) {
    var start: Int
    var stop: Int
    var count = 0
    Arrays.sort(arr)

    for (i in 0 until size - 2) {
        start = i + 1
        stop = size - 1
        while (start < stop) {
            if (arr[i] + arr[start] + arr[stop] >= value)
                stop -= 1
            else {
                count += stop - start
                start += 1
            }
        }
    }
    println(count)
}

fun APTriplets(arr: IntArray, size: Int) {
    var i: Int
    var j: Int
    var k: Int
    i = 1
    while (i < size - 1) {
        j = i - 1
        k = i + 1
        while (j >= 0 && k < size) {
            if (arr[j] + arr[k] == 2 * arr[i]) {
                println("Triplet ::" + arr[j] + arr[i] + arr[k])
                k += 1
                j -= 1
            } else if (arr[j] + arr[k] < 2 * arr[i])
                k += 1
            else
                j -= 1
        }
        i++
    }
}

fun GPTriplets(arr: IntArray, size: Int) {
    var i: Int
    var j: Int
    var k: Int
    i = 1
    while (i < size - 1) {
        j = i - 1
        k = i + 1
        while (j >= 0 && k < size) {
            if (arr[j] * arr[k] == arr[i] * arr[i]) {
                println("Triplet is :: " + arr[j] + arr[i] + arr[k])
                k += 1
                j -= 1
            } else if (arr[j] + arr[k] < 2 * arr[i])
                k += 1
            else
                j -= 1
        }
        i++
    }
}

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
                if (arr[i] + arr[j] > arr[k] && arr[k] + arr[j] > arr[i] && arr[i] + arr[k] > arr[j])
                    count += 1
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
             */
            while (k < size && arr[i] + arr[j] > arr[k])
                k += 1

            count += k - j - 1
            j++
        }
        i++
    }
    return count
}

fun getMax(arr: IntArray, size: Int): Int {
    var max = arr[0]
    var count : Int
    var maxCount = 1
    for (i in 0 until size) {
        count = 1
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


fun main11() {
    val first = intArrayOf(1, 30, 5, 30, 9, 30, 5)
    println("getMax : " + getMax(first, first.size))
    println("getMax2 : " + getMax2(first, first.size))
    println("getMax3 : " + getMax3(first, first.size, 50))
}

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
    var count : Int
    val candidate: Int
    Arrays.sort(arr) // Sort(arr,size);
    candidate = arr[majIndex]
    count = 0
    for (i in 0 until size) {
        if (arr[i] == candidate) {
            count++
        }
    }
    return if (count > size / 2) {
        arr[majIndex]
    } else {
        Integer.MIN_VALUE
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


fun main12() {
    val first = intArrayOf(1, 5, 5, 13, 5, 31, 5)
    println("getMajority : " + getMajority(first, first.size))
    println("getMajority2 : " + getMajority2(first, first.size))
    println("getMajority3 : " + getMajority3(first, first.size))
}

fun getMedian(arr: IntArray, size: Int): Int {
    Arrays.sort(arr)
    return arr[size / 2]
}

fun SearchBotinicArrayMax(arr: IntArray, size: Int): Int {
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
        if (arr[mid - 1] < arr[mid] && arr[mid + 1] < arr[mid])
        // maxima
        {
            maximaFound = 1
            break
        } else if (arr[mid - 1] < arr[mid] && arr[mid] < arr[mid + 1])
        // increasing
        {
            start = mid + 1
        } else if (arr[mid - 1] > arr[mid] && arr[mid] > arr[mid + 1])
        // decreasing
        {
            end = mid - 1
        } else {
            break
        }
    }
    if (maximaFound == 0) {
        println("error")
        return 0
    }
    return arr[mid]
}

fun SearchBitonicArray(arr: IntArray, size: Int, key: Int): Int {
    val max = FindMaxBitonicArray(arr, size)
    val k = BinarySearch(arr, 0, max, key, true)
    return if (k != -1) {
        k
    } else {
        BinarySearch(arr, max + 1, size - 1, key, false)
    }
}

fun FindMaxBitonicArray(arr: IntArray, size: Int): Int {
    var start = 0
    var end = size - 1
    var mid: Int
    if (size < 3) {
        println("error")
        return -1
    }
    while (start <= end) {
        mid = (start + end) / 2
        if (arr[mid - 1] < arr[mid] && arr[mid + 1] < arr[mid])
        // maxima
        {
            return mid
        } else if (arr[mid - 1] < arr[mid] && arr[mid] < arr[mid + 1])
        // increasing
        {
            start = mid + 1
        } else if (arr[mid - 1] > arr[mid] && arr[mid] > arr[mid + 1])
        // increasing
        {
            end = mid - 1
        } else {
            break
        }
    }
    println("error")
    return -1
}


fun main13() {
    val first = intArrayOf(1, 5, 10, 13, 20, 30, 8, 7, 6)

    println("SearchBotinicArrayMax : " + SearchBotinicArrayMax(first, first.size))
    println("SearchBitonicArray : " + SearchBitonicArray(first, first.size, 7))
}

fun findKeyCount(arr: IntArray, size: Int, key: Int): Int {
    var count = 0
    for (i in 0 until size) {
        if (arr[i] == key) {
            count++
        }
    }
    return count
}

fun findKeyCount2(arr: IntArray, size: Int, key: Int): Int {
    val firstIndex: Int
    val lastIndex: Int
    firstIndex = findFirstIndex(arr, 0, size - 1, key)
    lastIndex = findLastIndex(arr, 0, size - 1, key)
    return lastIndex - firstIndex + 1
}

/* Using binary search method. */
fun FirstIndex(arr: IntArray, size: Int, low: Int, high: Int, value: Int): Int {
    var mid = 0
    if (high >= low)
        mid = (low + high) / 2

    /*
     * Find first occurrence of value, either it should be the first element of the
     * array or the value before it is smaller than it.
     */
    return if ((mid == 0 || arr[mid - 1] < value) && arr[mid] == value)
        mid
    else if (arr[mid] < value)
        FirstIndex(arr, size, mid + 1, high, value)
    else
        FirstIndex(arr, size, low, mid - 1, value)
}

fun isMajority(arr: IntArray, size: Int): Boolean {
    val majority = arr[size / 2]
    val i = FirstIndex(arr, size, 0, size - 1, majority)
    /*
     * we are using majority element form array so we will get some valid index
     * always.
     */
    return if (i + size / 2 <= size - 1 && arr[i + size / 2] == majority)
        true
    else
        false
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
    return if (key <= arr[mid])
    // <= is us the number.t in sorted array.
    {
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
    return if (key < arr[mid])
    // <
    {
        findLastIndex(arr, start, mid - 1, key)
    } else {
        findLastIndex(arr, mid + 1, end, key)
    }
}


fun main14() {
    val first = intArrayOf(1, 5, 10, 13, 20, 30, 8, 7, 6)
    println("findKeyCount : " + findKeyCount(first, first.size, 6))
    println("findKeyCount2 : " + findKeyCount2(first, first.size, 6))
}

fun maxProfit(stocks: IntArray, size: Int): Int {
    var buy = 0
    var sell = 0
    var curMin = 0
    var currProfit : Int
    var maxProfit = 0
    for (i in 0 until size) {
        if (stocks[i] < stocks[curMin]) {
            curMin = i
        }
        currProfit = stocks[i] - stocks[curMin]
        if (currProfit > maxProfit) {
            buy = curMin
            sell = i
            maxProfit = currProfit
        }
    }
    println("Purchase day is- " + buy + " at price " + stocks[buy])
    println("Sell day is- " + sell + " at price " + stocks[sell])
    return maxProfit
}


fun main15() {
    val first = intArrayOf(10, 150, 6, 67, 61, 16, 86, 6, 67, 78, 150, 3, 28, 143)
    println("maxProfit : " + maxProfit(first, first.size))
}

fun findMedian(arrFirst: IntArray, sizeFirst: Int, arrSecond: IntArray, sizeSecond: Int): Int {
    val medianIndex = (sizeFirst + sizeSecond + (sizeFirst + sizeSecond) % 2) / 2// cealing
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


fun main16() {
    val first = intArrayOf(1, 5, 6, 6, 6, 6, 6, 6, 7, 8, 10, 13, 20, 30)
    val second = intArrayOf(1, 5, 6, 6, 6, 6, 6, 6, 7, 8, 10, 13, 20, 30)
    println("findMedian : " + findMedian(first, first.size, second, second.size))
}

fun BinarySearch01(arr: IntArray, size: Int): Int {
    return if (size == 1 && arr[0] == 1) {
        0
    } else BinarySearch01Util(arr, 0, size - 1)
}

fun BinarySearch01Util(arr: IntArray, start: Int, end: Int): Int {
    if (end < start) {
        return -1
    }
    val mid = (start + end) / 2
    if (1 == arr[mid] && 0 == arr[mid - 1]) {
        return mid
    }
    return if (0 == arr[mid]) {
        BinarySearch01Util(arr, mid + 1, end)
    } else {
        BinarySearch01Util(arr, start, mid - 1)
    }
}

fun main17() {
    val first = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1)
    println("BinarySearch01 : " + BinarySearch01(first, first.size))
}

fun RotationMaxUtil(arr: IntArray, start: Int, end: Int): Int {
    if (end <= start) {
        return arr[start]
    }
    val mid = (start + end) / 2
    if (arr[mid] > arr[mid + 1])
        return arr[mid]

    return if (arr[start] <= arr[mid])
    /* increasing part. */
        RotationMaxUtil(arr, mid + 1, end)
    else
        RotationMaxUtil(arr, start, mid - 1)
}

fun RotationMax(arr: IntArray, size: Int): Int {
    return RotationMaxUtil(arr, 0, size - 1)
}

fun FindRotationMaxUtil(arr: IntArray, start: Int, end: Int): Int {
    /* single element case. */
    if (end <= start)
        return start

    val mid = (start + end) / 2
    if (arr[mid] > arr[mid + 1])
        return mid

    return if (arr[start] <= arr[mid])
    /* increasing part. */
        FindRotationMaxUtil(arr, mid + 1, end)
    else
        FindRotationMaxUtil(arr, start, mid - 1)
}

fun FindRotationMax(arr: IntArray, size: Int): Int {
    return FindRotationMaxUtil(arr, 0, size - 1)
}

fun CountRotation(arr: IntArray, size: Int): Int {
    val maxIndex = FindRotationMaxUtil(arr, 0, size - 1)
    return (maxIndex + 1) % size
}

fun BinarySearchRotateArrayUtil(arr: IntArray, start: Int, end: Int, key: Int): Int {
    if (end < start) {
        return -1
    }
    val mid = (start + end) / 2
    if (key == arr[mid]) {
        return mid
    }
    return if (arr[mid] > arr[start]) {
        if (arr[start] <= key && key < arr[mid]) {
            BinarySearchRotateArrayUtil(arr, start, mid - 1, key)
        } else {
            BinarySearchRotateArrayUtil(arr, mid + 1, end, key)
        }
    } else {
        if (arr[mid] < key && key <= arr[end]) {
            BinarySearchRotateArrayUtil(arr, mid + 1, end, key)
        } else {
            BinarySearchRotateArrayUtil(arr, start, mid - 1, key)
        }
    }
}

fun BinarySearchRotateArray(arr: IntArray, size: Int, key: Int): Int {
    return BinarySearchRotateArrayUtil(arr, 0, size - 1, key)
}


fun main18() {
    val first = intArrayOf(34, 56, 77, 1, 5, 6, 6, 6, 6, 6, 6, 7, 8, 10, 13, 20, 30)
    println("BinarySearchRotateArray : " + BinarySearchRotateArray(first, first.size, 20))
    println("CountRotation : " + CountRotation(first, first.size))
    println("FindRotationMax : " + first[FindRotationMax(first, first.size)])
}

fun minAbsDiffAdjCircular(arr: IntArray, size: Int): Int {
    var diff = 9999999
    if (size < 2)
        return -1

    for (i in 0 until size)
        diff = Math.min(diff, Math.abs(arr[i] - arr[(i + 1) % size]))

    return diff
}

/*
 * Testing code
 */

fun main19() {
    val arr = intArrayOf(5, 29, 18, 51, 11)
    println("minAbsDiffAdjCircular" + minAbsDiffAdjCircular(arr, arr.size))
}

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


fun main20() {
    val str = "aaaabbbb".toCharArray()
    transformArrayAB1(str, str.size)
    print("transformArrayAB1 : " + str.toString())
}

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


fun main21() {
    val str1 = "aaaabbbb".toCharArray()
    val str2 = "bbaaaabb".toCharArray()

    println("checkPermutation : " + checkPermutation(str1, str1.size, str2, str2.size))
}

fun FindElementIn2DArray(arr: Array<IntArray>, r: Int, c: Int, value: Int): Boolean {
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
    if (size <= 1)
        return true

    Arrays.sort(arr)
    val diff = arr[1] - arr[0]
    for (i in 2 until size) {
        if (arr[i] - arr[i - 1] != diff)
            return false
    }
    return true
}

fun isAP2(arr: IntArray, size: Int): Boolean {
    var first = 9999999
    var second = 9999999
    var value: Int
    val hs = HashSet<Int>()
    for (i in 0 until size) {
        if (arr[i] < first) {
            second = first
            first = arr[i]
        } else if (arr[i] < second)
            second = arr[i]
    }
    val diff = second - first

    for (i in 0 until size) {
        if (hs.contains(arr[i]))
            return false
        hs.add(arr[i])
    }
    for (i in 0 until size) {
        value = first + i * diff
        if (!hs.contains(value))
            return false
    }
    return true
}

fun isAP3(arr: IntArray, size: Int): Boolean {
    var first = 9999999
    var second = 9999999
    val count = IntArray(size)
    var index = -1
    for (i in 0 until size) {
        if (arr[i] < first) {
            second = first
            first = arr[i]
        } else if (arr[i] < second)
            second = arr[i]
    }
    val diff = second - first

    for (i in 0 until size)
        index = (arr[i] - first) / diff
    if (index > size - 1 || count[index] != 0)
        return false
    count[index] = 1

    for (i in 0 until size)
        if (count[i] != 1)
            return false
    return true
}

fun findBalancedPoint(arr: IntArray, size: Int): Int {
    var first = 0
    var second = 0
    for (i in 1 until size)
        second += arr[i]

    for (i in 0 until size) {
        if (first == second) {
            return i
        }
        if (i < size - 1)
            first += arr[i]
        second -= arr[i + 1]
    }
    return -1
}

/*
 * Testing code
 */

fun main22() {
    val arr = intArrayOf(-7, 1, 5, 2, -4, 3, 0)
    println("findBalancedPoint : " + findBalancedPoint(arr, arr.size))

}

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
         */
        if (arr[mid] == value || arr[mid] < value && (mid == size - 1 || arr[mid + 1] > value))
            return mid
        else if (arr[mid] < value)
            start = mid + 1
        else
            stop = mid - 1
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
         */
        if (arr[mid] == value || arr[mid] > value && (mid == 0 || arr[mid - 1] < value))
            return mid
        else if (arr[mid] < value)
            start = mid + 1
        else
            stop = mid - 1
    }
    return -1
}

fun ClosestNumber(arr: IntArray, size: Int, num: Int): Int {
    var start = 0
    var stop = size - 1
    var output = -1
    var minDist = 9999
    var mid: Int

    while (start <= stop) {
        mid = (start + stop) / 2
        if (minDist > Math.abs(arr[mid] - num)) {
            minDist = Math.abs(arr[mid] - num)
            output = arr[mid]
        }
        if (arr[mid] == num)
            break
        else if (arr[mid] > num)
            stop = mid - 1
        else
            start = mid + 1
    }
    return output
}

fun DuplicateKDistance(arr: IntArray, size: Int, k: Int): Boolean {
    val hm = HashMap<Int, Int>()

    for (i in 0 until size) {
        if (hm.containsKey(arr[i]) && i - hm.getOrDefault(arr[i], 0) <= k) {
            println("Value:" + arr[i] + " Index: " + hm.get(arr[i]) + " & " + i)
            return true
        } else
            hm.put(arr[i], i)
    }
    return false
}

/*
 * Testing code
 */

fun main23() {
    val arr = intArrayOf(1, 2, 3, 1, 4, 5)
    println("DuplicateKDistance : ")
    DuplicateKDistance(arr, arr.size, 3)
}

fun frequencyCounts(arr: IntArray, size: Int) {
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
    for (i in 0 until size)
        println(i + 1 + Math.abs(arr[i]))
}

fun KLargestElements(arrIn: IntArray, size: Int, k: Int): Int {
    val arr = IntArray(size)
    for (i in 0 until size)
        arr[i] = arrIn[i]

    Arrays.sort(arr)
    for (i in 0 until size) {
        if (arrIn[i] >= arr[size - k]) {
            println(arrIn[i])
            return arrIn[i]
        }

    }
    return -1
}

fun QuickSelectUtil(arr: IntArray, lowerIn: Int, upperIn: Int, k: Int) {
    var lower = lowerIn
    var upper = upperIn
    if (upper <= lower)
        return

    val pivot = arr[lower]
    val start = lower
    val stop = upper

    while (lower < upper) {
        while (arr[lower] <= pivot) {
            lower++
        }
        while (arr[upper] > pivot) {
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

fun KLargestElements2(arrIn: IntArray, size: Int, k: Int): Int {
    val arr = IntArray(size)
    for (i in 0 until size)
        arr[i] = arrIn[i]

    QuickSelectUtil(arr, 0, size - 1, size - k)
    for (i in 0 until size) {
        if (arrIn[i] >= arr[size - k]) {
            println(arrIn[i])
            return arrIn[i]
        }
    }
    return -1
}

/* linear search method */
fun FixPoint(arr: IntArray, size: Int): Int {
    for (i in 0 until size) {
        if (arr[i] == i)
            return i
    } /* fix point not found so return invalid index */
    return -1
}

/* Binary search method */
fun FixPoint2(arr: IntArray, size: Int): Int {
    var low = 0
    var high = size - 1
    var mid: Int
    while (low <= high) {
        mid = (low + high) / 2
        if (arr[mid] == mid)
            return mid
        else if (arr[mid] < mid)
            low = mid + 1
        else
            high = mid - 1
    }
    /* fix point not found so return invalid index */
    return -1
}

fun subArraySums(arr: IntArray, size: Int, value: Int): Int {
    var first = 0
    var second = 0
    var sum = arr[first]
    while (second < size && first < size) {
        if (sum == value)
            println(first + second)

        if (sum < value) {
            second += 1
            if (second < size)
                sum += arr[second]
        } else {
            sum -= arr[first]
            first += 1
        }
    }
    return sum
}

fun MaxConSub(arr: IntArray, size: Int): Int {
    var currMax = 0
    var maximum = 0
    for (i in 0 until size) {
        currMax = Math.max(arr[i], currMax + arr[i])
        if (currMax < 0)
            currMax = 0
        if (maximum < currMax)
            maximum = currMax
    }
    println(maximum)
    return maximum
}

fun MaxConSubArr(A: IntArray, sizeA: Int, B: IntArray, sizeB: Int): Int {
    var currMax = 0
    var maximum = 0
    val hs = HashSet<Int>()

    for (i in 0 until sizeB)
        hs.add(B[i])

    for (i in 0 until sizeA)
        if (hs.contains(A[i]))
            currMax = 0
        else
            currMax = Math.max(A[i], currMax + A[i])
    if (currMax < 0)
        currMax = 0
    if (maximum < currMax)
        maximum = currMax
    println(maximum)
    return maximum
}

fun MaxConSubArr2(A: IntArray, sizeA: Int, B: IntArray, sizeB: Int): Int {
    Arrays.sort(B)
    var currMax = 0
    var maximum = 0

    for (i in 0 until sizeA) {
        if (Binarysearch(B, sizeB, A[i]))
            currMax = 0
        else {
            currMax = Math.max(A[i], currMax + A[i])
            if (currMax < 0)
                currMax = 0
            if (maximum < currMax)
                maximum = currMax
        }
    }
    println(maximum)
    return maximum
}

fun RainWater(arr: IntArray, size: Int): Int {
    val leftHigh = IntArray(size)
    val rightHigh = IntArray(size)

    var max = arr[0]
    leftHigh[0] = arr[0]
    for (i in 1 until size) {
        if (max < arr[i])
            max = arr[i]
        leftHigh[i] = max
    }
    max = arr[size - 1]
    rightHigh[size - 1] = arr[size - 1]
    for (i in size - 2 downTo 0) {
        if (max < arr[i])
            max = arr[i]
        rightHigh[i] = max
    }

    var water = 0
    for (i in 0 until size)
        water += Math.min(leftHigh[i], rightHigh[i]) - arr[i]
    println("Water : $water")
    return water
}

fun RainWater2(arr: IntArray, size: Int): Int {
    var water = 0
    var leftMax = 0
    var rightMax = 0
    var left = 0
    var right = size - 1

    while (left <= right) {
        if (arr[left] < arr[right]) {
            if (arr[left] > leftMax)
                leftMax = arr[left]
            else
                water += leftMax - arr[left]
            left += 1
        } else {
            if (arr[right] > rightMax)
                rightMax = arr[right]
            else
                water += rightMax - arr[right]
            right -= 1
        }
    }
    println("Water : $water")
    return water
}

fun seperateEvenAndOdd(arr: IntArray, size: Int) {
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


fun main24() {
    val first = intArrayOf(1, 5, 6, 6, 6, 6, 6, 6, 7, 8, 10, 13, 20, 30)
    seperateEvenAndOdd(first, first.size)
    print("seperateEvenAndOdd : ")
    for (`val` in first) {
        print("$`val` ")
    }
    println()
}

fun main(args: Array<String>) {
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
    main13()
    main14()
    main15()
    main16()
    main17()
    main18()
    main19()
    main20()
    main21()
    main22()
    main23()
    main24()
}