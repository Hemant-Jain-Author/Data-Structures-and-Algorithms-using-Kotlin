import java.util.Comparator
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Stack
import java.util.Arrays

class GraphAM internal constructor(var count: Int) {
    var adj: Array<IntArray>

    init {
        adj = Array(count) { IntArray(count) }
    }

    fun addDirectedEdge(src: Int, dst: Int, cost: Int = 1) {
        adj[src][dst] = cost
    }

    fun addUndirectedEdge(src: Int, dst: Int, cost: Int = 1) {
        addDirectedEdge(src, dst, cost)
        addDirectedEdge(dst, src, cost)
    }

    fun print() {
        for (i in 0 until count) {
            print("Vertex $i is connected to : ")
            for (j in 0 until count) {
                if (adj[i][j] != 0) print(j.toString() + "(cost: " + adj[i][j] + ") ")
            }
            println()
        }
    }

    /*
Vertex 0 is connected to : 1(cost: 1) 2(cost: 1) 
Vertex 1 is connected to : 0(cost: 1) 2(cost: 1) 
Vertex 2 is connected to : 0(cost: 1) 1(cost: 1) 3(cost: 1) 
Vertex 3 is connected to : 2(cost: 1) 
*/
    private class Edge(var src: Int, var dest: Int, var cost: Int) : Comparable<Edge> {
        override operator fun compareTo(other: Edge): Int {
            return cost - other.cost
        }
    }

    fun dijkstra(source: Int) {
        val previous = IntArray(count){-1}
        val dist = IntArray(count){Int.MAX_VALUE}
        val visited = BooleanArray(count)
        dist[source] = 0
        previous[source] = source
        val queue: PriorityQueue<Edge> = PriorityQueue<Edge>(100)
        var node = Edge(source, source, 0)
        queue.add(node)
        while (queue.isEmpty() != true) {
            node = queue.peek()
            queue.remove()
            val src = node.dest
            visited[src] = true
            for (dest in 0 until count) {
                val cost = adj[src][dest]
                if (cost != 0) {
                    val alt = cost + dist[src]
                    if (dist[dest] > alt && visited[dest] == false) {
                        dist[dest] = alt
                        previous[dest] = src
                        node = Edge(src, dest, alt)
                        queue.add(node)
                    }
                }
            }
        }
        printPath(previous, dist, count, source)
    }

    fun printPathUtil(previous: IntArray, source: Int, dest: Int): String {
        var path = ""
        if (dest == source) path += source else {
            path += printPathUtil(previous, source, previous[dest])
            path += "->$dest"
        }
        return path
    }

    fun printPath(previous: IntArray, dist: IntArray, count: Int, source: Int) {
        var output = "Shortest Paths: "
        for (i in 0 until count) {
            if (dist[i] == 99999) output += "($source->$i @ Unreachable) " else if (i != previous[i]) {
                output += "("
                output += printPathUtil(previous, source, i)
                output += " @ " + dist[i] + ") "
            }
        }
        println(output)
    }

    fun primsMST() {
        val previous = IntArray(count){-1}
        val dist = IntArray(count){Int.MAX_VALUE}
        val visited = BooleanArray(count)
        var source = 0
        dist[source] = 0
        previous[source] = source
        val queue: PriorityQueue<Edge> = PriorityQueue<Edge>(100)
        var node = Edge(source, source, 0)
        queue.add(node)
        while (queue.isEmpty() != true) {
            node = queue.peek()
            queue.remove()
            source = node.dest
            visited[source] = true
            for (dest in 0 until count) {
                val cost = adj[source][dest]
                if (cost != 0) {
                    if (dist[dest] > cost && visited[dest] == false) {
                        dist[dest] = cost
                        previous[dest] = source
                        node = Edge(source, dest, cost)
                        queue.add(node)
                    }
                }
            }
        }

        // printing result.
        var sum = 0
        var isMst = true
        var output = "Edges are "
        for (i in 0 until count) {
            if (dist[i] == Int.MAX_VALUE) {
                output += "($i, Unreachable) "
                isMst = false
            } else if (previous[i] != i) {
                output += "(" + previous[i] + "->" + i + " @ " + dist[i] + ") "
                sum += dist[i]
            }
        }
        if (isMst) {
            println(output)
            println("Total MST cost: $sum")
        } else println("Can't get a Spanning Tree")
    }

