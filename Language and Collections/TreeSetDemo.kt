import java.util.TreeSet

object TreeSetDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        // Create a tree set.
        val ts = TreeSet<String>()
        // Add elements to the tree set.
        ts.add("India")
        ts.add("USA")
        ts.add("Brazil")
        println(ts)
    }
}
