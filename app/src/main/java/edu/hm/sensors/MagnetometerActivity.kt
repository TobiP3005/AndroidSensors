package edu.hm.sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import edu.hm.sensors.databinding.ActivityMagnetometerBinding
import kotlin.math.roundToLong

class MagnetometerActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivityMagnetometerBinding
    private lateinit var sensorManager : SensorManager
    private lateinit var magnetometerSensor : Sensor
    private lateinit var magnetometerSensorValueX : TextView
    private lateinit var magnetometerSensorValueY : TextView
    private lateinit var magnetometerSensorValueZ : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_magnetometer)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_magnetometer)

        //define instances
        magnetometerSensorValueX = binding.sensorValueX
        magnetometerSensorValueY = binding.sensorValueY
        magnetometerSensorValueZ = binding.sensorValueZ
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        magnetometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    }


    override fun onSensorChanged(event: SensorEvent?) {

        //retrieve the current value of the proximity sensor


        //retrieve the current values of the magnetometer for each axis
        val current_xValue: Float = event!!.values[0]
        val current_yValue: Float = event!!.values[1]
        val current_zValue: Float = event!!.values[2]

        //display the retrieved value onto the textView
        magnetometerSensorValueX.text = String.format("%.4f", current_xValue)
        magnetometerSensorValueY.text = String.format("%.4f", current_yValue)
        magnetometerSensorValueZ.text = String.format("%.4f", current_zValue)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    //register the listener once the activity starts
    override fun onStart() {
        super.onStart()
        sensorManager.registerListener(this, magnetometerSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    //stop the sensor when the activity stops
    override fun onStop() {
        super.onStop()
        sensorManager.unregisterListener(this)
    }

}