package dev.linmg.codigo_code_test.data.response

import com.google.gson.annotations.SerializedName

data class BelongsToCollectionResponse (

    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String,
    @SerializedName("poster_path") var posterPath : String,
    @SerializedName("backdrop_path") var backdropPath : String

)