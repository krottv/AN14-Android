package com.github.krottv.tmstemp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.mainText.apply {
            text = item.mainText
            setOnClickListener {
                val navController = findNavController()

                val action = FullscreenFragmentDirections.actionFullscreenFragmentToRecyclerFragment()
                val extras = FragmentNavigatorExtras(view to RecyclerFragment.TRANSITION_ID)

                navController.navigate(action, extras)
            }
        }
    }
}