import java.util.HashSet

object HashSetDemo {
    @JvmStatic
    fun main() {
        // Create a hash set.
        val hs = HashSet<String>()
        // Add elements to the hash set.
        hs.add("India")
        hs.add("USA")
        hs.add("Brazil")
        println(hs)
        println("Hash Table contains USA : " + hs.contains("USA"))
        println("Hash Table contains UK : " + hs.contains("UK"))
        hs.remove("USA")
        println(hs)
        println("Hash Table contains USA : " + hs.contains("USA"))
    }
}