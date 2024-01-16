class SPLAYTree( var root: Node? = null ) {

    class Node(var data: Int, var left: Node?, var right: Node?, var parent: Node? = null) 

    fun printTree() {
        printTree(root, "", false)
        println()
    }

    private fun printTree(node: Node?, ind: String, isLeft: Boolean) {
        var indent = ind
        if (node == null) return
        indent += if (isLeft) {
            print(indent + "L:")
            "|  "
        } else {
            print(indent + "R:")
            "   "
        }
        println(node.data)
        printTree(node.left, indent, true)
        printTree(node.right, indent, false)
    }

    // Function to right rotate subtree rooted with x
    fun rightRotate(x: Node): Node? {
        val y = x.left
        val T = y!!.right

        // Rotation
        y.parent = x.parent
        y.right = x
        x.parent = y
        x.left = T
        if (T != null) T.parent = x
        if (y.parent != null && y.parent!!.left === x) y.parent!!.left =
            y else if (y.parent != null && y.parent!!.right === x) y.parent!!.right = y
        // Return new root
        return y
    }

    // Function to left rotate subtree rooted with x
    fun leftRotate(x: Node): Node? {
        val y = x.right
        val T = y!!.left

        // Rotation
        y.parent = x.parent
        y.left = x
        x.parent = y
        x.right = T
        if (T != null) T.parent = x
        if (y.parent != null && y.parent!!.left === x) y.parent!!.left =
            y else if (y.parent != null && y.parent!!.right === x) y.parent!!.right = y
        // Return new root
        return y
    }

    fun parent(node: Node?): Node? {
        return if (node == null || node.parent == null) null else node.parent
    }

    fun splay(nd: Node?) {
        var node = nd
        var parent: Node?
        var grand: Node?
        while (node !== root) {
            parent = parent(node)
            grand = parent(parent)
            if (parent == null) { // rotations had created new root, always last condition.
                root = node
            } else if (grand == null) { // single rotation case.
                node = if (parent.left === node) {
                    rightRotate(parent)
                } else {
                    leftRotate(parent)
                }
            } else if (grand.left === parent && parent.left === node) { // Zig Zig case.
                rightRotate(grand)
                node = rightRotate(parent)
            } else if (grand.right === parent && parent.right === node) { // Zag Zag case.
                leftRotate(grand)
                node = leftRotate(parent)
            } else if (grand.left === parent && parent.right === node) { //Zig Zag case.
                leftRotate(parent)
                node = rightRotate(grand)
            } else if (grand.right === parent && parent.left === node) { // Zag Zig case.
                rightRotate(parent)
                node = leftRotate(grand)
            }
        }
    }

    fun find(data: Int): Boolean {
        var curr = root
        while (curr != null) {
            curr = if (curr.data == data) {
                splay(curr)
                return true
            } else if (curr.data > data) {
                curr.left
            } else {
                curr.right
            }
        }
        return false
    }

    fun insert(data: Int) {
        val newNode = Node(data, null, null)
        if (root == null) {
            root = newNode
            return
        }
        var node = root
        var parent: Node? = null
        while (node != null) {
            parent = node
            node = if (node.data > data) {
                node.left
            } else if (node.data < data) {
                node.right
            } else {
                splay(node) // duplicate insertion not allowed but splaying for it.
                return
            }
        }
        newNode.parent = parent
        if (parent!!.data > data) {
            parent.left = newNode
        } else {
            parent.right = newNode
        }
        splay(newNode)
    }

    fun findMinNode(curr: Node?): Node? {
        var node: Node? = curr ?: return null
        while (node!!.left != null) {
            node = node.left
        }
        return node
    }

    fun delete(dt: Int) {
        var data = dt
        var node = root
        var parent: Node? = null
        var next: Node? = null
        while (node != null) {
            if (node.data == data) {
                parent = node.parent
                if (node.left == null && node.right == null) {
                    next = null
                } else if (node.left == null) {
                    next = node.right
                } else if (node.right == null) {
                    next = node.left
                }
                if (node.left == null || node.right == null) {
                    if (node === root) {
                        root = next
                        return
                    }
                    if (parent!!.left === node) {
                        parent!!.left = next
                    } else {
                        parent!!.right = next
                    }
                    if (next != null) next.parent = parent
                    break
                }
                val minNode = findMinNode(node.right)
                data = minNode!!.data
                node.data = data
                node = node.right
            } else if (node.data > data) {
                parent = node
                node = node.left
            } else {
                parent = node
                node = node.right
            }
        }
        splay(parent) // Splaying for the parent of the node deleted.
    }

    fun printInOrder() {
        printInOrder(root)
        println()
    }

    private fun printInOrder(node: Node?) /* In order */ {
        if (node != null) {
            printInOrder(node.left)
            print(node.data.toString() + " ")
            printInOrder(node.right)
        }
    }
}

fun main() {
    val tree = SPLAYTree()
    tree.insert(5)
    tree.insert(4)
    tree.insert(6)
    tree.insert(3)
    tree.insert(2)
    tree.insert(1)
    tree.insert(3)
    tree.printTree()
    println("Value 2 found: " + tree.find(2))
    tree.delete(2)
    tree.delete(5)
    tree.printTree()
}

/*
R:3
   L:2
   |  L:1
   R:6
      L:4
      |  R:5

Value 2 found: true
R:4
   L:3
   |  L:1
   R:6
*/