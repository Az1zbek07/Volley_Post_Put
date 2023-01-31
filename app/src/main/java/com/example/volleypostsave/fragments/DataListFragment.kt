package com.example.volleypostsave.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.volleypostsave.R
import com.example.volleypostsave.adapter.DataAdapter
import com.example.volleypostsave.databinding.FragmentDataListBinding
import com.example.volleypostsave.model.Data
import com.example.volleypostsave.network.ResponseHandler
import com.example.volleypostsave.network.VolleyInstance
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DataListFragment : Fragment(R.layout.fragment_data_list) {
    private var _binding: FragmentDataListBinding? = null
    private val binding get() = _binding!!
    private val volleyInstance by lazy { VolleyInstance() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDataListBinding.bind(view)

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_dataListFragment_to_addFragment)
        }
        val dataAdapter = DataAdapter()
        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = dataAdapter
        }

        dataAdapter.onClick = {
            val bundle = bundleOf("id" to it)
            findNavController().navigate(R.id.action_dataListFragment_to_detailFragment, bundle)
        }

        var dataList: List<Data> = mutableListOf()

        volleyInstance.get(object : ResponseHandler{
            override fun onResponse(string: String) {
                val type: Type = object : TypeToken<List<Data>>() {}.type
                dataList = Gson().fromJson<List<Data>>(string, type)
            }

            override fun onError(string: String) {
                Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
            }
        })

        dataAdapter.submitList(dataList)
        Toast.makeText(requireContext(), dataList.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}