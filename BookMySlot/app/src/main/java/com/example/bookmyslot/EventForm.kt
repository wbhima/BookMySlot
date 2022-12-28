package com.example.bookmyslot

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class EventForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_form)
        val register = findViewById<Button>(R.id.button)
        val nameofu = findViewById<EditText>(R.id.nameofu)
        val emailofu = findViewById<EditText>(R.id.emailofu)
        val noofu = findViewById<EditText>(R.id.noofu)
        val ss:String = intent.getStringExtra("name").toString()

        register.setOnClickListener {

            val name = nameofu.text.toString()
            val email = emailofu.text.toString()
            val no = noofu.text.toString()

            var fStore: FirebaseFirestore? = null
            fStore = FirebaseFirestore.getInstance()
            val documentReference: DocumentReference = fStore.collection("users").document((name+"|"+ss))
            val user: MutableMap<String, Any> = HashMap()
            user["name"] = name
            user["email"] = email
            user["phone"] = no
            user["eventname"] = ss
            documentReference.set(user).addOnFailureListener { e ->
                Log.d(
                    ContentValues.TAG,
                    "onFailure : $e"
                )
            }
            intent = Intent(applicationContext, TIcket::class.java)
            intent.putExtra("username", name)
            intent.putExtra("useremail", email)
            intent.putExtra("userno", no)
            intent.putExtra("eventname", ss)
            startActivity(intent)

        }
    }
}