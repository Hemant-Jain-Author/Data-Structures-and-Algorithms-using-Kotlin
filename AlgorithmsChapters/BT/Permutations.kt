fun printArray(arr: IntArray, n: Int) {
    for (i in 0 until n) print(arr[i].toString() + " ")
    println()
}

fun swap(arr: IntArray, i: Int, j: Int) {
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}

fun permutation(arr: IntArray, i: Int, length: Int) {
    if (length == i) {
        printArray(arr, length)
        return
    }
    for (j in i until length) {
        swap(arr, i, j)
        permutation(arr, i + 1, length)
        swap(arr, i, j)
    }
    return
}

/*
1 2 3 4 
1 2 4 3 
.....
4 1 3 2 
4 1 2 3 
*/
fun isValid(arr: IntArray, n: Int): Boolean {
    for (j in 1 until n) {
        if (java.lang.Math.abs(arr[j] - arr[j - 1]) < 2) return false
    }
    return true
}

fun permutation2(arr: IntArray, i: Int, length: Int) {
    if (length == i) {
        if (isValid(arr, length)) printArray(arr, length)
        return
    }
    for (j in i until length) {
        swap(arr, i, j)
        permutation2(arr, i + 1, length)
        swap(arr, i, j)
    }
    return
}

fun isValid2(arr: IntArray, i: Int): Boolean {
    return if (i < 1 || java.lang.Math.abs(arr[i] - arr[i - 1]) >= 2) true else false
}

fun permutation3(arr: IntArray, i: Int, length: Int) {
    if (length == i) {
        printArray(arr, length)
        return
    }
    for (j in i until length) {
        swap(arr, i, j)
        if (isValid2(arr, i)) permutation3(arr, i + 1, length)
        swap(arr, i, j)
    }
    return
}

/* Testing code */
fun main() {
    val arr = intArrayOf(1, 2, 3, 4)
    permutation(arr, 0, 4)
    println()
    permutation2(arr, 0, 4)
    println()
    permutation3(arr, 0, 4)
}

/*
1 2 3 4 
1 2 4 3 
.......
4 1 3 2 
4 1 2 3 

2 4 1 3 
3 1 4 2 

2 4 1 3 
3 1 4 2 
*/