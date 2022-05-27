fun matchExp(exp: String, str: String): Boolean {
    return matchExpUtil(exp.toCharArray(), str.toCharArray(), 0, 0)
}

fun matchExpUtil(exp: CharArray, str: CharArray, m: Int, n: Int): Boolean {
    if (m == exp.size && (n == str.size || exp[m - 1] == '*')) {
        return true
    }
    if ((m == exp.size && n != str.size || m != exp.size) && n == str.size) {
        return false
    }
    if (exp[m] == '?' || exp[m] == str[n]) {
        return matchExpUtil(exp, str, m + 1, n + 1)
    }
    if (exp[m] == '*') {
        return matchExpUtil(exp, str, m + 1, n) || matchExpUtil(exp, str, m, n + 1)
    } 
    return false
}
 
fun matchExpDP(exp: String, str: String): Boolean {
    return matchExpUtilDP(exp.toCharArray(), str.toCharArray(), exp.length, str.length)
}

fun matchExpUtilDP(exp: CharArray, str: CharArray, m: Int, n: Int): Boolean {
    val lookup = Array(m + 1) {
        BooleanArray(
            n + 1
        )
    }
    lookup[0][0] = true // empty exp and empty str match.

    // 0 row will remain all false. empty exp can't match any str.
    // '*' can match with empty string, column 0 update.
    for (i in 1..m) {
        if (exp[i - 1] == '*') lookup[i][0] = lookup[i - 1][0] else break
    }

    // Fill the table in bottom-up fashion
    for (i in 1..m) {
        for (j in 1..n) {
            // If we see a '*' in pattern:
            // 1) We ignore '*' character and consider next character in the pattern.
            // 2) We ignore one character in the input str and consider next character.
            if (exp[i - 1] == '*') {
                lookup[i][j] = lookup[i - 1][j] || lookup[i][j - 1]
            } else if (exp[i - 1] == '?' || str[j - 1] == exp[i - 1]) {
                lookup[i][j] = lookup[i - 1][j - 1]
            } else {
                lookup[i][j] = false
            }
        }
    }
    return lookup[m][n]
}

// Testing code.
fun main() {
    println("matchExp :: " + matchExp("*llo,?World?", "Hello, World!"))
    println("matchExp :: " + matchExpDP("*llo,?World?", "Hello, World!"))
}


