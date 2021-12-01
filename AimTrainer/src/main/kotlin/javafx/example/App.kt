package javafx.example

import javafx.animation.AnimationTimer
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.stage.Stage
import kotlin.concurrent.thread
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() {
    launch(StartPage::class.java)
    //launch(UfoApplication::class.java)
}

const val CANVAS_WIDTH = 800.0
const val CANVAS_HEIGHT = 600.0

class AimTrainerApplication : Application() {
    override fun start(primaryStage: Stage) {
        val layout = VBox()
        val canvas = Canvas(CANVAS_WIDTH, CANVAS_HEIGHT)
        layout.children.add(canvas)
        primaryStage.scene = Scene(layout)
        primaryStage.show()

        val game = Game()
        repeat(1) {
            val width = Random.nextDouble(CANVAS_WIDTH - 60.0)
            val height = Random.nextDouble(CANVAS_HEIGHT - 60.0)
            game.entities.add(UFO(width, height))
        }

        val timer = Timer(game, canvas)
        timer.start()
    }
}

class Timer(val game: Game, val canvas: Canvas) : AnimationTimer() {
    private var ticks = 0
//    private var ticks2 = 0

    override fun handle(now: Long) {
//        ticks2++
//        if (ticks2 == 3000){
//            println(ticks2)
//        }
        ticks = (ticks + 1) % 10
        if (ticks == 0) {
            game.tick()
        }
        game.renderScene(canvas)
    }
}

interface Renderable {
    fun render(canvas: Canvas)
}

abstract class Entity(var x: Double, var y: Double) {
    var isAlive: Boolean = true
    var clickTime: Long = -1

    init {
        thread {
            while (true) {

                clickTime = measureTimeMillis {
                    loop@ for (i in 1..5000) {
                        Thread.sleep(1)
                        if (!isAlive) {   //Ha közben kilőtték akkor új kordinátákat kap és nem várjuk ki az 5 másodpercet
                            isAlive = true
                            break@loop
                        }
                    }
                }
                if (isAlive) {
                    x = Random.nextDouble(CANVAS_WIDTH - 60.0)
                    y = Random.nextDouble(CANVAS_HEIGHT - 60.0)
                }
            }
        }
    }

    open fun progress() {
        /* Empty */
    }
}

class UFO(x: Double, y: Double) : Renderable, Entity(x, y) {
    companion object {
        const val WIDTH = 40.0
        const val HEIGHT = 40.0
    }

    override fun render(canvas: Canvas) {
        val context = canvas.graphicsContext2D

//        //println("${canvas.isPressed}")
//        context.canvas.setOnMouseClicked {
//            println("X-tengely: ${it.x}, Y-tengely: ${it.y}")
//            println("X-tengely: $x, Y-tengely: $y")
//            if (it.x < x + 100 && it.x > x - 100 ){//&& it.y < y + 100 && it.y > y - 100) {
//                isAlive = false
//            }
//        }

        context.fill = Color.DIMGRAY
        context.fillOval(x, y, WIDTH, HEIGHT)
        context.fill = Color.DODGERBLUE
        context.fillOval(x + WIDTH / 4, y + HEIGHT / 4, WIDTH / 2, HEIGHT / 2)
    }

    override fun progress() {
        x += Random.nextDouble(from = -5.0, until = 5.0)
        y += Random.nextDouble(from = -10.0, until = 10.0)
    }
}

class Game {
    val entities = mutableListOf<Entity>()
    var xclick: Double = -1.0
    var yclick: Double = -1.0


    fun tick() {
        for (entity in entities) {
            entity.progress()
        }
    }

    fun setClick(canvas: Canvas, entity: Entity) {
        val context = canvas.graphicsContext2D
        context.canvas.setOnMouseClicked {
            println("X-tengely: ${it.x}, Y-tengely: ${it.y}")
            //println("X-tengely: ${entity.x}, Y-tengely: ${entity.y}")
            xclick = it.x
            yclick = it.y
        }
    }

    fun renderScene(canvas: Canvas) {
        canvas.graphicsContext2D.fill = Color.BLACK
        canvas.graphicsContext2D.fillRect(0.0, 0.0, canvas.width, canvas.height)

        for (entity in entities) {
            setClick(canvas, entity)

            if (xclick < entity.x + 40 && xclick > entity.x - 40 && yclick < entity.y + 40 && yclick > entity.y - 40) {
                entity.isAlive = false
                thread {
                    Thread.sleep(500)
                    println("${entity.clickTime}")
                }
            }

            if (entity is Renderable && entity.isAlive) {
                entity.render(canvas)
            }
//            if (entity is Renderable && !entity.isAlive) {
//                entity.x = Random.nextDouble(CANVAS_WIDTH - 60.0)
//                entity.y = Random.nextDouble(CANVAS_HEIGHT - 60.0)
//                entity.isAlive = true
//            }
        }
//
//        for (entity in entities) {
//            (entity as? Renderable)?.render(canvas)
//        }
//
//        for (entity in entities) {
//            if (entity !is Renderable) continue
//            entity.render(canvas)
//        }
//
//        for (entity in entities) {
//            val renderable = entity as? Renderable ?: continue
//            renderable.render(canvas)
//        }
    }
}
