package com.lynx.kotlinemptyproject

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.lynx.tasm.LynxBooleanOption
import com.lynx.tasm.LynxLoadMeta
import com.lynx.tasm.LynxUpdateMeta
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

        val path = intent.getStringExtra("path")

        val uri = "${path}/main.lynx.bundle"

        if (path.equals("cssBtsInitData")) {

            val builder = LynxLoadMeta.Builder()


            builder.setUrl(uri)
// builder.setBinaryData();
// builder.setTemplateBundle();
            builder.setInitialData(TemplateData.fromMap(mapOf("title" to "XXXXXXXXXXXXXXXXXXXXXXX")))

// builder.addLoadOption();
            val meta = builder.build()
            lynxView.loadTemplate(meta)

            Thread {
                Thread.sleep(3000)

                Log.d("aaa", "3s 后执行")

                lynxView.post {
                    val builderUpdate = LynxUpdateMeta.Builder()
                    builderUpdate.setUpdatedData(TemplateData.fromMap(mapOf("title" to "ABCDEFGHIJKLMNOPQRSTUVWXYZ")))
                    val metaUpdate = builderUpdate.build()
                    lynxView.updateData(metaUpdate.updatedData)
                    Log.d("aaa", "updateData in ${Thread.currentThread().name}")
                }
            }.start()


        } else {

            // Replace it with local lynx bundle
//         val uri = "http://10.21.27.80:3000/main.lynx.bundle?fullscreen=true";
//        val uri = "http://10.21.27.80:3000/base.lynx.bundle"
            lynxView.renderTemplateUrl(uri, "")
        }


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