package edu.hm.sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import edu.hm.sensors.databinding.ActivityProximityBinding

class ProximityActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivityProximityBinding
    private lateinit var sensorManager : SensorManager
    private lateinit var proximitySensor : Sensor
    private lateinit var proximitySensorValue : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proximity)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_proximity)

        //define instances
        proximitySensorValue = binding.sensorValue
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
    }


    override fun onSensorChanged(event: SensorEvent?) {

        //retrieve the current value of the proximity sensor
        val currentValue: Float = event!!.values[0]

        //display the retrieved value onto the textView
        proximitySensorValue.text = currentValue.toString()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    //register the listener once the activity starts
    override fun onStart() {
        super.onStart()
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    //stop the sensor when the activity stops
    override fun onStop() {
        super.onStop()
        sensorManager.unregisterListener(this)
    }

}