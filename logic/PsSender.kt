package com.whats2ps.logic

import java.util.*

object PsSender {
    private val queue: Queue<String> = LinkedList()
    fun queue(text: String) { queue.add(text) }
    fun dequeue(): String? = queue.poll()
}
