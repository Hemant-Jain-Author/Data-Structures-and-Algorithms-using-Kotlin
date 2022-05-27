import java.util.Arrays


class JobSequencing(ids: CharArray, deadlines: IntArray, profits: IntArray, n: Int) {
    var jobs: Array<Job>
    var n: Int
    var maxDL: Int

    class Job(var id: Char, var deadline: Int, var profit: Int)

    init {
        jobs = Array(n){Job('a', 1, 1)}
        this.n = n
        for (i in 0 until n) {
            jobs[i] = Job(ids[i], deadlines[i], profits[i])
        }
        maxDL = deadlines[0]
        for (i in 1 until n) {
            if (deadlines[i] > maxDL) maxDL = deadlines[i]
        }
    }

    fun print() {
        Arrays.sort<Job>(
            jobs,
            Comparator<Job> { a: Job, b: Job -> b.profit - a.profit })
        val result = BooleanArray(maxDL)
        val job = CharArray(maxDL)
        var profit = 0

        // Iterate through all given jobs
        for (i in 0 until n) {
            for (j in jobs[i].deadline - 1 downTo 0) {
                if (result[j] == false) {
                    result[j] = true
                    job[j] = jobs[i].id
                    profit += jobs[i].profit
                    break
                }
            }
        }
        println("Profit is :: $profit")
        print("Jobs selected are::")
        for (i in 0 until maxDL) if (job[i] != '\u0000') print(" " + job[i])
    }
}

// Testing code.
fun main() {
    val id = charArrayOf('a', 'b', 'c', 'd', 'e')
    val deadline = intArrayOf(3, 1, 2, 4, 4)
    val profit = intArrayOf(50, 40, 27, 31, 30)
    val js = JobSequencing(id, deadline, profit, 5)
    js.print()
}
