package apps.sami.todofirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var ctRecyclerView: RecyclerView
//    private lateinit var tvLoadingData: TextView
    private lateinit var ctList: ArrayList<DataClass>
    private lateinit var dbRef: DatabaseReference

    lateinit var firebaseAuth : FirebaseAuth
    private lateinit var addCategory : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()


        ctRecyclerView = findViewById(R.id.recyclerView)
        ctRecyclerView.layoutManager = GridLayoutManager(this,2)
        ctRecyclerView.setHasFixedSize(true)
//      tvLoadingData = findViewById(R.id.tvLoadingData)
        ctList = arrayListOf<DataClass>()
        getEmployeesData()

        //addcategory
        addCategory = findViewById(R.id.btnCategory)
            addCategory.setOnClickListener {
                val intent = Intent(this, AddNewCategory::class.java)
                startActivity(intent)
                Log.d("addCategory","e")
            }


        firebaseAuth = FirebaseAuth.getInstance()
//        findViewById<TextView>(R.id.txtSignOut).text= firebaseAuth.currentUser?.uid
        findViewById<Button>(R.id.btnSignOut).setOnClickListener {
            signOut()
        }
    }

    private fun getEmployeesData() {

        ctRecyclerView.visibility = View.GONE
//        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Category")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                ctList.clear()
                if (snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val empData = empSnap.getValue(DataClass::class.java)
                        ctList.add(empData!!)
                    }
                    val mAdapter = Adapter(ctList)
                    ctRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : Adapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@MainActivity, TaskActivity::class.java)


                            intent.putExtra("empId", ctList[position].empId)
                            intent.putExtra("empName", ctList[position].empName)
                            startActivity(intent)


                        }

                    })

                    ctRecyclerView.visibility = View.VISIBLE
//                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }




    private fun signOut(){
        firebaseAuth.signOut()
        finish()
    }
}