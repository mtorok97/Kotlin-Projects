package javafx.example.viewelements

import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color

class MyButton (private val label: String, style: Styles, x: Double, y: Double, canvas: Canvas) : MyText(label, style, x, y, canvas) {

    init{
        context.fill = Color.color(205.0/255,183.0/255,183.0/255,0.3)
        context.fillRoundRect(x-10, y-50, label.length*26.toDouble(), 70.0,30.0,30.0)
    }

    fun onClick(function: () -> Unit) {

        context.canvas.setOnMouseClicked {
            //println("X: ${x}, Y: ${y}")
            //println("X-tengely: ${it.x}, Y-tengely: ${it.y}")
                if (it.x < x + label.length*22 && x <= it.x && y - 40 <= it.y && y >= it.y) {
                    function()
                }
        }
    }
}