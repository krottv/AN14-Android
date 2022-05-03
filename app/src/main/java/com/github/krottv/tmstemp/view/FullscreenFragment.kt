package com.github.krottv.tmstemp.view

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
    ): View {
        exitTransition = MaterialElevationScale(true)
        enterTransition = MaterialElevationScale(false)

        reenterTransition = MaterialElevationScale(true)
        returnTransition = MaterialElevationScale(false)

        binding = FullscreenPostFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item: PostModel = requireArguments().getParcelable("post")!!
        binding.button.text = item.button
        binding.textSubtitle.text = item.subtitle

        ObjectAnimator().apply {
            duration = 1000L
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            setValues(
                PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.2f)
            )
            target = binding.button
            start()
        }
        postponeEnterTransition(100L, java.util.concurrent.TimeUnit.MILLISECONDS)

        binding.imagePost.load(item.image) {
            listener { request, result ->
                (view.parent as? ViewGroup)?.doOnPreDraw {
                    startPostponedEnterTransition()
                }
            }
        }
        binding.button.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}