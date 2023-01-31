package com.example.volleypostsave.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.volleypostsave.R
import com.example.volleypostsave.databinding.FragmentDetailBinding
import com.example.volleypostsave.model.Data
import com.example.volleypostsave.network.ResponseHandler
import com.example.volleypostsave.network.VolleyInstance
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val volleyInstance by lazy { VolleyInstance() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        val id = arguments?.getInt("id")

        var data: Data = Data("", "", "", 0, "")
        volleyInstance.getById(id!!, object : ResponseHandler{
            override fun onResponse(string: String) {
                val type = object : TypeToken<Data>() {}.type
                data = Gson().fromJson(string, type)
            }

            override fun onError(string: String) {
                Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
            }
        })

        binding.btnPut.setOnClickListener{
            val bundle = bundleOf("data" to data)
            findNavController().navigate(R.id.action_detailFragment_to_addFragment, bundle)
        }

        binding.btnDelete.setOnClickListener{
            volleyInstance.delete(id!!, object : ResponseHandler{
                override fun onResponse(string: String) {
                    Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
                }

                override fun onError(string: String) {
                    Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}