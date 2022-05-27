import java.lang.*
import java.lang.Math

fun fun1(n: Int): Int {
    var m = 0
    for (i in 0 until n) {
        m += 1
    }
    return m
}

fun fun2(n: Int): Int {
    var i: Int
    var j: Int
    var m = 0
    i = 0
    while (i < n) {
        j = 0
        while (j < n) {
            m += 1
            j++
        }
        i++
    }
    return m
}

fun fun3(n: Int): Int {
    var i: Int
    var j: Int
    var m = 0
    i = 0
    while (i < n) {
        j = 0
        while (j < i) {
            m += 1
            j++
        }
        i++
    }
    return m
}

fun fun4(n: Int): Int {
    var i: Int
    var m = 0
    i = 1
    while (i < n) {
        m += 1
        i = i * 2
    }
    return m
}

fun fun5(n: Int): Int {
    var i: Int
    var m = 0
    i = n
    while (i > 0) {
        m += 1
        i = i / 2
    }
    return m
}

fun fun6(n: Int): Int {
    var i: Int
    var j: Int
    var k: Int
    var m = 0
    i = 0
    while (i < n) {
        j = 0
        while (j < n) {
            k = 0
            while (k < n) {
                m += 1
                k++
            }
            j++
        }
        i++
    }
    return m
}

fun fun7(n: Int): Int {
    var i: Int
    var j: Int
    var k: Int
    var m = 0
    i = 0
    while (i < n) {
        j = 0
        while (j < n) {
            m += 1
            j++
        }
        i++
    }
    i = 0
    while (i < n) {
        k = 0
        while (k < n) {
            m += 1
            k++
        }
        i++
    }
    return m
}

fun fun8(n: Int): Int {
    var i: Int
    var j: Int
    var m = 0
    i = 0
    while (i < n) {
        j = 0
        while (j < Math.sqrt(n.toDouble())) {
            m += 1
            j++
        }
        i++
    }
    return m
}

fun fun9(n: Int): Int {
    var i: Int
    var j: Int
    var m = 0
    i = n
    while (i > 0) {
        j = 0
        while (j < i) {
            m += 1
            j++
        }
        i /= 2
    }
    return m
}

fun fun10(n: Int): Int {
    var i: Int
    var j: Int
    var m = 0
    i = 0
    while (i < n) {
        j = i
        while (j > 0) {
            m += 1
            j--
        }
        i++
    }
    return m
}

fun fun11(n: Int): Int {
    var i: Int
    var j: Int
    var k: Int
    var m = 0
    i = 0
    while (i < n) {
        j = i
        while (j < n) {
            k = j + 1
            while (k < n) {
                m += 1
                k++
            }
            j++
        }
        i++
    }
    return m
}

fun fun12(n: Int): Int {
    var i: Int
    var j = 0
    var m = 0
    i = 0
    while (i < n) {
        while (j < n) {
            m += 1
            j++
        }
        i++
    }
    return m
}

fun fun13(n: Int): Int {
    var i: Int
    var j = 0
    var m = 0
    i = 1
    while (i <= n) {
        j = 0
        while (j <= i) {
            m += 1
            j++
        }
        i *= 2
    }
    return m
}

fun main(args: Array<String>) {
    println("N = 100, Number of instructions O(n):: " + fun1(100))
    println("N = 100, Number of instructions O(n^2):: " + fun2(100))
    println("N = 100, Number of instructions O(n^2):: " + fun3(100))
    println("N = 100, Number of instructions O(log(n)):: " + fun4(100))
    println("N = 100, Number of instructions O(log(n)):: " + fun5(100))
    println("N = 100, Number of instructions O(n^3):: " + fun6(100))
    println("N = 100, Number of instructions O(n^2):: " + fun7(100))
    println("N = 100, Number of instructions O(n^(3/2)):: " + fun8(100))
    println("N = 100, Number of instructions O(log(n)):: " + fun9(100))
    println("N = 100, Number of instructions O(n^2):: " + fun10(100))
    println("N = 100, Number of instructions O(n^3):: " + fun11(100))
    println("N = 100, Number of instructions O(n):: " + fun12(100))
    println("N = 100, Number of instructions O(n):: " + fun13(100))
}
/*
N = 100, Number of instructions O(n):: 100
N = 100, Number of instructions O(n^2):: 10000
N = 100, Number of instructions O(n^2):: 4950
N = 100, Number of instructions O(log(n)):: 7
N = 100, Number of instructions O(log(n)):: 7
N = 100, Number of instructions O(n^3):: 1000000
N = 100, Number of instructions O(n^2):: 20000
N = 100, Number of instructions O(n^(3/2)):: 1000
N = 100, Number of instructions O(log(n)):: 197
N = 100, Number of instructions O(n^2):: 4950
N = 100, Number of instructions O(n^3):: 166650
N = 100, Number of instructions O(n):: 100
N = 100, Number of instructions O(n):: 134
 */