package apps.sami.todofirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddNewTask : AppCompatActivity() {

    private lateinit var etTskName: EditText
    private lateinit var btnTask1: AppCompatButton
    private lateinit var dbRef: DatabaseReference
    private lateinit var btnBack2: AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_task)

        etTskName = findViewById(R.id.etTskName)
        btnTask1 = findViewById(R.id.btnTask1)
        btnBack2 = findViewById(R.id.btnBack2)
        btnBack2.setOnClickListener {
            val intent = Intent(this, TaskActivity::class.java)
            startActivity(intent)
            finish()
        }



      





    }


    }

