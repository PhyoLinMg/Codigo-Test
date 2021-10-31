package dev.linmg.codigo_code_test.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.linmg.codigo_code_test.R
import dev.linmg.codigo_code_test.databinding.FragmentDetailBinding
import dev.linmg.codigo_code_test.utils.StatefulData

@AndroidEntryPoint
class DetailFragment:Fragment(){
    private var _binding:FragmentDetailBinding?=null
    private val binding: FragmentDetailBinding get()= _binding!!


    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        setUpListeners()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }
    private fun setUpUi(){
        viewModel.movieDetails.observe(viewLifecycleOwner){ movieDetails->
            when(movieDetails.state){
                StatefulData.DataState.SUCCESS -> {
                    binding.layoutToolbar.isLoading = false
                    binding.data = movieDetails.data
                }
                StatefulData.DataState.ERROR -> {
                    binding.layoutToolbar.isLoading = false
                    showSnackBar(movieDetails.message.toString())
                }
                StatefulData.DataState.LOADING -> {
                    binding.layoutToolbar.isLoading = true
                }
            }

        }
    }
    private fun setUpListeners(){
        binding.layoutToolbar.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.layoutToolbar.btnFavourite.setOnClickListener {
            viewModel.onTapFavourite()
        }
    }
    private fun showSnackBar(msg : String) {
        Snackbar.make(requireView(), msg , Snackbar.LENGTH_SHORT).show()
    }
}