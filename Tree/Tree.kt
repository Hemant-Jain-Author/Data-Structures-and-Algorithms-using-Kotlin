import java.util.ArrayDeque
import java.util.Stack

class Tree (var root: Node? = null) {
    
    class Node (var value: Int, var left: Node? = null, var right: Node? = null)        

    /* Other methods */
    fun createCompleteBinaryTree(arr: IntArray) {
        root = createCompleteBinaryTree(arr, 0)
    }

    fun createCompleteBinaryTree(arr: IntArray, start: Int): Node {
        val size = arr.size
        val curr = Node(arr[start])
        val leftIndex = 2 * start + 1
        val rightIndex = 2 * start + 2
        if (leftIndex < size) curr.left = createCompleteBinaryTree(arr, leftIndex)
        if (rightIndex < size) curr.right = createCompleteBinaryTree(arr, rightIndex)
        return curr
    }

    fun insert(value: Int) {
        root = insert(root, value)
    }

    private fun insert(node: Node?, value: Int): Node {
        if (node == null) {
            return Node(value, null, null)
        } else if (node.value > value) {
            node.left = insert(node.left, value)
        } else {
            node.right = insert(node.right, value)
        }
        return node
    }

    fun printPreOrder() {
        printPreOrder(root)
        println()
    }

    private fun printPreOrder(node: Node?) { /* pre order */
        if (node != null) {
            print(node.value.toString() + " ")
            printPreOrder(node.left)
            printPreOrder(node.right)
        }
    }

    fun nthPreOrder(index: Int) {
        val counter = intArrayOf(0)
        nthPreOrder(root, index, counter)
    }

    private fun nthPreOrder(node: Node?, index: Int, counter: IntArray) { /* pre order */
        if (node != null) {
            counter[0]++
            if (counter[0] == index) {
                println(node.value)
            }
            nthPreOrder(node.left, index, counter)
            nthPreOrder(node.right, index, counter)
        }
    }

    fun printPostOrder() {
        printPostOrder(root)
        println()
    }

    private fun printPostOrder(node: Node?) { /* post order */
        if (node != null) {
            printPostOrder(node.left)
            printPostOrder(node.right)
            print(node.value.toString() + " ")
        }
    }

    fun nthPostOrder(index: Int) {
        val counter = intArrayOf(0)
        nthPostOrder(root, index, counter)
    }

    private fun nthPostOrder(node: Node?, index: Int, counter: IntArray) { /* post order */
        if (node != null) {
            nthPostOrder(node.left, index, counter)
            nthPostOrder(node.right, index, counter)
            counter[0]++
            if (counter[0] == index) {
                println(node.value)
            }
        }
    }

    fun printInOrder() {
        printInOrder(root)
        println()
    }

    private fun printInOrder(node: Node?) { /* In order */
        if (node != null) {
            printInOrder(node.left)
            print(node.value.toString() + " ")
            printInOrder(node.right)
        }
    }

    fun nthInOrder(index: Int) {
        val counter = intArrayOf(0)
        nthInOrder(root, index, counter)
    }

    private fun nthInOrder(curr: Node?, index: Int, counter: IntArray ) {
        if (curr != null) {
            nthInOrder(curr.left, index, counter)
            counter[0]++
            if (counter[0] == index) {
                println(curr.value)
            }
            nthInOrder(curr.right, index, counter)
        }
    }

    fun printBreadthFirst() {
        val que: ArrayDeque<Node> = ArrayDeque<Node>()
        var temp: Node
        if (root != null) que.add(root)
        while (que.isEmpty() == false) {
            temp = que.remove()
            print(temp.value.toString() + " ")
            if (temp.left != null) que.add(temp.left)
            if (temp.right != null) que.add(temp.right)
        }
    }

    fun printDepthFirst() {
        val stk: Stack<Node> = Stack<Node>()
        var temp: Node
        if (root != null) stk.push(root)
        while (stk.isEmpty() == false) {
            temp = stk.pop()
            print(temp.value.toString() + " ")
            if (temp.left != null) stk.push(temp.left)
            if (temp.right != null) stk.push(temp.right)
        }
    }

