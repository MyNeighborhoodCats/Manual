package kr.co.vacatio.ylee.my_neighborhood_cats.manual.data

import android.content.Context
import kr.co.vacatio.ylee.my_neighborhood_cats.manual.R
import timber.log.Timber
import java.io.IOException
import java.io.InputStream
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory


object SSLUtil {
    fun getPinnedCertSslSocketFactory(context: Context, trustManagerFactory: TrustManagerFactory?): SSLSocketFactory? {
        try {
            val sslContext: SSLContext = SSLContext.getInstance("TLSv1.2")
            sslContext.init(null, trustManagerFactory?.getTrustManagers(), null)
            return sslContext.getSocketFactory()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun getTrustManagerFactory(context : Context, isDevelopMode : Boolean) : TrustManagerFactory? {
        val keyStoreType: String = KeyStore.getDefaultType()
        val keyStore: KeyStore = KeyStore.getInstance(keyStoreType)
        keyStore.load(null, null)
        val cert = getCertificate(context, isDevelopMode)
        if (cert == null) {
            return null
        }
        keyStore.setCertificateEntry("ca", cert)
        val tmfAlgorithm: String = TrustManagerFactory.getDefaultAlgorithm()
        val trustManagerFactory: TrustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm)
        trustManagerFactory.init(keyStore)
        return trustManagerFactory
    }

    fun getCertificate(context : Context, isDevelopMode: Boolean) : Certificate? {
        val certificateFactory: CertificateFactory = CertificateFactory.getInstance("X.509")
        val caInput: InputStream = context.getResources().openRawResource(
            when(isDevelopMode) {
                true -> R.raw.cert_dev
                else -> R.raw.cert
            }
        )
        var cert: Certificate? = null
        try {
            cert = certificateFactory.generateCertificate(caInput)
            Timber.d("ca = ${(cert as X509Certificate)?.subjectDN}")
        } catch (e: CertificateException) {
            e.printStackTrace()
        } finally {
            caInput.close()
        }
        return cert
    }

}