package com.example.graphs.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graphs.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment(
        var xMin: Double,
        var xMax: Double,
        var yMin: Double,
        var yMax: Double,
        var color_osey: Int,
        var color_graph: Int,
        var size_line: Float,
        var graph: Boolean,
        var f: (Double) -> Double,
        var _f: (Double) -> Double
) : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        painter.init(xMin,xMax,yMin,yMax,color_osey,color_graph,size_line,graph,f,_f)
    }


}