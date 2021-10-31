package dev.linmg.codigo_code_test.data.response

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesResponse (

    @SerializedName("english_name") var englishName : String,
    @SerializedName("iso_639_1") var iso6391 : String,
    @SerializedName("name") var name : String

)