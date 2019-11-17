import java.util.HashMap
import java.util.Arrays

fun function2() {
    println("fun2 line 1")
}
fun function1() {
    println("fun1 line 1")
    function2()
    println("fun1 line 2")
}
fun main1() {
    println("main line 1")
    function1()
    println("main line 2")
}
fun factorial(i:Int):Int {
    // Termination Condition
    if (i <= 1)
    {
        return 1
    }
    // Body, Recursive Expansion
    return i * factorial(i - 1)
}
///////////////

fun printInt1(numberInput:Int) {
    var number = numberInput;
    var digit = (number % 10 + '0'.toInt()).toChar()
    number = number / 10
    if (number != 0)
    {
        printInt1(number)
    }
    print("%c" + digit)
}

fun printInt(numberInput:Int) {
    var conversion = "0123456789ABCDEF"
    var base = 16
    var number = numberInput;
    var digit = (number % base).toChar()
    number = number / base
    if (number != 0)
    {
        printInt(number)
    }
    print(digit)
}


fun printArray(arr:IntArray, count:Int) {
    print("[")
    for (i in 0 until count)
    {
        print(" " + arr[i])
    }
    print(" ]\n")
}
fun swap(arr:IntArray, x:Int, y:Int) {
    var temp = arr[x]
    arr[x] = arr[y]
    arr[y] = temp
    return
}
fun SumArray(arr:IntArray):Int {
    var size = arr.size
    var total = 0
    for (index in 0 until size)
    {
        total = total + arr[index]
    }
    return total
}
fun main2() {
    var arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    println("Sum of values in array:" + SumArray(arr))
}
fun SequentialSearch(arr:IntArray, size:Int, value:Int):Int {
    for (i in 0 until size)
    {
        if (value == arr[i])
        {
            run({ return i })
        }
    }
    return -1
}
fun BinarySearch(arr:IntArray, size:Int, value:Int):Int {
    var mid:Int
    var low = 0
    var high = size - 1
    while (low <= high)
    {
        mid = (low + high) / 2
        if (arr[mid] == value)
        {
            return mid
        }
        else
        {
            if (arr[mid] < value)
            {
                low = mid + 1
            }
            else
            {
                high = mid - 1
            }
        }
    }
    return -1
}

fun main3() {
    var arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    println("Sum of values in array:" + SequentialSearch(arr, arr.size, 7))
    println("Sum of values in array:" + BinarySearch(arr, arr.size, 7))
}

fun rotateArray(a:IntArray, n:Int, k:Int) {
    reverseArray(a, 0, k - 1)
    reverseArray(a, k, n - 1)
    reverseArray(a, 0, n - 1)
}

fun reverseArray(a:IntArray, start:Int, end:Int) {
    var i = start
    var j = end
    while (i < j)
    {
        var temp = a[i]
        a[i] = a[j]
        a[j] = temp
        i++
        j--
    }
}

fun reverseArray2(a:IntArray) {
    var start = 0
    var end = a.size - 1
    var i = start
    var j = end
    while (i < j)
    {
        var temp = a[i]
        a[i] = a[j]
        a[j] = temp
        i++
        j--
    }
}

fun main4() {
    var arr = intArrayOf(1, 2, 3, 4, 5, 6)
    rotateArray(arr, arr.size, 2)
    printArray(arr, arr.size)
}

fun maxSubArraySum(a:IntArray, size:Int):Int {
    var maxSoFar = 0
    var maxEndingHere = 0
    for (i in 0 until size)
    {
        maxEndingHere = maxEndingHere + a[i]
        if (maxEndingHere < 0)
        {
            maxEndingHere = 0
        }
        if (maxSoFar < maxEndingHere)
        {
            maxSoFar = maxEndingHere
        }
    }
    return maxSoFar
}

fun main5() {
    var arr = intArrayOf(1, -2, 3, 4, -4, 6, -4, 3, 2)
    println("Max sub array sum :" + maxSubArraySum(arr, 9))
}