    fun printLevelOrderLineByLine() {
        val que1: ArrayDeque<Node> = ArrayDeque<Node>()
        val que2: ArrayDeque<Node> = ArrayDeque<Node>()
        var temp: Node?
        if (root != null) que1.add(root)
        while (que1.size != 0 || que2.size != 0) {
            while (que1.size != 0) {
                temp = que1.remove()
                print(temp.value.toString() + " ")
                if (temp.left != null) que2.add(temp.left)
                if (temp.right != null) que2.add(temp.right)
            }
            println("")
            while (que2.size != 0) {
                temp = que2.remove()
                print(temp.value.toString() + " ")
                if (temp.left != null) que1.add(temp.left)
                if (temp.right != null) que1.add(temp.right)
            }
            println()
        }
    }

    fun printLevelOrderLineByLine2() {
        val que: ArrayDeque<Node> = ArrayDeque<Node>()
        var temp: Node?
        var count : Int
        if (root != null) que.add(root)
        while (que.size != 0) {
            count = que.size
            while (count > 0) {
                temp = que.remove()
                print(temp.value.toString() + " ")
                if (temp.left != null) que.add(temp.left)
                if (temp.right != null) que.add(temp.right)
                count -= 1
            }
            println()
        }
    }

    fun printSpiralTree() {
        val stk1: Stack<Node> = Stack<Node>()
        val stk2: Stack<Node> = Stack<Node>()
        var temp: Node
        if (root != null) stk1.push(root)
        while (stk1.size != 0 || stk2.size != 0) {
            while (stk1.size != 0) {
                temp = stk1.pop()
                print(temp.value.toString() + " ")
                if (temp.right != null) stk2.push(temp.right)
                if (temp.left != null) stk2.push(temp.left)
            }
            while (stk2.size != 0) {
                temp = stk2.pop()
                print(temp.value.toString() + " ")
                if (temp.left != null) stk1.push(temp.left)
                if (temp.right != null) stk1.push(temp.right)
            }
        }
        println()
    }

    fun find(value: Int): Boolean {
        var curr = root
        while (curr != null) {
            curr = if (curr.value == value) {
                return true
            } else if (curr.value > value) {
                curr.left
            } else {
                curr.right
            }
        }
        return false
    }

    fun find2(value: Int): Boolean {
        var curr = root
        while (curr != null && curr.value != value) 
            curr = if (curr.value > value) curr.left else curr.right
        return curr != null
    }

    fun findMin(): Int {
        var node: Node? = root ?: return Int.MAX_VALUE
        while (node!!.left != null) {
            node = node.left
        }
        return node.value
    }

    fun findMax(): Int {
        var node: Node? = root ?: return Int.MIN_VALUE
        while (node!!.right != null) {
            node = node.right
        }
        return node.value
    }

    fun findMaxNode(curr: Node?): Node? {
        var node: Node? = curr ?: return null
        while (node!!.right != null) {
            node = node.right
        }
        return node
    }

    fun findMinNode(curr: Node?): Node? {
        var node: Node? = curr ?: return null
        while (node!!.left != null) {
            node = node.left
        }
        return node
    }

    fun free() {
        root = null
    }

    fun deleteNode(value: Int) {
        root = deleteNode(root, value)
    }

    private fun deleteNode(node: Node?, value: Int): Node? {
        if (node != null) {
            if (node.value == value) {
                if (node.left == null && node.right == null) {
                    return null
                } else if (node.left == null) {
                    return node.right
                } else if (node.right == null) {
                    return node.left
                }
                val minNode = findMinNode(node.right)
                val minValue = minNode!!.value
                node.value = minValue
                node.right = deleteNode(node.right, minValue)
            } else {
                if (node.value > value) {
                    node.left = deleteNode(node.left, value)
                } else {
                    node.right = deleteNode(node.right, value)
                }
            }
        }
        return node
    }

    fun treeDepth(curr: Node? = root): Int {
        return if (curr == null) 0 else {
            val lDepth = treeDepth(curr.left)
            val rDepth = treeDepth(curr.right)
            if (lDepth > rDepth) lDepth + 1 else rDepth + 1
        }
    }

    fun isEqual(T2: Tree): Boolean {
        return isEqualUtil(root, T2.root)
    }

    private fun isEqualUtil(node1: Node?, node2: Node?): Boolean {
        return if (node1 == null && node2 == null) true 
            else if (node1 == null || node2 == null) false 
            else isEqualUtil(node1.left, node2.left) && isEqualUtil(node1.right, node2.right) && node1.value == node2.value
    }

    fun ancestor(first: Int, second: Int): Node? {
        return if (first > second) ancestor(root, second, first) 
            else ancestor(root, first, second)
    }

