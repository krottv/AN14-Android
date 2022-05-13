package com.tms.android.ffthtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MessageListViewModel: ViewModel() {

    val messageRemoteDataSource: MessageRemoteDataSourceFake = MessageRemoteDataSourceFake()

    private val _state = MutableStateFlow<Result<MutableList<Message>>?>(null)
    val state: StateFlow<Result<MutableList<Message>>?> = _state

    fun loadData(){
        viewModelScope.launch(Dispatchers.IO){

            val result = try{
                Result.success(messageRemoteDataSource.getMessages())
            } catch (t: Throwable){
                Result.failure(t)
            }

            _state.emit(result)

        }
    }
}