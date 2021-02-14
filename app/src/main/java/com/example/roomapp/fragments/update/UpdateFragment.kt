package com.example.roomapp.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomapp.R
import com.example.roomapp.databinding.FragmentUpdateBinding
import com.example.roomapp.model.User
import com.example.roomapp.viewmodel.UserViewModel


class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel: UserViewModel

    // Nav args
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        _binding = FragmentUpdateBinding.inflate(layoutInflater)

        binding.tilUpdateNom.editText?.setText(args.currentUser.nom)
        binding.tilUpdatePrenom.editText?.setText(args.currentUser.prenom)
        binding.tilUpdateAge.editText?.setText(args.currentUser.age.toString())

        binding.btnUpdate.setOnClickListener {

        }

        return binding.root
    }

    private fun updateItem() {
        val nom = binding.tilUpdateNom.editText?.text.toString()
        val prenom = binding.tilUpdatePrenom.editText?.text.toString()
        val age = Integer.parseInt(binding.tilUpdateAge.editText?.text.toString())

        if(inputCheck(nom, prenom, binding.tilUpdateAge.editText!!.text)) {
            // Créer un user
            val updateUser = User(args.currentUser.id, nom, prenom, age)
            // Mettre à jour l'utilisateur
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_LONG)
                .show()
            // Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Remplisser tout les champs", Toast.LENGTH_LONG)
                .show()
        }

    }

    private fun inputCheck(nom: String, prenom: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(nom)
                && TextUtils.isEmpty(prenom)
                && age.isEmpty())
    }


}