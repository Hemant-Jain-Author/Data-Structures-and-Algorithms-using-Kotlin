fun printSolution(colour: IntArray, V: Int) {
    print("Assigned colours are::")
    for (i in 0 until V) 
        print(" " + colour[i])
    println()
}

// Check if the whole graph is coloured properly.
fun isSafe2(graph: Array<BooleanArray>, colour: IntArray, V: Int): Boolean {
    for (i in 0 until V) 
        for (j in i + 1 until V) 
            if (graph[i][j] && colour[j] == colour[i]) 
                return false
    return true
}

fun graphColouring2(graph: Array<BooleanArray>, V: Int, m: Int, colour: IntArray, i: Int): Boolean {
    if (i == V) {
        if (isSafe2(graph, colour, V)) {
            printSolution(colour, V)
            return true
        }
        return false
    }

    // Assign each colour from 1 to m
    for (j in 1..m) {
        colour[i] = j
        if (graphColouring2(graph, V, m, colour, i + 1)) 
            return true
    }
    return false
}

fun graphColouring2(graph: Array<BooleanArray>, V: Int, m: Int): Boolean {
    val colour = IntArray(V)
    return if (graphColouring2(graph, V, m, colour, 0)) true else false
}

// Is it safe to colour vth vertice with c colour.
fun isSafe(graph: Array<BooleanArray>, V: Int, colour: IntArray, v: Int, c: Int): Boolean {
    for (i in 0 until V) 
        if (graph[v][i] == true && c == colour[i]) 
            return false
    return true
}

fun graphColouringUtil(graph: Array<BooleanArray>, V: Int, m: Int, colour: IntArray, i: Int): Boolean {
    if (i == V) {
        printSolution(colour, V)
        return true
    }
    for (j in 1..m) {
        if (isSafe(graph, V, colour, i, j)) {
            colour[i] = j
            if (graphColouringUtil(graph, V, m, colour, i + 1)) 
                return true
        }
    }
    return false
}

fun graphColouring(graph: Array<BooleanArray>, V: Int, m: Int): Boolean {
    val colour = IntArray(V)
    return if (graphColouringUtil(graph, V, m, colour, 0)) {
        true
    } else false
}

fun main() {
    val graph = arrayOf(
        booleanArrayOf(false, true, false, false, true),
        booleanArrayOf(true, false, true, false, true),
        booleanArrayOf(false, true, false, true, true),
        booleanArrayOf(false, false, true, false, true),
        booleanArrayOf(true, true, true, true, false))
    val V = 5 // Number of vertices
    val m = 4 // Number of colours
    if (!graphColouring2(graph, V, m)) 
        println("Solution does not exist")
    if (!graphColouring(graph, V, m)) 
        println("Solution does not exist")
}
