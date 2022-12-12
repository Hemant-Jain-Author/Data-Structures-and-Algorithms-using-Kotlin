fun main() {
    // Create a hash set.
    val hs = HashSet<String>()
    
    // Add elements to the hash set.
    hs.add("Banana")
    hs.add("Apple")
    hs.add("Mango")
    println(hs)

    println("Apple present: " + hs.contains("Apple"))
    println("Grapes present: " + hs.contains("Grapes"))

    hs.remove("Apple")
    println("Apple present: " + hs.contains("Apple"))
}

/*
[Apple, Mango, Banana]
Apple present: true
Grapes present: false
Apple present: false
*/