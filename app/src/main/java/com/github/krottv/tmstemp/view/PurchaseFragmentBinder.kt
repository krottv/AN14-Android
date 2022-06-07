package com.github.krottv.tmstemp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.krottv.tmstemp.databinding.PurchaseFragmentBinding

class PurchaseFragmentBinder(val fragment: PurchaseFragment) {

    lateinit var binding: PurchaseFragmentBinding

    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        binding = PurchaseFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }
}