package dev.linmg.codigo_code_test.utils

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.reflect.TypeVariable

class Ext {

    companion object {
        fun getImageUrl(path : String) = BASE_IMAGE_URL.plus(path)

        suspend fun <T> getResponse(errorMessage : String , request : suspend () -> Response<T>) : StatefulData<T> {
            return try {
                with(request.invoke()) {
                    if (isSuccessful) {
                        StatefulData.success(body())
                    } else {
                        StatefulData.error(errorMessage)
                    }
                }
            } catch (error : Throwable) {
                StatefulData.error("A Network Error Occurred")
            }
        }


    }


}