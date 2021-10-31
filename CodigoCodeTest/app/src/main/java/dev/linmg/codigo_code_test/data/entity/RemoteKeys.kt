package dev.linmg.codigo_code_test.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys (
    @PrimaryKey
    val movieId:String,
    val type:String,
    val prevKey:Int?,
    val nextKey:Int?
)