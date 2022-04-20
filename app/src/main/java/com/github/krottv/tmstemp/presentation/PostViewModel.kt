package com.github.krottv.tmstemp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.krottv.tmstemp.domain.PostModel
import com.github.krottv.tmstemp.data.PostsRemoteDataSource
import com.github.krottv.tmstemp.data.PostsRemoteDataSourceFake
import com.github.krottv.tmstemp.data.PostsRemoteDataSourceFakeError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val postsRemoteDataSourse: PostsRemoteDataSource = PostsRemoteDataSourceFake()

    private val _state = MutableStateFlow<Result<List<PostModel>>?>(null)
    val state: StateFlow<Result<List<PostModel>>?> = _state

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                Result.success(postsRemoteDataSourse.getPosts())
            } catch (t: Throwable) {
                Result.failure(t)
            }
            _state.emit(result)
        }
    }
}