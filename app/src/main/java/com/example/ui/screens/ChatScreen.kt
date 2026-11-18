package com.example.ui.screens

import com.example.ui.theme.AppColors
import com.example.ui.theme.EmochiGradients
import com.example.ui.theme.EmochiBackground
import com.example.ui.theme.InterFontFamily
import com.example.ui.theme.LiterarySerifFontFamily
import com.example.ui.theme.DisplaySerifFontFamily
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.icons.filled.ChevronLeft
import kotlinx.coroutines.delay
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.imageResource
import androidx.compose.foundation.layout.heightIn
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.platform.testTag
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.collectAsState
import androidx.compose.animation.togetherWith
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.zIndex
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.BotEntity
import com.example.data.local.MessageEntity
import com.example.data.local.UserSettingsEntity
import com.example.data.repository.KeyCharacter
import com.example.ui.components.BotQuickProfileSheet
import com.example.ui.components.BotSettingsModal
import com.example.ui.components.MoodColors
import com.example.ui.components.OrbView
import com.example.ui.components.TypingDots
import com.example.ui.components.customTextFieldColors
import com.example.ui.theme.EmochiError
import com.example.ui.theme.EmochiSurface

// Helper function to format spoken dialogue and narrative actions in AI messages with mandatory Literary Serif font
fun formatNarrativeText(text: String): AnnotatedString {
    val clean = cleanCopyText(text)
        .replace(
            "gizlemeye çalışarak. \"İstediğimiz",
            "gizlemeye çalışarak.\n\n“İstediğimiz"
        )
        .replace(
            "gizlemeye çalışarak. “İstediğimiz",
            "gizlemeye çalışarak.\n\n“İstediğimiz"
        )
        .replace("\"Tam zamanında geldin Rider,\"", "“Tam zamanında geldin Rider,”")
        .replace("\"İstediğimiz veri çipi elimizde ama izimizdeler. Planı devreye sokmaya hazır mısın?\"", "“İstediğimiz veri çipi elimizde ama izimizdeler. Planı devreye sokmaya hazır mısın?”")
    return buildAnnotatedString {
        val regex = Regex("(\"[^\"]*\")|(“[^”]*”)|(«[^»]*»)|(\\*[^\\*]*\\*)")
        var lastIdx = 0
        val matches = regex.findAll(clean)

        for (match in matches) {
            val start = match.range.first
            val end = match.range.last + 1
            if (start > lastIdx) {
                withStyle(
                    style = SpanStyle(
                        color = Color.White.copy(alpha = 0.95f),
                        fontFamily = LiterarySerifFontFamily
                    )
                ) {
                    append(clean.substring(lastIdx, start))
                }
            }
            val matchedStr = clean.substring(start, end)
            if (matchedStr.startsWith("*") && matchedStr.endsWith("*")) {
                withStyle(
                    style = SpanStyle(
                        color = Color.White.copy(alpha = 0.95f),
                        fontFamily = LiterarySerifFontFamily
                    )
                ) {
                    append(matchedStr)
                }
            } else {
                val curlyDialogue = if (matchedStr.startsWith("\"") && matchedStr.endsWith("\"") && matchedStr.length >= 2) {
                    "“" + matchedStr.substring(1, matchedStr.length - 1) + "”"
                } else {
                    matchedStr
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.White.copy(alpha = 0.98f),
                        fontFamily = LiterarySerifFontFamily
                    )
                ) {
                    append(curlyDialogue)
                }
            }
            lastIdx = end
        }
        if (lastIdx < clean.length) {
            withStyle(
                style = SpanStyle(
                    color = Color.White.copy(alpha = 0.95f),
                    fontFamily = LiterarySerifFontFamily
                )
            ) {
                append(clean.substring(lastIdx))
            }
        }
    }
}

// Generates a genuinely bright, milky-lavender Gaussian frosted bitmap from the actual gothic wisteria background
fun createFrostedWisteriaBackdropBitmap(context: android.content.Context): ImageBitmap {
    return try {
        val opts = android.graphics.BitmapFactory.Options().apply {
            inSampleSize = 2
            inPreferredConfig = android.graphics.Bitmap.Config.ARGB_8888
        }
        val raw = android.graphics.BitmapFactory.decodeResource(
            context.resources,
            com.example.R.drawable.img_chat_bg_aiden_wisteria,
            opts
        ) ?: return ImageBitmap(1, 1)

        val w = 240
        val h = ((raw.height.toFloat() / raw.width.toFloat().coerceAtLeast(1f)) * w).toInt().coerceIn(240, 560)
        val scaled = android.graphics.Bitmap.createScaledBitmap(raw, w, h, true)
        if (raw !== scaled) raw.recycle()

        val pixels = IntArray(w * h)
        scaled.getPixels(pixels, 0, w, 0, 0, w, h)
        val tmp = IntArray(w * h)

        // Two-pass separable box blur (radius 5 on 240px wide ~= 20dp crisp optical frosted glass)
        val radius = 5
        val div = radius * 2 + 1
        repeat(2) {
            // Horizontal pass
            for (y in 0 until h) {
                val rowOffset = y * w
                var rSum = 0
                var gSum = 0
                var bSum = 0
                for (dx in -radius..radius) {
                    val px = pixels[rowOffset + dx.coerceIn(0, w - 1)]
                    rSum += (px ushr 16) and 0xFF
                    gSum += (px ushr 8) and 0xFF
                    bSum += px and 0xFF
                }
                for (x in 0 until w) {
                    tmp[rowOffset + x] = (0xFF shl 24) or ((rSum / div) shl 16) or ((gSum / div) shl 8) or (bSum / div)
                    val nextX = (x + radius + 1).coerceAtMost(w - 1)
                    val prevX = (x - radius).coerceAtLeast(0)
                    val nextPx = pixels[rowOffset + nextX]
                    val prevPx = pixels[rowOffset + prevX]
                    rSum += ((nextPx ushr 16) and 0xFF) - ((prevPx ushr 16) and 0xFF)
                    gSum += ((nextPx ushr 8) and 0xFF) - ((prevPx ushr 8) and 0xFF)
                    bSum += (nextPx and 0xFF) - (prevPx and 0xFF)
                }
            }
            // Vertical pass
            for (x in 0 until w) {
                var rSum = 0
                var gSum = 0
                var bSum = 0
                for (dy in -radius..radius) {
                    val px = tmp[dy.coerceIn(0, h - 1) * w + x]
                    rSum += (px ushr 16) and 0xFF
                    gSum += (px ushr 8) and 0xFF
                    bSum += px and 0xFF
                }
                for (y in 0 until h) {
                    pixels[y * w + x] = (0xFF shl 24) or ((rSum / div) shl 16) or ((gSum / div) shl 8) or (bSum / div)
                    val nextY = (y + radius + 1).coerceAtMost(h - 1)
                    val prevY = (y - radius).coerceAtLeast(0)
                    val nextPx = tmp[nextY * w + x]
                    val prevPx = tmp[prevY * w + x]
                    rSum += ((nextPx ushr 16) and 0xFF) - ((prevPx ushr 16) and 0xFF)
                    gSum += ((nextPx ushr 8) and 0xFF) - ((prevPx ushr 8) and 0xFF)
                    bSum += (nextPx and 0xFF) - (prevPx and 0xFF)
                }
            }
        }

        // Preserve natural stained-glass light vs gothic cathedral depth with subtle cool-plum frost polish (no muddy grey wash)
        for (i in pixels.indices) {
            val px = pixels[i]
            val r = (px ushr 16) and 0xFF
            val g = (px ushr 8) and 0xFF
            val b = px and 0xFF
            val nr = (r * 0.94f + 16f).toInt().coerceIn(0, 255)
            val ng = (g * 0.90f + 10f).toInt().coerceIn(0, 255)
            val nb = (b * 0.97f + 22f).toInt().coerceIn(0, 255)
            pixels[i] = (0xFF shl 24) or (nr shl 16) or (ng shl 8) or nb
        }

        val out = android.graphics.Bitmap.createBitmap(w, h, android.graphics.Bitmap.Config.ARGB_8888)
        out.setPixels(pixels, 0, w, 0, 0, w, h)
        scaled.recycle()
        out.asImageBitmap()
    } catch (_: Throwable) {
        ImageBitmap(1, 1)
    }
}

private val LocalAetheriaRootPos = androidx.compose.runtime.compositionLocalOf { Offset.Zero }

@Composable
fun Modifier.aetheriaBackdropBlur(
    rootSize: androidx.compose.ui.unit.IntSize,
    blurredBitmap: ImageBitmap,
    alpha: Float = 0.94f
): Modifier {
    val rootPos = LocalAetheriaRootPos.current
    val coordsState = remember { mutableStateOf(Offset.Zero) }
    return this
        .onGloballyPositioned { layoutCoordinates ->
            coordsState.value = layoutCoordinates.positionInRoot()
        }
        .drawBehind {
            val imgW = blurredBitmap.width
            val imgH = blurredBitmap.height
            if (imgW > 1 && imgH > 1) {
                val coords = coordsState.value
                val relX = coords.x - rootPos.x
                val relY = coords.y - rootPos.y
                val rW = if (rootSize.width > 0) rootSize.width.toFloat() else size.width
                val rH = if (rootSize.height > 0) rootSize.height.toFloat() else size.height
                val scale = maxOf(rW / imgW.toFloat(), rH / imgH.toFloat())
                val scaledW = imgW * scale
                val scaledH = imgH * scale
                val rootLeft = (rW - scaledW) / 2f
                val rootTop = (rH - scaledH) / 2f
                drawImage(
                    image = blurredBitmap,
                    dstOffset = androidx.compose.ui.unit.IntOffset(
                        kotlin.math.round(rootLeft - relX).toInt(),
                        kotlin.math.round(rootTop - relY).toInt()
                    ),
                    dstSize = androidx.compose.ui.unit.IntSize(
                        kotlin.math.round(scaledW).toInt().coerceAtLeast(1),
                        kotlin.math.round(scaledH).toInt().coerceAtLeast(1)
                    ),
                    alpha = alpha,
                    filterQuality = FilterQuality.High
                )
            }
        }
}

// Stage 1 (BEFORE .clip): Soft ambient dark-plum drop shadow + delicate translucent purple rim aura (never heavy wireframe outline)
fun Modifier.morCamOuterGlow(
    cornerRadius: androidx.compose.ui.unit.Dp = 24.dp,
    shadowAlpha: Float = 0.42f,
    rimGlowAlpha: Float = 0.26f,
    bottomFlareAlpha: Float = 0.45f,
    flareColor: Color = Color(0xFFD8B4FE)
): Modifier = this.drawBehind {
    val cr = cornerRadius.toPx()
    drawIntoCanvas { canvas ->
        val native = canvas.nativeCanvas
        if (shadowAlpha > 0f) {
            val shadowPaint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                style = android.graphics.Paint.Style.FILL
                color = android.graphics.Color.argb((shadowAlpha * 255).toInt().coerceIn(0, 255), 10, 4, 24)
                maskFilter = android.graphics.BlurMaskFilter(12.dp.toPx(), android.graphics.BlurMaskFilter.Blur.NORMAL)
            }
            native.drawRoundRect(
                2.dp.toPx(),
                5.dp.toPx(),
                size.width - 2.dp.toPx(),
                size.height + 5.dp.toPx(),
                cr,
                cr,
                shadowPaint
            )
        }
        if (rimGlowAlpha > 0f) {
            val rimPaint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                style = android.graphics.Paint.Style.STROKE
                strokeWidth = 2.0.dp.toPx()
                color = android.graphics.Color.argb((rimGlowAlpha * 255).toInt().coerceIn(0, 255), 168, 85, 247)
                maskFilter = android.graphics.BlurMaskFilter(5.dp.toPx(), android.graphics.BlurMaskFilter.Blur.NORMAL)
            }
            native.drawRoundRect(0f, 0f, size.width, size.height, cr, cr, rimPaint)
        }
        if (bottomFlareAlpha > 0f) {
            val flarePaint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                style = android.graphics.Paint.Style.STROKE
                strokeWidth = 2.8.dp.toPx()
                color = android.graphics.Color.argb(
                    (bottomFlareAlpha * 170).toInt().coerceIn(0, 255),
                    (flareColor.red * 255).toInt(),
                    (flareColor.green * 255).toInt(),
                    (flareColor.blue * 255).toInt()
                )
                maskFilter = android.graphics.BlurMaskFilter(4.5.dp.toPx(), android.graphics.BlurMaskFilter.Blur.NORMAL)
            }
            native.drawLine(
                size.width * 0.24f,
                size.height,
                size.width * 0.76f,
                size.height,
                flarePaint
            )
        }
    }
}

// Stage 2 (AFTER .background & .border): Razor-crisp beveled glass specular reflections along top & bottom edges
fun Modifier.morCamTopAndBottomSpecular(
    cornerRadius: androidx.compose.ui.unit.Dp = 24.dp,
    topSheenAlpha: Float = 0.24f,
    bottomFlareAlpha: Float = 0.78f,
    flareColor: Color = Color(0xFFF3E8FF)
): Modifier = this.drawWithContent {
    drawContent()
    val inset = cornerRadius.toPx().coerceAtMost(size.width * 0.25f)
    if (topSheenAlpha > 0f && size.width > inset * 2f) {
        drawLine(
            brush = Brush.horizontalGradient(
                listOf(
                    Color.Transparent,
                    Color.White.copy(alpha = topSheenAlpha * 0.75f),
                    Color.White.copy(alpha = topSheenAlpha),
                    Color.White.copy(alpha = topSheenAlpha * 0.75f),
                    Color.Transparent
                ),
                startX = inset * 0.65f,
                endX = size.width - inset * 0.65f
            ),
            start = Offset(inset * 0.65f, 0.75.dp.toPx()),
            end = Offset(size.width - inset * 0.65f, 0.75.dp.toPx()),
            strokeWidth = 0.9.dp.toPx(),
            cap = androidx.compose.ui.graphics.StrokeCap.Round
        )
    }
    if (bottomFlareAlpha > 0f) {
        val sx = size.width * 0.18f
        val ex = size.width * 0.82f
        val y = size.height - 0.65.dp.toPx()
        // Soft inner edge bloom
        drawLine(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    Color.Transparent,
                    flareColor.copy(alpha = bottomFlareAlpha * 0.22f),
                    Color.White.copy(alpha = bottomFlareAlpha * 0.34f),
                    flareColor.copy(alpha = bottomFlareAlpha * 0.22f),
                    Color.Transparent
                ),
                startX = sx,
                endX = ex
            ),
            start = Offset(sx, y - 0.8.dp.toPx()),
            end = Offset(ex, y - 0.8.dp.toPx()),
            strokeWidth = 2.6.dp.toPx(),
            cap = androidx.compose.ui.graphics.StrokeCap.Round
        )
        // Razor-crisp bright rim highlight
        drawLine(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    Color.Transparent,
                    flareColor.copy(alpha = bottomFlareAlpha * 0.55f),
                    Color.White.copy(alpha = bottomFlareAlpha),
                    flareColor.copy(alpha = bottomFlareAlpha * 0.55f),
                    Color.Transparent
                ),
                startX = sx,
                endX = ex
            ),
            start = Offset(sx, y),
            end = Offset(ex, y),
            strokeWidth = 1.1.dp.toPx(),
            cap = androidx.compose.ui.graphics.StrokeCap.Round
        )
    }
}

