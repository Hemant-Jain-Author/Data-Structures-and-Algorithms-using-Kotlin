// Prints a maximum set of activities that can be done by a 
// single person performing one task at a time.
// s[] is an array that contains start time of all activities
// f[] is an array that contains finish time of all activities
import java.util.Arrays

class Activity(var start: Int, var stop: Int) : Comparable<Activity> {
    override operator fun compareTo(other: Activity): Int {
        return stop - other.stop
    }
}

fun maxActivities(s: IntArray, f: IntArray, n: Int) {
    val act = arrayOfNulls<Activity>(n)
    for (i in 0 until n) act[i] = Activity(s[i], f[i])
    Arrays.sort(act) // sort according to finish time.
    var i = 0 // The first activity at index 0 is always gets selected.
    print("Activities are : (" + act[i]!!.start + "," + act[i]!!.stop + ")")
    for (j in 1 until n) {
        // Find next activity whose start time is greater than or equal
        // to the finish time of previous activity.
        if (act[j]!!.start >= act[i]!!.stop) {
            print(", (" + act[j]!!.start + "," + act[j]!!.stop + ")")
            i = j
        }
    }
}

// Testing code.
fun main() {
    val s = intArrayOf(1, 5, 0, 3, 5, 6, 8)
    val f = intArrayOf(2, 6, 5, 4, 9, 7, 9)
    val n = s.size
    maxActivities(s, f, n)
}
