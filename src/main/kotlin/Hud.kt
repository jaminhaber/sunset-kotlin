import processing.core.PApplet
import processing.core.PConstants
import kotlin.math.roundToInt

class Hud(private val app: PApplet) : Layer() {
    var intro = true;

    override fun draw() {
        app.fill(255)
        app.textSize(14f)
        app.textAlign(PConstants.LEFT)
        app.text("FPS: ${app.frameRate.roundToInt()}", app.width - 60f, 14f)

        if (intro) {
            app.textAlign(PConstants.CENTER)
            app.text("move the mouse to change time\npress 'R' to generate new terrain\npress 'Q' or esc to quit",
                app.width / 2f,
                140f)
        }
    }

    override fun keyPressed(key: Char) {
        intro = false;
    }

    override fun mouseClicked() {
        intro = false
    }
}