fun WaveArray2(arr:IntArray) {
    var size = arr.size
    /* Odd elements are lesser then even elements. */
    var i = 1
    while (i < size)
    {
        if ((i - 1) >= 0 && arr[i] > arr[i - 1])
        {
            swap(arr, i, i - 1)
        }
        if ((i + 1) < size && arr[i] > arr[i + 1])
        {
            swap(arr, i, i + 1)
        }
        i += 2
    }
}

fun WaveArray(arr:IntArray) {
    var size = arr.size
    Arrays.sort(arr)
    printArray(arr, arr.size)
    var i = 0
    while (i < size - 1)
    {
        swap(arr, i, i + 1)
        i += 2
    }
}

/* Testing code */
fun main6() {
    var arr = intArrayOf(8, 1, 2, 3, 4, 5, 6, 4, 2)
    printArray(arr, arr.size)
    WaveArray(arr)
    printArray(arr, arr.size)
    var arr2 = intArrayOf(8, 1, 2, 3, 4, 5, 6, 4, 2)
    WaveArray2(arr2)
    printArray(arr2, arr2.size)
}

fun indexArray(arr:IntArray, size:Int) {
    for (i in 0 until size)
    {
        var curr = i
        var value = -1
        /* swaps to move elements in proper position. */
        while (arr[curr] != -1 && arr[curr] != curr)
        {
            var temp = arr[curr]
            arr[curr] = value
            curr = temp
            value = curr
        }
        /* check if some swaps happened. */
        if (value != -1)
        {
            arr[curr] = value
        }
    }
}

fun indexArray2(arr:IntArray, size:Int) {
    var temp:Int
    for (i in 0 until size)
    {
        while (arr[i] != -1 && arr[i] != i)
        {
            /* swap arr[i] and arr[arr[i]] */
            temp = arr[i]
            arr[i] = arr[temp]
            arr[temp] = temp
        }
    }
}

/* Testing code */
fun main7() {
    var arr = intArrayOf(8, -1, 6, 1, 9, 3, 2, 7, 4, -1)
    var size = arr.size
    indexArray2(arr, size)
    printArray(arr, size)
    var arr2 = intArrayOf(8, -1, 6, 1, 9, 3, 2, 7, 4, -1)
    size = arr2.size
    indexArray(arr2, size)
    printArray(arr2, size)
}

fun Sort1toN(arr:IntArray, size:Int) {
    var curr:Int
    var value:Int
    var next:Int
    for (i in 0 until size)
    {
        curr = i
        value = -1
        /* swaps to move elements in proper position. */
        while (curr >= 0 && curr < size && arr[curr] != curr + 1)
        {
            next = arr[curr]
            arr[curr] = value
            value = next
            curr = next - 1
        }
    }
}

fun Sort1toN2(arr:IntArray, size:Int) {
    var temp:Int
    for (i in 0 until size)
    {
        while (arr[i] != i + 1 && arr[i] > 1)
        {
            temp = arr[i]
            arr[i] = arr[temp - 1]
            arr[temp - 1] = temp
        }
    }
}

fun main8() {
    var arr = intArrayOf(8, 5, 6, 1, 9, 3, 2, 7, 4, 10)
    var size = arr.size
    Sort1toN2(arr, size)
    printArray(arr, size)
    var arr2 = intArrayOf(8, 5, 6, 1, 9, 3, 2, 7, 4, 10)
    size = arr2.size
    Sort1toN(arr2, size)
    printArray(arr2, size)
}

fun SmallestPositiveMissingNumber(arr:IntArray, size:Int):Int {
    var found:Int
    for (i in 1 until size + 1)
    {
        found = 0
        for (j in 0 until size)
        {
            if (arr[j] == i)
            {
                found = 1
                break
            }
        }
        if (found == 0)
        {
            return i
        }
    }
    return -1
}

fun SmallestPositiveMissingNumber2(arr:IntArray, size:Int):Int {
    var hs = HashMap<Int, Int>()
    for (i in 0 until size)
    {
        hs.put(arr[i], 1)
    }
    for (i in 1 until size + 1)
    {
        if (hs.containsKey(i) == false)
        {
            return i
        }
    }
    return -1
}

