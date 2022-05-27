import java.util.Comparator
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Stack
import java.util.Queue

class Graph(cnt:Int) {
    internal var count: Int = 0
    private val Adj: LinkedList<LinkedList<Edge>>

     internal class Edge(dst: Int, cst: Int) {
        var dest: Int = 0
        var cost: Int = 0

        init {
            dest = dst
            cost = cst
        }
    }

    init {
        count = cnt
        Adj = LinkedList<LinkedList<Edge>>()
        for (i in 0 until cnt) {
            Adj.add(LinkedList<Edge>())
        }
    }

    fun addDirectedEdge(source: Int, dest: Int, cost: Int = 1) {
        val edge = Edge(dest, cost)
        Adj.get(source).add(edge)
    }

    fun addUndirectedEdge(source: Int, dest: Int, cost: Int = 1) {
        addDirectedEdge(source, dest, cost)
        addDirectedEdge(dest, source, cost)
    }

    fun print() {
        for (i in 0 until count) {
            val ad = Adj.get(i)
            print("Vertex " + i + " is connected to : ")
            for (adn in ad) {
                print("(" + adn.dest + ", " + adn.cost + ") ")
            }
        }
        println()
    }

    fun dfsStack(source: Int, target: Int): Boolean {
        val count = count
        val visited = BooleanArray(count)
        val stk = Stack<Int>()
        stk.push(source)
        visited[source] = true
        while (stk.isEmpty() == false) {
            val curr = stk.pop()
            val adl = Adj.get(curr)
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
        val adl = Adj.get(index)
        for (adn in adl) {
            if (visited[adn.dest] == false)
                dfsUtil(adn.dest, visited)
        }
    }

    fun dfsUtil2(index: Int, visited: BooleanArray, stk: Stack<Int>) {
        visited[index] = true
        val adl = Adj.get(index)
        for (adn in adl) {
            if (visited[adn.dest] == false) {
                dfsUtil2(adn.dest, visited, stk)
            }
        }
        stk.push(index)
    }

    fun bfs(source: Int, target: Int): Boolean {
        val visited = BooleanArray(count)
        val que = LinkedList<Int>()
        que.add(source)
        visited[source] = true
        while (que.isEmpty() == false) {
            val curr = que.remove()
            val adl = Adj.get(curr)
            for (adn in adl) {
                if (visited[adn.dest] == false) {
                    visited[adn.dest] = true
                    que.add(adn.dest)
                }
            }
        }
        return visited[target]
    }

    fun topologicalSort() {
        val stk = Stack<Int>()

        val visited = BooleanArray(count)
        for (i in 0 until count)
        {
            if (visited[i] == false)
            {
                dfsUtil2(i, visited, stk)
            }
        }
        print("TopologicalSort :: ")
        while (stk.isEmpty() != true)
        {
            print(" " + stk.pop())
        }
    }
    fun pathExist(source:Int, dest:Int):Boolean {
        val visited = BooleanArray(count)
        dfsUtil(source, visited)
        return visited[dest]
    }
    fun countAllPathDFS(visited:BooleanArray, source:Int, dest:Int):Int {
        if (source == dest)
        {
            return 1
        }
        var count = 0
        visited[source] = true
        val adl = Adj.get(source)
        for (adn in adl)
        {
            if (visited[adn.dest] == false)
            {
                count += countAllPathDFS(visited, adn.dest, dest)
            }
            visited[source] = false
        }
        return count
    }

    fun countAllPath(src:Int, dest:Int):Int {
        val visited = BooleanArray(count)
        return countAllPathDFS(visited, src, dest)
    }

    fun printAllPathDFS(visited:BooleanArray, source:Int, dest:Int, path:Stack<Int>) {
        path.push(source)
        if (source == dest)
        {
            print(path)
            path.pop()
            return
        }
        visited[source] = true
        val adl = Adj.get(source)
        for (adn in adl)
        {
            if (visited[adn.dest] == false)
            {
                printAllPathDFS(visited, adn.dest, dest, path)
            }
        }
        visited[source] = false
        path.pop()
    }

    fun printAllPath(src:Int, dest:Int) {
        val visited = BooleanArray(count)
        val path = Stack<Int>()
        print("PrintAllPath :: ")
        printAllPathDFS(visited, src, dest, path)
        println()
    }

    fun rootVertex():Int {
        val visited = BooleanArray(count)
        var retVal = -1
        for (i in 0 until count)
        {
            if (visited[i] == false)
            {
                dfsUtil(i, visited)
                retVal = i
            }
        }
        println("Root vertex is :: " + retVal)
        return retVal
    }

    fun transitiveClosureUtil(source:Int, dest:Int, tc:Array<IntArray>) {
        tc[source][dest] = 1
        val adl = Adj.get(dest)
        for (adn in adl)
        {
            if (tc[source][adn.dest] == 0)
                transitiveClosureUtil(source, adn.dest, tc)
        }
    }
    fun transitiveClosure():Array<IntArray> {
        val tc = Array<IntArray>(count, {IntArray(count)})
        for (i in 0 until count)
        {
            transitiveClosureUtil(i, i, tc)
        }
        return tc
    }

    fun bfsLevelNode(source:Int) {
        val visited = BooleanArray(count)
        val level = IntArray(count)
        visited[source] = true
        val que = LinkedList<Int>()
        que.add(source)
        level[source] = 0
        println("Node - Level")
        while (que.isEmpty() == false)
        {
            val curr = que.remove()
            val depth = level[curr]
            val adl = Adj.get(curr)
            println(curr.toString() + " - " + depth.toString())
            for (adn in adl)
            {
                if (visited[adn.dest] == false)
                {
                    visited[adn.dest] = true
                    que.add(adn.dest)
                    level[adn.dest] = depth + 1
                }
            }
        }
    }
    fun bfsDistance(source:Int, dest:Int):Int {
        val visited = BooleanArray(count)
        val que = LinkedList<Int>()
        que.add(source)
        visited[source] = true
        val level = IntArray(count)
        level[source] = 0
        while (que.isEmpty() == false)
        {
            val curr = que.remove()
            val depth = level[curr]
            val adl = Adj.get(curr)
            for (adn in adl)
            {
                if (adn.dest == dest)
                {
                    return depth + 1
                }
                if (visited[adn.dest] == false)
                {
                    visited[adn.dest] = true
                    que.add(adn.dest)
                    level[adn.dest] = depth + 1
                }
            }
        }
        return -1
    }

    fun isCyclePresentUndirectedDFS(index:Int, parentIndex:Int, visited:BooleanArray):Boolean {
        visited[index] = true
        var dest:Int
        val adl = Adj.get(index)
        for (adn in adl)
        {
            dest = adn.dest
            if (visited[dest] == false)
            {
                if (isCyclePresentUndirectedDFS(dest, index, visited))
                    return true
            }
            else if (parentIndex != dest)
                return true
        }
        return false
    }

    fun isCyclePresentUndirected():Boolean {
        val visited = BooleanArray(count)
        for (i in 0 until count)
            if (visited[i] == false)
                if (isCyclePresentUndirectedDFS(i, -1, visited))
                    return true
        return false
    }

    fun isCyclePresentDFS(index:Int, visited:BooleanArray, marked:IntArray):Boolean {
        visited[index] = true
        marked[index] = 1
        val adl = Adj.get(index)
        for (adn in adl)
        {
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
    fun isCyclePresent():Boolean {
        val visited = BooleanArray(count)
        val marked = IntArray(count)
        for (index in 0 until count)
        {
            if (visited[index] == false)
                if (isCyclePresentDFS(index, visited, marked))
                    return true
        }
        return false
    }
    fun isCyclePresentDFSColor(index:Int, visited:IntArray):Boolean {
        visited[index] = 1 // 1 = grey
        var dest:Int
        val adl = Adj.get(index)
        for (adn in adl)
        {
            dest = adn.dest
            if (visited[dest] == 1)
            // "Grey":
                return true
            if (visited[dest] == 0)
            // "White":
                if (isCyclePresentDFSColor(dest, visited))
                    return true
        }
        visited[index] = 2 // "Black"
        return false
    }
    fun isCyclePresentColor():Boolean {
        val visited = IntArray(count)
        for (i in 0 until count)
        {
            if (visited[i] == 0)
            // "White"
                if (isCyclePresentDFSColor(i, visited))
                    return true
        }
        return false
    }

    fun transposeGraph():Graph {
        val g = Graph(count)
        for (i in 0 until count)
        {
            val adl = Adj.get(i)
            for (adn in adl)
            {
                val dest = adn.dest
                g.addDirectedEdge(dest, i)
            }
        }
        return g
    }
    fun isConnectedUndirected():Boolean {
        val visited = BooleanArray(count)
        dfsUtil(0, visited)
        for (i in 0 until count)
        {
            if (visited[i] == false)
            {
                return false
            }
        }
        return true
    }
    /*
    * Kosaraju Algorithm
    *
    * Kosaraju’s Algorithm to find strongly connected directed graph based on DFS :
    * 1) Create a visited array of size V, and Initialize all count in visited
    * array as 0. 2) Choose any vertex and perform a DFS traversal of graph. For
    * all visited count mark them visited as 1. 3) If DFS traversal does not mark
    * all count as 1, then return 0. 4) Find transpose or reverse of graph 5)
    * Repeat step 1, 2 and 3 for the reversed graph. 6) If DFS traversal mark all
    * the count as 1, then return 1.
    */
    fun isStronglyConnected():Boolean {
        
        val visited = BooleanArray(count)
        dfsUtil(0, visited)
        for (i in 0 until count)
        {
            if (visited[i] == false)
            {
                return false
            }
        }
        val gReversed = transposeGraph()
        for (i in 0 until count)
        {
            visited[i] = false
        }
        gReversed. dfsUtil(0, visited)
        for (i in 0 until count)
        {
            if (visited[i] == false)
            {
                return false
            }
        }
        return true
    }


    fun stronglyConnectedComponent() {
        val visited = BooleanArray(count)
        val stk = Stack<Int>()
        for (i in 0 until count)
        {
            if (visited[i] == false)
            {
                dfsUtil2(i, visited, stk)
            }
        }
        val gReversed = transposeGraph()
        for (i in 0 until count)
        {
            visited[i] = false
        }
        val stk2 = Stack<Int>()
        while (stk.isEmpty() == false)
        {
            val index = stk.pop()
            if (visited[index] == false)
            {
                stk2.clear()
                gReversed.dfsUtil2(index, visited, stk2)
                println(stk2)
            }
        }
    }

    internal class EdgeComparator:Comparator<Edge> {
        public override fun compare(x:Edge, y:Edge):Int {
            if (x.cost < y.cost)
            {
                return -1
            }
            if (x.cost > y.cost)
            {
                return 1
            }
            return 0
        }
    }

    fun prims() {
        val previous = IntArray(count)
        val dist = IntArray(count)
        val visited = BooleanArray(count)
        var source = 1
        for (i in 0 until count)
        {
            previous[i] = -1
            dist[i] = Int.MAX_VALUE // infinite
        }
        dist[source] = 0
        previous[source] = -1
        val comp = EdgeComparator()
        val queue = PriorityQueue<Edge>(100, comp)
        var node = Edge(source, 0)
        queue.add(node)
        while (queue.isEmpty() != true)
        {
            node = queue.peek()
            queue.remove()
            visited[source] = true
            source = node.dest
            val adl = Adj.get(source)
            for (adn in adl)
            {
                val dest = adn.dest
                val alt = adn.cost
                if (dist[dest] > alt && visited[dest] == false)
                {
                    dist[dest] = alt
                    previous[dest] = source
                    node = Edge(dest, alt)
                    queue.add(node)
                }
            }
        }
        // printing result.

        for (i in 0 until count)
        {
            if (dist[i] == Integer.MAX_VALUE)
            {
                println("Node id " + i + " prev " + previous[i] + " distance : Unreachable")
            }
            else
            {
                println("Node id " + i + " prev " + previous[i] + " distance : " + dist[i])
            }
        }
    }

    fun shortestPath(source:Int)// unweighted graph
    {
        var curr:Int
        val distance = IntArray(count)
        val path = IntArray(count)
        for (i in 0 until count)
        {
            distance[i] = -1
        }
        val que = LinkedList<Int>()
        que.add(source)
        distance[source] = 0
        while (que.isEmpty() == false)
        {
            curr = que.remove()
            val adl = Adj.get(curr)
            for (adn in adl)
            {
                if (distance[adn.dest] == -1)
                {
                    distance[adn.dest] = distance[curr] + 1
                    path[adn.dest] = curr
                    que.add(adn.dest)
                }
            }
        }
        for (i in 0 until count)
        {
            println(path[i].toString() + " to " + i + " weight " + distance[i])
        }
    }


    fun bellmanFordshortestPath(source:Int) {

        val distance = IntArray(count)
        val path = IntArray(count)
        for (i in 0 until count)
        {
            distance[i] = Int.MAX_VALUE // infinite
            path[i] = -1
        }
        distance[source] = 0
        // Outer loop will run (V-1) number of times.
        // Inner for loop and while loop runs combined will
        // run for Edges number of times.
        // Which make the total complexity as O(V*E)
        for (i in 0 until count - 1)
        {
            for (j in 0 until count)
            {
                val adl = Adj.get(j)
                for (adn in adl)
                {
                    val newDistance = distance[j] + adn.cost
                    if (distance[adn.dest] > newDistance)
                    {
                        distance[adn.dest] = newDistance
                        path[adn.dest] = j
                    }
                }
            }
        }
        for (i in 0 until count)
        {
            println(path[i].toString() + " to " + i + " weight " + distance[i])
        }
    }


    fun dijkstra(src:Int) {
        var source = src
        val previous = IntArray(count)
        val dist = IntArray(count)
        val visited = BooleanArray(count)
        for (i in 0 until count)
        {
            previous[i] = -1
            dist[i] = Int.MAX_VALUE // infinite
        }
        dist[source] = 0
        previous[source] = -1
        val comp = EdgeComparator()
        val queue = PriorityQueue<Edge>(100, comp)
        var node = Edge(source, 0)
        queue.add(node)
        while (queue.isEmpty() != true)
        {
            node = queue.peek()
            queue.remove()
            source = node.dest
            visited[source] = true
            val adl = Adj.get(source)
            for (adn in adl)
            {
                val dest = adn.dest
                val alt = adn.cost + dist[source]
                if (dist[dest] > alt && visited[dest] == false)
                {
                    dist[dest] = alt
                    previous[dest] = source
                    node = Edge(dest, alt)
                    queue.add(node)
                }
            }
        }

        for (i in 0 until count)
        {
            if (dist[i] == Integer.MAX_VALUE)
            {
                println("Node id " + i + " prev " + previous[i] + " distance : Unreachable")
            }
            else
            {
                println("Node id " + i + " prev " + previous[i] + " distance : " + dist[i])
            }
        }
    }

    fun bestFirstSearchPQ(gph:Graph, src:Int, dest:Int):Int {
        var source = src
        val previous = IntArray(gph.count)
        val dist = IntArray(gph.count)
        val visited = BooleanArray(gph.count)
        for (i in 0 until gph.count)
        {
            previous[i] = -1
            dist[i] = Int.MAX_VALUE // infinite
        }
        val comp = EdgeComparator()
        val pq = PriorityQueue<Edge>(100, comp)
        dist[source] = 0
        previous[source] = -1
        var node = Edge(source, 0)
        pq.add(node)
        while (pq.isEmpty() != true)
        {
            node = pq.peek()
            pq.remove()
            source = node.dest
            if (source == dest)
            {
                return node.cost
            }
            visited[source] = true
            val adl = gph.Adj.get(source)
            for (adn in adl)
            {
                val curr = adn.dest
                val cost = adn.cost
                val alt = cost + dist[source]
                if (dist[curr] > alt && visited[curr] == false)
                {
                    dist[curr] = alt
                    previous[curr] = source
                    node = Edge(curr, alt)
                    pq.add(node)
                }
            }
        }
        return -1
    }
    fun isConnected():Boolean {

        val visited = BooleanArray(count)
        // Find a vertex with non - zero degree
        // DFS traversal of graph from a vertex with non - zero degree
        var adl:LinkedList<Edge>
        for (i in 0 until count)
        {
            adl = Adj.get(i)
            if (adl.size > 0)
            {
                dfsUtil(i, visited)
                break
            }
        }
        // Check if all non - zero degree count are visited
        for (i in 0 until count)
        {
            adl = Adj.get(i)
            if (adl.size > 0)
                if (visited[i] == false)
                    return false
        }
        return true
    }
    /*
 * The function returns one of the following values Return 0 if graph is not
 * Eulerian Return 1 if graph has an Euler path (Semi-Eulerian) Return 2 if
 * graph has an Euler Circuit (Eulerian)
 */
    fun isEulerian():Int {

        var odd:Int
        val inDegree:IntArray
        val outDegree:IntArray
        var adl:LinkedList<Edge>
        // Check if all non - zero degree nodes are connected
        if (isConnected() == false)
        {
            println("graph is not Eulerian")
            return 0
        }
        else
        {
            // Count odd degree
            odd = 0
            inDegree = IntArray(count)
            outDegree = IntArray(count)
            for (i in 0 until count)
            {
                adl = Adj.get(i)
                for (adn in adl)
                {
                    outDegree[i] += 1
                    inDegree[adn.dest] += 1
                }
            }
            for (i in 0 until count)
            {
                if ((inDegree[i] + outDegree[i]) % 2 != 0)
                {
                    odd += 1
                }
            }
        }
        if (odd == 0)
        {
            println("graph is Eulerian")
            return 2
        }
        else if (odd == 2)
        {
            println("graph is Semi-Eulerian")
            return 1
        }
        else
        {
            println("graph is not Eulerian")
            return 0
        }
    }
    companion object {
        fun heightTreeParentArr(arr: IntArray): Int {
            val count = arr.size
            val heightArr = IntArray(count)
            val gph = Graph(count)
            var source = 0
            for (i in 0 until count) {
                if (arr[i] != -1) {
                    gph.addDirectedEdge(arr[i], i)
                } else {
                    source = i
                }
            }
            val visited = BooleanArray(count)
            visited[source] = true
            val que = LinkedList<Int>()
            que.add(source)
            heightArr[source] = 0
            var maxHight = 0
            while (que.isEmpty() == false) {
                val curr = que.remove()
                val height = heightArr[curr]
                if (height > maxHight) {
                    maxHight = height
                }
                val adl = gph.Adj.get(curr)
                for (adn in adl) {
                    if (visited[adn.dest] == false) {
                        visited[adn.dest] = true
                        que.add(adn.dest)
                        heightArr[adn.dest] = height + 1
                    }
                }
            }
            return maxHight
        }
    }

    fun isStronglyConnected2():Boolean {
        val visited = BooleanArray(count)
        val gReversed:Graph
        var index:Int
        // Find a vertex with non - zero degree
        var adl:LinkedList<Edge>
        index = 0
        while (index < count)
        {
            adl = Adj.get(index)
            if (adl.size > 0)
                break
            index++
        }
        // DFS traversal of graph from a vertex with non - zero degree
        dfsUtil(index, visited)
        for (i in 0 until count)
        {
            adl = Adj.get(i)
            if (visited[i] == false && adl.size > 0)
                return false
        }
        gReversed = transposeGraph()
        for (i in 0 until count)
            visited[i] = false
        gReversed.dfsUtil(index, visited)
        for (i in 0 until count)
        {
            adl = Adj.get(i)
            if (visited[i] == false && adl.size > 0)
                return false
        }
        return true
    }

    fun isEulerianCycle():Boolean {
        // Check if all non - zero degree count are connected

        val inDegree = IntArray(count)
        val outDegree = IntArray(count)
        if (!isStronglyConnected2())
            return false
        // Check if in degree and out degree of every vertex is same
        for (i in 0 until count)
        {
            val adl = Adj.get(i)
            for (adn in adl)
            {
                outDegree[i] += 1
                inDegree[adn.dest] += 1
            }
        }
        for (i in 0 until count)
            if (inDegree[i] != outDegree[i])
                return false
        return true
    }
}

fun getHeight(arr:IntArray, height:IntArray, index:Int):Int {
    if (arr[index] == -1)
    {
        return 0
    }
    else
    {
        return getHeight(arr, height, arr[index]) + 1
    }
}

fun heightTreeParentArr2(arr:IntArray):Int {
    val count = arr.size
    val height = IntArray(count)
    var maxHeight = -1
    for (i in 0 until count)
    {
        height[i] = getHeight(arr, height, i)
        maxHeight = Math.max(maxHeight, height[i])
    }
    return maxHeight
}

fun main1() {
    val gph = Graph(5)
    gph.addDirectedEdge(0, 1, 3)
    gph.addDirectedEdge(0, 4, 2)
    gph.addDirectedEdge(1, 2, 1)
    gph.addDirectedEdge(2, 3, 1)
    gph.addDirectedEdge(4, 1, -2)
    gph.addDirectedEdge(4, 3, 1)
    gph.print()
    println("DFS(0, 2) : " + gph.dfs(0, 2))
    println("BFS(0, 2) : " + gph.bfs(0, 2))
    println("DFS(0, 2) : " + gph.dfsStack(0, 2))
}

fun main2() {
    val gph = Graph(6)
    gph.addDirectedEdge(5, 2, 1)
    gph.addDirectedEdge(5, 0, 1)
    gph.addDirectedEdge(4, 0, 1)
    gph.addDirectedEdge(4, 1, 1)
    gph.addDirectedEdge(2, 3, 1)
    gph.addDirectedEdge(3, 1, 1)
    gph.print()
    gph.topologicalSort()
}

fun main3() {
    val gph = Graph(5)
    gph.addDirectedEdge(0, 1, 1)
    gph.addDirectedEdge(0, 2, 1)
    gph.addDirectedEdge(2, 3, 1)
    gph.addDirectedEdge(1, 3, 1)
    gph.addDirectedEdge(3, 4, 1)
    gph.addDirectedEdge(1, 4, 1)
    gph.print()
    println("PathExist(0, 4) :: " + gph.pathExist(0, 4))
    println("countAllPath(0, 4) :: " + gph.countAllPath(0, 4))
    gph.printAllPath(0, 4)
}

fun main4() {
    val gph = Graph(7)
    gph.addDirectedEdge(0, 1, 1)
    gph.addDirectedEdge(0, 2, 1)
    gph.addDirectedEdge(1, 3, 1)
    gph.addDirectedEdge(4, 1, 1)
    gph.addDirectedEdge(6, 4, 1)
    gph.addDirectedEdge(5, 6, 1)
    gph.addDirectedEdge(5, 2, 1)
    gph.addDirectedEdge(6, 0, 1)
    gph.print()
    print("rootVertex :: " + gph.rootVertex())
}

fun main5() {
    val gph = Graph(4)
    gph.addDirectedEdge(0, 1, 1)
    gph.addDirectedEdge(0, 2, 1)
    gph.addDirectedEdge(1, 2, 1)
    gph.addDirectedEdge(2, 0, 1)
    gph.addDirectedEdge(2, 3, 1)
    gph.addDirectedEdge(3, 3, 1)
    val tc = gph.transitiveClosure()
    for (i in 0..3)
    {
        for (j in 0..3)
        {
            print(" " + tc[i][j])
        }
        println()
    }
}

fun main6() {
    val gph = Graph(7)
    gph.addUndirectedEdge(0, 1, 1)
    gph.addUndirectedEdge(0, 2, 1)
    gph.addUndirectedEdge(0, 4, 1)
    gph.addUndirectedEdge(1, 2, 1)
    gph.addUndirectedEdge(2, 5, 1)
    gph.addUndirectedEdge(3, 4, 1)
    gph.addUndirectedEdge(4, 5, 1)
    gph.addUndirectedEdge(4, 6, 1)
    gph.print()
    gph.bfsLevelNode(1)
    println(gph.bfsDistance(1, 6))
}

fun main7() {
    val gph = Graph(6)
    gph.addUndirectedEdge(0, 1, 1)
    gph.addUndirectedEdge(1, 2, 1)
    gph.addUndirectedEdge(3, 4, 1)
    gph.addUndirectedEdge(4, 2, 1)
    gph.addUndirectedEdge(2, 5, 1)
    gph.addUndirectedEdge(4, 1, 1);
    println(gph.isCyclePresentUndirected())
}

fun main8() {
    val gph = Graph(5)
    gph.addDirectedEdge(0, 1, 1)
    gph.addDirectedEdge(0, 2, 1)
    gph.addDirectedEdge(2, 3, 1)
    gph.addDirectedEdge(1, 3, 1)
    gph.addDirectedEdge(3, 4, 1)
    gph.addDirectedEdge(4, 1, 1)
    println(gph.isCyclePresentColor())
    println(gph.isCyclePresent())
}

fun main9() {
    val gph = Graph(5)
    gph.addDirectedEdge(0, 1, 1)
    gph.addDirectedEdge(1, 2, 1)
    gph.addDirectedEdge(2, 3, 1)
    gph.addDirectedEdge(3, 0, 1)
    gph.addDirectedEdge(2, 4, 1)
    gph.addDirectedEdge(4, 2, 1)
    println(" IsStronglyConnected:: " + gph.isStronglyConnected())
}

fun main10() {
    val gph = Graph(7)
    gph.addDirectedEdge(0, 1, 1)
    gph.addDirectedEdge(1, 2, 1)
    gph.addDirectedEdge(2, 0, 1)
    gph.addDirectedEdge(2, 3, 1)
    gph.addDirectedEdge(3, 4, 1)
    gph.addDirectedEdge(4, 5, 1)
    gph.addDirectedEdge(5, 3, 1)
    gph.addDirectedEdge(5, 6, 1)
    gph.stronglyConnectedComponent()
}

fun main11() {
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
    gph.print()
    println()
    gph.prims()
    println()
    //gph.dijkstra(0)
}

fun main12() {
    val gph = Graph(9)
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
    //gph.bellmanFordshortestPath(1)
    // gph.dijkstra(gph, 1);
    // gph.prims(gph);
    println("isConnectedUndirected :: " + gph.isConnectedUndirected())
}

fun main13() {
    val gph = Graph(5)
    gph.addDirectedEdge(0, 1, 3)
    gph.addDirectedEdge(0, 4, 2)
    gph.addDirectedEdge(1, 2, 1)
    gph.addDirectedEdge(2, 3, 1)
    gph.addDirectedEdge(4, 1, -2)
    gph.addDirectedEdge(4, 3, 1)
    gph.print()
    println()
    gph.bellmanFordshortestPath(0)
}

fun main14() {
    val gph = Graph(5)
    gph.addDirectedEdge(1, 0, 1)
    gph.addDirectedEdge(0, 2, 1)
    gph.addDirectedEdge(2, 1, 1)
    gph.addDirectedEdge(0, 3, 1)
    gph.addDirectedEdge(3, 4, 1)
    println("gph.isEulerian() :: " + gph.isEulerian())
}


fun main15() {
    val parentArray = intArrayOf(-1, 0, 1, 2, 3)
    println(Graph.heightTreeParentArr(parentArray))
    println(heightTreeParentArr2(parentArray))
}


fun main16() {
    val gph = Graph(5)
    gph.addDirectedEdge(0, 1, 1)
    gph.addDirectedEdge(1, 2, 1)
    gph.addDirectedEdge(2, 0, 1)
    gph.addDirectedEdge(0, 4, 1)
    gph.addDirectedEdge(4, 3, 1)
    gph.addDirectedEdge(3, 0, 1)
    println("isEulerianCycle :: " + gph.isEulerianCycle())
}

fun main() {
    main1()
    main2()
    main3()
    main4()
    main5()
    main6()
    main7()
    main8()
    main9()
    main10()
    main11()
    main12()
    main13()
    main14()
    main15()
    main16()
}