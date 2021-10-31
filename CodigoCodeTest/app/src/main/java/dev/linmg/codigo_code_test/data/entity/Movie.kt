package dev.linmg.codigo_code_test.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    var id : Int = 0,
    var title : String = "",
    var coverImageUrl : String = "",
    var rating : Double = 0.0,
    var releaseDate : String = "",
    var genres : String = "",
    var description : String = "",
    var isFavourite : Boolean = false
) {
    fun toMovieInfoEntity(): MovieDetailEntity {
        return MovieDetailEntity(
            id = id,
            title = title,
            coverImageUrl = coverImageUrl,
            rating = rating,
            releaseDate = releaseDate,
            genres = genres,
            description = description,
        )
    }
}
