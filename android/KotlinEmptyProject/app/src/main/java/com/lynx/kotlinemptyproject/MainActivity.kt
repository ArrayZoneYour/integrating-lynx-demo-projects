package com.lynx.kotlinemptyproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.lynx.tasm.LynxBooleanOption
import com.lynx.tasm.LynxView
import com.lynx.tasm.LynxViewBuilder
import com.lynx.tasm.TemplateData
// import com.lynx.tasm.utils.DisplayMetricsHolder

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lynxView: LynxView = buildLynxView()
        lynxView.updateGlobalProps(TemplateData.fromMap(mapOf("platform" to "android")))
        setContentView(lynxView)

        val uri = "main.lynx.bundle";
        // Replace it with local lynx bundle
//         val uri = "http://10.21.27.80:3000/main.lynx.bundle?fullscreen=true";
//        val uri = "http://10.21.27.80:3000/base.lynx.bundle"
        lynxView.renderTemplateUrl(uri, "")

        // open switch page
        // startActivity(Intent(this, SwitchActivity::class.java));
    }

    private fun buildLynxView(): LynxView {
        val viewBuilder: LynxViewBuilder = LynxViewBuilder()
        viewBuilder.setMediaResourceFetcher(DemoLynxMediaResourceFetcher(this))
        viewBuilder.setEnableGenericResourceFetcher(LynxBooleanOption.TRUE);
        viewBuilder.setTemplateProvider(DemoTemplateProvider(this))
        return viewBuilder.build(this)
    }
}