package com.bignerdranch.android.trainingapp

import androidx.lifecycle.ViewModel
import com.bignerdranch.android.trainingapp.models.Contact

class MainActivityViewModel : ViewModel() {
    var contacts: List<Contact>? = null
}