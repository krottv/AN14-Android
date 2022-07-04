package com.example.app.data.purchase

import android.content.Context
import com.android.billingclient.api.*
import com.example.app.domain.purchase.ProductEntity
import com.example.app.domain.purchase.PurchaseStateInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PurchaseStateInteractorGooglePlay(
    context: Context,
    private val mapper: GooglePlayProductMapper
) : PurchaseStateInteractor {
    init {
        startConnection()
    }

    val _isPremium = MutableStateFlow(false)
    override var isPremium: StateFlow<Boolean> = _isPremium

    private val purchasesUpdatedListener =
        PurchasesUpdatedListener { billingResult, purchases ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
                var checker = false

                for (purchase in purchases) {
                    if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
                        checker = true
                    }
                }
                _isPremium.value = checker
            }
        }

    var billingClient = BillingClient.newBuilder(context)
        .setListener(purchasesUpdatedListener)
        .enablePendingPurchases()
        .build()

    override suspend fun getListPurchases(): List<ProductEntity> {

        val params = QueryProductDetailsParams.newBuilder()

        val productDetailsResult = billingClient.queryProductDetails(params.build())

        return productDetailsResult.productDetailsList!!.map { mapper.map(it) }
    }

    private fun startConnection() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    // Соединение установлено, можно запрашивать покупки
                }
            }

            override fun onBillingServiceDisconnected() {
                // Тут нужно попробовать установить соединение заново
            }
        })
    }
}
