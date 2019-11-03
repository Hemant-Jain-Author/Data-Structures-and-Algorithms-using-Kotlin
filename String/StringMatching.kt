fun bruteForceSearch(text: String, pattern: String): Int {
    return bruteForceSearch(text.toCharArray(), pattern.toCharArray())
}

fun bruteForceSearch(text: CharArray, pattern: CharArray): Int {
    var i = 0
    var j = 0
    val n = text.size
    val m = pattern.size
    while (i <= n - m) {
        j = 0
        while (j < m && pattern[j] == text[i + j]) {
            j++
        }
        if (j == m) {
            return i
        }
        i++
    }
    return -1
}

fun robinKarp(text: String, pattern: String): Int {
    return robinKarp(text.toCharArray(), pattern.toCharArray())
}

fun robinKarp(text: CharArray, pattern: CharArray): Int {
    val n = text.size
    val m = pattern.size
    var i: Int
    var j: Int
    val prime = 101
    var powm = 1
    var TextHash = 0
    var PatternHash = 0
    if (m == 0 || m > n) {
        return -1
    }

    i = 0
    while (i < m - 1) {
        powm = (powm shl 1) % prime
        i++
    }

    i = 0
    while (i < m) {
        PatternHash = ((PatternHash shl 1) + pattern[i].toInt()) % prime
        TextHash = ((TextHash shl 1) + text[i].toInt()) % prime
        i++
    }

    i = 0
    while (i <= n - m) {
        if (TextHash == PatternHash) {
            j = 0
            while (j < m) {
                if (text[i + j] != pattern[j]) {
                    break
                }
                j++
            }
            if (j == m)
                return i
        }
        TextHash = ((TextHash - text[i].toInt() * powm shl 1) + text[i + m].toInt()) % prime
        if (TextHash < 0) {
            TextHash = TextHash + prime
        }
        i++
    }
    return -1
}

fun KMPPreprocess(pattern: CharArray, ShiftArr: IntArray) {
    val m = pattern.size
    var i = 0
    var j = -1
    ShiftArr[i] = -1
    while (i < m) {
        while (j >= 0 && pattern[i] != pattern[j]) {
            j = ShiftArr[j]
        }
        i++
        j++
        ShiftArr[i] = j
    }
}

fun KMP(text: String, pattern: String): Int {
    return KMP(text.toCharArray(), pattern.toCharArray())
}

fun KMP(text: CharArray, pattern: CharArray): Int {
    var i = 0
    var j = 0
    val n = text.size
    val m = pattern.size
    val ShiftArr = IntArray(m + 1)
    KMPPreprocess(pattern, ShiftArr)
    while (i < n) {
        while (j >= 0 && text[i] != pattern[j])
            j = ShiftArr[j]
        i++
        j++
        if (j == m) {
            return i - m
        }
    }
    return -1
}

fun KMPFindCount(text: CharArray, pattern: CharArray): Int {
    var i = 0
    var j = 0
    var count = 0
    val n = text.size
    val m = pattern.size
    val ShiftArr = IntArray(m + 1)
    KMPPreprocess(pattern, ShiftArr)
    while (i < n) {
        while (j >= 0 && text[i] != pattern[j]) {
            j = ShiftArr[j]
        }
        i++
        j++
        if (j == m) {
            count++
            j = ShiftArr[j]
        }
    }
    return count
}

fun main(args: Array<String>) {
    val st1 = "hello, world!"
    val st2 = "world"
    println("BruteForceSearch return : " + bruteForceSearch(st1, st2))
    println("RobinKarp return : " + robinKarp(st1, st2))
    println("KMP return : " + KMP(st1, st2))
}