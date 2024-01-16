import java.util.Stack
import java.util.Scanner
import java.util.ArrayDeque
import java.util.Arrays

fun function2() {
    println("fun2 line 1")
}

fun function1() {
    println("fun1 line 1")
    function2()
    println("fun1 line 2")
}

/* Testing code */
fun main1() {
    println("main line 1")
    function1()
    println("main line 2")
}

/*
main line 1
fun1 line 1
fun2 line 1
fun1 line 2
main line 2
*/

fun isBalancedParenthesis(expn: String): Boolean {
    val stk: Stack<Char> = Stack<Char>()
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

/* Testing code */
fun main2() {
    val expn = "{()}[]"
    val value = isBalancedParenthesis(expn)
    println("isBalancedParenthesis: $value")
}

/*
isBalancedParenthesis: true
*/

fun postfixEvaluate(expn: String?): Int {
    val stk: Stack<Int> = Stack<Int>()
    val tokens: Scanner = Scanner(expn)
    while (tokens.hasNext()) {
        if (tokens.hasNextInt()) {
            stk.push(tokens.nextInt())
        } else {
            val num1: Int = stk.pop()
            val num2: Int = stk.pop()
            val op: Char = tokens.next().get(0)
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

/* Testing code */
fun main3() {
    val expn = "6 5 2 3 + 8 * + 3 + *"
    val value = postfixEvaluate(expn)
    println("Result after Evaluation: $value")
}

/*
Result after Evaluation: 288
*/

fun precedence(x: Char): Int {
    if (x == '(') {
        return 0
    }
    if (x == '+' || x == '-') {
        return 1
    }
    if (x == '*' || x == '/' || x == '%') return 2
    return if (x == '^') {
        3
    } else 4
}

fun infixToPostfix(expn: String): String {
    var output = ""
    val out: CharArray = infixToPostfix(expn.toCharArray())
    for (ch in out) {
        output = output + ch
    }
    return output
}

fun infixToPostfix(expn: CharArray): CharArray {
    val stk: Stack<Char> = Stack<Char>()
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
                ')' -> while (stk.isEmpty() == false) {
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

/* Testing code */
fun main4() {
    val expn = "10+((3))*5/(16-4)"
    val value = infixToPostfix(expn)
    println("Infix Expn: $expn")
    println("Postfix Expn: $value")
}

/*
Infix Expn: 10+((3))*5/(16-4)
Postfix Expn: 10 3 5 * 16 4 - / +
*/

fun infixToPrefix(expn: String): String {
    var arr: CharArray = expn.toCharArray()
    reverseString(arr)
    replaceParenthesis(arr)
    arr = infixToPostfix(arr)
    reverseString(arr)
    return String(arr)
}

fun replaceParenthesis(a: CharArray) {
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

/* Testing code */
fun main5() {
    val expn = "10+((3))*5/(16-4)"
    val value = infixToPrefix(expn)
    println("Infix Expn: $expn")
    println("Prefix Expn: $value")
}

/*
Infix Expn: 10+((3))*5/(16-4)
Prefix Expn:  +10 * 3 / 5  - 16 4
*/

fun stockSpanRange(arr: IntArray): IntArray {
    val SR = IntArray(arr.size)
    SR[0] = 1
    for (i in 1 until arr.size) {
        SR[i] = 1
        var j: Int = i - 1
        while (j >= 0 && arr[i] >= arr[j]) {
            SR[i]++
            j--
        }
    }
    return SR
}

fun stockSpanRange2(arr: IntArray): IntArray {
    val stk: Stack<Int> = Stack<Int>()
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

/* Testing code */
fun main6() {
    val arr = intArrayOf(6, 5, 4, 3, 2, 4, 5, 7, 9)
    var value = stockSpanRange(arr)
    print("stockSpanRange : ")
    for (`val` in value) print("$`val` ")
    println()
    value = stockSpanRange2(arr)
    print("stockSpanRange : ")
    for (`val` in value) print("$`val` ")
    println()
}

/*
stockSpanRange : 1 1 1 1 1 4 6 8 9
stockSpanRange : 1 1 1 1 1 4 6 8 9
*/

fun getMaxArea(arr: IntArray): Int {
    val size = arr.size
    var maxArea = -1
    var currArea: Int
    var minHeight : Int
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

fun getMaxArea2(arr: IntArray): Int {
    val size = arr.size
    val stk: Stack<Int> = Stack<Int>()
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

/* Testing code */
fun main7() {
    val arr = intArrayOf(7, 6, 5, 4, 4, 1, 6, 3, 1)
    var value = getMaxArea(arr)
    println("getMaxArea :: $value")
    value = getMaxArea2(arr)
    println("getMaxArea :: $value")
}

/*
getMaxArea :: 20
getMaxArea :: 20
*/

fun stockAnalystAdd(stk: Stack<Int>, value: Int) {
    while (!stk.isEmpty() && stk.peek() <= value) stk.pop()
    stk.push(value)
}

/* Testing code */
fun main8() {
    val arr = intArrayOf(20, 19, 10, 21, 40, 35, 39, 50, 45, 42)
    val stk: Stack<Int> = Stack<Int>()
    for (i in arr.indices.reversed()) stockAnalystAdd(stk, arr[i])
    println(stk)
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

/* Testing code */
fun main9() {
    val stk: Stack<Int> = Stack<Int>()
    stk.push(1)
    stk.push(3)
    stk.push(4)
    println(stk)
    sortedInsert(stk, 2)
    println(stk)
}

/*
[1, 3, 4]
[1, 2, 3, 4]
*/

fun sortStack(stk: Stack<Int>) {
    val temp: Int
    if (stk.isEmpty() == false) {
        temp = stk.pop()
        sortStack(stk)
        sortedInsert(stk, temp)
    }
}

fun sortStack2(stk: Stack<Int>) {
    var temp: Int
    val stk2: Stack<Int> = Stack<Int>()
    while (stk.isEmpty() == false) {
        temp = stk.pop()
        while (stk2.isEmpty() == false && stk2.peek() < temp) 
            stk.push(stk2.pop())
        stk2.push(temp)
    }
    while (stk2.isEmpty() == false) 
        stk.push(stk2.pop())
}

/* Testing code */
fun main10() {
    var stk: Stack<Int> = Stack<Int>()
    stk.push(3)
    stk.push(1)
    stk.push(4)
    stk.push(2)
    println(stk)
    sortStack(stk)
    println(stk)
    stk = Stack<Int>()
    stk.push(3)
    stk.push(1)
    stk.push(4)
    stk.push(2)
    println(stk)
    sortStack2(stk)
    println(stk)
}

/*
[3, 1, 4, 2]
[1, 2, 3, 4]
[3, 1, 4, 2]
[1, 2, 3, 4]
*/

fun bottomInsert(stk: Stack<Int>, element: Int) {
    val temp: Int
    if (stk.isEmpty()) stk.push(element) else {
        temp = stk.pop()
        bottomInsert(stk, element)
        stk.push(temp)
    }
}

/* Testing code */
fun main11() {
    val stk: Stack<Int> = Stack<Int>()
    stk.push(1)
    stk.push(2)
    stk.push(3)
    println(stk)
    bottomInsert(stk, 4)
    println(stk)
}

/*
[1, 2, 3]
[4, 1, 2, 3]
*/

fun <T> bottomInsert(stk: Stack<T>, value: T) {
    if (stk.isEmpty()) {
        stk.push(value)
    } else {
        val out: T = stk.pop()
        bottomInsert(stk, value)
        stk.push(out)
    }
}

fun <T> reverseStack(stk: Stack<T>) {
    if (stk.isEmpty()) {
        return
    } else {
        val value: T = stk.pop()
        reverseStack<T>(stk)
        bottomInsert(stk, value)
    }
}

fun reverseStack2(stk: Stack<Int>) {
    val que: ArrayDeque<Int> = ArrayDeque<Int>()
    while (stk.isEmpty() == false) que.add(stk.pop())
    while (que.isEmpty() == false) stk.push(que.remove())
}

fun reverseKElementInStack(stk: Stack<Int>, k: Int) {
    val que: ArrayDeque<Int> = ArrayDeque<Int>()
    var i = 0
    while (stk.isEmpty() == false && i < k) {
        que.add(stk.pop())
        i++
    }
    while (que.isEmpty() == false) stk.push(que.remove())
}

fun reverseQueue(que: ArrayDeque<Int>) {
    val stk: Stack<Int> = Stack<Int>()
    while (que.isEmpty() == false) stk.push(que.remove())
    while (stk.isEmpty() == false) que.add(stk.pop())
}

fun reverseKElementInQueue(que: ArrayDeque<Int>, k: Int) {
    val stk: Stack<Int> = Stack<Int>()
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

/* Testing code */
fun main12() {
    val stk: Stack<Int> = Stack<Int>()
    stk.push(1)
    stk.push(2)
    stk.push(3)
    println(stk)
}

// [1, 2, 3]

/* Testing code */
fun main13() {
    val stk: Stack<Int> = Stack<Int>()
    stk.push(1)
    stk.push(2)
    stk.push(3)
    stk.push(4)
    println(stk)
    reverseStack<Int>(stk)
    println(stk)
    reverseStack2(stk)
    println(stk)
    reverseKElementInStack(stk, 2)
    println(stk)
    println()
}

/*
[1, 2, 3, 4]
[4, 3, 2, 1]
[1, 2, 3, 4]
[1, 2, 4, 3]
*/

/* Testing code */
fun main14() {
    val que: ArrayDeque<Int> = ArrayDeque<Int>()
    que.add(1)
    que.add(2)
    que.add(3)
    println(que)
    reverseQueue(que)
    println(que)
    reverseKElementInQueue(que, 2)
    println(que)
}

/*
[1, 2, 3]
[3, 2, 1]
[2, 3, 1]
*/

fun maxDepthParenthesis(expn: String, size: Int): Int {
    val stk: Stack<Char> = Stack<Char>()
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

/* Testing code */
fun main15() {
    val expn = "((((A)))((((BBB()))))()()()())"
    val size = expn.length
    println("Max depth parenthesis is " + maxDepthParenthesis(expn, size))
    println("Max depth parenthesis is " + maxDepthParenthesis2(expn, size))
}

/*
Max depth parenthesis is 6
Max depth parenthesis is 6
*/

fun longestContBalParen(string: String, size: Int): Int {
    val stk: Stack<Int> = Stack<Int>()
    stk.push(-1)
    var length = 0
    for (i in 0 until size) {
        if (string[i] == '(') stk.push(i) else {
            stk.pop()
            if (stk.size != 0) 
                length = Math.max(length, i - stk.peek()) else stk.push(i)
        }
    }
    return length
}

fun longestContBalParen2(string: String, size: Int): Int {
    val stk: Stack<Int> = Stack<Int>()
    var length = 0
    for (i in 0 until size) {
        if (string[i] == '(') stk.push(i) else  // string[i] == ')'
        {
            if (stk.size != 0) {
                length = Math.max(length, i - stk.peek() + 1)
                stk.pop()
            }
        }
    }
    return length
}

/* Testing code */
fun main16() {
    val expn = "())((()))(())()(()"
    val size = expn.length
    println("longestContBalParen " + longestContBalParen(expn, size))
    println("longestContBalParen " + longestContBalParen2(expn, size))
}

// longestContBalParen 12

fun reverseParenthesis(expn: String, size: Int): Int {
    val stk: Stack<Char> = Stack<Char>()
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
        else if (ch == ')') { 
            if (stk.size != 0 && stk.peek() == '(') 
                stk.pop() 
            else 
                stk.push(')')
        }    
    }
    while (stk.size != 0) {
        if (stk.pop() == '(') 
            openCount += 1 
        else 
            closeCount += 1
    }
    return Math.ceil(openCount / 2.0).toInt() + Math.ceil(closeCount / 2.0).toInt()
}

/* Testing code */
fun main17() {
    val expn2 = ")(())((("
    val size = expn2.length
    val value = reverseParenthesis(expn2, size)
    println("reverse Parenthesis is : $value")
}

// reverse Parenthesis is : 3

fun findDuplicateParenthesis(expn: String, size: Int): Boolean {
    val stk: Stack<Char> = Stack<Char>()
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

/* Testing code */
fun main18() {
    val expn = "(((a+b))+c)"
    val size = expn.length
    val value = findDuplicateParenthesis(expn, size)
    println("Duplicate Found : $value")
}

fun printParenthesisNumber(expn: String, size: Int) {
    var ch: Char
    val stk: Stack<Int> = Stack<Int>()
    var output = String()
    var count = 1
    for (i in 0 until size) {
        ch = expn[i]
        if (ch == '(') {
            stk.push(count)
            output += count
            output += " "
            count += 1
        } else if (ch == ')') {
            output += stk.pop()
            output += " "
        }
    }
    println("Parenthesis Count :: $output")
}

/* Testing code */
fun main19() {
    val expn1 = "(((a+(b))+(c+d)))"
    printParenthesisNumber(expn1, expn1.length)
}

/*
Parenthesis Count :: 1 2 3 4 4 3 5 5 2 1
*/

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
    println()
}

fun nextLargerElement2(arr: IntArray, size: Int) {
    val stk: Stack<Int> = Stack<Int>()
    val output = IntArray(size)
    var index : Int
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
    println()
}

fun nextSmallerElement(arr: IntArray, size: Int) {
    val output = IntArray(size)
    Arrays.fill(output, -1)
    for (i in 0 until size) {
        for (j in i + 1 until size) {
            if (arr[j] < arr[i]) {
                output[i] = arr[j]
                break
            }
        }
    }
    for (`val` in output) print("$`val` ")
    println()
}

fun nextSmallerElement2(arr: IntArray, size: Int) {
    val stk: Stack<Int> = Stack<Int>()
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
    println()
}

/* Testing code */
fun main20() {
    val arr = intArrayOf(13, 21, 3, 6, 20, 3)
    val size = arr.size
    nextLargerElement(arr, size)
    nextLargerElement2(arr, size)
    nextSmallerElement(arr, size)
    nextSmallerElement2(arr, size)
}

/*
21 -1 6 20 -1 -1
21 -1 6 20 -1 -1
3 3 -1 3 3 -1
*/

fun nextLargerElementCircular(arr: IntArray, size: Int) {
    val output = IntArray(size)
    Arrays.fill(output, -1)
    for (i in 0 until size) {
        for (j in 1 until size) {
            if (arr[i] < arr[(i + j) % size]) {
                output[i] = arr[(i + j) % size]
                break
            }
        }
    }
    for (`val` in output) 
        print("$`val` ")
    println()
}

fun nextLargerElementCircular2(arr: IntArray, size: Int) {
    val stk: Stack<Int> = Stack<Int>()
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
    println()
}

/* Testing code */
fun main21() {
    val arr = intArrayOf(6, 3, 9, 8, 10, 2, 1, 15, 7)
    nextLargerElementCircular(arr, arr.size)
    nextLargerElementCircular2(arr, arr.size)
}

// 9 9 10 10 15 15 15 -1 9

fun isKnown(relation: Array<IntArray>, a: Int, b: Int): Boolean {
    return if (relation[a][b] == 1) true else false
}

fun findCelebrity(relation: Array<IntArray>, count: Int): Int {
    var i: Int
    var j: Int
    var cel : Boolean
    i = 0
    while (i < count) {
        cel = true
        j = 0
        while (j < count) {
            if (i != j && (!isKnown(relation, j, i) || isKnown(relation, i, j))) {
                cel = false
                break
            }
            j++
        }
        if (cel == true) return i
        i++
    }
    return -1
}

fun findCelebrity2(relation: Array<IntArray>, count: Int): Int {
    val stk: Stack<Int> = Stack<Int>()
    var first : Int
    var second : Int
    for (i in 0 until count) {
        stk.push(i)
    }
    first = stk.pop()
    while (stk.size != 0) {
        second = stk.pop()
        if (isKnown(relation, first, second)) first = second
    }
    for (i in 0 until count) {
        if (first != i && isKnown(relation, first, i)) return -1
        if (first != i && isKnown(relation, i, first) == false) return -1
    }
    return first
}

fun findCelebrity3(relation: Array<IntArray>, count: Int): Int {
    var first = 0
    var second = 1
    for (i in 0 until count - 1) {
        if (isKnown(relation, first, second)) first = second
        second = second + 1
    }
    for (i in 0 until count) {
        if (first != i && isKnown(relation, first, i)) return -1
        if (first != i && isKnown(relation, i, first) == false) return -1
    }
    return first
}

/* Testing code */
fun main22() {
    val arr = arrayOf(
        intArrayOf(1, 0, 1, 1, 0),
        intArrayOf(1, 0, 0, 1, 0),
        intArrayOf(0, 0, 1, 1, 1),
        intArrayOf(0, 0, 0, 0, 0),
        intArrayOf(1, 1, 0, 1, 1)
    )
    println("Celebrity : " + findCelebrity3(arr, 5))
    println("Celebrity : " + findCelebrity(arr, 5))
    println("Celebrity : " + findCelebrity2(arr, 5))
}

/*
Celebrity : 3
Celebrity : 3
*/

fun isMinHeap(arr: IntArray, size: Int): Int {
    for (i in 0..(size - 2) / 2) {
        if (2 * i + 1 < size) {
            if (arr[i] > arr[2 * i + 1]) return 0
        }
        if (2 * i + 2 < size) {
            if (arr[i] > arr[2 * i + 2]) return 0
        }
    }
    return 1
}

fun isMaxHeap(arr: IntArray, size: Int): Int {
    for (i in 0..(size - 2) / 2) {
        if (2 * i + 1 < size) {
            if (arr[i] < arr[2 * i + 1]) return 0
        }
        if (2 * i + 2 < size) {
            if (arr[i] < arr[2 * i + 2]) return 0
        }
    }
    return 1
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
    main17()
    main18()
    main19()
    main20()
    main21()
    main22()
}