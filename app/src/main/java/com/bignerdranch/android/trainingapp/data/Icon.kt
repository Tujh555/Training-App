package com.bignerdranch.android.trainingapp.data

sealed class Icon(
    open val id: Int,
    open val title: String,
) {
    class Active(
        override val id: Int,
        override val title: String,
    ) : Icon(id, title)

    class Disable(
        override val id: Int,
        override val title: String,
    ) : Icon(id, title)
}
