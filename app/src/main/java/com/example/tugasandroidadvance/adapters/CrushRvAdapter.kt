package com.example.tugasandroidadvance.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasandroidadvance.databinding.ItemCrushBinding
import com.example.tugasandroidadvance.entities.CrushEntity

class CrushRvAdapter(private val list: List<CrushEntity>, val onDelete:(CrushEntity) -> Unit, val onClick:(Int)-> Unit) : RecyclerView.Adapter<CrushRvAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemCrushBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCrushBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.tvNama.text = this.name
                binding.tvNohp.text = this.phonenumber
                binding.tvDeskripsi.text = this.description
                binding.cdCard.setOnClickListener {
                    onClick.invoke(this.id)
                }
                binding.ivTrash.setOnClickListener {
                    onDelete.invoke(this)
                }
            }
        }
    }
}