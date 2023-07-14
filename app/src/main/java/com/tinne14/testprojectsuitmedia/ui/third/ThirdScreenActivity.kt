package com.tinne14.testprojectsuitmedia.ui.third

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.tinne14.testprojectsuitmedia.LoadingStateAdapter
import com.tinne14.testprojectsuitmedia.data.ApiConfig
import com.tinne14.testprojectsuitmedia.databinding.ActivityThirdScreenBinding

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private val viewModel = ThirdScreenViewmodel(ApiConfig.getApiService())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.barThirdScreen.apply {
            tvTitle.text = "Third Screen"
            back.setOnClickListener {
                finish()
            }
        }

        binding.rvUser.layoutManager = LinearLayoutManager(this)
        val adapter = UserAdapter()

        binding.rvUser.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )

        viewModel.getUser().observe(this) {
            adapter.submitData(lifecycle, it)
        }
        
        adapter.addLoadStateListener {
            when(it.source.refresh){
                is LoadState.Loading -> {
                    binding.progressBar.visibility = VISIBLE
                }
                is LoadState.NotLoading -> {
                    binding.progressBar.visibility = INVISIBLE
                }
                is LoadState.Error -> {
                    binding.progressBar.visibility = INVISIBLE
                    Toast.makeText(this@ThirdScreenActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
            if (it.source.refresh is LoadState.NotLoading && it.append.endOfPaginationReached && adapter.itemCount < 1) {
                binding.rvUser.isVisible = false
                binding.empty.root.isVisible = true
            } else {
                binding.rvUser.isVisible = true
                binding.empty.root.isVisible= false
            }
        }

        binding.refresh.setOnRefreshListener {
            viewModel.getUser().observe(this) {
                adapter.submitData(lifecycle, it)
            }
            binding.refresh.isRefreshing = false
        }

    }
}