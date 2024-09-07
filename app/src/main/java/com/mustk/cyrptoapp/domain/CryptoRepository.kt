package com.mustk.cyrptoapp.domain

import com.mustk.cyrptoapp.model.Crypto
import com.mustk.cyrptoapp.model.CryptoList
import com.mustk.cyrptoapp.service.CryptoAPI
import com.mustk.cyrptoapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CryptoRepository @Inject constructor(
    private val api: CryptoAPI
) {
    suspend fun getCryptoList(): Resource<CryptoList> {
        val response = try {
            api.getCryptoList()
        } catch (e: Exception) {
            return Resource.Error("Error.")
        }
        return Resource.Success(response)
    }

    suspend fun getCrypto(): Resource<Crypto> {
        val response = try {
            api.getCrypto()
        } catch (e: Exception) {
            return Resource.Error("Error.")
        }
        return Resource.Success(response)
    }
}