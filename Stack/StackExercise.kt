import java.util.Stack
import java.util.Scanner
import java.util.ArrayDeque

fun isBalancedParenthesis(expn: String): Boolean {
    val stk = Stack<Char>()
    for (ch in expn.toCharArray()) {
        when (ch) {
            '{', '[', '(' -> stk.push(ch)
            '}' -> if (stk.pop() != '{') {
                return false
            }
            ']' -> if (stk.pop() != '[') {
                return false
            }
            ')' -> if (stk.pop() != '(') {
                return false
            }
        }
    }
    return stk.isEmpty()
}

fun main1() {
    val expn = "{()}[]"
    val value = isBalancedParenthesis(expn)
    println("Given Expn:$expn")
    println("Result after isParenthesisMatched:$value")
}

fun <T> insertAtBottom(stk: Stack<T>, value: T) {
    if (stk.isEmpty()) {
        stk.push(value)
    } else {

        val out = stk.pop()
        insertAtBottom(stk, value)
        stk.push(out)
    }
}

fun <T> reverseStack(stk: Stack<T>) {
    if (stk.isEmpty()) {
        return
    } else {

        val value = stk.pop()
        reverseStack<T>(stk)
        insertAtBottom<T>(stk, value)
    }
}

fun postfixEvaluate(expn: String): Int {
    val stk = Stack<Int>()
    val tokens = Scanner(expn)

    while (tokens.hasNext()) {
        if (tokens.hasNextInt()) {
            stk.push(tokens.nextInt())
        } else {
            val num1 = stk.pop()
            val num2 = stk.pop()
            val op = tokens.next().get(0)
            when (op) {
                '+' -> stk.push(num1 + num2)
                '-' -> stk.push(num1 - num2)
                '*' -> stk.push(num1 * num2)
                '/' -> stk.push(num1 / num2)
            }
        }
    }
    tokens.close()
    return stk.pop()
}

fun main2() {
    val expn = "6 5 2 3 + 8 * + 3 + *"
    val value = postfixEvaluate(expn)
    println("Given Postfix Expn: $expn")
    println("Result after Evaluation: $value")
}

fun precedence(x: Char): Int {
    if (x == '(') {
        return 0
    }
    if (x == '+' || x == '-') {
        return 1
    }
    if (x == '*' || x == '/' || x == '%')
        return 2
    return if (x == '^') {
        3
    } else 4
}

fun infixToPostfix(expn: String): String {
    var output = ""
    val out = infixToPostfix(expn.toCharArray())

    for (ch in out) {
        output = output + ch
    }
    return output
}

fun infixToPostfix(expn: CharArray): CharArray {
    val stk = Stack<Char>()

    var output = ""
    var out: Char

    for (ch in expn) {
        if (ch <= '9' && ch >= '0') {
            output = output + ch
        } else {
            when (ch) {
                '+', '-', '*', '/', '%', '^' -> {
                    while (stk.isEmpty() == false && precedence(ch) <= precedence(stk.peek())) {
                        out = stk.pop()
                        output = "$output $out"
                    }
                    stk.push(ch)
                    output = "$output "
                }
                '(' -> stk.push(ch)
                ')' -> while (stk.isEmpty() == false ) {
                    out = stk.pop()
                    if(out != '(')
                        output = "$output $out "
                    else
                        break
                }
            }
        }
    }

    while (stk.isEmpty() == false) {
        out = stk.pop()
        output = "$output$out "
    }
    return output.toCharArray()
}

fun main3() {
    val expn = "10+((3))*5/(16-4)"
    val value = infixToPostfix(expn)
    println("Infix Expn: $expn")
    println("Postfix Expn: $value")
}

fun infixToPrefix(expn: String): String {
    var expn = expn
    var arr = expn.toCharArray()
    reverseString(arr)
    replaceParanthesis(arr)
    arr = infixToPostfix(arr)
    reverseString(arr)
    expn = arr.joinToString("")
    return expn
}

fun replaceParanthesis(a: CharArray) {
    var lower = 0
    val upper = a.size - 1
    while (lower <= upper) {
        if (a[lower] == '(') {
            a[lower] = ')'
        } else if (a[lower] == ')') {
            a[lower] = '('
        }
        lower++
    }
}

