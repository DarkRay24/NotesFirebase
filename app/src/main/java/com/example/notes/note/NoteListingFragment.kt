package com.example.notes.note

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.notes.databinding.FragmentNoteListingBinding
import com.example.notes.util.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListingFragment : Fragment() {

    val TAG: String = "NoteListingFragment"

    private lateinit var binding: FragmentNoteListingBinding
    val viewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteListingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNotes()
        viewModel.notes.observe(viewLifecycleOwner, Observer { state ->
            when(state){
                is UiState.Loading -> {
                    Log.d(TAG, "Loading")
                }
                is UiState.Failure -> {
                    Log.d(TAG, state.error.toString())
                }
                is UiState.Success -> {
                    state.data.forEach { note ->
                        Log.d(TAG, note.toString())
                    }
                }
            }
        })

    }


}