fun matchExpUtil(exp: CharArray, str: CharArray, i: Int, j: Int): Boolean {
    if (i == exp.size && j == str.size) {
        return true
    }
    if (i == exp.size && j != str.size || i != exp.size && j == str.size) {
        return false
    }
    if (exp[i] == '?' || exp[i] == str[j]) {
        return matchExpUtil(exp, str, i + 1, j + 1)
    }
    return if (exp[i] == '*') {
        (matchExpUtil(exp, str, i + 1, j) || matchExpUtil(exp, str, i, j + 1)
                || matchExpUtil(exp, str, i + 1, j + 1))
    } else false
}

fun matchExp(exp: String, str: String): Boolean {
    return matchExpUtil(exp.toCharArray(), str.toCharArray(), 0, 0)
}

fun main1() {
    println(matchExp("*llo,?World?", "Hello, World!"))
}

fun match(src: String, ptn: String): Boolean {
    val source = src.toCharArray()
    val pattern = ptn.toCharArray()
    var iSource = 0
    var iPattern = 0
    val sourceLen = source.size
    val patternLen = pattern.size
    iSource = 0
    while (iSource < sourceLen) {
        if (source[iSource] == pattern[iPattern]) {
            iPattern++
        }
        if (iPattern == patternLen) {
            return true
        }
        iSource++
    }
    return false
}

fun main2() {
    println(match("harrypottermustnotgotoschool", "pottergo"))
}

fun myStrdup(src: CharArray): CharArray {
    val index = 0
    val dst = CharArray(src.size)
    for (ch in src) {
        dst[index] = ch
    }
    return dst
}

fun isPrime(n: Int): Boolean {
    var answer = if (n > 1) true else false
    var i = 2
    while (i * i < n) {
        if (n % i == 0) {
            answer = false
            break
        }
        ++i
    }
    return answer
}

fun main3() {
    print("Prime numbers under 100 :: ")
    for (i in 0..99)
        if (isPrime(i))
            print("$i ")
    println()
}

fun myAtoi(str: String): Int {
    var value = 0
    val size = str.length
    for (i in 0 until size) {
        val ch = str[i]
        value = (value shl 3) + (value shl 1) + (ch - '0')
    }
    return value
}

fun main4() {
    println(myAtoi("1000"))
}

fun isUniqueChar(str: String): Boolean {
    val bitarr = IntArray(26)
    var index: Int
    for (i in 0..25) {
        bitarr[i] = 0
    }
    val size = str.length
    for (i in 0 until size) {
        val c = str[i]
        if ('A' <= c && 'Z' >= c) {
            index = c - 'A'
        } else if ('a' <= c && 'z' >= c) {
            index = c - 'a'
        } else {
            println("Unknown Char!\n")
            return false
        }
        if (bitarr[index] != 0) {
            println("Duplicate detected!")
            return false
        }
        bitarr[index] += 1
    }
    println("No duplicate detected!")
    return true
}

fun main5() {
    println(isUniqueChar("aple"))
    println(isUniqueChar("apple"))
}

fun ToUpper(s: Char): Char {
    var s = s
    if (s.toInt() >= 97 && s.toInt() <= 97 + 25) {
        s = (s.toInt() - 32).toChar()
    }
    return s
}

fun ToLower(s: Char): Char {
    var s = s
    if (s.toInt() >= 65 && s.toInt() <= 65 + 25) {
        s = (s.toInt() + 32).toChar()
    }
    return s
}

fun LowerUpper(s: Char): Char {
    var s = s
    if (s.toInt() >= 97 && s.toInt() <= 97 + 25) {
        s = (s.toInt() - 32).toChar()
    } else if (s.toInt() >= 65 && s.toInt() <= 65 + 25) {
        s = (s.toInt() + 32).toChar()
    }
    return s
}

fun main6() {
    println(ToLower('A'))
    println(ToUpper('a'))
    println(LowerUpper('s'))
    println(LowerUpper('S'))
}

fun isPermutation(s1: String, s2: String): Boolean {
    val count = IntArray(256)
    val length = s1.length
    if (s2.length != length) {
        println("is permutation return false\n")
        return false
    }
    for (i in 0..255) {
        count[i] = 0
    }
    for (i in 0 until length) {
        var ch = s1[i]
        count[ch.toInt()]++
        ch = s2[i]
        count[ch.toInt()]--
    }
    for (i in 0 until length) {
        if (count[i] != 0) {
            println("is permutation return false\n")
            return false
        }
    }
    println("is permutation return true\n")
    return true
}

fun main7() {
    println(isPermutation("apple", "plepa"))
}

