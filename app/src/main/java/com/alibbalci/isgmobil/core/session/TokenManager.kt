package com.alibbalci.isgmobil.core.session

import kotlinx.coroutines.flow.Flow

interface TokenManager {
    //Flow kullanmak tokenin sürekli durumunu gözlemyebilmemizi sağlar .
    val token: Flow<String?>
    //suspend bulunmasının sebebi DataStore yazma işleminin coroutine içerisinde yapılmasıdır .
    suspend fun saveToken(token: String)
    suspend fun clearToken()
}