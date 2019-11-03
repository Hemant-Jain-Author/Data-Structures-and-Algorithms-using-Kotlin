object ForDemo {

    private val text = "Hello, World!"
    internal val PI = 3.141592653589793

    fun main1() {
        val numbers = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        var sum = 0
        for (n in numbers) {
            sum += n
        }

        println("Sum is :: $sum")
    }

    fun main2() {
        val numbers = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        var sum = 0
        for (i in numbers.indices) {
            sum += numbers[i]
        }

        println("Sum is :: $sum")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val numbers = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        var sum = 0
        var i = 0
        while (i < numbers.size) {
            sum += numbers[i]
            i++
        }
        println("Sum is :: $sum")
    }
}