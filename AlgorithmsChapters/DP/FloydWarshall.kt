const val INF = Int.MAX_VALUE

fun floydWarshall(graph: Array<IntArray>, V: Int) {
    val dist = Array(V) { IntArray(V) }
    for (i in 0 until V) for (j in 0 until V) dist[i][j] = graph[i][j]

    for (k in 0 until V) { // Pick intermediate vertices.
        for (i in 0 until V) { // Pick source vertices one by one.
            for (j in 0 until V) { // Pick destination vertices.
                // If we have shorter path from i to j via k.
                // then update dist[i][j]
                if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j]
                }
            }
        }
    }
    printSolution(dist, V) // Print the shortest distance matrix
}

fun printSolution(dist: Array<IntArray>, V: Int) {
    for (i in 0 until V) {
        for (j in 0 until V) {
            if (dist[i][j] == INF) print("INF ") else print(dist[i][j].toString() + " ")
        }
        println()
    }
}

// Testing code.
fun main() {
    val graph = arrayOf(
        intArrayOf(0, 2, 4, INF, INF, INF, INF),
        intArrayOf(2, 0, 4, 1, INF, INF, INF),
        intArrayOf(4, 4, 0, 2, 8, 4, INF),
        intArrayOf(INF, 1, 2, 0, 3, INF, 6 ),
        intArrayOf(INF, INF, 6, 4, 0, 3, 1),
        intArrayOf(INF, INF, 4, INF, 4, 0, 2),
        intArrayOf(INF, INF, INF, 4, 2, 3, 0))
    floydWarshall(graph, 7)
}

/* 
0 2 4 3 6 8 7 
2 0 3 1 4 7 5 
4 3 0 2 5 4 6 
3 1 2 0 3 6 4 
7 5 6 4 0 3 1 
8 7 4 6 4 0 2 
7 5 6 4 2 3 0 
*/