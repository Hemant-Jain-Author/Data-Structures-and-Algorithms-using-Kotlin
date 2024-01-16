import java.util.TreeMap

fun main() {
    // create a tree map.
    val tm = TreeMap<String, Int>()
    // Put elements into the map
    tm.put("Apple", 40)
    tm.put("Banana", 10)
    tm.put("Mango", 20)
    println("Size :: " + tm.size)
    
    for (key in tm.keys) {
        println(key + " cost : " + tm.get(key))
    }
    println("Apple present : " + tm.containsKey("Apple"))
    println("Grapes present : " + tm.containsKey("Grapes"))

    tm.remove("Apple")
    println("Apple present : " + tm.containsKey("Apple"))
}

/*
Size :: 3
Apple cost : 40
Banana cost : 10
Mango cost : 20
Apple present : true
Grapes present : false
Apple present : false
*/