package com.fosusstart.task2.ui.main.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.fosusstart.task2.R

class NavAFragment : Fragment(){

    companion object {
        fun newInstance() = NavAFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layoutInflater = inflater.inflate(R.layout.nav_a_fragment, container, false)
        // При клике переходим на следующий фрагмент
        layoutInflater.findViewById<Button>(R.id.goto_next_fragment_button)
            .setOnClickListener {
                findNavController().navigate(R.id.action_navAFragment_to_navBFragment)
            }
        return layoutInflater
    }
}