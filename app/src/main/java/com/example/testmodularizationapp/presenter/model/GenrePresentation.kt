package com.example.testmodularizationapp.presenter.model

import android.os.Parcelable
import com.example.testmodularizationapp.domain.model.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenrePresentation(
    val id: Int?,
    val name: String?,
    val movies: List<Movie?>
): Parcelable