    private fun ancestor(curr: Node?, first: Int, second: Int): Node? {
        return if (curr == null) null
            else if (curr.value > first && curr.value > second) ancestor(curr.left, first, second)
            else if (curr.value < first && curr.value < second) ancestor(curr.right, first, second)
            else curr
    }

    fun copyTree(): Tree {
        val tree2 = Tree()
        tree2.root = copyTree(root)
        return tree2
    }

    private fun copyTree(curr: Node?): Node? {
        return if (curr == null) null else {
            val temp: Node = Node(curr.value)
            temp.left = copyTree(curr.left)
            temp.right = copyTree(curr.right)
            temp
        }
    }

    fun copyMirrorTree(): Tree {
        val tree2 = Tree()
        tree2.root = copyMirrorTree(root)
        return tree2
    }

    private fun copyMirrorTree(curr: Node?): Node? {
        return if (curr == null) null else {
            val temp: Node = Node(curr.value)
            temp.right = copyMirrorTree(curr.left)
            temp.left = copyMirrorTree(curr.right)
            temp
        }
    }

    fun numNodes(curr: Node? = root): Int {
        return if (curr == null) 0 else 1 + numNodes(curr.right) + numNodes(curr.left)
    }

    fun numFullNodesBT(curr: Node? = root): Int {
        if (curr == null) return 0
        var count = numFullNodesBT(curr.right) + numFullNodesBT(curr.left)
        if (curr.right != null && curr.left != null) count++
        return count
    }

    fun maxLengthPathBT(curr: Node? = root): Int { // diameter
        var max: Int
        val leftPath: Int
        val rightPath: Int
        val leftMax: Int
        val rightMax: Int
        if (curr == null) return 0
        leftPath = treeDepth(curr.left)
        rightPath = treeDepth(curr.right)
        max = leftPath + rightPath + 1
        leftMax = maxLengthPathBT(curr.left)
        rightMax = maxLengthPathBT(curr.right)
        if (leftMax > max) max = leftMax
        if (rightMax > max) max = rightMax
        return max
    }

    fun numLeafNodes(curr: Node? = root): Int {
        return if (curr == null) 0
            else if (curr.left == null && curr.right == null) 1 
            else numLeafNodes(curr.right) + numLeafNodes(curr.left)
    }

    fun sumAllBT(curr: Node? = root): Int {
        return if (curr == null) 0 else curr.value + sumAllBT(curr.left) + sumAllBT(curr.right)
    }

    fun iterativePreOrder() {
        val stk: Stack<Node> = Stack<Node>()
        var curr: Node
        if (root != null) stk.add(root)
        while (stk.isEmpty() == false) {
            curr = stk.pop()
            print(curr.value.toString() + " ")
            if (curr.right != null) stk.push(curr.right)
            if (curr.left != null) stk.push(curr.left)
        }
        println()
    }

    fun iterativePostOrder() {
        val stk: Stack<Node> = Stack<Node>()
        val visited: Stack<Int> = Stack<Int>()
        var curr: Node
        var vtd: Int
        if (root != null) {
            stk.add(root)
            visited.add(0)
        }
        while (stk.isEmpty() == false) {
            curr = stk.pop()
            vtd = visited.pop()
            if (vtd == 1) {
                print(curr.value.toString() + " ")
            } else {
                stk.push(curr)
                visited.push(1)
                if (curr.right != null) {
                    stk.push(curr.right)
                    visited.push(0)
                }
                if (curr.left != null) {
                    stk.push(curr.left)
                    visited.push(0)
                }
            }
        }
        println()
    }

    fun iterativeInOrder() {
        val stk: Stack<Node> = Stack<Node>()
        val visited: Stack<Int> = Stack<Int>()
        var curr: Node
        var vtd: Int
        if (root != null) {
            stk.add(root)
            visited.add(0)
        }
        while (stk.isEmpty() == false) {
            curr = stk.pop()
            vtd = visited.pop()
            if (vtd == 1) {
                print(curr.value.toString() + " ")
            } else {
                if (curr.right != null) {
                    stk.push(curr.right)
                    visited.push(0)
                }
                stk.push(curr)
                visited.push(1)
                if (curr.left != null) {
                    stk.push(curr.left)
                    visited.push(0)
                }
            }
        }
        println()
    }

