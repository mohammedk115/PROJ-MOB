package com.example.project

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    var btnStartService: Button? = null
    var btnStopService: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnStartService:Button = findViewById(R.id.buttonStartService)
        val btnStopService:Button = findViewById(R.id.buttonStopService)
        btnStartService.setOnClickListener(View.OnClickListener { startService() })
        btnStopService.setOnClickListener(View.OnClickListener { stopService() })

        val resultTV: TextView = findViewById(R.id.textResult)
        val spinnerVal: Spinner = findViewById(R.id.spinnerV)
        val options = arrayOf("bargar","pizza")
        spinnerVal.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options )








        spinnerVal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2==0)
                {
                    val fragS = SandwichFragment()
                    fragS.show(supportFragmentManager, "Sandwich Dialog")
                }
                else if(p2==1)
                {
                    val fragP = PizzaFragment()
                    fragP.show(supportFragmentManager, "Pizza Dialog")
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
    fun receiveFeedback(feedback: String){
        val resultTV: TextView = findViewById(R.id.textResult)

        resultTV.text = feedback;
    }



    fun startService() {
        val serviceIntent = Intent(this, ForegroundService::class.java)
        serviceIntent.putExtra("inputExtra", "your order will be delivered")
        ContextCompat.startForegroundService(this, serviceIntent)    }
    fun stopService() {
        val serviceIntent = Intent(this, ForegroundService::class.java)
        stopService(serviceIntent)
    }



    
    fun onClickAddName(view: View?) {
        val values = ContentValues()
        values.put(
            EmployeeYear.NAME,
            (findViewById<View>(R.id.editText2) as EditText).text.toString()
        )
        values.put(
            EmployeeYear.GRADE,
            (findViewById<View>(R.id.editText3) as EditText).text.toString()
        )
        val uri = contentResolver.insert(
            EmployeeYear.CONTENT_URI, values
        )
        Toast.makeText(baseContext, uri.toString(), Toast.LENGTH_LONG).show()
       }


        fun onClickRetrieveStudents(view: View?) {

            val URL = "content://com.example.MyApplication.EmployeeYear"
            val students = Uri.parse(URL)

            var c = contentResolver.query(students, null, null, null,null)

             if (c != null) {
                 if (c?.moveToFirst()) {
             do {
             Toast.makeText(this, c.getString(c.getColumnIndex( EmployeeYear._ID)) +
            ", " + c.getString(c.getColumnIndex( EmployeeYear.NAME)) + ", "
            + c.getString(c.getColumnIndex( EmployeeYear.GRADE)), Toast.LENGTH_SHORT).show()
        } while (c.moveToNext())
    }
}
}



}