fun reverseString(expn: CharArray) {
    var lower = 0
    var upper = expn.size - 1
    var tempChar: Char
    while (lower < upper) {
        tempChar = expn[lower]
        expn[lower] = expn[upper]
        expn[upper] = tempChar
        lower++
        upper--
    }
}

fun main4() {
    val expn = "10+((3))*5/(16-4)"
    val value = infixToPrefix(expn)
    println("Infix Expn: $expn")
    println("Prefix Expn: $value")
}

fun StockSpanRange(arr: IntArray): IntArray {
    val SR = IntArray(arr.size)
    SR[0] = 1
    for (i in 1 until arr.size) {
        SR[i] = 1
        var j = i - 1
        while (j >= 0 && arr[i] >= arr[j]) {
            SR[i]++
            j--
        }
    }
    return SR
}

fun StockSpanRange2(arr: IntArray): IntArray {
    val stk = Stack<Int>()

    val SR = IntArray(arr.size)
    stk.push(0)
    SR[0] = 1
    for (i in 1 until arr.size) {
        while (!stk.isEmpty() && arr[stk.peek()] <= arr[i]) {
            stk.pop()
        }
        SR[i] = if (stk.isEmpty()) i + 1 else i - stk.peek()
        stk.push(i)
    }
    return SR
}

fun main5() {
    val arr = intArrayOf(6, 5, 4, 3, 2, 4, 5, 7, 9)
    val size = arr.size
    var value = StockSpanRange(arr)
    print("\nStockSpanRange : ")
    for (`val` in value)
        print(" $`val`")
    value = StockSpanRange2(arr)
    print("\nStockSpanRange : ")
    for (`val` in value)
        print(" $`val`")
}

fun GetMaxArea(arr: IntArray): Int {
    val size = arr.size
    var maxArea = -1
    var currArea: Int
    var minHeight = 0
    for (i in 1 until size) {
        minHeight = arr[i]
        for (j in i - 1 downTo 0) {
            if (minHeight > arr[j]) {
                minHeight = arr[j]
            }
            currArea = minHeight * (i - j + 1)
            if (maxArea < currArea) {
                maxArea = currArea
            }
        }
    }
    return maxArea
}

fun GetMaxArea2(arr: IntArray): Int {
    val size = arr.size
    val stk = Stack<Int>()
    var maxArea = 0
    var top: Int
    var topArea: Int
    var i = 0
    while (i < size) {
        while (i < size && (stk.isEmpty() || arr[stk.peek()] <= arr[i])) {
            stk.push(i)
            i++
        }
        while (!stk.isEmpty() && (i == size || arr[stk.peek()] > arr[i])) {
            top = stk.peek()
            stk.pop()
            topArea = arr[top] * if (stk.isEmpty()) i else i - stk.peek() - 1
            if (maxArea < topArea) {
                maxArea = topArea
            }
        }
    }
    return maxArea
}

fun main6() {
    val arr = intArrayOf(7, 6, 5, 4, 4, 1, 6, 3, 1)
    val size = arr.size
    var value = GetMaxArea(arr)
    println("GetMaxArea :: $value")
    value = GetMaxArea2(arr)
    println("GetMaxArea :: $value")
}

fun sortedInsert(stk: Stack<Int>, element: Int) {
    val temp: Int
    if (stk.isEmpty() || element > stk.peek())
        stk.push(element)
    else {
        temp = stk.pop()
        sortedInsert(stk, element)
        stk.push(temp)
    }
}

fun sortStack(stk: Stack<Int>) {
    val temp: Int
    if (stk.isEmpty() == false) {
        temp = stk.pop()
        sortStack(stk)
        stk.push(temp)
    }
}

fun sortStack2(stk: Stack<Int>) {
    var temp: Int
    val stk2 = Stack<Int>()
    while (stk.isEmpty() == false) {
        temp = stk.pop()
        while (stk.isEmpty() == false && stk2.peek() < temp)
            stk.push(stk2.pop())
        stk2.push(temp)
    }
    while (stk2.isEmpty() == false)
        stk.push(stk2.pop())
}

