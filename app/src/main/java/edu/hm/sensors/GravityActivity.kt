package edu.hm.sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import edu.hm.sensors.databinding.ActivityGravityBinding
import kotlin.math.*

class GravityActivity: AppCompatActivity(), SensorEventListener {


    private lateinit var binding: ActivityGravityBinding
    private lateinit var sensorManager : SensorManager
    private lateinit var gravitySensor : Sensor
    private lateinit var gravityPicture : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_gravity)

        //define instances
        gravityPicture = binding.gravityImage
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val hyp = sqrt(x*x + y*y)
            var angle = acos(-x/hyp) * 360 / (2* PI)
            if (y < 0) {
                angle = 360.0 - angle
            }

            val hyp2 = sqrt(y*y + z*z)
            var angle2 = acos(y/hyp2) * 360 / (2* PI)
            if (z > 0) {
                angle2 = 360 - angle2
            }

            if (y < 0) {
                angle2 -= 180
            }

            gravityPicture.rotation = angle.toFloat()

            gravityPicture.rotationX = angle2.toFloat()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}