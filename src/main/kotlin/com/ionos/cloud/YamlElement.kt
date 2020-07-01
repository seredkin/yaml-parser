package com.ionos.cloud

data class YamlElement(
        val line: Int,
        val level: Int,
        val path: String,
        val value: Any? = null
) {
    companion object {
        private const val COLON = ":"
        private const val INDENT_SIZE = 2
        fun of(line: Int, str: String): YamlElement {
            return YamlElement(line, countIndent(str), str.substringBefore(COLON).trim(), str.substringAfter(COLON).trim())
        }

        private fun countIndent(str: String): Int = (str.length - str.trimStart().length) / INDENT_SIZE
    }
}