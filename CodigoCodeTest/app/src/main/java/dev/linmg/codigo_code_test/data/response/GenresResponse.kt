package dev.linmg.codigo_code_test.data.response

import com.google.gson.annotations.SerializedName

data class GenresResponse (
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String
)