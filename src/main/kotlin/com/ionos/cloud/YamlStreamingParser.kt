package com.ionos.cloud

import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.stream.Stream

/** Non-recursive streaming implementation of a simple YAML parser */
class YamlStreamingParser : DataParser<Stream<String>, Stream<YamlElement>> {
    private val _separator = "."
    override fun readData(stream: Stream<String>, vararg filters: String): Stream<YamlElement> {
        val filterSet = filters.toSet()
        val i = AtomicInteger(0)
        // Deque for parent path tracking
        val breadCrumb = LinkedList<YamlElement>()
        val elements = stream.map { i.incrementAndGet() to it }
                .filter { it.second.trim().isNotBlank() }
                .map {
                    val yamlLine = YamlElement.of(it.first, it.second)
                    if (yamlLine.level == 0) {
                        breadCrumb.clear()
                        breadCrumb.add(yamlLine)
                    } else if (yamlLine.level == breadCrumb.last().level) {
                        breadCrumb.removeLast()
                        breadCrumb.add(yamlLine)
                    } else if (yamlLine.level > breadCrumb.last().level) {
                        breadCrumb.add(yamlLine)
                    } else if (yamlLine.level < breadCrumb.last().level) {
                        val diff = breadCrumb.last().level - yamlLine.level
                        repeat(diff + 1) {
                            breadCrumb.removeLast()
                        }
                        breadCrumb.add(yamlLine)
                    }
                    yamlLine.copy(
                            path = _separator.plus(breadCrumb.joinToString(_separator) { parent -> parent.path })
                    )
                }
        return when (filterSet.isEmpty()) {
            true -> elements
            else -> elements.filter {
                filterSet.contains(it.path)
            }
        }
    }

}