// 1:1 Reference Cosmic Nebula 'A' Avatar Medallion with Radiant Lens Flares, Inner Mini Diamonds & Metallic Serif Letter
@Composable
fun AidenMagicSigilAvatar(
    totalSize: androidx.compose.ui.unit.Dp,
    contentDescription: String? = null,
    initial: String = "A",
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.size(totalSize),
        contentAlignment = Alignment.Center
    ) {
        val coreSize = totalSize * 0.84f
        val ringRFraction = 0.415f

        // Crisp circular cosmic nebula core
        Box(
            modifier = Modifier
                .size(coreSize)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF42186E),
                            Color(0xFF200B3D),
                            Color(0xFF0E041F)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            // Nebula wisps, stardust & 3/9 o'clock 4-point mini diamonds matching img_chat_aiden_sigil_avatar
            Canvas(modifier = Modifier.fillMaxSize()) {
                val w = size.width
                val h = size.height
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0x99C084FC), Color.Transparent),
                        center = Offset(w * 0.74f, h * 0.78f),
                        radius = w * 0.38f
                    ),
                    radius = w * 0.38f,
                    center = Offset(w * 0.74f, h * 0.78f)
                )
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0x77A855F7), Color.Transparent),
                        center = Offset(w * 0.24f, h * 0.24f),
                        radius = w * 0.34f
                    ),
                    radius = w * 0.34f,
                    center = Offset(w * 0.24f, h * 0.24f)
                )
                // Mini 4-point stars at 9 o'clock and 3 o'clock inside the ring
                val miniStarCenters = listOf(
                    Offset(w * 0.16f, h * 0.50f),
                    Offset(w * 0.84f, h * 0.50f)
                )
                val starR = w * 0.068f
                val starW = w * 0.018f
                miniStarCenters.forEach { c ->
                    val miniPath = Path().apply {
                        moveTo(c.x, c.y - starR)
                        lineTo(c.x + starW, c.y - starW)
                        lineTo(c.x + starR, c.y)
                        lineTo(c.x + starW, c.y + starW)
                        lineTo(c.x, c.y + starR)
                        lineTo(c.x - starW, c.y + starW)
                        lineTo(c.x - starR, c.y)
                        lineTo(c.x - starW, c.y - starW)
                        close()
                    }
                    drawPath(miniPath, Color(0xFFE9D5FF))
                }
                val specks = listOf(
                    Offset(0.24f, 0.30f) to 1.1f,
                    Offset(0.76f, 0.25f) to 1.0f,
                    Offset(0.22f, 0.70f) to 0.9f,
                    Offset(0.75f, 0.75f) to 1.4f,
                    Offset(0.50f, 0.17f) to 0.9f,
                    Offset(0.50f, 0.84f) to 1.0f
                )
                specks.forEach { (pt, rad) ->
                    drawCircle(
                        color = Color(0xFFF3E8FF).copy(alpha = 0.88f),
                        radius = rad.dp.toPx(),
                        center = Offset(w * pt.x, h * pt.y)
                    )
                }
            }

            // Crisp metallic silver-lilac serif initial matching reference medallion
            val aFontSize = (totalSize.value * 0.44f).sp
            Text(
                text = initial.ifBlank { "A" }.take(1).uppercase(),
                fontSize = aFontSize,
                fontFamily = LiterarySerifFontFamily,
                fontWeight = FontWeight.Normal,
                style = TextStyle(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White,
                            Color(0xFFF3E8FF),
                            Color(0xFFD8B4FE)
                        )
                    ),
                    shadow = Shadow(
                        color = Color(0xCCA855F7),
                        offset = Offset(0f, 0f),
                        blurRadius = 10f
                    )
                )
            )
        }

        // Outer neon purple-pink aura + crisp inner circle + diagonal lens-flare glints + delicate outer celestial arc + 4/8 needle points
        Canvas(modifier = Modifier.fillMaxSize()) {
            val cx = size.width / 2f
            val cy = size.height / 2f
            val ringR = size.minDimension * ringRFraction
            val outerTipCard = size.minDimension * 0.495f
            val outerTipDiag = size.minDimension * 0.456f

            drawIntoCanvas { canvas ->
                val native = canvas.nativeCanvas
                val auraPaint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                    style = android.graphics.Paint.Style.STROKE
                    strokeWidth = 3.0.dp.toPx()
                    color = android.graphics.Color.argb(185, 192, 132, 252)
                    maskFilter = android.graphics.BlurMaskFilter(5.dp.toPx(), android.graphics.BlurMaskFilter.Blur.NORMAL)
                }
                native.drawCircle(cx, cy, ringR, auraPaint)
            }

            // Faint outer celestial arc circle
            drawCircle(
                color = Color(0x709333EA),
                radius = ringR * 1.09f,
                center = Offset(cx, cy),
                style = Stroke(width = 0.7.dp.toPx())
            )

            // Crisp luminous inner circle border
            drawCircle(
                brush = Brush.sweepGradient(
                    listOf(
                        Color(0xFFF3E8FF),
                        Color(0xFFC084FC),
                        Color(0xFFFFFFFF),
                        Color(0xFFA855F7),
                        Color(0xFFF3E8FF)
                    )
                ),
                radius = ringR,
                center = Offset(cx, cy),
                style = Stroke(width = 1.25.dp.toPx())
            )

            // Radiant lens-flare bursts on the ring (at ~1:30, ~5:00, ~10:30) just like img_chat_aiden_sigil_avatar
            val flareAngles = listOf(-42.0, 52.0, -135.0)
            flareAngles.forEachIndexed { idx, deg ->
                val rad = Math.toRadians(deg)
                val fx = cx + (Math.cos(rad) * ringR).toFloat()
                val fy = cy + (Math.sin(rad) * ringR).toFloat()
                val fR = if (idx < 2) size.minDimension * 0.14f else size.minDimension * 0.10f
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0xEEFFFFFF), Color(0xAAE879F9), Color(0x44A855F7), Color.Transparent),
                        center = Offset(fx, fy),
                        radius = fR
                    ),
                    radius = fR,
                    center = Offset(fx, fy)
                )
            }

            // 8 sleek, delicate geometric compass/star needle points protruding slightly outside the circle
            for (i in 0 until 8) {
                val angleDeg = i * 45f
                val isCardinal = i % 2 == 0
                val tipR = if (isCardinal) outerTipCard else outerTipDiag
                val baseHalfW = if (isCardinal) size.minDimension * 0.028f else size.minDimension * 0.018f
                rotate(degrees = angleDeg, pivot = Offset(cx, cy)) {
                    val spikePath = Path().apply {
                        moveTo(cx, cy - tipR)
                        lineTo(cx + baseHalfW, cy - ringR * 0.96f)
                        lineTo(cx, cy - ringR * 0.89f)
                        lineTo(cx - baseHalfW, cy - ringR * 0.96f)
                        close()
                    }
                    drawPath(
                        path = spikePath,
                        brush = Brush.verticalGradient(
                            colors = if (isCardinal) {
                                listOf(Color.White, Color(0xFFE9D5FF), Color(0xFFA855F7))
                            } else {
                                listOf(Color(0xFFE9D5FF), Color(0xFFC084FC), Color(0xFF7C3AED))
                            },
                            startY = cy - tipR,
                            endY = cy - ringR * 0.89f
                        )
                    )
                }
            }
        }
    }
}

// 1:1 Reference Purple Verified Shield/Starburst Badge (mor yıldızlı/kalkan onay rozeti içinde beyaz tik ✓)
@Composable
fun PurpleVerifiedBadge(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(16.dp)) {
        val cx = size.width / 2f
        val cy = size.height / 2f
        val outerR = size.minDimension * 0.46f
        val innerR = size.minDimension * 0.36f

        drawIntoCanvas { canvas ->
            val glowPaint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                color = android.graphics.Color.argb(175, 168, 85, 247)
                maskFilter = android.graphics.BlurMaskFilter(3.dp.toPx(), android.graphics.BlurMaskFilter.Blur.NORMAL)
            }
            canvas.nativeCanvas.drawCircle(cx, cy, outerR, glowPaint)
        }

        // 8-scallop starburst/shield polygon
        val badgePath = Path().apply {
            val points = 16
            for (i in 0 until points) {
                val angle = Math.toRadians((i * 360.0 / points) - 90.0)
                val r = if (i % 2 == 0) outerR else innerR
                val px = cx + (Math.cos(angle) * r).toFloat()
                val py = cy + (Math.sin(angle) * r).toFloat()
                if (i == 0) moveTo(px, py) else lineTo(px, py)
            }
            close()
        }
        drawPath(
            path = badgePath,
            brush = Brush.linearGradient(
                listOf(Color(0xFFC084FC), Color(0xFF9333EA), Color(0xFF6D28D9))
            )
        )
        drawPath(
            path = badgePath,
            color = Color(0xCCEDE9FE),
            style = Stroke(width = 0.7.dp.toPx(), join = androidx.compose.ui.graphics.StrokeJoin.Round)
        )

        // Crisp white checkmark (✓) inside
        val checkPath = Path().apply {
            moveTo(cx - outerR * 0.36f, cy + outerR * 0.02f)
            lineTo(cx - outerR * 0.08f, cy + outerR * 0.30f)
            lineTo(cx + outerR * 0.40f, cy - outerR * 0.24f)
        }
        drawPath(
            path = checkPath,
            color = Color.White,
            style = Stroke(
                width = 1.55.dp.toPx(),
                cap = androidx.compose.ui.graphics.StrokeCap.Round,
                join = androidx.compose.ui.graphics.StrokeJoin.Round
            )
        )
    }
}

// Razor-sharp, long-pointed 4-point star (✦) SVG & horizontal light ray on the top border of every bot message bubble (NO blurry white blob!)
@Composable
fun TopBorderGlowingStar(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier.size(width = 104.dp, height = 24.dp)
    ) {
        val cx = size.width / 2f
        val cy = size.height / 2f

        // Thin horizontal light ray extending left and right along the top border
        drawLine(
            brush = Brush.horizontalGradient(
                listOf(
                    Color.Transparent,
                    Color(0x66C084FC),
                    Color(0xFFE9D5FF),
                    Color.White,
                    Color(0xFFE9D5FF),
                    Color(0x66C084FC),
                    Color.Transparent
                )
            ),
            start = Offset(0f, cy),
            end = Offset(size.width, cy),
            strokeWidth = 1.35.dp.toPx(),
            cap = androidx.compose.ui.graphics.StrokeCap.Round
        )

        // Subtle 4-point star aura + tiny pinpoint glow right behind the star core
        drawCircle(
            brush = Brush.radialGradient(
                listOf(Color(0x55FFFFFF), Color(0x38D8B4FE), Color.Transparent),
                center = Offset(cx, cy),
                radius = 7.dp.toPx()
            ),
            radius = 7.dp.toPx(),
            center = Offset(cx, cy)
        )
        val vGlow = 12.dp.toPx()
        val hGlow = 11.dp.toPx()
        val wGlow = 2.4.dp.toPx()
        val glowStar = Path().apply {
            moveTo(cx, cy - vGlow)
            lineTo(cx + wGlow, cy - wGlow)
            lineTo(cx + hGlow, cy)
            lineTo(cx + wGlow, cy + wGlow)
            lineTo(cx, cy + vGlow)
            lineTo(cx - wGlow, cy + wGlow)
            lineTo(cx - hGlow, cy)
            lineTo(cx - wGlow, cy - wGlow)
            close()
        }
        drawPath(
            path = glowStar,
            color = Color(0x66C084FC)
        )

        // Crisp outer lilac/lavender 4-point star (✦)
        val vOuter = 10.5.dp.toPx()
        val hOuter = 9.5.dp.toPx()
        val waistOuter = 1.85.dp.toPx()
        val outerStar = Path().apply {
            moveTo(cx, cy - vOuter)
            lineTo(cx + waistOuter, cy - waistOuter)
            lineTo(cx + hOuter, cy)
            lineTo(cx + waistOuter, cy + waistOuter)
            lineTo(cx, cy + vOuter)
            lineTo(cx - waistOuter, cy + waistOuter)
            lineTo(cx - hOuter, cy)
            lineTo(cx - waistOuter, cy - waistOuter)
            close()
        }
        drawPath(path = outerStar, color = Color(0xFFE9D5FF))

        // Razor-sharp inner pure white 4-point star core (✦)
        val vInner = 8.2.dp.toPx()
        val hInner = 7.2.dp.toPx()
        val waistInner = 1.15.dp.toPx()
        val innerStar = Path().apply {
            moveTo(cx, cy - vInner)
            lineTo(cx + waistInner, cy - waistInner)
            lineTo(cx + hInner, cy)
            lineTo(cx + waistInner, cy + waistInner)
            lineTo(cx, cy + vInner)
            lineTo(cx - waistInner, cy + waistInner)
            lineTo(cx - hInner, cy)
            lineTo(cx - waistInner, cy - waistInner)
            close()
        }
        drawPath(path = innerStar, color = Color.White)
    }
}

// Sleek overlapping double checkmark icon (✓✓) matching reference 22:47 ✓✓, 22:50 ✓✓, 22:53 ✓✓
@Composable
fun DoubleCheckmarkIcon(
    tint: Color = Color(0xFFE9D5FF),
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(width = 16.dp, height = 11.dp)) {
        val w = size.width
        val h = size.height
        val strokeW = 1.45.dp.toPx()
        val tick1 = Path().apply {
            moveTo(w * 0.06f, h * 0.56f)
            lineTo(w * 0.28f, h * 0.82f)
            lineTo(w * 0.66f, h * 0.18f)
        }
        val tick2 = Path().apply {
            moveTo(w * 0.36f, h * 0.56f)
            lineTo(w * 0.56f, h * 0.82f)
            lineTo(w * 0.94f, h * 0.18f)
        }
        drawPath(
            path = tick1,
            color = tint,
            style = Stroke(width = strokeW, cap = androidx.compose.ui.graphics.StrokeCap.Round, join = androidx.compose.ui.graphics.StrokeJoin.Round)
        )
        drawPath(
            path = tick2,
            color = tint,
            style = Stroke(width = strokeW, cap = androidx.compose.ui.graphics.StrokeCap.Round, join = androidx.compose.ui.graphics.StrokeJoin.Round)
        )
    }
}

// 100% Vector-Sharp Icon Badges for the 3 Action Capsules ([Kopyala] [Devam Et] [Diğer])
@Composable
fun GlassPillIconBadge(type: String, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(26.dp)) {
        val cx = size.width / 2f
        val cy = size.height / 2f
        val r = size.minDimension / 2f

        when (type) {
            "copy" -> {
                // Translucent dark circular badge matching img_chat_ref_pills_row
                drawCircle(
                    color = Color(0x881A0B34),
                    radius = r,
                    center = Offset(cx, cy)
                )
                drawCircle(
                    color = Color(0x38D8B4FE),
                    radius = r - 0.5.dp.toPx(),
                    center = Offset(cx, cy),
                    style = Stroke(width = 0.75.dp.toPx())
                )
                val bw = size.width * 0.42f
                val bh = size.height * 0.48f
                val left = cx - bw / 2f
                val top = cy - bh / 2f + 1.dp.toPx()
                // Crisp white Clipboard body
                drawRoundRect(
                    color = Color.White,
                    topLeft = Offset(left, top),
                    size = androidx.compose.ui.geometry.Size(bw, bh),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(2.2.dp.toPx(), 2.2.dp.toPx())
                )
                // Top clip tab
                val cw = bw * 0.54f
                val ch = 2.6.dp.toPx()
                drawRoundRect(
                    color = Color(0xFF1A0B34),
                    topLeft = Offset(cx - cw / 2f, top - 0.6.dp.toPx()),
                    size = androidx.compose.ui.geometry.Size(cw, ch + 1.2.dp.toPx()),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(1.2.dp.toPx(), 1.2.dp.toPx())
                )
                drawRoundRect(
                    color = Color.White,
                    topLeft = Offset(cx - cw * 0.38f, top - 1.2.dp.toPx()),
                    size = androidx.compose.ui.geometry.Size(cw * 0.76f, ch),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(1.dp.toPx(), 1.dp.toPx())
                )
                // 3 dark lines on clipboard
                for (i in 0..2) {
                    val ly = top + bh * (0.42f + i * 0.20f)
                    drawLine(
                        color = Color(0xFF28134A),
                        start = Offset(left + bw * 0.22f, ly),
                        end = Offset(left + bw * 0.78f, ly),
                        strokeWidth = 1.15.dp.toPx(),
                        cap = androidx.compose.ui.graphics.StrokeCap.Round
                    )
                }
            }
            "continue" -> {
                // Dark circular outer recess + bright amber/orange rounded-md square badge
                drawCircle(
                    color = Color(0x881A0B34),
                    radius = r,
                    center = Offset(cx, cy)
                )
                drawCircle(
                    color = Color(0x38D8B4FE),
                    radius = r - 0.5.dp.toPx(),
                    center = Offset(cx, cy),
                    style = Stroke(width = 0.75.dp.toPx())
                )
                val sq = size.minDimension * 0.64f
                val left = cx - sq / 2f
                val top = cy - sq / 2f
                drawIntoCanvas { canvas ->
                    val glowPaint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                        color = android.graphics.Color.argb(175, 245, 158, 11)
                        maskFilter = android.graphics.BlurMaskFilter(4.dp.toPx(), android.graphics.BlurMaskFilter.Blur.NORMAL)
                    }
                    canvas.nativeCanvas.drawRoundRect(left, top, left + sq, top + sq, 4.dp.toPx(), 4.dp.toPx(), glowPaint)
                }
                drawRoundRect(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFFFBBF24), Color(0xFFF97316)),
                        start = Offset(left, top),
                        end = Offset(left + sq, top + sq)
                    ),
                    topLeft = Offset(left, top),
                    size = androidx.compose.ui.geometry.Size(sq, sq),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(4.dp.toPx(), 4.dp.toPx())
                )
                // Crisp opposing arrows/chevrons (><) inside the amber badge
                val chevStroke = 1.5.dp.toPx()
                val leftChev = Path().apply {
                    moveTo(cx - sq * 0.30f, cy - sq * 0.19f)
                    lineTo(cx - sq * 0.08f, cy)
                    lineTo(cx - sq * 0.30f, cy + sq * 0.19f)
                }
                val rightChev = Path().apply {
                    moveTo(cx + sq * 0.30f, cy - sq * 0.19f)
                    lineTo(cx + sq * 0.08f, cy)
                    lineTo(cx + sq * 0.30f, cy + sq * 0.19f)
                }
                drawPath(leftChev, Color.White, style = Stroke(width = chevStroke, cap = androidx.compose.ui.graphics.StrokeCap.Round, join = androidx.compose.ui.graphics.StrokeJoin.Round))
                drawPath(rightChev, Color.White, style = Stroke(width = chevStroke, cap = androidx.compose.ui.graphics.StrokeCap.Round, join = androidx.compose.ui.graphics.StrokeJoin.Round))
            }
            "more" -> {
                // Dark circular badge with 3 horizontal white dots (•••)
                drawCircle(
                    color = Color(0x881A0B34),
                    radius = r,
                    center = Offset(cx, cy)
                )
                drawCircle(
                    color = Color(0x38D8B4FE),
                    radius = r - 0.5.dp.toPx(),
                    center = Offset(cx, cy),
                    style = Stroke(width = 0.75.dp.toPx())
                )
                val dotR = 1.75.dp.toPx()
                val spacing = 4.6.dp.toPx()
                for (i in -1..1) {
                    drawCircle(
                        color = Color.White,
                        radius = dotR,
                        center = Offset(cx + i * spacing, cy)
                    )
                }
            }
        }
    }
}

