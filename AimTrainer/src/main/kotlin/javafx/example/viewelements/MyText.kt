package javafx.example.viewelements

import javafx.example.UFO
import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

open class MyText(private val label: String, x: Double, y: Double, canvas: Canvas) : MyView(x, y, canvas) {

    init {
        gc.fill = Color.RED
        gc.stroke = Color.BLACK
        gc.lineWidth = 2.0
        val theFont: Font = Font.font("Times New Roman", FontWeight.BOLD, 48.0)
        gc.font = theFont
        gc.fillText(label, x, y)
        gc.strokeText(label, x, y)
    }
}