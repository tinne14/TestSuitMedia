package com.tinne14.testprojectsuitmedia.ui.first

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.tinne14.testprojectsuitmedia.Constant.EXTRA_NAME
import com.tinne14.testprojectsuitmedia.R
import com.tinne14.testprojectsuitmedia.ui.second.SecondScreenActivity
import com.tinne14.testprojectsuitmedia.databinding.ActivityFirstScreenBinding

class FirstScreenActivity : AppCompatActivity() {

    private val viewModel = FirstScreenViewModel()

    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener {
            val textPalindrome = binding.edPalindrome.text.toString()
            when {
                textPalindrome.isEmpty() -> {
                    binding.tiPalindrome.error =
                        getString(R.string.please_fill_the_palindrome_section)
                }

                else -> {
                    viewModel.checkPalindrome(textPalindrome)
                    showAlertDialog(viewModel.isPalindrome)
                }
            }
        }
        binding.btnNext.setOnClickListener {
            val name = binding.edName.text.toString()
            when {
                name.isEmpty() -> {
                    binding.tiName.error = getString(R.string.please_fill_your_name)
                }

                else -> {
                    val intent = Intent(this@FirstScreenActivity, SecondScreenActivity::class.java)
                    intent.putExtra(EXTRA_NAME, name)
                    startActivity(intent)
                }
            }
        }
    }

    private fun showAlertDialog(isPalindrome: Boolean) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Palindrome Check")
        if (isPalindrome == true) {
            alertDialogBuilder.setMessage("isPalindrome")
        } else {
            alertDialogBuilder.setMessage("not palindrome")
        }

        alertDialogBuilder.setPositiveButton("OK") { dialogInterface: DialogInterface, i: Int ->
            // Perform action when OK button is clicked
            // You can add your code here
            dialogInterface.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}