// 100% Vector-Sharp 3D Circular Icon Well for the 3 Bottom Quick-Action Cards matching img_chat_ref_quick_cards
@Composable
fun QuickActionIconWell(type: String, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(34.dp)) {
        val cx = size.width / 2f
        val cy = size.height / 2f
        val r = size.minDimension / 2f

        // Translucent deep plum circular well with subtle lavender rim
        drawCircle(
            color = Color(0xA61D0D3B),
            radius = r,
            center = Offset(cx, cy)
        )
        drawCircle(
            color = Color(0x4AC084FC),
            radius = r - 0.5.dp.toPx(),
            center = Offset(cx, cy),
            style = Stroke(width = 0.8.dp.toPx())
        )

        when (type) {
            "compass" -> {
                // Crisp 8-pointed purple/lilac compass star SVG icon
                drawIntoCanvas { canvas ->
                    val glowPaint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                        color = android.graphics.Color.argb(150, 192, 132, 252)
                        maskFilter = android.graphics.BlurMaskFilter(3.5.dp.toPx(), android.graphics.BlurMaskFilter.Blur.NORMAL)
                    }
                    canvas.nativeCanvas.drawCircle(cx, cy, r * 0.52f, glowPaint)
                }
                // 4 diagonal points of the 8-point star
                val diagOuter = r * 0.54f
                val diagWaist = r * 0.11f
                rotate(degrees = 45f, pivot = Offset(cx, cy)) {
                    val diagStar = Path().apply {
                        moveTo(cx, cy - diagOuter)
                        lineTo(cx + diagWaist, cy - diagWaist)
                        lineTo(cx + diagOuter, cy)
                        lineTo(cx + diagWaist, cy + diagWaist)
                        lineTo(cx, cy + diagOuter)
                        lineTo(cx - diagWaist, cy + diagWaist)
                        lineTo(cx - diagOuter, cy)
                        lineTo(cx - diagWaist, cy - diagWaist)
                        close()
                    }
                    drawPath(path = diagStar, color = Color(0xFFC084FC))
                }
                // 4 cardinal points of the 8-point compass star
                val outerL = r * 0.78f
                val waist = r * 0.15f
                val starPath = Path().apply {
                    moveTo(cx, cy - outerL)
                    lineTo(cx + waist, cy - waist)
                    lineTo(cx + outerL, cy)
                    lineTo(cx + waist, cy + waist)
                    lineTo(cx, cy + outerL)
                    lineTo(cx - waist, cy + waist)
                    lineTo(cx - outerL, cy)
                    lineTo(cx - waist, cy - waist)
                    close()
                }
                drawPath(
                    path = starPath,
                    brush = Brush.linearGradient(
                        listOf(Color.White, Color(0xFFE9D5FF), Color(0xFFA855F7))
                    )
                )
                drawCircle(color = Color.White, radius = 1.6.dp.toPx(), center = Offset(cx, cy))
            }
            "bulb" -> {
                // Warm golden glow behind lightbulb
                drawIntoCanvas { canvas ->
                    val glowPaint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                        color = android.graphics.Color.argb(170, 251, 191, 36)
                        maskFilter = android.graphics.BlurMaskFilter(4.dp.toPx(), android.graphics.BlurMaskFilter.Blur.NORMAL)
                    }
                    canvas.nativeCanvas.drawCircle(cx, cy - 1.dp.toPx(), r * 0.45f, glowPaint)
                }
                // Radiating light lines around bulb
                val rayAngles = listOf(-150f, -125f, -90f, -55f, -30f, 150f, 30f)
                rayAngles.forEach { deg ->
                    val rad = Math.toRadians(deg.toDouble())
                    val r1 = r * 0.52f
                    val r2 = r * 0.68f
                    drawLine(
                        color = Color(0xFFFDE047),
                        start = Offset(cx + (Math.cos(rad) * r1).toFloat(), (cy - 1.dp.toPx()) + (Math.sin(rad) * r1).toFloat()),
                        end = Offset(cx + (Math.cos(rad) * r2).toFloat(), (cy - 1.dp.toPx()) + (Math.sin(rad) * r2).toFloat()),
                        strokeWidth = 1.25.dp.toPx(),
                        cap = androidx.compose.ui.graphics.StrokeCap.Round
                    )
                }
                // Bulb glass dome
                val bulbR = r * 0.34f
                val bulbCy = cy - r * 0.12f
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0xFFFFFBEB), Color(0xFFFDE047), Color(0xFFF59E0B)),
                        center = Offset(cx - bulbR * 0.25f, bulbCy - bulbR * 0.25f),
                        radius = bulbR * 1.3f
                    ),
                    radius = bulbR,
                    center = Offset(cx, bulbCy)
                )
                // Bulb lower neck
                drawRoundRect(
                    color = Color(0xFFF59E0B),
                    topLeft = Offset(cx - bulbR * 0.56f, bulbCy + bulbR * 0.45f),
                    size = androidx.compose.ui.geometry.Size(bulbR * 1.12f, bulbR * 0.64f),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(2.dp.toPx(), 2.dp.toPx())
                )
                // Screw base
                drawRoundRect(
                    color = Color(0xFFD8B4FE),
                    topLeft = Offset(cx - bulbR * 0.48f, bulbCy + bulbR * 1.12f),
                    size = androidx.compose.ui.geometry.Size(bulbR * 0.96f, 2.dp.toPx()),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(1.dp.toPx(), 1.dp.toPx())
                )
                drawRoundRect(
                    color = Color(0xFFA855F7),
                    topLeft = Offset(cx - bulbR * 0.34f, bulbCy + bulbR * 1.40f),
                    size = androidx.compose.ui.geometry.Size(bulbR * 0.68f, 1.8.dp.toPx()),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(1.dp.toPx(), 1.dp.toPx())
                )
            }
            "pencil" -> {
                // Crisp white linear edit pencil icon
                rotate(degrees = 45f, pivot = Offset(cx, cy)) {
                    val pw = r * 0.34f
                    val ph = r * 0.94f
                    drawRoundRect(
                        color = Color.White,
                        topLeft = Offset(cx - pw / 2f, cy - ph * 0.48f),
                        size = androidx.compose.ui.geometry.Size(pw, ph * 0.78f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(1.6.dp.toPx(), 1.6.dp.toPx()),
                        style = Stroke(width = 1.55.dp.toPx())
                    )
                    drawLine(
                        color = Color.White,
                        start = Offset(cx - pw / 2f, cy - ph * 0.24f),
                        end = Offset(cx + pw / 2f, cy - ph * 0.24f),
                        strokeWidth = 1.45.dp.toPx()
                    )
                    val tipPath = Path().apply {
                        moveTo(cx - pw / 2f, cy + ph * 0.30f)
                        lineTo(cx, cy + ph * 0.56f)
                        lineTo(cx + pw / 2f, cy + ph * 0.30f)
                        close()
                    }
                    drawPath(
                        path = tipPath,
                        color = Color.White,
                        style = Stroke(width = 1.55.dp.toPx(), join = androidx.compose.ui.graphics.StrokeJoin.Round)
                    )
                }
            }
        }
    }
}

