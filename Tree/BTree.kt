class BTree(var max: Int ) { // Maximum degree
    var root : Node? = null // Pointer to root node
    var min : Int // Minimum degree

    init {
        min = max / 2 // Min number of children.
    }

    inner class Node(leaf: Boolean) {
        var n = 0 // Current number of keys
        var keys : IntArray // An array of keys
        var arr : Array<Node?> // An array of child pointers
        var leaf : Boolean // Is true when node is leaf. Otherwise false

        // Constructor
        init {
            keys = IntArray(max)
            arr = arrayOfNulls(max+1)
            this.leaf = leaf
        }
    }

    fun printTree() {
        printTree(root, "")
        println()
    }

    private fun printTree(node: Node?, indent: String) {
        if (node == null) return
        var i: Int = 0
        while (i < node.n) {
            printTree(node.arr[i], "$indent    ")
            println(indent + "key[" + i + "]:" + node.keys[i])
            i++
        }
        printTree(node.arr[i], "$indent    ")
    }

    fun printInOrder(node: Node?) {
        var i: Int = 0
        while (i < node!!.n) {
            if (node.leaf == false) printInOrder(node.arr[i])
            print(node.keys[i].toString() + " ")
            i++
        }
        if (node.leaf == false) {
            printInOrder(node.arr[i])
        }
    }

    fun search(key: Int): Node? {
        return if (root == null) null else search(root, key)
    }

    fun search(node: Node?, key: Int): Node? {
        var i = 0
        while (i < node!!.n && node.keys[i] < key) i++

        // If the found key is equal to key, return this node
        if (node.keys[i] == key) return node

        // If the key is not found and this is a leaf node
        return if (node.leaf == true) null else search(node.arr[i], key)
    }

    fun insert(key: Int) {
        // If tree is empty
        if (root == null) {
            // Allocate memory for root
            root = Node(true)
            root!!.keys[0] = key // Insert key
            root!!.n = 1 // Update number of keys in root
            return
        }
        if (root!!.leaf == true) {
            // Finds the location where new key can be inserted.
            // By moving all keys greater than key to one place forward.
            var i = root!!.n-1
            while (i >= 0 && root!!.keys[i] > key) {
                root!!.keys[i+1] = root!!.keys[i]
                i--
            }

            // Insert the new key at found location
            root!!.keys[i+1] = key
            root!!.n = root!!.n+1
        } else {
            var i = 0
            while (i < root!!.n && root!!.keys[i] < key) i++
            insert(root, root!!.arr[i], i, key)
        }
        if (root!!.n == max) {
            // If root contains more then allowed nodes, then tree grows in height.
            // Allocate memory for new root
            val rt: Node = Node(false)
            rt.arr[0] = root
            split(rt, root, 0) // divide the child into two and then add the median to the parent.
            root = rt
        }
    }

    // Insert a new key in this node
    // Arguments are parent, child, index of child and key.
    fun insert(parent: Node?, child: Node?, index: Int, key: Int) {
        if (child!!.leaf == true) {
            // Finds the location where new key will be inserted 
            // by moving all keys greater than key to one place forward.
            var i = child.n-1
            while (i >= 0 && child.keys[i] > key) {
                child.keys[i+1] = child.keys[i]
                i--
            }

            // Insert the new key at found location
            child.keys[i+1] = key
            child.n += 1
        } else {
            var i = 0
            // insert the node to the proper child.
            while (i < child.n && child.keys[i] < key) i++
            insert(child, child.arr[i], i, key) // parent, child and index of child.
        }
        if (child.n == max) // More nodes than allowed.
        {
            // divide the child into two and then add the median to the parent.
            split(parent, child, index)
        }
    }

    fun split(parent: Node?, child: Node?, index: Int) {
        // Getting index of median.
        val median = max / 2
        // Reduce the number of keys in child
        child!!.n = median
        val node: Node = Node(child.leaf)
        // Copy the second half keys of child to node
        var j = 0
        while (median+1 + j < max) {
            node.keys[j] = child.keys[median+1 + j]
            j++
        }
        node.n = j

        // Copy the second half children of child to node
        j = 0
        while (child.leaf == false && median + j <= max-1) {
            node.arr[j] = child.arr[median+1 + j]
            j++
        }

        // parent is going to have a new child,
        // create space of new child
        j = parent!!.n
        while (j >= index+1) {
            parent.arr[j+1] = parent.arr[j]
            j--
        }

        // Link the new child to the parent node
        parent.arr[index+1] = node

        // A key of child will move to the parent node. 
        // Find the location of new key by moving
        // all greater keys one space forward.
        j = parent.n-1
        while (j >= index) {
            parent.keys[j+1] = parent.keys[j]
            j--
        }

        // Copy the middle key of child to the parent
        parent.keys[index] = child.keys[median]

        // Increment count of keys in this parent
        parent.n += 1
    }

    fun remove(key: Int) {
        remove(root, key)
        if (root!!.n == 0) {
            // Shrinking : If root is pointing to empty node.
            // If that node is a leaf node then root will become null.
            // Else root will point to first child of node.
            root = if (root!!.leaf) null else root!!.arr[0]
        }
    }

    fun remove(node: Node?, key: Int) {
        val index = findKey(node, key)
        if (node!!.leaf) {
            if (index < node.n && node.keys[index] == key) {
                removeFromLeaf(node, index) // Leaf node key found.
            } else {
                println("The key $key not found.")
                return
            }
        } else {
            if (index < node.n && node.keys[index] == key) {
                removeFromNonLeaf(node, index) // Internal node key found.
            } else {
                remove(node.arr[index], key)
            }

            // All the property violation in index subtree only.
            // which include remove from leaf case too.
            if (node.arr[index]!!.n < min) {
                fixBTree(node, index)
            }
        }
    }

    // Returns the index of first key which is greater than or equal to key.
    fun findKey(node: Node?, key: Int): Int {
        var index = 0
        while (index < node!!.n && node.keys[index] < key) index++
        return index
    }

    // Remove the index key from leaf node.
    fun removeFromLeaf(node: Node?, index: Int) {
        // Move all the keys after the index position one step left.
        for (i in index+1 until node!!.n) node.keys[i-1] = node.keys[i]

        // Reduce the key count.
        node.n--
    }

    // Remove the index key from a non-leaf node.
    fun removeFromNonLeaf(node: Node?, index: Int) {
        val key = node!!.keys[index]

        // If the child that precedes key has at least min keys,
        // Find the predecessor 'pred' of key in the subtree rooted at index.
        // Replace key by pred and recursively delete pred in arr[index]
        if (node.arr[index]!!.n > min) {
            val pred = getPred(node, index)
            node.keys[index] = pred
            remove(node.arr[index], pred)
        } else if (node.arr[index+1]!!.n > min) {
            val succ = getSucc(node, index)
            node.keys[index] = succ
            remove(node.arr[index+1], succ)
        } else {
            merge(node, index)
            remove(node.arr[index], key)
        }
    }

    // To get predecessor of keys[index]
    fun getPred(node: Node?, index: Int): Int {
        // Keep moving to the right most node of left subtree until we reach a leaf.
        var cur = node!!.arr[index]
        while (!cur!!.leaf) cur = cur.arr[cur.n]

        // Return the last key of the leaf
        return cur.keys[cur.n-1]
    }

    // To get successor of keys[index]
    fun getSucc(node: Node?, index: Int): Int {
        // Keep moving to the left most node of right subtree until we reach a leaf
        var cur = node!!.arr[index+1]
        while (!cur!!.leaf) cur = cur.arr[0]

        // Return the first key of the leaf
        return cur.keys[0]
    }

    // Make sure that the node have at least min number of keys
    fun fixBTree(node: Node?, index: Int) {
        // If the left sibling has more than min keys.
        if (index != 0 && node!!.arr[index-1]!!.n > min) {
            borrowFromLeft(node, index)
        } else if (index != node!!.n && node.arr[index+1]!!.n > min) {
            borrowFromRight(node, index)
        } else {
            if (index != node.n) merge(node, index) 
            else merge(node, index-1)
        }
    }

    // Move a key from parent to right and left to parent.
    fun borrowFromLeft(node: Node?, index: Int) {
        val child = node!!.arr[index]
        val sibling = node.arr[index-1]

        // Moving all key in child one step forward.
        for (i in child!!.n-1 downTo 0) child.keys[i+1] = child.keys[i]

        // Move all its child pointers one step forward.
        var i = child.n
        while (!child.leaf && i >= 0) {
            child.arr[i+1] = child.arr[i]
            i--
        }

        // Setting child's first key equal to of the current node.
        child.keys[0] = node.keys[index-1]

        // Moving sibling's last child as child's first child.
        if (!child.leaf) child.arr[0] = sibling!!.arr[sibling.n]

        // Moving the key from the sibling to the parent
        node.keys[index-1] = sibling!!.keys[sibling.n-1]

        // Increase child key count and decrease sibling key count.
        child.n += 1
        sibling.n -= 1
    }

    // Move a key from parent to left and right to parent.
    fun borrowFromRight(node: Node?, index: Int) {
        val child = node!!.arr[index]
        val sibling = node.arr[index+1]

        // node key is inserted as the last key in child.
        child!!.keys[child.n] = node.keys[index]

        // Sibling's first child is inserted as the last child of child.
        if (!child.leaf) child.arr[child.n+1] = sibling!!.arr[0]

        //First key from sibling is inserted into node.
        node.keys[index] = sibling!!.keys[0]

        // Moving all keys in sibling one step left
        for (i in 1 until sibling.n) sibling.keys[i-1] = sibling.keys[i]

        // Moving the child pointers one step behind
        var i = 1
        while (!sibling.leaf && i <= sibling.n) {
            sibling.arr[i-1] = sibling.arr[i]
            ++i
        }

        // Increase child key count and decrease sibling key count.
        child.n += 1
        sibling.n -= 1
    }

    // Merge node's children at index and index+1.
    fun merge(node: Node?, index: Int) {
        val left = node!!.arr[index]
        val right = node.arr[index+1]
        val start = left!!.n
        // Adding a key from node to the left child.
        left.keys[start] = node.keys[index]

        // Copying the keys from right to left.
        for (i in 0 until right!!.n) left.keys[start+1 + i] = right.keys[i]

        // Copying the child pointers from right to left.
        var i = 0
        while (!left.leaf && i <= right.n) {
            left.arr[start+1 + i] = right.arr[i]
            ++i
        }

        // Moving all keys after  index in the current node one step forward.
        for (j in index+1 until node.n) node.keys[j-1] = node.keys[j]

        // Moving the child pointers after (index+1) in the current node one step forward.
        for (j in index + 2..node.n) node.arr[j-1] = node.arr[j]

        // Updating the key count of child and the current node
        left.n += right.n+1
        node.n--
            }
}

fun main() {
    val t = BTree(3) // A B-Tree with max key 3
    t.insert(1)
    t.insert(2)
    t.insert(3)
    t.insert(4)
    t.insert(5)
    t.insert(6)
    t.insert(7)
    t.insert(8)
    t.insert(9)
    t.insert(10)
    t.printTree()
    println("6 : " + if (t.search(6) != null) "Present" else "Not Present")
    println("11 : " + if (t.search(11) != null) "Present" else "Not Present")
    t.remove(6)
    t.remove(3)
    t.remove(7)
    t.printTree()
}

/* 
        key[0]:1
    key[0]:2
        key[0]:3
key[0]:4
        key[0]:5
    key[0]:6
        key[0]:7
    key[1]:8
        key[0]:9
        key[1]:10

6 : Present
11 : Not Present
    key[0]:1
    key[1]:2
key[0]:4
    key[0]:5
key[1]:8
    key[0]:9
    key[1]:10

*/