fun bottomInsert(stk: Stack<Int>, element: Int) {
    val temp: Int
    if (stk.isEmpty())
        stk.push(element)
    else {
        temp = stk.pop()
        bottomInsert(stk, element)
        stk.push(temp)
    }
}

fun reverseStack2(stk: Stack<Int>) {
    val que = ArrayDeque<Int>()
    while (stk.isEmpty() == false)
        que.add(stk.pop())

    while (que.isEmpty() == false)
        stk.push(que.remove())
}

fun reverseKElementInStack(stk: Stack<Int>, k: Int) {
    val que = ArrayDeque<Int>()
    var i = 0
    while (stk.isEmpty() == false && i < k) {
        que.add(stk.pop())
        i++
    }
    while (que.isEmpty() == false)
        stk.push(que.remove())
}

fun reverseQueue(que: ArrayDeque<Int>) {
    val stk = Stack<Int>()
    while (que.isEmpty() == false)
        stk.push(que.remove())

    while (stk.isEmpty() == false)
        que.add(stk.pop())
}

fun reverseKElementInQueue(que: ArrayDeque<Int>, k: Int) {
    val stk = Stack<Int>()
    var i = 0
    var diff: Int
    var temp: Int
    while (que.isEmpty() == false && i < k) {
        stk.push(que.remove())
        i++
    }
    while (stk.isEmpty() == false) {
        que.add(stk.pop())
    }
    diff = que.size - k
    while (diff > 0) {
        temp = que.remove()
        que.add(temp)
        diff -= 1
    }
}

fun main7() {
    val stk = Stack<Int>()
    stk.push(1)
    stk.push(2)
    stk.push(3)
    stk.push(4)
    stk.push(5)
    println(stk)
}


fun main8() {
    val stk = Stack<Int>()
    stk.push(-2)
    stk.push(13)
    stk.push(16)
    stk.push(-6)
    stk.push(40)
    println(stk)

    reverseStack2(stk)
    println(stk)
    reverseKElementInStack(stk, 2)
    println(stk)
    /*
     * System.out.println(stk); sortStack2(stk); System.out.println(stk);
     */
    val que = ArrayDeque<Int>()
    que.add(1)
    que.add(2)
    que.add(3)
    que.add(4)
    que.add(5)
    que.add(6)
    println(que)
    reverseQueue(que)
    println(que)
    reverseKElementInQueue(que, 2)
    println(que)
}

fun maxDepthParenthesis(expn: String, size: Int): Int {
    val stk = Stack<Char>()
    var maxDepth = 0
    var depth = 0
    var ch: Char

    for (i in 0 until size) {
        ch = expn[i]

        if (ch == '(') {
            stk.push(ch)
            depth += 1
        } else if (ch == ')') {
            stk.pop()
            depth -= 1
        }
        if (depth > maxDepth)
            maxDepth = depth
    }
    return maxDepth
}

fun maxDepthParenthesis2(expn: String, size: Int): Int {
    var maxDepth = 0
    var depth = 0
    var ch: Char
    for (i in 0 until size) {
        ch = expn[i]
        if (ch == '(')
            depth += 1
        else if (ch == ')')
            depth -= 1

        if (depth > maxDepth)
            maxDepth = depth
    }
    return maxDepth
}


fun main9() {
    val expn = "((((A)))((((BBB()))))()()()())"
    val size = expn.length
    val value = maxDepthParenthesis(expn, size)
    val value2 = maxDepthParenthesis2(expn, size)

    println("Given expn $expn")
    println("Max depth parenthesis is $value")
    println("Max depth parenthesis is $value2")
}

fun longestContBalParen(string: String, size: Int): Int {
    val stk = Stack<Int>()
    stk.push(-1)
    var length = 0

    for (i in 0 until size) {

        if (string[i] == '(')
            stk.push(i)
        else
        // string[i] == ')'
        {
            stk.pop()
            if (stk.size != 0)
                length = Math.max(length, i - stk.peek())
            else
                stk.push(i)
        }
    }
    return length
}

fun main10() {
    val expn = "())((()))(())()(()"
    val size = expn.length
    val value = longestContBalParen(expn, size)
    println("longestContBalParen $value")
}

