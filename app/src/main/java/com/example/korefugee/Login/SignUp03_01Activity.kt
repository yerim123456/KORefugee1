package com.example.korefugee.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.example.korefugee.R
import com.example.korefugee.databinding.ActivitySignUp0301Binding
import com.example.korefugee.databinding.ActivitySignUp03Binding

class SignUp03_01Activity : AppCompatActivity() {
    // 뷰 바인딩을 위한 객체 획득
    lateinit var binding: ActivitySignUp0301Binding

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
        // 바인딩 기본 작업
        binding= ActivitySignUp0301Binding.inflate(layoutInflater)
        setContentView(binding.root)

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

        // 나라 팝업 메뉴 내려오기
        binding.countryTextView2.setOnClickListener(View.OnClickListener { v ->
            val popupMenu = PopupMenu(this, v)
            val inflater = popupMenu.menuInflater
            val menu = popupMenu.menu
            inflater.inflate(R.menu.countrylist, menu)
            popupMenu.setOnMenuItemClickListener { item ->

                var x =item.title.toString()  // 메뉴의 타이틀을 불러와서

                binding.countryTextView2.setText(x)    // 카테고리 텍스트뷰에 출력해줌

                false
            }
            popupMenu.show()
        })



        // 오류 메세지 출력
        // 결과 저장 및 넘기기
        binding.completeButton.setOnClickListener{
            if(binding.countryTextView2.text.toString() != "" ){
                val intent = Intent(this,SignUp04Activity::class.java)
                intent.putExtra("email", email)
                intent.putExtra("password", password)
                intent.putExtra("position", position)
                intent.putExtra("name", name)
                intent.putExtra("birth", birth)
                intent.putExtra("gender", gender)
                intent.putExtra("field", field)
                intent.putExtra("country", binding.countryTextView2.text.toString())
                // intent.putExtra("korean", binding.koreanskilsTextView2.text.toString())
                intent.putExtra("imgUrl", "")
                startActivity(intent)
            }
        }
    }
}