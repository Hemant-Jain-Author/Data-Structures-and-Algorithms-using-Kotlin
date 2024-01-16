fun main() {
    // Create a hash map.
    val hm = HashMap<String, Int>()

    // Put elements into the map
    hm.put("Apple", 40)
    hm.put("Banana", 10)
    hm.put("Mango", 20)

    println("Size : " + hm.size)
    for (key in hm.keys) {
        println(key + " cost : " + hm.get(key))
    }
    println("Apple present : " + hm.containsKey("Apple"))
    println("Grapes present : " + hm.containsKey("Grapes"))
}

/*
Size : 3
Apple cost : 40
Mango cost : 20
Banana cost : 10
Apple present : true
Grapes present : false
*/