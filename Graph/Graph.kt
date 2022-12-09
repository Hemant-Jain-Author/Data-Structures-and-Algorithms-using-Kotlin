import java.util.Comparator
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Stack
import java.util.Queue
import java.util.Arrays

class Graph(var count: Int) {
    private val Adj: LinkedList<LinkedList<Edge>>
    var INF = 9999

    private class Edge(var src: Int, var dest: Int, var cost: Int) : Comparable<Edge> {
        override operator fun compareTo(other: Edge): Int {
            return cost - other.cost
        }
    }

    init {
        Adj = LinkedList<LinkedList<Edge>>()
        for (i in 0 until count) {
            Adj.add(LinkedList<Edge>())
        }
    }

    public fun addDirectedEdge(source: Int, dest: Int, cost: Int = 1) {
        val edge = Edge(source, dest, cost)
        Adj.get(source).add(edge)
    }

    public fun addUndirectedEdge(source: Int, dest: Int, cost: Int = 1) {
        addDirectedEdge(source, dest, cost)
        addDirectedEdge(dest, source, cost)
    }

    fun print() {
        for (i in 0 until count) {
            val ad: LinkedList<Edge> = Adj.get(i)
            print("Vertex $i is connected to : ")
            for (adn in ad) {
                print(adn.dest.toString() + "(cost: " + adn.cost + ") ")
            }
            println()
        }
    }

    fun dfsStack(source: Int, target: Int): Boolean {
        val visited = BooleanArray(count)
        val stk: Stack<Int> = Stack<Int>()
        stk.push(source)
        visited[source] = true
        while (stk.isEmpty() == false) {
            val curr: Int = stk.pop()
            val adl: LinkedList<Edge> = Adj.get(curr)
            for (adn in adl) {
                if (visited[adn.dest] == false) {
                    visited[adn.dest] = true
                    stk.push(adn.dest)
                }
            }
        }
        return visited[target]
    }

    fun dfs(source: Int, target: Int): Boolean {
        val visited = BooleanArray(count)
        dfsUtil(source, visited)
        return visited[target]
    }

    fun dfsUtil(index: Int, visited: BooleanArray) {
        visited[index] = true
        val adl: LinkedList<Edge> = Adj.get(index)
        for (adn in adl) {
            if (visited[adn.dest] == false) dfsUtil(adn.dest, visited)
        }
    }

    fun dfsUtil2(index: Int, visited: BooleanArray, stk: Stack<Int>) {
        visited[index] = true
        val adl: LinkedList<Edge> = Adj.get(index)
        for (adn in adl) {
            if (visited[adn.dest] == false) dfsUtil2(adn.dest, visited, stk)
        }
        stk.push(index)
    }

    fun bfs(source: Int, target: Int): Boolean {
        val visited = BooleanArray(count)
        val que: LinkedList<Int> = LinkedList<Int>()
        que.add(source)
        visited[source] = true
        while (que.isEmpty() == false) {
            val curr: Int = que.remove()
            val adl: LinkedList<Edge> = Adj.get(curr)
            for (adn in adl) {
                if (visited[adn.dest] == false) {
                    visited[adn.dest] = true
                    que.add(adn.dest)
                }
            }
        }
        return visited[target]
    }

    /*
	Path between 0 & 6 : true
	Path between 0 & 6 : true
	Path between 0 & 6 : true
	*/

    fun topologicalSort() {
        val stk: Stack<Int> = Stack<Int>()
        val visited = BooleanArray(count)
        for (i in 0 until count) {
            if (visited[i] == false) {
                dfsUtil2(i, visited, stk)
            }
        }
        print("Topological Sort::")
        while (stk.isEmpty() != true) {
            print(" " + stk.pop())
        }
    }

    /*
	Topological Sort:: 1 4 6 3 5 7 8 0 2
	*/

    fun pathExist(source: Int, dest: Int): Boolean {
        val visited = BooleanArray(count)
        dfsUtil(source, visited)
        return visited[dest]
    }

    fun countAllPathDFS(visited: BooleanArray, source: Int, dest: Int): Int {
        if (source == dest) {
            return 1
        }
        var count = 0
        visited[source] = true
        val adl: LinkedList<Edge> = Adj.get(source)
        for (adn in adl) {
            if (visited[adn.dest] == false) {
                count += countAllPathDFS(visited, adn.dest, dest)
            }
        }
        visited[source] = false
        return count
    }

    fun countAllPath(src: Int, dest: Int): Int {
        val visited = BooleanArray(count)
        return countAllPathDFS(visited, src, dest)
    }

