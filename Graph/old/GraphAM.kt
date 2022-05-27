import java.util.Comparator
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Stack
import java.util.Queue


class GraphAM internal constructor(cnt:Int) {
    internal var count: Int = 0
    internal var adj: Array<IntArray>

    init {
        count = cnt
        adj = Array<IntArray>(count, { IntArray(count) })
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
            print("Node index [ " + i + " ] is connected with : ")
            for (j in 0 until count) {
                if (adj[i][j] != 0)
                    print(j.toString() + " ")
            }
            println("")
        }
    }

    internal class Edge(dst: Int, cst: Int) {
        var dest: Int = 0
        var cost: Int = 0

        init {
            dest = dst
            cost = cst
        }
    }

    internal class EdgeComparator : Comparator<Edge> {
        public override fun compare(x: Edge, y: Edge): Int {
            if (x.cost < y.cost) {
                return -1
            }
            if (x.cost > y.cost) {
                return 1
            }
            return 0
        }
    }

    fun dijkstra(src: Int) {
        var source = src
        val previous = IntArray(count)
        val dist = IntArray(count)
        val visited = BooleanArray(count)
        for (i in 0 until count) {
            previous[i] = -1
            dist[i] = Integer.MAX_VALUE // infinite
            visited[i] = false
        }
        dist[source] = 0
        previous[source] = -1
        val comp = EdgeComparator()
        val queue = PriorityQueue<Edge>(100, comp)
        var node = Edge(source, 0)
        queue.add(node)
        while (queue.isEmpty() != true) {
            node = queue.peek()
            queue.remove()
            source = node.dest
            visited[source] = true
            for (dest in 0 until count) {
                val cost = adj[source][dest]
                if (cost != 0) {
                    val alt = cost + dist[source]
                    if (dist[dest] > alt && visited[dest] == false) {
                        dist[dest] = alt
                        previous[dest] = source
                        node = Edge(dest, alt)
                        queue.add(node)
                    }
                }
            }
        }
        for (i in 0 until count) {
            if (dist[i] == Integer.MAX_VALUE) {
                println("Node id " + i + " prev " + previous[i] + " distance : Unreachable")
            } else {
                println("Node id " + i + " prev " + previous[i] + " distance : " + dist[i])
            }
        }
    }

    fun prims() {
        val previous = IntArray(count)
        val dist = IntArray(count)
        var source = 0
        val visited = BooleanArray(count)
        for (i in 0 until count) {
            previous[i] = -1
            dist[i] = Integer.MAX_VALUE // infinite
            visited[i] = false
        }
        dist[source] = 0
        previous[source] = -1
        val comp = EdgeComparator()
        val queue = PriorityQueue<Edge>(100, comp)
        var node = Edge(source, 0)
        queue.add(node)
        while (queue.isEmpty() != true) {
            node = queue.peek()
            queue.remove()
            source = node.dest
            visited[source] = true
            for (dest in 0 until count) {
                val cost = adj[source][dest]
                if (cost != 0) {
                    val alt = cost
                    if (dist[dest] > alt && visited[dest] == false) {
                        dist[dest] = alt
                        previous[dest] = source
                        node = Edge(dest, alt)
                        queue.add(node)
                    }
                }
            }
        }
        for (i in 0 until count) {
            if (dist[i] == Integer.MAX_VALUE) {
                println("Node id " + i + " prev " + previous[i] + " distance : Unreachable")
            } else {
                println("Node id " + i + " prev " + previous[i] + " distance : " + dist[i])
            }
        }
    }

    fun hamiltonianPathUtil(path: IntArray, pSizeIn: Int, added: IntArray): Boolean {
        var pSize = pSizeIn
        // Base case full length path is found
        if (pSize == count) {
            return true
        }
        for (vertex in 0 until count) {
            // there is a path from last element and next vertex
            // and next vertex is not already included in path.
            if (pSize == 0 || (adj[path[pSize - 1]][vertex] == 1 && added[vertex] == 0)) {
                path[pSize++] = vertex
                added[vertex] = 1
                if (hamiltonianPathUtil(path, pSize, added))
                    return true
                // backtracking
                pSize--
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
            for (i in 0 until count)
                print(" " + path[i])
            println()
            return true
        }
        println("Hamiltonian Path not found")
        return false
    }

    fun hamiltonianCycleUtil(path: IntArray, pSizeIn: Int, added: IntArray): Boolean {
        var pSize = pSizeIn
        // Base case full length path is found
        // this last check can be modified to make it a path.
        if (pSize == count) {
            if (adj[path[pSize - 1]][path[0]] == 1) {
                path[pSize] = path[0]
                return true
            } else
                return false
        }
        for (vertex in 0 until count) {
            // there is a path from last element and next vertex
            if (pSize == 0 || (adj[path[pSize - 1]][vertex] == 1 && added[vertex] == 0)) {
                path[pSize++] = vertex
                added[vertex] = 1
                if (hamiltonianCycleUtil(path, pSize, added))
                    return true
                // backtracking
                pSize--
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
            for (i in 0..count)
                print(" " + path[i])
            println()
            return true
        }
        println("Hamiltonian Cycle not found")
        return false
    }
}

fun main1() {
    val graph = GraphAM(4)
    graph.addUndirectedEdge(0, 1, 1)
    graph.addUndirectedEdge(0, 2, 1)
    graph.addUndirectedEdge(1, 2, 1)
    graph.addUndirectedEdge(2, 3, 1)
    graph.print()
}
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
    gph.print()
    gph.prims()
    gph.dijkstra(0)
}

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
    gph.print()
    gph.prims()
    gph.dijkstra(1)
}

fun main4() {
    val count = 5
    val graph = GraphAM(count)
    val adj = arrayOf<IntArray>(
        intArrayOf(0, 1, 0, 1, 0),
        intArrayOf(1, 0, 1, 1, 0),
        intArrayOf(0, 1, 0, 0, 1),
        intArrayOf(1, 1, 0, 0, 1),
        intArrayOf(0, 1, 1, 1, 0)
    )
    for (i in 0 until count)
        for (j in 0 until count)
            if (adj[i][j] == 1)
                graph.addDirectedEdge(i, j, 1)
    println("hamiltonianPath : " + graph.hamiltonianPath())
    println("hamiltonianCycle : " + graph.hamiltonianCycle())
    val graph2 = GraphAM(count)
    val adj2 = arrayOf<IntArray>(
        intArrayOf(0, 1, 0, 1, 0),
        intArrayOf(1, 0, 1, 1, 0),
        intArrayOf(0, 1, 0, 0, 1),
        intArrayOf(1, 1, 0, 0, 0),
        intArrayOf(0, 1, 1, 0, 0)
    )
    for (i in 0 until count)
        for (j in 0 until count)
            if (adj2[i][j] == 1)
                graph2.addDirectedEdge(i, j, 1)
    println("hamiltonianPath : " + graph2.hamiltonianPath())
    println("hamiltonianCycle : " + graph2.hamiltonianCycle())
}

fun main(args : Array<String>) {
    main1()
    main2()
    main3()
    main4()
}