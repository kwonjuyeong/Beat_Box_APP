package com.example.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class SoundViewModel(private val beatBox: BeatBox) : BaseObservable() {
    fun onButtonClicked() {
        sound?.let {
            beatBox.play(it)
        }
    }

    //Sound객체 참조를 갖는 속성 추가
    var sound: Sound? = null
        set(sound) {
            field = sound
            notifyChange()
        }

    //버튼 제목을 갖는 속성 추가
    @get:Bindable
    val title: String?
        get() = sound?.name
}