    fun printAllPathDFS(visited: BooleanArray, source: Int, dest: Int, path: Stack<Int>) {
        path.push(source)
        if (source == dest) {
            println(path)
            path.pop()
            return
        }
        visited[source] = true
        val adl: LinkedList<Edge> = Adj.get(source)
        for (adn in adl) {
            if (visited[adn.dest] == false) printAllPathDFS(visited, adn.dest, dest, path)
        }
        visited[source] = false
        path.pop()
    }

    fun printAllPath(src: Int, dest: Int) {
        val visited = BooleanArray(count)
        val path: Stack<Int> = Stack<Int>()
        printAllPathDFS(visited, src, dest, path)
    }

    /*
	Vertex 0 is connected to : 1(cost: 1) 2(cost: 1) 
	Vertex 1 is connected to : 3(cost: 1) 4(cost: 1) 
	Vertex 2 is connected to : 3(cost: 1) 
	Vertex 3 is connected to : 4(cost: 1) 
	Vertex 4 is connected to : 
	
	PathExist :: true
	
	Path Count :: 3
	
	[0, 1, 3, 4]
	[0, 1, 4]
	[0, 2, 3, 4]
	*/

    fun rootVertex(): Int {
        val visited = BooleanArray(count)
        var retVal = -1
        for (i in 0 until count) {
            if (visited[i] == false) {
                dfsUtil(i, visited)
                retVal = i
            }
        }
        print("Root vertex is :: $retVal")
        return retVal
    }

    /*
	Root vertex is :: 5
	*/

    fun transitiveClosureUtil(source: Int, dest: Int, tc: Array<IntArray>) {
        tc[source][dest] = 1
        val adl: LinkedList<Edge> = Adj.get(dest)
        for (adn in adl) {
            if (tc[source][adn.dest] == 0) transitiveClosureUtil(source, adn.dest, tc)
        }
    }

    fun transitiveClosure(): Array<IntArray> {
        val tc = Array(count) { IntArray(count) }
        for (i in 0 until count) {
            transitiveClosureUtil(i, i, tc)
        }
        return tc
    }

    /*
	1 1 1 1 
	1 1 1 1 
	1 1 1 1 
	0 0 0 1 
	*/

    fun bfsLevelNode(source: Int) {
        val visited = BooleanArray(count)
        val level = IntArray(count)
        visited[source] = true
        val que: LinkedList<Int> = LinkedList<Int>()
        que.add(source)
        level[source] = 0
        println("Node  - Level")
        while (que.isEmpty() == false) {
            val curr: Int = que.remove()
            val depth = level[curr]
            val adl: LinkedList<Edge> = Adj.get(curr)
            println("$curr - $depth")
            for (adn in adl) {
                if (visited[adn.dest] == false) {
                    visited[adn.dest] = true
                    que.add(adn.dest)
                    level[adn.dest] = depth + 1
                }
            }
        }
    }

    fun bfsDistance(source: Int, dest: Int): Int {
        val visited = BooleanArray(count)
        val que: LinkedList<Int> = LinkedList<Int>()
        que.add(source)
        visited[source] = true
        val level = IntArray(count)
        level[source] = 0
        while (que.isEmpty() == false) {
            val curr: Int = que.remove()
            val depth = level[curr]
            val adl: LinkedList<Edge> = Adj.get(curr)
            for (adn in adl) {
                if (adn.dest == dest) {
                    return depth + 1
                }
                if (visited[adn.dest] == false) {
                    visited[adn.dest] = true
                    que.add(adn.dest)
                    level[adn.dest] = depth + 1
                }
            }
        }
        return -1
    }

    /*
	Node  - Level
	1 - 0
	0 - 1
	2 - 1
	4 - 2
	5 - 2
	3 - 3
	6 - 3
	
    BfsDistance(1, 6) : 3
	*/

    fun isCyclePresentUndirectedDFS(index: Int, parentIndex: Int, visited: BooleanArray): Boolean {
        visited[index] = true
        var dest: Int
        val adl: LinkedList<Edge> = Adj.get(index)
        for (adn in adl) {
            dest = adn.dest
            if (visited[dest] == false) {
                if (isCyclePresentUndirectedDFS(dest, index, visited)) 
                    return true
            } else if (parentIndex != dest) 
                return true
        }
        return false
    }

    fun isCyclePresentUndirected(): Boolean {
        val visited = BooleanArray(count)
        for (i in 0 until count) {
            if (visited[i] == false && isCyclePresentUndirectedDFS(i, -1, visited)) 
                return true
        }
        return false
    }

    fun find(parent: IntArray, idx: Int): Int {
        var index = idx
        var p = parent[index]
        while (p != -1) {
            index = p
            p = parent[index]
        }
        return index
    }

    fun union(parent: IntArray, x: Int, y: Int) {
        parent[y] = x
    }