fun isPalindrome(str: String): Boolean {
    var i = 0
    var j = str.length - 1
    while (i < j && str[i] == str[j]) {
        i++
        j--
    }
    if (i < j) {
        println("String is not a Palindrome")
        return false
    } else {
        println("String is a Palindrome")
        return true
    }
}

fun main8() {
    println(isPalindrome("hello"))
    println(isPalindrome("eoloe"))
}

fun pow(x: Int, n: Int): Int {
    val value: Int
    if (n == 0) {
        return 1
    } else if (n % 2 == 0) {
        value = pow(x, n / 2)
        return value * value
    } else {
        value = pow(x, n / 2)
        return x * value * value
    }
}

fun main9() {
    println(pow(5, 2))
}

fun myStrcmp(a: String, b: String): Int {
    var index = 0
    val len1 = a.length
    val len2 = b.length
    var minlen = len1
    if (len1 > len2) {
        minlen = len2
    }

    while (index < minlen && a[index] == b[index]) {
        index++
    }

    return if (index == len1 && index == len2) {
        0
    } else if (len1 == index) {
        -1
    } else if (len2 == index) {
        1
    } else {
        a[index] - b[index]
    }
}

fun main10() {
    println(myStrcmp("abs", "abs"))
}

fun reverseString(str: String): String {
    val a = str.toCharArray()
    reverseStringUtil(a)
    return a.joinToString("")
}

fun reverseStringUtil(a: CharArray) {
    var lower = 0
    var upper = a.size - 1
    var tempChar: Char
    while (lower < upper) {
        tempChar = a[lower]
        a[lower] = a[upper]
        a[upper] = tempChar
        lower++
        upper--
    }
}

fun reverseStringUtil(a: CharArray, lower: Int, upper: Int) {
    var lower = lower
    var upper = upper
    var tempChar: Char
    while (lower < upper) {
        tempChar = a[lower]
        a[lower] = a[upper]
        a[upper] = tempChar
        lower++
        upper--
    }
}

fun reverseWords(str: String): String {
    val a = str.toCharArray()
    val length = a.size
    var lower = 0
    var upper = -1
    for (i in 0..length) {
        if (i == length || a[i] == ' ') {
            reverseStringUtil(a, lower, upper)
            lower = i + 1
            upper = i
        } else {
            upper++
        }
    }
    reverseStringUtil(a, 0, length - 1)
    return a.joinToString("")
}

fun main11() {
    println(reverseString("apple"))
    println(reverseWords("hello world"))
}

fun printAnagram(str: String) {
    val a = str.toCharArray()
    val n = a.size
    printAnagram(a, n, n)
}

fun printAnagram(a: CharArray, max: Int, n: Int) {
    if (max == 1) {
        println(a)
    }
    var temp: Char
    for (i in -1 until max - 1) {
        if (i != -1) {
            temp = a[i]
            a[i] = a[max - 1]
            a[max - 1] = temp
        }
        printAnagram(a, max - 1, n)
        if (i != -1) {
            temp = a[i]
            a[i] = a[max - 1]
            a[max - 1] = temp
        }
    }
}

fun main12() {
    printAnagram("123")
}

fun shuffle(str: String) {
    val ar = str.toCharArray()
    val n = ar.size / 2
    var count = 0
    var k = 1
    var temp = '\u0000'
    var i = 1
    while (i < n) {
        temp = ar[i]
        k = i
        do {
            k = 2 * k % (2 * n - 1)
            //swap
            var temp2 = temp
            temp = ar[k]
            ar[k] = temp2

            count++
        } while (i != k)
        if (count == 2 * n - 2) {
            break
        }
        i = i + 2
    }
}

fun main13() {
    shuffle("ABCDE12345")
}

fun addBinary(firstStr: String, secondStr: String): CharArray {
    val first = firstStr.toCharArray()
    val second = secondStr.toCharArray()
    var size1 = first.size
    var size2 = second.size
    var totalIndex: Int
    val total: CharArray
    if (size1 > size2) {
        total = CharArray(size1 + 1)
        totalIndex = size1
    } else {
        total = CharArray(size2 + 1)
        totalIndex = size2
    }
    //total[totalIndex + 1] = '\u0000'
    var carry = 0
    size1--
    size2--
    while (size1 >= 0 || size2 >= 0) {
        val firstValue = if (size1 < 0) 0 else first[size1] - '0'
        val secondValue = if (size2 < 0) 0 else second[size2] - '0'
        var sum = firstValue + secondValue + carry
        carry = sum shr 1
        sum = sum and 1
        total[totalIndex] = if (sum == 0) '0' else '1'
        totalIndex--
        size1--
        size2--
    }
    total[totalIndex] = if (carry == 0) '0' else '1'
    return total
}

fun main14() {
    println(addBinary("1000", "11111111"))
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
}