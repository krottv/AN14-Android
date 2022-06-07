package com.github.krottv.tmstemp.presentation

import com.github.krottv.tmstemp.domain.purchase.ProductEntity
import kotlinx.coroutines.flow.StateFlow

 class PurchaseViewModel(
    val purchase: StateFlow<Result<List<ProductEntity>>>,
    val isPremised: StateFlow<Boolean>
) {


}