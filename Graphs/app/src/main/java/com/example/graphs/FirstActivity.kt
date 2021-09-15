package com.example.graphs

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import com.example.graphs.ui.main.Builder
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity() {

    var builder: Builder = Builder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        getSharedPref()

        seekbar_xmin.progress = (builder.xMin).toInt()
        seekbar_xmax.progress = (builder.xMax).toInt()
        seekbar_ymin.progress = (builder.yMin).toInt()
        seekbar_ymax.progress = (builder.yMax).toInt()

        tv_xmin.text = "xMin = ${builder.xMin}"
        tv_xmax.text = "xMax = ${builder.xMax}"
        tv_ymin.text = "yMin = ${builder.yMin}"
        tv_ymax.text = "yMax = ${builder.yMax}"

        color_osey.check(builder.color_osey)
        color_graph.check(builder.color_graph)
        size_line.check(builder.size_line)

        switch_graph.isChecked = builder.func

        seekbar_xmin.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            @SuppressLint("SetTextI18n")
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                tv_xmin.text = "xMin = ${seekBar?.progress.toString()}"
            }
        })

        seekbar_xmax.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            @SuppressLint("SetTextI18n")
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                tv_xmax.text = "xMax = ${seekBar?.progress.toString()}"
            }
        })

        seekbar_ymin.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            @SuppressLint("SetTextI18n")
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                tv_ymin.text = "yMin = ${seekBar?.progress.toString()}"
            }
        })
        seekbar_ymax.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            @SuppressLint("SetTextI18n")
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                tv_ymax.text = "yMax = ${seekBar?.progress.toString()}"
            }
        })

        btn1.setOnClickListener {
            if (seekbar_xmin.progress.toDouble() >= seekbar_xmax.progress.toDouble()
                || seekbar_ymin.progress.toDouble() >= seekbar_ymax.progress.toDouble()
            ) {
                Toast.makeText(this, "Неккоректный ввод отрезков", Toast.LENGTH_LONG).show()
            } else {
                initBuilder()
                putSharedPref()
                startAcivity()
            }
        }
    }

    private fun getSharedPref() {
        val prefs = getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
        prefs?.run {
            builder.func = getBoolean("function", false)
            builder.xMin = getFloat("xMin", -10F).toDouble()
            builder.xMax = getFloat("xMax", 10F).toDouble()
            builder.yMin = getFloat("yMin", -10F).toDouble()
            builder.yMax = getFloat("yMax", 10F).toDouble()
            builder.color_osey = getInt("color_osey", os_red.id)
            builder.color_graph = getInt("color_graph", graph_red.id)
            builder.size_line = getInt("size_line", 10)
        }
    }

    private fun startAcivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("xMin", builder.xMin)
        intent.putExtra("xMax", builder.xMax)
        intent.putExtra("yMin", builder.yMin)
        intent.putExtra("yMax", builder.yMax)
        intent.putExtra("color_osey", builder.color_osey)
        intent.putExtra("color_graph", builder.color_graph)
        intent.putExtra("size_line", builder.size_line)
        intent.putExtra("graph", builder.func)
        startActivity(intent)
    }

    private fun initBuilder() {
        builder.xMin = seekbar_xmin.progress.toDouble()
        builder.xMax = seekbar_xmax.progress.toDouble()
        builder.yMin = seekbar_ymin.progress.toDouble()
        builder.yMax = seekbar_ymax.progress.toDouble()
        builder.func = switch_graph.isChecked
        builder.color_osey = color_osey.checkedRadioButtonId
        builder.color_graph = color_graph.checkedRadioButtonId
        builder.size_line = size_line.checkedRadioButtonId
    }

    private fun putSharedPref() {
        val prefs = getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
        prefs.run {
            edit().run {
                putBoolean("function", builder.func)
                putFloat("xMin", builder.xMin.toFloat())
                putFloat("xMax", builder.xMax.toFloat())
                putFloat("yMin", builder.yMin.toFloat())
                putFloat("yMax", builder.yMax.toFloat())
                putInt("color_osey", builder.color_osey)
                putInt("color_graph", builder.color_graph)
                putInt("size_line", builder.size_line)
                apply()
            }
        }
    }
}