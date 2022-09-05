package com.aungbophyoe.space.statedatacodetest

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aungbophyoe.space.statedatacodetest.Utility.isEmail
import com.aungbophyoe.space.statedatacodetest.Utility.showSnackbar
import com.aungbophyoe.space.statedatacodetest.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar

/*
* 5.9.2022
* created by aungbophyoe
* */
class SignUpFragment : Fragment() {
    private var _binding : FragmentSignUpBinding? = null
    private val binding get() = _binding
    private var dateOfBirth : String? = Utility.getCurrentDateOnly()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(layoutInflater,container,false)
        val view = _binding!!.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.apply {
            ivBack.setOnClickListener {
                findNavController().navigate(R.id.action_signUpFragment_to_logInFragment)
            }

            rlBirth.setOnClickListener {
                tvDateOfBirth.error = null
                tvDateOfBirth.clearFocus()
                Utility.hideSoftKeyboard(requireActivity())
                var chooseDate = dateOfBirth!!.split("/")
                var mYear: Int = chooseDate[2].toInt()
                var mMonth: Int = chooseDate[1].toInt()
                var mDay: Int = chooseDate[0].toInt()
                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { view, year, monthOfYear, dayOfMonth ->
                        dateOfBirth = "$dayOfMonth/${monthOfYear}/$year"
                        tvDateOfBirth.setText(dateOfBirth)
                    }, mYear, mMonth, mDay
                )
                datePickerDialog.show()
            }

            rlCreateNow.setOnClickListener {
                createAccount()
            }
        }
    }

    private fun createAccount(){
        binding!!.apply {
            val firstName = edtFirstName.text.toString().trim()
            val lastName = edtLastName.text.toString().trim()
            val email = edtEmail.text.toString().trim()
            val dob = tvDateOfBirth.text.toString().trim()
            val nationality = edtNation.text.toString().trim()
            val country = edtCountry.text.toString().trim()
            val phone = edtPhone.text.toString().trim()
            val gender = if(toggle.checkedRadioButtonId==female.id){
                "female"
            }else{
                "male"
            }
            if(firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && nationality.isNotEmpty() && country.isNotEmpty() && !dob.startsWith("DD")){
                if(!email.isEmail()){
                    edtEmail.error="error!"
                    return
                }
                binding!!.view.showSnackbar("Success!",Snackbar.LENGTH_SHORT)
            }else{
                if(firstName.isEmpty()){
                    edtFirstName.error="required!"
                }else if(lastName.isEmpty()){
                    edtLastName.error="required!"
                }else if(email.isEmpty()){
                    edtEmail.error="required!"
                }else if(dob.startsWith("DD")){
                    tvDateOfBirth.error="required!"
                } else if(nationality.isEmpty()){
                    edtNation.error="required!"
                }else if (country.isEmpty()){
                    edtCountry.error="required!"
                }
                binding!!.view.showSnackbar("Required!",Snackbar.LENGTH_SHORT)
                return
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}