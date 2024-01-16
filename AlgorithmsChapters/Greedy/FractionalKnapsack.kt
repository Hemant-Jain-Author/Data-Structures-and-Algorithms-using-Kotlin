
class Items internal constructor(var wt: Int, var cost: Int) : Comparable<Items> {
    var density: Double

    init {
        density = cost.toDouble() / wt
    }

    override operator fun compareTo(other: Items): Int { // decreasing order.
        return (other.density - density).toInt()
    }
}

fun getMaxCostFractional(wt: IntArray, cost: IntArray, capa: Int): Double {
    var capacity = capa
    var totalCost = 0.0
    val n = wt.size
    val itemList = arrayOfNulls<Items>(n)
    for (i in 0 until n) itemList[i] = Items(wt[i], cost[i])
    itemList.sort()
    
    for (i in 0 until n) {
        if (capacity - itemList[i]!!.wt >= 0) {
            capacity -= itemList[i]!!.wt
            totalCost += itemList[i]!!.cost.toDouble()
        } else {
            totalCost += itemList[i]!!.density * capacity
            break
        }
    }
    return totalCost
}


// Testing code.
    fun main() {
    val wt = intArrayOf(10, 40, 20, 30)
    val cost = intArrayOf(60, 40, 90, 120)
    val capacity = 50
    val maxCost = getMaxCostFractional(wt, cost, capacity)
    println("Maximum cost obtained = $maxCost")
}

