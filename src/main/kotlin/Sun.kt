import processing.core.PApplet

class Sun(private val app:PApplet):Layer() {

    override fun draw() {
        app.fill(247f, 253f, 4f)
        app.circle(app.mouseX.toFloat(),app.mouseY.toFloat(),20f)

        val orangeAlpha = (app.mouseY.toFloat() / app.height.toFloat()) * 255f
        app.fill(252f, 84f, 4f,orangeAlpha)
        app.circle(app.mouseX.toFloat(),app.mouseY.toFloat(),20f)

    }
}