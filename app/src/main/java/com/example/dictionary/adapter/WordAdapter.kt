package com.example.dictionary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.Model.Model
import com.example.dictionary.R
import com.example.dictionary.database.Database
import com.example.dictionary.databinding.ItemWordsBinding

class WordAdapter : RecyclerView.Adapter<WordAdapter.ViewHolder>() {
    var data = ArrayList<Model>()
    lateinit var OnitemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemWordsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private var clickFavouriteImage: ((position: Int) -> Unit)? = null

    fun setClickFavouriteImage(f: (position: Int) -> Unit) {
        clickFavouriteImage = f
    }

    interface OnItemClickListener {
        fun onClick(position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.OnitemClickListener = onItemClickListener
    }

    fun addAllWords(list:ArrayList<Model>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(var binding: ItemWordsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Model) {
            binding.word.text = data.word
            binding.translate.text = data.translate
            binding.favourite.setImageResource(
                if (data.favourite == 1) {
                    R.drawable.ic_favourite
                } else {
                    R.drawable.ic_star
                }
            )
            binding.favourite.setOnClickListener {
                clickFavouriteImage?.invoke(layoutPosition)
            }
        }
    }
}