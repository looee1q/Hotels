package com.example.hotels.data.utils

object ParsingUtils {

    /**
     * Parses a string of numbers separated by colons into a list of integers.
     *
     * @param input A string consisting of numbers separated by colons (e.g., "1:2:3").
     * @return A list of integers parsed from the input string.
     * @throws IllegalArgumentException If the input contains invalid characters.
     */
    fun parseSuitesAvailabilityStringToList(input: String): List<Int> {
        if (input.all { it.isDigit() || it == ':' }) {
            return input.split(':').filter { it.isNotEmpty() }.map { it.toInt() }
        } else {
            throw IllegalArgumentException("Input string cannot be parsed into List<Int>. Expected format: digits separated by colons (e.g., \"1:2:3\").")
        }
    }
}