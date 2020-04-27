package com.example.cardgame.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cardgame.R
import com.example.cardgame.data.db.entities.Photo


class CardAdapter(
    val context: Context,
    var List:List<Photo>,
    val itemClickListener: OnItemClickListener
): RecyclerView.Adapter<CardAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_cell_image,parent,false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return List.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var path = ""
        if (List[position].clickt==false){
            path= "https://images.squarespace-cdn.com/content/v1/5abd8db4620b85fa99f15131/15423035617" +
                    "14-6AVVNV48KEV7U4HH9J84/ke17ZwdGBToddI8pDm48kHWzSshJXR3awtTpu27O8pAUqsxRUqqbr1mOJY" +
                    "KfIPR7LoDQ9mXPOjoJoqy81S2I8PaoYXhp6HxIwZIk7-Mi3Tsic-L2IOPH3Dwrhl-Ne3Z2HJVGOXx9WFeQD" +
                    "YkSiL52RCeFwTXzKLXN-" +
                    "j1fKEB_iLMKMshLAGzx4R3EDFOm1kBS/Card+Back+2.0+-+Poker+Size+-+Blue_shw.png"
        }
        else if(List[position].clickt==true || List[position].matched==true) {
            path = "https://farm"+List[position].farm+
                    ".staticflickr.com/"+List[position].server+
                    "/"+List[position].id+
                    "_"+List[position].secret+".jpg"
        }
        Glide.with(context).load(path)
            .into(holder.img)
        //set onclickListener
        holder.bind(List[position],itemClickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById(R.id.img) as ImageView
        fun bind(photo: Photo,clickListener: OnItemClickListener)
        {
            itemView.setOnClickListener {
                clickListener.onItemClicked(photo)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClicked(photo: Photo)
    }
}