package my.edu.tarc.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Linking UI to code
        val imageViewBMI: ImageView = findViewById(R.id.imageViewBMI)

        val textViewBMI: TextView = findViewById(R.id.textViewBMI)
        val textViewStatus: TextView = findViewById(R.id.textViewStatus)

        val editTextWeight: EditText = findViewById(R.id.editTextWeight)
        val editTextHeight: EditText = findViewById(R.id.editTextHeight)

        val buttonCalculate: Button = findViewById(R.id.buttonCalculate)
        val buttonReset: Button = findViewById(R.id.buttonReset)

        buttonCalculate.setOnClickListener {
            if (editTextWeight.text.isEmpty()) {
                editTextWeight.setError(getString(R.string.value_required))
                return@setOnClickListener //terminate the program
            }
            if (editTextHeight.text.isEmpty()) {
                editTextHeight.setError(getString(R.string.value_required))
                return@setOnClickListener //terminate the program
            }

            var weight = editTextWeight.text.toString().toFloat()
            var height = editTextHeight.text.toString().toFloat()
            var bmi = weight / (height / 100).pow(2)

            //Body Mass Index : 18.24
            textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), bmi)
            if (bmi < 18.5) { //Underweight
                imageViewBMI.setImageResource(R.drawable.under)
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.under))
            } else if (bmi > 25) {
                imageViewBMI.setImageResource(R.drawable.over)
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.over))
            } else {
                imageViewBMI.setImageResource(R.drawable.normal)
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.normal))
            }
        }

        buttonReset.setOnClickListener {
            imageViewBMI.setImageResource(R.drawable.empty)
            editTextWeight.text.clear()
            editTextHeight.text.clear()
            textViewBMI.text = getString(R.string.bmi)
            textViewStatus.text = getString(R.string.status)
        }
    }
}