    // Using flags[][] array, if considered edge x to y, 
    // then ignore edge y to x.
    fun isCyclePresentUndirected2(): Boolean {
        val parent = IntArray(count){-1}
        val edge: LinkedList<Edge> = LinkedList<Edge>()
        val flags = Array(count) {BooleanArray(count)}
        for (i in 0 until count) {
            val ad: LinkedList<Edge> = Adj.get(i)
            for (adn in ad) {
                // Using flags[][] array, if considered edge x to y, 
                // then ignore edge y to x.
                if (flags[adn.dest][adn.src] == false) {
                    edge.add(adn)
                    flags[adn.src][adn.dest] = true
                }
            }
        }
        for (e in edge) {
            val x = find(parent, e.src)
            val y = find(parent, e.dest)
            if (x == y) {
                return true
            }
            union(parent, x, y)
        }
        return false
    }

    fun isCyclePresentUndirected3(): Boolean {
        val sets = arrayOfNulls<Sets>(count)
        for (i in 0 until count) sets[i] = Sets(i, 0)
        val edge: LinkedList<Edge> = LinkedList<Edge>()
        val flags = Array(count) {
            BooleanArray(
                count
            )
        }
        for (i in 0 until count) {
            val ad: LinkedList<Edge> = Adj.get(i)
            for (adn in ad) {
                // Using flags[][] array, if considered edge x to y, 
                // then ignore edge y to x.
                if (flags[adn.dest][adn.src] == false) {
                    edge.add(adn)
                    flags[adn.src][adn.dest] = true
                }
            }
        }
        for (e in edge) {
            val x = find(sets, e.src)
            val y = find(sets, e.dest)
            if (x == y) {
                return true
            }
            union(sets, x, y)
        }
        return false
    }

    /*
      Cycle Presen : false
      Cycle Presen : false
      Cycle Presen : false
      Cycle Presen : true
      Cycle Presen : true
      Cycle Presen : true
    */

    fun isCyclePresentDFS(index: Int, visited: BooleanArray, marked: IntArray): Boolean {
        visited[index] = true
        marked[index] = 1
        val adl: LinkedList<Edge> = Adj.get(index)
        for (adn in adl) {
            val dest = adn.dest
            if (marked[dest] == 1) 
                return true
            if (visited[dest] == false) 
                if (isCyclePresentDFS(dest, visited, marked)) 
                    return true
        }
        marked[index] = 0
        return false
    }

    fun isCyclePresent() : Boolean {
        val visited = BooleanArray(count)
        val marked = IntArray(count)
        for (index in 0 until count) {
            if (!visited[index]) 
                if (isCyclePresentDFS(index, visited, marked)) 
                    return true
        }
        return false
    }

    fun isCyclePresentDFSColour(index: Int, visited: IntArray): Boolean {
        visited[index] = 1 // 1 = grey
        var dest: Int
        val adl: LinkedList<Edge> = Adj.get(index)
        for (adn in adl) {
            dest = adn.dest
            if (visited[dest] == 1) // "Grey":
                return true
            if (visited[dest] == 0) // "White":
                if (isCyclePresentDFSColour(dest, visited)) return true
        }
        visited[index] = 2 // "Black"
        return false
    }

    fun isCyclePresentColour(): Boolean {
        val visited = IntArray(count)
        for (i in 0 until count) {
            if (visited[i] == 0) // "White"
                if (isCyclePresentDFSColour(i, visited)) return true
        }
        return false
    }

    /*
      isCyclePresent : false
      isCyclePresent : false
      isCyclePresent : true
      isCyclePresent : true
    */

    fun transposeGraph(): Graph {
        val g = Graph(count)
        for (i in 0 until count) {
            val adl: LinkedList<Edge> = Adj.get(i)
            for (adn in adl) {
                val dest = adn.dest
                g.addDirectedEdge(dest, i)
            }
        }
        return g
    }

    /*
      Vertex 0 is connected to : 
      Vertex 1 is connected to : 0(cost: 1) 4(cost: 1) 
      Vertex 2 is connected to : 0(cost: 1) 
      Vertex 3 is connected to : 1(cost: 1) 2(cost: 1) 
      Vertex 4 is connected to : 3(cost: 1) 
    */
    
    fun isConnectedUndirected(): Boolean {
        val visited = BooleanArray(count)
        dfsUtil(0, visited)
        for (i in 0 until count) {
            if (visited[i] == false) {
                return false
            }
        }
        return true
    }

    /*
      isConnectedUndirected:: true
    */
    
    fun isStronglyConnected(): Boolean {
        val visited = BooleanArray(count)
        dfsUtil(0, visited)
        for (i in 0 until count) {
            if (visited[i] == false) {
                return false
            }
        }
        val gReversed = transposeGraph()
        for (i in 0 until count) {
            visited[i] = false
        }
        gReversed.dfsUtil(0, visited)
        for (i in 0 until count) {
            if (visited[i] == false) {
                return false
            }
        }
        return true
    }

