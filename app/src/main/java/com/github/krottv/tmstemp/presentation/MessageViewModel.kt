package com.github.krottv.tmstemp.presentation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.krottv.tmstemp.data.RemoteDataSource
import com.github.krottv.tmstemp.data.RemoteDataSourceFake
import com.github.krottv.tmstemp.domain.MessageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MessageViewModel: ViewModel() {
    private val fakeMessages: RemoteDataSource = RemoteDataSourceFake()

    private val _state = MutableStateFlow<Result<List<MessageModel>>?>(null)
    val state: StateFlow<Result<List<MessageModel>>?> = _state

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