fun reverseParenthesis(expn: String, size: Int): Int {
    val stk = Stack<Char>()
    var openCount = 0
    var closeCount = 0
    var ch: Char

    if (size % 2 == 1) {
        println("Invalid odd length $size")
        return -1
    }
    for (i in 0 until size) {
        ch = expn[i]
        if (ch == '(')
            stk.push(ch)
        else if (ch == ')')
            if (stk.size != 0 && stk.peek() == '(')
                stk.pop()
            else
                stk.push(')')
    }
    while (stk.size != 0) {
        if (stk.pop() == '(')
            openCount += 1
        else
            closeCount += 1
    }
    return Math.ceil(openCount / 2.0).toInt() + Math.ceil(closeCount / 2.0).toInt()
}

fun main11() {
    val expn = "())((()))(())()(()()()()))"
    val expn2 = ")(())((("
    val size = expn2.length
    val value = reverseParenthesis(expn2, size)
    println("Given expn : $expn2")
    println("reverse Parenthesis is : $value")
}

fun findDuplicateParenthesis(expn: String, size: Int): Boolean {
    val stk = Stack<Char>()
    var ch: Char
    var count: Int

    for (i in 0 until size) {
        ch = expn[i]
        if (ch == ')') {
            count = 0
            while (stk.size != 0 && stk.peek() != '(') {
                stk.pop()
                count += 1
            }
            if (count <= 1)
                return true
        } else
            stk.push(ch)
    }
    return false
}

fun main12() {
    // expn = "(((a+(b))+(c+d)))"
    // expn = "(b)"
    val expn = "(((a+b))+c)"
    println("Given expn : $expn")
    val size = expn.length
    val value = findDuplicateParenthesis(expn, size)
    println("Duplicate Found : $value")
}

fun printParenthesisNumber(expn: String, size: Int) {
    var ch: Char
    val stk = Stack<Int>()
    var output = String()
    var count = 1
    for (i in 0 until size) {
        ch = expn[i]
        if (ch == '(') {
            stk.push(count)
            output += count
            count += 1
        } else if (ch == ')')
            output += stk.pop()
    }
    println("Parenthesis Count ")
    println(output)
}

fun main13() {
    val expn1 = "(((a+(b))+(c+d)))"
    val expn2 = "(((a+b))+c)((("
    var size = expn1.length
    println("Given expn $expn1")
    printParenthesisNumber(expn1, size)
    size = expn2.length
    println("\nGiven expn $expn2")
    printParenthesisNumber(expn2, size)
}

fun nextLargerElement(arr: IntArray, size: Int) {
    val output = IntArray(size)
    var outIndex = 0
    var next: Int

    for (i in 0 until size) {
        next = -1
        for (j in i + 1 until size) {
            if (arr[i] < arr[j]) {
                next = arr[j]
                break
            }
        }
        output[outIndex++] = next
    }
    for (`val` in output)
        print("$`val` ")
}

fun nextLargerElement2(arr: IntArray, size: Int) {
    val stk = Stack<Int>()
    // output = [-1] * size;
    val output = IntArray(size)
    var index = 0
    var curr: Int

    for (i in 0 until size) {
        curr = arr[i]
        // stack always have values in decreasing order.
        while (stk.isEmpty() == false && arr[stk.peek()] <= curr) {
            index = stk.pop()
            output[index] = curr
        }
        stk.push(i)
    }
    // index which dont have any next Larger.
    while (stk.isEmpty() == false) {
        index = stk.pop()
        output[index] = -1
    }
    for (`val` in output)
        print("$`val` ")
}

fun nextSmallerElement(arr: IntArray, size: Int) {
    val stk = Stack<Int>()
    val output = IntArray(size)
    var curr: Int
    var index: Int
    for (i in 0 until size) {
        curr = arr[i]
        // stack always have values in increasing order.
        while (stk.isEmpty() == false && arr[stk.peek()] > curr) {
            index = stk.pop()
            output[index] = curr
        }
        stk.push(i)
    }
    // index which dont have any next Smaller.
    while (stk.isEmpty() == false) {
        index = stk.pop()
        output[index] = -1
    }
    for (`val` in output)
        print("$`val` ")
}

