package com.example.cardgame.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cardgame.R
import com.example.cardgame.data.db.entities.Level
import com.example.cardgame.data.db.entities.Photo


class ScoreAdapter(
    var List:List<Level>
): RecyclerView.Adapter<ScoreAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_cell_score,parent,false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return List.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.lev.text=List[position].id.toString()
        holder.score.text=List[position].score.toString()
        if (List[position].finished==false){
            holder.finished.text="NO"
        }else{
            holder.finished.text="Yes"
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lev = itemView.findViewById(R.id.level) as TextView
        val score = itemView.findViewById(R.id.score) as TextView
        val finished = itemView.findViewById(R.id.finished) as TextView
    }
}