package com.github.krottv.tmstemp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.AutoTransition
import com.github.krottv.tmstemp.binder.RecyclerFragmentBinder
import com.github.krottv.tmstemp.presentation.MessageViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class RecyclerFragment: Fragment() {

    companion object {
        const val TRANSITION_ID = "RecyclerView"
    }

    private lateinit var fragment: RecyclerFragmentBinder
    private lateinit var viewModel: MessageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MessageViewModel::class.java]

        sharedElementEnterTransition = AutoTransition()
        sharedElementReturnTransition = AutoTransition()

        fragment = RecyclerFragmentBinder(this) { view, item ->
            val navController = findNavController()

            val action = RecyclerFragmentDirections.actionRecyclerFragmentToFullscreenFragment(item)
            val extras = FragmentNavigatorExtras(view to FullscreenFragment.TRANSITION_ID)

            navController.navigate(action, extras)
        }

        fragment.bindView(inflater, container, savedInstanceState).transitionName = TRANSITION_ID

        return fragment.bindView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition(1000, TimeUnit.MILLISECONDS)

        viewModel.loadData()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    if (it != null) {
                        if (it.isSuccess) {
                            fragment.onDataLoaded(it.getOrThrow())

                            (view.parent as? ViewGroup)?.doOnPreDraw {
                                startPostponedEnterTransition()
                            }
                        } else {
                            fragment.showError(it.exceptionOrNull() as Throwable)
                        }
                    } else {
                        fragment.showProgress()
                    }
                }
            }
        }
    }
}