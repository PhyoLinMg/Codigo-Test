package dev.linmg.codigo_code_test.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


/**
 * Popular movie entity
 *
 * @property movieId [Int]
 */
@Entity(tableName = "popular_movies")
data class PopularMovieEntity(
    @PrimaryKey(autoGenerate = false)
    var movieId : Int = 0,
) {

}

/**
 * Popular movie entity with movie item
 *
 *  * Used to bind popular movie's id to movie object

 *
 * @property entity [PopularMovieEntity]
 * @property movie [MovieDetail]
 */
data class PopularMovieEntityWithMovieItem(
    @Embedded
    var entity : PopularMovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "id"
    )
    var movie : Movie? = Movie()
) {

}