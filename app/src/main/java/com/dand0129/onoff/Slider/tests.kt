import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import kotlin.math.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CircularSlider(
    modifier: Modifier = Modifier,
    padding: Float = 50f,
    stroke: Float = 20f,
    cap: StrokeCap = StrokeCap.Round,
    touchStroke: Float = 50f,
    thumbColor: Color = Color.Blue,
    progressColor: Color = Color(0xFF3d3d47),
    backgroundColor: Color = Color(0xFF0d9490),
    debug: Boolean = false,
    onChange: ((Float) -> Unit)? = null
) {
    var width by remember { mutableStateOf(0) }
    var height by remember { mutableStateOf(0) }
    var angle by remember { mutableStateOf(-180f) }
    var down by remember { mutableStateOf(false) }
    var radius by remember { mutableStateOf(0f) }
    var center by remember { mutableStateOf(Offset.Zero) }
    var appliedAngle by remember { mutableStateOf(0f) }
    val threshold = 10f // Umbral para evitar el cierre de la aplicación al pasar el slider

    LaunchedEffect(key1 = angle) {
        var a = angle
        a += 180
        if (a <= 0f) {
            a += 360
        }
        a = a.coerceIn(0f, 180f) // Ajustar el rango de 0 a 180 grados para el semicírculo
        appliedAngle = a
    }
    LaunchedEffect(key1 = appliedAngle) {
        onChange?.invoke(appliedAngle / 180f) // Normalizar el ángulo en el rango de 0 a 1
    }

    Canvas(
        modifier = modifier
            .onGloballyPositioned {
                width = it.size.width
                height = it.size.height
                center = Offset(width / 2f, height / 2f)
                radius = min(width.toFloat(), height.toFloat()) / 2f - padding - stroke / 2f
            }
            .pointerInteropFilter { event ->
                val x = event.x
                val y = event.y
                val offset = Offset(x, y)
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        val d = distance(offset, center)
                        val a = angle(center, offset)
                        if (d >= radius - touchStroke / 2f &&
                            d <= radius + touchStroke / 2f &&
                            a !in -180f..0f
                        ) {
                            down = true
                            angle = a
                        } else {
                            down = false
                        }
                    }
                    MotionEvent.ACTION_MOVE -> {
                        if (down) {
                            angle = angle(center, offset)
                            }
                        }
                    MotionEvent.ACTION_UP -> {
                        down = false
                    }
                    else -> return@pointerInteropFilter false
                }
                return@pointerInteropFilter true
            }
    ) {
        drawArc(
            color = backgroundColor,
            startAngle = -180f,
            sweepAngle = 180f, // Cambiar a 180 grados para el semicírculo
            topLeft = center - Offset(radius, radius),
            size = Size(radius * 2, radius * 2),
            useCenter = false,
            style = Stroke(
                width = stroke,
                cap = cap
            )
        )
        drawArc(
            color = progressColor,
            startAngle = 0f,
            sweepAngle = -appliedAngle,
            topLeft =  center - Offset(radius, radius),
            size = Size(radius * 2, radius * 2),
            useCenter = false,
            style = Stroke(
                width = stroke,
                cap = cap
            )
        )
        drawCircle(
            color = thumbColor,
            radius = stroke,
            center = center + Offset(
                radius * cos((appliedAngle) * PI / -180f).toFloat(),
                radius * sin((appliedAngle) * PI / -180f).toFloat()
            )
        )
        if (debug) {
            drawRect(
                color = Color.Green,
                topLeft = Offset.Zero,
                size = Size(width.toFloat(), height.toFloat()),
                style = Stroke(
                    4f
                )
            )
            drawRect(
                color = Color.Red,
                topLeft = Offset(padding, padding),
                size = Size(width.toFloat() - padding * 2, height.toFloat() - padding * 2),
                style = Stroke(
                    4f
                )
            )
            drawRect(
                color = Color.Blue,
                topLeft = Offset(padding, padding),
                size = Size(width.toFloat() - padding * 2, height.toFloat() - padding * 2),
                style = Stroke(
                    4f
                )
            )
            drawCircle(
                color = Color.Red,
                center = center,
                radius = radius + stroke / 2f,
                style = Stroke(2f)
            )
            drawCircle(
                color = Color.Red,
                center = center,
                radius = radius - stroke / 2f,
                style = Stroke(2f)
            )
        }
    }
}


fun angle(center: Offset, offset: Offset): Float {
    val rad = atan2(center.y - offset.y, center.x - offset.x)
    val deg = Math.toDegrees(rad.toDouble())
    return deg.toFloat()
}

fun distance(first: Offset, second: Offset): Float {
    return sqrt((first.x - second.x).square() + (first.y - second.y).square())
}

fun Float.square(): Float {
    return this * this
}