fun main14() {
    val arr = intArrayOf(13, 21, 3, 6, 20, 3)
    val size = arr.size
    nextLargerElement(arr, size)
    nextLargerElement2(arr, size)
    nextSmallerElement(arr, size)
}

fun nextLargerElementCircular(arr: IntArray, size: Int) {
    val stk = Stack<Int>()
    var curr: Int
    var index: Int
    val output = IntArray(size)
    for (i in 0 until 2 * size - 1) {
        curr = arr[i % size]
        // stack always have values in decreasing order.
        while (stk.isEmpty() == false && arr[stk.peek()] <= curr) {
            index = stk.pop()
            output[index] = curr
        }
        stk.push(i % size)
    }
    // index which dont have any next Larger.
    while (stk.isEmpty() == false) {
        index = stk.pop()
        output[index] = -1
    }
    for (`val` in output)
        print("$`val` ")
}

fun main15() {
    val arr = intArrayOf(6, 3, 9, 8, 10, 2, 1, 15, 7)
    val size = arr.size
    nextLargerElementCircular(arr, size)
}

fun RottenFruitUtil(
    arr: Array<IntArray>, maxCol: Int, maxRow: Int, currCol: Int, currRow: Int, traversed: Array<IntArray>,
    day: Int
) { // Range check
    if (currCol < 0 || currCol >= maxCol || currRow < 0 || currRow >= maxRow)
        return
    // Traversable and rot if not already rotten.
    if (traversed[currCol][currRow] <= day || arr[currCol][currRow] == 0)
        return
    // Update rot time.
    traversed[currCol][currRow] = day
    // each line corresponding to 4 direction.
    RottenFruitUtil(arr, maxCol, maxRow, currCol - 1, currRow, traversed, day + 1)
    RottenFruitUtil(arr, maxCol, maxRow, currCol + 1, currRow, traversed, day + 1)
    RottenFruitUtil(arr, maxCol, maxRow, currCol, currRow + 1, traversed, day + 1)
    RottenFruitUtil(arr, maxCol, maxRow, currCol, currRow - 1, traversed, day + 1)
}

fun RottenFruit(arr: Array<IntArray>, maxCol: Int, maxRow: Int): Int {
    val traversed = Array(maxCol) { IntArray(maxRow) }
    for (i in 0 until maxCol) {
        for (j in 0 until maxRow) {
            traversed[i][j] = Integer.MAX_VALUE
        }
    }

    for (i in 0 until maxCol - 1) {
        for (j in 0 until maxRow - 1) {
            if (arr[i][j] == 2)
                RottenFruitUtil(arr, maxCol, maxRow, i, j, traversed, 0)
        }
    }

    var maxDay = 0
    for (i in 0 until maxCol - 1) {
        for (j in 0 until maxRow - 1) {
            if (arr[i][j] == 1) {
                if (traversed[i][j] == Integer.MAX_VALUE)
                    return -1
                if (maxDay < traversed[i][j])
                    maxDay = traversed[i][j]
            }
        }
    }
    return maxDay
}

fun main16() {
    val arr = arrayOf(
        intArrayOf(1, 0, 1, 1, 0),
        intArrayOf(2, 1, 0, 1, 0),
        intArrayOf(0, 0, 0, 2, 1),
        intArrayOf(0, 2, 0, 0, 1),
        intArrayOf(1, 1, 0, 0, 1)
    )
    println(RottenFruit(arr, 5, 5))
}

