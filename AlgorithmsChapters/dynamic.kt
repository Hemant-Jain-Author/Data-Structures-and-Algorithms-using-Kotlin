fun TopDownFunction(ways: IntArray, target: Int): Int {
    val dp = IntArray(target)
	// fill dp[] with INVALID value
    return TopDownFunction(dp, ways, target)
}

fun TopDownFunction(dp: IntArray, ways: IntArray, target: Int): Int {
    // Base Case
    if (target == 0) return 0
    if (dp[target] != INVALID) return dp[target]

    // Recursion
    for(i in 0 to way.size-1) {
    	dp[target] = Math.min(dp[target], TopDownFunction(dp, ways, target - ways[i]) + cost)
    }
    return dp[target]
}


fun BottomUpFunction(ways: IntArray, target: Int): Int {
    val dp = IntArray(target)
	// fill dp[] array with INVALID value
	dp[0] = 0 // Base value.
	
    for (i in 1..target) {
        for (j in 0..ways.size) {
            // For all fusible ways.
             dp[i] = Math.min(dp[i], dp[i - ways[j]] + cost)
        }
    }
    return dp[target]
}

//////////////////////
fun TopDownFunction(ways: IntArray, target: Int): Int {
    val dp = IntArray(target)
    return TopDownFunction(dp, ways, target)
}

fun TopDownFunction(dp: IntArray, ways: IntArray, target: Int): Int {
    // Base Case
    if (target == 0) return 0
    if (dp[target] != 0) return dp[target]

    // Recursion
    var i = 0
    while (i < ways.size && ways[i] <= i) {
        // For all fusible ways.
        dp[target] += TopDownFunction(dp, ways, target - ways[i])
        i++
    }
    return dp[target]
}


fun BottomUpFunction(ways: IntArray, target: Int): Int {
    val dp = IntArray(target)
    for (i in 1..target) {
        var j = 0
        while (j < ways.size && ways[i] <= i) {
            // For all fusible ways.
            dp[i] += dp[i - ways[j]]
            j++
        }
    }
    return dp[target]
}

//////////////////////// 3 


fun TopDownFunction(costs: IntArray): Int {
    val n = costs.size
    val dp = Array(n) { IntArray(n) }
    for (row in dp) {
        //fill row[] array with value INVALID
    }
    return TopDownFunction(dp, costs, 0, n-1)
}

fun TopDownFunction(dp: Array<IntArray>, costs: IntArray, i: Int, j: Int): Int {
    // Base Case
    if (i == j) return 0
    if (dp[i][j] != INVALID) return dp[i][j]

    // Recursion
    for (k in i until j) {
        dp[i][j] = Math.min(dp[i][j], TopDownFunction(dp, costs, i, k)
				+ costs[k] +  TopDownFunction(dp, costs, k + 1, j))
    }
    return dp[i][j]
}

fun BottomUpFunction(costs: IntArray): Int {
    val n = costs.size
    val dp = Array(n) { IntArray(n) }
    for (row in dp) {
        //fill array row[] with value INVALID
    }
    for (l in 1 until n) { // l is length of range.
        var i = 1
        var j: Int = i + l
        while (j < n) {
            for (k in i until j) {
                dp[i][j] = Math.min(dp[i][j], dp[i][k] + costs[k] + dp[k + 1][j])
            }
            i++
            j++
        }
    }
    return dp[1][n-1]
}

////////////////////// 4


for (i in 0 until n) {
    for (j in 0 until i) {
        // incremental found pattern of sub-problem.
    }
}

for (l in 1 until n) {  // Range.
    var i = 0
    var j: Int = i + l
    while (j < n) {
        // incremental calculation of sub-problem
        // with increasing range.
        i++
        j++
    }
}

for (i in 1..m) { // First string index.
    for (j in 1..n) { // Second string index.
        // Comparison of two strings.
    }
}

///////////////////////////////// 5

fun BottomUpFunction(costs: IntArray): Int {
    val n = costs.size
    val dp = Array(n) { IntArray(2) }
    /* Initialization of 0th state of various types.*/
    dp[0][1] =  /* Initialization value */
    dp[0][0] =  /* Initialization value */
    for (i in 1 until n) {
        dp[i][1] =  /*Max values based on previous states.*/
        dp[i][0] =  /*Max values based on previous states.*/
    }
    return Math.max(dp[n-1][1], dp[n-1][0])
}

