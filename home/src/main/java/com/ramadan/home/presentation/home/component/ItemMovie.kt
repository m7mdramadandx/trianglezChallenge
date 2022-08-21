package com.ramadan.home.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.ramadan.home.BuildConfig
import com.ramadan.home.presentation.components.MyCircularProgressIndicator
import com.ramadan.home.theme.TrianglzShape
import com.ramadan.home.theme.TrianglzTheme
import com.ramadan.netwrok.data.model.MovieApiModel

@Composable
fun ItemMovie(
    modifier: Modifier = Modifier,
    movieApiModel: MovieApiModel,
    onItemClick: () -> Unit
) {

    Card(
        modifier = modifier
            .padding(bottom = 20.dp, start = 8.dp, end = 8.dp)
            .clickable { onItemClick.invoke() },
        backgroundColor = TrianglzTheme.colors.cardBg,
        elevation = 12.dp
    ) {

        Column {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {

                SubcomposeAsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = BuildConfig.BASE_URL_IMG.plus("w342").plus(movieApiModel.poster_path),
                    contentScale = ContentScale.FillWidth,
                    loading = {
                        MyCircularProgressIndicator(modifier.fillMaxSize())
                    },
                    onError = {

                    },
                    contentDescription = ""
                )
                movieApiModel.vote_average?.let {
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.TopStart)
                            .clip(TrianglzShape.myShapes.medium)
                            .background(TrianglzTheme.colors.cardBg.copy(alpha = 0.6f))
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            painterResource(id = com.ramadan.home.R.drawable.ic_rate),
                            modifier = Modifier.size(16.dp),
                            contentDescription = ""
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 2.dp),
                            text = it.toString(),
                            fontSize = 10.sp,

                            )
                    }
                }
            }
        }
    }

}