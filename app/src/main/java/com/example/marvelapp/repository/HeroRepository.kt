package com.example.marvelapp.repository

import com.example.marvelapp.R
import com.example.marvelapp.model.Hero

class HeroRepository {
    fun getAllHeroes(): List<Hero> {
        return listOf(
            Hero(
                id = 0,
                name = "Deadpool",
                description = "Deadpool description",
                imageId = R.drawable.deadpool_image
            ),
            Hero(
                id = 1,
                name = "Iron Man",
                description = "Iron Man description",
                imageId = R.drawable.iron_man_image
            ),
            Hero(
                id = 2,
                name = "Doctor Strange",
                description = "Doctor Strange description",
                imageId = R.drawable.doctor_strange_image
            ),
            Hero(
                id = 3,
                name = "Captain America",
                description = "Captain America description",
                imageId = R.drawable.captain_america_image
            ),
            Hero(
                id = 4,
                name = "Spider Man",
                description = "Spider Man description",
                imageId = R.drawable.spider_man_image
            ),
            Hero(
                id = 5,
                name = "Thor",
                description = "Thor description",
                imageId = R.drawable.thor_image
            ),
            Hero(
                id = 6,
                name = "Thanos",
                description = "Thanos description",
                imageId = R.drawable.thanos_image
            )
        )
    }
}