object ArrayDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val arr = IntArray(10)
        for (i in 0..9) {
            arr[i] = i
        }
        println(arr)
    }
}