fun StepsOfKnightUtil(size: Int, currCol: Int, currRow: Int, traversed: Array<IntArray>, dist: Int) {
    // Range check
    if (currCol < 0 || currCol >= size || currRow < 0 || currRow >= size)
        return

    // Traversable and rot if not already rotten.
    if (traversed[currCol][currRow] <= dist)
        return

    // Update rot time.
    traversed[currCol][currRow] = dist
    // each line corresponding to 4 direction.
    StepsOfKnightUtil(size, currCol - 2, currRow - 1, traversed, dist + 1)
    StepsOfKnightUtil(size, currCol - 2, currRow + 1, traversed, dist + 1)
    StepsOfKnightUtil(size, currCol + 2, currRow - 1, traversed, dist + 1)
    StepsOfKnightUtil(size, currCol + 2, currRow + 1, traversed, dist + 1)
    StepsOfKnightUtil(size, currCol - 1, currRow - 2, traversed, dist + 1)
    StepsOfKnightUtil(size, currCol + 1, currRow - 2, traversed, dist + 1)
    StepsOfKnightUtil(size, currCol - 1, currRow + 2, traversed, dist + 1)
    StepsOfKnightUtil(size, currCol + 1, currRow + 2, traversed, dist + 1)
}

fun StepsOfKnight(size: Int, srcX: Int, srcY: Int, dstX: Int, dstY: Int): Int {
    val traversed = Array(size) { IntArray(size) }
    for (i in 0 until size) {
        for (j in 0 until size) {
            traversed[i][j] = Integer.MAX_VALUE
        }
    }

    StepsOfKnightUtil(size, srcX - 1, srcY - 1, traversed, 0)
    return traversed[dstX - 1][dstY - 1]
}

fun main17() {
    println(StepsOfKnight(20, 10, 10, 20, 20))
}

fun DistNearestFillUtil(
    arr: Array<IntArray>, maxCol: Int, maxRow: Int, currCol: Int, currRow: Int,
    traversed: Array<IntArray>, dist: Int
) { // Range check
    if (currCol < 0 || currCol >= maxCol || currRow < 0 || currRow >= maxRow)
        return
    // Traversable if their is a better distance.
    if (traversed[currCol][currRow] <= dist)
        return
    // Update distance.
    traversed[currCol][currRow] = dist
    // each line corresponding to 4 direction.
    DistNearestFillUtil(arr, maxCol, maxRow, currCol - 1, currRow, traversed, dist + 1)
    DistNearestFillUtil(arr, maxCol, maxRow, currCol + 1, currRow, traversed, dist + 1)
    DistNearestFillUtil(arr, maxCol, maxRow, currCol, currRow + 1, traversed, dist + 1)
    DistNearestFillUtil(arr, maxCol, maxRow, currCol, currRow - 1, traversed, dist + 1)
}

fun DistNearestFill(arr: Array<IntArray>, maxCol: Int, maxRow: Int) {
    val traversed = Array(maxCol) { IntArray(maxRow) }
    for (i in 0 until maxCol) {
        for (j in 0 until maxRow) {
            traversed[i][j] = Integer.MAX_VALUE
        }
    }
    for (i in 0 until maxCol) {
        for (j in 0 until maxRow) {
            if (arr[i][j] == 1)
                DistNearestFillUtil(arr, maxCol, maxRow, i, j, traversed, 0)
        }
    }

    for (i in 0 until maxCol) {
        for (j in 0 until maxRow) {
            println("" + traversed[i][j])
        }
        println("\n")
    }
}

fun main18() {
    val arr = arrayOf(
        intArrayOf(1, 0, 1, 1, 0),
        intArrayOf(1, 1, 0, 1, 0),
        intArrayOf(0, 0, 0, 0, 1),
        intArrayOf(0, 0, 0, 0, 1),
        intArrayOf(0, 0, 0, 0, 1)
    )
    DistNearestFill(arr, 5, 5)
}

fun findLargestIslandUtil(
    arr: Array<IntArray>, maxCol: Int, maxRow: Int, currCol: Int, currRow: Int, value: Int,
    traversed: Array<IntArray>
): Int {
    if (currCol < 0 || currCol >= maxCol || currRow < 0 || currRow >= maxRow)
        return 0
    if (traversed[currCol][currRow] == 1 || arr[currCol][currRow] != value)
        return 0
    traversed[currCol][currRow] = 1
    // each call corresponding to 8 direction.
    return (1 + findLargestIslandUtil(arr, maxCol, maxRow, currCol - 1, currRow - 1, value, traversed)
            + findLargestIslandUtil(arr, maxCol, maxRow, currCol - 1, currRow, value, traversed)
            + findLargestIslandUtil(arr, maxCol, maxRow, currCol - 1, currRow + 1, value, traversed)
            + findLargestIslandUtil(arr, maxCol, maxRow, currCol, currRow - 1, value, traversed)
            + findLargestIslandUtil(arr, maxCol, maxRow, currCol, currRow + 1, value, traversed)
            + findLargestIslandUtil(arr, maxCol, maxRow, currCol + 1, currRow - 1, value, traversed)
            + findLargestIslandUtil(arr, maxCol, maxRow, currCol + 1, currRow, value, traversed)
            + findLargestIslandUtil(arr, maxCol, maxRow, currCol + 1, currRow + 1, value, traversed))
}

