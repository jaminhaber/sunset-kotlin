import processing.core.PVector
import kotlin.random.Random

class Clouds(private val app: SunsetApp) : Layer() {
    private var nextCloudFrame = app.frameCount + Random.nextInt(0, 1000)
    private var clouds = MutableList(Random.nextInt(0, 10)) { Cloud(app.width.toFloat()) }

    override fun draw() {
        /* spawn one cloud at random intervals */
        if (app.frameCount > nextCloudFrame) {
            clouds.add(Cloud(0f))
            nextCloudFrame += Random.nextInt(0, 1000)
        }

        app.fill(255f, 255f, 255f, 100f)

        val itr = clouds.iterator()

        while (itr.hasNext()) {
            val c = itr.next()
            c.move()
            app.rect(c.pos.x, c.pos.y, c.size.x, c.size.y)

            /* remove cloud if off-screen */
            if (c.pos.x > app.width) itr.remove()
        }
    }

}

class Cloud(maxInitialX: Float) {
    private val speed = Random.nextFloat() * .5f + .2f
    private val height = Random.nextFloat() * 50 + 10

    /* width must always be greater than height */
    val size = PVector((1 + 2 * Random.nextFloat()) * height, height)
    val pos = PVector(Random.nextFloat() * maxInitialX - size.x, Random.nextFloat() * 200 + 5)

    fun move() {
        pos.x += speed;
    }
}