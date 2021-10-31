package dev.linmg.codigo_code_test.data.response

import com.google.gson.annotations.SerializedName
import dev.linmg.codigo_code_test.data.entity.Movie

import dev.linmg.codigo_code_test.utils.Ext

import java.io.Serializable

data class MovieResponse(
    @SerializedName("adult")
    var adult : Boolean,

    @SerializedName("backdrop_path")
    var backdropPath : String,

    @SerializedName("genre_ids")
    var genreIds : List<Int>,

    @SerializedName("id")
    var id : Int,

    @SerializedName("original_language")
    var originalLanguage : String,

    @SerializedName("original_title")
    var originalTitle : String,

    @SerializedName("overview")
    var overview : String,

    @SerializedName("popularity")
    var popularity : Double,

    @SerializedName("poster_path")
    var posterPath : String,

    @SerializedName("release_date")
    var releaseDate : String,

    @SerializedName("title")
    var title : String,

    @SerializedName("video")
    var video : Boolean,

    @SerializedName("vote_average")
    var voteAverage : Double,

    @SerializedName("vote_count")

    var voteCount : Int
) : Serializable{

    fun toMovie() : Movie = Movie(
        id = id,
        title = title,
        coverImageUrl = Ext.getImageUrl(posterPath),
        rating = voteAverage
    )

}