package com.github.krottv.tmstemp.view

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.github.krottv.tmstemp.databinding.FullscreenPostFragmentBinding
import com.github.krottv.tmstemp.domain.PostModel
import com.google.android.material.transition.MaterialElevationScale

class FullscreenFragment : Fragment() {

    private lateinit var binding: FullscreenPostFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        enterTransition = MaterialElevationScale(true)
        exitTransition = MaterialElevationScale(true)

        reenterTransition = MaterialElevationScale(true)
        returnTransition = MaterialElevationScale(true)

        binding = FullscreenPostFragmentBinding.inflate(inflater, container, false)

        val item: PostModel = requireArguments().getParcelable("post")!!


        binding.imageView3.load(item.image)
        binding.textView4.text = item.subtitle

        ObjectAnimator().apply {
            duration = 1000L
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            setValues(
                PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.2f)
            )
            target = binding.button2
            start()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}