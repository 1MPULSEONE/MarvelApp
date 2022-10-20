package com.example.marvelapp.repository

import com.example.marvelapp.R
import com.example.marvelapp.model.Hero

class HeroRepository {
    fun getAllHeroes(): List<Hero> {
        return listOf(
            Hero(
                id = 0,
                name = "Deadpool",
                imageId = R.drawable.deadpool_image
            ),
            Hero(
                id = 1,
                name = "Iron Man",
                imageId = R.drawable.iron_man_image
            ),
            Hero(
                id = 2,
                name = "Doctor Strange",
                imageId = R.drawable.doctor_strange_image
            ),
            Hero(
                id = 3,
                name = "Captain America",
                imageId = R.drawable.captain_america_image
            ),
            Hero(
                id = 4,
                name = "Spider Man",
                imageId = R.drawable.spider_man_image
            ),
            Hero(
                id = 5,
                name = "Thor",
                imageId = R.drawable.thor_image
            ),
            Hero(
                id = 6,
                name = "Thanos",
                imageId = R.drawable.thanos_image
            )
        )
    }
}