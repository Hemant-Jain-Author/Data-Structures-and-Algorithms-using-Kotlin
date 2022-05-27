class RBTree {
    private var root: Node?
    private val NullNode: Node

    class Node(var data: Int, var left: Node?) {
        var right : Node?
        var parent : Node?
        var colour : Boolean // true for red colour, false for black colour

        init {
            right = left
            parent = left
            colour = true // New node are red in colour.
        }
    }

    init {
        NullNode = Node(0, null)
        NullNode.colour = false
        root = NullNode
    }

    // To check whether node is of colour red or not.
    fun isRed(node: Node?): Boolean {
        return if (node == null) false else node.colour == true
    }

    fun printTree() {
        printTree(root, "", false)
        println()
    }

    private fun printTree(node: Node?, inde: String, isLeft: Boolean) {
        var indent = inde
        if (node === NullNode) return
        if (isLeft) {
            print(indent + "L:")
            indent += "|  "
        } else {
            print(indent + "R:")
            indent += "   "
        }
        println(node!!.data.toString() + "(" + node.colour + ")")
        printTree(node.left, indent, true)
        printTree(node.right, indent, false)
    }

    /* Other methods */ // Function to right rotate subtree rooted with x
    fun rightRotate(x: Node?): Node? {
        val y = x!!.left
        val T = y!!.right

        // Rotation
        y.parent = x.parent
        y.right = x
        x.parent = y
        x.left = T
        if (T !== NullNode) T!!.parent = x
        if (x === root) {
            root = y
            return y
        }
        if (y.parent!!.left === x) y.parent!!.left = y 
        else y.parent!!.right = y

        // Return new root
        return y
    }

    // Function to left rotate subtree rooted with x
    fun leftRotate(x: Node?): Node? {
        val y = x!!.right
        val T = y!!.left

        // Rotation
        y.parent = x.parent
        y.left = x
        x.parent = y
        x.right = T
        if (T !== NullNode) T!!.parent = x
        if (x === root) {
            root = y
            return y
        }
        if (y.parent!!.left === x) y.parent!!.left = y 
        else y.parent!!.right = y

        // Return new root
        return y
    }

    fun rightLeftRotate(node: Node?): Node? {
        node!!.right = rightRotate(node.right)
        return leftRotate(node)
    }

    fun leftRightRotate(node: Node?): Node? {
        node!!.left = leftRotate(node.left)
        return rightRotate(node)
    }

    fun find(data: Int): Node? {
        var curr = root
        while (curr !== NullNode) {
            if (curr!!.data == data) {
                return curr
            } else if (curr.data > data) {
                curr = curr.left
            } else {
                curr = curr.right
            }
        }
        return null
    }

    fun insert(data: Int) {
        root = insert(root, data)
        val temp = find(data)
        fixRedRed(temp)
    }

    private fun insert(nd: Node?, data: Int): Node? {
        var node = nd
        if (node === NullNode) {
            node = Node(data, NullNode)
        } else if (node!!.data > data) {
            node.left = insert(node.left, data)
            node.left!!.parent = node
        } else if (node.data < data) {
            node.right = insert(node.right, data)
            node.right!!.parent = node
        }
        return node
    }

    fun fixRedRed(x: Node?) {
        // if x is root colour it black and return
        if (x === root) {
            x!!.colour = false
            return
        }
        if (x!!.parent === NullNode || x!!.parent!!.parent === NullNode) {
            return
        }
        // Initialize parent, grandparent, uncle
        val parent = x!!.parent
        val grandparent = parent!!.parent
        val uncle = uncle(x)
        var mid: Node? = null
        if (parent.colour == false) {
            return
        }

        // parent colour is red. gp is black.
        if (uncle !== NullNode && uncle!!.colour == true) {
            // uncle and parent is red.
            parent.colour = false
            uncle.colour = false
            grandparent!!.colour = true
            fixRedRed(grandparent)
            return
        }

        // parent is red, uncle is black and gp is black.
        // Perform LR, LL, RL, RR
        if (parent === grandparent!!.left && x === parent.left) // LL
        {
            mid = rightRotate(grandparent)
        } else if (parent === grandparent!!.left && x === parent.right) // LR
        {
            mid = leftRightRotate(grandparent)
        } else if (parent === grandparent!!.right && x === parent.left) // RL
        {
            mid = rightLeftRotate(grandparent)
        } else if (parent === grandparent!!.right && x === parent.right) // RR
        {
            mid = leftRotate(grandparent)
        }
        mid!!.colour = false
        mid.left!!.colour = true
        mid.right!!.colour = true
    }

    private fun uncle(node: Node?): Node? {
        // If no parent or grandparent, then no uncle
        if (node!!.parent === NullNode || node!!.parent!!.parent === NullNode) return null
        return if (node!!.parent === node!!.parent!!.parent!!.left) // uncle on right
            node!!.parent!!.parent!!.right else  // uncle on left
            node!!.parent!!.parent!!.left
    }

    fun delete(data: Int) {
        delete(root, data)
    }

    private fun delete(nd: Node?, key: Int) {
        var node = nd
        var z: Node? = NullNode
        val x: Node?
        var y: Node?
        while (node !== NullNode) {
            if (node!!.data == key) {
                z = node
                break
            } else if (node.data <= key) {
                node = node.right
            } else {
                node = node.left
            }
        }
        if (z === NullNode) {
            println("Couldn't find key in the tree")
            return
        }
        y = z
        var yColour = y!!.colour
        if (z!!.left === NullNode) {
            x = z!!.right
            joinParentChild(z, z.right)
        } else if (z!!.right === NullNode) {
            x = z!!.left
            joinParentChild(z, z.left)
        } else {
            y = minimum(z!!.right)
            yColour = y!!.colour
            z.data = y.data
            joinParentChild(y, y.right)
            x = y.right
        }
        if (yColour == false) {
            if (x!!.colour == true) {
                x.colour = false
                return
            } else {
                fixDoubleBlack(x)
            }
        }
    }

    private fun fixDoubleBlack(x: Node?) {
        if (x === root) // Root node.
            return
        val sib = sibling(x)
        val parent = x!!.parent
        if (sib === NullNode) {
            // No sibling double black shifted to parent.
            fixDoubleBlack(parent)
        } else {
            if (sib!!.colour == true) {
                // Sibling colour is red.
                parent!!.colour = true
                sib.colour = false
                if (sib.parent!!.left === sib) {
                    // Sibling is left child.
                    rightRotate(parent)
                } else {
                    // Sibling is right child.
                    leftRotate(parent)
                }
                fixDoubleBlack(x)
            } else {
                // Sibling colour is black
                // At least one child is red.
                if (sib.left!!.colour == true || sib.right!!.colour == true) {
                    if (sib.parent!!.left === sib) {
                        // Sibling is left child.
                        if (sib.left !== NullNode && sib.left!!.colour == true) {
                            // left left case.
                            sib.left!!.colour = sib.colour
                            sib.colour = parent!!.colour
                            rightRotate(parent)
                        } else {
                            // left right case.
                            sib.right!!.colour = parent!!.colour
                            leftRotate(sib)
                            rightRotate(parent)
                        }
                    } else {
                        // Sibling is right child.
                        if (sib.left !== NullNode && sib.left!!.colour == true) {
                            // right left case.
                            sib.left!!.colour = parent!!.colour
                            rightRotate(sib)
                            leftRotate(parent)
                        } else {
                            // right right case.
                            sib.right!!.colour = sib.colour
                            sib.colour = parent!!.colour
                            leftRotate(parent)
                        }
                    }
                    parent.colour = false
                } else {
                    // Both children black.
                    sib.colour = true
                    if (parent!!.colour == false) fixDoubleBlack(parent) else parent.colour = false
                }
            }
        }
    }

    private fun sibling(node: Node?): Node? {
        // sibling null if no parent
        if (node!!.parent === NullNode) return null
        if (node!!.parent!!.left === node) 
            return node!!.parent!!.right 
        else 
            return node!!.parent!!.left
    }

    private fun joinParentChild(u: Node?, v: Node?) {
        if (u!!.parent === NullNode) {
            root = v
        } else if (u === u!!.parent!!.left) {
            u!!.parent!!.left = v
        } else {
            u!!.parent!!.right = v
        }
        v!!.parent = u!!.parent
    }

    private fun minimum(nd: Node?): Node? {
        var node = nd
        while (node!!.left !== NullNode) {
            node = node!!.left
        }
        return node
    }
}

fun main() {
    val tree = RBTree()
    tree.insert(1)
    tree.insert(2)
    tree.insert(3)
    tree.insert(4)
    tree.insert(5)
    tree.insert(7)
    tree.insert(6)
    tree.insert(8)
    tree.insert(9)
    tree.printTree()
    tree.delete(4)
    tree.printTree()
    tree.delete(7)
    tree.printTree()
}

/*
R:4(false)
   L:2(true)
   |  L:1(false)
   |  R:3(false)
   R:6(true)
      L:5(false)
      R:8(false)
         L:7(true)
         R:9(true)

R:5(false)
   L:2(true)
   |  L:1(false)
   |  R:3(false)
   R:7(true)
      L:6(false)
      R:8(false)
         R:9(true)

R:5(false)
   L:2(true)
   |  L:1(false)
   |  R:3(false)
   R:8(true)
      L:6(false)
      R:9(false)

*/