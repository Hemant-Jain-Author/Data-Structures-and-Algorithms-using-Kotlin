fun andEx(a: Int, b: Int): Int {
    return a and b
}

fun orEx(a: Int, b: Int): Int {
    return a or b
}

fun xorEx(a: Int, b: Int): Int {
    return a xor b
}

fun leftShiftEx(a: Int): Int { // multiply by 2
    return a shl 1
}

fun rightShiftEx(a: Int): Int { // divide by 2
    return a shr 1
}

fun bitReversalEx(a: Int): Int {
    return a.inv()
}

fun twoComplementEx(a: Int): Int {
    return -a
}

fun kthBitCheck(a: Int, k: Int): Boolean {
    return (a and (1 shl (k - 1))) > 0
}

fun kthBitSet(a: Int, k: Int): Int {
    return a or (1 shl (k- 1)) 
}

fun kthBitReset(a: Int, k: Int): Int {
    return a and (1 shl (k - 1)).inv()
}

fun kthBitToggle(a: Int, k: Int): Int {
    return a xor (1 shl (k - 1))
}

fun rightMostBit(a: Int): Int {
    return a and -a
}

fun resetRightMostBit(a: Int): Int {
    return a and a - 1
}

fun isPowerOf2(a: Int): Boolean {
    return if (a and a - 1 == 0) true else false
}

fun countBits(a1: Int): Int {
    var a = a1
    var count = 0
    while (a > 0) {
        count += 1
        a = a and a - 1 // reset least significant bit.
    }
    return count
}

fun main() {
    val a = 4
    val b = 8
    println(andEx(a, b))
    println(orEx(a, b))
    println(xorEx(a, b))
    println(leftShiftEx(a)) // multiply by 2
    println(rightShiftEx(a)) // divide by 2
    println(bitReversalEx(a))
    println(twoComplementEx(a))
    println(kthBitCheck(a, 3))
    println(kthBitSet(a, 2))
    println(kthBitReset(a, 3))
    println(kthBitToggle(a, 3))
    println(rightMostBit(a))
    println(resetRightMostBit(a))
    println(isPowerOf2(a))
    for (i in 0..9) println(i.toString() + " bit count : " + countBits(i))
}

/*
0
12
12
8
2
-5
-4
true
6
0
0
4
0
true
*/

/*
0 bit count : 0
1 bit count : 1
2 bit count : 1
3 bit count : 2
4 bit count : 1
5 bit count : 2
6 bit count : 2
7 bit count : 3
8 bit count : 1
9 bit count : 2
*/