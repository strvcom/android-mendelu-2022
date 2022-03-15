package com.strv.archdemo.ui.counter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.strv.archdemo.databinding.FragmentCounterBinding

class CounterFragment : Fragment() {
    private var _binding: FragmentCounterBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CounterViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCounterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // As we cannot touch the view from ViewModel, this is the best place to do any View <-> ViewModel communication.
        binding.message.text = viewModel.title
    }
}