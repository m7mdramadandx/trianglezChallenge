package com.ramadan.home.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramadan.home.domain.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
//    private val savedStateHandle: SavedStateHandle,
    private val homeUseCase: HomeUseCase,
) : ViewModel() {

    inline fun CoroutineScope.launchCatching(
        noinline block: suspend CoroutineScope.() -> Unit,
        crossinline onError: (Throwable) -> Unit,
    ) {
        launch(
            CoroutineExceptionHandler { _, throwable -> onError(throwable) },
            block = block
        )
    }

    private val _screenState = mutableStateOf(ScreenState())
    val screenState: State<ScreenState> = _screenState

    init {
        getPopularMovies()
    }


    fun getPopularMovies() {
        _screenState.value = ScreenState()

        viewModelScope.launchCatching(
            block = {
                delay(2000L)
                val response = homeUseCase.getPopularMovies()
                _screenState.value = screenState.value.copy(
                    isLoading = false,
                    items = response.movieApiModels,
                )
            },
            onError = {
                _screenState.value = screenState.value.copy(
                    isLoading = false,
                    error = it.localizedMessage ?: "something went wrong"
                )
            })
    }

    fun getLocalMovies() {
        _screenState.value = ScreenState()

        viewModelScope.launchCatching(
            block = {
                val response = homeUseCase.getLocalPopularMovies()
                _screenState.value = screenState.value.copy(
                    isLoading = false,
                    items = response,
                )
            },
            onError = {
                _screenState.value = screenState.value.copy(
                    isLoading = false,
                    error = it.localizedMessage ?: "something went wrong"
                )
            })
    }

    fun sortList(popularity: Boolean) {
        if (popularity) {
            _screenState.value = screenState.value.copy(
                items = screenState.value.items?.sortedByDescending { it.popularity },
            )
        } else {

            _screenState.value = screenState.value.copy(
                items = screenState.value.items?.sortedByDescending { it.vote_average },
            )
        }
    }
}