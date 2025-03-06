package com.lynx.kotlinemptyproject

import android.content.Context
import com.lynx.tasm.resourceprovider.LynxResourceCallback
import com.lynx.tasm.resourceprovider.LynxResourceRequest
import com.lynx.tasm.resourceprovider.LynxResourceResponse
import com.lynx.tasm.resourceprovider.generic.LynxGenericResourceFetcher
import com.lynx.tasm.resourceprovider.media.LynxMediaResourceFetcher
import com.lynx.tasm.resourceprovider.media.OptionalBool
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.ByteArrayOutputStream
import java.io.Closeable
import java.io.IOException

class DemoLynxMediaResourceFetcher(context: Context): LynxMediaResourceFetcher() {

    override fun isLocalResource(url: String): OptionalBool {
        return if (url.startsWith("/")) { OptionalBool.TRUE } else {
            OptionalBool.FALSE
        }
    }

    override fun shouldRedirectUrl(request: LynxResourceRequest): String {
        return if (request.url.startsWith("/")) { "asset://" + request.url } else { request.url }
    }

}