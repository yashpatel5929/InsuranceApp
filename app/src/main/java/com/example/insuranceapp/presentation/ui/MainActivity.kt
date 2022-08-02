package com.example.insuranceapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.insuranceapp.R
import com.example.insuranceapp.databinding.FragmentLoginBinding
import com.example.insuranceapp.presentation.ui.adduserpolicy.AddPolicyFragmentDirections
import com.example.insuranceapp.presentation.ui.chooseentity.SelectEntityFragmentDirections
import com.example.insuranceapp.presentation.ui.registration.RegistrationFragment
import com.example.insuranceapp.presentation.ui.registration.RegistrationFragmentDirections
import com.example.insuranceapp.presentation.ui.userpolicies.UserPoliciesDirections
import com.example.insuranceapp.presentation.ui.userprofile.UserProfileFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var nestedNavHostFragment: NavHostFragment? = null
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        hideToolBar()
        toolbarBackButton.setOnClickListener {
            callBackPressed()
        }
        nestedNavHostFragment =
            supportFragmentManager.findFragmentById(R.id.navMainHost) as? NavHostFragment
        navController = findNavController(R.id.navMainHost)
        NavigationUI.setupWithNavController(toolbar, navController)
        bottomNavigationView.setupWithNavController(navController)
        onBottomNavigationSelectionTab()

    }
    fun hideBottomNav() {
        bottomNavigationView.visibility = View.GONE
    }

    fun showBottomNav() {
        bottomNavigationView.visibility = View.VISIBLE
    }

    private fun onBottomNavigationSelectionTab() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.userPolicies -> {
                    if(NavHostFragment.findNavController(navMainHost).currentDestination?.id!=R.id.userPolicies ) {
                        navController.navigate(R.id.userPolicies)
                    }
                    true
                }
                R.id.userProfile -> {
                    if(NavHostFragment.findNavController(navMainHost).currentDestination?.id!=R.id.userProfileFragment ) {
                        navController.navigate(R.id.userProfileFragment)
                    }
                    true
                }
                else -> false
            }
        }
    }

    fun hideToolBar() {
        toolbar.visibility = View.GONE
    }

    fun showToolBarNoBackButton(title: String) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbarBackButton.visibility = View.GONE
        toolbar.visibility = View.VISIBLE
        toolbar_title.text = title
    }

    fun showToolBarWithBackButton(title: String) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.visibility = View.VISIBLE
        toolbarBackButton.visibility = View.VISIBLE
        toolbar_title.text = title
    }

    override fun onBackPressed() {
        super.onBackPressed()
        callBackPressed()
    }

    private fun callBackPressed() {
        when(NavHostFragment.findNavController(navMainHost).currentDestination?.id){
            R.id.loginAuthFragment -> {

            }
            R.id.registerAuthFragment -> {
                findNavController(R.id.navMainHost).navigate(RegistrationFragmentDirections.actionBackToLoginFromRegister())
            }
            R.id.chooseEntityFragment -> {
                findNavController(R.id.navMainHost).navigate(SelectEntityFragmentDirections.actionBackToLogin())
            }
            R.id.otpVerificationFragment -> {
                findNavController(R.id.navMainHost).navigate(OtpVerificationFragmentDirections.actionBackToLogin())
            }
            R.id.userProfile -> {
                findNavController(R.id.navMainHost).navigate(UserProfileFragmentDirections.actionBackTOPolicies())
            }
            R.id.userPolicies -> {
                findNavController(R.id.navMainHost).navigate(UserPoliciesDirections.actionBackToLogin())
            }
            R.id.addPolicyFragment -> {
                findNavController(R.id.navMainHost).navigate(AddPolicyFragmentDirections.actionBackToUserPolicy())
            }

            else -> {
                super.onBackPressed()
            }
        }
    }
}