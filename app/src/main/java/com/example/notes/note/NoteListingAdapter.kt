package com.example.notes.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.data.model.Note
import com.example.notes.databinding.ItemNoteLayoutBinding

class NoteListingAdapter(
    val onItemClicked: (Int, Note) -> Unit,
    val onEditClicked: (Int, Note) -> Unit,
    val onDeleteClicked: (Int, Note) -> Unit
)  : RecyclerView.Adapter<NoteListingAdapter.MyViewHolder>() {

    private var list: MutableList<Note> = arrayListOf()


    inner class MyViewHolder(val binding: ItemNoteLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Note){
            binding.apply {
                tvNoteId.text = item.id
                tvNoteBody.text = item.text
                btnEdit.setOnClickListener { onEditClicked.invoke(adapterPosition, item) }
                btnDelete.setOnClickListener { onDeleteClicked.invoke(adapterPosition, item) }
                itemLayout.setOnClickListener { onItemClicked.invoke(adapterPosition, item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = ItemNoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent,  false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    fun updateList(list: MutableList<Note>){
        this.list = list
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        list.removeAt(position)
        notifyDataSetChanged()
    }


}