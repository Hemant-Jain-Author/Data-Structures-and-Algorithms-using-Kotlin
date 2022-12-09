fun bruteForceSearch(txt: String, ptn: String): Int {
    val text: CharArray = txt.toCharArray()
    val pattern: CharArray = ptn.toCharArray()
    
    val n = text.size
    val m = pattern.size
    var i = 0
    while (i <= n - m) {
        var j = 0
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

fun robinKarp(txt: String, ptn: String): Int {
    val text: CharArray = txt.toCharArray()
    val pattern: CharArray = ptn.toCharArray()
    
    val n = text.size
    val m = pattern.size
    val prime = 101
    var powm = 1
    if (m == 0 || m > n) {
        return -1
    }

    var i = 0
    while (i < m - 1) {
        powm = (powm shl 1) % prime
        i++
    }

    var TextHash = 0
    var PatternHash = 0
    i = 0
    while (i < m) {
        PatternHash = ((PatternHash shl 1) + pattern[i].code) % prime
        TextHash = ((TextHash shl 1) + text[i].code) % prime
        i++
    }

    i = 0
    var j: Int
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
        TextHash = ((TextHash - text[i].code * powm shl 1) + text[i + m].code) % prime
        if (TextHash < 0) {
            TextHash = TextHash + prime
        }
        i++
    }
    return -1
}

private fun kmpPreprocess(pattern: CharArray, ShiftArr: IntArray) {
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

fun kmp(txt: String, ptn: String): Int {
    val text: CharArray = txt.toCharArray()
    val pattern: CharArray = ptn.toCharArray()

    val n = text.size
    val m = pattern.size
    val ShiftArr = IntArray(m + 1)
    kmpPreprocess(pattern, ShiftArr)
    var i = 0
    var j = 0
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

fun kmpFindCount(txt: String, ptn: String): Int {
    val text: CharArray = txt.toCharArray()
    val pattern: CharArray = ptn.toCharArray()
    val n = text.size
    val m = pattern.size
    val ShiftArr = IntArray(m + 1)
    kmpPreprocess(pattern, ShiftArr)
    var i = 0
    var j = 0
    var count = 0
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

fun main() {
    val st1 = "hello, world!"
    val st2 = "world"
    println("BruteForceSearch return : " + bruteForceSearch(st1, st2))
    println("RobinKarp return : " + robinKarp(st1, st2))
    println("KMP return : " + kmp(st1, st2))

    val st3 = "Only time will tell if we stand the test of time"
    println("Frequency of time is : " +  kmpFindCount(st3, "time"))
}

/*
BruteForceSearch return : 7
RobinKarp return : 7
KMP return : 7
Frequency of time is : 2
*/