@Composable
fun RpAtmosphericBackground() {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF100720))
        )

        androidx.compose.foundation.Image(
            painter = androidx.compose.ui.res.painterResource(id = com.example.R.drawable.img_chat_bg_aiden_wisteria),
            contentDescription = "Purple Stained Glass & Wisteria Gothic Background",
            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ChatScreen(
    bot: BotEntity,
    messages: List<MessageEntity>,
    userSettings: UserSettingsEntity?,
    isSending: Boolean,
    errorMessage: String?,
    keyCharacters: List<KeyCharacter>,
    characterEmotions: List<com.example.data.local.CharacterEmotionEntity> = emptyList(),
    affectionEvents: List<com.example.data.local.AffectionEventEntity> = emptyList(),
    onBack: () -> Unit,
    onSendMessage: (String) -> Unit,
    onContinueScene: (() -> Unit)? = null,
    onRegenerate: () -> Unit,
    onEditMessage: (String, String) -> Unit,
    onDeleteMessage: (String) -> Unit,
    onSaveBotProfile: (BotEntity, List<KeyCharacter>) -> Unit,
    onResetChat: (Boolean) -> Unit,
    onDeleteBot: () -> Unit,
    onSpeakText: ((String) -> Unit)? = null,
    isSpeaking: Boolean = false,
    onStopSpeaking: () -> Unit = {},
    onEnsureOpeningMessage: () -> Unit = {},
    onClearError: () -> Unit = {},
    onRetryMessage: ((String) -> Unit)? = null,
    onRetryFromBanner: (() -> Unit)? = null,
    onRewindToMessage: ((String) -> Unit)? = null,
    onCloneBot: (() -> Unit)? = null,
    streamingMsgId: String? = null,
    streamingText: String? = null
) {
    var inputText by remember { mutableStateOf("") }
    var showBotSettings by remember { mutableStateOf(false) }
    var showQuickProfile by remember { mutableStateOf(false) }
    var showBackgroundPicker by remember { mutableStateOf(false) }
    var showPlusToolkit by remember { mutableStateOf(false) }

    var editingMessageId by remember { mutableStateOf<String?>(null) }
    var editingText by remember { mutableStateOf("") }
    var expandedMenuMessageId by remember { mutableStateOf<String?>(null) }
    var expandedUserMenuMessageId by remember { mutableStateOf<String?>(null) }
    var rewindTargetMessageId by remember { mutableStateOf<String?>(null) }
    var isStatsExpanded by remember { mutableStateOf(false) }
    var showSearchBar by remember { mutableStateOf(false) }
    var localSearchQuery by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val clipboardManager = LocalClipboardManager.current
    val context = androidx.compose.ui.platform.LocalContext.current
    val viewModel: com.example.ui.viewmodel.EmochiViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

    val searchResults by viewModel.searchResults.collectAsState()
    val starredMessages by viewModel.starredMessages.collectAsState()
    var showStarredSheet by remember { mutableStateOf(false) }
    var activeMilestone by remember { mutableStateOf<com.example.data.local.MilestoneEntity?>(null) }
    // Reaction picker state
    var reactionPickerMessageId by remember { mutableStateOf<String?>(null) }
    val reactionEmojis = listOf("❤️", "😂", "😮", "😢", "👍", "🔥")
    val milestoneEvent = viewModel.milestoneEvent

    // Milestone event collector
    LaunchedEffect(Unit) {
        milestoneEvent.collect { milestone ->
            activeMilestone = milestone
            kotlinx.coroutines.delay(4000)
            activeMilestone = null
        }
    }

    // Stop speaking immediately when exiting chat
    DisposableEffect(Unit) {
        onDispose {
            onStopSpeaking()
        }
    }

    // Automatically ensure opening message exists if list is empty (except for book mode)
    val rawValidMessages = remember(messages) {
        messages.filter { it.text.isNotBlank() && !it.id.contains("_ref_msg_") }
    }

    LaunchedEffect(bot.id, rawValidMessages.isEmpty()) {
        if (rawValidMessages.isEmpty() && !isSending && bot.mode != "book") {
            onEnsureOpeningMessage()
        }
    }

    // Scroll to bottom on new messages
    LaunchedEffect(rawValidMessages.size, isSending) {
        val totalItems = rawValidMessages.size + if (isSending) 1 else 0
        if (totalItems > 0) {
            try {
                listState.animateScrollToItem(totalItems - 1)
            } catch (_: Exception) {}
        }
    }

    val isUniverse = bot.mode == "universe"
    val displayName = if (isUniverse) bot.universeName.ifBlank { bot.aiName.ifBlank { "Evren" } } else bot.aiName.ifBlank { bot.universeName.ifBlank { "Karakter" } }
    val avatarInitial = remember(displayName) {
        displayName.trim().firstOrNull { it.isLetterOrDigit() }?.uppercaseChar()?.toString() ?: "A"
    }

    // --- Rich Animations Setup ---
    val infiniteTransition = rememberInfiniteTransition(label = "chatScreenAnimations")

    // 1. Breathing Glow Alpha Pulse (0.35f to 0.90f)
    val pulseGlowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.90f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseGlowAlpha"
    )

    // 2. Tactile Pulse Scale (0.96f to 1.04f)
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.96f,
        targetValue = 1.04f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    // 3. Rotating Sweep Angle for Neon Borders (0f to 360f)
    val sweepRotateAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(7000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "sweepRotateAngle"
    )

    // Mood detection based on last AI text
    val lastAiText = rawValidMessages.lastOrNull { it.role == "assistant" }?.text ?: ""
    val detectedMood = remember(lastAiText) {
        val lower = lastAiText.lowercase()
        when {
            listOf("harika", "süper", "mutlu", "güldü", "🎉", "😊").any { lower.contains(it) } -> "joy"
            listOf("canım", "değerlisin", "sarıl", "güzelim", "sevgi", "💕", "❤️").any { lower.contains(it) } -> "warm"
            listOf("üzgün", "üzülüyorum", "kötü", "yalnız", "😢").any { lower.contains(it) } -> "sad"
            listOf("tehlike", "sinirli", "öfke", "korktu").any { lower.contains(it) } -> "tense"
            listOf("merak", "acaba", "ilginç").any { lower.contains(it) } -> "curious"
            else -> "calm"
        }
    }

    val hue = MoodColors.getMoodHue(detectedMood)
    val emotionState = remember(bot.emotionState) { com.example.data.local.EmotionState.fromJson(bot.emotionState) }
    val realMoodDisplay = "${emotionState.getMoodEmoji()} ${emotionState.mood.replaceFirstChar { it.uppercase() }}"

    val lastAiIndex = rawValidMessages.indexOfLast { it.role == "assistant" }

    val isBookMode = remember(bot) {
        bot.mode == "book" ||
        bot.id == "preset_aiden_mcu_cosmic" ||
        bot.aiName.contains("Kitap") ||
        bot.aiPersonality.contains("KİTAP MODU") ||
        bot.scenario.contains("KİTAP MODU")
    }

    if (isBookMode) {
        BookReaderView(
            bot = bot,
            messages = rawValidMessages,
            isSending = isSending,
            errorMessage = errorMessage,
            onBack = onBack,
            onSendMessage = onSendMessage,
            onEnsureOpeningMessage = onEnsureOpeningMessage,
            onResetChat = { onResetChat(it) },
            onClearError = onClearError
        )
    } else {
    var rootSize by remember { mutableStateOf(androidx.compose.ui.unit.IntSize.Zero) }
    var rootPosInRoot by remember { mutableStateOf(Offset.Zero) }
    val blurredBgBitmap = remember(context) { createFrostedWisteriaBackdropBitmap(context) }
    val headerTitle = displayName
    val validMessages = rawValidMessages

    androidx.compose.runtime.CompositionLocalProvider(LocalAetheriaRootPos provides rootPosInRoot) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged { rootSize = it }
            .onGloballyPositioned { rootPosInRoot = it.positionInRoot() }
    ) {
        val isImeVisible = WindowInsets.isImeVisible

        // Base atmospheric wallpaper (clean seamless wisteria gothic cathedral background)
        RpAtmosphericBackground()

        // Custom wallpaper overlay only when explicitly customized with a custom image path/URL
        if (bot.chatBgUrl.isNotBlank() && bot.chatBgUrl != "USE_AVATAR" && bot.id != "starter_aetheria" && bot.id != "starter_aiden_stories") {
            coil.compose.AsyncImage(
                model = bot.chatBgUrl,
                contentDescription = "Sohbet Arka Planı",
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                alpha = 0.85f
            )
        }

        Scaffold(
            containerColor = Color.Transparent,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            topBar = {
                // ── SECTION 2: ÜST BAR (HEADER) — TRUE TRANSLUCENT GLASS & SMOOTH FEATHERED TRANSITION ──
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color(0x280D041E),
                                    Color(0x0E100522),
                                    Color.Transparent
                                )
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .statusBarsPadding()
                            .padding(top = 6.dp, bottom = 12.dp)
                            .animateContentSize(animationSpec = tween(220, easing = FastOutSlowInEasing)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // 2.1 Sol Geri Butonu (<) — Translucent Frosted Glass Circle
                            Box(
                                modifier = Modifier
                                    .size(43.dp)
                                    .morCamOuterGlow(cornerRadius = 22.dp, shadowAlpha = 0.34f, rimGlowAlpha = 0.24f, bottomFlareAlpha = 0.42f)
                                    .clip(CircleShape)
                                    .aetheriaBackdropBlur(rootSize, blurredBgBitmap, alpha = 0.92f)
                                    .background(
                                        Brush.verticalGradient(
                                            colors = listOf(
                                                Color(0x52301658),
                                                Color(0x6E15082A)
                                            )
                                        )
                                    )
                                    .border(
                                        BorderStroke(
                                            0.85.dp,
                                            Brush.linearGradient(
                                                listOf(Color(0xBCE9D5FF), Color(0x559333EA), Color(0xCCD8B4FE))
                                            )
                                        ),
                                        CircleShape
                                    )
                                    .morCamTopAndBottomSpecular(cornerRadius = 22.dp, topSheenAlpha = 0.24f, bottomFlareAlpha = 0.78f)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null
                                    ) { onBack() },
                                contentAlignment = Alignment.Center
                            ) {
                                Canvas(modifier = Modifier.size(16.dp)) {
                                    val p = Path().apply {
                                        moveTo(size.width * 0.66f, size.height * 0.16f)
                                        lineTo(size.width * 0.28f, size.height * 0.50f)
                                        lineTo(size.width * 0.66f, size.height * 0.84f)
                                    }
                                    drawPath(
                                        path = p,
                                        color = Color(0xFFFBF8FF),
                                        style = Stroke(
                                            width = 2.1.dp.toPx(),
                                            cap = androidx.compose.ui.graphics.StrokeCap.Round,
                                            join = androidx.compose.ui.graphics.StrokeJoin.Round
                                        )
                                    )
                                }
                            }

                            // 2.2 Orta Avatar + Başlık Cam Kapsülü (Sleek Smoky Banner matching img_chat_ref_header_notitle)
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(56.dp)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null
                                    ) {
                                        showBotSettings = true
                                    },
                                contentAlignment = Alignment.CenterStart
                            ) {
                                // Smoky frosted glass banner pill (soft diffused fade into night air, zero sharp wireframe outline)
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(48.dp)
                                        .padding(start = 16.dp)
                                        .clip(RoundedCornerShape(50))
                                        .aetheriaBackdropBlur(rootSize, blurredBgBitmap, alpha = 0.86f)
                                        .background(
                                            Brush.horizontalGradient(
                                                colors = listOf(
                                                    Color(0x8815072A),
                                                    Color(0x6E1B0A35),
                                                    Color(0x36140726),
                                                    Color.Transparent
                                                )
                                            )
                                        )
                                        .padding(start = 36.dp, end = 6.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = headerTitle,
                                                color = Color(0xFFFBF8FF),
                                                fontSize = 14.2.sp,
                                                fontWeight = FontWeight.Normal,
                                                fontFamily = LiterarySerifFontFamily,
                                                letterSpacing = 0.05.sp,
                                                maxLines = 1,
                                                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.weight(1f, fill = false),
                                                style = TextStyle(
                                                    shadow = Shadow(
                                                        color = Color(0xAA120524),
                                                        offset = Offset(0f, 1.5f),
                                                        blurRadius = 6f
                                                    )
                                                )
                                            )
                                            Spacer(modifier = Modifier.width(5.dp))
                                            PurpleVerifiedBadge()
                                        }
                                        Spacer(modifier = Modifier.height(1.dp))
                                        // Ornamental ──── ✦ Evrenler ✦ ──── subtitle
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .width(34.dp)
                                                    .height(0.8.dp)
                                                    .background(
                                                        Brush.horizontalGradient(
                                                            listOf(Color.Transparent, Color(0xBBE9D5FF))
                                                        )
                                                    )
                                            )
                                            Text(
                                                text = " ✦ Evrenler ✦ ",
                                                color = Color(0xFFE9D5FF).copy(alpha = 0.90f),
                                                fontSize = 10.sp,
                                                fontFamily = LiterarySerifFontFamily,
                                                letterSpacing = 0.3.sp
                                            )
                                            Box(
                                                modifier = Modifier
                                                    .width(34.dp)
                                                    .height(0.8.dp)
                                                    .background(
                                                        Brush.horizontalGradient(
                                                            listOf(Color(0xBBE9D5FF), Color.Transparent)
                                                        )
                                                    )
                                            )
                                        }
                                    }
                                }

                                // Left Cosmic Sigil Medallion overlapping the pill edge
                                AidenMagicSigilAvatar(
                                    totalSize = 56.dp,
                                    contentDescription = headerTitle,
                                    initial = avatarInitial,
                                    modifier = Modifier
                                        .align(Alignment.CenterStart)
                                        .zIndex(10f)
                                )
                            }

                            // 2.3 Sağ Arama (🔍) Butonu — Translucent Frosted Glass Circle
                            Box(
                                modifier = Modifier
                                    .size(43.dp)
                                    .morCamOuterGlow(cornerRadius = 22.dp, shadowAlpha = 0.34f, rimGlowAlpha = 0.24f, bottomFlareAlpha = 0.42f)
                                    .clip(CircleShape)
                                    .aetheriaBackdropBlur(rootSize, blurredBgBitmap, alpha = 0.92f)
                                    .background(
                                        Brush.verticalGradient(
                                            colors = listOf(
                                                Color(0x52301658),
                                                Color(0x6E15082A)
                                            )
                                        )
                                    )
                                    .border(
                                        BorderStroke(
                                            0.85.dp,
                                            Brush.linearGradient(
                                                listOf(Color(0xBCE9D5FF), Color(0x559333EA), Color(0xCCD8B4FE))
                                            )
                                        ),
                                        CircleShape
                                    )
                                    .morCamTopAndBottomSpecular(cornerRadius = 22.dp, topSheenAlpha = 0.24f, bottomFlareAlpha = 0.78f)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null
                                    ) {
                                        showSearchBar = !showSearchBar
                                        if (!showSearchBar) {
                                            localSearchQuery = ""
                                            viewModel.clearSearch()
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Ara",
                                    tint = Color(0xFFFBF8FF),
                                    modifier = Modifier.size(19.dp)
                                )
                            }

                            // 2.4 Sağ Ayarlar (⚙️) Butonu — Translucent Frosted Glass Circle
                            Box(
                                modifier = Modifier
                                    .size(43.dp)
                                    .morCamOuterGlow(cornerRadius = 22.dp, shadowAlpha = 0.34f, rimGlowAlpha = 0.24f, bottomFlareAlpha = 0.42f)
                                    .clip(CircleShape)
                                    .aetheriaBackdropBlur(rootSize, blurredBgBitmap, alpha = 0.92f)
                                    .background(
                                        Brush.verticalGradient(
                                            colors = listOf(
                                                Color(0x52301658),
                                                Color(0x6E15082A)
                                            )
                                        )
                                    )
                                    .border(
                                        BorderStroke(
                                            0.85.dp,
                                            Brush.linearGradient(
                                                listOf(Color(0xBCE9D5FF), Color(0x559333EA), Color(0xCCD8B4FE))
                                            )
                                        ),
                                        CircleShape
                                    )
                                    .morCamTopAndBottomSpecular(cornerRadius = 22.dp, topSheenAlpha = 0.24f, bottomFlareAlpha = 0.78f)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null
                                    ) { showBotSettings = true }
                                    .testTag("bot_settings_button"),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "Ayarlar",
                                    tint = Color(0xFFFBF8FF),
                                    modifier = Modifier.size(19.dp)
                                )
                            }
                        }

                        // Search bar (slide-in)
                        AnimatedVisibility(
                            visible = showSearchBar,
                            enter = slideInVertically(tween(220, easing = FastOutSlowInEasing)) { -it } + fadeIn(tween(220)),
                            exit = slideOutVertically(tween(180)) { -it } + fadeOut(tween(180))
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 14.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(14.dp))
                                        .aetheriaBackdropBlur(rootSize, blurredBgBitmap, alpha = 0.28f)
                                        .background(Color(0x66160830))
                                        .border(1.dp, Brush.linearGradient(listOf(Color(0xFFD8B4FE), Color(0xFF7C3AED))), RoundedCornerShape(14.dp))
                                        .padding(horizontal = 14.dp, vertical = 10.dp)
                                ) {
                                    if (localSearchQuery.isEmpty()) {
                                        Text("Mesajlarda ara...", color = Color(0x88E9D5FF), fontSize = 13.sp, fontFamily = LiterarySerifFontFamily)
                                    }
                                    androidx.compose.foundation.text.BasicTextField(
                                        value = localSearchQuery,
                                        onValueChange = { q ->
                                            localSearchQuery = q
                                            viewModel.setSearchQuery(q.ifBlank { null })
                                        },
                                        textStyle = androidx.compose.ui.text.TextStyle(color = Color.White, fontSize = 13.sp, fontFamily = LiterarySerifFontFamily),
                                        singleLine = true,
                                        cursorBrush = androidx.compose.ui.graphics.SolidColor(Color(0xFFA855F7)),
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                                if (localSearchQuery.isNotEmpty()) {
                                    val clrInteraction = remember { MutableInteractionSource() }
                                    Box(
                                        modifier = Modifier
                                            .size(34.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(Color(0x38F87171))
                                            .border(1.dp, Color(0x70F87171), RoundedCornerShape(10.dp))
                                            .clickable(interactionSource = clrInteraction, indication = null) {
                                                localSearchQuery = ""
                                                viewModel.clearSearch()
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(Icons.Default.Close, "Temizle", tint = Color(0xFFF87171), modifier = Modifier.size(16.dp))
                                    }
                                }
                            }
                        }

                        // Search results summary
                        AnimatedVisibility(
                            visible = showSearchBar && searchResults.isNotEmpty(),
                            enter = fadeIn(tween(200)),
                            exit = fadeOut(tween(150))
                        ) {
                            Text(
                                text = "${searchResults.size} sonuç bulundu",
                                color = Color(0xFFD8B4FE),
                                fontSize = 11.sp,
                                fontFamily = LiterarySerifFontFamily,
                                modifier = Modifier.padding(start = 16.dp, bottom = 4.dp)
                            )
                        }

                        AnimatedVisibility(
                            visible = isSpeaking && !isImeVisible,
                            enter = fadeIn(tween(200)) + expandVertically(tween(200)),
                            exit = fadeOut(tween(160)) + shrinkVertically(tween(160))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 14.dp, vertical = 4.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .aetheriaBackdropBlur(rootSize, blurredBgBitmap, alpha = 0.28f)
                                    .background(Color(0x5E1A0A38))
                                    .border(
                                        BorderStroke(
                                            1.dp,
                                            Brush.linearGradient(
                                                listOf(Color(0xBBD8B4FE), Color(0x667C3AED))
                                            )
                                        ),
                                        RoundedCornerShape(14.dp)
                                    )
                                    .padding(horizontal = 14.dp, vertical = 7.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "🔊 Sesli okunuyor...",
                                        color = Color(0xFFE9D5FF),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color(0x38A855F7))
                                            .border(1.dp, Color(0x70D8B4FE), RoundedCornerShape(8.dp))
                                            .clickable { onStopSpeaking() }
                                            .padding(horizontal = 10.dp, vertical = 4.dp)
                                    ) {
                                        Text("⏹️ Durdur", color = Color(0xFFF3E8FF), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                }
            },
            bottomBar = {
                // ── SECTION 5 & SECTION 6: ALT HIZLI ÖNERİ KARTLARI + ALT MESAJ YAZMA BARI (TRUE TRANSLUCENT GLASS & SMOOTH FEATHERED TRANSITION) ──
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color(0x14100422),
                                    Color(0x480D041C)
                                )
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom))
                            .padding(top = 12.dp, bottom = 6.dp)
                    ) {
                        // Interactive (+) RP & Sahne Araçları Drawer
                        AnimatedVisibility(
                            visible = showPlusToolkit,
                            enter = fadeIn(tween(180)) + expandVertically(tween(180)),
                            exit = fadeOut(tween(140)) + shrinkVertically(tween(140))
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState())
                                    .padding(horizontal = 12.dp, vertical = 6.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                val tools = listOf(
                                    "⏩ Devam Et" to {
                                        showPlusToolkit = false
                                        if (!isSending) {
                                            if (onContinueScene != null) onContinueScene()
                                            else viewModel.continueStoryScene()
                                        }
                                    },
                                    "✨ Sahneyi Derinleştir" to {
                                        showPlusToolkit = false
                                        if (!isSending) {
                                            onSendMessage("Mevcut sahneyi kesmeden ortam atmosferini, karakterin iç sesini ve duygusal gerilimi edebi olarak derinleştir.")
                                        }
                                    },
                                    "💡 Ne Yapmalıyım?" to {
                                        showPlusToolkit = false
                                        if (!isSending) {
                                            onSendMessage("Şu anki roman kurgusunda yapabileceğim 3 farklı dramatik hamle (1. Karanlık/Riskli hamle, 2. Tutkulu/Duygusal hamle, 3. Stratejik hamle) öner ve sahneyi sürdür.")
                                        }
                                    },
                                    "✨ *Eylem Ekle*" to {
                                        val t = inputText.trim()
                                        inputText = if (t.isBlank()) "*...*" else if (t.startsWith("*") && t.endsWith("*")) t.trim('*') else "*$t*"
                                        showPlusToolkit = false
                                    },
                                    "🎲 Kader Zarı" to {
                                        showPlusToolkit = false
                                        if (!isSending) onSendMessage("*Beklenmedik bir gelişme yaşanır ve sahnenin dengesi aniden değişir.*")
                                    },
                                    "⏳ Zaman Atla" to {
                                        showPlusToolkit = false
                                        if (!isSending) onSendMessage("*Aradan biraz zaman geçer; sahne bir sonraki kritik ana ilerler.*")
                                    },
                                    "🌌 Ortamı Betimle" to {
                                        showPlusToolkit = false
                                        if (!isSending) onSendMessage("Lütfen şu an bulunduğumuz ortamı, ışığı, sesleri ve atmosferi tüm duyularla hissettirecek şekilde betimle.")
                                    },
                                    "🧠 İç Ses" to {
                                        showPlusToolkit = false
                                        if (!isSending) onSendMessage("Karakterin şu an dışarıya belli etmediği gerçek iç düşüncelerini ve duygularını hissettirerek devam et.")
                                    }
                                )
                                tools.forEach { (label, action) ->
                                    Box(
                                        modifier = Modifier
                                            .height(36.dp)
                                            .morCamOuterGlow(cornerRadius = 18.dp, shadowAlpha = 0.30f, rimGlowAlpha = 0.32f, bottomFlareAlpha = 0.62f)
                                            .clip(RoundedCornerShape(50))
                                            .aetheriaBackdropBlur(rootSize, blurredBgBitmap, alpha = 0.84f)
                                            .background(Color(0x4432165C))
                                            .border(
                                                BorderStroke(1.dp, Color(0xBBE9D5FF)),
                                                RoundedCornerShape(50)
                                            )
                                            .morCamTopAndBottomSpecular(cornerRadius = 18.dp, topSheenAlpha = 0.24f, bottomFlareAlpha = 0.80f)
                                            .clickable { action() }
                                            .padding(horizontal = 14.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = label,
                                            color = Color.White,
                                            fontSize = 12.5.sp,
                                            fontFamily = LiterarySerifFontFamily
                                        )
                                    }
                                }
                            }
                        }

                        // ── SECTION 5: ALT HIZLI ÖNERİ KARTLARI (True Translucent Glass Cards) ──
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            @Composable
                            fun TranslucentQuickActionCard(
                                iconType: String,
                                title: String,
                                subtitle: String,
                                modifier: Modifier = Modifier,
                                enabled: Boolean = true,
                                onClick: () -> Unit
                            ) {
                                val cardInteraction = remember { MutableInteractionSource() }
                                val isCardPressed by cardInteraction.collectIsPressedAsState()
                                val cardScale by animateFloatAsState(
                                    targetValue = if (isCardPressed) 0.97f else 1f,
                                    animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                                    label = "quickCardScale_$iconType"
                                )
                                Box(
                                    modifier = modifier
                                        .height(58.dp)
                                        .graphicsLayer {
                                            scaleX = cardScale
                                            scaleY = cardScale
                                        }
                                        .morCamOuterGlow(cornerRadius = 18.dp, shadowAlpha = 0.34f, rimGlowAlpha = 0.24f, bottomFlareAlpha = 0.44f)
                                        .clip(RoundedCornerShape(18.dp))
                                        .aetheriaBackdropBlur(rootSize, blurredBgBitmap, alpha = 0.94f)
                                        .background(
                                            Brush.verticalGradient(
                                                colors = listOf(
                                                    Color(0x54381C66),
                                                    Color(0x6E1B0B34)
                                                )
                                            )
                                        )
                                        .border(
                                            BorderStroke(
                                                0.9.dp,
                                                Brush.linearGradient(
                                                    listOf(
                                                        Color(0xC8E9D5FF),
                                                        Color(0x589333EA),
                                                        Color(0xC8D8B4FE)
                                                    )
                                                )
                                            ),
                                            RoundedCornerShape(18.dp)
                                        )
                                        .morCamTopAndBottomSpecular(cornerRadius = 18.dp, topSheenAlpha = 0.26f, bottomFlareAlpha = 0.84f)
                                        .clickable(
                                            enabled = enabled,
                                            interactionSource = cardInteraction,
                                            indication = null
                                        ) { onClick() }
                                        .padding(horizontal = 8.dp, vertical = 6.dp),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(7.dp)
                                    ) {
                                        QuickActionIconWell(type = iconType, modifier = Modifier.size(32.dp))
                                        Column(
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = title,
                                                color = Color(0xFFFBF8FF),
                                                fontSize = 11.2.sp,
                                                lineHeight = 13.sp,
                                                fontFamily = LiterarySerifFontFamily,
                                                maxLines = 2,
                                                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                                            )
                                            Text(
                                                text = subtitle,
                                                color = Color(0xFFE9D5FF).copy(alpha = 0.76f),
                                                fontSize = 9.5.sp,
                                                lineHeight = 11.sp,
                                                fontFamily = LiterarySerifFontFamily,
                                                maxLines = 1,
                                                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                                            )
                                        }
                                    }
                                }
                            }

                            // Card 1: Sahneyi Derinleştir
                            TranslucentQuickActionCard(
                                iconType = "compass",
                                title = "Sahneyi\nDerinleştir",
                                subtitle = "Daha fazla ...",
                                modifier = Modifier.weight(1.05f),
                                enabled = !isSending
                            ) {
                                onSendMessage("Mevcut sahneyi kesmeden ortam atmosferini, karakterin iç sesini ve duygusal gerilimi edebi olarak derinleştir.")
                            }

                            // Card 2: Ne Yapmalıyım?
                            TranslucentQuickActionCard(
                                iconType = "bulb",
                                title = "Ne\nYapmalıyım?",
                                subtitle = "Strateji öner",
                                modifier = Modifier.weight(1.08f),
                                enabled = !isSending
                            ) {
                                onSendMessage("Şu anki roman kurgusunda yapabileceğim 3 farklı dramatik hamle (1. Karanlık/Riskli hamle, 2. Tutkulu/Duygusal hamle, 3. Stratejik hamle) öner ve sahneyi sürdür.")
                            }

                            // Card 3: Düzenle
                            TranslucentQuickActionCard(
                                iconType = "pencil",
                                title = "Düzenle",
                                subtitle = "Mesajı değiştir",
                                modifier = Modifier.weight(0.95f),
                                enabled = true
                            ) {
                                val targetMsg = validMessages.lastOrNull()
                                if (targetMsg != null) {
                                    editingMessageId = targetMsg.id
                                    editingText = targetMsg.text
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // ── SECTION 6: ALT MESAJ YAZMA BARI (Nested Double-Glass Capsule matching img_chat_ref_input_bar_notext + Glowing Send Orb) ──
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Outer Frosted Glass Stadium Frame wrapping BOTH (+) Circle AND (Bir mesaj yazın... 📎) Pill
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .heightIn(min = 52.dp)
                                    .morCamOuterGlow(cornerRadius = 26.dp, shadowAlpha = 0.36f, rimGlowAlpha = 0.24f, bottomFlareAlpha = 0.44f)
                                    .clip(RoundedCornerShape(50))
                                    .aetheriaBackdropBlur(rootSize, blurredBgBitmap, alpha = 0.94f)
                                    .background(
                                        Brush.verticalGradient(
                                            colors = listOf(
                                                Color(0x5230175A),
                                                Color(0x6C15082A)
                                            )
                                        )
                                    )
                                    .border(
                                        BorderStroke(
                                            0.9.dp,
                                            Brush.linearGradient(
                                                listOf(Color(0xBCE9D5FF), Color(0x589333EA), Color(0xC8D8B4FE))
                                            )
                                        ),
                                        RoundedCornerShape(50)
                                    )
                                    .morCamTopAndBottomSpecular(cornerRadius = 26.dp, topSheenAlpha = 0.24f, bottomFlareAlpha = 0.80f)
                                    .padding(4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                // 6.1 Sol (+) Butonu — Nested Inner Glass Circle
                                Box(
                                    modifier = Modifier
                                        .size(44.dp)
                                        .clip(CircleShape)
                                        .background(
                                            Brush.verticalGradient(
                                                colors = listOf(
                                                    Color(0x5C241046),
                                                    Color(0x70120726)
                                                )
                                            )
                                        )
                                        .border(
                                            BorderStroke(
                                                0.8.dp,
                                                Brush.linearGradient(
                                                    listOf(Color(0x88D8B4FE), Color(0x457C3AED), Color(0x80C084FC))
                                                )
                                            ),
                                            CircleShape
                                        )
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = null
                                        ) {
                                            showPlusToolkit = !showPlusToolkit
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Canvas(modifier = Modifier.size(18.dp)) {
                                        val cx = size.width / 2f
                                        val cy = size.height / 2f
                                        drawLine(
                                            color = Color.White,
                                            start = Offset(cx, size.height * 0.14f),
                                            end = Offset(cx, size.height * 0.86f),
                                            strokeWidth = 2.dp.toPx(),
                                            cap = androidx.compose.ui.graphics.StrokeCap.Round
                                        )
                                        drawLine(
                                            color = Color.White,
                                            start = Offset(size.width * 0.14f, cy),
                                            end = Offset(size.width * 0.86f, cy),
                                            strokeWidth = 2.dp.toPx(),
                                            cap = androidx.compose.ui.graphics.StrokeCap.Round
                                        )
                                    }
                                }

                                // 6.2 Orta Mesaj Giriş Kapsülü — Nested Inner Glass Pill + Paperclip 📎
                                Row(
                                    modifier = Modifier
                                        .weight(1f)
                                        .heightIn(min = 44.dp)
                                        .clip(RoundedCornerShape(50))
                                        .background(
                                            Brush.verticalGradient(
                                                colors = listOf(
                                                    Color(0x4C220E42),
                                                    Color(0x64120726)
                                                )
                                            )
                                        )
                                        .border(
                                            BorderStroke(
                                                0.8.dp,
                                                Brush.linearGradient(
                                                    listOf(Color(0x78D8B4FE), Color(0x407C3AED), Color(0x70C084FC))
                                                )
                                            ),
                                            RoundedCornerShape(50)
                                        )
                                        .padding(start = 16.dp, end = 10.dp, top = 7.dp, bottom = 7.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier.weight(1f),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        if (inputText.isEmpty()) {
                                            Text(
                                                text = "Bir mesaj yazın...",
                                                color = Color(0xFFE9D5FF).copy(alpha = 0.78f),
                                                fontSize = 15.sp,
                                                fontFamily = LiterarySerifFontFamily
                                            )
                                        }
                                        androidx.compose.foundation.text.BasicTextField(
                                            value = inputText,
                                            onValueChange = { inputText = it },
                                            textStyle = TextStyle(
                                                color = Color.White,
                                                fontSize = 15.5.sp,
                                                fontFamily = LiterarySerifFontFamily
                                            ),
                                            cursorBrush = androidx.compose.ui.graphics.SolidColor(Color(0xFFE9D5FF)),
                                            maxLines = 3,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .testTag("chat_input_field")
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(6.dp))

                                    // 6.3 Sağ İç Ataş (📎) Butonu
                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .clip(CircleShape)
                                            .clickable(
                                                interactionSource = remember { MutableInteractionSource() },
                                                indication = null
                                            ) {
                                                showBackgroundPicker = true
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Canvas(modifier = Modifier.size(18.dp)) {
                                            rotate(degrees = 35f, pivot = Offset(size.width / 2f, size.height / 2f)) {
                                                val w = size.width
                                                val h = size.height
                                                val clipPath = Path().apply {
                                                    moveTo(w * 0.35f, h * 0.72f)
                                                    lineTo(w * 0.35f, h * 0.25f)
                                                    arcTo(
                                                        rect = androidx.compose.ui.geometry.Rect(w * 0.35f, h * 0.08f, w * 0.75f, h * 0.42f),
                                                        startAngleDegrees = 180f,
                                                        sweepAngleDegrees = 180f,
                                                        forceMoveTo = false
                                                    )
                                                    lineTo(w * 0.75f, h * 0.76f)
                                                    arcTo(
                                                        rect = androidx.compose.ui.geometry.Rect(w * 0.25f, h * 0.56f, w * 0.75f, h * 0.94f),
                                                        startAngleDegrees = 0f,
                                                        sweepAngleDegrees = 180f,
                                                        forceMoveTo = false
                                                    )
                                                    lineTo(w * 0.25f, h * 0.32f)
                                                }
                                                drawPath(
                                                    path = clipPath,
                                                    color = Color(0xFFE9D5FF),
                                                    style = Stroke(
                                                        width = 1.65.dp.toPx(),
                                                        cap = androidx.compose.ui.graphics.StrokeCap.Round,
                                                        join = androidx.compose.ui.graphics.StrokeJoin.Round
                                                    )
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                            // 6.4 Sağ Gönder (➤) Butonu — Luminous Neon Purple Crystal Orb
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .morCamOuterGlow(cornerRadius = 25.dp, shadowAlpha = 0.45f, rimGlowAlpha = 0.76f, bottomFlareAlpha = 0.75f)
                                    .clip(CircleShape)
                                    .aetheriaBackdropBlur(rootSize, blurredBgBitmap, alpha = 0.84f)
                                    .background(
                                        Brush.radialGradient(
                                            colors = listOf(
                                                Color(0xEEB86CFF),
                                                Color(0xDA7C3AED),
                                                Color(0xC8581C87)
                                            )
                                        )
                                    )
                                    .border(
                                        BorderStroke(
                                            1.3.dp,
                                            Brush.linearGradient(
                                                listOf(Color.White, Color(0xFFE9D5FF), Color(0xFFC084FC))
                                            )
                                        ),
                                        CircleShape
                                    )
                                    .morCamTopAndBottomSpecular(cornerRadius = 25.dp, topSheenAlpha = 0.36f, bottomFlareAlpha = 0.88f)
                                    .clickable(
                                        enabled = !isSending,
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null
                                    ) {
                                        val text = inputText.trim()
                                        if (text.isNotBlank()) {
                                            inputText = ""
                                            onSendMessage(text)
                                        } else {
                                            if (onContinueScene != null) onContinueScene()
                                            else viewModel.continueStoryScene()
                                        }
                                    }
                                    .testTag("send_message_button"),
                                contentAlignment = Alignment.Center
                            ) {
                                Canvas(modifier = Modifier.size(20.dp)) {
                                    val w = size.width
                                    val h = size.height
                                    val sendPath = Path().apply {
                                        moveTo(w * 0.14f, h * 0.16f)
                                        lineTo(w * 0.90f, h * 0.50f)
                                        lineTo(w * 0.14f, h * 0.84f)
                                        lineTo(w * 0.30f, h * 0.50f)
                                        close()
                                    }
                                    drawPath(
                                        path = sendPath,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
    ) { innerPadding ->
        val topInsetPadding = innerPadding.calculateTopPadding()
        val bottomInsetPadding = innerPadding.calculateBottomPadding()

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                errorMessage?.let { err ->
                    androidx.compose.animation.AnimatedVisibility(
                        visible = true,
                        modifier = Modifier.padding(top = topInsetPadding),
                        enter = androidx.compose.animation.slideInVertically(
                            animationSpec = tween(280, easing = FastOutSlowInEasing)
                        ) { -it } + fadeIn(tween(200)),
                        exit = androidx.compose.animation.slideOutVertically(
                            animationSpec = tween(220)
                        ) { -it } + fadeOut(tween(160))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(
                                    Brush.linearGradient(
                                        listOf(Color(0xCC3D1018), Color(0xCC1F0A10))
                                    )
                                )
                                .border(
                                    BorderStroke(
                                        1.dp,
                                        Brush.linearGradient(
                                            listOf(Color(0xFFF87171), Color(0xFFDC2626), Color(0xFFF87171))
                                        )
                                    ),
                                    RoundedCornerShape(14.dp)
                                )
                                .padding(horizontal = 14.dp, vertical = 10.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .clip(CircleShape)
                                            .background(Color(0x30F87171)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            Icons.Default.Close,
                                            contentDescription = null,
                                            tint = EmochiError,
                                            modifier = Modifier.size(14.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = err,
                                        color = EmochiError,
                                        fontSize = 12.sp,
                                        lineHeight = 16.sp,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    if (onRetryFromBanner != null) {
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(Color(0x40F87171))
                                                .border(1.dp, Color(0x60F87171), RoundedCornerShape(8.dp))
                                                .clickable { onRetryFromBanner(); onClearError() }
                                                .padding(horizontal = 10.dp, vertical = 5.dp)
                                        ) {
                                            Text(
                                                text = "Tekrar",
                                                color = EmochiError,
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(6.dp))
                                    }
                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .clip(CircleShape)
                                            .background(Color(0x25F87171))
                                            .clickable { onClearError() },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            Icons.Default.Close,
                                            contentDescription = "Kapat",
                                            tint = EmochiError,
                                            modifier = Modifier.size(13.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Message region with smooth feathered top & bottom alpha dissolve so it never looks harshly cut off at the top/bottom bars!
                LazyColumn(
                    state = listState,
                    contentPadding = PaddingValues(
                        top = (topInsetPadding + 6.dp).coerceAtLeast(88.dp),
                        bottom = (bottomInsetPadding + 14.dp).coerceAtLeast(144.dp)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .graphicsLayer {
                            compositingStrategy = androidx.compose.ui.graphics.CompositingStrategy.Offscreen
                        }
                        .drawWithContent {
                            drawContent()
                            val h = size.height
                            if (h > 1f) {
                                val topFadeStart = (topInsetPadding.toPx() - 26.dp.toPx()).coerceAtLeast(0f)
                                val topFadeEnd = (topInsetPadding.toPx() + 18.dp.toPx()).coerceAtMost(h * 0.25f)
                                val topFadeMid = (topFadeStart + topFadeEnd) * 0.5f
                                val botFadeStart = (h - bottomInsetPadding.toPx() - 18.dp.toPx()).coerceAtLeast(h * 0.65f)
                                val botFadeEnd = (h - bottomInsetPadding.toPx() + 26.dp.toPx()).coerceAtMost(h)
                                val botFadeMid = (botFadeStart + botFadeEnd) * 0.5f
                                drawRect(
                                    brush = Brush.verticalGradient(
                                        0.0f to Color.Transparent,
                                        (topFadeStart / h).coerceIn(0.01f, 0.18f) to Color.Black.copy(alpha = 0.14f),
                                        (topFadeMid / h).coerceIn(0.02f, 0.22f) to Color.Black.copy(alpha = 0.62f),
                                        (topFadeEnd / h).coerceIn(0.04f, 0.28f) to Color.Black,
                                        (botFadeStart / h).coerceIn(0.68f, 0.94f) to Color.Black,
                                        (botFadeMid / h).coerceIn(0.72f, 0.96f) to Color.Black.copy(alpha = 0.62f),
                                        (botFadeEnd / h).coerceIn(0.75f, 0.99f) to Color.Black.copy(alpha = 0.12f),
                                        1.0f to Color.Transparent
                                    ),
                                    blendMode = androidx.compose.ui.graphics.BlendMode.DstIn
                                )
                            }
                        },
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    itemsIndexed(validMessages, key = { _, msg -> msg.id }) { _, msg ->
                        val isUser = msg.role == "user"

                        val timeFormatted = remember(msg.timestamp) {
                            if (msg.timestamp > 0L) {
                                val sdf = java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
                                sdf.format(java.util.Date(msg.timestamp))
                            } else ""
                        }

                        if (editingMessageId == msg.id) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 14.dp, vertical = 4.dp)
                                    .clip(RoundedCornerShape(18.dp))
                                    .aetheriaBackdropBlur(rootSize, blurredBgBitmap, alpha = 0.28f)
                                    .background(
                                        Brush.linearGradient(
                                            listOf(Color(0x991C0B3B), Color(0x99100624))
                                        )
                                    )
                                    .border(
                                        BorderStroke(
                                            1.5.dp,
                                            Brush.linearGradient(
                                                listOf(Color(0xFFD8B4FE), Color(0xFF9333EA), Color(0xFF6D28D9))
                                            )
                                        ),
                                        RoundedCornerShape(18.dp)
                                    )
                                    .padding(12.dp)
                            ) {
                                Column {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp)
                                                .clip(CircleShape)
                                                .background(Color(0x30A855F7)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text("✏️", fontSize = 10.sp)
                                        }
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = "Mesajı Düzenle",
                                            color = Color(0xFFCDA8FF),
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = LiterarySerifFontFamily
                                        )
                                    }
                                    OutlinedTextField(
                                        value = editingText,
                                        onValueChange = { editingText = it },
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = customTextFieldColors(),
                                        shape = RoundedCornerShape(14.dp)
                                    )
                                    Row(
                                        modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                                        horizontalArrangement = Arrangement.End,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(10.dp))
                                                .background(Color(0x20A855F7))
                                                .border(1.dp, Color(0x40A855F7), RoundedCornerShape(10.dp))
                                                .clickable { editingMessageId = null }
                                                .padding(horizontal = 14.dp, vertical = 7.dp)
                                        ) {
                                            Text("Vazgeç", color = AppColors.TextMuted, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                                        }
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(10.dp))
                                                .background(
                                                    Brush.linearGradient(
                                                        listOf(Color(0xFF9333EA), Color(0xFF5B21B6))
                                                    )
                                                )
                                                .border(
                                                    BorderStroke(
                                                        1.dp,
                                                        Brush.linearGradient(
                                                            listOf(Color(0xFFE9D5FF), Color(0xFFA855F7))
                                                        )
                                                    ),
                                                    RoundedCornerShape(10.dp)
                                                )
                                                .clickable {
                                                    val text = editingText
                                                    editingMessageId = null
                                                    onEditMessage(msg.id, text)
                                                }
                                                .padding(horizontal = 18.dp, vertical = 7.dp)
                                        ) {
                                            Text("Kaydet", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                        }
                                    }
                                }
                            }
                        } else if (isUser) {
                            // ── SECTION 3.1: SAĞ HİZALI KULLANICI MESAJ BALONU (TRUE TRANSLUCENT MOR CAM GLASS) ──
                            val userBubbleShape = RoundedCornerShape(
                                topStart = 22.dp,
                                topEnd = 22.dp,
                                bottomStart = 22.dp,
                                bottomEnd = 18.dp
                            )
                            val userInteraction = remember { MutableInteractionSource() }
                            val isUserPressed by userInteraction.collectIsPressedAsState()
                            val userScale by animateFloatAsState(
                                targetValue = if (isUserPressed) 0.985f else 1f,
                                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                                label = "userBubbleScale_${msg.id}"
                            )
                            var showSecondaryUserActions by remember(msg.id) { mutableStateOf(false) }
                            val userPillsScrollState = rememberScrollState()

                            LaunchedEffect(showSecondaryUserActions) {
                                if (showSecondaryUserActions) {
                                    kotlinx.coroutines.delay(60)
                                    userPillsScrollState.animateScrollTo(260)
                                }
                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 14.dp, vertical = 2.dp),
                                horizontalAlignment = Alignment.End
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(0.79f)
                                        .graphicsLayer {
                                            scaleX = userScale
                                            scaleY = userScale
                                        }
                                        .morCamOuterGlow(
                                            cornerRadius = 22.dp,
                                            shadowAlpha = 0.38f,
                                            rimGlowAlpha = if (isUserPressed) 0.50f else 0.28f,
                                            bottomFlareAlpha = 0.48f
                                        )
                                        .clip(userBubbleShape)
                                        .aetheriaBackdropBlur(rootSize, blurredBgBitmap, alpha = 0.90f)
                                        .background(
                                            Brush.linearGradient(
                                                colors = listOf(
                                                    Color(0x7A6D27CF),
                                                    Color(0x6C45148C),
                                                    Color(0x78561CA6)
                                                )
                                            )
                                        )
                                        .border(
                                            BorderStroke(
                                                0.9.dp,
                                                Brush.linearGradient(
                                                    listOf(
                                                        Color(0xCCE9D5FF),
                                                        Color(0x66A855F7),
                                                        Color(0xD8F3E8FF)
                                                    )
                                                )
                                            ),
                                            userBubbleShape
                                        )
                                        .morCamTopAndBottomSpecular(
                                            cornerRadius = 22.dp,
                                            topSheenAlpha = 0.26f,
                                            bottomFlareAlpha = 0.82f
                                        )
                                        .combinedClickable(
                                            interactionSource = userInteraction,
                                            indication = null,
                                            onClick = {
                                                if (reactionPickerMessageId != null) {
                                                    reactionPickerMessageId = null
                                                } else {
                                                    val willOpen = expandedUserMenuMessageId != msg.id
                                                    expandedUserMenuMessageId = if (willOpen) msg.id else null
                                                    if (!willOpen) showSecondaryUserActions = false
                                                }
                                            },
                                            onLongClick = {
                                                expandedUserMenuMessageId = null
                                                expandedMenuMessageId = null
                                                reactionPickerMessageId = if (reactionPickerMessageId == msg.id) null else msg.id
                                            }
                                        )
                                        .padding(horizontal = 18.dp, vertical = 12.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = formatNarrativeText(cleanCopyText(msg.text)),
                                            color = Color.White.copy(alpha = 0.97f),
                                            fontSize = 15.5.sp,
                                            lineHeight = 23.sp,
                                            letterSpacing = 0.12.sp,
                                            fontFamily = LiterarySerifFontFamily,
                                            style = TextStyle(
                                                shadow = Shadow(
                                                    color = Color(0x77120524),
                                                    offset = Offset(0f, 1f),
                                                    blurRadius = 3f
                                                )
                                            )
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Row(
                                            modifier = Modifier.align(Alignment.End),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = timeFormatted.ifBlank { "22:47" },
                                                color = Color(0xE6E9D5FF),
                                                fontSize = 11.5.sp,
                                                fontWeight = FontWeight.Normal,
                                                fontFamily = InterFontFamily
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            DoubleCheckmarkIcon(tint = Color(0xE6E9D5FF))
                                        }
                                    }
                                }

                                // ── User Message Action Capsules (Single Horizontal Row — Diğer expands to the right!) ──
                                androidx.compose.animation.AnimatedVisibility(
                                    visible = expandedUserMenuMessageId == msg.id && reactionPickerMessageId == null,
                                    enter = fadeIn(tween(180)) + expandVertically(tween(180)),
                                    exit = fadeOut(tween(140)) + shrinkVertically(tween(140))
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .horizontalScroll(userPillsScrollState)
                                            .padding(top = 8.dp, bottom = 2.dp),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        @Composable
                                        fun UserGlassActionPill(
                                            iconType: String? = null,
                                            label: String,
                                            isDanger: Boolean = false,
                                            isActive: Boolean = false,
                                            onClick: () -> Unit
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .height(39.dp)
                                                    .morCamOuterGlow(cornerRadius = 20.dp, shadowAlpha = 0.32f, rimGlowAlpha = 0.24f, bottomFlareAlpha = 0.44f)
                                                    .clip(RoundedCornerShape(50))
                                                    .aetheriaBackdropBlur(rootSize, blurredBgBitmap, alpha = 0.92f)
                                                    .background(
                                                        when {
                                                            isDanger -> Brush.verticalGradient(listOf(Color(0x664A1120), Color(0x762A0812)))
                                                            isActive -> Brush.verticalGradient(listOf(Color(0x665B21B6), Color(0x723B1378)))
                                                            else -> Brush.verticalGradient(listOf(Color(0x5632185A), Color(0x7016092B)))
                                                        }
                                                    )
                                                    .border(
                                                        BorderStroke(
                                                            0.85.dp,
                                                            if (isDanger) Color(0xAAF87171) else Color(0xBCE9D5FF)
                                                        ),
                                                        RoundedCornerShape(50)
                                                    )
                                                    .morCamTopAndBottomSpecular(cornerRadius = 20.dp, topSheenAlpha = 0.24f, bottomFlareAlpha = 0.80f)
                                                    .clickable { onClick() }
                                                    .padding(start = if (iconType != null) 7.dp else 14.dp, end = 14.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(7.dp)
                                            ) {
                                                if (iconType != null) {
                                                    GlassPillIconBadge(type = iconType, modifier = Modifier.size(24.dp))
                                                }
                                                Text(
                                                    text = label,
                                                    color = if (isDanger) Color(0xFFFCA5A5) else Color(0xFFFBF8FF),
                                                    fontSize = 12.5.sp,
                                                    fontFamily = LiterarySerifFontFamily
                                                )
                                            }
                                        }

                                        UserGlassActionPill(iconType = "copy", label = "Kopyala") {
                                            val cleanText = cleanCopyText(msg.text)
                                            clipboardManager.setText(AnnotatedString(cleanText))
                                            android.widget.Toast.makeText(context, "Kopyalandı", android.widget.Toast.LENGTH_SHORT).show()
                                        }

                                        UserGlassActionPill(iconType = "continue", label = "Devam Et") {
                                            if (!isSending) {
                                                expandedUserMenuMessageId = null
                                                if (onContinueScene != null) onContinueScene()
                                                else viewModel.continueStoryScene()
                                            }
                                        }

                                        UserGlassActionPill(
                                            iconType = "more",
                                            label = "Diğer",
                                            isActive = showSecondaryUserActions
                                        ) {
                                            showSecondaryUserActions = !showSecondaryUserActions
                                        }

                                        // Secondary options expand HORIZONTALLY to the right on the exact same row!
                                        androidx.compose.animation.AnimatedVisibility(
                                            visible = showSecondaryUserActions,
                                            enter = androidx.compose.animation.fadeIn(tween(180)) +
                                                androidx.compose.animation.expandHorizontally(tween(220, easing = FastOutSlowInEasing)),
                                            exit = androidx.compose.animation.fadeOut(tween(140)) +
                                                androidx.compose.animation.shrinkHorizontally(tween(180))
                                        ) {
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                UserGlassActionPill(label = "✏️ Düzenle") {
                                                    editingMessageId = msg.id
                                                    editingText = msg.text
                                                    expandedUserMenuMessageId = null
                                                    showSecondaryUserActions = false
                                                }
                                                UserGlassActionPill(label = "⏪ Buraya Sar") {
                                                    rewindTargetMessageId = msg.id
                                                    expandedUserMenuMessageId = null
                                                    showSecondaryUserActions = false
                                                }
                                                UserGlassActionPill(label = "🗑️ Sil", isDanger = true) {
                                                    expandedUserMenuMessageId = null
                                                    showSecondaryUserActions = false
                                                    onDeleteMessage(msg.id)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            // ── SECTION 3.2: SOL HİZALI BOT / HİKAYE MESAJ BALONLARI (TRUE TRANSLUCENT MOR CAM GLASS & ZERO WHITE BOX ON LONG-PRESS!) ──
                            val isThisStreaming = streamingMsgId == msg.id
                            val displayText = if (isThisStreaming && streamingText != null) streamingText else msg.text

                            // Cursor blink animation
                            val cursorInfinite = rememberInfiniteTransition(label = "cursor_${msg.id}")
                            val cursorAlpha by if (isThisStreaming) {
                                cursorInfinite.animateFloat(
                                    initialValue = 1f, targetValue = 0f,
                                    animationSpec = infiniteRepeatable(tween(500), RepeatMode.Reverse),
                                    label = "cursorBlink"
                                )
                            } else {
                                cursorInfinite.animateFloat(
                                    initialValue = 0f, targetValue = 0f,
                                    animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Restart),
                                    label = "cursorOff"
                                )
                            }

                            val botBubbleShape = RoundedCornerShape(24.dp)
                            val botBubbleInteraction = remember { MutableInteractionSource() }
                            val isBotBubblePressed by botBubbleInteraction.collectIsPressedAsState()
                            val isReactionActiveOnThis = reactionPickerMessageId == msg.id
                            val botBubbleScale by animateFloatAsState(
                                targetValue = when {
                                    isBotBubblePressed -> 0.985f
                                    isReactionActiveOnThis -> 1.01f
                                    else -> 1f
                                },
                                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                                label = "botBubbleScale_${msg.id}"
                            )

                            var showSecondaryAiActions by remember(msg.id) { mutableStateOf(false) }
                            val aiPillsScrollState = rememberScrollState()

                            LaunchedEffect(showSecondaryAiActions) {
                                if (showSecondaryAiActions) {
                                    kotlinx.coroutines.delay(60)
                                    aiPillsScrollState.animateScrollTo(320)
                                }
                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp, vertical = 2.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 4.dp)
                                ) {
                                    // True Translucent Glass Bubble Body (Clipped & No White Ripple Box!)
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 25.dp, top = 8.dp)
                                            .graphicsLayer {
                                                scaleX = botBubbleScale
                                                scaleY = botBubbleScale
                                            }
                                            .morCamOuterGlow(
                                                cornerRadius = 22.dp,
                                                shadowAlpha = 0.40f,
                                                rimGlowAlpha = if (isBotBubblePressed || isReactionActiveOnThis) 0.48f else 0.24f,
                                                bottomFlareAlpha = 0.46f
                                            )
                                            .clip(RoundedCornerShape(22.dp))
                                            .aetheriaBackdropBlur(rootSize, blurredBgBitmap, alpha = 0.95f)
                                            .background(
                                                Brush.verticalGradient(
                                                    colors = listOf(
                                                        Color(0x38886CB0),
                                                        Color(0x4C3B2358),
                                                        Color(0x60201038)
                                                    )
                                                )
                                            )
                                            .background(
                                                Brush.linearGradient(
                                                    colors = listOf(
                                                        Color(0x22F3E8FF),
                                                        Color(0x0AD8B4FE),
                                                        Color.Transparent
                                                    ),
                                                    start = Offset.Zero,
                                                    end = Offset(420f, 260f)
                                                )
                                            )
                                            .border(
                                                BorderStroke(
                                                    if (isReactionActiveOnThis) 1.35.dp else 0.85.dp,
                                                    Brush.linearGradient(
                                                        listOf(
                                                            Color(0xCCE9D5FF),
                                                            Color(0x529333EA),
                                                            Color(0xD0E9D5FF)
                                                        )
                                                    )
                                                ),
                                                RoundedCornerShape(22.dp)
                                            )
                                            .morCamTopAndBottomSpecular(
                                                cornerRadius = 22.dp,
                                                topSheenAlpha = 0.26f,
                                                bottomFlareAlpha = 0.84f
                                            )
                                            .combinedClickable(
                                                interactionSource = botBubbleInteraction,
                                                indication = null,
                                                onClick = {
                                                    // CRITICAL GUARD: When reaction picker is open, tapping NEVER opens Kopyala/Devam Et/Diğer!
                                                    if (reactionPickerMessageId != null) {
                                                        reactionPickerMessageId = null
                                                    } else {
                                                        val willOpen = expandedMenuMessageId != msg.id
                                                        expandedMenuMessageId = if (willOpen) msg.id else null
                                                        if (!willOpen) showSecondaryAiActions = false
                                                    }
                                                },
                                                onLongClick = {
                                                    // Close action menus when opening reaction picker
                                                    expandedMenuMessageId = null
                                                    expandedUserMenuMessageId = null
                                                    showSecondaryAiActions = false
                                                    reactionPickerMessageId = if (reactionPickerMessageId == msg.id) null else msg.id
                                                }
                                            )
                                            .padding(
                                                start = 28.dp,
                                                end = 16.dp,
                                                top = 15.dp,
                                                bottom = 12.dp
                                            )
                                    ) {
                                        Column(
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Text(
                                                text = formatNarrativeText(displayText + if (isThisStreaming && cursorAlpha > 0.5f) "▌" else ""),
                                                color = Color.White.copy(alpha = 0.97f),
                                                fontSize = 15.5.sp,
                                                lineHeight = 23.5.sp,
                                                letterSpacing = 0.12.sp,
                                                fontFamily = LiterarySerifFontFamily,
                                                style = TextStyle(
                                                    shadow = Shadow(
                                                        color = Color(0x77120524),
                                                        offset = Offset(0f, 1f),
                                                        blurRadius = 3f
                                                    )
                                                )
                                            )

                                            Spacer(modifier = Modifier.height(6.dp))

                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                // Active reaction badge if user reacted to this message
                                                if (msg.reaction.isNotBlank()) {
                                                    Box(
                                                        modifier = Modifier
                                                            .clip(RoundedCornerShape(50))
                                                            .background(Color(0x553B1568))
                                                            .border(0.9.dp, Color(0xBBE9D5FF), RoundedCornerShape(50))
                                                            .clickable(
                                                                interactionSource = remember { MutableInteractionSource() },
                                                                indication = null
                                                            ) {
                                                                viewModel.setMessageReaction(msg.id, "")
                                                            }
                                                            .padding(horizontal = 8.dp, vertical = 2.dp),
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        Text(text = msg.reaction, fontSize = 13.sp)
                                                    }
                                                } else {
                                                    Spacer(modifier = Modifier.width(1.dp))
                                                }

                                                Row(
                                                    modifier = Modifier
                                                        .clickable(
                                                            interactionSource = remember { MutableInteractionSource() },
                                                            indication = null
                                                        ) {
                                                            if (isSpeaking) onStopSpeaking() else onSpeakText?.invoke(msg.text)
                                                        },
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Text(
                                                        text = timeFormatted.ifBlank { "22:50" },
                                                        color = Color(0xE6E9D5FF),
                                                        fontSize = 11.5.sp,
                                                        fontWeight = FontWeight.Normal,
                                                        fontFamily = InterFontFamily
                                                    )
                                                    Spacer(modifier = Modifier.width(4.dp))
                                                    if (isSpeaking) {
                                                        Text(
                                                            text = "🔊",
                                                            color = Color(0xFFE879F9),
                                                            fontSize = 11.5.sp
                                                        )
                                                    } else {
                                                        DoubleCheckmarkIcon(tint = Color(0xE6E9D5FF))
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    // Razor-sharp 4-point glowing star (✦) on the top border of the bot bubble
                                    TopBorderGlowingStar(
                                        modifier = Modifier
                                            .align(Alignment.TopCenter)
                                            .offset(x = 12.dp, y = (-4).dp)
                                            .zIndex(15f)
                                    )

                                    // Cosmic Sigil Avatar overlapping top-left corner of the bot bubble
                                    AidenMagicSigilAvatar(
                                        totalSize = 46.dp,
                                        contentDescription = displayName,
                                        initial = avatarInitial,
                                        modifier = Modifier
                                            .align(Alignment.TopStart)
                                            .offset(x = (-2).dp, y = (-4).dp)
                                            .zIndex(20f)
                                    )
                                }

                                // ── SECTION 4: MESAJ ALTI AKSİYON KAPSÜLLERİ (Single Horizontal Row — Diğer expands to the right, hidden when reaction picker is open!) ──
                                val showAiPills = expandedMenuMessageId == msg.id && reactionPickerMessageId == null

                                androidx.compose.animation.AnimatedVisibility(
                                    visible = showAiPills,
                                    enter = fadeIn(tween(180)) + expandVertically(tween(180)),
                                    exit = fadeOut(tween(140)) + shrinkVertically(tween(140))
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .horizontalScroll(aiPillsScrollState)
                                            .padding(start = 4.dp, end = 4.dp, top = 8.dp, bottom = 2.dp),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        @Composable
                                        fun BotGlassActionPill(
                                            iconType: String? = null,
                                            label: String,
                                            enabled: Boolean = true,
                                            isDanger: Boolean = false,
                                            isActive: Boolean = false,
                                            onClick: () -> Unit
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .height(39.dp)
                                                    .morCamOuterGlow(cornerRadius = 20.dp, shadowAlpha = 0.32f, rimGlowAlpha = 0.24f, bottomFlareAlpha = 0.44f)
                                                    .clip(RoundedCornerShape(50))
                                                    .aetheriaBackdropBlur(rootSize, blurredBgBitmap, alpha = 0.92f)
                                                    .background(
                                                        when {
                                                            isDanger -> Brush.verticalGradient(listOf(Color(0x664A1120), Color(0x762A0812)))
                                                            isActive -> Brush.verticalGradient(listOf(Color(0x665B21B6), Color(0x723B1378)))
                                                            else -> Brush.verticalGradient(listOf(Color(0x5632185A), Color(0x7016092B)))
                                                        }
                                                    )
                                                    .border(
                                                        BorderStroke(
                                                            0.85.dp,
                                                            if (isDanger) Color(0xAAF87171) else Color(0xBCE9D5FF)
                                                        ),
                                                        RoundedCornerShape(50)
                                                    )
                                                    .morCamTopAndBottomSpecular(cornerRadius = 20.dp, topSheenAlpha = 0.24f, bottomFlareAlpha = 0.80f)
                                                    .clickable(enabled = enabled) { onClick() }
                                                    .padding(start = if (iconType != null) 7.dp else 14.dp, end = 15.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(7.dp)
                                            ) {
                                                if (iconType != null) {
                                                    GlassPillIconBadge(type = iconType, modifier = Modifier.size(25.dp))
                                                }
                                                Text(
                                                    text = label,
                                                    color = if (isDanger) Color(0xFFFCA5A5) else Color(0xFFFBF8FF),
                                                    fontSize = 12.8.sp,
                                                    fontFamily = LiterarySerifFontFamily
                                                )
                                            }
                                        }

                                        // 4.1 Kopyala Capsule
                                        BotGlassActionPill(iconType = "copy", label = "Kopyala") {
                                            val cleanText = cleanCopyText(msg.text)
                                            clipboardManager.setText(AnnotatedString(cleanText))
                                            android.widget.Toast.makeText(context, "Kopyalandı", android.widget.Toast.LENGTH_SHORT).show()
                                        }

                                        // 4.2 Devam Et Capsule
                                        BotGlassActionPill(iconType = "continue", label = "Devam Et", enabled = !isSending) {
                                            expandedMenuMessageId = null
                                            showSecondaryAiActions = false
                                            if (onContinueScene != null) onContinueScene()
                                            else viewModel.continueStoryScene()
                                        }

                                        // 4.3 Diğer Capsule (Toggles horizontal expansion on the same row!)
                                        BotGlassActionPill(
                                            iconType = "more",
                                            label = "Diğer",
                                            isActive = showSecondaryAiActions
                                        ) {
                                            showSecondaryAiActions = !showSecondaryAiActions
                                        }

                                        // Secondary options expand HORIZONTALLY to the right on the exact same row (alt alta değil yana doğru!)
                                        androidx.compose.animation.AnimatedVisibility(
                                            visible = showSecondaryAiActions,
                                            enter = androidx.compose.animation.fadeIn(tween(180)) +
                                                androidx.compose.animation.expandHorizontally(tween(240, easing = FastOutSlowInEasing)),
                                            exit = androidx.compose.animation.fadeOut(tween(140)) +
                                                androidx.compose.animation.shrinkHorizontally(tween(180))
                                        ) {
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                BotGlassActionPill(label = "🔄 Yeniden Oluştur", enabled = !isSending) {
                                                    expandedMenuMessageId = null
                                                    showSecondaryAiActions = false
                                                    onRegenerate()
                                                }
                                                BotGlassActionPill(label = if (isSpeaking) "⏹️ Durdur" else "🎙️ Sesli Oku") {
                                                    if (isSpeaking) onStopSpeaking() else onSpeakText?.invoke(msg.text)
                                                }
                                                BotGlassActionPill(label = if (msg.isStarred) "⭐ Yıldızı Kaldır" else "☆ Yıldızla") {
                                                    viewModel.toggleMessageStar(msg.id)
                                                }
                                                BotGlassActionPill(label = "⏪ Buraya Sar") {
                                                    rewindTargetMessageId = msg.id
                                                    expandedMenuMessageId = null
                                                    showSecondaryAiActions = false
                                                }
                                                BotGlassActionPill(label = "✏️ Düzenle") {
                                                    editingMessageId = msg.id
                                                    editingText = msg.text
                                                    expandedMenuMessageId = null
                                                    showSecondaryAiActions = false
                                                }
                                                BotGlassActionPill(label = "🗑️ Sil", isDanger = true) {
                                                    expandedMenuMessageId = null
                                                    showSecondaryAiActions = false
                                                    onDeleteMessage(msg.id)
                                                }
                                            }
                                        }
                                    }
                                }

                                // 😊 Reaction Picker Popup (Translucent Glass Capsule)
                                androidx.compose.animation.AnimatedVisibility(
                                    visible = reactionPickerMessageId == msg.id,
                                    enter = androidx.compose.animation.fadeIn(tween(150)) +
                                        androidx.compose.animation.scaleIn(tween(150), transformOrigin = androidx.compose.ui.graphics.TransformOrigin(0f, 1f)),
                                    exit = androidx.compose.animation.fadeOut(tween(100)) +
                                        androidx.compose.animation.scaleOut(tween(100), transformOrigin = androidx.compose.ui.graphics.TransformOrigin(0f, 1f))
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .padding(start = 26.dp, top = 6.dp, bottom = 4.dp)
                                            .morCamOuterGlow(cornerRadius = 22.dp, shadowAlpha = 0.38f, rimGlowAlpha = 0.38f, bottomFlareAlpha = 0.72f)
                                            .clip(RoundedCornerShape(22.dp))
                                            .aetheriaBackdropBlur(rootSize, blurredBgBitmap, alpha = 0.84f)
                                            .background(Brush.linearGradient(listOf(Color(0x58381A68), Color(0x4C200D3E))))
                                            .border(1.1.dp, Brush.linearGradient(listOf(Color(0xFFE9D5FF), Color(0xFFA855F7), Color(0xFFD8B4FE))), RoundedCornerShape(22.dp))
                                            .padding(horizontal = 10.dp, vertical = 8.dp)
                                    ) {
                                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                            reactionEmojis.forEach { emoji ->
                                                val isSelected = msg.reaction == emoji
                                                val emojiInteraction = remember { MutableInteractionSource() }
                                                val emojiPressed by emojiInteraction.collectIsPressedAsState()
                                                val emojiScale by animateFloatAsState(
                                                    targetValue = when {
                                                        isSelected -> 1.25f
                                                        emojiPressed -> 0.85f
                                                        else -> 1f
                                                    },
                                                    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                                                    label = "emojiScale_$emoji"
                                                )
                                                Box(
                                                    modifier = Modifier
                                                        .size(36.dp)
                                                        .graphicsLayer { scaleX = emojiScale; scaleY = emojiScale }
                                                        .clip(CircleShape)
                                                        .background(
                                                            if (isSelected)
                                                                Brush.radialGradient(listOf(Color(0xFF9333EA), Color(0xFF4C1D95)))
                                                            else
                                                                Brush.radialGradient(listOf(Color(0x38A855F7), Color(0x10000000)))
                                                        )
                                                        .clickable(interactionSource = emojiInteraction, indication = null) {
                                                            viewModel.setMessageReaction(msg.id, if (isSelected) "" else emoji)
                                                            reactionPickerMessageId = null
                                                        },
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Text(emoji, fontSize = 18.sp)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (isSending) {
                        item(key = "typing_dots_indicator") {
                            androidx.compose.animation.AnimatedVisibility(
                                visible = true,
                                enter = androidx.compose.animation.fadeIn(tween(300)) +
                                    androidx.compose.animation.slideInVertically(tween(300)) { it / 2 },
                                exit  = androidx.compose.animation.fadeOut(tween(200))
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp, end = 48.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .padding(start = 25.dp, top = 8.dp)
                                            .morCamOuterGlow(
                                                cornerRadius = 22.dp,
                                                shadowAlpha = 0.40f,
                                                rimGlowAlpha = 0.24f,
                                                bottomFlareAlpha = 0.46f
                                            )
                                            .clip(RoundedCornerShape(22.dp))
                                            .aetheriaBackdropBlur(rootSize, blurredBgBitmap, alpha = 0.95f)
                                            .background(
                                                Brush.verticalGradient(
                                                    listOf(
                                                        Color(0x38886CB0),
                                                        Color(0x4C3B2358),
                                                        Color(0x60201038)
                                                    )
                                                )
                                            )
                                            .background(
                                                Brush.linearGradient(
                                                    colors = listOf(
                                                        Color(0x22F3E8FF),
                                                        Color(0x0AD8B4FE),
                                                        Color.Transparent
                                                    ),
                                                    start = Offset.Zero,
                                                    end = Offset(420f, 260f)
                                                )
                                            )
                                            .border(
                                                BorderStroke(
                                                    0.85.dp,
                                                    Brush.linearGradient(
                                                        listOf(
                                                            Color(0xCCE9D5FF),
                                                            Color(0x529333EA),
                                                            Color(0xD0E9D5FF)
                                                        )
                                                    )
                                                ),
                                                RoundedCornerShape(22.dp)
                                            )
                                            .morCamTopAndBottomSpecular(
                                                cornerRadius = 22.dp,
                                                topSheenAlpha = 0.26f,
                                                bottomFlareAlpha = 0.84f
                                            )
                                            .padding(start = 28.dp, end = 20.dp, top = 14.dp, bottom = 14.dp)
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            TypingDots()
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Text(
                                                text = "$headerTitle hikâyeyi dokuyor...",
                                                color = Color(0xFFF3E8FF).copy(alpha = 0.94f),
                                                fontSize = 13.5.sp,
                                                fontFamily = LiterarySerifFontFamily
                                            )
                                        }
                                    }

                                    AidenMagicSigilAvatar(
                                        totalSize = 46.dp,
                                        contentDescription = displayName,
                                        initial = avatarInitial,
                                        modifier = Modifier
                                            .align(Alignment.TopStart)
                                            .offset(x = (-2).dp, y = (-4).dp)
                                            .zIndex(20f)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            val showScrollToBottom = remember {
                derivedStateOf {
                    val layoutInfo = listState.layoutInfo
                    val totalItems = layoutInfo.totalItemsCount
                    if (totalItems == 0) return@derivedStateOf false

                    val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf false
                    val itemsFromEnd = totalItems - 1 - lastVisibleItem.index

                    if (itemsFromEnd >= 2) {
                        true
                    } else {
                        // On last visible item: check if scrolled up by at least ~400px
                        val viewportEnd = layoutInfo.viewportEndOffset
                        val itemBottom = lastVisibleItem.offset + lastVisibleItem.size
                        (itemBottom - viewportEnd) > 400
                    }
                }
            }

            if (showScrollToBottom.value) {
                val totalCount = messages.size + if (isSending) 1 else 0
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = bottomInsetPadding + 10.dp, end = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(24.dp))
                            .background(
                                Brush.linearGradient(
                                    listOf(Color(0xFF9333EA), Color(0xFF6D28D9), Color(0xFF4C1D95))
                                )
                            )
                            .border(
                                BorderStroke(
                                    1.5.dp,
                                    Brush.linearGradient(
                                        listOf(Color(0xFFE9D5FF), Color(0xFFA855F7), Color(0xFFC084FC))
                                    )
                                ),
                                RoundedCornerShape(24.dp)
                            )
                            .clickable {
                                coroutineScope.launch {
                                    if (totalCount > 0) {
                                        listState.animateScrollToItem(totalCount - 1)
                                    }
                                }
                            }
                            .padding(horizontal = 16.dp, vertical = 9.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "En aşağı in",
                                tint = Color.White,
                                modifier = Modifier.size(17.dp)
                            )
                            Text(
                                text = "En Aşağı İn",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = 0.3.sp
                            )
                        }
                    }
                }
            }
        }
    }
    }
}

    if (showBackgroundPicker) {
        androidx.compose.ui.window.Dialog(onDismissRequest = { showBackgroundPicker = false }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xF2170A32), Color(0xF50D0520), Color(0xF214082C))
                        )
                    )
                    .border(
                        BorderStroke(
                            1.3.dp,
                            Brush.linearGradient(
                                listOf(Color(0xFFE9D5FF), Color(0xFF9333EA), Color(0xFF60A5FA), Color(0xFFC084FC))
                            )
                        ),
                        RoundedCornerShape(24.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.padding(22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        androidx.compose.foundation.Image(
                            painter = androidx.compose.ui.res.painterResource(id = com.example.R.drawable.img_chat_title_sparkle),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp).padding(end = 6.dp)
                        )
                        Text(
                            text = "Sahne Atmosferi & Arka Plan",
                            color = Color.White,
                            fontSize = 17.5.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(
                                shadow = Shadow(color = Color(0xCCA855F7), offset = Offset(0f, 2f), blurRadius = 12f)
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    androidx.compose.foundation.Image(
                        painter = androidx.compose.ui.res.painterResource(id = com.example.R.drawable.img_chat_header_divider),
                        contentDescription = null,
                        contentScale = androidx.compose.ui.layout.ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth().height(14.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Sohbet ekranında görünmesini istediğiniz atmosfer ve duvar kağıdı seçeneği:",
                        color = Color(0xFFD8B4FE),
                        fontSize = 12.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Option 1: Use Avatar
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Brush.horizontalGradient(listOf(Color(0xFF230E48), Color(0xFF160830))))
                            .border(
                                BorderStroke(1.1.dp, Brush.horizontalGradient(listOf(Color(0xFFD8B4FE), Color(0xFF9333EA)))),
                                RoundedCornerShape(14.dp)
                            )
                            .clickable {
                                onSaveBotProfile(bot.copy(chatBgUrl = "USE_AVATAR"), keyCharacters)
                                showBackgroundPicker = false
                            }
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(Icons.Default.Face, contentDescription = null, tint = Color(0xFFE9D5FF), modifier = Modifier.size(19.dp))
                            Text("Karakter Portresini Arka Plan Yap", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Option 2: Default Aetheria Gothic Theme
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Brush.horizontalGradient(listOf(Color(0xFF1A0D3A), Color(0xFF100724))))
                            .border(
                                BorderStroke(1.1.dp, Brush.horizontalGradient(listOf(Color(0xFF60A5FA), Color(0xFFA855F7)))),
                                RoundedCornerShape(14.dp)
                            )
                            .clickable {
                                onSaveBotProfile(bot.copy(chatBgUrl = ""), keyCharacters)
                                showBackgroundPicker = false
                            }
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = Color(0xFF93C5FD), modifier = Modifier.size(19.dp))
                            Text("Aetheria Gotik Katedral Teması", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Option 3: Custom Gallery Photo / Profile Settings
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Brush.horizontalGradient(listOf(Color(0xFF9333EA), Color(0xFF6366F1))))
                            .border(
                                BorderStroke(1.2.dp, Brush.horizontalGradient(listOf(Color(0xFFF3E8FF), Color(0xFFC084FC)))),
                                RoundedCornerShape(14.dp)
                            )
                            .clickable {
                                showBackgroundPicker = false
                                showBotSettings = true
                            }
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(Icons.Default.Image, contentDescription = null, tint = Color.White, modifier = Modifier.size(19.dp))
                            Text("Galeriden Özel Fotoğraf Seç", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.ExtraBold)
                        }
                    }
                }
            }
        }
    }

    // --- 3.2: Pending Cast Member Banner (non-blocking, sohbet akışını kesmez) ---
    val pendingCastMember by viewModel.pendingCastMember.collectAsState()
    LaunchedEffect(bot.id, messages.size) {
        viewModel.checkForPendingCastMembers(bot.id, messages.size)
    }
    AnimatedVisibility(
        visible = pendingCastMember != null,
        enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(tween(250)),
        exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut(tween(200))
    ) {
        pendingCastMember?.let { pending ->
            androidx.compose.foundation.layout.Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        androidx.compose.ui.graphics.Brush.horizontalGradient(
                            listOf(Color(0xFF1A0A3A), Color(0xFF2D1060))
                        )
                    )
                    .border(1.dp, AppColors.PrimaryViolet.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .testTag("pending_cast_banner")
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text("👤", fontSize = 15.sp)
                        Text(
                            text = "Yeni karakter fark ettim: ${pending.name}",
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.5.sp,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Text(
                        text = pending.description.take(90).ifBlank { pending.role },
                        color = AppColors.TextLight,
                        fontSize = 11.5.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(top = 3.dp)
                    )
                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { viewModel.confirmPendingCastMember(bot.id, pending.id) },
                            modifier = Modifier
                                .height(32.dp)
                                .testTag("pending_cast_confirm_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = AppColors.PrimaryViolet),
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 14.dp, vertical = 0.dp)
                        ) { Text("Evet, ekle", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold) }
                        TextButton(
                            onClick = { viewModel.dismissCastMemberTemporarily(bot.id, pending.id) },
                            modifier = Modifier
                                .height(32.dp)
                                .testTag("pending_cast_dismiss_button"),
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 0.dp)
                        ) { Text("Hayır, önemli değil", color = AppColors.TextSecondary, fontSize = 12.sp) }
                        TextButton(
                            onClick = { viewModel.blacklistAndDismissCastMember(bot.id, pending.id) },
                            modifier = Modifier
                                .height(32.dp)
                                .testTag("pending_cast_blacklist_button"),
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 0.dp)
                        ) { Text("Bir daha sorma", color = AppColors.TextMuted, fontSize = 11.sp) }
                    }
                }
            }
        }
    }

    if (rewindTargetMessageId != null) {
        val targetId = rewindTargetMessageId!!
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { rewindTargetMessageId = null },
            containerColor = AppColors.CardDarkViolet,
            title = {
                Text(
                    text = "⏪ Buradan Devam Et",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            },
            text = {
                Text(
                    text = "Bu noktadan devam edersen sonraki mesajlar ve onlarla ilgili hafıza/duygu değişimi SİLİNECEK, emin misin?",
                    color = AppColors.TextLight,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        val idToRewind = targetId
                        rewindTargetMessageId = null
                        onRewindToMessage?.invoke(idToRewind)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = AppColors.ErrorRedSoft)
                ) {
                    Text("Evet, Devam Et", color = Color.White, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Brush.linearGradient(listOf(Color(0xFF130C2A), Color(0xFF0E0820))))
                        .border(0.8.dp, Color(0x50A855F7), RoundedCornerShape(10.dp))
                        .clickable { rewindTargetMessageId = null }
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("İptal", color = AppColors.TextSecondary, fontWeight = FontWeight.Medium)
                }
            }
        )
    }

    if (showBotSettings) {
        BotSettingsModal(
            bot = bot,
            keyCharacters = keyCharacters,
            characterEmotions = characterEmotions,
            affectionEvents = affectionEvents,
            onDismiss = { showBotSettings = false },
            onSave = onSaveBotProfile,
            onResetChat = onResetChat,
            onDeleteBot = onDeleteBot,
            onCloneBot = onCloneBot
        )
    }

    // 🏆 Milestone Overlay
    AnimatedVisibility(
        visible = activeMilestone != null,
        enter = fadeIn(tween(400)) + scaleIn(tween(400, easing = FastOutSlowInEasing), initialScale = 0.7f),
        exit = fadeOut(tween(500)) + scaleOut(tween(300), targetScale = 0.8f)
    ) {
        val milestone = activeMilestone ?: return@AnimatedVisibility
        val infinite = rememberInfiniteTransition(label = "milestoneGlow")
        val glowAlpha by infinite.animateFloat(0.4f, 1f, infiniteRepeatable(tween(800), RepeatMode.Reverse), label = "mg")
        val particleProgress by infinite.animateFloat(0f, 1f, infiniteRepeatable(tween(2000, easing = LinearEasing)), label = "mp")

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            // Semi-transparent backdrop
            Box(modifier = Modifier.fillMaxSize().background(Color(0x99000000)).clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) { activeMilestone = null })

            // Confetti canvas
            Canvas(modifier = Modifier.fillMaxSize()) {
                val colors = listOf(Color(0xFFF59E0B), Color(0xFFA855F7), Color(0xFF34D399), Color(0xFFF472B6), Color(0xFF60A5FA))
                repeat(30) { i ->
                    val hue = (i * 37f) % 360f
                    val x = size.width * ((i * 0.13f + particleProgress * 0.7f) % 1f)
                    val y = size.height * ((i * 0.17f + particleProgress * 0.4f + i * 0.03f) % 1f)
                    val r = 4f + (i % 5) * 2f
                    drawCircle(color = colors[i % colors.size].copy(alpha = 0.7f), radius = r, center = Offset(x, y))
                }
            }

            // Card
            Column(
                modifier = Modifier.padding(40.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Brush.linearGradient(listOf(Color(0xFF1F0D4A), Color(0xFF0D0820))))
                    .border(BorderStroke(1.5.dp, Brush.linearGradient(listOf(Color(0xFFF59E0B), Color(0xFFD97706)))), RoundedCornerShape(24.dp))
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(milestone.emoji, fontSize = 52.sp, modifier = Modifier.graphicsLayer { alpha = glowAlpha })
                Text("Yeni Kilometre Taşı!", color = Color(0xFFF59E0B), fontSize = 13.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                Text(milestone.title, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                Text("Sevgi: ${milestone.affectionAtTime} · Güven: ${milestone.trustAtTime}", color = Color(0xFFCDA8FF), fontSize = 12.sp)
                Box(
                    modifier = Modifier.clip(RoundedCornerShape(20.dp))
                        .background(Brush.linearGradient(listOf(Color(0xFFF59E0B), Color(0xFFD97706))))
                        .clickable { activeMilestone = null }
                        .padding(horizontal = 24.dp, vertical = 10.dp)
                ) { Text("Harika! ✨", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp) }
            }
        }
    }

    // ⭐ Starred Messages BottomSheet — Premium redesign
    if (showStarredSheet) {
        LaunchedEffect(showStarredSheet) { viewModel.loadStarredMessages() }
        androidx.compose.material3.ModalBottomSheet(
            onDismissRequest = { showStarredSheet = false },
            containerColor = Color(0xFF080614),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            tonalElevation = 0.dp,
            dragHandle = {
                Box(modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)) {
                    Box(
                        modifier = Modifier
                            .width(40.dp).height(4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(Brush.linearGradient(listOf(Color(0xFF9333EA), Color(0xFF6D28D9))))
                    )
                }
            }
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp)) {
                // Premium header
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .background(Brush.verticalGradient(listOf(Color(0xFF1A0D3A), Color(0xFF0D0820))))
                        .padding(horizontal = 20.dp, vertical = 14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            Box(
                                modifier = Modifier.size(36.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Brush.radialGradient(listOf(Color(0xFFF59E0B), Color(0xFFD97706)))),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("⭐", fontSize = 18.sp)
                            }
                            Column {
                                Text(
                                    "Yıldızlanan Mesajlar",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    letterSpacing = (-0.3).sp
                                )
                                Text(
                                    "${starredMessages.size} mesaj kaydedildi",
                                    color = Color(0x80CDA8FF),
                                    fontSize = 11.sp
                                )
                            }
                        }
                        // Close button
                        val closeInteraction = remember { MutableInteractionSource() }
                        val closePressed by closeInteraction.collectIsPressedAsState()
                        val closeScale by animateFloatAsState(if (closePressed) 0.88f else 1f, tween(80), label = "cs")
                        Box(
                            modifier = Modifier
                                .graphicsLayer { scaleX = closeScale; scaleY = closeScale }
                                .size(34.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Brush.linearGradient(listOf(Color(0xFF2A0A0A), Color(0xFF1A0606))))
                                .border(1.dp, Brush.linearGradient(listOf(Color(0xFFF87171), Color(0xFFDC2626))), RoundedCornerShape(10.dp))
                                .clickable(interactionSource = closeInteraction, indication = null) { showStarredSheet = false },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Close, null, tint = Color(0xFFF87171), modifier = Modifier.size(16.dp))
                        }
                    }
                }
                // Gradient separator
                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(
                    Brush.horizontalGradient(listOf(Color.Transparent, Color(0x60F59E0B), Color(0x60A855F7), Color.Transparent))
                ))

                if (starredMessages.isEmpty()) {
                    Box(modifier = Modifier.fillMaxWidth().padding(48.dp), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Box(
                                modifier = Modifier.size(72.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(Brush.radialGradient(listOf(Color(0x30F59E0B), Color(0x10D97706)))),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("⭐", fontSize = 32.sp)
                            }
                            Text("Henüz yıldızlanan mesaj yok", color = Color(0x80CDA8FF), fontSize = 14.sp, fontWeight = FontWeight.Medium)
                            Text("Mesajlara uzun basarak yıldızlayabilirsin", color = Color(0x50CDA8FF), fontSize = 12.sp, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(starredMessages, key = { it.id }) { starred ->
                            val isAi = starred.role == "assistant"
                            val cardInteraction = remember { MutableInteractionSource() }
                            val cardPressed by cardInteraction.collectIsPressedAsState()
                            val cardScale by animateFloatAsState(if (cardPressed) 0.97f else 1f, tween(80), label = "cardScale")

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .graphicsLayer { scaleX = cardScale; scaleY = cardScale }
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Brush.linearGradient(
                                        if (isAi)
                                            listOf(Color(0xFF140D2E), Color(0xFF0C0820))
                                        else
                                            listOf(Color(0xFF220A45), Color(0xFF150630))
                                    ))
                                    .border(
                                        1.2.dp,
                                        Brush.linearGradient(listOf(Color(0xFFF59E0B), Color(0xFFD97706), Color(0xFFF59E0B))),
                                        RoundedCornerShape(16.dp)
                                    )
                                    .clickable(interactionSource = cardInteraction, indication = null) {}
                                    .padding(14.dp)
                            ) {
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    // Header row
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                            Box(
                                                modifier = Modifier.size(22.dp)
                                                    .clip(CircleShape)
                                                    .background(if (isAi)
                                                        Brush.radialGradient(listOf(Color(0xFF7C3AED), Color(0xFF4C1D95)))
                                                    else
                                                        Brush.radialGradient(listOf(Color(0xFF9333EA), Color(0xFF6D28D9)))),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(if (isAi) "🤖" else "👤", fontSize = 10.sp)
                                            }
                                            Text(
                                                if (isAi) bot.aiName else (bot.userCharName.ifBlank { "Sen" }),
                                                color = Color(0xFFF59E0B),
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Text("⭐", fontSize = 12.sp)
                                    }
                                    // Gradient divider
                                    Box(modifier = Modifier.fillMaxWidth().height(0.8.dp).background(
                                        Brush.horizontalGradient(listOf(Color.Transparent, Color(0x40F59E0B), Color.Transparent))
                                    ))
                                    // Message text
                                    Text(
                                        starred.text.take(220) + if (starred.text.length > 220) "…" else "",
                                        color = Color(0xFFE8DEFF),
                                        fontSize = 13.sp,
                                        lineHeight = 20.sp
                                    )
                                    // Unstar button
                                    val unstarInteraction = remember { MutableInteractionSource() }
                                    val unstarPressed by unstarInteraction.collectIsPressedAsState()
                                    val unstarScale by animateFloatAsState(if (unstarPressed) 0.93f else 1f, tween(80), label = "us")
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                        Box(
                                            modifier = Modifier
                                                .graphicsLayer { scaleX = unstarScale; scaleY = unstarScale }
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(Brush.linearGradient(listOf(Color(0xFF2A1500), Color(0xFF1A0E00))))
                                                .border(0.8.dp, Brush.linearGradient(listOf(Color(0xFFF59E0B), Color(0xFFD97706))), RoundedCornerShape(8.dp))
                                                .clickable(interactionSource = unstarInteraction, indication = null) {
                                                    viewModel.toggleMessageStar(starred.id)
                                                }
                                                .padding(horizontal = 10.dp, vertical = 4.dp)
                                        ) {
                                            Text("☆ Yıldızı Kaldır", color = Color(0xFFF59E0B), fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showQuickProfile) {
        val context = LocalContext.current
        BotQuickProfileSheet(
            bot = bot,
            keyCharacters = keyCharacters,
            characterEmotions = characterEmotions,
            affectionEvents = affectionEvents,
            onDismiss = { showQuickProfile = false },
            onSaveBot = { updatedBot ->
                onSaveBotProfile(updatedBot, keyCharacters)
            },
            onOpenFullSettings = { showBotSettings = true },
            onResetChat = { fullReset ->
                showQuickProfile = false
                onResetChat(fullReset)
            },
            onExportChat = {
                val fullText = messages.joinToString("\n\n") { "${if (it.role == "user") bot.userCharName.ifBlank { "Kullanıcı" } else displayName}: ${it.text}" }
                clipboardManager.setText(AnnotatedString(fullText))
                android.widget.Toast.makeText(context, "Tüm sohbet metni panoya kopyalandı!", android.widget.Toast.LENGTH_SHORT).show()
            }
        )
    }
    } // End of else block for non-book mode
}

data class BookChoiceOption(val text: String, val rawLine: String)

fun parseBookChoiceOptions(rawText: String): List<BookChoiceOption> {
    val result = mutableListOf<BookChoiceOption>()
    val lines = rawText.split("\n")
    var inChoiceSection = false

    for (line in lines) {
        val trimmed = line.trim()
        if (trimmed.startsWith("🔀")) {
            inChoiceSection = true
            continue
        }

        val isChoice = trimmed.startsWith("1️⃣") || trimmed.startsWith("2️⃣") || trimmed.startsWith("3️⃣") ||
                trimmed.startsWith("4️⃣") || trimmed.startsWith("5️⃣") || trimmed.startsWith("6️⃣") ||
                trimmed.matches(Regex("^[1-9][0-9]*[\\.\\)]\\s*.*")) ||
                (inChoiceSection && (trimmed.startsWith("-") || trimmed.startsWith("•") || trimmed.startsWith("*")))

        if (isChoice) {
            val choiceText = trimmed
                .replace(Regex("^(1️⃣|2️⃣|3️⃣|4️⃣|5️⃣|6️⃣|\\d+[\\.\\)]|[-•*])\\s*"), "")
                .trim()
            if (choiceText.length >= 2) {
                result.add(BookChoiceOption(choiceText, trimmed))
            }
        }
    }
    return result
}

enum class SummaryViewMode {
    PROMPT,
    SEQUENTIAL,
    FINAL_GRID
}

enum class StepPhase {
    CARD_ONLY,
    RESULT_REVEALED
}

data class ChapterChoiceItem(
    val id: Int,
    val title: String,
    val selectedOption: String,
    val impactText: String,
    val icon: String, // "🔀", "💫", "💭"
    val isBranching: Boolean = false,
    val isRelationship: Boolean = false,
    val impactLevel: String = "orta" // "dusuk", "orta", "kritik"
)

@OptIn(ExperimentalMaterial3Api::class)

private data class CosmicParticle(
    val xRatio: Float,
    val yRatio: Float,
    val radius: Float,
    val baseAlpha: Float,
    val speed: Float
)







fun cleanCopyText(rawText: String): String {
    return com.example.data.repository.EmochiRepository.cleanEmotionTags(rawText)
}