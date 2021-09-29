package com.example.navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var listener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(navController.graph,drawerLayout)
        setupActionBarWithNavController(navController,appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)

        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)

        // first parameter is the file for icon and second one is menu
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // We are using switch case because multiple icons can be kept
        when (item.getItemId()) {
            R.id.shareButton -> {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                // type of the content to be shared
                sharingIntent.type = "text/plain"
                // Body of the content
                val shareBody = "Your Body Here"
                // subject of the content. you can share anything
                val shareSubject = "Your Subject Here"
                // passing body of the content
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
                // passing subject of the content
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject)
                startActivity(Intent.createChooser(sharingIntent, "Share using"))
            }
            R.id.viewBalance -> {
                navController!!.navigate(R.id.action_mainFragment_to_viewBalanceFragment);
            }
            R.id.viewTransaction -> {
                navController!!.navigate(R.id.action_mainFragment_to_viewTransactionFragment);
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
