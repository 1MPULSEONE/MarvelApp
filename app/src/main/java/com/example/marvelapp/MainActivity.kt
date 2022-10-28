package com.example.marvelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marvelapp.model.Hero
import com.example.marvelapp.repository.HeroRepository
import com.example.marvelapp.ui.theme.MarvelAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelAppTheme {
                rememberSystemUiController().setStatusBarColor(Color.DarkGray)
                val navController = rememberNavController()
                val heroesList = HeroRepository().getAllHeroes()
                NavHost(
                    navController = navController, startDestination = "HeroesScreen"
                ) {
                    composable(route = "HeroesScreen") {
                        HeroesScreen(navController, heroesList)
                    }
                    composable(
                        route = "HeroScreen/{heroId}/{heroName}/{heroDescription}/{heroBackgroundImageId}",
                        arguments = listOf(navArgument("heroId") {
                            type = NavType.IntType
                        }, navArgument("heroName") {
                            type = NavType.StringType
                        }, navArgument("heroDescription") {
                            type = NavType.StringType
                        }, navArgument("heroBackgroundImageId") {
                            type = NavType.IntType
                        }
                        )) {
                        HeroScreen(
                            navController = navController,
                            heroId = it.arguments?.getInt("heroId"),
                            heroName = it.arguments?.getString("heroName"),
                            heroDescription = it.arguments?.getString("heroDescription"),
                            heroBackgroundImageId = it.arguments?.getInt("heroBackgroundImageId")
                        )
                    }
                }
            }
        }
    }

    @Composable
    @Suppress("Compose function")
    fun HeroesScreen(navController: NavController, placeHolderItems: List<Hero>) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = colorResource(id = R.color.grey_background)
        ) {}
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.marvel_logo),
                modifier = Modifier
                    .height(70.dp)
                    .width(150.dp),
                contentDescription = stringResource(id = R.string.marvel_logo_description)
            )

            Text(
                text = stringResource(id = R.string.choose_your_hero),
                fontSize = 32.sp,
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.Bold
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 48.dp, top = 18.dp),
                contentPadding = PaddingValues(12.dp)
            ) {
                items(placeHolderItems) { hero ->
                    HeroCard(hero = hero, navController)
                }
            }
        }
    }


    @Composable
    fun HeroScreen(
        navController: NavController,
        heroId: Int?,
        heroName: String?,
        heroDescription: String?,
        heroBackgroundImageId: Int?,
    ) {
        val hero = Hero(heroId!!, heroName!!, heroDescription!!, heroBackgroundImageId!!)
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = hero.imageId),
                    modifier = Modifier
                        .fillMaxSize()
                        .width(345.dp)
                        .height(400.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(id = R.string.background_hero_image)
                )
                Image(
                    modifier = Modifier
                        .clickable { navController.navigateUp() }
                        .size(60.dp)
                        .padding(top = 12.dp),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.arrow_left_icon),
                    contentDescription = "arrow icon",
                )

                Text(
                    text = hero.name,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 90.dp, start = 12.dp),
                    fontSize = 38.sp,
                    color = colorResource(id = R.color.white),
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = hero.description,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(top = 10.dp, bottom = 40.dp, start = 12.dp),
                    fontSize = 24.sp,
                    color = colorResource(id = R.color.white),
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }

    @Composable
    @Suppress("Compose function")
    fun HeroCard(hero: Hero, navController: NavController) {
        Column(modifier = Modifier
            .fillMaxSize()
            .clickable {
                navController.navigate(
                    "HeroScreen/${hero.id}/${hero.name}/${hero.description}/${hero.imageId}"
                )
            }
            .padding(all = 8.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(18.dp))
            ) {
                Image(
                    painter = painterResource(id = hero.imageId),
                    modifier = Modifier
                        .fillMaxSize()
                        .width(345.dp)
                        .height(400.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(id = R.string.background_hero_image)
                )
                Text(
                    text = hero.name,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 40.dp, start = 12.dp),
                    fontSize = 38.sp,
                    color = colorResource(id = R.color.white),
                    fontWeight = FontWeight.Bold,

                    )
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    @Suppress("Compose function")
    fun PreviewHeroesScreen() {
        MarvelAppTheme {
            val navController = rememberNavController()
            HeroesScreen(navController, HeroRepository().getAllHeroes())
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewHeroScreen() {
        MarvelAppTheme {
            val navController = rememberNavController()
            HeroScreen(
                navController,
                0,
                "Iron Man",
                "I AM IRON MAN",
                R.drawable.iron_man_image
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    @Suppress("Compose function")
    fun ShowHeroCard() {
        MarvelAppTheme {
            val navController = rememberNavController()
            HeroCard(
                Hero(
                    0,
                    "Captain America",
                    "Sample description",
                    R.drawable.captain_america_image_test
                ), navController
            )
        }
    }
}
