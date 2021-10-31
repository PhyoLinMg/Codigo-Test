package dev.linmg.codigo_code_test.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieDetailEntity(
    @PrimaryKey(autoGenerate = false)
    var id : Int = 0,
    var title : String = "",
    var coverImageUrl : String = "",
    var rating : Double = 0.0,
    var releaseDate : String = "",
    var genres : String = "",
    var description : String = ""
) {

}