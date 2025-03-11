package com.lynx.kotlinemptyproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.compose.foundation.gestures.Orientation

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

        linearLayout.addView(buildBtn("204"))
        linearLayout.addView(buildBtn("204to1000"))
        linearLayout.addView(buildBtn("images"))
        linearLayout.addView(buildBtn("cssInJs"))
        linearLayout.addView(buildBtn("css"))
        linearLayout.addView(buildBtn("cssBst"))
        linearLayout.addView(buildBtn("cssBtsInitData"))
        linearLayout.addView(buildBtn("cssBtsInitDataNoCrash"))

        setContentView(linearLayout, layoutParams)
    }

     fun buildBtn(text: String) :Button{
         val button = Button(this)
         button.text = "点击打开一个Lynx测试页面 - 用例：${text}"
         button.setOnClickListener {
             val intent = Intent(this, MainActivity::class.java)
             intent.putExtra("path", text)
             startActivity(intent)
         }
         return button
     }
}