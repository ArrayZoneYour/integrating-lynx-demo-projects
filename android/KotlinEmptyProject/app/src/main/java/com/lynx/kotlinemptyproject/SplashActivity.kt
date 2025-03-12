package com.lynx.kotlinemptyproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

class SplashActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        layoutParams.gravity = Gravity.CENTER

        linearLayout.addView(buildBtn("204(测试 - 长列表静置)"))
        linearLayout.addView(buildBtn("204to1000(测试 - 长列表滚动)"))
        linearLayout.addView(buildBtn("images(测试 - 多图渲染场景)"))
        linearLayout.addView(buildBtn("cssInJs"))
        linearLayout.addView(buildBtn("css"))
        linearLayout.addView(buildBtn("cssBst"))
        linearLayout.addView(buildBtn("cssBtsInitData"))
        linearLayout.addView(buildBtn("cssBtsInitDataNoCrash(测试 - 卡片场景)"))

        setContentView(linearLayout, layoutParams)
    }

     fun buildBtn(text: String) :Button{
         val button = Button(this)
         button.text = "LynxDemo - 用例：${text}"
         if (!text.contains("测试 - ")) {
             button.setTextColor(Color.LightGray.toArgb())
         }
         button.setOnClickListener {
             val intent = Intent(this, MainActivity::class.java)
             intent.putExtra("path", text)
             startActivity(intent)
         }
         return button
     }
}