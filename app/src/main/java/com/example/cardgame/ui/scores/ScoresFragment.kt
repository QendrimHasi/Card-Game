package com.example.cardgame.ui.scores

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.cardgame.R
import com.example.cardgame.ui.adapters.ScoreAdapter
import com.example.cardgame.ui.level.LevelViewMode
import com.example.cardgame.ui.level.LevelViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ScoresFragment : Fragment() , KodeinAware {

    override val kodein by kodein()
    private lateinit var viewmodel : LevelViewMode
    private val factory : LevelViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scores, container, false)
        viewmodel = ViewModelProviders.of(this,factory).get(LevelViewMode::class.java)
        val recylerview = view.findViewById<RecyclerView>(R.id.recylerview)

        viewmodel.getAllLevels().observe(this, Observer {
            val scoreadapter= ScoreAdapter(it)
            recylerview.apply {
                layoutManager=GridLayoutManager(context,1)
                setHasFixedSize(true)
                adapter=scoreadapter
            }
        })

        return view
    }
}
