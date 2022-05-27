import java.util.Arrays



fun closestPairBF(arr: Array<IntArray>): Double {
    val n = arr.size
    var dmin = Double.MAX_VALUE
    var d: Double
    for (i in 0 until n - 1) {
        for (j in i + 1 until n) {
            d = Math.sqrt(
                ((arr[i][0] - arr[j][0]) * (arr[i][0] - arr[j][0])
                        + (arr[i][1] - arr[j][1]) * (arr[i][1] - arr[j][1])).toDouble()
            )
            if (d < dmin) {
                dmin = d
            }
        }
    }
    return dmin
}

class Point internal constructor(var x: Int = 0, var y: Int = 0)


internal class xComp : Comparator<Point> {
    public override fun compare(s1: Point, s2: Point): Int {
        return s1.x - s2.x
    }
}

internal class yComp : Comparator<Point> {
    public override fun compare(s1: Point, s2: Point): Int {
        return s1.y - s2.y
    }
}

private fun closestPairUtil(p: Array<Point>, start: Int, stop: Int, q: Array<Point>, n: Int): Double {
    if (stop - start < 1) return Double.MAX_VALUE
    if (stop - start == 1) return distance(p[start], p[stop])

    // Find the middle point
    val mid = (start + stop) / 2
    val dl = closestPairUtil(p, start, mid, q, n)
    val dr = closestPairUtil(p, mid + 1, stop, q, n)
    val d: Double = Math.min(dl, dr)

    // Build an array strip[] that contains points whose x axis coordinate
    // in the range p[mid]-d and p[mid]+d
    // Points are already sorted according to y axis.
    val strip = Array(n){Point(0, 0)}
    var j = 0
    for (i in 0 until n) {
        if (Math.abs(q[i].x - p[mid].x) < d) {
            strip[j].x = q[i].x
            strip[j].y = q[i].y
            j++
        }
    }
    // Find the closest points in strip and compare with d.
    return Math.min(d, stripMin(strip, j, d))
}

fun closestPairDC(arr: Array<IntArray>): Double {
    val n = arr.size
    val p = Array(n){Point(0, 0)}
    for (i in 0 until n) {
        p[i].x = arr[i][0]
        p[i].y = arr[i][1]
    }
    // Sort according to x axis.
    Arrays.sort<Point>(p, xComp())
    val q: Array<Point> = p.clone()
    // Sort according to y axis.
    Arrays.sort<Point>(q, yComp())
    return closestPairUtil(p, 0, n - 1, q, n)
}

private fun distance(a: Point, b: Point): Double {
    return Math.sqrt(((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y)).toDouble())
}

private fun stripMin(q: Array<Point>, n: Int, d: Double): Double {
    var min = d

    // Find the distance between all the points in the strip. 
    // Array q is sorted according to the y axis coordinate.
    // The inner loop will run at most 6 times for each point.
    for (i in 0 until n) {
        var j: Int = i + 1
        while (j < n && q[j].y - q[i].y < min) {
            var dst = distance(q[i], q[j])
            if (dst < min) 
                min = dst
            ++j
        }
    }
    return min
}

fun main() {
    val arr = arrayOf(
        intArrayOf(648, 896),
        intArrayOf(269, 879),
        intArrayOf(250, 922),
        intArrayOf(453, 347),
        intArrayOf(213, 17)
    )
    println("Smallest distance is:" + closestPairBF(arr))
    println("Smallest distance is:" + closestPairDC(arr))
}
