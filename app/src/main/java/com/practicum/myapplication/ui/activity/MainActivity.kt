package com.practicum.myapplication.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.practicum.myapplication.creator.AppCreator
import com.practicum.myapplication.creator.SearchViewModelFactory
import com.practicum.myapplication.ui.screen.SearchScreen
import com.practicum.myapplication.ui.view_model.SearchViewModel

class MainActivity : ComponentActivity() {

    private val searchViewModel by viewModels<SearchViewModel> {
        SearchViewModelFactory(AppCreator.tracksRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                SearchScreen(
                    modifier = Modifier.padding(innerPadding),
                    viewModel = searchViewModel
                )
            }
        }
    }
}
