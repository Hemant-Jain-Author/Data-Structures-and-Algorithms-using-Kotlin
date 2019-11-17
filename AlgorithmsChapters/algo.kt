fun isPrime(n:Int):Int {
    var answer = if (n > 1) 1 else 0
    var i = 2
    while (i * i <= n)
    {
        if (n % i == 0)
        {
            answer = 1
            break
        }
        ++i
    }
    return answer
}

fun fibonacci(n:Int):Int {
    if (n <= 1)
    {
        return n
    }
    return fibonacci(n - 1) + fibonacci(n - 2)
}

fun fibonacci2(n:Int):Int {
    var first = 0
    var second = 1
    var temp = 0
    if (n == 0)
        return first
    else if (n == 1)
        return second
    var i = 2
    while (i <= n)
    {
        temp = first + second
        first = second
        second = temp
        i += 1
    }
    return temp
}

fun print(Q:IntArray, n:Int) {
    for (i in 0 until n)
    {
        print(" " + Q[i])
    }
    println(" ")
}

fun Feasible(Q:IntArray, k:Int):Boolean {
    for (i in 0 until k)
    {
        if (Q[k] == Q[i] || Math.abs(Q[i] - Q[k]) == Math.abs(i - k))
        {
            return false
        }
    }
    return true
}

fun NQueens(Q:IntArray, k:Int, n:Int) {
    if (k == n)
    {
        print(Q, n)
        return
    }
    for (i in 0 until n)
    {
        Q[k] = i
        if (Feasible(Q, k))
        {
            NQueens(Q, k + 1, n)
        }
    }
}

fun main1() {
        val Q = IntArray(8)
        NQueens(Q, 0, 8)
}

fun TOHUtil(num:Int, from:Char, to:Char, temp:Char) {
    if (num < 1)
    {
        return
    }
    TOHUtil(num - 1, from, temp, to)
    println("Move disk " + num + " from peg " + from + " to peg " + to)
    TOHUtil(num - 1, temp, to, from)
}

fun TowersOfHanoi(num:Int) {
    println("The sequence of moves involved in the Tower of Hanoi are :")
    TOHUtil(num, 'A', 'C', 'B')
}
fun main2() {
    TowersOfHanoi(3)
}

fun main(args : Array<String>){
    main1()
    main2()
}