import java.util.ArrayDeque

object QueueDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val que = ArrayDeque<Int>()
        que.add(1)
        que.add(2)
        que.add(3)
        println("Queue : $que")
        println("Queue size : " + que.size)
        println("Queue peek : " + que.peek())
        println("Queue remove : " + que.remove())
        println("Queue isEmpty : " + que.isEmpty())
    }
}