package org.whocare.carespoon.ui.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.whocare.carespoon.R
import org.whocare.carespoon.ui.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /* 자동 로그인 처리
        if (!CareSpoonSharedPreference.getAccessToken().isNullOrBlank()) {
            moveMainActivity(1)
        else
        }*/

        moveSignFragment(1)
    }

    private fun moveMainActivity(sec: Int){
            Handler().postDelayed(Runnable {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 1000 * sec.toLong()) // sec초 정도 딜레이를 준 후 시작
    }

    private fun moveSignFragment(sec: Int){
        Handler().postDelayed(Runnable {
            val fragment = SignFragment()
            supportFragmentManager.beginTransaction().add(R.id.container_sign, fragment).commit()
        }, 1000 * sec.toLong()) // sec초 정도 딜레이를 준 후 시작
    }

    /* 로고 애니메이션 처리
    private fun animate(property: Property<View, Float>, distance: Float) {
        ObjectAnimator.ofFloat(textView, "translationX", 100f).apply {
            duration = 1000
            start()
        }
    }
    */
}