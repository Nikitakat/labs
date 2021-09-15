package com.example.graphs.ui.main


data class Builder(
    var func: Boolean = false,
    var xMin: Double = -10.0,
    var xMax: Double = 10.0,
    var yMin: Double = -10.0,
    var yMax: Double = 10.0,
    var color_osey: Int = 0,
    var color_graph: Int = 0,
    var size_line: Int = 0
)