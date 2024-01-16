import java.util.Arrays

fun maxValueJobUtil(arr: Array<Job?>, n: Int): Int {
    // Base case
    if (n == 1) return arr[0]!!.value

    // Find Value when current job is included
    var incl = arr[n - 1]!!.value
    for (j in n - 1 downTo 0) {
        if (arr[j]!!.stop <= arr[n - 1]!!.start) {
            incl += maxValueJobUtil(arr, j + 1)
            break
        }
    }

    // Find Value when current job is excluded
    val excl = maxValueJobUtil(arr, n - 1)
    return Math.max(incl, excl)
}

fun maxValueJobs(s: IntArray, f: IntArray, v: IntArray, n: Int): Int {
    val act = arrayOfNulls<Job>(n)
    for (i in 0 until n) act[i] = Job(s[i], f[i], v[i])
    Arrays.sort(act) // sort according to finish time.
    return maxValueJobUtil(act, n)
}

fun maxValueJobUtilTD(dp: IntArray, arr: Array<Job?>, n: Int): Int {
    // Base case
    if (n == 0) return 0
    if (dp[n - 1] != 0) {
        return dp[n - 1]
    }

    // Find Value when current job is included
    var incl = arr[n - 1]!!.value
    for (j in n - 2 downTo 0) {
        if (arr[j]!!.stop <= arr[n - 1]!!.start) {
            incl += maxValueJobUtilTD(dp, arr, j + 1)
            break
        }
    }

    // Find Value when current job is excluded
    val excl = maxValueJobUtilTD(dp, arr, n - 1)
    dp[n - 1] = Math.max(incl, excl)
    return dp[n - 1]
}

fun maxValueJobsTD(s: IntArray, f: IntArray, v: IntArray, n: Int): Int {
    val act = arrayOfNulls<Job>(n)
    for (i in 0 until n) act[i] = Job(s[i], f[i], v[i])
    Arrays.sort(act) // sort according to finish time.
    val dp = IntArray(n)
    return maxValueJobUtilTD(dp, act, n)
}

fun maxValueJobsBU(s: IntArray, f: IntArray, v: IntArray, n: Int): Int {
    val act = arrayOfNulls<Job>(n)
    for (i in 0 until n) act[i] = Job(s[i], f[i], v[i])
    Arrays.sort(act) // sort according to finish time.
    val dp = IntArray(n)
    dp[0] = act[0]!!.value
    for (i in 1 until n) {
        var incl = act[i]!!.value
        for (j in i - 1 downTo 0) {
            if (act[j]!!.stop <= act[i]!!.start) {
                incl += dp[j]
                break
            }
        }
        dp[i] = Math.max(incl, dp[i - 1])
    }
    return dp[n - 1]
}

class Job internal constructor(var start: Int, var stop: Int, var value: Int) : Comparable<Job> {
    override operator fun compareTo(other: Job): Int {
        return stop - other.stop
    }
}

// Testing code.
fun main() {
    val start = intArrayOf(1, 5, 0, 3, 5, 6, 8)
    val finish = intArrayOf(2, 6, 5, 4, 9, 7, 9)
    val value = intArrayOf(2, 2, 4, 3, 10, 2, 8)
    val n = start.size
    println(maxValueJobs(start, finish, value, n))
    println(maxValueJobsTD(start, finish, value, n))
    println(maxValueJobsBU(start, finish, value, n))
}

/*
17
17
17
*/
