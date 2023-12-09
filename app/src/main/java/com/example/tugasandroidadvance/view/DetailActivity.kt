package com.example.tugasandroidadvance.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.tugasandroidadvance.R
import com.example.tugasandroidadvance.database.DatabaseCrush
import com.example.tugasandroidadvance.databinding.ActivityDetailBinding
import com.example.tugasandroidadvance.entities.CrushEntity
import com.example.tugasandroidadvance.repositories.CrushRepository
import com.example.tugasandroidadvance.utils.ViewModelFactory
import com.example.tugasandroidadvance.viewmodels.CrushViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getId = intent.getStringExtra("ID_CRUSH")
        getData(crushViewModel(),getId!!.toInt())
        binding.btnEdit.setOnClickListener{
            //mengambil nilai dari inputan untuk mengetahui apakah inputan kosong
            val inputNama1 = binding.etNama1.text.toString().trim()
            val inputNohp1 = binding.etNohp1.text.toString().trim()
            val inputDeskripsi1 = binding.etDeskripsi1.text.toString().trim()

            //validasi input
            var isInputErr = false
            if (inputNama1.isEmpty()){
                isInputErr = true
                binding.etNama1.error = getString(R.string.err_nama)
            }
            if (inputNohp1.isEmpty()){
                isInputErr = true
                binding.etNohp1.error = getString(R.string.err_nohp)
            }
            if (inputDeskripsi1.isEmpty()){
                isInputErr = true
                binding.etDeskripsi1.error = getString(R.string.err_desc)
            }

            if (!isInputErr) {
                //Mengedit data ke database
                editData(crushViewModel(),getId!!.toInt())
            }
        }

        binding.btnKangen.setOnClickListener {
            //mengambil nohp
            val inputNohp1 = binding.etNohp1.text.toString().trim()
            val noWa = convertToInternationalFormat(inputNohp1)
            //membuka WA
            openWhatsApp(noWa)
        }
    }

    fun crushViewModel(): CrushViewModel {
        val database = DatabaseCrush(this)
        val repo = CrushRepository(database)
        val factory = ViewModelFactory(repo)
        return ViewModelProvider(this, factory)[CrushViewModel::class.java]
    }

    fun getData(viewModel: CrushViewModel,getId:Int) {

        viewModel.getCrushId(getId).observe(this) {
            binding.etNama1.setText(it.name)
            binding.etNohp1.setText(it.phonenumber)
            binding.etDeskripsi1.setText(it.description)
        }
    }

    fun editData(vm: CrushViewModel,id:Int){
        vm.updateData(
            CrushEntity(
                id = id,
                name = binding.etNama1.text.toString(),
                phonenumber = binding.etNohp1.text.toString(),
                description = binding.etDeskripsi1.text.toString()
            )
        ).let {
            val intent = Intent(this, BottomNavbarActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra("FRAGMENT_TO_SHOW", "HOME_FRAGMENT")
            startActivity(intent)
        }
    }

    fun openWhatsApp(nohp: String){
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://api.whatsapp.com/send?phone=$nohp")
            startActivity(intent)
        } catch (e: Exception) {
            // WhatsApp tidak terinstal atau terjadi kesalahan
            e.printStackTrace()
        }
    }

    fun convertToInternationalFormat(originalNumber: String): String {
        // Hapus karakter awal "0" jika ada
        val cleanedNumber = if (originalNumber.startsWith("0")) {
            originalNumber.substring(1)
        } else {
            originalNumber
        }

        // Tambahkan awalan "62"
        val internationalNumber = "62$cleanedNumber"
        return internationalNumber
    }
}