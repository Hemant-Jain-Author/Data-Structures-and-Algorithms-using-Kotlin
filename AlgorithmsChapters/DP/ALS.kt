fun fastestWayBU2(a: Array<IntArray>, t: Array<IntArray>, e: IntArray, x: IntArray, n: Int): Int {
    val f1 = IntArray(n)
    val f2 = IntArray(n)

    // Time taken to leave first station.
    f1[0] = e[0] + a[0][0]
    f2[0] = e[1] + a[1][0]

    // Fill the tables f1[] and f2[] using
    // bottom up approach.
    for (i in 1 until n) {
        f1[i] = Math.min(f1[i - 1] + a[0][i], f2[i - 1] + t[1][i - 1] + a[0][i])
        f2[i] = Math.min(f2[i - 1] + a[1][i], f1[i - 1] + t[0][i - 1] + a[1][i])
    }

    // Consider exit times and return minimum.
    return Math.min(f1[n - 1] + x[0], f2[n - 1] + x[1])
}

fun fastestWayBU(a: Array<IntArray>, t: Array<IntArray>, e: IntArray, x: IntArray, n: Int): Int {
    val f = Array(2) { IntArray(n) }

    // Time taken to leave first station.
    f[0][0] = e[0] + a[0][0]
    f[1][0] = e[1] + a[1][0]

    // Fill the tables f1[] and f2[] using
    // bottom up approach.
    for (i in 1 until n) {
        f[0][i] = Math.min(f[0][i - 1] + a[0][i], f[1][i - 1] + t[1][i - 1] + a[0][i])
        f[1][i] = Math.min(f[1][i - 1] + a[1][i], f[0][i - 1] + t[0][i - 1] + a[1][i])
    }

    // Consider exit times and return minimum.
    return Math.min(f[0][n - 1] + x[0], f[1][n - 1] + x[1])
}

fun fastestWayTD(a: Array<IntArray>, t: Array<IntArray>, e: IntArray, x: IntArray, n: Int): Int {
    val f = Array(2) { IntArray(n) }

    // Time taken to leave first station.
    f[0][0] = e[0] + a[0][0]
    f[1][0] = e[1] + a[1][0]
    fastestWayTD(f, a, t, n - 1)
    return Math.min(f[0][n - 1] + x[0], f[1][n - 1] + x[1])
}

fun fastestWayTD(f: Array<IntArray>, a: Array<IntArray>, t: Array<IntArray>, i: Int) {
    if (i == 0) return
    fastestWayTD(f, a, t, i - 1)
    // Fill the tables f1[] and f2[] using top-down approach.
    f[0][i] = Math.min(f[0][i - 1] + a[0][i], f[1][i - 1] + t[1][i - 1] + a[0][i])
    f[1][i] = Math.min(f[1][i - 1] + a[1][i], f[0][i - 1] + t[0][i - 1] + a[1][i])
}

// Driver code
fun main() {
    val a = arrayOf(intArrayOf(7, 9, 3, 4, 8, 4), intArrayOf(8, 5, 6, 4, 5, 7))
    val t = arrayOf(intArrayOf(2, 3, 1, 3, 4), intArrayOf(2, 1, 2, 2, 1))
    val e = intArrayOf(2, 4)
    val x = intArrayOf(3, 2)
    val n = 6
    println(fastestWayBU2(a, t, e, x, n))
    println(fastestWayBU(a, t, e, x, n))
    println(fastestWayTD(a, t, e, x, n))
}