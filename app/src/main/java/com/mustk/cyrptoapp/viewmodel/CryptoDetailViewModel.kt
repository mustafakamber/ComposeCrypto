package com.mustk.cyrptoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.mustk.cyrptoapp.domain.CryptoRepository
import com.mustk.cyrptoapp.model.Crypto
import com.mustk.cyrptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val repository :  CryptoRepository
): ViewModel() {

    suspend fun getCrypto() : Resource<Crypto>{
        return repository.getCrypto()
    }
}