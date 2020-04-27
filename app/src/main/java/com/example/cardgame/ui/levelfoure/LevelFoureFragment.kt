package com.example.cardgame.ui.levelfoure

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.cardgame.R
import com.example.cardgame.data.db.entities.Level
import com.example.cardgame.data.db.entities.Photo
import com.example.cardgame.ui.adapters.CardAdapter
import com.example.cardgame.ui.level.ErrorListener
import com.example.cardgame.ui.level.LevelViewMode
import com.example.cardgame.ui.level.LevelViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class LevelFoureFragment : Fragment(), KodeinAware, CardAdapter.OnItemClickListener, ErrorListener {

    override val kodein by kodein()
    private lateinit var viewmodel: LevelViewMode
    private val factory: LevelViewModelFactory by instance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_level_foure, container, false)
        var score = view.findViewById<TextView>(R.id.textView2)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)

        viewmodel = ViewModelProviders.of(this, factory).get(LevelViewMode::class.java)

        viewmodel.getLevelById(4).observe(this, Observer {
            if (it == null) {
                viewmodel.insertSaveUpdateLevel(Level(4, 0, finished = false))
            } else if (it.finished == true) {
                restertGame()
            }
        })
        viewmodel.getLevelById(4).observe(this, Observer {
            if (it != null) {
                score.text = it.score.toString()
            }
        })

        viewmodel.getImages(5).observe(this, Observer {
            val imageAdapter = CardAdapter(context!!, it!!, this)
            recyclerView.apply {
                layoutManager = GridLayoutManager(context, 4)
                setHasFixedSize(true)
                adapter = imageAdapter
            }
        })


        viewmodel.getListOfPhoto().observe(this, Observer {
            if (it.size == 10 && score.text != "0") {
                var s: Int = score.text.toString().toInt()
                viewmodel.insertSaveUpdateLevel(Level(4, s, finished = true))
            }
        })
        return view
    }

    override fun onItemClicked(photo: Photo) {
        viewmodel.imageClicked(photo, 4)

    }

    fun restertGame() {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle(R.string.finished)
                setPositiveButton(R.string.otherlevel,
                    DialogInterface.OnClickListener { dialog, id ->
                        activity!!.onBackPressed()
                    })
                setNegativeButton(R.string.restart,
                    DialogInterface.OnClickListener { dialog, id ->
                        viewmodel.insertSaveUpdateLevel(Level(4, 0, finished = false))
                        viewmodel.retartgame()
                    })
            }
            builder.create()
        }
        alertDialog!!.show()
    }

    override fun onFailure(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}
