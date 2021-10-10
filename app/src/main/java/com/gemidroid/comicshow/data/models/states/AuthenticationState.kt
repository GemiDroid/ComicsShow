import com.gemidroid.comicshow.data.models.Comic
import com.gemidroid.comicshow.data.models.NetworkError

sealed class AuthenticationState {
    class Success(var response: Any) : AuthenticationState()
    class Error(val error: NetworkError) : AuthenticationState()
    class Loading(var isLoading: Boolean) : AuthenticationState()
    object Start : AuthenticationState()
}