fun SmallestPositiveMissingNumber3(arr:IntArray, size:Int):Int {
    var aux = IntArray(size)
    Arrays.fill(aux, -1)
    for (i in 0 until size)
    {
        if (arr[i] > 0 && arr[i] <= size)
        {
            aux[arr[i] - 1] = arr[i]
        }
    }
    for (i in 0 until size)
    {
        if (aux[i] != i + 1)
        {
            return i + 1
        }
    }
    return -1
}

fun SmallestPositiveMissingNumber4(arr:IntArray, size:Int):Int {
    var temp:Int
    for (i in 0 until size)
    {
        while (arr[i] != i + 1 && arr[i] > 0 && arr[i] <= size)
        {
            temp = arr[i]
            arr[i] = arr[temp - 1]
            arr[temp - 1] = temp
        }
    }
    for (i in 0 until size)
    {
        if (arr[i] != i + 1)
        {
            return i + 1
        }
    }
    return -1
}

fun main9() {
    var arr = intArrayOf(8, 5, 6, 1, 9, 11, 2, 7, 4, 10)
    var size = arr.size
    println("Max sub array sum :" + SmallestPositiveMissingNumber(arr, size))
    println("Max sub array sum :" + SmallestPositiveMissingNumber2(arr, size))
    println("Max sub array sum :" + SmallestPositiveMissingNumber3(arr, size))
    println("Max sub array sum :" + SmallestPositiveMissingNumber4(arr, size))
}

fun MaxMinArr(arr:IntArray, size:Int) {
    var aux = Arrays.copyOf(arr, size)
    var start = 0
    var stop = size - 1
    for (i in 0 until size)
    {
        if (i % 2 == 0)
        {
            arr[i] = aux[stop]
            stop -= 1
        }
        else
        {
            arr[i] = aux[start]
            start += 1
        }
    }
}

fun ReverseArr(arr:IntArray, startInput: Int, stopInput:Int) {
    var start = startInput
    var stop = stopInput
    while (start < stop)
    {
        swap(arr, start, stop)
        start += 1
        stop -= 1
    }
}

fun MaxMinArr2(arr:IntArray, size:Int) {
    for (i in 0 until (size - 1))
    {
        ReverseArr(arr, i, size - 1)
    }
}

/* Testing code */
fun main10() {
    var arr = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    var size = arr.size
    MaxMinArr(arr, size)
    printArray(arr, size)
    var arr2 = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    var size2 = arr.size
    MaxMinArr2(arr2, size2)
    printArray(arr2, size2)
}

fun maxCircularSum(arr:IntArray, size:Int):Int {
    var sumAll = 0
    var currvar = 0
    var maxvar:Int
    for (i in 0 until size)
    {
        sumAll += arr[i]
        currvar += (i * arr[i])
    }
    maxvar = currvar
    for (i in 1 until size)
    {
        currvar = (currvar + sumAll) - (size * arr[size - i])
        if (currvar > maxvar)
        {
            maxvar = currvar
        }
    }
    return maxvar
}

/* Testing code */
fun main11() {
    var arr = intArrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
    println("MaxCirculrSm: " + maxCircularSum(arr, arr.size))
}

fun ArrayIndexMaxDiff(arr:IntArray, size:Int):Int {
    var maxDiff = -1
    var j:Int
    for (i in 0 until size)
    {
        j = size - 1
        while (j > i)
        {
            if (arr[j] > arr[i])
            {
                maxDiff = Math.max(maxDiff, j - i)
                break
            }
            j -= 1
        }
    }
    return maxDiff
}

fun ArrayIndexMaxDiff2(arr:IntArray, size:Int):Int {
    var leftMin = IntArray(size)
    var rightMax = IntArray(size)
    leftMin[0] = arr[0]
    var i:Int
    var j:Int
    var maxDiff:Int
    i = 1
    while (i < size)
    {
        if (leftMin[i - 1] < arr[i])
        {
            leftMin[i] = leftMin[i - 1]
        }
        else
        {
            leftMin[i] = arr[i]
        }
        i++
    }
    rightMax[size - 1] = arr[size - 1]
    i = size - 2
    while (i >= 0)
    {
        if (rightMax[i + 1] > arr[i])
        {
            rightMax[i] = rightMax[i + 1]
        }
        else
        {
            rightMax[i] = arr[i]
        }
        i--
    }
    i = 0
    j = 0
    maxDiff = -1
    while (j < size && i < size)
    {
        if (leftMin[i] < rightMax[j])
        {
            maxDiff = Math.max(maxDiff, j - i)
            j = j + 1
        }
        else
        {
            i = i + 1
        }
    }
    return maxDiff
}

