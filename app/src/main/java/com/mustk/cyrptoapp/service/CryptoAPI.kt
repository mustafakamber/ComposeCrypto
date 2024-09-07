package com.mustk.cyrptoapp.service

import com.mustk.cyrptoapp.model.Crypto
import com.mustk.cyrptoapp.model.CryptoList
import com.mustk.cyrptoapp.util.Constant.EXT_URL_DETAIL
import com.mustk.cyrptoapp.util.Constant.EXT_URL_LIST
import retrofit2.http.GET

interface CryptoAPI {

    @GET(EXT_URL_LIST)
    suspend fun getCryptoList(): CryptoList

    @GET(EXT_URL_DETAIL)
    suspend fun getCrypto(): Crypto
}