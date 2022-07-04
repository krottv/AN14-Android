package com.example.app.domain.purchase

import kotlinx.coroutines.flow.StateFlow

interface PurchaseStateInteractor {
    val isPremium: StateFlow<Boolean>

    suspend fun getListPurchases(): List<ProductEntity>
}