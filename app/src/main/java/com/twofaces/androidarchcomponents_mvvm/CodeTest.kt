package com.twofaces.androidarchcomponents_mvvm

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main(args: Array<String>) = runBlocking{

    println("main start(): ${Thread.currentThread().name}")

    launch(Dispatchers.IO) {
        println("launch start: ${Thread.currentThread().name}")
        test()
        println("launch end: ${Thread.currentThread().name}")
    }

    println("main() end")

}

    suspend fun test(){
        println("test start(): ${Thread.currentThread().name}")
        delay(2000)
        println("test end(): ${Thread.currentThread().name}")
    }





