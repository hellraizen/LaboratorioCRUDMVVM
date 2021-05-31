package br.com.douglasmotta.mysubscribers.ui.subscriberlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.douglasmotta.mysubscribers.data.db.entity.SubscriberEntity
import br.com.douglasmotta.mysubscribers.repository.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberListViewModel(
    private val repository: SubscriberRepository
) : ViewModel() {

    private val _allSubscriberEvent = MutableLiveData<List<SubscriberEntity>>()
    val allSubscribersEvent: LiveData<List<SubscriberEntity>>
        get() = _allSubscriberEvent

    fun getSubscribers() = viewModelScope.launch {
        _allSubscriberEvent.postValue(repository.getAllSubscriber())
    }


}