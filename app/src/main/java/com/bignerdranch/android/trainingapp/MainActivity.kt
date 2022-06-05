package com.bignerdranch.android.trainingapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bignerdranch.android.trainingapp.databinding.ActivityMainBinding
import com.bignerdranch.android.trainingapp.fragments.FirstFragment

class MainActivity : AppCompatActivity(), Callback {
    private val vb by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val secondActivityButtonListener = View.OnClickListener {
        SecondActivity.getLaunchingIntent(this, vb.sentDataEditText.text.toString()).also {
            startActivity(it)
        }
    }

    private val firstFragmentButtonListener = View.OnClickListener {
        val msg = vb.sentDataEditText.text.toString()
        setConstraintLayoutVisibility(View.GONE)

        supportFragmentManager.beginTransaction()
            .add(vb.fragmentContainer.id, FirstFragment.newInstance(msg))
            .addToBackStack(null)
            .commit()
    }

    private val navigateToFirstGraphFragmentListener = View.OnClickListener {
        setConstraintLayoutVisibility(View.GONE)
        vb.navContainer.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.navContainer.visibility = View.GONE

        vb.navigateToSecondActivityButton.setOnClickListener(secondActivityButtonListener)
        vb.navigateToFirstFragmentButton.setOnClickListener(firstFragmentButtonListener)
        vb.navigateToFirstGraphFragment.setOnClickListener(navigateToFirstGraphFragmentListener)
    }

    override fun setConstraintLayoutVisibility(visibility: Int) {
        vb.constraintLayout.visibility = visibility
    }
}