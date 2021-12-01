package javafx.example.viewelements

import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext

abstract class MyView(val x: Double, val y: Double, val canvas: Canvas){
    val gc: GraphicsContext = canvas.graphicsContext2D
    val context: GraphicsContext = canvas.graphicsContext2D
}