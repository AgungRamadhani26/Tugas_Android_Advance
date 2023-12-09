package com.example.tugasandroidadvance.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.tugasandroidadvance.utils.ViewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugasandroidadvance.adapters.CrushRvAdapter
import com.example.tugasandroidadvance.database.DatabaseCrush
import com.example.tugasandroidadvance.databinding.FragmentHomeBinding
import com.example.tugasandroidadvance.entities.CrushEntity
import com.example.tugasandroidadvance.repositories.CrushRepository
import com.example.tugasandroidadvance.view.AddActivity
import com.example.tugasandroidadvance.view.DetailActivity
import com.example.tugasandroidadvance.viewmodels.CrushViewModel

class HomeFragment : Fragment() {
    //Binding fragment home
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: CrushRvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout untuk fragment home
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        init(crushViewModel())

        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    fun init(vm: CrushViewModel) {
        changeData(vm) {
                list ->

            val layoutManager = LinearLayoutManager(requireContext())
            binding.rvList.layoutManager = layoutManager
            adapter = CrushRvAdapter(list, onDelete = { item ->
                vm.deleteData(item).let {
                    Toast.makeText(requireContext(),"Udah kehapus", Toast.LENGTH_SHORT).show()
                }
            }) {
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra("ID_CRUSH",it.toString())
                startActivity(intent)
            }
            adapter.notifyDataSetChanged()
            binding.rvList.adapter = adapter
        }
        onClick()
    }

    fun onClick() {
        binding.fabAdd.setOnClickListener {
            val intent = Intent(requireContext(), AddActivity::class.java)
            startActivity(intent)
        }
    }

    fun crushViewModel(): CrushViewModel {
        val database = DatabaseCrush(requireContext())
        val repo = CrushRepository(database)
        val factory = ViewModelFactory(repo)
        return ViewModelProvider(this, factory)[CrushViewModel::class.java]
    }

    fun changeData(vm: CrushViewModel, getData: (List<CrushEntity>) -> Unit) {
        vm.getAllCrushData().observe(viewLifecycleOwner) { list ->
            if (list.isNotEmpty()) {
                getData.invoke(list)
                binding.rvList.visibility = View.VISIBLE
                binding.fabAdd.visibility = View.VISIBLE
                binding.tvEmpty.visibility = View.GONE
            } else {
                binding.rvList.visibility = View.GONE
                binding.fabAdd.visibility = View.VISIBLE
                binding.tvEmpty.visibility = View.VISIBLE
            }
        }
    }
}