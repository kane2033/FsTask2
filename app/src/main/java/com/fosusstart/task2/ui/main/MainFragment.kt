package com.fosusstart.task2.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.fosusstart.task2.R

class MainFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layoutInflater = inflater.inflate(R.layout.main_fragment, container, false)
        layoutInflater.findViewById<Button>(R.id.goto_navigation_button).setOnClickListener(this)
        layoutInflater.findViewById<Button>(R.id.goto_background_button).setOnClickListener(this)
        layoutInflater.findViewById<Button>(R.id.goto_phonebook_button).setOnClickListener(this)
        return layoutInflater
    }

    override fun onClick(view: View?) {
        val navController = findNavController()
        when (view?.id) {
            R.id.goto_navigation_button -> navController.navigate(R.id.action_mainFragment_to_navAFragment)
            R.id.goto_background_button -> navController.navigate(R.id.action_mainFragment_to_backgroundFragment)
            R.id.goto_phonebook_button -> navController.navigate(R.id.action_mainFragment_to_phonebookFragment)
        }
    }
}