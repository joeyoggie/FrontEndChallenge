package com.clap.android.features.currency


/*
       3 1 3 9 = 12
       (3 + 1) * 3 - 9 = 1 + 2
       4 * 3 - 9 = 1 + 2
       12 - 9 = 1 + 2
       3 = 3 ##
   */

fun anagramCheck(first: String, second: String): Boolean {
    //setup the 2 strings
    val firstString = first.toLowerCase().replace(" ", "")
    val secondString = first.toLowerCase().replace(" ", "")

    //if the 2 strings are not equal in length, then they must contain different number of letters, hence not anagrams
    if(firstString.length != secondString.length) return false

    for(char in firstString) {
        if(!secondString.contains(char)) {
            return false
        }
    }
    for(char in secondString) {
        if(!firstString.contains(char)) {
            return false
        }
    }

    return true
}

/**
 * generate first @param fibonacciCount Fibonacci numbers using iterative approach
 */
fun generateFibonacci(fibonacciCount: Int) {
    var first = 0
    var second = 1
    var temp: Int

    repeat(fibonacciCount) {
        println(second)
        temp = first
        first = second
        second += temp
    }
}

/**
 * generate first @param fibonacciCount Fibonacci numbers using recursive approach
 */
tailrec fun generateFibonacci(fibonacciCount: Int, first: Int = 0, second: Int = 1) {
    println(second)

    if(fibonacciCount == 0)
        return
    else
        generateFibonacci(fibonacciCount-1, second, first+second)
}