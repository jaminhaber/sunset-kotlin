import processing.core.PApplet

const val program = "SunsetApp"
fun main() {
    println("Launching $program")
    PApplet.main(program)
}


class SunsetApp : PApplet() {
    private var layers = listOf<Layer>()

    override fun settings() {
        size(720, 480)
    }

    override fun setup() {
        noStroke()
        noCursor()
        layers = listOf(Sky(this), Sun(this), Clouds(this), Background(this), Hud(this))
    }

    override fun draw() {
        clear()
        for (layer in layers) layer.draw()
    }

    override fun mouseClicked() {
        for (l in layers) l.mouseClicked()
    }

    override fun keyPressed() {
        when (key.lowercaseChar()) {
            'q' -> this.exit()
            'r' -> this.setup()
        }
        for (l in layers) l.keyPressed(key)
    }

}

abstract class Layer {
    open fun draw() {}
    open fun keyPressed(key: Char) {}
    open fun mouseClicked() {}
}