    /*
Shortest Paths: (0->2->1 @ 6) (0->2 @ 1) (0->2->3 @ 3) (0->2->3->5->4 @ 13) (0->2->3->5 @ 7) (0->2->3->5->7->6 @ 15) (0->2->3->5->7 @ 8) (0->2->3->5->7->8 @ 25) 
*/
    fun hamiltonianPathUtil(path: IntArray, pSize: Int, added: IntArray): Boolean {
        // Base case full length path is found
        if (pSize == count) {
            return true
        }
        for (vertex in 0 until count) {
            // There is an edge from last element of path and next vertex
            // and the next vertex is not already included in the path.
            if ((pSize == 0 || adj[path[pSize - 1]][vertex] == 1) && added[vertex] == 0) {
                path[pSize] = vertex
                added[vertex] = 1
                if (hamiltonianPathUtil(path, pSize + 1, added)) return true
                // backtracking
                added[vertex] = 0
            }
        }
        return false
    }

    fun hamiltonianPath(): Boolean {
        val path = IntArray(count)
        val added = IntArray(count)
        if (hamiltonianPathUtil(path, 0, added)) {
            print("Hamiltonian Path found :: ")
            for (i in 0 until count) print(" " + path[i])
            println("")
            return true
        }
        println("Hamiltonian Path not found")
        return false
    }

    /*
Hamiltonian Path found ::  0 1 2 4 3
hamiltonianPath : true

Hamiltonian Path found ::  0 3 1 2 4
hamiltonianPath :  true
*/
    fun hamiltonianCycleUtil(path: IntArray, pSize: Int, added: IntArray): Boolean {
        // Base case full length path is found
        // this last check can be modified to make it a path.
        if (pSize == count) {
            return if (adj[path[pSize - 1]][path[0]] == 1) {
                path[pSize] = path[0]
                true
            } else false
        }
        for (vertex in 0 until count) {
            // there is a path from last element and next vertex
            if (pSize == 0 || adj[path[pSize - 1]][vertex] == 1 && added[vertex] == 0) {
                path[pSize] = vertex
                added[vertex] = 1
                if (hamiltonianCycleUtil(path, pSize + 1, added)) return true
                // backtracking
                added[vertex] = 0
            }
        }
        return false
    }

    fun hamiltonianCycle(): Boolean {
        val path = IntArray(count + 1)
        val added = IntArray(count)
        if (hamiltonianCycleUtil(path, 0, added)) {
            print("Hamiltonian Cycle found :: ")
            for (i in 0..count) print(" " + path[i])
            println("")
            return true
        }
        println("Hamiltonian Cycle not found")
        return false
    }
}


// Testing code.
fun main1() {
    val gph = GraphAM(4)
    gph.addUndirectedEdge(0, 1)
    gph.addUndirectedEdge(0, 2)
    gph.addUndirectedEdge(1, 2)
    gph.addUndirectedEdge(2, 3)
    gph.print()
}

// Testing code.
fun main2() {
    val gph = GraphAM(9)
    gph.addUndirectedEdge(0, 1, 4)
    gph.addUndirectedEdge(0, 7, 8)
    gph.addUndirectedEdge(1, 2, 8)
    gph.addUndirectedEdge(1, 7, 11)
    gph.addUndirectedEdge(2, 3, 7)
    gph.addUndirectedEdge(2, 8, 2)
    gph.addUndirectedEdge(2, 5, 4)
    gph.addUndirectedEdge(3, 4, 9)
    gph.addUndirectedEdge(3, 5, 14)
    gph.addUndirectedEdge(4, 5, 10)
    gph.addUndirectedEdge(5, 6, 2)
    gph.addUndirectedEdge(6, 7, 1)
    gph.addUndirectedEdge(6, 8, 6)
    gph.addUndirectedEdge(7, 8, 7)
    gph.primsMST()
}

