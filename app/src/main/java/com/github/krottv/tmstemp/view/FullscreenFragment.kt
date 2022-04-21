package com.github.krottv.tmstemp.view

import android.os.Bundle
import android.transition.AutoTransition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
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
    ): View {

        sharedElementReturnTransition = AutoTransition()
        sharedElementEnterTransition = AutoTransition()

        binding = FullscreenPostFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item: PostModel = requireArguments().getParcelable("post")!!
        binding.textTitle.text = item.title

        binding.textSubtitle.text = item.subtitle
        binding.root.transitionName = TRANSITION_NAME

        postponeEnterTransition(2000L, java.util.concurrent.TimeUnit.MILLISECONDS)
        binding.imagePost.load(item.image) {
            listener { request, result ->

                (view.parent as? ViewGroup)?.doOnPreDraw {
                    startPostponedEnterTransition()
                }
            }
        }
    }

    companion object {
        const val TRANSITION_NAME = "FullscreenFragment.transition"
    }
}