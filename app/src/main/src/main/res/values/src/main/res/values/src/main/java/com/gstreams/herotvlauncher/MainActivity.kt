package com.gstreams.herotvlauncher

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            val candidates = listOf(
                "ar.tvplayer.tv",
                "com.tivimate.player"
            )

            var intent: Intent? = null
            for (pkg in candidates) {
                intent = packageManager.getLaunchIntentForPackage(pkg)
                if (intent != null) break
            }

            if (intent != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "TiviMate not installed", Toast.LENGTH_LONG).show()
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.amazon.com/s?k=tivimate")
                    )
                )
            }
            finish()
        }, 3500)
    }
}
