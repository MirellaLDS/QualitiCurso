package com.domain.intentproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.domain.intentproject.databinding.ActivityReplyBinding
import com.domain.intentproject.databinding.ActivitySendBinding

class ReplyActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityReplyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityReplyBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

       val mensagem = intent.getStringExtra("ID_SEND_MESSAGE")

        viewBinding.tvText1Reply.text = mensagem

    }
}