fun main12() {
    var arr = intArrayOf(33, 9, 10, 3, 2, 60, 30, 33, 1)
    println("ArrayIndexMaxDiff : " + ArrayIndexMaxDiff(arr, arr.size))
    println("ArrayIndexMaxDiff : " + ArrayIndexMaxDiff2(arr, arr.size))
    // System.out.println("ArrayIndexMaxDiff : " + ArrayIndexMaxDiff3(arr, arr.length));
}

fun maxPathSum(arr1:IntArray, size1:Int, arr2:IntArray, size2:Int):Int {
    var i = 0
    var j = 0
    var result = 0
    var sum1 = 0
    var sum2 = 0
    while (i < size1 && j < size2)
    {
        if (arr1[i] < arr2[j])
        {
            sum1 += arr1[i]
            i += 1
        }
        else if (arr1[i] > arr2[j])
        {
            sum2 += arr2[j]
            j += 1
        }
        else
        {
            result += Math.max(sum1, sum2)
            result = result + arr1[i]
            sum1 = 0
            sum2 = 0
            i += 1
            j += 1
        }
    }
    while (i < size1)
    {
        sum1 += arr1[i]
        i += 1
    }
    while (j < size2)
    {
        sum2 += arr2[j]
        j += 1
    }
    result += Math.max(sum1, sum2)
    return result
}

/* Testing code */
fun main13() {
    var arr1 = intArrayOf(12, 13, 18, 20, 22, 26, 70)
    var arr2 = intArrayOf(11, 15, 18, 19, 20, 26, 30, 31)
    println("Max Path Sum :: " + maxPathSum(arr1, arr1.size, arr2, arr2.size))
}

fun towerOfHanoi(num:Int, src:Char, dst:Char, temp:Char) {
    if (num < 1)
    {
        return
    }
    towerOfHanoi(num - 1, src, temp, dst)
    println("Move " + num + " disk from peg " + src + " to peg " + dst)
    towerOfHanoi(num - 1, temp, dst, src)
}

fun main14() {
    var num = 4
    println("The sequence of moves involved in the Tower of Hanoi are :\n")
    towerOfHanoi(num, 'A', 'C', 'B')
}

fun GCD(m:Int, n:Int):Int {
    if (m < n)
    {
        return (GCD(n, m))
    }
    if (m % n == 0)
    {
        return (n)
    }
    return (GCD(n, m % n))
}

fun fibonacci(n:Int):Int {
    if (n <= 1)
    {
        return n
    }
    return fibonacci(n - 1) + fibonacci(n - 2)
}

fun permutation(arr:IntArray, i:Int, length:Int) {
    if (length == i)
    {
        printArray(arr, length)
        return
    }
    var j = i
    j = i
    while (j < length)
    {
        swap(arr, i, j)
        permutation(arr, i + 1, length)
        swap(arr, i, j)
        j++
    }
    return
}

fun main15() {
    var arr = IntArray(5)
    for (i in 0..4)
    {
        arr[i] = i
    }
    permutation(arr, 0, 5)
}

// Binary Search Algorithm - Recursive
fun BinarySearchRecursive(arr:IntArray, low:Int, high:Int, value:Int):Int {
    if (low > high)
        return -1
    var mid = (low + high) / 2
    if (arr[mid] == value)
    {
        return mid
    }
    else if (arr[mid] < value)
    {
        return BinarySearchRecursive(arr, mid + 1, high, value)
    }
    else
    {
        return BinarySearchRecursive(arr, low, mid - 1, value)
    }
}

/* Testing code */
fun main16() {
    var arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    println(BinarySearchRecursive(arr, 0, arr.size - 1, 6))
    println(BinarySearchRecursive(arr, 0, arr.size - 1, 16))
}

fun main(args : Array<String>){
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
    main11();
    main12();
    main13();
    main14();
    main15();
    main16();
}