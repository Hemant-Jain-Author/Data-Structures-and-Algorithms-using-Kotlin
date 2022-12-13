class Polynomial internal constructor(coeffs: IntArray? = null, pows: IntArray? = null, size: Int = 0) {

	class Node internal constructor(var coeff: Int, var pow: Int) {
        var next: Node? = null
    }

    var head: Node? = null
    var tail: Node? = null

    init {
        for (i in 0 until size) {
            var temp = Node(coeffs!![i], pows!![i])
            if (head == null) {
                tail = temp
                head = tail
            } else {
                tail!!.next = temp
                tail = tail!!.next
            }
        }
    }

    fun add(poly2: Polynomial): Polynomial {
        var p1 = head
        var p2 = poly2.head
        var temp: Node? = null
        val poly = Polynomial()
        while (p1 != null || p2 != null) {
            if (p1 == null || (p2 != null && p1.pow < p2.pow)) {
                temp = Node(p2!!.coeff, p2.pow)
                p2 = p2.next
            } else if (p2 == null || p1.pow > p2.pow) {
                temp = Node(p1.coeff, p1.pow)
                p1 = p1.next
            } else if (p1.pow == p2.pow) {
                temp = Node(p1.coeff + p2.coeff, p1.pow)
                p1 = p1.next
                p2 = p2.next
            }
            if (poly.head == null) {
                poly.tail = temp
                poly.head = poly.tail
            } else {
                poly.tail!!.next = temp
                poly.tail = poly.tail!!.next
            }
        }
        return poly
    }

    fun print() {
        var curr = head
        while (curr != null) {
            print(curr.coeff.toString() + "x^" + curr.pow)
            if (curr.next != null) print(" + ")
            curr = curr.next
        }
        println()
    }
}


// Testing Code.
fun main() {
	val c1 = intArrayOf(6, 5, 4)
	val p1 = intArrayOf(2, 1, 0)
	val s1 = c1.size
	val first = Polynomial(c1, p1, s1)
	first.print()
	val c2 = intArrayOf(3, 2, 1)
	val p2 = intArrayOf(3, 1, 0)
	val s2 = c2.size
	val second = Polynomial(c2, p2, s2)
	second.print()
	val sum = first.add(second)
	sum.print()
}

/*
6x^2 + 5x^1 + 4x^0
3x^3 + 2x^1 + 1x^0
3x^3 + 6x^2 + 7x^1 + 5x^0
*/