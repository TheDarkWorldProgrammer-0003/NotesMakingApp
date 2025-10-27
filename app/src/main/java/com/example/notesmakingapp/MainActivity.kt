package com.example.notesmakingapp


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(), INotesRVAdapter {
    // 1. Implement the click listener interface from the adapter
    lateinit var viewModel: NoteViewModel
    lateinit var submitButton: Button

    // 2. Declare variables for the RecyclerView and EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var input: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        submitButton = findViewById(R.id.addButton)
        input = findViewById(R.id.input)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        submitButton.setOnClickListener {
            submitData(it)
        }


        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            NoteViewModelFactory(application)
        ).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)

            }
        })

    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.text} Deleted", Toast.LENGTH_LONG).show()

    }

    fun submitData(view: View) {
        val noteText = input.text.toString()
        if (noteText.isNotEmpty()) {
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this, "$noteText Inserted", Toast.LENGTH_LONG).show()
            input.text.clear()
        }

    }

}
