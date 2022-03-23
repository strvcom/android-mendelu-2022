package com.strv.archdemo.ui.counter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.strv.archdemo.databinding.FragmentCounterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CounterFragment : Fragment() {
    private var _binding: FragmentCounterBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CounterViewModel>()

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
        setupBinding()
    }

    private fun setupBinding() {
        // As we cannot touch the view from ViewModel, this is the best place to do any View <-> ViewModel communication
        binding.message.text = viewModel.title

        // Handle plus and minus click logic
        binding.buttonMinus.setOnClickListener { viewModel.onMinusClick() }
        binding.buttonPlus.setOnClickListener { viewModel.onPlusClick() }

        binding.buttonBlock.setOnClickListener { viewModel.onBlockingCallClick() }
        binding.buttonAsync.setOnClickListener { viewModel.onAsyncCallClick() }

        // Update the UI based on ViewModel data change
        lifecycleScope.launch {
            viewModel.clickCount.collect { count ->
                binding.clickCount.text = count.toString()
            }
        }

        lifecycleScope.launch {
            viewModel.callStatusText.collect { status ->
                binding.callStatus.text = status
            }
        }
    }
}