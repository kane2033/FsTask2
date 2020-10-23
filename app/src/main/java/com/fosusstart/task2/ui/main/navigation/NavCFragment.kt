package com.fosusstart.task2.ui.main.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.fosusstart.task2.R

class NavCFragment : Fragment(){

    companion object {
        fun newInstance() = NavCFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layoutInflater = inflater.inflate(R.layout.nav_c_fragment, container, false)
        // Переходим на первый фрагмент
        layoutInflater.findViewById<Button>(R.id.goto_next_fragment_button)
            .setOnClickListener {
                findNavController().navigate(R.id.action_navCFragment_to_navAFragment)
            }
        return layoutInflater
    }
}