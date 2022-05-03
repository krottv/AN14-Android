package com.github.krottv.tmstemp.view

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.AutoTransition
import coil.load
import com.github.krottv.tmstemp.databinding.FullscreenMessageBinding
import com.github.krottv.tmstemp.domain.MessageModel
import java.util.concurrent.TimeUnit

class FullscreenFragment: Fragment() {

    companion object {
        const val TRANSITION_ID = "Fullscreen"
    }

    private lateinit var binding: FullscreenMessageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedElementEnterTransition = AutoTransition()
        sharedElementReturnTransition = AutoTransition()

        binding = FullscreenMessageBinding.inflate(inflater, container, false)

        binding.root.transitionName = TRANSITION_ID

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item: MessageModel = requireArguments().getParcelable("currentMessage")!!

        postponeEnterTransition(3000, TimeUnit.MILLISECONDS)

        binding.anyText.text = item.littleText

        binding.imageView.load(item.mainImage) {
            listener { request, result ->
                (view.parent as? View)?.doOnPreDraw {
                    startPostponedEnterTransition()
                }
            }
        }

        binding.imageView.scaleType = ImageView.ScaleType.CENTER_CROP

        ObjectAnimator().apply {
            target = binding.buttonBack
            duration = 500
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            interpolator = LinearInterpolator()
            setValues(PropertyValuesHolder.ofFloat("scaleX", 1f, 1.2f),
                      PropertyValuesHolder.ofFloat("scaleY", 1f, 1.2f))
            start()
        }

        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}