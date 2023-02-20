package com.example.korefugee.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.korefugee.R
import com.example.korefugee.databinding.ActivitySignUp0301Binding
import com.example.korefugee.databinding.ActivitySignUp0302Binding

class SignUp03_02Activity : AppCompatActivity() {
    // 뷰 바인딩을 위한 객체 획득
    lateinit var binding: ActivitySignUp0302Binding

    lateinit var email: String
    lateinit var password: String
    lateinit var position: String
    lateinit var name: String
    lateinit var birth: String
    lateinit var gender: String
    lateinit var field: String
    lateinit var imgUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up0302)

        // 이메일 및 비밀번호, 지위 등 받아오기
        if (intent.hasExtra("email") && intent.hasExtra("password")
            && intent.hasExtra("position") && intent.hasExtra("name")
            && intent.hasExtra("birth") && intent.hasExtra("gender")
            && intent.hasExtra("field")) {

            email = intent.getStringExtra("email").toString()
            password =  intent.getStringExtra("password").toString()
            position = intent.getStringExtra("position").toString()
            name = intent.getStringExtra("name").toString()
            birth = intent.getStringExtra("birth").toString()
            gender = intent.getStringExtra("gender").toString()
            field = intent.getStringExtra("field").toString()
        } else {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }

        // 결과 저장 및 넘기기
        binding.completeButton.setOnClickListener{
            if( imgUrl != null ){
                val intent = Intent(this,SignUp04Activity::class.java)
                intent.putExtra("email", email)
                intent.putExtra("password", password)
                intent.putExtra("position", position)
                intent.putExtra("name", name)
                intent.putExtra("birth", birth)
                intent.putExtra("gender", gender)
                intent.putExtra("field", field)
                intent.putExtra("country", "Korea")
                intent.putExtra("korean", "")
                intent.putExtra("imgUrl", "")
                startActivity(intent)
            }
        }

    }
}