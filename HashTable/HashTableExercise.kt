fun isAnagram(str1: CharArray, str2: CharArray): Boolean {
    val size1: Int = str1.size
    val size2: Int = str2.size
    if (size1 != size2) return false
    val hm: HashMap<Char, Int> = HashMap<Char, Int>()
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

//Testing code.
fun main1() {
    val first: CharArray = "hello".toCharArray()
    val second: CharArray = "elloh".toCharArray()
    val third: CharArray = "world".toCharArray()
    println("isAnagram : " + isAnagram(first, second))
    println("isAnagram : " + isAnagram(first, third))
}

/*
isAnagram : true
isAnagram : false
*/

fun removeDuplicate(str: CharArray): String {
    val hs: HashSet<Char> = HashSet<Char>()
    var out = String()
    for (ch in str) {
        if (hs.contains(ch) == false) {
            out += ch
            hs.add(ch)
        }
    }
    return out
}

//Testing code.
fun main2() {
    val first: CharArray = "hello".toCharArray()
    println(removeDuplicate(first))
}

/*
helo
*/
fun findMissing(arr: IntArray, start: Int, end: Int): Int {
    val hs: HashSet<Int> = HashSet<Int>()
    for (i in arr) {
        hs.add(i)
    }
    for (curr in start..end) {
        if (hs.contains(curr) == false) return curr
    }
    return Int.MAX_VALUE
}

//Testing code.
fun main3() {
    val arr = intArrayOf(1, 2, 3, 5, 6, 7, 8, 9, 10)
    println(findMissing(arr, 1, 10))
}

/*
4
*/

fun printRepeating(arr: IntArray) {
    val hs: HashSet<Int> = HashSet<Int>()
    print("Repeating elements are:")
    for (ele in arr) {
        if (hs.contains(ele)) print(" $ele") else hs.add(ele)
    }
}

//Testing code.
fun main4() {
    val arr1 = intArrayOf(1, 2, 3, 4, 4, 5, 6, 7, 8, 9, 1)
    printRepeating(arr1)
}

/*
Repeating elements are: 4 1
*/

fun printFirstRepeating(arr: IntArray) {
    val size: Int = arr.size
    val hs: HashSet<Int> = HashSet<Int>()
    var firstRepeating = Int.MAX_VALUE
    var i: Int = size - 1
    while (i >= 0) {
        if (hs.contains(arr[i])) {
            firstRepeating = arr[i]
        }
        hs.add(arr[i])
        i--
    }
    println("First Repeating number is : $firstRepeating")
}

//Testing code.
fun main5() {
    val arr1 = intArrayOf(1, 2, 3, 4, 4, 5, 6, 7, 8, 9, 1)
    printFirstRepeating(arr1)
}

/*
First Repeating number is:1
*/

fun hornerHash(key: CharArray, tableSize: Int): Int {
    val size: Int = key.size
    var h: Int = 0
    var i: Int = 0
    while (i < size) {
        h = (32 * h + key[i].code) % tableSize
        i++
    }
    return h
}

// Testing code
fun main() {
    main1()
    main2()
    main3()
    main4()
    main5()
}
