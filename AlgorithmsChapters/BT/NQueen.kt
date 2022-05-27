fun print(Q: IntArray, n: Int) {
    for (i in 0 until n) {
        print(" " + Q[i])
    }
    println(" ")
}

fun feasible(Q: IntArray, k: Int): Boolean {
    for (i in 0 until k) {
        if (Q[k] == Q[i] || java.lang.Math.abs(Q[i] - Q[k]) == java.lang.Math.abs(i - k)) {
            return false
        }
    }
    return true
}

fun nQueens(Q: IntArray, k: Int, n: Int) {
    if (k == n) {
        print(Q, n)
        return
    }
    for (i in 0 until n) {
        Q[k] = i
        if (feasible(Q, k)) {
            nQueens(Q, k + 1, n)
        }
    }
}

fun main() {
    val Q = IntArray(8)
    nQueens(Q, 0, 8)
}