    fun isBST3(curr: Node? = root): Boolean {
        return if (curr == null) true
            else if (curr.left != null && findMaxNode(curr.left)!!.value > curr.value) false
            else if (curr.right != null && findMinNode(curr.right)!!.value <= curr.value) false 
            else isBST3(curr.left) && isBST3(curr.right)
    }

    fun isBST(curr: Node? = root, min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE): Boolean {
        return if (curr == null) true
            else if (curr.value < min || curr.value > max) false 
            else isBST(curr.left, min, curr.value) && isBST(curr.right, curr.value, max)
    }

    fun isBST2(curr: Node? = root, count: IntArray = IntArray(1)): Boolean { /* in order traversal */
        if (curr == null) return true
        if (!isBST2(curr.left, count)) return false
        if (count[0] > curr.value) return false
        count[0] = curr.value
        if (!isBST2(curr.right, count)) return false
        return true
    }

    fun isCompleteTree(): Boolean {
        val que: ArrayDeque<Node> = ArrayDeque<Node>()
        var temp: Node?
        var noChild = 0
        if (root != null) que.add(root)
        while (que.size != 0) {
            temp = que.remove()
            if (temp.left != null) {
                if (noChild == 1) return false
                que.add(temp.left)
            } else noChild = 1
            if (temp.right != null) {
                if (noChild == 1) return false
                que.add(temp.right)
            } else noChild = 1
        }
        return true
    }

    fun isCompleteTreeUtil(curr: Node?, index: Int, count: Int): Boolean {
        return if (curr == null) return true 
            else if (index > count) false 
            else isCompleteTreeUtil(curr.left, index * 2 + 1, count)
                && isCompleteTreeUtil(curr.right, index * 2 + 2, count)
    }

    fun isCompleteTree2(): Boolean {
        val count = numNodes()
        return isCompleteTreeUtil(root, 0, count)
    }

    fun isHeapUtil(curr: Node?, parentValue: Int): Boolean {
        return if (curr == null) true
            else if (curr.value < parentValue) false 
            else isHeapUtil(curr.left, curr.value) && isHeapUtil(curr.right,curr.value)
    }

    fun isHeap(): Boolean {
        val infinite = -9999999
        return isCompleteTree() && isHeapUtil(root, infinite)
    }

    fun isHeapUtil2(curr: Node?, index: Int, count: Int, parentValue: Int): Boolean {
        return if (curr == null) true
            else if (index > count) return false
            else if (curr.value < parentValue) false 
            else isHeapUtil2(curr.left, index * 2 + 1, count, curr.value)
                && isHeapUtil2(curr.right, index * 2 + 2, count, curr.value)
    }

    fun isHeap2(): Boolean {
        val count = numNodes()
        val parentValue = -9999999
        return isHeapUtil2(root, 0, count, parentValue)
    }

    fun treeToListRec(curr: Node? = root): Node? {
        var Head: Node?
        var Tail: Node?
        if (curr == null) return null
        if (curr.left == null && curr.right == null) {
            curr.left = curr
            curr.right = curr
            return curr
        }

        if (curr.left != null) {
            Head = treeToListRec(curr.left)
            Tail = Head!!.left
            curr.left = Tail
            Tail!!.right = curr
        } else Head = curr

        if (curr.right != null) {
            val tempHead = treeToListRec(curr.right)
            Tail = tempHead!!.left
            curr.right = tempHead
            tempHead.left = curr
        } else Tail = curr
        Head.left = Tail
        Tail!!.right = Head
        return Head
    }

    fun printAllPath() {
        val stk: Stack<Int> = Stack<Int>()
        printAllPathUtil(root, stk)
    }

    private fun printAllPathUtil(curr: Node?, stk: Stack<Int>) {
        if (curr == null) return
        stk.push(curr.value)
        if (curr.left == null && curr.right == null) {
            println(stk)
            stk.pop()
            return
        }
        printAllPathUtil(curr.right, stk)
        printAllPathUtil(curr.left, stk)
        stk.pop()
    }

    fun lca(first: Int, second: Int): Int {
        val ans = lca(root, first, second)
        return ans?.value ?: Int.MIN_VALUE
    }

    private fun lca(curr: Node?, first: Int, second: Int): Node? {
        val left: Node?
        val right: Node?
        if (curr == null) return null
        if (curr.value == first || curr.value == second) return curr
        left = lca(curr.left, first, second)
        right = lca(curr.right, first, second)
        return if (left != null && right != null) curr else left ?: right
    }

