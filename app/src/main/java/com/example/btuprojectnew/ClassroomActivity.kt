package com.example.btuprojectnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.btuprojectnew.databinding.ActivityClassroomBinding
import com.example.btuprojectnew.fragments.SettingsFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class ClassroomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClassroomBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_BTUProjectNew)
        binding = ActivityClassroomBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        binding.textViewAvatar.text = auth.currentUser?.email.toString().first().uppercase()

        binding.textViewAvatar.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
        }

        /* burger button function */

        binding.imageViewBurger.setOnClickListener {
            binding.layoutDrawer.openDrawer(GravityCompat.START)
        }

        navigationFunction()

    }

    private fun navigationFunction() {

        val burgerNavigationView = findViewById<NavigationView>(R.id.navigationBurger)
        val controller = findNavController(R.id.nav_host_fragment)

        val appBarConfig = AppBarConfiguration(setOf(
            R.id.coursesFragment,
            R.id.tableFragment
        ))

        setupActionBarWithNavController(controller, appBarConfig)
        burgerNavigationView.setupWithNavController(controller)

    }

}