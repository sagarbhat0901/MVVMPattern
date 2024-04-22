import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmpattern.MyApiClient
import com.example.mvvmpattern.UserData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.json.JSONObject

class UserRepo {

    fun userLogin(userData: UserData): LiveData<String> {
        val loginResponse = MutableLiveData<String>()

        MyApiClient.MyGetResponse().ApiClientUserLogin(userData)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()?.string()
                        val jsonObject = JSONObject(responseBody)
                        val token = jsonObject.getString("token")
                        loginResponse.value = token
                        Log.i("mytag", "Response Success: Token = $token")
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Log.i("mytag", "Response failure: $errorBody")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("mytag", "Failed to make network call: ${t.message}")
                }
            })

        return loginResponse
    }
}
