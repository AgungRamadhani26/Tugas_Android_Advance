package com.example.tugasandroidadvance.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.tugasandroidadvance.R
import com.example.tugasandroidadvance.database.DatabaseCrush
import com.example.tugasandroidadvance.databinding.ActivityAddBinding
import com.example.tugasandroidadvance.entities.CrushEntity
import com.example.tugasandroidadvance.fragment.HomeFragment
import com.example.tugasandroidadvance.repositories.CrushRepository
import com.example.tugasandroidadvance.utils.ViewModelFactory
import com.example.tugasandroidadvance.viewmodels.CrushViewModel

class AddActivity : AppCompatActivity() {
    //binding
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            //mengambil nilai dari inputan untuk mengetahui apakah inputan kosong
            val inputNama = binding.etNama.text.toString().trim()
            val inputNohp = binding.etNohp.text.toString().trim()
            val inputDeskripsi = binding.etDeskripsi.text.toString().trim()

            //validasi input
            var isInputErr = false
            if (inputNama.isEmpty()){
                isInputErr = true
                binding.etNama.error = getString(R.string.err_nama)
            }
            if (inputNohp.isEmpty()){
                isInputErr = true
                binding.etNohp.error = getString(R.string.err_nohp)
            }
            if (inputDeskripsi.isEmpty()){
                isInputErr = true
                binding.etDeskripsi.error = getString(R.string.err_desc)
            }

            if (!isInputErr) {
                //Mengirim data ke database
                addData(crushViewModel())
            }
        }
    }

    fun crushViewModel(): CrushViewModel {
        val database = DatabaseCrush(this)
        val repo = CrushRepository(database)
        val factory = ViewModelFactory(repo)
        return ViewModelProvider(this, factory)[CrushViewModel::class.java]
    }

    fun addData(vm:CrushViewModel) {
        vm.insertData(
            CrushEntity(
                name = binding.etNama.text.toString(),
                phonenumber = binding.etNohp.text.toString(),
                description = binding.etDeskripsi.text.toString()
            )
        ).let {
            val intent = Intent(this, BottomNavbarActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra("FRAGMENT_TO_SHOW", "HOME_FRAGMENT")
            startActivity(intent)
        }
    }
}