package com.github.krottv.tmstemp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.krottv.tmstemp.data.PostRemoteDataSource
import com.github.krottv.tmstemp.data.PostRemoteDataSourceFake
import com.github.krottv.tmstemp.domain.PostModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val postsRemoteDataSource: PostRemoteDataSource = PostRemoteDataSourceFake()

    private val _state = MutableStateFlow<Result<List<PostModel>>?>(null)
    val state: StateFlow<Result<List<PostModel>>?> = _state

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                Result.success(postsRemoteDataSource.getPosts())
            } catch (t: Throwable) {
                Result.failure(t)
            }
            _state.emit(result)

        }
    }
}