/*
Edges are (0->1 @ 4) (5->2 @ 4) (2->3 @ 7) (3->4 @ 9) (6->5 @ 2) (7->6 @ 1) (0->7 @ 8) (2->8 @ 2) 
Total MST cost: 37
*/
// Testing code.
fun main3() {
    val gph = GraphAM(9)
    gph.addUndirectedEdge(0, 2, 1)
    gph.addUndirectedEdge(1, 2, 5)
    gph.addUndirectedEdge(1, 3, 7)
    gph.addUndirectedEdge(1, 4, 9)
    gph.addUndirectedEdge(3, 2, 2)
    gph.addUndirectedEdge(3, 5, 4)
    gph.addUndirectedEdge(4, 5, 6)
    gph.addUndirectedEdge(4, 6, 3)
    gph.addUndirectedEdge(5, 7, 1)
    gph.addUndirectedEdge(6, 7, 7)
    gph.addUndirectedEdge(7, 8, 17)
    gph.dijkstra(0)
}

// Testing code.
fun main4() {
    val count = 5
    val gph = GraphAM(count)
    val adj = arrayOf(
        intArrayOf(0, 1, 0, 1, 0),
        intArrayOf(1, 0, 1, 1, 0),
        intArrayOf(0, 1, 0, 0, 1),
        intArrayOf(1, 1, 0, 0, 1),
        intArrayOf(0, 1, 1, 1, 0)
    )
    for (i in 0 until count) for (j in 0 until count) if (adj[i][j] == 1) gph.addDirectedEdge(i, j, 1)
    println("hamiltonianPath : " + gph.hamiltonianPath())
    val gph2 = GraphAM(count)
    val adj2 = arrayOf(
        intArrayOf(0, 1, 0, 1, 0),
        intArrayOf(1, 0, 1, 1, 0),
        intArrayOf(0, 1, 0, 0, 1),
        intArrayOf(1, 1, 0, 0, 0),
        intArrayOf(0, 1, 1, 0, 0)
    )
    for (i in 0 until count) for (j in 0 until count) if (adj2[i][j] == 1) gph2.addDirectedEdge(i, j, 1)
    println("hamiltonianPath :  " + gph2.hamiltonianPath())
}

// Testing code.
fun main5() {
    val count = 5
    val gph = GraphAM(count)
    val adj = arrayOf(
        intArrayOf(0, 1, 0, 1, 0),
        intArrayOf(1, 0, 1, 1, 0),
        intArrayOf(0, 1, 0, 0, 1),
        intArrayOf(1, 1, 0, 0, 1),
        intArrayOf(0, 1, 1, 1, 0)
    )
    for (i in 0 until count) for (j in 0 until count) if (adj[i][j] == 1) gph.addDirectedEdge(i, j, 1)
    println("hamiltonianCycle : " + gph.hamiltonianCycle())
    val gph2 = GraphAM(count)
    val adj2 = arrayOf(
        intArrayOf(0, 1, 0, 1, 0),
        intArrayOf(1, 0, 1, 1, 0),
        intArrayOf(0, 1, 0, 0, 1),
        intArrayOf(1, 1, 0, 0, 0),
        intArrayOf(0, 1, 1, 0, 0)
    )
    for (i in 0 until count) for (j in 0 until count) if (adj2[i][j] == 1) gph2.addDirectedEdge(i, j, 1)
    println("hamiltonianCycle :  " + gph2.hamiltonianCycle())
}

/*
Hamiltonian Cycle found ::  0 1 2 4 3 0
hamiltonianCycle : true

Hamiltonian Cycle not found
hamiltonianCycle :  false
*/

// Testing code
fun main() {
    main1()
    main2()
    main3()
    main4()
    main5()
}