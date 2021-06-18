package edu.hm.sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Math.*

class AccelerometerActivity : AppCompatActivity(), SensorEventListener {

    lateinit var manager: SensorManager
    lateinit var sensor : Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_accelerometer)

        manager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onStart() {
        super.onStart()
        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            val x = event.values[0] // x acceleration
            val y = event.values[1] // y acceleration

            var angle = kotlin.math.acos(-x / kotlin.math.sqrt(x * x + y * y)) *360 /(2* PI)
            if (y < 0) {
                angle = 360 - angle
            }

            findViewById<ImageView>(R.id.gravity_image).rotation = angle.toFloat()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}