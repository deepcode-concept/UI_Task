package com.example.taskui2

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.viewpager2.widget.ViewPager2
import com.example.taskui2.Adapter.ImageAdapter
import com.example.taskui2.Fragment.AccountFragment
import com.example.taskui2.Fragment.HomeFragment
import com.example.taskui2.Fragment.OrderFragment
import com.example.taskui2.Fragment.VoucherFragment
import com.example.taskui2.ui.theme.Taskui2Theme
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().getDecorView().getWindowInsetsController()
                ?.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
        };
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        // Load default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        bottomNav.setOnItemSelectedListener { item ->
            val selectedFragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_voucher -> VoucherFragment()
                R.id.nav_order -> OrderFragment()
                R.id.nav_account -> AccountFragment()
                else -> HomeFragment()
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit()

            true
        }
    }
}

