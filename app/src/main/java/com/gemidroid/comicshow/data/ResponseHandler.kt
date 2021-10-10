package com.gemidroid.comicshow.data

import AuthenticationState
import com.gemidroid.comicshow.data.models.Comic
import com.gemidroid.comicshow.data.models.NetworkError
import kotlinx.coroutines.flow.*
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.net.UnknownHostException

private fun handleErrorState(error: Throwable): NetworkError {
    return when (error) {
        is HttpException -> {
            val errorMsg = when (error.code()) {
                400, 401, 404 -> error.message()
                else -> jsonFormatter(error.response()?.errorBody()!!.charStream().readText())
            }
            NetworkError.ServerError(errorMsg ?: "General Error!!")
        }
        is IllegalStateException -> NetworkError.ParsingError(error = error.localizedMessage?:"Parsing issue!")
        is UnknownHostException -> NetworkError.Internet
        is IllegalArgumentException-> NetworkError.GeneralError(error = error.localizedMessage?:"General Error!!")
        else -> NetworkError.GeneralError("General Error!!")
    }
}

fun jsonFormatter(response: String): String {
    return try {
        JSONObject(response).getJSONArray("errors").getJSONObject(0).getString("msg")
    } catch (e: Exception) {
        try {
            JSONObject(response).getString("message")
        } catch (e: JSONException) {
           "General error"
        }
    }
}

suspend fun handleResponse(
    block: Flow<Any>,
    emitter: MutableStateFlow<AuthenticationState>
) {
    block.onStart { emitter.emit(AuthenticationState.Loading(true)) }
        .onCompletion { emitter.emit(AuthenticationState.Loading(false)) }
        .catch { exception -> emitter.emit(AuthenticationState.Error(handleErrorState(exception))) }
        .collect { emitter.emit(AuthenticationState.Success(it)) }
}

