import java.util.HashMap
import java.util.HashSet

fun main(args: Array<String>) {
    val first = "hello".toCharArray()
    val second = "elloh".toCharArray()
    val third = "world".toCharArray()

    println("isAnagram : " + isAnagram(first, second))
    println("isAnagram : " + isAnagram(first, third))

    println(removeDuplicate(first))

    val arr = intArrayOf(1, 2, 3, 5, 6, 7, 8, 9, 10)
    println(findMissing(arr, 1, 10))

    val arr1 = intArrayOf(1, 2, 3, 4, 4, 5, 6, 7, 8, 9, 1)
    printRepeating(arr1)
    printFirstRepeating(arr1)
}

fun isAnagram(str1: CharArray, str2: CharArray): Boolean {
    val size1 = str1.size
    val size2 = str2.size

    if (size1 != size2)
        return false

    val hm = HashMap<Char, Int>()

    for (ch in str1) {
        hm.put(ch, hm.getOrDefault(ch, 0) + 1)
    }

    for (ch in str2) {
        val value = hm.getOrDefault(ch, 0)
        if (value == 0)
            return false
        else
            hm.put(ch, value - 1)
    }

    return true
}

fun removeDuplicate(str: CharArray): String {
    val hs = HashSet<Char>()
    var out = String()

    for (ch in str) {
        if (hs.contains(ch) == false) {
            out += ch
            hs.add(ch)
        }
    }
    return out
}

fun findMissing(arr: IntArray, start: Int, end: Int): Int {
    val hs = HashSet<Int>()
    for (i in arr) {
        hs.add(i)
    }

    for (curr in start..end) {
        if (hs.contains(curr) == false)
            return curr
    }
    return Integer.MAX_VALUE
}

fun printRepeating(arr: IntArray) {
    val hs = HashSet<Int>()

    print("Repeating elements are:")
    for (`val` in arr) {
        if (hs.contains(`val`))
            print(" $`val`")
        else
            hs.add(`val`)
    }
    println()
}

fun printFirstRepeating(arr: IntArray) {
    var i: Int
    val size = arr.size
    val hs = HashSet<Int>()
    var firstRepeating = Integer.MAX_VALUE

    i = size - 1
    while (i >= 0) {
        if (hs.contains(arr[i])) {
            firstRepeating = arr[i]
        }
        hs.add(arr[i])
        i--
    }
    println("First Repeating number is : $firstRepeating")
}

fun hornerHash(key: CharArray, tableSize: Int): Int {
    val size = key.size
    var h = 0
    var i: Int
    i = 0
    while (i < size) {
        h = (32 * h + key[i].toInt()) % tableSize
        i++
    }
    return h
}
