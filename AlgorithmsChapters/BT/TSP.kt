// Function to find the minimum weight Hamiltonian Cycle
fun tspUtil( graph: Array<IntArray>, n: Int, path: IntArray, pSize: Int,
    pCost: Int, visited: BooleanArray, ansIn: Int, ansPath: IntArray): Int {
    var ans = ansIn
    if (pCost > ans) return ans
    val curr = path[pSize - 1]
    if (pSize == n) {
        if (graph[curr][0] > 0 && ans > pCost + graph[curr][0]) {
            ans = pCost + graph[curr][0]
            for (i in 0..n) ansPath[i] = path[i % n]
        }
        return ans
    }
    for (i in 0 until n) {
        if (visited[i] == false && graph[curr][i] > 0) {
            visited[i] = true
            path[pSize] = i
            ans = tspUtil(graph, n, path, pSize + 1, pCost + graph[curr][i], visited, ans, ansPath)
            visited[i] = false
        }
    }
    return ans
}

fun tsp(graph: Array<IntArray>, n: Int): Int {
    val visited = BooleanArray(n)
    val path = IntArray(n)
    val ansPath = IntArray(n + 1)
    path[0] = 0
    visited[0] = true
    var ans = Int.MAX_VALUE
    ans = tspUtil(graph, n, path, 1, 0, visited, ans, ansPath)
    println("Path length : $ans")
    print("Path : ")
    for (i in 0..n) print(ansPath[i].toString() + " ")
    return ans
}

// Testing code.
fun main() {
    val n = 4
    val graph = arrayOf(
        intArrayOf(0, 10, 15, 20),
        intArrayOf(10, 0, 35, 25),
        intArrayOf(15, 35, 0, 30),
        intArrayOf(20, 25, 30, 0))
    tsp(graph, n)
}