    fun lcaBST(first: Int, second: Int): Int {
        val result: Int
        result = if (first > second) lcaBST(root, second, first) else lcaBST(root, first, second)
        if (result == Int.MAX_VALUE) println("lca does not exist") else println("lca is :$result")
        return result
    }

    private fun lcaBST(curr: Node?, first: Int, second: Int): Int {
        if (curr == null) {
            return Int.MAX_VALUE
        }
        if (curr.value > second) {
            return lcaBST(curr.left, first, second)
        }
        if (curr.value < first) {
            return lcaBST(curr.right, first, second)
        }
        return if (find(first) && find(second)) curr.value else Int.MAX_VALUE
    }

    fun trimOutsideRange(min: Int, max: Int) {
        trimOutsideRange(root, min, max)
    }

    private fun trimOutsideRange(curr: Node?, min: Int, max: Int): Node? {
        if (curr == null) return null
        curr.left = trimOutsideRange(curr.left, min, max)
        curr.right = trimOutsideRange(curr.right, min, max)
        return if (curr.value < min) curr.right
            else if (curr.value > max) curr.left
            else curr
    }

    fun printInRange(min: Int, max: Int) {
        printInRange(root, min, max)
        println()
    }

    private fun printInRange(root: Node?, min: Int, max: Int) {
        if (root == null) return
        printInRange(root.left, min, max)
        if (root.value >= min && root.value <= max) print(root.value.toString() + " ")
        printInRange(root.right, min, max)
    }

    fun floorBST(`val`: Double): Int {
        var curr = root
        var floor = Int.MAX_VALUE
        while (curr != null) {
            if (curr.value.toDouble() == `val`) {
                floor = curr.value
                break
            } else if (curr.value > `val`) {
                curr = curr.left
            } else {
                floor = curr.value
                curr = curr.right
            }
        }
        return floor
    }

    fun ceilBST(`val`: Double): Int {
        var curr = root
        var ceil = Int.MIN_VALUE
        while (curr != null) {
            if (curr.value.toDouble() == `val`) {
                ceil = curr.value
                break
            } else if (curr.value > `val`) {
                ceil = curr.value
                curr = curr.left
            } else {
                curr = curr.right
            }
        }
        return ceil
    }

    fun findMaxBT(curr: Node? = root): Int {
        if (curr == null) return Int.MIN_VALUE
        var max = curr.value
        val leftmax = findMaxBT(curr.left)
        val rightmax = findMaxBT(curr.right)
        if (leftmax > max) max = leftmax
        if (rightmax > max) max = rightmax
        return max
    }

    fun searchBT(value: Int): Boolean {
        return searchBTUtil(root, value)
    }

    fun searchBTUtil(curr: Node?, value: Int): Boolean {
        return if (curr == null) false
            else if (curr.value == value || searchBTUtil(curr.left, value) || searchBTUtil(curr.right,value)) true 
            else false
    }

    fun createBinarySearchTree(arr: IntArray) {
        root = createBinarySearchTree(arr, 0, arr.size - 1)
    }

    private fun createBinarySearchTree(arr: IntArray, start: Int, end: Int): Node? {
        if (start > end) return null
        val mid = (start + end) / 2
        val curr = Node(arr[mid])
        curr.left = createBinarySearchTree(arr, start, mid - 1)
        curr.right = createBinarySearchTree(arr, mid + 1, end)
        return curr
    }
}

fun main1() {
    val t = Tree()
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    t.createCompleteBinaryTree(arr)
    t.printPreOrder()
    // 1 2 4 8 9 5 10 3 6 7 
    t.printPostOrder()
    // 8 9 4 10 5 2 6 7 3 1 
    t.printInOrder()
    // 8 4 9 2 10 5 1 6 3 7 
    t.iterativePreOrder()
    // 1 2 4 8 9 5 10 3 6 7 
    t.iterativePostOrder()
    // 8 9 4 10 5 2 6 7 3 1 
    t.iterativeInOrder()
    // 8 4 9 2 10 5 1 6 3 7 
    t.printBreadthFirst()
    // 1 2 3 4 5 6 7 8 9 10 
    t.printDepthFirst()
    // 1 3 7 6 2 5 10 4 9 8
    t.printLevelOrderLineByLine()
/*
1 
2 3 
4 5 6 7 
8 9 10 
*/
    t.printLevelOrderLineByLine2()
/*
1 
2 3 
4 5 6 7 
8 9 10 

1 
2 3 
4 5 6 7 
8 9 10 
*/
    t.printSpiralTree()
    // 1 2 3 7 6 5 4 8 9 10 
    t.nthInOrder(2)
    t.nthPostOrder(2)
    t.nthPreOrder(2)

/*
4
9
2
*/
    t.printAllPath()

/*
[1, 3, 7]
[1, 3, 6]
[1, 2, 5, 10]
[1, 2, 4, 9]
[1, 2, 4, 8]
*/
}

