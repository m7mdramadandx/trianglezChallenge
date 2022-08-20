package com.ramadan.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramadan.home.domain.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
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

    init {
        getPopularMovies()
    }


    fun getPopularMovies() {
        viewModelScope.launchCatching(
            block = {
                val resonse = homeUseCase.getPopularMovies()
                println(resonse)

            },
            onError = {
                println(it.message)
            })
    }


}