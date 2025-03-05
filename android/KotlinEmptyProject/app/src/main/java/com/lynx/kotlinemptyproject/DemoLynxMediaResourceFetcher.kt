package com.lynx.kotlinemptyproject

import android.content.Context
import com.lynx.tasm.resourceprovider.LynxResourceCallback
import com.lynx.tasm.resourceprovider.LynxResourceRequest
import com.lynx.tasm.resourceprovider.LynxResourceResponse
import com.lynx.tasm.resourceprovider.generic.LynxGenericResourceFetcher
import com.lynx.tasm.resourceprovider.media.LynxMediaResourceFetcher
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.ByteArrayOutputStream
import java.io.Closeable
import java.io.IOException

class DemoLynxMediaResourceFetcher(context: Context): LynxMediaResourceFetcher() {
    private val client = OkHttpClient()

    private var mContext: Context = context.applicationContext

    override fun fetchImage(
        request: LynxResourceRequest,
        callback: LynxResourceCallback<Closeable>
    ) {
        Thread {
            try {
                val uri = request.url
                if (uri.startsWith("http")) {
                    val request = Request.Builder()
                        .url(uri)
                        .build()

                    client.newCall(request).execute().use { response ->
                        if (!response.isSuccessful) throw IOException("Unexpected code $response")

                        for ((name, value) in response.headers) {
                            println("$name: $value")
                        }

                        callback.onResponse(LynxResourceResponse.onSuccess(response.body!!.byteStream()))
                    }
                } else {
                    mContext.assets.open(uri).use { inputStream ->
                        ByteArrayOutputStream().use { byteArrayOutputStream ->
                            val buffer = ByteArray(1024)
                            var length: Int
                            while ((inputStream.read(buffer).also { length = it }) != -1) {
                                byteArrayOutputStream.write(buffer, 0, length)
                            }
                            callback.onResponse(LynxResourceResponse.onSuccess(byteArrayOutputStream))
                        }
                    }
                }
            } catch (e: IOException) {
//                callback.onResponse(LynxResourceResponse.onFailed(Throwable(e.message)) as LynxResourceResponse<String>?)
            }
        }.start()
    }

    override fun shouldRedirectUrl(request: LynxResourceRequest): String {
        return request.url
    }
//    override fun fetchResourcePath(p0: LynxResourceRequest?, callback: LynxResourceCallback<String>) {
//        callback.onResponse(LynxResourceResponse.onFailed(Throwable("fetchResourcePath not supported.")) as LynxResourceResponse<String>?);
//    }
}