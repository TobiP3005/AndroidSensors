package edu.hm.sensors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import edu.hm.sensors.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this

    }

    fun onProximityButtonClick(view: View) {
        val intent = Intent(this@MainActivity, ProximityActivity::class.java)
        this.startActivity(intent)
    }

    fun onLightButtonClick(view: View) {
        val intent = Intent(this@MainActivity, LightActivity::class.java)
        this.startActivity(intent)
    }

    fun onMagnetometerButtonClick(view: View) {
        val intent = Intent(this@MainActivity, MagnetometerActivity::class.java)
        this.startActivity(intent)
    }

    fun onGravityButtonClick(view: View) {
        val intent = Intent(this@MainActivity, AccelerometerActivity::class.java)
        this.startActivity(intent)
    }
}