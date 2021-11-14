import processing.core.PApplet
import processing.core.PVector
import kotlin.random.Random

class Sky(private val app: PApplet) : Layer() {
    private val stars = (1..Random.nextInt(80, 200)).map { PVector(Random.nextFloat() * 720, Random.nextFloat() * 480) }


    override fun draw() {
        val ratio = app.mouseY.toFloat() / app.height.toFloat()

        /* background */
        app.background(100f, 100f, 255f)
        app.fill(49f, 31f, 49f, ratio * 250);
        app.rect(0f, 0f, app.width.toFloat(), app.height.toFloat());

        /* stars */
        val alpha = (3 * (ratio - .4f)).coerceIn(0f, 1f) * 255
        app.fill(255f, 255f, 255f, alpha)
        for (i in stars.indices) {
            app.circle(stars[i].x, stars[i].y, 2f)
        }

    }
}

