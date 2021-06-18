package edu.hm.sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import edu.hm.sensors.databinding.ActivityLightBinding

class LightActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivityLightBinding
    private lateinit var sensorManager : SensorManager
    private lateinit var lightSensor : Sensor
    private lateinit var lightSensorValue : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proximity)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_light)

        //define instances
        lightSensorValue = binding.sensorValue
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }


    override fun onSensorChanged(event: SensorEvent?) {

        //retrieve the current value of the proximity sensor
        val currentValue: Float = event!!.values[0]

        //display the retrieved value onto the textView
        lightSensorValue.text = currentValue.toString()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    //register the listener once the activity starts
    override fun onStart() {
        super.onStart()
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    //stop the sensor when the activity stops
    override fun onStop() {
        super.onStop()
        sensorManager.unregisterListener(this)
    }

}