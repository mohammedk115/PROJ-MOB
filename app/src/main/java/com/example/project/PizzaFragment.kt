package com.example.project
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import android.widget.*
class PizzaFragment {

    class PizzaFragment:DialogFragment(R.layout.fragment_pizza) {

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            val cancelbt : Button = view.findViewById(R.id.cancelbtn);
            val submitbt : Button = view.findViewById(R.id.submitBtn);
            val radioGroup = view.findViewById<RadioGroup>(R.id.pizzaRadioGroup)
            cancelbt.setOnClickListener{
                dismiss()
            }
            submitbt.setOnClickListener {
                val selectedOption: Int = radioGroup.checkedRadioButtonId
                val radioButton = view.findViewById<RadioButton>(selectedOption)
                Toast.makeText(context,radioButton.text,Toast.LENGTH_SHORT).show()
                val m1:MainActivity = getActivity() as MainActivity
                m1.receiveFeedback(radioButton.text.toString())

                dismiss()
            }
        }
    }
}