package com.github.krottv.tmstemp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.krottv.tmstemp.data.MessagesRemoteDataSource
import com.github.krottv.tmstemp.data.MessagesRemoteDataSourceError
import com.github.krottv.tmstemp.data.MessagesRemoteDataSourceFake
import com.github.krottv.tmstemp.domain.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MessageViewModel: ViewModel() {
    private val fakeMessages: MessagesRemoteDataSource = MessagesRemoteDataSourceFake()

    private val _state = MutableStateFlow<Result<List<Message>>?>(null)
    val state: StateFlow<Result<List<Message>>?> = _state

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                Result.success(fakeMessages.getMessages())
            } catch (exception: Throwable) {
                Result.failure(exception)
            }

            _state.emit(result)
        }
    }
}
