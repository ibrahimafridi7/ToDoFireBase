package apps.sami.todofirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddNewCategory : AppCompatActivity() {

    private lateinit var etEmpName: EditText
    private lateinit var btnSaveData: AppCompatButton
    private lateinit var btnBack: AppCompatButton
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_category)
        etEmpName = findViewById(R.id.etEmpName)
        btnSaveData = findViewById(R.id.btnSave)
        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        dbRef = FirebaseDatabase.getInstance().getReference("Category")

        btnSaveData.setOnClickListener {
            saveEmployeeData()
        }
        Log.d("btnSaveData", "e")
    }

    private fun saveEmployeeData() {

            val empName = etEmpName.text.toString()

            if (empName.isEmpty()) {
                etEmpName.error = "Please Enter Category"
            }

            val empId = dbRef.push().key!!

            val employee = DataClass(empId, empName)

            dbRef.child(empId).setValue(employee)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                    etEmpName.text.clear()


                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }

        }
    }