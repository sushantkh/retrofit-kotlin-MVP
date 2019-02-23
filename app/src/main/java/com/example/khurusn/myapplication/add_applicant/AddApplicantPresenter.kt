package com.example.khurusn.myapplication.add_applicant

import com.example.khurusn.myapplication.user_list.UsersResponse

class AddApplicantPresenter {
    var mIAddApplicantView:IAddApplicantView

    constructor(iAddApplicantView: IAddApplicantView)
    {
        mIAddApplicantView=iAddApplicantView
    }


    public fun saveApplicant( user: UsersResponse.Data)
    {
        // save user
    }

    public fun validateForm()
    {

    }


    /*
    *  name min 3 max 30
    *  email
    *  loan less than 1l
    *  pan  optional 5 alpha 4 digits 1 letter
    *  adhaar 12
    *  voter 3 alpha 7 digits
    *
    *  <30000 - pan/adhar/voter
    *  >30k <50k - pan/adhar
    *  >50k - adhar
    * */
}