package com.ramadan.home.presentation.movieDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.ramadan.home.BuildConfig
import com.ramadan.home.presentation.components.MyCircularProgressIndicator
import com.ramadan.home.theme.TrianglzShape
import com.ramadan.home.theme.TrianglzTheme
import com.ramadan.netwrok.data.model.MovieApiModel

@Composable
fun MovieDetailsScreen(
    movieApiModel: MovieApiModel,
    navController: NavController
) {

    Column() {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            SubcomposeAsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = BuildConfig.BASE_URL_IMG.plus("w500").plus(movieApiModel.backdrop_path),
                contentScale = ContentScale.FillWidth,
                loading = {
                    MyCircularProgressIndicator(Modifier.fillMaxSize())
                },
                onError = {

                },
                contentDescription = ""
            )


            Icon(
                painterResource(id = com.ramadan.home.R.drawable.ic_back),
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopStart)
                    .clip(TrianglzShape.myShapes.medium)
                    .background(TrianglzTheme.colors.cardBg.copy(alpha = 0.6f))
                    .padding(4.dp)
                    .clickable {
                        navController.navigateUp()
                    },
                contentDescription = ""
            )

        }


        movieApiModel.title?.let {
            Text(
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                text = it,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TrianglzTheme.colors.button
            )
        }

        Text(
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
            text = movieApiModel.overview.toString(),
            fontSize = 14.sp,
        )

        Row(
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp)
        ) {
            movieApiModel.vote_average?.let {
                DetailsItem(com.ramadan.home.R.drawable.ic_rate, it.toString())
            }

            movieApiModel.original_language?.let {
                DetailsItem(com.ramadan.home.R.drawable.ic_language, it)
            }

            if (movieApiModel.adult == true) {
                DetailsItem(com.ramadan.home.R.drawable.ic_plus_18)
            }
        }



        movieApiModel.release_date?.let {
            Text(
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                text = it,
                fontSize = 14.sp,
            )
        }


    }

}

@Composable
private fun DetailsItem(drawableId: Int, attribute: String? = null) {
    Row(
        modifier = Modifier
            .padding(end = 16.dp)
            .clip(TrianglzShape.myShapes.medium)
            .background(TrianglzTheme.colors.cardBg.copy(alpha = 0.8f))
            .padding(4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painterResource(id = drawableId),
            modifier = Modifier.size(20.dp),
            contentDescription = ""
        )

        attribute?.let {
            Text(
                modifier = Modifier.padding(horizontal = 2.dp),
                text = it,
                fontSize = 12.sp
            )
        }
    }
}
