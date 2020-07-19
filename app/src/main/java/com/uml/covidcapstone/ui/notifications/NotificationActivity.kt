package com.gettweets
import android.app.Activity
import android.app.ListActivity
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.AsyncTask
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.covidcapstone.TwitterAPI
import com.google.gson.Gson
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpRequestBase
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.params.BasicHttpParams
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.net.URLEncoder
import java.util.ArrayList
/**
 * Demonstrates how to use a twitter application keys to access a user's timeline
 */
class MainActivity:Activity() {
    internal lateinit var lv_list:ListView
    internal var al_text = ArrayList<String>()
    internal lateinit var obj_adapter:TwitterAPI
    fun onCreateView(savedInstanceState:Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lv_list = findViewById(R.id.lv_list) as ListView
        downloadTweets()
    }
    // download twitter timeline after first checking to see if there is a network connection
    fun downloadTweets() {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.getActiveNetworkInfo()
        if (networkInfo != null && networkInfo.isConnected())
        {
            DownloadTwitterTask().execute(ScreenName)
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show()
        }
    }
    // Uses an AsyncTask to download a Twitter user's timeline
    private inner class DownloadTwitterTask:AsyncTask<String, Void, String>() {
        internal val dialog = ProgressDialog(this@MainActivity)

        protected override fun doInBackground(vararg screenNames:String):String {
            val result:String = null
            if (screenNames.size > 0)
            {
                result = getTwitterStream(screenNames[0])
            }
            return result
        }
        // onPostExecute convert the JSON results into a Twitter object (which is an Array list of tweets
        protected override fun onPostExecute(result:String) {
            Log.e("result", result)
            dialog.dismiss()
            try
            {
                val jsonArray_data = JSONArray(result)
                al_text.clear()
                for (i in 0 until jsonArray_data.length())
                {
                    val jsonObject = jsonArray_data.getJSONObject(i)
                    al_text.add(jsonObject.getString("text"))
                }
            }
            catch (e:Exception) {
                e.printStackTrace()
            }
            // send the tweets to the adapter for rendering
            obj_adapter = TwitterAPI(getApplicationContext(), al_text)
            lv_list.setAdapter(obj_adapter)
        }
        // convert a JSON authentication object into an Authenticated object
        private fun jsonToAuthenticated(rawAuthorization:String):Authenticated {
            val auth:Authenticated = null
            if (rawAuthorization != null && rawAuthorization.length > 0)
            {
                try
                {
                    val gson = Gson()
                    auth = gson.fromJson(rawAuthorization, Authenticated::class.java)
                }
                catch (ex:IllegalStateException) {
                    // just eat the exception
                }
            }
            return auth
        }
        private fun getResponseBody(request:HttpRequestBase):String {
            val sb = StringBuilder()
            try
            {
                val httpClient = DefaultHttpClient(BasicHttpParams())
                val response = httpClient.execute(request)
                val statusCode = response.getStatusLine().getStatusCode()
                val reason = response.getStatusLine().getReasonPhrase()
                if (statusCode == 200)
                {
                    val entity = response.getEntity()
                    val inputStream = entity.getContent()
                    val bReader = BufferedReader(InputStreamReader(inputStream, "UTF-8"), 8)
                    val line:String = null
                    while ((line = bReader.readLine()) != null)
                    {
                        sb.append(line)
                    }
                }
                else
                {
                    sb.append(reason)
                }
            }
            catch (ex:UnsupportedEncodingException) {}
            catch (ex1:ClientProtocolException) {}
            catch (ex2:IOException) {}
            return sb.toString()
        }
        private fun getTwitterStream(screenName:String):String {
            val results:String = null
            // Step 1: Encode consumer key and secret
            try
            {
                // URL encode the consumer key and secret
                val urlApiKey = URLEncoder.encode(CONSUMER_KEY, "UTF-8")
                val urlApiSecret = URLEncoder.encode(CONSUMER_SECRET, "UTF-8")
                // Concatenate the encoded consumer key, a colon character, and the
                // encoded consumer secret
                val combined = urlApiKey + ":" + urlApiSecret
                // Base64 encode the string
                val base64Encoded = Base64.encodeToString(combined.toByteArray(), Base64.NO_WRAP)
                // Step 2: Obtain a bearer token
                val httpPost = HttpPost(TwitterTokenURL)
                httpPost.setHeader("Authorization", "Basic " + base64Encoded)
                httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                httpPost.setEntity(StringEntity("grant_type=client_credentials"))
                val rawAuthorization = getResponseBody(httpPost)
                val auth = jsonToAuthenticated(rawAuthorization)
                // Applications should verify that the value associated with the
                // token_type key of the returned object is bearer
                if (auth != null && auth.token_type.equals("bearer"))
                {
                    // Step 3: Authenticate API requests with bearer token
                    val httpGet = HttpGet(TwitterStreamURL + screenName)
                    // construct a normal HTTPS request and include an Authorization
                    // header with the value of Bearer <>
                    httpGet.setHeader("Authorization", "Bearer " + auth.access_token)
                    httpGet.setHeader("Content-Type", "application/json")
                    // update the results with the body of the response
                    results = getResponseBody(httpGet)
                }
            }
            catch (ex:UnsupportedEncodingException) {}
            catch (ex1:IllegalStateException) {}
            return results
        }
        companion object {
            internal val CONSUMER_KEY = "nW88XLuFSI9DEfHOX2tpleHbR"
            internal val CONSUMER_SECRET = "hCg3QClZ1iLR13D3IeMvebESKmakIelp4vwFUICuj6HAfNNCer"
            internal val TwitterTokenURL = "https://api.twitter.com/oauth2/token"
            internal val TwitterStreamURL = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name="
        }
    }
    companion object {
        internal val ScreenName = "Deepshikhapuri"
        internal val LOG_TAG = "rnc"
    }
}