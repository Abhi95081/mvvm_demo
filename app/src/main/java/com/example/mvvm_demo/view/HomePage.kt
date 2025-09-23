package com.example.mvvm_demo.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mvvm_demo.viewmodel.HomeViewModel

@Composable
fun MainScreen(viewModel: HomeViewModel) {

    val userdata = viewModel.userData.observeAsState()
    val isLoading = viewModel.isLoading.observeAsState(false)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { viewModel.fetchUserData() }) {
            Text("Get Data")
        }

        if (isLoading.value) {
            CircularProgressIndicator()
        } else {
            userdata.value?.let { user ->
                Text("Name: ${user.name}")
                Text("Age: ${user.age}")
            }
        }
    }
}
