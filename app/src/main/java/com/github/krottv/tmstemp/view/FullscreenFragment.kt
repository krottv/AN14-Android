package com.github.krottv.tmstemp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.github.krottv.tmstemp.databinding.FullscreenPostFragmentBinding
import com.github.krottv.tmstemp.domain.PostModel

class FullscreenFragment : Fragment() {

    private lateinit var binding: FullscreenPostFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FullscreenPostFragmentBinding.inflate(inflater, container, false)

        val item: PostModel = requireArguments().getParcelable("post")!!

        binding.textView3.text = item.title
        binding.imageView3.load(item.image)
        binding.textView4.text = item.subtitle

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}