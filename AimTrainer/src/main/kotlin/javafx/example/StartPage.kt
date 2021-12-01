package javafx.example

import javafx.application.Application
import javafx.example.viewelements.MyButton
import javafx.example.viewelements.MyText
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.stage.Stage


class StartPage : Application() {
    //var buttonLambdaList = mutableListOf<() -> Unit>() //lambdákat tartalmazó lista
    //var buttonList = mutableListOf<MyButton>() //lambdákat tartalmazó lista

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Aim trainer"
        val layout = VBox()
        val canvas = Canvas(CANVAS_WIDTH, CANVAS_HEIGHT)
        layout.children.add(canvas)
        primaryStage.scene = Scene(layout)
        val bullseye = Image("file:assets/bullseye.png")
        primaryStage.icons.add(bullseye)

        val gc: GraphicsContext = canvas.graphicsContext2D

        val aimTrainerLabel = MyText("Aim Trainer", CANVAS_WIDTH / 2 - 115, CANVAS_HEIGHT / 2 - 200, canvas)
        val startGameLabel = MyButton("Start Game", CANVAS_WIDTH / 2 - 115, CANVAS_HEIGHT / 2 - 100, canvas)

//        var startGameLambda = {
//            val x = 5
//            AimTrainerApplication().start(primaryStage)
//            println("hiiiiiiiiiiiiiiiiiiiiiiiii")
//        }
//
//        buttonList.add(startGameLabel)
//        buttonLambdaList.add(startGameLambda)

        //buttonList.add(startGameLabel)
        startGameLabel.onClick {
            AimTrainerApplication().start(primaryStage)
            println("hiiiiiiiiiiiiiiiiiiiiiiiii")
        }
        val scoresLabel = MyButton("Scores", CANVAS_WIDTH / 2 - 70, CANVAS_HEIGHT / 2, canvas)
//        buttonList.add(scoresLabel)
//        for(e in buttonList){
//            println(e.x)
//        }
        scoresLabel.onClick{
            AimTrainerApplication().start(primaryStage)
            println("yooooooooooooooooo")
        }

        gc.drawImage(bullseye, CANVAS_WIDTH / 2 - 25, CANVAS_HEIGHT / 2 + 100)

        primaryStage.show()
    }
}