package com.tinne14.testprojectsuitmedia.ui.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FirstScreenViewModel: ViewModel() {

    var isPalindrome: Boolean = false

    fun checkPalindrome(text: String) {
        val inputReversed = text.reversed()
        isPalindrome = text.equals(inputReversed, ignoreCase = true)
    }
}