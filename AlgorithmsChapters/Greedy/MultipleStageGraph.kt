var INF = Int.MAX_VALUE

// Returns shortest distance from 0 to N-1.
fun shortestDist(graph: Array<IntArray>, n: Int): Int {
    // dist[i] is going to store shortest
    // distance from node i to node n-1.
    val dist = IntArray(n){INF}
    val path = IntArray(n)
    var value: Int
    dist[0] = 0
    path[0] = -1

    // Calculating shortest path for the nodes
    for (i in 0 until n) {
        // Check all nodes of next
        for (j in i until n) {
            // Reject if no edge exists
            if (graph[i][j] == INF) continue
            value = graph[i][j] + dist[i]
            if (dist[j] > value) {
                dist[j] = value
                path[j] = i
            }
        }
    }
    value = n - 1
    while (value != -1) {
        print(" $value")
        value = path[value]
    }
    println()
    return dist[n - 1]
}

// Testing code.
fun main() {
    // Graph stored in the form of an
    // adjacency Matrix
    val graph = arrayOf(
        intArrayOf(INF, 1, 2, 5, INF, INF, INF, INF),
        intArrayOf(INF, INF, INF, INF, 4, 11, INF, INF),
        intArrayOf(INF, INF, INF, INF, 9, 5, 16, INF),
        intArrayOf(INF, INF, INF, INF, INF, INF, 2, INF),
        intArrayOf(INF, INF, INF, INF, INF, INF, INF, 18),
        intArrayOf(INF, INF, INF, INF, INF, INF, INF, 13),
        intArrayOf(INF, INF, INF, INF, INF, INF, INF, 2),
        intArrayOf(INF, INF, INF, INF, INF, INF, INF, INF)
    )
    println(shortestDist(graph, 8))
}

/*
 7 6 3 0
9
 */