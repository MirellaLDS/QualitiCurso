package com.domain.intentproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.domain.intentproject.databinding.ActivitySendBinding

class SendActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySendBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySendBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btText1Reply.setOnClickListener {
            val mensagem = viewBinding.edText1Reply.text.toString()

            val mensageiro = Intent(this, ReplyActivity::class.java)
            mensageiro.putExtra("ID_SEND_MESSAGE", mensagem)

            startActivity(mensageiro)
        }

    }
}