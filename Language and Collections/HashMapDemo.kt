/*
 * A map is an object that stores associations between keys and values, or key/value pairs.
 Both keys and values are objects. The keys must be unique, but
the values may be duplicated.
 */
import java.util.HashMap

object HashMapDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        // Create a hash map.
        val hm = HashMap<String, Int>()
        // Put elements into the map
        hm.put("Mason", 55)
        hm.put("Jacob", 77)
        hm.put("William", 99)
        hm.put("Emma", 65)
        println("Students count :: " + hm.size)
        for (key in hm.keys) {
            println(key + " score marks :" + hm.get(key))
        }
        println("Emma score available ::" + hm.containsKey("Emma"))
        println("John score available :: " + hm.containsKey("John"))
    }
}
