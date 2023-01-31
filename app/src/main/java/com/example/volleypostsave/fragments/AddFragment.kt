package com.example.volleypostsave.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.volleypostsave.R
import com.example.volleypostsave.databinding.FragmentAddBinding
import com.example.volleypostsave.model.Data
import com.example.volleypostsave.network.ResponseHandler
import com.example.volleypostsave.network.VolleyInstance
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class AddFragment : Fragment(R.layout.fragment_add) {
    private var data: Data? = null
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private val volleyInstance by lazy { VolleyInstance() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddBinding.bind(view)

        data = arguments?.getParcelable("data")

        if (data == null){
            binding.btnAdd.setText("Add")
        }else{
            binding.btnAdd.setText("Update")
        }

        binding.btnAdd.setOnClickListener{
            if (data == null){
                val name = binding.editFirstName.text.toString().trim()
                val lastName = binding.editSecondName.text.toString().trim()
                val email = binding.editEmail.text.toString().trim()
                if (name.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()){
                    val data = Data("", email, name, 0, lastName)
                    volleyInstance.post(data, object : ResponseHandler{
                        override fun onResponse(string: String) {
                            Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
                        }

                        override fun onError(string: String) {
                            Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
                        }
                    })
                    findNavController().popBackStack()
                }else{
                    Toast.makeText(requireContext(), "Please enter values", Toast.LENGTH_SHORT).show()
                }
            }else{
                val name = binding.editFirstName.text.toString().trim()
                val lastName = binding.editSecondName.text.toString().trim()
                val email = binding.editEmail.text.toString().trim()

                if (name.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()){
                    val data = Data("", email, name, 0, lastName)
                    volleyInstance.put(data, object : ResponseHandler{
                        override fun onResponse(string: String) {
                            Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
                        }

                        override fun onError(string: String) {
                            Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
                        }
                    })
                    findNavController().popBackStack()
                }else{
                    Toast.makeText(requireContext(), "Please enter values", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}