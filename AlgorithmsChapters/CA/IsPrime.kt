fun isPrime(n: Int): Boolean {
    var answer = if (n > 1) true else false
    var i = 2
    while (i * i <= n) {
        if (n % i == 0) {
            answer = false
            break
        }
        ++i
    }
    return answer
}

fun main() {
    println(isPrime(8))
    println(isPrime(11))
}