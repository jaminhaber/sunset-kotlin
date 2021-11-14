import processing.core.PApplet
import processing.core.PVector
import kotlin.random.Random

class Background(val app: PApplet) : Layer() {
    val ground: Array<Float>

    init {
        /* generate ground randomly */
        var dy = 0f;
        var height = Random.nextFloat() * 100 + 20
        this.ground = Array(app.width) {
            if (height > 150 || height < 20) dy *= -.5f
            dy = (dy + (Random.nextFloat() - .5f) * .25f).coerceIn(-3f, 3f)
            height += dy
            480 - height
        }
    }

    private val features = Feature.create(this)


    override fun draw() {
        val ratio = app.mouseY.toFloat() / app.height.toFloat()
        app.fill(ratio / 5)
        for (i in ground.indices) app.rect(i.toFloat(), ground[i], 1f, 480 - ground[i])
        for (f in features) f.draw()


    }
}

class Forrest(private val bg: Background, xPos: Int) : Feature() {
    override val width = Random.nextInt(20, 260);
    private val trees = mutableListOf<Pair<Int, Int>>()

    init {
        var acc = xPos
        while (acc < xPos + width) {
            acc += Random.nextInt(8, 20)
            if (acc >= bg.app.width) break;
            trees.add(Pair(acc, Random.nextInt(12, 16)))
        }
    }

    override fun draw() {
        for ((i, height) in trees) {
            val x = i.toFloat();
            val y = bg.ground[i] - height
            bg.app.rect(x - 2, y, 4f, 20f)
            for (i in -3..6 step 4) bg.app.triangle(x - 6, y + 6 + i, x, y + i, x + 6, y + 6 + i)
        }
    }
}

class House(private val bg: Background, xPos: Int) : Feature() {
    override val width = 20
    private var pos: PVector;
    private val chimney = Random.nextBoolean()

    init {
        var d = 0f
        for (i in xPos..xPos + width) {
            if (i >= bg.ground.size) break;
            if (bg.ground[i] > d) d = bg.ground[i]
        }
        pos = PVector(xPos.toFloat(), d - width / 1.5f)

    }

    override fun draw() {
        val prevFill = bg.app.graphics.fillColor
        bg.app.rect(pos.x, pos.y, width.toFloat(), width * 1.5f)
        bg.app.triangle(pos.x - 3, pos.y + 1, pos.x + width / 2, pos.y - 8, pos.x + width + 3, pos.y + 1)

        if (chimney) {
            bg.app.rect(pos.x + width * .65f, pos.y - 8, 5f, 6f)
        }

        bg.app.fill(prevFill)
    }
}


abstract class Feature {
    companion object {
        fun create(bg: Background): List<Feature> {
            val features = mutableListOf<Feature>()
            var featureX = Random.nextInt(bg.app.width / 3)
            while (featureX < bg.app.width) {
                val feature = when (Random.nextInt(3)) {
                    0 -> Forrest(bg, featureX)
                    1 -> Forrest(bg, featureX)
                    2 -> House(bg, featureX)
                    else -> throw Error("Bad feature generated")
                }
                features.add(feature)
                featureX += feature.width + Random.nextInt(bg.app.width / 3)
            }
            return features
        }
    }


    abstract val width: Int
    abstract fun draw()
}


