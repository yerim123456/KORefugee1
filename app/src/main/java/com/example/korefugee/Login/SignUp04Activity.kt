package com.example.korefugee.Login

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.example.korefugee.MainActivity
import com.example.korefugee.R
import com.example.korefugee.databinding.ActivitySignUp03Binding
import com.example.korefugee.databinding.ActivitySignUp04Binding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class SignUp04Activity : AppCompatActivity() {
    // 뷰 바인딩을 위한 객체 획득
    lateinit var binding: ActivitySignUp04Binding
    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up04)
        binding = ActivitySignUp04Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // gallery request launcher..................
        // 되돌아 왔을 때 사후 처리
        val requestGalleryLauncher = registerForActivityResult(
            // intent 발생
            ActivityResultContracts.StartActivityForResult())
        {
            // call back 처리
            try {
                // inSampleSize 비율 계산, 지정
                // 사진 사이즈 줄이기
                val calRatio = calculateInSampleSize(
                    it.data!!.data!!,
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize)
                )
                // BitmapFactory 이용하여 이미지 줄여주기
                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio

                // 이미지 로딩
                var inputStream = contentResolver.openInputStream(it.data!!.data!!) // 로딩해야 하는 이미지 파일
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option) // 넘어오는 이미지를 갖고 bitmap 파일 만들기, option의 사이즈 적용해서
                inputStream!!.close()
                inputStream = null
                bitmap?.let {
                    // 화면에 띄우기
                    binding.uploadprofileImageView.setImageBitmap(bitmap)
                } ?: let {
                    Log.d("tagg", "bitmap null")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // 갤러리 버튼 클릭 시
        binding.profilephotoguideGallery.setOnClickListener {
            // 갤러리 접근 권한 물어보기 및 얻어오기
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)

            //gallery app........................
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent) // 이거 실행되면서 call back 부분 실행됨
        }

        // camera request launcher.................
        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            // 카메라 앱
            val calRatio = calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                binding.uploadprofileImageView.setImageBitmap(bitmap)
            }
        }

        binding.profilephotoguideCamera.setOnClickListener {
            // 카메라 앱
            // 파일 준비
            val timeStamp: String =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())  // 사진 파일 이름 지정
            val storageDir: File? =
                getExternalFilesDir(Environment.DIRECTORY_PICTURES) // 외장 메모리 공간에 저장하겠다
            val file = File.createTempFile(
                "JPEG_${timeStamp}_", // 이름
                ".jpg", // 형식
                storageDir // 저장될 공간
            )
            filePath = file.absolutePath // 절대 경로로
            val photoURI: Uri = FileProvider.getUriForFile( // 갤러리앱에 전달하기 위한 uri 객체
                this,
                "com.example.korefugee.Login.fileprovider", file
            )

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) // 카메라 앱 구동
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI) // 우리 파일에 write 해줌 - 파일 정보 넘기기
            requestCameraFileLauncher.launch(intent)
        }
        // 결과 저장 및 넘기기
        binding.skipButton.setOnClickListener{
                val intent = Intent(this,MainActivity::class.java)
            /*
                intent.putExtra("email", email)
                intent.putExtra("password", password)
                intent.putExtra("position", position)
                intent.putExtra("name", name)
                intent.putExtra("birth", birth)
                intent.putExtra("gender", gender)
                intent.putExtra("field", field)
                intent.putExtra("country", binding.countryTextView2.text.toString())
                intent.putExtra("korean", binding.koreanskilsTextView2.text.toString())
                intent.putExtra("imgUrl", "")
             */
                startActivity(intent)
            }
    }

    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try {
            var inputStream = contentResolver.openInputStream(fileUri)

            //inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            //로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //비율 계산........................
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        //inSampleSize 비율 계산
        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}