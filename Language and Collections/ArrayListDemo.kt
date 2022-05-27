import java.util.ArrayList

object ArrayListDemo {
    @JvmStatic
    fun main() {
        val al = ArrayList<Int>()

        al.add(1) // add 1 to the end of the list
        al.add(2) // add 2 to the end of the list
        println("Contents of Array : $al")
        println("Array Size : " + al.size)
        println("Array IsEmpty : " + al.isEmpty())
        al.removeAt(al.size - 1) // last element of array is removed.
        al.removeAll(al) // all the elements of array are removed.
        println("Array IsEmpty : " + al.isEmpty())
    }
}
