package com.example.khurusn.myapplication.add_applicant

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.khurusn.myapplication.R
import kotlinx.android.synthetic.main.activity_add_aplicant.*
import java.util.regex.Pattern


class AddAplicantActivity : AppCompatActivity(), IAddApplicantView {
    private lateinit var mAddApplicantPresenter: AddApplicantPresenter

    private val ANY_DOC: Int = 0
    private val PAN_OR_VOTER: Int = 1
    private val PAN: Int = 2
    private val DOCUMENTS_CLEARED = 3
    private val NO_DOCS_PROVIDED = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_aplicant)

        mAddApplicantPresenter = AddApplicantPresenter(this)

        btnSubmit.setOnClickListener {
            if (TextUtils.isEmpty(etLoanAmount.text.toString()))
                Toast.makeText(this, "Please enter loan amount.", Toast.LENGTH_SHORT).show()
            else
                validateForm()
        }
    }

    override fun showProgress() {

    }

    override fun stopProgress() {
    }

    override fun addApplicantSuccess() {
    }

    override fun addApplicantFailure() {
    }

    private fun validateForm() {
        var validDetails: Int

        val intLoan = etLoanAmount.text.toString().toInt()
        var validateDocs: Boolean = validateDocs(intLoan)
        if (!validFirstName()) {
            return
        }

        if (!validLastName()) {
            return
        }

        if (!validateEmail()) {
            return
        }

        if (!validLoanAmount()) {
            return
        } else {
            validDetails = checkDocumentsNeeded(intLoan)
        }

        if (validDetails == NO_DOCS_PROVIDED) {
            tvErrorMesg.text = getString(R.string.err_mesg_3o_t0_50kamount)
        } else if (validDetails == PAN_OR_VOTER) {
            tvErrorMesg.text = getString(R.string.err_mesg_3o_t0_50kamount)
        } else if (validDetails == PAN) {
            tvErrorMesg.text = getString(R.string.err_mesg_50kamount)
        } else if (validDetails == ANY_DOC) {
            tvErrorMesg.text = getString(R.string.err_mesg_below_3okamount)
        }

        if (validateDocs) {
            Toast.makeText(this, "All details saved Thank you !!!", Toast.LENGTH_SHORT).show()
            tvErrorMesg.text = "All details saved Thank you !!!"
            etPAN.error=null
            etVoterID.error=null
            etAdhaar.error=null
        } else
            Toast.makeText(this, "Please enter required fields", Toast.LENGTH_SHORT).show()

    }


    private fun validateDocs(intLoan: Int): Boolean {
        var docsProvided: Boolean = false
        if (intLoan < 30000) {
            docsProvided = validPAN() || validAdhar() || validVoterId()
        } else if (intLoan in 30000..50000) {
            docsProvided = validPAN() || validAdhar()
        } else if (intLoan >= 50000) {
            docsProvided = validPAN()
        }
        return docsProvided
    }

    /**
     *  will check documents needed as per loan amount
     * */
    private fun checkDocumentsNeeded(loan: Int): Int {
        var docStatus: Int = NO_DOCS_PROVIDED
        if (loan < 30000) {
            docStatus = ANY_DOC
        } else if (loan in 30000..50000) {
            docStatus = PAN_OR_VOTER
        } else if (loan >= 50000) {
            docStatus = PAN
        }
        return docStatus

    }

    /**
     *   Name should have min 3 char and max 30 char
     * */
    private fun validFirstName(): Boolean {
        val strFirstName = etFirstName.text.toString()
        return if (TextUtils.isEmpty(strFirstName.trim()) ||
                strFirstName.length < 3 || strFirstName.length > 30) {
            etFirstName.error = getString(R.string.name_error_mesg)
            requestFocus(etFirstName)
            false
        } else {
            true
        }
    }

    /**
     *   Name should have min 3 char and max 30 char
     * */
    private fun validLastName(): Boolean {
        val strLastName = etLastName.text.toString()
        return if (TextUtils.isEmpty(strLastName.trim()) ||
                strLastName.length < 3 || strLastName.length > 30) {
            etLastName.error = getString(R.string.name_error_mesg)
            requestFocus(etLastName)
            false
        } else {
            true
        }
    }

    /**
     *   Checks if email id is in valid format
     * */
    private fun validateEmail(): Boolean {
        val strEmail = etEmail.text.toString()
        return if (!strEmail.isValidEmail()) {
            etEmail.error = getString(R.string.email_error_mesg)
            requestFocus(etEmail)
            false
        } else {
            true
        }
    }

    /**
     *   PAN should contain 5 aplha , 4 digits , 1 alpha
     * */
    private fun validPAN(): Boolean {
        val pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}")
        val strPAN = etPAN.text.toString()

        val matcher = pattern.matcher(strPAN)
        return if (!matcher.matches()) {
            etPAN.error = getString(R.string.pan_error_mesg)
            requestFocus(etPAN)
            false
        } else {
            true
        }
    }

    /**
     *   Adhaar should contain 12 digits
     * */
    private fun validAdhar(): Boolean {
        val strAdhar = etAdhaar.text.toString()
        return if (strAdhar.length != 12) {
            etAdhaar.error = getString(R.string.adhaar_error_mesg)
            requestFocus(etAdhaar)
            false
        } else {
            true
        }
    }

    /**
     *  voter id should contain 3 alpha and 7 digits
     * */
    private fun validVoterId(): Boolean {
        val pattern = Pattern.compile("[A-Z]{3}[0-9]{7}")
        val strVoterId = etVoterID.text.toString()

        val matcher = pattern.matcher(strVoterId)
        return if (!matcher.matches()) {
            etVoterID.error = getString(R.string.voter_error_mesg)
            requestFocus(etVoterID)
            false
        } else {
            true
        }
    }

    /**
     *   Loan amount should be less than 100000
     * */
    private fun validLoanAmount(): Boolean {
        if (TextUtils.isEmpty(etLoanAmount.text.toString())) {
            return false
        }
        val intLoan = etLoanAmount.text.toString().toInt()
        return if (intLoan > 100000) {
            etLoanAmount.error = getString(R.string.loan_error_mesg)
            requestFocus(etLoanAmount)
            false
        } else {
            true
        }
    }


    private fun requestFocus(view: View) {
        if (view.requestFocus()) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        }
    }

    private fun String.isValidEmail(): Boolean = this.isNotEmpty() &&
            Patterns.EMAIL_ADDRESS.matcher(this).matches()

}
