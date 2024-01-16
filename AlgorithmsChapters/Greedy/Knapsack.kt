import java.util.Arrays

class Items internal constructor(var wt: Int, var cost: Int) : Comparable<Items> {
    var density: Double

    init {
        density = cost.toDouble() / wt
    }

    override operator fun compareTo(other: Items): Int { // decreasing order.
        return (other.density - density).toInt()
    }
}

// Approximate solution.
fun getMaxCostGreedy(wt: IntArray, cost: IntArray, capacty: Int): Int {
    var capacity = capacty
    var totalCost = 0
    val n = wt.size
    val itemList = Array(n){Items(0,0)}
    for (i in 0 until n) itemList[i] = Items(wt[i], cost[i])
    itemList.sort()
	
    var i = 0
    while (i < n && capacity > 0) {
        if (capacity - itemList[i].wt >= 0) {
            capacity -= itemList[i].wt
            totalCost += itemList[i].cost
        }
        i++
    }
    return totalCost
}

// Testing code.
fun main() {
    val wt = intArrayOf(10, 40, 20, 30)
    val cost = intArrayOf(60, 40, 90, 120)
    val capacity = 50
    val maxCost = getMaxCostGreedy(wt, cost, capacity)
    println("Maximum cost obtained = $maxCost")
}

/*
Maximum cost obtained = 150
*/