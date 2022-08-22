package com.example.dobcalkot

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDateTV : TextView? = null
    private var ageInMinutesTV : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePicker : Button = findViewById(R.id.datePicker)
        selectedDateTV = findViewById(R.id.selectedDateTV)
        ageInMinutesTV = findViewById(R.id.ageInMintuesTV)
            datePicker.setOnClickListener{
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val date = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ _, selectedYear, selectedMonth, selectedDayOfMonth ->
            //  Toast.makeText(this,"Year was $selectedYear, month was ${selectedMonth+1}, day of month was $selectedDayOfMonth",Toast.LENGTH_LONG).show()

            val selectedDate =  "$selectedDayOfMonth/ ${selectedMonth+1}/ $selectedYear"
            selectedDateTV?.text = selectedDate
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val  theDate = sdf.parse(selectedDate)
            theDate?.let { val selectedDateInMinutes = theDate.time/60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let { val currentDateInMintues = currentDate.time /60000
                    val differenceInMinutes = currentDateInMintues - selectedDateInMinutes
                    ageInMinutesTV?.text  = differenceInMinutes.toString()
                }
            }
        },
            year,
            month,
            date )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}