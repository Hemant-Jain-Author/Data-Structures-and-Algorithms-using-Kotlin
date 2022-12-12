import java.lang.*
import java.lang.Math

fun fun1(n: Int): Int {
    var m = 0
    for (i in 0 until n) {
        m += 1
    }
    return m
}

// Testing Code.
fun main1() {
    println("N = 100, Number of instructions O(n):: " + fun1(100))
}

fun fun2(n: Int): Int {
    var m = 0
    for (i in 0 until n) {
        for (j in 0 until n) {
            m += 1
        }
    }
    return m
}

// Testing Code.
fun main2() {
	println("N = 100, Number of instructions O(n^2):: " + fun2(100))
}

fun fun3(n: Int): Int {
    var m = 0
    for (i in 0 until n) {
        for (j in 0 until n) {
            for (k in 0 until n) {
                m += 1
            }
        }
    }
    return m
}

// Testing Code.
fun main3() {
    println("N = 100, Number of instructions O(n^3):: " + fun3(100))
}

fun fun4(n: Int): Int {
    var m = 0
    for (i in 0 until n) {
        for (j in i until n) {
            for (k in j+1 until n) {
                m += 1
            }
        }
    }
    return m
}

// Testing Code.
fun main4() {
	println("N = 100, Number of instructions O(n^3):: " + fun4(100))
}

fun fun5(n: Int): Int {
    var m = 0
    for (i in 0 until n) {
        for (j in 0 until i) {
            m += 1
        }
    }
    return m
}

// Testing Code.
fun main5() {
	println("N = 100, Number of instructions O(n^2):: " + fun5(100))
}

fun fun6(n: Int): Int {
    var m = 0
    for (i in 0 until n) {
        for (j in i downTo 0) {
            m += 1
        }
    }
    return m
}

// Testing Code.
fun main6() {
	println("N = 100, Number of instructions O(n^2):: " + fun6(100))
}

fun fun7(n: Int): Int {
    var m = 0
    var i = n
    while (i > 0) {
        var j = 0
        while (j < i) {
            m += 1
            j++
        }
        i /= 2
    }
    return m
}

// Testing Code.
fun main7() {
	println("N = 100, Number of instructions O(n):: " + fun7(100))
}

fun fun8(n: Int): Int {
    var m = 0
    var i = 1
    while (i <= n) {
        var j = 0
        while (j <= i) {
            m += 1
            j++
        }
        i *= 2
    }
    return m
}

// Testing Code.
fun main8() {
	println("N = 100, Number of instructions O(n):: " + fun8(100))
}

fun fun9(n: Int): Int {
    var m = 0
    var i = 1
    while (i < n) {
        m += 1
        i = i * 2
    }
    return m
}

// Testing Code.
fun main9() {
    println("N = 100, Number of instructions O(log(n)):: " + fun9(100))
}

fun fun10(n: Int): Int {
    var m = 0
    var i = n
    while (i > 0) {
        m += 1
        i = i / 2
    }
    return m
}

// Testing Code.
fun main10() {
    println("N = 100, Number of instructions O(log(n)):: " + fun10(100))
}

fun fun11(n: Int): Int {
    var m = 0
    for (i in 0 until n) {
        for (j in 0 until n) {
            m += 1
        }
    }

    for (i in 0 until n) {
        for (k in 0 until n) {
            m += 1
        }
    }
    return m
}

// Testing Code.
fun main11() {
    println("N = 100, Number of instructions O(n^2):: " + fun11(100))
}

fun fun12(n: Int): Int {
    var m = 0
    for (i in 0 until n) {
        for (j in 0 until Math.sqrt(n.toDouble()).toInt()) {
            m += 1
        }
    }
    return m
}

// Testing Code.
fun main12() {
    println("N = 100, Number of instructions O(n^(3/2)):: " + fun12(100))
}

fun fun13(n: Int): Int {
    var m = 0
    var i = 0
    var j = 0
    while (i < n) {
        while (j < n) {
            m += 1
            j++
        }
        i++
    }
    return m
}

// Testing Code.
fun main13() {
    println("N = 100, Number of instructions O(n):: " + fun13(100))
}

fun main(){
    main1();
    main2();
    main3();
    main4();
    main5();
    main6();
    main7();
    main8();
    main9();
    main10();
    main11();
    main12();
    main13();   
}

/*
N = 100, Number of instructions O(n):: 100
N = 100, Number of instructions O(n^2):: 10000
N = 100, Number of instructions O(n^3):: 1000000
N = 100, Number of instructions O(n^3):: 166650
N = 100, Number of instructions O(n^2):: 4950
N = 100, Number of instructions O(n^2):: 5050
N = 100, Number of instructions O(n):: 197
N = 100, Number of instructions O(n):: 134
N = 100, Number of instructions O(log(n)):: 7
N = 100, Number of instructions O(log(n)):: 7
N = 100, Number of instructions O(n^2):: 20000
N = 100, Number of instructions O(n^(3/2)):: 1000
N = 100, Number of instructions O(n):: 100
 */