    /*
      IsStronglyConnected:: true
    */

    fun stronglyConnectedComponent() {
        val visited = BooleanArray(count)
        val stk: Stack<Int> = Stack<Int>()
        for (i in 0 until count) {
            if (visited[i] == false) 
                dfsUtil2(i, visited, stk)
        }
        val gReversed = transposeGraph()
        for (i in 0 until count) {
            visited[i] = false
        }
        val stk2: Stack<Int> = Stack<Int>()
        while (stk.isEmpty() == false) {
            val index: Int = stk.pop()
            if (visited[index] == false) {
                stk2.clear()
                gReversed.dfsUtil2(index, visited, stk2)
                println(stk2)
            }
        }
    }

    /*
	[1, 2, 0]
	[4, 5, 3]
	[6]
    */

    fun primsMST() {
        val previous = IntArray(count){-1}
        val dist = IntArray(count){Int.MAX_VALUE} // infinite
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
            visited[source] = true
            source = node.dest
            val adl: LinkedList<Edge> = Adj.get(source)
            for (adn in adl) {
                val dest = adn.dest
                val alt = adn.cost
                if (dist[dest] > alt && visited[dest] == false) {
                    dist[dest] = alt
                    previous[dest] = source
                    node = Edge(source, dest, alt)
                    queue.add(node)
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

    class Sets internal constructor(var parent: Int, var rank: Int)

    // root element of set
    fun find(sets: Array<Sets?>, idx: Int): Int {
        var index = idx
        var p = sets[index]!!.parent
        while (p != index) {
            index = p
            p = sets[index]!!.parent
        }
        return index
    }

    // consider x and y are roots of sets.
    fun union(sets: Array<Sets?>, x: Int, y: Int) {
        if (sets[x]!!.rank < sets[y]!!.rank) sets[x]!!.parent =
            y else if (sets[y]!!.rank < sets[x]!!.rank) sets[y]!!.parent = x else {
            sets[x]!!.parent = y
            sets[y]!!.rank++
        }
    }

    fun kruskalMST() {
        //Different subsets are created.
        val sets = arrayOfNulls<Sets>(count)
        for (i in 0 until count) sets[i] = Sets(i, 0)

        // Edges are added to array and sorted.
        var E = 0
        val edge = arrayOfNulls<Edge>(100)
        for (i in 0 until count) {
            val ad: LinkedList<Edge> = Adj.get(i)
            for (adn in ad) {
                edge[E++] = adn
            }
        }
        Arrays.sort(edge, 0, E - 1)
        var sum = 0
        var output = "Edges are "
        for (i in 0 until E) {
            val x = find(sets, edge[i]!!.src)
            val y = find(sets, edge[i]!!.dest)
            if (x != y) {
                output += "(" + edge[i]!!.src + "->" + edge[i]!!.dest + " @ " + edge[i]!!.cost + ") "
                sum += edge[i]!!.cost
                union(sets, x, y)
            }
        }
        println(output)
        println("Total MST cost: $sum")
    }

    /*	
	Edges are (0->1 @ 4) (5->2 @ 4) (2->3 @ 7) (3->4 @ 9) (6->5 @ 2) (7->6 @ 1) (0->7 @ 8) (2->8 @ 2) 
	Total MST cost: 37
	
	Edges are (6->7 @ 1) (2->8 @ 2) (5->6 @ 2) (0->1 @ 4) (2->5 @ 4) (2->3 @ 7) (0->7 @ 8) (3->4 @ 9) 
	Total MST cost: 37
	
	Shortest Paths: (0->1 @ 4) (0->1->2 @ 12) (0->1->2->3 @ 19) (0->7->6->5->4 @ 21) (0->7->6->5 @ 11) (0->7->6 @ 9) (0->7 @ 8) (0->1->2->8 @ 14) 
	*/

    // Unweighed graph
    fun shortestPath(source: Int) {
        var curr: Int
        val distance = IntArray(count)
        val path = IntArray(count)
        for (i in 0 until count) {
            distance[i] = -1
            path[i] = -1
        }
        val que: java.util.Queue<Int> = LinkedList<Int>()
        que.add(source)
        distance[source] = 0
        path[source] = source
        while (que.isEmpty() == false) {
            curr = que.remove()
            val adl: LinkedList<Edge> = Adj.get(curr)
            for (adn in adl) {
                if (distance[adn.dest] == -1) {
                    distance[adn.dest] = distance[curr] + 1
                    path[adn.dest] = curr
                    que.add(adn.dest)
                }
            }
        }
        printPath(path, distance, count, source)
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

    fun dijkstra(source: Int) {
        val previous = IntArray(count)
        val dist = IntArray(count)
        val visited = BooleanArray(count)
        Arrays.fill(previous, -1)
        Arrays.fill(dist, 99999) // infinite
        dist[source] = 0
        previous[source] = source
        val queue: PriorityQueue<Edge> = PriorityQueue<Edge>(100)
        var edge = Edge(source, source, 0)
        queue.add(edge)
        var curr: Int
        while (queue.isEmpty() != true) {
            edge = queue.peek()
            queue.remove()
            curr = edge.dest
            visited[curr] = true
            val adl: LinkedList<Edge> = Adj.get(curr)
            for (adn in adl) {
                val dest = adn.dest
                val alt = adn.cost + dist[curr]
                if (alt < dist[dest] && visited[dest] == false) {
                    dist[dest] = alt
                    previous[dest] = curr
                    edge = Edge(curr, dest, alt)
                    queue.add(edge)
                }
            }
        }
        printPath(previous, dist, count, source)
    }

    fun bellmanFordShortestPath(source: Int) {
        val distance = IntArray(count)
        Arrays.fill(distance, 99999) // infinite
        val path = IntArray(count)
        Arrays.fill(path, -1)
        distance[source] = 0
        path[source] = source
        // Outer loop will run (V-1) number of times.
        // Inner for loop and while loop runs combined will
        // run for Edges number of times.
        // Which make the total complexity as O(V*E)
        for (i in 0 until count - 1) {
            for (j in 0 until count) {
                val adl: LinkedList<Edge> = Adj.get(j)
                for (adn in adl) {
                    val newDistance = distance[j] + adn.cost
                    if (distance[adn.dest] > newDistance) {
                        distance[adn.dest] = newDistance
                        path[adn.dest] = j
                    }
                }
            }
        }
        printPath(path, distance, count, source)
    }

    /*
      4
      4
    */

    fun isConnected(): Boolean {
        val visited = BooleanArray(count)
        // Find a vertex with non - zero degree
        // DFS traversal of graph from a vertex with non - zero degree
        var adl: LinkedList<Edge>
        for (i in 0 until count) {
            adl = Adj.get(i)
            if (adl.size > 0) {
                dfsUtil(i, visited)
                break
            }
        }
        // Check if all non - zero degree count are visited
        for (i in 0 until count) {
            adl = Adj.get(i)
            if (adl.size > 0) if (visited[i] == false) return false
        }
        return true
    }

    // Check if all non - zero degree nodes are connected
    // Count odd degree
    fun isEulerian(): Int {
        // Check if all non - zero degree nodes are connected
        if (isConnected() == false) {
            println("graph is not Eulerian")
            return 0
        }

        // Count odd degree
        var odd = 0
        val inDegree = IntArray(count)
        val outDegree = IntArray(count)
        var adl: LinkedList<Edge>
        for (i in 0 until count) {
            adl = Adj.get(i)
            for (adn in adl) {
                outDegree[i] += 1
                inDegree[adn.dest] += 1
            }
        }
        for (i in 0 until count) {
            if ((inDegree[i] + outDegree[i]) % 2 != 0) {
                odd += 1
            }
        }
        return if (odd == 0) {
            println("graph is Eulerian")
            2
        } else if (odd == 2) {
            println("graph is Semi-Eulerian")
            1
        } else {
            println("graph is not Eulerian")
            0
        }
    }

    /*
      graph is Semi-Eulerian
      graph is Eulerian
    */

    fun isStronglyConnected2(): Boolean {
        val visited = BooleanArray(count)
        var index: Int
        // Find a vertex with non - zero degree
        var adl: LinkedList<Edge>
        index = 0
        while (index < count) {
            adl = Adj.get(index)
            if (adl.size > 0) break
            index++
        }
        // DFS traversal of graph from a vertex with non - zero degree
        dfsUtil(index, visited)
        for (i in 0 until count) {
            adl = Adj.get(i)
            if (visited[i] == false && adl.size > 0) return false
        }
        val gReversed = transposeGraph()
        for (i in 0 until count) visited[i] = false
        gReversed.dfsUtil(index, visited)
        for (i in 0 until count) {
            adl = Adj.get(i)
            if (visited[i] == false && adl.size > 0) return false
        }
        return true
    }

    // Check if all non - zero degree count are connected
    // Check if in degree and out degree of every vertex is same
    fun isEulerianCycle(): Boolean {
        // Check if all non - zero degree count are connected
        val inDegree = IntArray(count)
        val outDegree = IntArray(count)
        if (!isStronglyConnected2()) return false

        // Check if in degree and out degree of every vertex is same
        for (i in 0 until count) {
            val adl: LinkedList<Edge> = Adj.get(i)
            for (adn in adl) {
                outDegree[i] += 1
                inDegree[adn.dest] += 1
            }
        }
        for (i in 0 until count) if (inDegree[i] != outDegree[i]) return false
        return true
    }

    /*
      Shortest Paths: (0->1 @ 1)(0->1->2 @ 2)(0->1->2->3 @ 3)(0->1->2->3->4 @ 4)(0->1->2->5 @ 3)(0->7->6 @ 2)(0->7 @ 1)(0->7->8 @ 2)
    */

    fun floydWarshall() {
        val V = count
        val dist = Array(V) { IntArray(V) }
        val path = Array(V) { IntArray(V) }
        val INF = 99999
        for (i in 0 until V) {
            for (j in 0 until V) {
                dist[i][j] = INF
                if (i == j) path[i][j] = 0 else path[i][j] = -1
            }
        }
        for (i in 0 until V) {
            val adl: LinkedList<Edge> = Adj.get(i)
            for (adn in adl) {
                path[adn.src][adn.dest] = adn.src
                dist[adn.src][adn.dest] = adn.cost
            }
        }

        
        for (k in 0 until V) { // Pick intermediate vertices.
            for (i in 0 until V) { // Pick source vertices one by one.
                for (j in 0 until V) { // Pick destination vertices.
                    // If we have a shorter path from i to j via k.
                    // then update dist[i][j] and  and path[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j]
                        path[i][j] = path[k][j]
                    }
                }
                // dist[i][i] is 0 in the start.
                // If there is a better path from i to i and is better path then we have -ve cycle.                //
                if (dist[i][i] < 0) {
                    println("Negative-weight cycle found.")
                    return
                }
            }
        }
        printSolution(dist, path, V)
    }

    private fun printSolution(cost: Array<IntArray>, path: Array<IntArray>, V: Int) {
        print("Shortest Paths : ")
        for (u in 0 until V) {
            for (v in 0 until V) {
                if (u != v && path[u][v] != -1) {
                    print("(")
                    printPath2(path, u, v)
                    print(" @ " + cost[u][v] + ") ")
                }
            }
        }
        println()
    }

    private fun printPath2(path: Array<IntArray>, u: Int, v: Int) {
        if (path[u][v] == u) {
            print("$u->$v")
            return
        }
        printPath2(path, u, path[u][v])
        print("->$v")
    }
}


fun main0() {
    val gph = Graph(4)
    gph.addUndirectedEdge(0, 1)
    gph.addUndirectedEdge(0, 2)
    gph.addUndirectedEdge(1, 2)
    gph.addUndirectedEdge(2, 3)
    gph.print()
}

// Testing Code
fun main1() {
    val gph = Graph(8)
    gph.addUndirectedEdge(0, 3)
    gph.addUndirectedEdge(0, 2)
    gph.addUndirectedEdge(0, 1)
    gph.addUndirectedEdge(1, 4)
    gph.addUndirectedEdge(2, 5)
    gph.addUndirectedEdge(3, 6)
    gph.addUndirectedEdge(6, 7)
    gph.addUndirectedEdge(5, 7)
    gph.addUndirectedEdge(4, 7)
    println("Path between 0 & 6 : " + gph.dfs(0, 6))
    println("Path between 0 & 6 : " + gph.bfs(0, 6))
    println("Path between 0 & 6 : " + gph.dfsStack(0, 6))
}

// Testing Code
fun main2() {
    val gph = Graph(9)
    gph.addDirectedEdge(0, 2)
    gph.addDirectedEdge(1, 2)
    gph.addDirectedEdge(1, 3)
    gph.addDirectedEdge(1, 4)
    gph.addDirectedEdge(3, 2)
    gph.addDirectedEdge(3, 5)
    gph.addDirectedEdge(4, 5)
    gph.addDirectedEdge(4, 6)
    gph.addDirectedEdge(5, 7)
    gph.addDirectedEdge(6, 7)
    gph.addDirectedEdge(7, 8)
    gph.topologicalSort()
}

// Testing Code
fun main3() {
    val gph = Graph(5)
    gph.addDirectedEdge(0, 1)
    gph.addDirectedEdge(0, 2)
    gph.addDirectedEdge(2, 3)
    gph.addDirectedEdge(1, 3)
    gph.addDirectedEdge(3, 4)
    gph.addDirectedEdge(1, 4)
    gph.print()
    println("PathExist :: " + gph.pathExist(0, 4))
    println("Path Count :: " + gph.countAllPath(0, 4))
    gph.printAllPath(0, 4)
}

// Testing Code
fun main4() {
    val gph = Graph(7)
    gph.addDirectedEdge(0, 1)
    gph.addDirectedEdge(0, 2)
    gph.addDirectedEdge(1, 3)
    gph.addDirectedEdge(4, 1)
    gph.addDirectedEdge(6, 4)
    gph.addDirectedEdge(5, 6)
    gph.addDirectedEdge(5, 2)
    gph.addDirectedEdge(6, 0)
    gph.rootVertex()
}

// Testing Code
fun main5() {
    val gph = Graph(4)
    gph.addDirectedEdge(0, 1)
    gph.addDirectedEdge(0, 2)
    gph.addDirectedEdge(1, 2)
    gph.addDirectedEdge(2, 0)
    gph.addDirectedEdge(2, 3)
    gph.addDirectedEdge(3, 3)
    val tc = gph.transitiveClosure()
    for (i in 0..3) {
        for (j in 0..3) {
            print(tc[i][j].toString() + " ")
        }
        println()
    }
}

// Testing Code
fun main6() {
    val gph = Graph(7)
    gph.addUndirectedEdge(0, 1)
    gph.addUndirectedEdge(0, 2)
    gph.addUndirectedEdge(0, 4)
    gph.addUndirectedEdge(1, 2)
    gph.addUndirectedEdge(2, 5)
    gph.addUndirectedEdge(3, 4)
    gph.addUndirectedEdge(4, 5)
    gph.addUndirectedEdge(4, 6)
    gph.bfsLevelNode(1)
    println("BfsDistance(1, 6) : " + gph.bfsDistance(1, 6))
}

// Testing Code
fun main7() {
    val gph = Graph(6)
    gph.addUndirectedEdge(0, 1)
    gph.addUndirectedEdge(1, 2)
    gph.addUndirectedEdge(3, 4)
    gph.addUndirectedEdge(4, 2)
    gph.addUndirectedEdge(2, 5)
    println("Cycle Presen : " + gph.isCyclePresentUndirected())
    println("Cycle Presen : " + gph.isCyclePresentUndirected2())
    println("Cycle Presen : " + gph.isCyclePresentUndirected3())
    gph.addUndirectedEdge(4, 1)
    println("Cycle Presen : " + gph.isCyclePresentUndirected())
    println("Cycle Presen : " + gph.isCyclePresentUndirected2())
    println("Cycle Presen : " + gph.isCyclePresentUndirected3())
}

// Testing Code
fun main8() {
    val gph = Graph(5)
    gph.addDirectedEdge(0, 1)
    gph.addDirectedEdge(0, 2)
    gph.addDirectedEdge(2, 3)
    gph.addDirectedEdge(1, 3)
    gph.addDirectedEdge(3, 4)
    println("isCyclePresent : " + gph.isCyclePresent())
    println("isCyclePresent : " + gph.isCyclePresentColour())
    gph.addDirectedEdge(4, 1)
    println("isCyclePresent : " + gph.isCyclePresent())
    println("isCyclePresent : " + gph.isCyclePresentColour())
}

// Testing Code
fun main9() {
    val gph = Graph(5)
    gph.addDirectedEdge(0, 1)
    gph.addDirectedEdge(0, 2)
    gph.addDirectedEdge(2, 3)
    gph.addDirectedEdge(1, 3)
    gph.addDirectedEdge(3, 4)
    gph.addDirectedEdge(4, 1)
    val gReversed = gph.transposeGraph()
    gReversed.print()
}

//Testing Code
fun main10() {
    val gph = Graph(6)
    gph.addUndirectedEdge(0, 1)
    gph.addUndirectedEdge(1, 2)
    gph.addUndirectedEdge(3, 4)
    gph.addUndirectedEdge(4, 2)
    gph.addUndirectedEdge(2, 5)
    println("isConnectedUndirected:: " + gph.isConnectedUndirected())
}

// Testing Code
fun main11() {
    val gph = Graph(5)
    gph.addDirectedEdge(0, 1)
    gph.addDirectedEdge(1, 2)
    gph.addDirectedEdge(2, 3)
    gph.addDirectedEdge(3, 0)
    gph.addDirectedEdge(2, 4)
    gph.addDirectedEdge(4, 2)
    println("IsStronglyConnected:: " + gph.isStronglyConnected())
}

// Testing Code
fun main12() {
    val gph = Graph(7)
    gph.addDirectedEdge(0, 1)
    gph.addDirectedEdge(1, 2)
    gph.addDirectedEdge(2, 0)
    gph.addDirectedEdge(2, 3)
    gph.addDirectedEdge(3, 4)
    gph.addDirectedEdge(4, 5)
    gph.addDirectedEdge(5, 3)
    gph.addDirectedEdge(5, 6)
    gph.stronglyConnectedComponent()
}

// Testing Code
fun main13() {
    val gph = Graph(9)
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
    println()
    gph.primsMST()
    println()
    gph.kruskalMST()
    println()
    gph.dijkstra(0)
}

// Testing Code
fun main14() {
    val gph = Graph(5)
    gph.addDirectedEdge(0, 1, 3)
    gph.addDirectedEdge(0, 4, 2)
    gph.addDirectedEdge(1, 2, 1)
    gph.addDirectedEdge(2, 3, 1)
    gph.addDirectedEdge(4, 1, -2)
    gph.addDirectedEdge(4, 3, 1)
    gph.bellmanFordShortestPath(0)
}

/*
Shortest Paths: (0->4->1 @ 0) (0->4->1->2 @ 1) (0->4->1->2->3 @ 2) (0->4 @ 2) 
*/

// Testing Code
fun main15() {
    val gph = Graph(5)
    gph.addDirectedEdge(1, 0)
    gph.addDirectedEdge(0, 2)
    gph.addDirectedEdge(2, 1)
    gph.addDirectedEdge(0, 3)
    gph.addDirectedEdge(3, 4)
    gph.isEulerian()
    gph.addDirectedEdge(4, 0)
    gph.isEulerian()
}

// Testing Code
fun main16() {
    val gph = Graph(5)
    gph.addDirectedEdge(0, 1)
    gph.addDirectedEdge(1, 2)
    gph.addDirectedEdge(2, 0)
    gph.addDirectedEdge(0, 4)
    gph.addDirectedEdge(4, 3)
    gph.addDirectedEdge(3, 0)
    println(gph.isEulerianCycle())
}

/*
true
*/
// Testing Code
fun main17() {
    val gph = Graph(7)
    gph.addDirectedEdge(0, 1)
    gph.addDirectedEdge(1, 2)
    gph.addDirectedEdge(2, 0)
    gph.addDirectedEdge(2, 3)
    gph.addDirectedEdge(3, 4)
    gph.addDirectedEdge(4, 5)
    gph.addDirectedEdge(5, 3)
    gph.addDirectedEdge(5, 6)
    val gReversed = gph.transposeGraph()
    gReversed.print()
}

/*
Vertex 0 is connected to : 2(cost: 1) 
Vertex 1 is connected to : 0(cost: 1) 
Vertex 2 is connected to : 1(cost: 1) 
Vertex 3 is connected to : 2(cost: 1) 5(cost: 1) 
Vertex 4 is connected to : 3(cost: 1) 
Vertex 5 is connected to : 4(cost: 1) 
Vertex 6 is connected to : 5(cost: 1) 
*/
// Testing Code
fun main18() {
    val gph = Graph(9)
    gph.addUndirectedEdge(0, 1)
    gph.addUndirectedEdge(0, 7)
    gph.addUndirectedEdge(1, 2)
    gph.addUndirectedEdge(1, 7)
    gph.addUndirectedEdge(2, 3)
    gph.addUndirectedEdge(2, 8)
    gph.addUndirectedEdge(2, 5)
    gph.addUndirectedEdge(3, 4)
    gph.addUndirectedEdge(3, 5)
    gph.addUndirectedEdge(4, 5)
    gph.addUndirectedEdge(5, 6)
    gph.addUndirectedEdge(6, 7)
    gph.addUndirectedEdge(6, 8)
    gph.addUndirectedEdge(7, 8)
    gph.shortestPath(0)
}

// Testing code.
fun main19() {
    val gph = Graph(4)
    gph.addDirectedEdge(0, 0, 0)
    gph.addDirectedEdge(1, 1, 0)
    gph.addDirectedEdge(2, 2, 0)
    gph.addDirectedEdge(3, 3, 0)
    gph.addDirectedEdge(0, 1, 5)
    gph.addDirectedEdge(0, 3, 10)
    gph.addDirectedEdge(1, 2, 3)
    gph.addDirectedEdge(2, 3, 1)
    gph.floydWarshall()
}

/*
Shortest Paths : (0->1 @ 5) (0->1->2 @ 8) (0->1->2->3 @ 9) (1->2 @ 3) (1->2->3 @ 4) (2->3 @ 1) 
*/

fun getHeight(arr: IntArray, height: IntArray?, index: Int): Int {
    if (arr[index] == -1) {
        return 0
    } else {
        return getHeight(arr, height, arr[index]) + 1
    }
}

// Testing code
fun main() {
    main0();
    main1();
    main2(); 
    main3(); 
    main4(); 
    main5(); 
    main6(); 
    main7(); 
    main8();
    main9();
    main10()
    main11()
    main12()
    main13()
    main14()
    main15()
    main16()
    main17()
    main18()
    main19()
}

