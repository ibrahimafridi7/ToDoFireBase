package apps.sami.todofirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatButton

class TaskActivity : AppCompatActivity() {
    private lateinit var btnBack1: AppCompatButton
    private lateinit var addTask : AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        btnBack1 = findViewById(R.id.btnBack1)
        btnBack1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        addTask = findViewById(R.id.btnTask)
        addTask.setOnClickListener {
            val intent = Intent(this, AddNewTask::class.java)
            startActivity(intent)
        }
    }
}