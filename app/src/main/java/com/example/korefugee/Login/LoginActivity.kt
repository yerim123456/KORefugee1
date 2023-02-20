package com.example.korefugee.Login

import android.content.Context
import android.content.Intent
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import com.example.korefugee.MainActivity
import com.example.korefugee.R
import com.example.korefugee.databinding.ActivityLoginBinding
import com.example.korefugee.databinding.ActivityMainBinding

// email, password 검증 및 오류 메세지 보여주기
// 로그인 유지 시 그냥 들어올 수 있도록 설정
// 오류 메세지 한 번 클릭으로 없어지도록 or 오류 메세지 sign in 눌렀을 때 알아서 3초 후 없어지도록
// 눌렸을 때 디자인 손보기..?
// 키보드 올라오는 것 막기

class LoginActivity : AppCompatActivity() {

    // 뷰 바인딩을 위한 객체 획득
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩 기본 작업
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // sign in 버튼 클릭 시
        binding.signinButton.setOnClickListener{
            // email, password 가 db에 있는지 확인
                // email, password String 전달
            binding.emailEditText.text.toString()
            binding.passwordEditText.text.toString()

            // 맞다면 sign in + 화면 전환 + uid 저장
            // 화면 전환
            // val intent = Intent(this,MainActivity::class.java)
            // startActivity(intent)

            // 아니라면 오류 메세지 전달 및 전부 지우기
                // email 오류
            binding.emailerrortextView.setText("오류 메세지 받아와서 보여주기")
            binding.emailerrortextView.visibility = VISIBLE
            // password 오류 메세지 받아와서 설정 및 보이도록 설정
            binding.passworderrortextView.setText("오류 메세지 받아와서 보여주기")
            binding.passworderrortextView.visibility = VISIBLE
        }

        // 만약 email 입력 부분 클릭 되었다면 오류 메세지 다시 보이지 않도록 설정
        binding.emailEditText.setOnClickListener {
            if ( binding.emailerrortextView.visibility ==  VISIBLE){
                binding.emailerrortextView.visibility = GONE
            }
        }

        // 만약 password 입력 부분 클릭 되었다면 오류 메세지 다시 보이지 않도록 설정
        binding.passwordEditText.setOnClickListener {
            if ( binding.passworderrortextView.visibility ==  VISIBLE){
                binding.passworderrortextView.visibility = GONE
            }
        }

        // sign up 버튼 클릭 시
        binding.signupbutton.setOnClickListener{
            // 회원 가입 화면 전환
            val intent = Intent(this,SignUp01Activity::class.java)
            startActivity(intent)
        }

    }
    // 앱이 종료 후 다시 시작 시 로그인 유지 될 수 있도록
    public override fun onStart() {
        super.onStart()
        /* 파이어 베이스 이용 로그인의 경우 이렇게 진행함 - 참고하기!
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if(account!==null){ // 이미 로그인 되어있을 시 바로 메인 액티비티로 이동
            moveMainPage(auth.currentUser)
        }
         */
    }
}