package com.example.project

import android.content.ContentValues
import android.content.Intent
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
        Add a new student record
        val values = ContentValues()
        values.put(
            StudentsProvider.NAME,
            (findViewById<View>(R.id.editText2) as EditText).text.toString()
        )
        values.put(
            StudentsProvider.GRADE,
            (findViewById<View>(R.id.editText3) as EditText).text.toString()
        )
        val uri = contentResolver.insert(
            StudentsProvider.CONTENT_URI, values
        )
        Toast.makeText(baseContext, uri.toString(), Toast.LENGTH_LONG).show()
       }

}







