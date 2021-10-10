package com.gemidroid.comicshow.data.models

sealed class NetworkError {
    object Internet : NetworkError()
    class ServerError(val error: String) : NetworkError()
    class ParsingError(val error: String) : NetworkError()
    class GeneralError(val error: String) : NetworkError()
}
