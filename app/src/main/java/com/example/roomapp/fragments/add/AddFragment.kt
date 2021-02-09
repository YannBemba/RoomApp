package com.example.roomapp.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.data.User
import com.example.roomapp.data.UserViewModel
import com.example.roomapp.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(layoutInflater)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnAjouter.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {
        val nom = binding.tilNom.editText?.text.toString()
        val prenom = binding.tilPrenom.editText?.text.toString()
        val age = binding.tilAge.editText!!.text

        if(inputCheck(nom, prenom, age)) {
            // Créer un utilisateur
            val user = User(0, nom, prenom, Integer.parseInt(age.toString()))
            // Ajouter à la db
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Ajouté avec succès !", Toast.LENGTH_LONG)
                .show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Veuillez remplir tous les champs !", Toast.LENGTH_LONG)
                .show()
        }

    }

    private fun inputCheck(nom: String, prenom: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(nom)
                && TextUtils.isEmpty(prenom)
                && age.isEmpty())
    }

}