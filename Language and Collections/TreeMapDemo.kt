import java.util.TreeMap

fun main() {
    // create a tree map.
    val tm = TreeMap<String, Int>()
    // Put elements into the map
    tm.put("Mason", 55)
    tm.put("Jacob", 77)
    tm.put("William", 99)
    tm.put("Emma", 65)
    println("Students count :: " + tm.size)
    for (key in tm.keys) {
        println(key + " score marks :" + tm.get(key))
    }
    println("Emma score available ::" + tm.containsKey("Emma"))
    println("John score available :: " + tm.containsKey("John"))
    tm.remove("Emma")
    println("Emma score available ::" + tm.containsKey("Emma"))
}
