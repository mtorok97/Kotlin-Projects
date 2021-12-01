package javafx.example.viewelements

import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

enum class Styles{
    AppStyle,
    SmallLabel
}


open class MyText(label: String, style: Styles, x: Double, y: Double, canvas: Canvas) : MyView(x, y, canvas) {

    init {
        when(style){
            Styles.AppStyle -> {
                gc.fill = Color.ORANGE
                gc.stroke = Color.BLACK
                gc.lineWidth = 1.5
                val theFont: Font = Font.font("Times New Roman", FontWeight.BOLD, 48.0)
                gc.font = theFont
                gc.fillText(label, x, y)
                gc.strokeText(label, x, y)
            }
            Styles.SmallLabel -> {
                gc.fill = Color.RED
                gc.stroke = Color.BLACK
                gc.lineWidth = 0.5
                val theFont: Font = Font.font("Times New Roman", FontWeight.BOLD, 20.0)
                gc.font = theFont
                gc.fillText(label, x, y)
                gc.strokeText(label, x, y)
            }
        }
    }
}