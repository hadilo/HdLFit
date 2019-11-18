package com.hadilo.hdlfit.helper.network

import com.hadilo.hdlfit.model.pojo.error.APIError
import retrofit2.Response
import java.io.IOException


object ErrorUtils {

    fun parseError(response: Response<*>): APIError {
        val converter = Client().getData().responseBodyConverter<APIError>(APIError::class.java, arrayOfNulls<Annotation>(0))

//            .responseBodyConverter(APIError::class.java, arrayOfNulls<Annotation>(0))

        val apiError: APIError

        try {
            apiError = converter.convert(response.errorBody())!!
        } catch (e: IOException) {
            return APIError()
        }

        return apiError
    }
}