fun main2() {
    val t = Tree()
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    t.createCompleteBinaryTree(arr)
    println(t.numNodes())
    // 10
    println(t.sumAllBT())
    // 55
    println(t.numLeafNodes())
    // 5
    println(t.numFullNodesBT())
    // 4
    println(t.searchBT(9))
    // true
    println(t.findMaxBT())
    // 10
    println(t.treeDepth())
    // 4
    println(t.maxLengthPathBT())
    // 6
}

fun main3() {
    val t = Tree()
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    t.createCompleteBinaryTree(arr)
    val t2 = t.copyTree()
    t2.printInOrder()
/*
8 4 9 2 10 5 1 6 3 7 
*/
    val t3 = t.copyMirrorTree()
    t3.printInOrder()
/*
7 3 6 1 5 10 2 9 4 8
*/
    println(t.isEqual(t2))
/*
true
*/
    println(t.isHeap())
    println(t.isHeap2())
    println(t.isCompleteTree())
    println(t.isCompleteTree2())
/*
true
true
true
true
*/
}

fun main4() {
    val t = Tree()
    t.insert(6)
    t.insert(4)
    t.insert(2)
    t.insert(5)
    t.insert(1)
    t.insert(3)
    t.insert(8)
    t.insert(7)
    t.insert(9)
    t.insert(10)
    t.printInOrder()

/*
1 2 3 4 5 6 7 8 9 10 
*/

    println("IsBST : " + t.isBST())
    println("IsBST : " + t.isBST2())
    println("IsBST : " + t.isBST3())
/*
true
true
true
*/
}

fun main5() {
    val t = Tree()
    t.insert(2)
    t.insert(1)
    t.insert(3)
    t.insert(4)
    println("Before delete operation.")
    t.printInOrder()
    t.deleteNode(2)
    println("After delete operation.")
    t.printInOrder()
}

/*
Before delete operation.
1 2 3 4 
After delete operation.
1 3 4 
*/

fun main6() {
    val t = Tree()
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    t.createBinarySearchTree(arr)
	t.printInOrder()
    println(t.find(3))
    println(t.find(16))
/*
true
false
*/
    println(t.findMin())
    println(t.findMax())
    t.lcaBST(3, 4)
    t.lcaBST(1, 4)
    t.lcaBST(10, 4)
}

/*
1
10
lca is :3
lca is :2
lca is :5
*/

fun main7() {
    val t = Tree()
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    t.createBinarySearchTree(arr)
    t.printInOrder()
    t.printInRange(4, 7)
    t.trimOutsideRange(4, 7)
    t.printInOrder()
}

/*
1 2 3 4 5 6 7 8 9 10 
4 5 6 7 
4 5 6 7 
*/

fun main8() {
    val t = Tree()
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    t.createBinarySearchTree(arr)
    println(t.ancestor(1, 10)!!.value)
    // 5
    println(t.ceilBST(5.5))
    // 6
    println(t.floorBST(5.5))
    // 5
}

fun isBSTArray(preorder: IntArray): Boolean {
    val size = preorder.size
    val stk: Stack<Int> = Stack<Int>()
    var value: Int
    var root = -999999
    for (i in 0 until size) {
        value = preorder[i]

        // If value of the right child is less than root.
        if (value < root) return false
        // First left child values will be popped
        // Last popped value will be the root.
        while (stk.size > 0 && stk.peek() < value) root = stk.pop()

        // add current value to the stack.
        stk.push(value)
    }
    return true
}

// Testing code.
fun main9() {
    val arr1 = intArrayOf(5, 2, 4, 6, 9, 10)
    val arr2 = intArrayOf(5, 2, 6, 4, 7, 9, 10)
    println(isBSTArray(arr1))
    println(isBSTArray(arr2))
}

/*
true
false
*/

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
}