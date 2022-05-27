object ArrayDemo {
    @JvmStatic
    fun main() {
        val arr = IntArray(10)
        for (i in 0..9) {
            arr[i] = i
        }
        println(arr)
    }
}