import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class MainViewModel: ViewModel() {

    private val _stateFlow = MutableStateFlow<String?>(null)
    val state: StateFlow<String?> = _stateFlow.asStateFlow()

    private var job: Job? = null

    fun loadData() {
        if (job != null) return
        job = viewModelScope.launch {
            for (i in 1..8) {
                _stateFlow.value = "Загружаем данные. Шаг - $i"
                delay(1000L)
            }
            _stateFlow.value = "Загрузка данных завершена"
            job = null
        }
    }
}