package com.mustk.cyrptoapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustk.cyrptoapp.domain.CryptoRepository
import com.mustk.cyrptoapp.model.CryptoListItem
import com.mustk.cyrptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    var cryptoList = mutableStateOf<List<CryptoListItem>>(listOf())
    var errorMessage = mutableStateOf("")
    var loading = mutableStateOf(false)

    private var initialCryptoList = listOf<CryptoListItem>()
    private var searchStaring = true

    init {
        loadCryptos()
    }

    fun searchCryptoList(query : String){
        val listToSearch = if (searchStaring){
            cryptoList.value
        } else {
            initialCryptoList
        }

        viewModelScope.launch(Dispatchers.Default) {
            if (query.isBlank()){
                cryptoList.value = initialCryptoList
                searchStaring = true
                return@launch
            }
            val results = listToSearch.filter {
                it.currency.contains(query.trim(), ignoreCase = true)
            }
            if (searchStaring) {
                initialCryptoList = cryptoList.value
                searchStaring = false
            }
            cryptoList.value = results
        }
    }

    fun loadCryptos() {
        viewModelScope.launch {
            loading.value = true
            val result = repository.getCryptoList()
            when (result) {
                is Resource.Success -> {
                    val cryptoItems = result.data!!.mapIndexed { index, cryptoListItem ->
                        CryptoListItem(cryptoListItem.currency,cryptoListItem.price)
                    } as List<CryptoListItem>

                    errorMessage.value = ""
                    loading.value = false
                    cryptoList.value += cryptoItems
                }

                is Resource.Error -> {
                    errorMessage.value = result.message!!
                    loading.value = false
                }

                is Resource.Loading -> TODO()
            }
        }
    }

}