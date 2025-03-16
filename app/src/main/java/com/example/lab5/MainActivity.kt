package com.example.lab5

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab5.models.FormData
import com.example.lab5.models.validations.ValidationResult

class MainActivity : AppCompatActivity() {
    private lateinit var edtStudentId: EditText
    private lateinit var spnYear: Spinner
    private lateinit var spnSemester: Spinner
    private lateinit var cbAgree: CheckBox
    private lateinit var btnSubmit: View
    private lateinit var btnCancel: View
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtStudentId = findViewById(R.id.edtStudentId)
        spnYear = findViewById(R.id.spnYear)
        spnSemester = findViewById(R.id.spnSemester)
        cbAgree = findViewById(R.id.cbAgree)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnCancel = findViewById(R.id.btnCancel)

        // Set click listeners
        btnSubmit.setOnClickListener { submit(it) }
        btnCancel.setOnClickListener { clearForm() }
    }

    private fun displayAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)
        builder.create().show()
    }

    private fun submit(v: View) {
        count = 0
        val myForm = FormData(
            edtStudentId.text.toString(),
            spnYear.selectedItem.toString(),
            spnSemester.selectedItem.toString(),
            cbAgree.isChecked
        )

        val studentIdValidation = myForm.validateStudentId()
        val yearValidation = myForm.validateYear()
        val semesterValidation = myForm.validateSemester()
        val agreementValidation = myForm.validateAgreement()

        when (studentIdValidation) {
            is ValidationResult.Valid -> { count++ }
            is ValidationResult.Invalid -> { edtStudentId.error = studentIdValidation.errorMessage }
            is ValidationResult.Empty -> { edtStudentId.error = studentIdValidation.errorMessage }
        }

        when (yearValidation) {
            is ValidationResult.Valid -> { count++ }
            is ValidationResult.Invalid -> {
                val tv: TextView = spnYear.selectedView as TextView
                tv.error = ""
                tv.text = yearValidation.errorMessage
            }
            is ValidationResult.Empty -> {
                val tv: TextView = spnYear.selectedView as TextView
                tv.error = ""
                tv.text = yearValidation.errorMessage
            }
        }

        when (semesterValidation) {
            is ValidationResult.Valid -> { count++ }
            is ValidationResult.Invalid -> {
                val tv: TextView = spnSemester.selectedView as TextView
                tv.error = ""
                tv.text = semesterValidation.errorMessage
            }
            is ValidationResult.Empty -> {
                val tv: TextView = spnSemester.selectedView as TextView
                tv.error = ""
                tv.text = semesterValidation.errorMessage
            }
        }

        when (agreementValidation) {
            is ValidationResult.Valid -> { count++ }
            is ValidationResult.Invalid -> {
                displayAlert("Error", agreementValidation.errorMessage)
            }
            is ValidationResult.Empty -> { }
        }

        if (count == 4) {
            displayAlert("Success", "You have successfully registered")
        }
    }

    private fun clearForm() {
        edtStudentId.text.clear()
        spnYear.setSelection(0) // Reset to the first item
        spnSemester.setSelection(0) // Reset to the first item
        cbAgree.isChecked = false // Uncheck the checkbox
    }
}