package com.example.khurusn.myapplication.add_applicant

interface IAddApplicantView {

    fun showProgress()
    fun stopProgress()
    fun addApplicantSuccess()
    fun addApplicantFailure()

}