package kr.co.vacatio.ylee.my_neighborhood_cats.manual.data

import kr.co.vacatio.ylee.my_neighborhood_cats.manual.BuildConfig
import kr.co.vacatio.ylee.my_neighborhood_cats.manual.Manual
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

object RetrofitObject {
    // TODO { modify baseURL }
    private const val baseUrl = BuildConfig.SERVER_URL_DEV
    //    private const val baseUrl = BuildConfig.SERVER_URL

    // TODO { 통신 프로토콜 확정 시 정리 }
    // http ver : false, https ver : true
    private val retrofit = createRetrofit(false)
//    private val retrofit = createRetrofit(true)

    fun createRetrofitWithoutSSL() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createRetrofit(withSSL : Boolean) : Retrofit {
        if (withSSL == false) return createRetrofitWithoutSSL()
        val context = Manual.applicationContext()
        val isDevelopMode : Boolean = (baseUrl == BuildConfig.SERVER_URL_DEV)
        val trustManagerFactory : TrustManagerFactory? = SSLUtil.getTrustManagerFactory(context, isDevelopMode)
        val trustManager : X509TrustManager? = trustManagerFactory?.trustManagers?.get(0) as X509TrustManager?
        val sslSocketFactory : SSLSocketFactory? = SSLUtil.getPinnedCertSslSocketFactory(context, trustManagerFactory)
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustManager)
                .build()
                )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}