fun findLargestIsland(arr: Array<IntArray>, maxCol: Int, maxRow: Int): Int {
    var maxVal = 0
    var currVal = 0
    val traversed = Array(maxCol) { IntArray(maxRow) }
    for (i in 0 until maxCol) {
        for (j in 0 until maxRow) {
            traversed[i][j] = Integer.MAX_VALUE
        }
    }
    for (i in 0 until maxCol) {
        for (j in 0 until maxRow) {
            run {
                currVal = findLargestIslandUtil(arr, maxCol, maxRow, i, j, arr[i][j], traversed)
                if (currVal > maxVal)
                    maxVal = currVal
            }
        }
    }
    return maxVal
}

fun main19() {
    val arr = arrayOf(
        intArrayOf(1, 0, 1, 1, 0),
        intArrayOf(1, 0, 0, 1, 0),
        intArrayOf(0, 1, 1, 1, 1),
        intArrayOf(0, 1, 0, 0, 0),
        intArrayOf(1, 1, 0, 0, 1)
    )
    println("Largest Island : " + findLargestIsland(arr, 5, 5))
}

fun isKnown(relation: Array<IntArray>, a: Int, b: Int): Boolean {
    return if (relation[a][b] == 1) true else false
}

fun findCelebrity(relation: Array<IntArray>, count: Int): Int {
    val stk = Stack<Int>()
    var first = 0
    var second = 0
    for (i in 0 until count) {
        stk.push(i)
    }
    first = stk.pop()
    while (stk.size != 0) {
        second = stk.pop()
        if (isKnown(relation, first, second))
            first = second
    }
    for (i in 0 until count) {
        if (first != i && isKnown(relation, first, i))
            return -1
        if (first != i && isKnown(relation, i, first) == false)
            return -1
    }
    return first
}

fun findCelebrity2(relation: Array<IntArray>, count: Int): Int {
    var first = 0
    var second = 1

    for (i in 0 until count - 1) {
        if (isKnown(relation, first, second))
            first = second
        second = second + 1
    }
    for (i in 0 until count) {
        if (first != i && isKnown(relation, first, i))
            return -1
        if (first != i && isKnown(relation, i, first) == false)
            return -1
    }
    return first
}

fun main20() {
    val arr = arrayOf(
        intArrayOf(1, 0, 1, 1, 0),
        intArrayOf(1, 0, 0, 1, 0),
        intArrayOf(0, 0, 1, 1, 1),
        intArrayOf(0, 0, 0, 0, 0),
        intArrayOf(1, 1, 0, 1, 1)
    )

    println("Celebrity : " + findCelebrity(arr, 5))
    println("Celebrity : " + findCelebrity2(arr, 5))
}

fun IsMinHeap(arr: IntArray, size: Int): Int {
    for (i in 0..(size - 2) / 2) {
        if (2 * i + 1 < size) {
            if (arr[i] > arr[2 * i + 1])
                return 0
        }
        if (2 * i + 2 < size) {
            if (arr[i] > arr[2 * i + 2])
                return 0
        }
    }
    return 1
}

fun IsMaxHeap(arr: IntArray, size: Int): Int {
    for (i in 0..(size - 2) / 2) {
        if (2 * i + 1 < size) {
            if (arr[i] < arr[2 * i + 1])
                return 0
        }
        if (2 * i + 2 < size) {
            if (arr[i] < arr[2 * i + 2])
                return 0
        }
    }
    return 1
}

fun main(args: Array<String>) {
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
    main17()
    main18()
    main19()
    main20()
}