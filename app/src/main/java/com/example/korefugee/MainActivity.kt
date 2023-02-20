package com.example.korefugee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import com.example.korefugee.Community.CommunityFragment
import com.example.korefugee.Guide.GuideFragment
import com.example.korefugee.Map.MapFragment
import com.example.korefugee.Voca.VocaFragment
import com.example.korefugee.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener  {

    // 뷰 바인딩을 위한 객체 획득
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩 기본 작업
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 첫 화면 설정 위한 변수 설정
        val fragmentmanger: FragmentManager = supportFragmentManager
        val guidefragment = GuideFragment()
        val transaction = fragmentmanger.beginTransaction()

        // 첫 화면 설정
        transaction.add(R.id.main_content, guidefragment)
        transaction.commit()


        // 바텀 네비게이션과 바인딩하여
        binding.bottomNavigation.setOnItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(p0 : MenuItem): Boolean { // 바텀 네비게이션 클릭 시 이동
        when(p0.itemId){
            R.id.action_guide->{
                var guideFragment = GuideFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,guideFragment).commit()
                return true
            }
            R.id.action_map->{
                var mapFragment = MapFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,mapFragment).commit()
                return true
            }

            R.id.action_voca->{
                var vocaFragment = VocaFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,vocaFragment).commit()
                return true
            }
            R.id.action_community->{
                var communityFragment = CommunityFragment()
                supportFragmentManager.beginTransaction().replace(R.id.main_content,communityFragment).commit()
                return true
            }
        }
        return false
    }
}