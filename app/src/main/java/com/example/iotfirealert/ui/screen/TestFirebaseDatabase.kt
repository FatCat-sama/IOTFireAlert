package com.example.iotfirealert.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun TestFirebaseDatabase() {
    var fbValue1 by remember { mutableStateOf("") }
    var fbValue2 by remember { mutableStateOf("") }

    var lcValue1 by remember { mutableStateOf("") }
    var lcValue2 by remember { mutableStateOf("") }

    val database = Firebase.database.reference

    @OptIn(DelicateCoroutinesApi::class)
    database.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            GlobalScope.launch {
                fbValue1 = snapshot.child("Android/key1").value.toString()
                fbValue2 = snapshot.child("Android/key2").value.toString()
            }
        }

        override fun onCancelled(error: DatabaseError) {
            fbValue1 = "Error"
            fbValue2 = "Error"
        }
    })

    @OptIn(ExperimentalMaterial3Api::class)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Value 1: $fbValue1",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            TextField(value = lcValue1, onValueChange = {
                lcValue1 = it
            })
        }

        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Value 2: $fbValue2",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            TextField(value = lcValue2, onValueChange = {
                lcValue2 = it
            })
        }

        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    setValue1(lcValue1)
                }
            ) {
                Text(text = "Set Value 1")
            }

            Button(
                onClick = {
                    setValue2(lcValue2)
                }
            ) {
                Text(text = "Set Value 2")
            }
        }
    }
}

private fun setValue1(value: String) {
    val database = Firebase.database
    val reference = database.reference

    reference.child("Android/key1").setValue(value)
}

private fun setValue2(value: String) {
    val database = Firebase.database
    val reference = database.reference

    reference.child("Android/key2").setValue(value)
}