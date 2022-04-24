package com.github.krottv.tmstemp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.github.krottv.tmstemp.databinding.FullscreenMessageBinding
import com.github.krottv.tmstemp.domain.Message

class FullscreenFragment: Fragment() {

    lateinit var binding: FullscreenMessageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FullscreenMessageBinding.inflate(inflater, container, false)

        val item: Message = requireArguments().getParcelable("message")!!

        binding.mainText.text = item.mainText
        binding.imageView.load(item.mainImage)
        binding.anyText.text = item.littleText

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}