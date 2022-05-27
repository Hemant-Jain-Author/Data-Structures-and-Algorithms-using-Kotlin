import java.util.HashMap

class CountMap<T> {
    internal var hm = HashMap<T, Int>()

    fun add(key: T) {
        hm.put(key, hm.getOrElse(key){0} + 1)
    }

    fun remove(key: T) {
        if (hm.containsKey(key)) {
            val value = hm.get(key)
            if (value == null) {
                return;
            } else if (value == 1) {
                hm.remove(key)
            } else {
                hm.put(key, value - 1)
            }
        }
    }

    operator fun get(key: T): Int {
        return hm.getOrElse(key){0}
    }

    fun containsKey(key: T): Boolean {
        return hm.containsKey(key)
    }

    fun size(): Int {
        return hm.size
    }
}

fun main() {
    val cm = CountMap<Int>()
    cm.add(2)
    cm.add(2)
    println("count is : " + cm[2])
    cm.remove(2)
    println("count is : " + cm[2])
    cm.remove(2)
    println("count is : " + cm[2])
}

