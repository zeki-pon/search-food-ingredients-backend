package com.sfi.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SearchFoodIngredientsApplication

fun main(args: Array<String>) {
	runApplication<SearchFoodIngredientsApplication>(*args)
}
