import java.util.ArrayDeque
import java.util.Stack

class Tree {
    var root: Node? = null

    class Node {
        var value: Int = 0
        var lChild: Node? = null
        var rChild: Node? = null

        constructor(v: Int, l: Node? = null, r: Node? = null) {
            value = v
            lChild = l
            rChild = r
        }
    }

    val isBST: Boolean
        get() = isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE)

    val isBST2: Boolean
        get() {

            val count = IntArray(1)
            return isBST2(root, count)
        }

    internal val isCompleteTree: Boolean
        get() {
            val que = ArrayDeque<Node>()
            var temp: Node? = null
            var noChild = 0
            if (root != null)
                que.add(root)
            while (que.size != 0) {
                temp = que.remove()
                if (temp!!.lChild != null) {
                    if (noChild == 1)
                        return false
                    que.add(temp!!.lChild)
                } else
                    noChild = 1

                if (temp!!.rChild != null) {
                    if (noChild == 1)
                        return false
                    que.add(temp!!.rChild)
                } else
                    noChild = 1
            }
            return true
        }

    internal val isCompleteTree2: Boolean
        get() {
            val count = numNodes()
            return isCompleteTreeUtil(root, 0, count)
        }

    internal val isHeap: Boolean
        get() {
            val infi = -9999999
            return isCompleteTree && isHeapUtil(root, infi)
        }

    internal val isHeap2: Boolean
        get() {
            val count = numNodes()
            val parentValue = -9999999
            return isHeapUtil2(root, 0, count, parentValue)
        }

    /* Other methods */

    fun levelOrderBinaryTree(arr: IntArray) {
        root = levelOrderBinaryTree(arr, 0)
    }

    fun levelOrderBinaryTree(arr: IntArray, start: Int): Node {
        val size = arr.size
        val curr = Node(arr[start])

        val left = 2 * start + 1
        val right = 2 * start + 2

        if (left < size)
            curr.lChild = levelOrderBinaryTree(arr, left)
        if (right < size)
            curr.rChild = levelOrderBinaryTree(arr, right)

        return curr
    }

    fun InsertNode(value: Int) {
        root = InsertNode(root, value)
    }

    private fun InsertNode(node: Node?, value: Int): Node {
        var node = node
        if (node == null) {
            node = Node(value, null, null)
        } else {
            if (node.value > value) {
                node.lChild = InsertNode(node.lChild, value)
            } else {
                node.rChild = InsertNode(node.rChild, value)
            }
        }
        return node
    }

    fun PrintPreOrder() {
        PrintPreOrder(root)
    }

    private fun PrintPreOrder(node: Node?)/* pre order */ {
        if (node != null) {
            print(" " + node.value)
            PrintPreOrder(node.lChild)
            PrintPreOrder(node.rChild)
        }
    }

    fun NthPreOrder(index: Int) {
        val counter = intArrayOf(0)
        NthPreOrder(root, index, counter)
    }

    private fun NthPreOrder(node: Node?, index: Int, counter: IntArray)/* pre order */ {
        if (node != null) {
            counter[0]++
            if (counter[0] == index) {
                print(node.value)
            }
            NthPreOrder(node.lChild, index, counter)
            NthPreOrder(node.rChild, index, counter)
        }
    }

    fun PrintPostOrder() {
        PrintPostOrder(root)
    }

    private fun PrintPostOrder(node: Node?)/* post order */ {
        if (node != null) {
            PrintPostOrder(node.lChild)
            PrintPostOrder(node.rChild)
            print(" " + node.value)
        }
    }

    fun NthPostOrder(index: Int) {
        val counter = intArrayOf(0)
        NthPostOrder(root, index, counter)
    }

    private fun NthPostOrder(node: Node?, index: Int, counter: IntArray)/* post order */ {
        if (node != null) {
            NthPostOrder(node.lChild, index, counter)
            NthPostOrder(node.rChild, index, counter)
            counter[0]++
            if (counter[0] == index) {
                print(" " + node.value)
            }
        }
    }

    fun PrintInOrder() {
        PrintInOrder(root)
    }

    private fun PrintInOrder(node: Node?)/* In order */ {
        if (node != null) {
            PrintInOrder(node.lChild)
            print(" " + node.value)
            PrintInOrder(node.rChild)
        }
    }

    fun NthInOrder(index: Int) {
        val counter = intArrayOf(0)
        NthInOrder(root, index, counter)
    }

    private fun NthInOrder(node: Node?, index: Int, counter: IntArray) {

        if (node != null) {
            NthInOrder(node.lChild, index, counter)
            counter[0]++
            if (counter[0] == index) {
                print(" " + node.value)
            }
            NthInOrder(node.rChild, index, counter)
        }
    }

    fun PrintBredthFirst() {
        val que = ArrayDeque<Node>()
        var temp: Node
        if (root != null)
            que.add(root)

        while (que.isEmpty() == false) {
            temp = que.remove()
            print(" " + temp.value)

            if (temp.lChild != null)
                que.add(temp.lChild)
            if (temp.rChild != null)
                que.add(temp.rChild)
        }
    }

    fun PrintDepthFirst() {
        val stk = ArrayDeque<Node>()
        var temp: Node

        if (root != null)
            stk.push(root)

        while (stk.isEmpty() == false) {
            temp = stk.pop()
            println(temp.value)

            if (temp.lChild != null)
                stk.push(temp.lChild)
            if (temp.rChild != null)
                stk.push(temp.rChild)
        }
    }

    internal fun PrintLevelOrderLineByLine() {
        val que1 = ArrayDeque<Node>()
        val que2 = ArrayDeque<Node>()
        var temp: Node? = null
        if (root != null)
            que1.add(root)
        while (que1.size != 0 || que2.size != 0) {
            while (que1.size != 0) {
                temp = que1.remove()
                print(" " + temp!!.value)
                if (temp!!.lChild != null)
                    que2.add(temp!!.lChild)
                if (temp!!.rChild != null)
                    que2.add(temp!!.rChild)
            }
            println("")

            while (que2.size != 0) {
                temp = que2.remove()
                print(" " + temp!!.value)
                if (temp!!.lChild != null)
                    que1.add(temp.lChild)
                if (temp.rChild != null)
                    que1.add(temp.rChild)
            }
            println("")
        }
    }

    internal fun PrintLevelOrderLineByLine2() {
        val que = ArrayDeque<Node>()
        var temp: Node? = null
        var count = 0

        if (root != null)
            que.add(root)
        while (que.size != 0) {
            count = que.size
            while (count > 0) {
                temp = que.remove()
                print(" " + temp!!.value)
                if (temp!!.lChild != null)
                    que.add(temp!!.lChild)
                if (temp!!.rChild != null)
                    que.add(temp!!.rChild)
                count -= 1
            }
            println("")
        }
    }

    internal fun PrintSpiralTree() {
        val stk1 = Stack<Node>()
        val stk2 = Stack<Node>()

        var temp: Node
        if (root != null)
            stk1.push(root)
        while (stk1.size != 0 || stk2.size != 0) {
            while (stk1.size != 0) {
                temp = stk1.pop()
                print(" " + temp.value)
                if (temp.rChild != null)
                    stk2.push(temp.rChild)
                if (temp.lChild != null)
                    stk2.push(temp.lChild)
            }
            while (stk2.size != 0) {
                temp = stk2.pop()
                print(" " + temp.value)
                if (temp.lChild != null)
                    stk1.push(temp.lChild)
                if (temp.rChild != null)
                    stk1.push(temp.rChild)
            }
        }
    }

    fun Find(value: Int): Boolean {
        var curr = root

        while (curr != null) {
            if (curr.value == value) {
                return true
            } else if (curr.value > value) {
                curr = curr.lChild
            } else {
                curr = curr.rChild
            }
        }
        return false
    }

    fun Find2(value: Int): Boolean {
        var curr = root
        while (curr != null && curr.value != value)
            curr = if (curr.value > value) curr.lChild else curr.rChild
        return curr != null
    }

    fun FindMin(): Int {
        var node: Node? = root ?: return Integer.MAX_VALUE

        while (node!!.lChild != null) {
            node = node.lChild
        }
        return node.value
    }

    fun FindMax(): Int {
        var node: Node? = root ?: return Integer.MIN_VALUE

        while (node!!.rChild != null) {
            node = node.rChild
        }
        return node.value
    }

    fun FindMaxNode(curr: Node?): Node? {
        var node: Node? = curr ?: return null

        while (node!!.rChild != null) {
            node = node.rChild
        }
        return node
    }

    fun FindMinNode(curr: Node?): Node? {
        var node: Node? = curr ?: return null

        while (node!!.lChild != null) {
            node = node.lChild
        }
        return node
    }

    fun Free() {
        root = null
    }

    fun DeleteNode(value: Int) {
        root = DeleteNode(root, value)
    }

    private fun DeleteNode(node: Node?, value: Int): Node? {
        var temp: Node? = null

        if (node != null) {
            if (node.value == value) {
                if (node.lChild == null && node.rChild == null) {
                    return null
                } else {
                    if (node.lChild == null) {
                        temp = node.rChild
                        return temp
                    }

                    if (node.rChild == null) {
                        temp = node.lChild
                        return temp
                    }
                    val minNode = FindMinNode(node.rChild)
                    val minValue = minNode!!.value
                    node.value = minValue
                    node.rChild = DeleteNode(node.rChild, minValue)
                }
            } else {
                if (node.value > value) {
                    node.lChild = DeleteNode(node.lChild, value)
                } else {
                    node.rChild = DeleteNode(node.rChild, value)
                }
            }
        }
        return node
    }

    fun TreeDepth(): Int {
        return TreeDepth(root)
    }

    private fun TreeDepth(curr: Node?): Int {
        if (curr == null)
            return 0
        else {
            val lDepth = TreeDepth(curr.lChild)
            val rDepth = TreeDepth(curr.rChild)

            return if (lDepth > rDepth)
                lDepth + 1
            else
                rDepth + 1
        }
    }

    fun isEqual(T2: Tree): Boolean {
        return isEqualUtil(root, T2.root)
    }

    private fun isEqualUtil(node1: Node?, node2: Node?): Boolean {
        return if (node1 == null && node2 == null)
            true
        else if (node1 == null || node2 == null)
            false
        else
            isEqualUtil(node1.lChild, node2.lChild) && isEqualUtil(node1.rChild, node2.rChild)
                    && node1.value == node2.value
    }

    fun Ancestor(first: Int, second: Int): Node? {
        var first = first
        var second = second
        if (first > second) {
            val temp = first
            first = second
            second = temp
        }
        return Ancestor(root, first, second)
    }

    private fun Ancestor(curr: Node?, first: Int, second: Int): Node? {
        if (curr == null) {
            return null
        }

        if (curr.value > first && curr.value > second) {
            return Ancestor(curr.lChild, first, second)
        }
        return if (curr.value < first && curr.value < second) {
            Ancestor(curr.rChild, first, second)
        } else curr
    }

    fun CopyTree(): Tree {
        val tree2 = Tree()
        tree2.root = CopyTree(root)
        return tree2
    }

    private fun CopyTree(curr: Node?): Node? {
        val temp: Node
        if (curr != null) {
            temp = Node(curr.value)
            temp.lChild = CopyTree(curr.lChild)
            temp.rChild = CopyTree(curr.rChild)
            return temp
        } else
            return null
    }

    fun CopyMirrorTree(): Tree {
        val tree2 = Tree()
        tree2.root = CopyMirrorTree(root)
        return tree2
    }

    private fun CopyMirrorTree(curr: Node?): Node? {
        val temp: Node
        if (curr != null) {
            temp = Node(curr.value)
            temp.rChild = CopyMirrorTree(curr.lChild)
            temp.lChild = CopyMirrorTree(curr.rChild)
            return temp
        } else
            return null
    }

    @JvmOverloads
    fun numNodes(curr: Node? = root): Int {
        return if (curr == null)
            0
        else
            1 + numNodes(curr.rChild) + numNodes(curr.lChild)
    }

    fun numFullNodesBT(): Int {
        return numNodes(root)
    }

    fun numFullNodesBT(curr: Node?): Int {
        var count: Int
        if (curr == null)
            return 0

        count = numFullNodesBT(curr.rChild) + numFullNodesBT(curr.lChild)
        if (curr.rChild != null && curr.lChild != null)
            count++

        return count
    }

    fun maxLengthPathBT(): Int {
        return maxLengthPathBT(root)
    }

    private fun maxLengthPathBT(curr: Node?)// diameter
            : Int {
        var max: Int
        val leftPath: Int
        val rightPath: Int
        val leftMax: Int
        val rightMax: Int

        if (curr == null)
            return 0

        leftPath = TreeDepth(curr.lChild)
        rightPath = TreeDepth(curr.rChild)

        max = leftPath + rightPath + 1

        leftMax = maxLengthPathBT(curr.lChild)
        rightMax = maxLengthPathBT(curr.rChild)

        if (leftMax > max)
            max = leftMax

        if (rightMax > max)
            max = rightMax

        return max
    }

    fun numLeafNodes(): Int {
        return numLeafNodes(root)
    }

    private fun numLeafNodes(curr: Node?): Int {
        if (curr == null)
            return 0
        return if (curr.lChild == null && curr.rChild == null)
            1
        else
            numLeafNodes(curr.rChild) + numLeafNodes(curr.lChild)
    }

    fun sumAllBT(): Int {
        return sumAllBT(root)
    }

    private fun sumAllBT(curr: Node?): Int {
        return if (curr == null) 0 else curr.value + sumAllBT(curr.lChild) + sumAllBT(curr.lChild)

    }

    fun iterativePreOrder() {
        val stk = Stack<Node>()
        var curr: Node

        if (root != null)
            stk.add(root)

        while (stk.isEmpty() == false) {
            curr = stk.pop()
            print(curr.value.toString() + " ")

            if (curr.rChild != null)
                stk.push(curr.rChild)

            if (curr.lChild != null)
                stk.push(curr.lChild)
        }
    }

    fun iterativePostOrder() {
        val stk = Stack<Node>()
        val visited = Stack<Int>()
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
                if (curr.rChild != null) {
                    stk.push(curr.rChild)
                    visited.push(0)
                }
                if (curr.lChild != null) {
                    stk.push(curr.lChild)
                    visited.push(0)
                }
            }
        }
    }

    fun iterativeInOrder() {
        val stk = Stack<Node>()
        val visited = Stack<Int>()
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
                if (curr.rChild != null) {
                    stk.push(curr.rChild)
                    visited.push(0)
                }
                stk.push(curr)
                visited.push(1)
                if (curr.lChild != null) {
                    stk.push(curr.lChild)
                    visited.push(0)
                }
            }
        }
    }

    fun isBST3(root: Node?): Boolean {
        if (root == null)
            return true
        if (root.lChild != null && FindMaxNode(root.lChild)!!.value > root.value)
            return false
        return if (root.rChild != null && FindMinNode(root.rChild)!!.value <= root.value) false else isBST3(root.lChild) && isBST3(
            root.rChild
        )
    }

    fun isBST(curr: Node?, min: Int, max: Int): Boolean {
        if (curr == null)
            return true

        return if (curr.value < min || curr.value > max) false else isBST(
            curr.lChild,
            min,
            curr.value
        ) && isBST(curr.rChild, curr.value, max)

    }

    private fun isBST2(root: Node?, count: IntArray)/* in order traversal */: Boolean {
        var ret: Boolean
        if (root != null) {
            ret = isBST2(root.lChild, count)
            if (!ret)
                return false

            if (count[0] > root.value)
                return false
            count[0] = root.value

            ret = isBST2(root.rChild, count)
            if (!ret)
                return false
        }
        return true
    }

    internal fun isCompleteTreeUtil(curr: Node?, index: Int, count: Int): Boolean {
        if (curr == null)
            return true
        return if (index > count) false else isCompleteTreeUtil(
            curr.lChild,
            index * 2 + 1,
            count
        ) && isCompleteTreeUtil(curr.rChild, index * 2 + 2, count)
    }

    internal fun isHeapUtil(curr: Node?, parentValue: Int): Boolean {
        if (curr == null)
            return true
        return if (curr.value < parentValue) false else isHeapUtil(
            curr.lChild,
            curr.value
        ) && isHeapUtil(curr.rChild, curr.value)
    }

    internal fun isHeapUtil2(curr: Node?, index: Int, count: Int, parentValue: Int): Boolean {
        if (curr == null)
            return true
        if (index > count)
            return false
        return if (curr.value < parentValue) false else isHeapUtil2(
            curr.lChild,
            index * 2 + 1,
            count,
            curr.value
        ) && isHeapUtil2(curr.rChild, index * 2 + 2, count, curr.value)
    }

    // void DFS(Node head)
    // {
    // Node curr = head, prev;
    // int count = 0;
    // while (curr && ! curr.visited)
    // {
    // count++;
    // if (curr.lChild && ! curr.lChild.visited)
    // {
    // curr= curr.lChild;
    // }
    // else if (curr.rChild && ! curr.rChild.visited)
    // {
    // curr= curr.rChild;
    // }
    // else
    // {
    // System.out.print((" " + curr.value);
    // curr.visited = 1;
    // curr = head;
    // }
    // }
    // System.out.print(("count is : " + count);
    // }

    fun treeToListRec(): Node? {
        return treeToListRec(root)
    }

    private fun treeToListRec(curr: Node?): Node? {
        var Head: Node? = null
        var Tail: Node? = null
        if (curr == null)
            return null

        if (curr.lChild == null && curr.rChild == null) {
            curr.lChild = curr
            curr.rChild = curr
            return curr
        }

        if (curr.lChild != null) {
            Head = treeToListRec(curr.lChild)
            Tail = Head!!.lChild

            curr.lChild = Tail
            Tail!!.rChild = curr
        } else
            Head = curr

        if (curr.rChild != null) {
            val tempHead = treeToListRec(curr.rChild)
            Tail = tempHead!!.lChild

            curr.rChild = tempHead
            tempHead.lChild = curr
        } else
            Tail = curr

        Head.lChild = Tail
        Tail!!.rChild = Head
        return Head
    }

    fun printAllPath() {
        val stk = Stack<Int>()
        printAllPathUtil(root, stk)
    }

    private fun printAllPathUtil(curr: Node?, stk: Stack<Int>) {
        if (curr == null)
            return

        stk.push(curr.value)

        if (curr.lChild == null && curr.rChild == null) {
            println(stk)
            stk.pop()
            return
        }

        printAllPathUtil(curr.rChild, stk)
        printAllPathUtil(curr.lChild, stk)
        stk.pop()
    }

    fun LCA(first: Int, second: Int): Int {
        val ans = LCA(root, first, second)
        return ans?.value ?: Integer.MIN_VALUE
    }

    private fun LCA(curr: Node?, first: Int, second: Int): Node? {
        val left: Node?
        val right: Node?

        if (curr == null)
            return null

        if (curr.value == first || curr.value == second)
            return curr

        left = LCA(curr.lChild, first, second)
        right = LCA(curr.rChild, first, second)

        return if (left != null && right != null)
            curr
        else left ?: right
    }

    fun LcaBST(first: Int, second: Int): Int {
        return LcaBST(root, first, second)
    }

    private fun LcaBST(curr: Node?, first: Int, second: Int): Int {
        if (curr == null) {
            return Integer.MAX_VALUE
        }

        if (curr.value > first && curr.value > second) {
            return LcaBST(curr.lChild, first, second)
        }
        return if (curr.value < first && curr.value < second) {
            LcaBST(curr.rChild, first, second)
        } else curr.value
    }

    fun trimOutsideRange(min: Int, max: Int) {
        trimOutsideRange(root, min, max)
    }

    private fun trimOutsideRange(curr: Node?, min: Int, max: Int): Node? {
        if (curr == null)
            return null

        curr.lChild = trimOutsideRange(curr.lChild, min, max)
        curr.rChild = trimOutsideRange(curr.rChild, min, max)

        if (curr.value < min) {
            return curr.rChild
        }

        return if (curr.value > max) {
            curr.lChild
        } else curr

    }

    fun printInRange(min: Int, max: Int) {
        printInRange(root, min, max)
    }

    private fun printInRange(root: Node?, min: Int, max: Int) {
        if (root == null)
            return

        printInRange(root.lChild, min, max)

        if (root.value >= min && root.value <= max)
            print(root.value.toString() + " ")

        printInRange(root.rChild, min, max)
    }

    fun FloorBST(`val`: Int): Int {
        var curr = root
        var floor = Integer.MAX_VALUE

        while (curr != null) {
            if (curr.value == `val`) {
                floor = curr.value
                break
            } else if (curr.value > `val`) {
                curr = curr.lChild
            } else {
                floor = curr.value
                curr = curr.rChild
            }
        }
        return floor
    }

    fun CeilBST(`val`: Int): Int {
        var curr = root
        var ceil = Integer.MIN_VALUE

        while (curr != null) {
            if (curr.value == `val`) {
                ceil = curr.value
                break
            } else if (curr.value > `val`) {
                ceil = curr.value
                curr = curr.lChild
            } else {
                curr = curr.rChild
            }
        }
        return ceil
    }

    fun findMaxBT(): Int {
        return findMaxBT(root)
    }

    private fun findMaxBT(curr: Node?): Int {
        val left: Int
        val right: Int

        if (curr == null)
            return Integer.MIN_VALUE

        var max = curr.value

        left = findMaxBT(curr.lChild)
        right = findMaxBT(curr.rChild)

        if (left > max)
            max = left
        if (right > max)
            max = right

        return max
    }

    fun searchBT(value: Int): Boolean {
        return searchBTUtil(root, value)
    }

    fun searchBTUtil(curr: Node?, value: Int): Boolean {
        val left: Boolean
        val right: Boolean
        if (curr == null)
            return false

        if (curr.value == value)
            return true

        left = searchBTUtil(curr.lChild, value)
        if (left)
            return true

        right = searchBTUtil(curr.rChild, value)
        return if (right) true else false
    }

    fun CreateBinaryTree(arr: IntArray) {
        root = CreateBinaryTree(arr, 0, arr.size - 1)
    }

    private fun CreateBinaryTree(arr: IntArray, start: Int, end: Int): Node? {
        var curr: Node? = null
        if (start > end)
            return null

        val mid = (start + end) / 2
        curr = Node(arr[mid])
        curr.lChild = CreateBinaryTree(arr, start, mid - 1)
        curr.rChild = CreateBinaryTree(arr, mid + 1, end)
        return curr
    }

    internal fun isBSTArray(preorder: IntArray, size: Int): Boolean {
        val stk = Stack<Int>()
        var value: Int
        var root = -999999
        for (i in 0 until size) {
            value = preorder[i]

            // If value of the right child is less than root.
            if (value < root)
                return false
            // First left child values will be popped
            // Last popped value will be the root.
            while (stk.size > 0 && stk.peek() < value)
                root = stk.pop()
            // add current value to the stack.
            stk.push(value)
        }
        return true
    }
}

fun main(args: Array<String>) {
    val t = Tree()
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    t.levelOrderBinaryTree(arr)
    println("")
    println(t.isHeap)
    println(t.isHeap2)
    println(t.isCompleteTree)

    println("")
    t.PrintBredthFirst()
    println("")
    t.PrintPreOrder()
    println("")
    t.PrintLevelOrderLineByLine()
    println("")
    t.PrintLevelOrderLineByLine2()
    println("")
    t.PrintSpiralTree()
    println("")
    t.printAllPath()
    println("")
    t.NthInOrder(4)
    println("")
    t.NthPostOrder(4)
    println("")
    t.NthPreOrder(4)
    println("")
    /*
 * t.PrintPostOrder(); System.out.println(); t.iterativePostOrder();
 * t.PrintBredthFirst(); // t.treeToListRec(); t.printAllPath();
 * System.out.println(t.LCA(10, 3)); t.iterativePreOrder(); t.PrintPreOrder();
 * // t.CreateBinaryTree(arr); // System.out.println(t.isBST2());
 */
}
