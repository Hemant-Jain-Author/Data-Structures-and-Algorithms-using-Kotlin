
fun main() {
    val al = ArrayList<Int>()
    al.add(1) // add 1 to the end of the list
    al.add(2) // add 2 to the end of the list

    println("Array : $al")
    println("Array Size : " + al.size)
    println("Array IsEmpty : " + al.isEmpty())

    al.removeLast() // last element of array is removed.
    println("Array : $al")

    al.removeAll(al) // all the elements of array are removed.
    println("Array IsEmpty : " + al.isEmpty())
}

/*
Array : [1, 2]
Array Size : 2
Array IsEmpty : false
Array : [1]
Array IsEmpty : true
*/
