package com.example.notes.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.notes.data.model.Note
import com.example.notes.databinding.FragmentNoteDetailBinding
import com.example.notes.util.UiState
import com.example.notes.util.hide
import com.example.notes.util.show
import com.example.notes.util.toast
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {

    val TAG: String = "NoteDetailFragment"

    private lateinit var binding: FragmentNoteDetailBinding
    private val viewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            if(validation()){
                viewModel.addNote(
                    Note(
                        id = "",
                        text = binding.etNoteMsg.text.toString(),
                        date = Date()
                    )
                )
            }
        }

        viewModel.addNote.observe(viewLifecycleOwner, Observer { state ->
            when(state){
                is UiState.Loading -> {
                    binding.btnProgressBar.show()
                }
                is UiState.Failure -> {
                    binding.btnProgressBar.hide()
                    toast(state.error)
                }
                is UiState.Success -> {
                    binding.btnProgressBar.hide()
                    toast(state.data)
                }
            }
        })


    }

    private fun validation(): Boolean{
        var isValid = true
        if(binding.etNoteMsg.text.toString().isEmpty()){
            isValid = false
            toast("Enter Message")
        }
        return isValid
    }

}