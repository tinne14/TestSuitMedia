package com.tinne14.testprojectsuitmedia.ui.second

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tinne14.testprojectsuitmedia.Constant
import com.tinne14.testprojectsuitmedia.Constant.EXTRA_NAME
import com.tinne14.testprojectsuitmedia.databinding.ActivitySecondScreenBinding
import com.tinne14.testprojectsuitmedia.ui.first.FirstScreenActivity
import com.tinne14.testprojectsuitmedia.ui.first.FirstScreenViewModel
import com.tinne14.testprojectsuitmedia.ui.third.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding
    private val viewModel = SecondScreenViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.barSecondScreen.apply {
            tvTitle.text = "Second Screen"
            back.setOnClickListener {
                finish()
            }
        }
        val name = intent.getStringExtra(EXTRA_NAME)
        if (name != null) {
            viewModel.setName(name)
        }
        viewModel.name.observe(this){
            binding.tvName.text = it
        }
        binding.tvSelectedUser.text = Constant.fullname

        binding.btnChooseUser.setOnClickListener {
            startActivity(Intent(this@SecondScreenActivity, ThirdScreenActivity::class.java))
        }
    }
    override fun onResume() {
        super.onResume()
        binding.tvSelectedUser.text = Constant.fullname ?: ""
    }
}