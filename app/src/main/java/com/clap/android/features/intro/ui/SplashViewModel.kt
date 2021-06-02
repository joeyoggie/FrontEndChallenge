package com.clap.android.features.intro.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clap.android.common.data.local.db.AppPrefs
import com.clap.android.common.data.repository.UserRepository
import com.clap.android.common.data.state.Event
import com.clap.android.common.di.ApplicationScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val userRepository: UserRepository,
    val appPrefs: AppPrefs,
    @ApplicationScope val applicationScope: CoroutineScope
): ViewModel() {

    private val _loadingState = MutableLiveData<Event<Boolean>>()
    val loadingState: LiveData<Event<Boolean>> = _loadingState

    private val _loggedIn = MutableLiveData<Event<Boolean>>()
    val loggedIn: LiveData<Event<Boolean>> = _loggedIn

    fun isLoggedIn() {
        _loggedIn.postValue(Event(appPrefs.isLoggedIn))
    }

    fun getUser() {
        userRepository.getUser()
    }
}