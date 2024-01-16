
fun main() {
    val que = ArrayDeque<Int>()
    que.add(1)
    que.add(2)
    que.add(3)
    println("Queue : $que")

    println("Queue size : " + que.size)
    println("Queue peek : " + que.first())
    println("Queue remove : " + que.removeFirst())
    println("Queue isEmpty : " + que.isEmpty())
}

/*
Queue : [1, 2, 3]
Queue size : 3
Queue peek : 1
Queue remove : 1
Queue isEmpty : false
*/