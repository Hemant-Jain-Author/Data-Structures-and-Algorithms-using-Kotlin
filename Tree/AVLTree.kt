class AVLTree(var root: Node? = null) {

    class Node(var data: Int, var left: Node?, var right: Node?) {
        var height = 0
    }

    fun height(n: Node?): Int {
        return n?.height ?: -1
    }

    fun getBalance(node: Node?): Int {
        return if (node == null) 0 
               else height(node.left) - height(node.right)
    }

    fun printTree() {
        printTree(root, "", false)
        println()
    }

    private fun printTree(node: Node?, indentIn: String, isLeft: Boolean) {
        var indent = indentIn
        if (node == null) return
        if (isLeft) {
            print(indent + "L:")
            indent += "|  "
        } else {
            print(indent + "R:")
            indent += "   "
        }
        println(node.data.toString() + "(" + node.height + ")")
        printTree(node.left, indent, true)
        printTree(node.right, indent, false)
    }

    fun insert(data: Int) {
        root = insert(root, data)
    }

    private fun insert(node: Node?, data: Int): Node? {
        if (node == null) return Node(data, null, null)
        if (node.data > data) {
            node.left = insert(node.left, data)
        } else if (node.data < data) {
            node.right = insert(node.right, data)
        } else { // Duplicate data not allowed
            return node
        }
        node.height = max(height(node.left), height(node.right)) + 1
        val balance = getBalance(node)
        if (balance > 1) {
            if (data < node.left!!.data) // Left Left Case
            {
                return rightRotate(node)
            }
            if (data > node.left!!.data) // Left Right Case
            {
                return leftRightRotate(node)
            }
        }
        if (balance < -1) {
            if (data > node.right!!.data) // Right Right Case
            {
                return leftRotate(node)
            }
            if (data < node.right!!.data) // Right Left Case
            {
                return rightLeftRotate(node)
            }
        }
        return node
    }

    // Function to right rotate subtree rooted with x
    fun rightRotate(x: Node?): Node? {
        val y = x!!.left
        val T = y!!.right

        // Rotation
        y.right = x
        x.left = T

        // Update heights
        x.height = max(height(x.left), height(x.right)) + 1
        y.height = max(height(y.left), height(y.right)) + 1

        // Return new root
        return y
    }

    // Function to left rotate subtree rooted with x
    fun leftRotate(x: Node?): Node? {
        val y = x!!.right
        val T = y!!.left

        // Rotation
        y.left = x
        x.right = T

        // Update heights
        x.height = max(height(x.left), height(x.right)) + 1
        y.height = max(height(y.left), height(y.right)) + 1

        // Return new root
        return y
    }

    // Function to right then left rotate subtree rooted with x
    fun rightLeftRotate(x: Node): Node? {
        x.right = rightRotate(x.right)
        return leftRotate(x)
    }

    // Function to left then right rotate subtree rooted with x
    fun leftRightRotate(x: Node): Node? {
        x.left = leftRotate(x.left)
        return rightRotate(x)
    }

    fun delete(data: Int) {
        root = delete(root, data)
    }

    private fun delete(node: Node?, data: Int): Node? {
        if (node == null) return null
        if (node.data == data) {
            if (node.left == null && node.right == null) {
                return null
            } else if (node.left == null) {
                return node.right // no need to modify height
            } else if (node.right == null) {
                return node.left // no need to modify height
            } else {
                val minNode = findMin(node.right)
                node.data = minNode!!.data
                node.right = delete(node.right, minNode.data)
            }
        } else {
            if (node.data > data) {
                node.left = delete(node.left, data)
            } else {
                node.right = delete(node.right, data)
            }
        }
        node.height = max(height(node.left), height(node.right)) + 1
        val balance = getBalance(node)
        if (balance > 1) {
            if (data >= node.left!!.data) { // Left Left Case 
                return rightRotate(node)
            }
            if (data < node.left!!.data) { // Left Right Case
                return leftRightRotate(node)
            }
        }
        if (balance < -1) {
            if (data <= node.right!!.data) // Right Right Case 
            {
                return leftRotate(node)
            }
            if (data > node.right!!.data) // Right Left Case
            {
                return rightLeftRotate(node)
            }
        }
        return node
    }

    fun findMin(curr: Node?): Node? {
        var node: Node? = curr ?: return null
        while (node!!.left != null) {
            node = node.left
        }
        return node
    }

    fun max(a: Int, b: Int): Int {
        return if (a > b) a else b
    }
}

// Testing Code.
fun main() {
    val t = AVLTree()
    t.insert(1)
    t.insert(2)
    t.insert(3)
    t.insert(4)
    t.insert(5)
    t.insert(6)
    t.insert(7)
    t.insert(8)
    t.printTree()

/*
R:4(3)
L:2(1)
|  L:1(0)
|  R:3(0)
R:6(2)
L:5(0)
R:7(1)
    R:8(0)

*/
    t.delete(5)
    t.printTree()

/*
R:4(2)
L:2(1)
|  L:1(0)
|  R:3(0)
R:7(1)
L:6(0)
R:8(0)

*/
    t.delete(1)
    t.printTree()

/*
R:4(2)
L:2(1)
|  R:3(0)
R:7(1)
L:6(0)
R:8(0)

*/
    t.delete(2)
    t.printTree()

/*
R:4(2)
L:3(0)
R:7(1)
L:6(0)
R:8(0) 
*/
}