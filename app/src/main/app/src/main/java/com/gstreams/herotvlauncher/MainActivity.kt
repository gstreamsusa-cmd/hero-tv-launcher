package com.gstreams.herotvlauncher

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tivimatePackage = "ar.tvplayer.tv"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Launch TiViMate if installed
        val launchIntent = packageManager.getLaunchIntentForPackage(tivimatePackage)
        if (launchIntent != null) {
            startActivity(launchIntent)
            finish()
            return
        }

        // If not installed, send to Play Store search (fallback to web)
        val storeIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=tivimate&c=apps"))
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        val webIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://play.google.com/store/search?q=tivimate&c=apps")
        )

        try {
            startActivity(storeIntent)
        } catch (e: Exception) {
            startActivity(webIntent)
        }

        finish()
    }
}
