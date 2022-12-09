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

/*
0 4 7 5 2 6 1 3 
0 5 7 2 6 3 1 4 
......
7 2 0 5 1 4 6 3 
7 3 0 2 5 1 6 4  
*/