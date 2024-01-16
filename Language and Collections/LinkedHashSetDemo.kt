fun main() {
    // Create a hash set.
    val hs = HashSet<String>()
    // Add elements to the hash set.
    hs.add("Banana")
    hs.add("Apple")
    hs.add("Mango")

    println("HashSet : $hs")

    // Create a linked hash set.
    val lhs = LinkedHashSet<String>()
    // Add elements to the linked hash set.
    lhs.add("Banana")
    lhs.add("Apple")
    lhs.add("Mango")
    println("LinkedHashSet : $lhs")
}

/*
HashSet : [Apple, Mango, Banana]
LinkedHashSet : [Banana, Apple, Mango]
*/