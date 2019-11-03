import java.util.HashSet
import java.util.LinkedHashSet

object LinkedHashSetDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        // Create a hash set.
        val hs = HashSet<String>()
        // Add elements to the hash set.
        hs.add("India")
        hs.add("USA")
        hs.add("Brazil")
        println("HashSet value:: $hs")

        // Create a linked hash set.
        val lhs = LinkedHashSet<String>()
        // Add elements to the linked hash set.
        lhs.add("India")
        lhs.add("USA")
        lhs.add("Brazil")
        println("LinkedHashSet value:: $lhs")
    }
}