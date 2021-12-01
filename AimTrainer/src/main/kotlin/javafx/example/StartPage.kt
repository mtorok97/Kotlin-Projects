package javafx.example

import javafx.application.Application
import javafx.example.viewelements.MyButton
import javafx.example.viewelements.MyText
import javafx.example.viewelements.Styles
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import javafx.stage.Stage
import kotlin.system.exitProcess


class StartPage : Application() {

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Aim trainer"
        val layout = VBox()
        val canvas = Canvas(CANVAS_WIDTH, CANVAS_HEIGHT)
        layout.children.add(canvas)
        primaryStage.scene = Scene(layout)
        val bullseye = Image("file:assets/bullseye.png")
        primaryStage.icons.add(bullseye)

        val gc: GraphicsContext = canvas.graphicsContext2D

        val aimTrainerLabel = MyText("Aim Trainer", Styles.AppStyle,CANVAS_WIDTH / 2 - 115, CANVAS_HEIGHT / 2 - 200, canvas)
        val startGameLabel = MyButton("Start Game", Styles.AppStyle,CANVAS_WIDTH / 2 - 115, CANVAS_HEIGHT / 2 - 100, canvas)
        startGameLabel.onClick {
            AimTrainerApplication().start(primaryStage)
        }

        primaryStage.scene.setOnKeyPressed {
            println("${it.code}")
            if (it.code.toString() == "ENTER"){
                AimTrainerApplication().start(primaryStage)
            }
            else if (it.code.toString() == "ESCAPE"){
                exitProcess(1)
            }
        }
//        val scoresLabel = MyButton("Scores", 48.0,CANVAS_WIDTH / 2 - 70, CANVAS_HEIGHT / 2, canvas)
//        scoresLabel.onClick{
//            AimTrainerApplication().start(primaryStage)
//        }

        gc.drawImage(bullseye, CANVAS_WIDTH / 2 - 25, CANVAS_HEIGHT / 2)
        val pressEnter = MyText("Click to Start Game or press Enter to Start",  Styles.SmallLabel, CANVAS_WIDTH / 2 - 180, CANVAS_HEIGHT / 2 + 100, canvas)
        val pressEscape = MyText("Press ESCAPE to exit",  Styles.SmallLabel, CANVAS_WIDTH / 2 - 90, CANVAS_HEIGHT / 2 + 150, canvas)
        primaryStage.show()
    }
}