package com.example.cardgame.ui.level

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.cardgame.R
import com.example.cardgame.data.db.entities.Level
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class LevelFragment : Fragment() , KodeinAware{

    override val kodein by kodein()
    private lateinit var viewmodel : LevelViewMode
    private val factory : LevelViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_level, container, false)
         viewmodel = ViewModelProviders.of(this,factory).get(LevelViewMode::class.java)


        val btn1 = view.findViewById<Button>(R.id.btn1);
        btn1.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_levelFragment_to_levelOneFragment)
        })

        viewmodel.getLevelById(1).observe(this, Observer {
            val btn2 = view.findViewById<Button>(R.id.btn2);
            if (it!=null && it.finished==true){
                btn2.setOnClickListener(View.OnClickListener {
                    findNavController().navigate(R.id.action_levelFragment_to_levelTwoFragment)
                })
                btn2.alpha=1f
            }else{
                btn2.alpha=0.5f
            }
        })
        viewmodel.getLevelById(2).observe(this, Observer {
            val btn3 = view.findViewById<Button>(R.id.btn3);
            if (it!=null && it.finished==true){
                btn3.setOnClickListener(View.OnClickListener {
                    findNavController().navigate(R.id.action_levelFragment_to_levelThreeFragment)
                })
                btn3.alpha=1f
            }else{
                btn3.alpha=0.5f
            }
        })
        viewmodel.getLevelById(3).observe(this, Observer {
            val btn4 = view.findViewById<Button>(R.id.btn4);
            if (it!=null && it.finished==true){
                btn4.setOnClickListener(View.OnClickListener {
                    findNavController().navigate(R.id.action_levelFragment_to_levelFoureFragment)
                })
                btn4.alpha=1f
            }else{
                btn4.alpha=0.5f
            }

        })
        viewmodel.getLevelById(4).observe(this, Observer {
            val btn5 = view.findViewById<Button>(R.id.btn5);
            if (it!=null && it.finished==true){
                btn5.setOnClickListener(View.OnClickListener {
                    findNavController().navigate(R.id.action_levelFragment_to_levelfiveFragment)
                })
                btn5.alpha=1f
            }else{
                btn5.alpha=0.5f
            }

        })
        viewmodel.getLevelById(5).observe(this, Observer {
            val btn6 = view.findViewById<Button>(R.id.btn6);
            if (it!=null && it.finished==true){
                btn6.setOnClickListener(View.OnClickListener {
                    findNavController().navigate(R.id.action_levelFragment_to_levelSixFragment)
                })
                btn6.alpha=1f
            }else{
                btn6.alpha=0.5f
            }
        })


        val btnscore = view.findViewById<Button>(R.id.btnscore);
        btnscore.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_levelFragment_to_scoresFragment)
        })
        return view
    }
}
