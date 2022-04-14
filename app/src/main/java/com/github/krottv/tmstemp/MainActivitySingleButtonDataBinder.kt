package com.github.krottv.tmstemp

class MainActivitySingleButtonDataBinder(val activity: MainActivity): MainActivityDataBinder {

    private val singleButtonAnimation by lazy { SingleButtonAnimation() }

    override fun bind() {
        /*val binding = MainSingleButtonBinding.inflate(LayoutInflater.from(activity))
        binding.buttonClickToRotate.setOnClickListener {
            singleButtonAnimation.animate(it)
        }*/
    }
}