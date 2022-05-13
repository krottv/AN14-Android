package com.tms.android.ffthtask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.motion.utils.ViewState
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.github.krottv.tmstemp.R
import com.github.ybq.android.spinkit.SpinKitView
import com.github.ybq.android.spinkit.style.Circle
import kotlinx.coroutines.delay
import okhttp3.internal.notify
import okhttp3.internal.notifyAll
import okhttp3.internal.wait

class MessageFragment: Fragment() {

    private lateinit var msgRecyclerView: RecyclerView
    private var adapter: MessageAdapter? = null
    private var messages = mutableListOf<Message>()

    private val messageListViewModel: MessageListViewModel by lazy{
        ViewModelProviders.of(this).get(MessageListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.message_recycle_view, container, false)

        msgRecyclerView = view.findViewById(R.id.msg_recycler_view) as RecyclerView
        msgRecyclerView.layoutManager = LinearLayoutManager(context)

        messageListViewModel.loadData()
        updateMessages()

        return view
    }

    private inner class MsgHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener{

        private lateinit var message: Message
        private val mainText: TextView = itemView.findViewById(R.id.textView)
        private val subText: TextView  = itemView.findViewById(R.id.textView2)
        private val img1: ImageView = itemView.findViewById(R.id.img1)
        private val img2: ImageView = itemView.findViewById(R.id.img2)

        fun bind(message: Message) {
            this.message = message
            mainText.text = this.message.mainText
            subText.text = this.message.subText
            val link = this.message.imgLink

            img1.load(link){
                crossfade(true)
                transformations(CircleCropTransformation())
            }

            img2.load(link){
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            Toast.makeText(context, mainText.text, Toast.LENGTH_SHORT).show()
        }
    }

    private inner class MessageAdapter(var messages: List<Message>): RecyclerView.Adapter<MsgHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsgHolder {

            val view = layoutInflater.inflate(R.layout.list_item_message, parent, false)
            return MsgHolder(view)
        }

        override fun getItemCount(): Int = messages.size

        override fun onBindViewHolder(holder: MsgHolder, position: Int) {
            val message = messages[position]
            holder.bind(message)
        }
    }

    companion object{
        fun newInstance(): MessageFragment {
            return MessageFragment()
        }
    }

    private fun updateMessages(){
        lifecycleScope.launchWhenStarted {
            messageListViewModel.state.collect{
                if (it != null){
                    if (it.isSuccess){
                        messages = it.getOrThrow()
                        adapter = MessageAdapter(messages)
                        msgRecyclerView.adapter = adapter
                    }
                }
            }
        }
    }

    fun deleteOne(){
        if (msgRecyclerView.isAnimating){
            msgRecyclerView.itemAnimator?.isRunning(RecyclerView.ItemAnimator.ItemAnimatorFinishedListener{})
            return
        } else {
            this.messages.removeAt(0)
            adapter?.notifyItemRemoved(0)
        }
    }

    fun checkEmpty(): Boolean{
        return this.messages.size == 0
    }
}

