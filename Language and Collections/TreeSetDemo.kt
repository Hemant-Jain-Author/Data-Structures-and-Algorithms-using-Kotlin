import java.util.TreeSet

fun main() {
    // Create a tree set.
    val ts = TreeSet<String>()
    // Add elements to the tree set.
    ts.add("Banana")
    ts.add("Apple")
    ts.add("Mango")

    println(ts)
    println("Apple present : " + ts.contains("Apple"))
    println("Grapes present : " + ts.contains("Grapes"))
    
    ts.remove("Apple")
    println("Apple present : " + ts.contains("Apple"))
}

/*
[Apple, Banana, Mango]
Apple present : true
Grapes present : false
Apple present : false
*/