package com.bignerdranch.android.trainingapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.trainingapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private val vb by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)

        vb.receivedDataTextView.text = intent
            .extras
            ?.getString(FIRST_ACTIVITY_MESSAGE)
            ?: "Ничего не отправлено"

        vb.navigateToFirstActivityButton.setOnClickListener {
            finish()
        }
    }

    companion object {
        private const val FIRST_ACTIVITY_MESSAGE = "firstActivityMessage"

        fun getLaunchingIntent(launchContext: Context, message: String) =
            Intent(launchContext, SecondActivity::class.java)
                .apply {
                    putExtra(FIRST_ACTIVITY_MESSAGE, message)
                }
    }
}