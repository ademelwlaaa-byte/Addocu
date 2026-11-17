package com.example.ui.screens

import com.example.ui.theme.AppColors
import com.example.ui.theme.EmochiGradients

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import com.example.ui.theme.InterFontFamily
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Close
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Speed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import coil.compose.AsyncImage
import com.example.R
import com.example.data.local.BotEntity
import com.example.data.local.UserSettingsEntity
import com.example.ui.components.AppBackground
import com.example.ui.components.GlobalSettingsModal
import com.example.ui.components.MoodColors
import com.example.ui.components.OrbView
import com.example.ui.components.customTextFieldColors
import com.example.ui.theme.EmochiBackground
import com.example.ui.theme.EmochiBorder
import com.example.ui.theme.EmochiCard
import com.example.ui.theme.EmochiError
import com.example.ui.theme.EmochiPrimary
import com.example.ui.theme.EmochiSurface
import com.example.ui.theme.EmochiTextMuted
import com.example.ui.theme.EmochiTextPrimary
import com.example.ui.theme.EmochiTextSecondary

@Composable
fun BotListScreen(
    botList: List<BotEntity>,
    userSettings: UserSettingsEntity?,
    onOpenBot: (String) -> Unit,
    onNewBot: () -> Unit,
    onDeleteBot: (String) -> Unit,
    onSaveSettings: (UserSettingsEntity) -> Unit,
    onExportData: suspend () -> String,
    onImportData: suspend (String) -> Unit,
    onImportPresetBot: ((BotEntity) -> Unit)? = null,
    onTogglePrivacy: ((BotEntity) -> Unit)? = null,
    onCloneBot: ((String) -> Unit)? = null
) {
    var showGlobalSettings by remember { mutableStateOf(false) }
    var showAidenStoriesMenu by remember { mutableStateOf(false) }
    var confirmDeleteId by remember { mutableStateOf<String?>(null) }
    var selectedFilter by remember { mutableStateOf("all") } // "all", "personal", "universe"
    var searchQuery by remember { mutableStateOf("") }
    var activeTab by androidx.compose.runtime.saveable.rememberSaveable { mutableStateOf("chats") } // "chats", "discover", "templates", "books"

    val chatsListState = androidx.compose.foundation.lazy.rememberLazyListState()
    val templatesListState = androidx.compose.foundation.lazy.rememberLazyListState()
    val discoverListState = androidx.compose.foundation.lazy.rememberLazyListState()
    val booksListState = androidx.compose.foundation.lazy.rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // System Back button closes Aiden Stories menu or returns to "chats" tab first before exiting
    BackHandler(enabled = showAidenStoriesMenu || activeTab != "chats") {
        if (showAidenStoriesMenu) {
            showAidenStoriesMenu = false
        } else {
            activeTab = "chats"
            coroutineScope.launch { chatsListState.scrollToItem(0) }
        }
    }

    var bottomBarTopInRootPx by remember { mutableStateOf(0f) }
    var contentTopInRootPx by remember { mutableStateOf(0f) }
    val context = LocalContext.current
    val frostedManorBitmap = remember { createFrostedGothicManorBitmap(context, R.drawable.img_templates_bg_gothic_manor) }
    val frostedCosmicBitmap = remember { createFrostedGothicManorBitmap(context, R.drawable.img_botlist_cosmic_bg) }
    val bgSeamlessEasing = remember { androidx.compose.animation.core.CubicBezierEasing(0.22f, 1.0f, 0.36f, 1.0f) }
    val templatesBgProgress by animateFloatAsState(
        targetValue = if (activeTab == "templates") 1f else 0f,
        animationSpec = tween(durationMillis = 600, easing = bgSeamlessEasing),
        label = "bottomBarTemplatesBgProgress"
    )
    val activeScrollSync = when (activeTab) {
        "templates" -> templatesListState.firstVisibleItemIndex * 100000 + templatesListState.firstVisibleItemScrollOffset
        "discover"  -> discoverListState.firstVisibleItemIndex * 100000 + discoverListState.firstVisibleItemScrollOffset
        "books"     -> booksListState.firstVisibleItemIndex * 100000 + booksListState.firstVisibleItemScrollOffset
        else        -> chatsListState.firstVisibleItemIndex * 100000 + chatsListState.firstVisibleItemScrollOffset
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AppBackground(activeTab = activeTab)
        Scaffold(
            containerColor = Color.Transparent,
            contentWindowInsets = androidx.compose.foundation.layout.WindowInsets(0, 0, 0, 0),
        bottomBar = {
            // Infinite transitions for live neon glow, center orb pulse, and specular shimmer
            val navInfinite = rememberInfiniteTransition(label = "navInfinite")
            val centerPulseScale by navInfinite.animateFloat(
                initialValue = 0.96f, targetValue = 1.08f,
                animationSpec = infiniteRepeatable(tween(1600, easing = FastOutSlowInEasing), RepeatMode.Reverse),
                label = "centerPulse"
            )
            val centerPulseAlpha by navInfinite.animateFloat(
                initialValue = 0.55f, targetValue = 0.95f,
                animationSpec = infiniteRepeatable(tween(1600, easing = FastOutSlowInEasing), RepeatMode.Reverse),
                label = "centerPulseAlpha"
            )
            val orbRingRot by navInfinite.animateFloat(
                initialValue = 0f, targetValue = 360f,
                animationSpec = infiniteRepeatable(tween(9000, easing = LinearEasing), RepeatMode.Restart),
                label = "orbRingRot"
            )

            data class NavTab(val key: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val label: String)
            val tabs = listOf(
                NavTab("discover",  Icons.Default.Public,   "Keşfet"),
                NavTab("chats",     Icons.Default.Forum,    "Sohbet"),
                NavTab("templates", Icons.Default.Star,     "Şablon"),
                NavTab("books",     Icons.Default.MenuBook, "Kitap")
            )
            val listStates = mapOf(
                "discover"  to discoverListState,
                "chats"     to chatsListState,
                "templates" to templatesListState,
                "books"     to booksListState
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                // True See-Through Frosted Mor Cam Rounded Dock (y=1630..1804 in reference)
                // Sheer 20%..27% glass glaze over 9dp optical frost backdrop so the courtyard reflections
                // and dark wisteria foliage of AppBackground shine clearly through the glass!
                val dockShape = RoundedCornerShape(34.dp)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(74.dp)
                        .align(Alignment.Center)
                        .then(
                            Modifier.let {
                                androidx.compose.ui.Modifier.onGloballyPositioned { coords ->
                                    bottomBarTopInRootPx = coords.positionInRoot().y
                                }
                            }
                        )
                        .drawBehind {
                            drawIntoCanvas { canvas ->
                                val paint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                                    style = android.graphics.Paint.Style.STROKE
                                    strokeWidth = 1.6.dp.toPx()
                                    color = Color(0x389333EA).toArgb()
                                    maskFilter = android.graphics.BlurMaskFilter(
                                        5.dp.toPx(),
                                        android.graphics.BlurMaskFilter.Blur.NORMAL
                                    )
                                }
                                canvas.nativeCanvas.drawRoundRect(
                                    0f,
                                    0f,
                                    size.width,
                                    size.height,
                                    34.dp.toPx(),
                                    34.dp.toPx(),
                                    paint
                                )
                            }
                        }
                        .clip(dockShape)
                        .templatesBackdropBlur(
                            blurredBitmap = frostedCosmicBitmap,
                            secondaryBitmap = frostedManorBitmap,
                            secondaryProgress = templatesBgProgress,
                            alpha = 0.72f,
                            scrollSync = activeScrollSync
                        )
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color(0x42381E5E),
                                    Color(0x321A0C32),
                                    Color(0x40321956)
                                )
                            )
                        )
                        .drawBehind {
                            val cr = 34.dp.toPx()
                            // 1. Subtle top inner frosted glass shelf refraction
                            drawRect(
                                brush = Brush.verticalGradient(
                                    0.00f to Color(0x24C4B5FD),
                                    0.20f to Color.Transparent,
                                    0.82f to Color.Transparent,
                                    1.00f to Color(0x1C7C3AED)
                                )
                            )
                            // 2. Diagonal specular glass reflection band
                            drawRect(
                                brush = Brush.linearGradient(
                                    0.00f to Color.White.copy(alpha = 0.04f),
                                    0.35f to Color.Transparent,
                                    0.70f to Color(0xFFE9D5FF).copy(alpha = 0.02f),
                                    1.00f to Color.White.copy(alpha = 0.04f),
                                    start = Offset(0f, 0f),
                                    end = Offset(size.width, size.height)
                                )
                            )
                            // 3. Calm inner frosted perimeter bevel
                            drawIntoCanvas { canvas ->
                                val edgeFrost = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                                    style = android.graphics.Paint.Style.STROKE
                                    strokeWidth = 5.dp.toPx()
                                    color = android.graphics.Color.argb(36, 230, 214, 255)
                                    maskFilter = android.graphics.BlurMaskFilter(4.dp.toPx(), android.graphics.BlurMaskFilter.Blur.NORMAL)
                                }
                                canvas.nativeCanvas.drawRoundRect(0f, 0f, size.width, size.height, cr, cr, edgeFrost)
                            }
                            // 4. Delicate top-border specular glint between Sohbet and + Oluştur (x = 0.35f)
                            drawCircle(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        Color.White.copy(alpha = 0.26f),
                                        Color(0xFFE9D5FF).copy(alpha = 0.12f),
                                        Color.Transparent
                                    ),
                                    center = Offset(size.width * 0.35f, 0f),
                                    radius = size.width * 0.16f
                                ),
                                center = Offset(size.width * 0.35f, 0f),
                                radius = size.width * 0.16f
                            )
                            // 5. Center ambient violet glass refraction around + Oluştur
                            drawCircle(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        Color(0x229333EA),
                                        Color(0x106D28D9),
                                        Color.Transparent
                                    ),
                                    center = Offset(size.width * 0.50f, size.height * 0.5f),
                                    radius = size.width * 0.32f
                                ),
                                center = Offset(size.width * 0.50f, size.height * 0.5f),
                                radius = size.width * 0.32f
                            )
                        }
                        .border(
                            BorderStroke(
                                1.15.dp,
                                Brush.horizontalGradient(
                                    0.00f to Color(0x889F7AEA),
                                    0.24f to Color(0xAAC8B2F0),
                                    0.35f to Color(0xDCE9D5FF),
                                    0.50f to Color(0xCCE2D0F8),
                                    0.72f to Color(0xB8C4A8EC),
                                    1.00f to Color(0x889F7AEA)
                                )
                            ),
                            dockShape
                        )
                        .padding(horizontal = 6.dp, vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Helper renderer for all 4 navigation items so whichever tab is active
                        // gets the exact reference tall see-through Mor Cam selection capsule
                        @Composable
                        fun RenderNavTabItem(
                            tab: NavTab,
                            isSelected: Boolean,
                            modifier: Modifier = Modifier
                        ) {
                            val iconScale by animateFloatAsState(
                                targetValue = if (isSelected) 1.05f else 1.0f,
                                animationSpec = androidx.compose.animation.core.spring(
                                    dampingRatio = 0.60f,
                                    stiffness = 380f
                                ),
                                label = "navTabScale_${tab.key}"
                            )
                            val tabShape = RoundedCornerShape(24.dp)

                            Box(
                                modifier = modifier
                                    .fillMaxHeight()
                                    .padding(horizontal = 2.dp)
                                    .graphicsLayer {
                                        scaleX = iconScale
                                        scaleY = iconScale
                                    }
                                    .then(
                                        if (isSelected) {
                                            // True See-Through 3D Frosted Purple Glass Active Tab Capsule
                                            Modifier
                                                .drawBehind {
                                                    drawIntoCanvas { canvas ->
                                                        val outerGlow = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                                                            style = android.graphics.Paint.Style.STROKE
                                                            strokeWidth = 1.8.dp.toPx()
                                                            color = android.graphics.Color.argb(55, 180, 120, 240)
                                                            maskFilter = android.graphics.BlurMaskFilter(
                                                                5.dp.toPx(),
                                                                android.graphics.BlurMaskFilter.Blur.NORMAL
                                                            )
                                                        }
                                                        val r = 24.dp.toPx()
                                                        canvas.nativeCanvas.drawRoundRect(0f, 0f, size.width, size.height, r, r, outerGlow)
                                                    }
                                                }
                                                .clip(tabShape)
                                                .background(
                                                    Brush.verticalGradient(
                                                        0.00f to Color(0x629054D2),
                                                        0.28f to Color(0x426834A4),
                                                        0.60f to Color(0x3228124A),
                                                        0.85f to Color(0x4454268C),
                                                        1.00f to Color(0x608446C8)
                                                    )
                                                )
                                                .drawBehind {
                                                    val r = 24.dp.toPx()
                                                    drawIntoCanvas { canvas ->
                                                        val innerRim = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                                                            style = android.graphics.Paint.Style.STROKE
                                                            strokeWidth = 4.5.dp.toPx()
                                                            color = android.graphics.Color.argb(38, 237, 228, 255)
                                                            maskFilter = android.graphics.BlurMaskFilter(4.dp.toPx(), android.graphics.BlurMaskFilter.Blur.NORMAL)
                                                        }
                                                        canvas.nativeCanvas.drawRoundRect(0f, 0f, size.width, size.height, r, r, innerRim)
                                                    }
                                                    // Subtle top-center & bottom-center specular flares
                                                    drawCircle(
                                                        brush = Brush.radialGradient(
                                                            colors = listOf(
                                                                Color.White.copy(alpha = 0.28f),
                                                                Color(0xFFE9D5FF).copy(alpha = 0.12f),
                                                                Color.Transparent
                                                            ),
                                                            center = Offset(size.width * 0.50f, 0f),
                                                            radius = size.width * 0.36f
                                                        ),
                                                        center = Offset(size.width * 0.50f, 0f),
                                                        radius = size.width * 0.36f
                                                    )
                                                    drawCircle(
                                                        brush = Brush.radialGradient(
                                                            colors = listOf(
                                                                Color.White.copy(alpha = 0.32f),
                                                                Color(0xFFD8B4FE).copy(alpha = 0.14f),
                                                                Color.Transparent
                                                            ),
                                                            center = Offset(size.width * 0.50f, size.height),
                                                            radius = size.width * 0.36f
                                                        ),
                                                        center = Offset(size.width * 0.50f, size.height),
                                                        radius = size.width * 0.36f
                                                    )
                                                }
                                                .border(
                                                    BorderStroke(
                                                        1.2.dp,
                                                        Brush.verticalGradient(
                                                            listOf(
                                                                Color(0xDCE9D5FF),
                                                                Color(0xB8C084FC),
                                                                Color(0x989333EA),
                                                                Color(0xB8C084FC),
                                                                Color(0xDCE9D5FF)
                                                            )
                                                        )
                                                    ),
                                                    tabShape
                                                )
                                        } else Modifier.clip(tabShape)
                                    )
                                    .clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }
                                    ) {
                                        if (activeTab == tab.key) {
                                            coroutineScope.launch { listStates[tab.key]?.animateScrollToItem(0) }
                                        } else {
                                            activeTab = tab.key
                                            coroutineScope.launch { listStates[tab.key]?.scrollToItem(0) }
                                        }
                                    }
                                    .padding(vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    when (tab.key) {
                                        "discover" -> {
                                            // Reference Wireframe Meridian/Latitude Globe Icon (🌐)
                                            Canvas(modifier = Modifier.size(25.dp)) {
                                                val r = size.minDimension * 0.44f
                                                val c = Offset(size.width * 0.5f, size.height * 0.5f)
                                                val strokeColor = if (isSelected) Color.White else Color(0xFFD8B4FE)
                                                val sw = 1.65.dp.toPx()
                                                drawCircle(
                                                    color = strokeColor,
                                                    radius = r,
                                                    center = c,
                                                    style = Stroke(width = sw)
                                                )
                                                drawOval(
                                                    color = strokeColor,
                                                    topLeft = Offset(c.x - r * 0.48f, c.y - r),
                                                    size = Size(r * 0.96f, r * 2f),
                                                    style = Stroke(width = sw * 0.92f)
                                                )
                                                drawLine(
                                                    color = strokeColor,
                                                    start = Offset(c.x, c.y - r),
                                                    end = Offset(c.x, c.y + r),
                                                    strokeWidth = sw * 0.85f
                                                )
                                                drawLine(
                                                    color = strokeColor,
                                                    start = Offset(c.x - r, c.y),
                                                    end = Offset(c.x + r, c.y),
                                                    strokeWidth = sw * 0.9f
                                                )
                                                drawLine(
                                                    color = strokeColor.copy(alpha = 0.85f),
                                                    start = Offset(c.x - r * 0.84f, c.y - r * 0.48f),
                                                    end = Offset(c.x + r * 0.84f, c.y - r * 0.48f),
                                                    strokeWidth = sw * 0.8f
                                                )
                                                drawLine(
                                                    color = strokeColor.copy(alpha = 0.85f),
                                                    start = Offset(c.x - r * 0.84f, c.y + r * 0.48f),
                                                    end = Offset(c.x + r * 0.84f, c.y + r * 0.48f),
                                                    strokeWidth = sw * 0.8f
                                                )
                                            }
                                        }
                                        "chats" -> {
                                            // Reference Double Speech Bubble with '...' (💬)
                                            Canvas(modifier = Modifier.size(25.dp)) {
                                                val bw = size.width
                                                val bh = size.height
                                                val mainBrush = Brush.verticalGradient(
                                                    if (isSelected) listOf(Color.White, Color(0xFFE9D5FF))
                                                    else listOf(Color(0xFFE9D5FF), Color(0xFFC4B5FD))
                                                )
                                                val backColor = if (isSelected) Color(0xFFD8B4FE).copy(alpha = 0.72f)
                                                else Color(0xFFA78BFA).copy(alpha = 0.65f)

                                                val subBubble = Path().apply {
                                                    addRoundRect(
                                                        androidx.compose.ui.geometry.RoundRect(
                                                            left = bw * 0.36f,
                                                            top = bh * 0.34f,
                                                            right = bw * 0.94f,
                                                            bottom = bh * 0.80f,
                                                            cornerRadius = CornerRadius(bw * 0.14f, bw * 0.14f)
                                                        )
                                                    )
                                                    moveTo(bw * 0.74f, bh * 0.78f)
                                                    lineTo(bw * 0.88f, bh * 0.92f)
                                                    lineTo(bw * 0.62f, bh * 0.78f)
                                                    close()
                                                }
                                                drawPath(path = subBubble, color = backColor)

                                                val mainBubble = Path().apply {
                                                    addRoundRect(
                                                        androidx.compose.ui.geometry.RoundRect(
                                                            left = bw * 0.06f,
                                                            top = bh * 0.12f,
                                                            right = bw * 0.82f,
                                                            bottom = bh * 0.68f,
                                                            cornerRadius = CornerRadius(bw * 0.16f, bw * 0.16f)
                                                        )
                                                    )
                                                    moveTo(bw * 0.24f, bh * 0.66f)
                                                    lineTo(bw * 0.16f, bh * 0.86f)
                                                    lineTo(bw * 0.40f, bh * 0.68f)
                                                    close()
                                                }
                                                drawPath(path = mainBubble, brush = mainBrush)

                                                val dotColor = Color(0xFF281646)
                                                val dotY = bh * 0.40f
                                                val dotR = bw * 0.052f
                                                drawCircle(color = dotColor, radius = dotR, center = Offset(bw * 0.28f, dotY))
                                                drawCircle(color = dotColor, radius = dotR, center = Offset(bw * 0.44f, dotY))
                                                drawCircle(color = dotColor, radius = dotR, center = Offset(bw * 0.60f, dotY))
                                            }
                                        }
                                        "templates" -> {
                                            // Reference 3D Glossy Beveled Lavender-White Star (⭐)
                                            Canvas(modifier = Modifier.size(26.dp)) {
                                                val cx = size.width * 0.5f
                                                val cy = size.height * 0.5f
                                                val outerR = size.minDimension * 0.47f
                                                val innerR = outerR * 0.45f
                                                val starPath = Path().apply {
                                                    for (i in 0 until 10) {
                                                        val angle = Math.toRadians((i * 36 - 90).toDouble())
                                                        val rad = if (i % 2 == 0) outerR else innerR
                                                        val px = cx + (rad * kotlin.math.cos(angle)).toFloat()
                                                        val py = cy + (rad * kotlin.math.sin(angle)).toFloat()
                                                        if (i == 0) moveTo(px, py) else lineTo(px, py)
                                                    }
                                                    close()
                                                }
                                                drawPath(
                                                    path = starPath,
                                                    brush = Brush.verticalGradient(
                                                        if (isSelected) {
                                                            listOf(
                                                                Color.White,
                                                                Color(0xFFF3E8FF),
                                                                Color(0xFFD8B4FE)
                                                            )
                                                        } else {
                                                            listOf(
                                                                Color(0xFFE9D5FF),
                                                                Color(0xFFC4B5FD),
                                                                Color(0xFFA78BFA)
                                                            )
                                                        }
                                                    )
                                                )
                                            }
                                        }
                                        else -> {
                                            // Reference 3D Open Book Icon (📖)
                                            Canvas(modifier = Modifier.size(25.dp)) {
                                                val bw = size.width
                                                val bh = size.height
                                                val leftPage = Path().apply {
                                                    moveTo(bw * 0.48f, bh * 0.22f)
                                                    cubicTo(bw * 0.34f, bh * 0.14f, bw * 0.16f, bh * 0.16f, bw * 0.07f, bh * 0.24f)
                                                    lineTo(bw * 0.07f, bh * 0.80f)
                                                    cubicTo(bw * 0.20f, bh * 0.72f, bw * 0.35f, bh * 0.72f, bw * 0.48f, bh * 0.82f)
                                                    close()
                                                }
                                                val rightPage = Path().apply {
                                                    moveTo(bw * 0.52f, bh * 0.22f)
                                                    cubicTo(bw * 0.66f, bh * 0.14f, bw * 0.84f, bh * 0.16f, bw * 0.93f, bh * 0.24f)
                                                    lineTo(bw * 0.93f, bh * 0.80f)
                                                    cubicTo(bw * 0.80f, bh * 0.72f, bw * 0.65f, bh * 0.72f, bw * 0.52f, bh * 0.82f)
                                                    close()
                                                }
                                                drawPath(
                                                    path = leftPage,
                                                    brush = Brush.verticalGradient(
                                                        if (isSelected) listOf(Color.White, Color(0xFFE9D5FF))
                                                        else listOf(Color(0xFFE9D5FF), Color(0xFFB794F4))
                                                    )
                                                )
                                                drawPath(
                                                    path = rightPage,
                                                    brush = Brush.verticalGradient(
                                                        if (isSelected) listOf(Color.White, Color(0xFFF3E8FF), Color(0xFFD8B4FE))
                                                        else listOf(Color(0xFFD8B4FE), Color(0xFFA78BFA))
                                                    )
                                                )
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = tab.label,
                                        fontFamily = androidx.compose.ui.text.font.FontFamily.Serif,
                                        color = if (isSelected) Color.White else Color(0xFFE9D5FF).copy(alpha = 0.92f),
                                        fontSize = 11.8.sp,
                                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                                    )
                                }
                            }
                        }

                        // Left two tabs ("Keşfet", "Sohbet")
                        tabs.take(2).forEachIndexed { idx, tab ->
                            RenderNavTabItem(
                                tab = tab,
                                isSelected = activeTab == tab.key,
                                modifier = Modifier.weight(1f)
                            )

                            // Subtle vertical divider line between Keşfet and Sohbet matching reference
                            if (idx == 0) {
                                Box(
                                    modifier = Modifier
                                        .width(0.9.dp)
                                        .height(38.dp)
                                        .background(
                                            Brush.verticalGradient(
                                                listOf(
                                                    Color.Transparent,
                                                    Color(0x52E9D5FF),
                                                    Color.Transparent
                                                )
                                            )
                                        )
                                )
                            }
                        }

                        // Spacer for Center FAB (+ Oluştur)
                        Spacer(modifier = Modifier.width(86.dp))

                        // Right two tabs ("Şablon", "Kitap")
                        tabs.drop(2).forEach { tab ->
                            RenderNavTabItem(
                                tab = tab,
                                isSelected = activeTab == tab.key,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                // Center concentric 3D Orb FAB (+ Oluştur) matching reference image
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = (-2).dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Outer see-through frosted Mor Cam glass ring (#1)
                    Box(
                        modifier = Modifier
                            .size(84.dp)
                            .clip(CircleShape)
                            .templatesBackdropBlur(
                                blurredBitmap = frostedCosmicBitmap,
                                secondaryBitmap = frostedManorBitmap,
                                secondaryProgress = templatesBgProgress,
                                alpha = 0.72f,
                                scrollSync = activeScrollSync
                            )
                            .background(
                                Brush.radialGradient(
                                    listOf(
                                        Color(0x34381C60),
                                        Color(0x241A0B32)
                                    )
                                )
                            )
                            .drawBehind {
                                drawIntoCanvas { canvas ->
                                    val rim = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                                        style = android.graphics.Paint.Style.STROKE
                                        strokeWidth = 4.5.dp.toPx()
                                        color = android.graphics.Color.argb(36, 230, 214, 255)
                                        maskFilter = android.graphics.BlurMaskFilter(4.dp.toPx(), android.graphics.BlurMaskFilter.Blur.NORMAL)
                                    }
                                    canvas.nativeCanvas.drawCircle(size.width * 0.5f, size.height * 0.5f, size.minDimension * 0.5f, rim)
                                }
                            }
                            .border(
                                BorderStroke(
                                    1.1.dp,
                                    Brush.linearGradient(
                                        listOf(Color(0xCCE9D5FF), Color(0x887C3AED), Color(0xDDE9D5FF))
                                    )
                                ),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        // Inner translucent glowing violet-purple crystal sphere (#2) with '+' and 'Oluştur'
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.radialGradient(
                                        colors = listOf(
                                            Color(0x7CB366FF),
                                            Color(0x607E22CE),
                                            Color(0x544C1D95),
                                            Color(0x682E1065)
                                        ),
                                        center = Offset(75f, 60f),
                                        radius = 115f
                                    )
                                )
                                .drawBehind {
                                    // Subtle top & bottom specular flares on center orb
                                    drawCircle(
                                        brush = Brush.radialGradient(
                                            colors = listOf(
                                                Color.White.copy(alpha = 0.24f),
                                                Color(0xFFE9D5FF).copy(alpha = 0.10f),
                                                Color.Transparent
                                            ),
                                            center = Offset(size.width * 0.42f, 0f),
                                            radius = size.width * 0.36f
                                        ),
                                        center = Offset(size.width * 0.42f, 0f),
                                        radius = size.width * 0.36f
                                    )
                                    drawCircle(
                                        brush = Brush.radialGradient(
                                            colors = listOf(
                                                Color.White.copy(alpha = 0.32f),
                                                Color(0xFFE9D5FF).copy(alpha = 0.14f),
                                                Color.Transparent
                                            ),
                                            center = Offset(size.width * 0.5f, size.height),
                                            radius = size.width * 0.38f
                                        ),
                                        center = Offset(size.width * 0.5f, size.height),
                                        radius = size.width * 0.38f
                                    )
                                }
                                .border(
                                    BorderStroke(
                                        1.2.dp,
                                        Brush.verticalGradient(
                                            listOf(Color(0xDDE9D5FF), Color(0xAA9F7AEA), Color(0xDDE9D5FF))
                                        )
                                    ),
                                    CircleShape
                                )
                                .clickable { onNewBot() }
                                .testTag("create_new_bot_button"),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Oluştur",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(25.dp)
                                        .offset(y = 1.dp)
                                )
                                Text(
                                    text = "Oluştur",
                                    fontFamily = androidx.compose.ui.text.font.FontFamily.Serif,
                                    color = Color.White,
                                    fontSize = 10.8.sp,
                                    fontWeight = FontWeight.Medium,
                                    lineHeight = 10.8.sp,
                                    modifier = Modifier.offset(y = (-1).dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            val tabOrder = listOf("discover", "chats", "templates", "books")
            val tabCubicEasing = remember { androidx.compose.animation.core.CubicBezierEasing(0.22f, 1.0f, 0.36f, 1.0f) }
            AnimatedContent(
                targetState = activeTab,
                transitionSpec = {
                    val fromIdx = tabOrder.indexOf(initialState)
                    val toIdx   = tabOrder.indexOf(targetState)
                    val dir     = if (toIdx > fromIdx) 1 else -1
                    (slideInHorizontally(tween(460, easing = tabCubicEasing)) { (it * 0.22f * dir).toInt() } +
                        fadeIn(tween(460, easing = tabCubicEasing))) togetherWith
                    (slideOutHorizontally(tween(380, easing = tabCubicEasing)) { (it * -0.18f * dir).toInt() } +
                        fadeOut(tween(320, easing = tabCubicEasing)))
                },
                modifier = Modifier
                    .weight(1f)
                    .then(
                        Modifier.let {
                            androidx.compose.ui.Modifier.onGloballyPositioned { coords ->
                                contentTopInRootPx = coords.positionInRoot().y
                            }
                        }
                    )
                    // Exact root-coordinate measured feathered DstIn vertical fade mask right above the bottom dock:
                    // Scrolling cards dissolve smoothly over a ~50dp feather zone before reaching the top border
                    // of the bottom dock, so there is NO hard horizontal scissor-cut line when scrolling AND
                    // NO cards ever show through behind the see-through glass bottom bar!
                    .graphicsLayer {
                        compositingStrategy = androidx.compose.ui.graphics.CompositingStrategy.Offscreen
                    }
                    .drawWithContent {
                        drawContent()
                        val h = size.height
                        if (h > 0f) {
                            val dockTopLocalY = if (bottomBarTopInRootPx > 0f) {
                                (bottomBarTopInRootPx - contentTopInRootPx).coerceIn(0f, h)
                            } else {
                                (h - 94.dp.toPx()).coerceAtLeast(0f)
                            }
                            val fadeEndPx = (dockTopLocalY - 2.dp.toPx()).coerceAtLeast(1f)
                            val fadeStartPx = (fadeEndPx - 50.dp.toPx()).coerceAtLeast(0f)
                            val s0 = (fadeStartPx / h).coerceIn(0f, 1f)
                            val s1 = (fadeEndPx / h).coerceIn(s0, 1f)
                            drawRect(
                                brush = Brush.verticalGradient(
                                    0.00f to Color.White,
                                    s0 to Color.White,
                                    (s0 + (s1 - s0) * 0.35f) to Color.White.copy(alpha = 0.72f),
                                    (s0 + (s1 - s0) * 0.70f) to Color.White.copy(alpha = 0.28f),
                                    s1 to Color.Transparent,
                                    1.00f to Color.Transparent
                                ),
                                blendMode = BlendMode.DstIn
                            )
                        }
                    }
            ) { tab ->
                when (tab) {
                    "templates" -> {
                        ExploreTabContent(
                            userSettings = userSettings,
                            onImportPresetBot = onImportPresetBot,
                            onOpenAidenMenu = { showAidenStoriesMenu = true },
                            onOpenSettings = { showGlobalSettings = true },
                            lazyListState = templatesListState
                        )
                    }
                    "discover" -> {
                        val publicBots = remember(botList) {
                            botList.filter { it.isPublic }
                        }
                        DiscoverTabContent(
                            botList = publicBots,
                            searchQuery = searchQuery,
                            onSearchQueryChange = { searchQuery = it },
                            onOpenBot = onOpenBot,
                            onOpenSettings = { showGlobalSettings = true },
                            onCloneBot = onCloneBot
                        )
                    }
                    "books" -> {
                        BooksTabContent(
                            botList = botList,
                            userSettings = userSettings,
                            onOpenBot = onOpenBot,
                            onDeleteBot = onDeleteBot,
                            onImportPresetBot = { bot -> onImportPresetBot?.invoke(bot) }
                        )
                    }
                    else -> {
                        // "chats" tab -> Shows user's active bots
                        ChatsTabContent(
                            botList = botList,
                            userSettings = userSettings,
                            searchQuery = searchQuery,
                            onSearchQueryChange = { searchQuery = it },
                            selectedFilter = selectedFilter,
                            onFilterChange = { selectedFilter = it },
                            confirmDeleteId = confirmDeleteId,
                            onConfirmDeleteChange = { confirmDeleteId = it },
                            onOpenBot = onOpenBot,
                            onDeleteBot = onDeleteBot,
                            onTogglePrivacy = onTogglePrivacy,
                            onCloneBot = onCloneBot,
                            onOpenAidenMenu = { showAidenStoriesMenu = true },
                            onOpenSettings = { showGlobalSettings = true },
                            onNewBot = onNewBot,
                            lazyListState = chatsListState
                        )
                    }
                }
            }
        }
    }
    }

    if (showGlobalSettings && userSettings != null) {
        GlobalSettingsModal(
            settings = userSettings,
            onDismiss = { showGlobalSettings = false },
            onSaveSettings = onSaveSettings,
            onExportData = onExportData,
            onImportData = onImportData
        )
    }

    if (showAidenStoriesMenu) {
        AidenStoriesModal(
            userSettings = userSettings,
            existingBots = botList,
            onDismiss = { showAidenStoriesMenu = false },
            onImportPresetBot = onImportPresetBot,
            onOpenExistingBot = onOpenBot
        )
    }
}

@Composable
fun TopHeader(
    activeTab: String,
    botCount: Int,
    onOpenSettings: () -> Unit
) {
    val infinite = rememberInfiniteTransition(label = "header")

    val shimmerX by infinite.animateFloat(
        initialValue = -250f,
        targetValue = 550f,
        animationSpec = infiniteRepeatable(
            animation = tween(3200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerX"
    )

    val settingsRing by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(7500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "settingsRing"
    )

    val waveAngle by infinite.animateFloat(
        initialValue = -6f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(1100, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "waveAngle"
    )

    val pillGlowPulse by infinite.animateFloat(
        initialValue = 0.65f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pillGlowPulse"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                // Greeting with subtle waving hand animation
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "👋",
                        fontSize = 17.sp,
                        modifier = Modifier.graphicsLayer { rotationZ = waveAngle }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Hoş geldin",
                        fontFamily = InterFontFamily,
                        color = Color(0xFFD8CEFF),
                        fontSize = 15.5.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))

                // Title ("Botlarım") with white-to-lavender gradient + subtle purple glow shadow
                val titleText = when (activeTab) {
                    "templates" -> "Şablonlar"
                    "discover"  -> "Keşfet"
                    "books"     -> "Kitaplar"
                    else        -> "Botlarım"
                }
                Text(
                    text = titleText,
                    style = androidx.compose.ui.text.TextStyle(
                        fontFamily = InterFontFamily,
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.White,
                                Color(0xFFFAF5FF),
                                Color(0xFFE9D5FF),
                                Color(0xFFD8B4FE)
                            ),
                            startX = 0f,
                            endX = 480f
                        ),
                        fontSize = 37.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-0.6).sp,
                        shadow = Shadow(
                            color = Color(0x779333EA),
                            offset = Offset(0f, 2f),
                            blurRadius = 12f
                        )
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Status capsule pill — pulsing live emerald dot + text + chevron + subtle STROKE neon rim
                val dotPulse by infinite.animateFloat(
                    initialValue = 0.6f, targetValue = 1.0f,
                    animationSpec = infiniteRepeatable(tween(900, easing = FastOutSlowInEasing), RepeatMode.Reverse),
                    label = "dotPulse"
                )
                val pillShape = RoundedCornerShape(50)
                Box(
                    modifier = Modifier
                        .drawBehind {
                            drawIntoCanvas { canvas ->
                                val outerGlow = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                                    style = android.graphics.Paint.Style.STROKE
                                    strokeWidth = 2.dp.toPx()
                                    color = Color(0x779333EA).copy(alpha = 0.45f * pillGlowPulse).toArgb()
                                    maskFilter = android.graphics.BlurMaskFilter(
                                        8.dp.toPx(),
                                        android.graphics.BlurMaskFilter.Blur.NORMAL
                                    )
                                }
                                canvas.nativeCanvas.drawRoundRect(
                                    0f,
                                    0f,
                                    size.width,
                                    size.height,
                                    50.dp.toPx(),
                                    50.dp.toPx(),
                                    outerGlow
                                )
                            }
                        }
                        .clip(pillShape)
                        .background(
                            Brush.horizontalGradient(
                                listOf(Color(0xFF140A30), Color(0xFF0E0622), Color(0xFF220E4B))
                            )
                        )
                        .drawBehind {
                            drawCircle(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        Color(0x45E879F9),
                                        Color.Transparent
                                    ),
                                    center = Offset(size.width * 0.54f, size.height),
                                    radius = size.width * 0.25f
                                ),
                                center = Offset(size.width * 0.54f, size.height),
                                radius = size.width * 0.25f
                            )
                        }
                        .border(
                            BorderStroke(
                                1.3.dp,
                                Brush.horizontalGradient(
                                    listOf(
                                        Color(0xFF7C3AED),
                                        Color(0xFFA855F7),
                                        Color(0xFFF5D0FE),
                                        Color(0xFFD946EF),
                                        Color(0xFF9333EA)
                                    )
                                )
                            ),
                            pillShape
                        )
                        .clickable { onOpenSettings() }
                        .padding(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(22.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF0D3B2E))
                                .border(1.dp, Color(0x6610B981), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(
                                        Brush.radialGradient(
                                            listOf(Color(0xFFA7F3D0), Color(0xFF34D399), Color(0xFF10B981))
                                        )
                                    )
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = when (activeTab) {
                                "templates" -> "Şablonlar • Gemini 2.5 Flash"
                                "discover"  -> "Keşfet • Gemini 2.5 Flash"
                                "books"     -> "Kitaplar • Gemini 2.5 Flash"
                                else        -> "$botCount bot aktif • Gemini 2.5 Flash"
                            },
                            fontFamily = InterFontFamily,
                            color = Color.White,
                            fontSize = 13.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = null,
                            tint = Color(0xFFD8B4FE),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            // Double-ring circular Settings button with refined STROKE purple aura
            Box(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .size(62.dp)
                    .drawBehind {
                        drawIntoCanvas { canvas ->
                            val settingsGlow = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                                style = android.graphics.Paint.Style.STROKE
                                strokeWidth = 2.dp.toPx()
                                color = Color(0x779333EA).toArgb()
                                maskFilter = android.graphics.BlurMaskFilter(
                                    8.dp.toPx(),
                                    android.graphics.BlurMaskFilter.Blur.NORMAL
                                )
                            }
                            canvas.nativeCanvas.drawCircle(
                                size.width / 2f,
                                size.height / 2f,
                                29.dp.toPx(),
                                settingsGlow
                            )
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                listOf(Color(0x443B1578), Color(0xCC090416))
                            )
                        )
                        .border(
                            BorderStroke(
                                1.3.dp,
                                Brush.sweepGradient(
                                    listOf(
                                        Color(0xFFE9D5FF),
                                        Color(0xFFC084FC),
                                        Color(0xFF6D28D9),
                                        Color(0x554C1D95),
                                        Color(0xFF9333EA),
                                        Color(0xFFE9D5FF)
                                    )
                                )
                            ),
                            CircleShape
                        )
                )
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .graphicsLayer { rotationZ = settingsRing }
                        .clip(CircleShape)
                        .border(
                            BorderStroke(
                                1.1.dp,
                                Brush.sweepGradient(
                                    listOf(
                                        Color.Transparent,
                                        Color(0x99FAF5FF),
                                        Color(0x77A855F7),
                                        Color.Transparent,
                                        Color.Transparent
                                    )
                                )
                            ),
                            CircleShape
                        )
                )
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                listOf(Color(0xFF2E1566), Color(0xFF12072B))
                            )
                        )
                        .border(
                            BorderStroke(
                                1.4.dp,
                                Brush.linearGradient(
                                    listOf(Color(0xFFE9D5FF), Color(0xFFA855F7), Color(0xFF6D28D9))
                                )
                            ),
                            CircleShape
                        )
                        .clickable { onOpenSettings() }
                        .testTag("global_settings_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Settings,
                        contentDescription = "Ayarlar",
                        tint = Color.White,
                        modifier = Modifier.size(23.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ChatsTabContent(
    botList: List<BotEntity>,
    userSettings: UserSettingsEntity? = null,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    selectedFilter: String,
    onFilterChange: (String) -> Unit,
    confirmDeleteId: String?,
    onConfirmDeleteChange: (String?) -> Unit,
    onOpenBot: (String) -> Unit,
    onDeleteBot: (String) -> Unit,
    onTogglePrivacy: ((BotEntity) -> Unit)? = null,
    onCloneBot: ((String) -> Unit)? = null,
    onOpenAidenMenu: (() -> Unit)? = null,
    onOpenSettings: () -> Unit,
    onNewBot: (() -> Unit)? = null,
    lazyListState: androidx.compose.foundation.lazy.LazyListState = androidx.compose.foundation.lazy.rememberLazyListState()
) {
    val chatsOnlyList = remember(botList) {
        botList.filter { bot ->
            bot.mode != "book" &&
            bot.id != "preset_aiden_mcu_cosmic" &&
            !bot.id.startsWith("book_") &&
            !bot.aiName.contains("Kitap") &&
            !bot.aiPersonality.contains("KİTAP MODU") &&
            !bot.scenario.contains("KİTAP MODU")
        }
    }

    val filteredBots = remember(chatsOnlyList, searchQuery, selectedFilter) {
        chatsOnlyList.filter { bot ->
            val query = searchQuery.trim().lowercase()
            val matchesQuery = query.isEmpty() ||
                    bot.aiName.lowercase().contains(query) ||
                    bot.universeName.lowercase().contains(query) ||
                    bot.scenario.lowercase().contains(query)

            val matchesFilter = when (selectedFilter) {
                "personal" -> bot.mode == "personal"
                "universe" -> bot.mode == "universe"
                else -> true
            }
            matchesFilter && matchesQuery
        }
    }

    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(bottom = 18.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        // 1. Top Header
        item(key = "top_header") {
            TopHeader(
                activeTab = "chats",
                botCount = chatsOnlyList.size,
                onOpenSettings = onOpenSettings
            )
        }

        // 2. Search & Filters Section
        item(key = "search_and_filters") {
            Column(modifier = Modifier.fillMaxWidth()) {
                var isSearchFocused by remember { mutableStateOf(false) }
                val searchBorderAlpha by animateFloatAsState(
                    targetValue = if (isSearchFocused) 1f else 0.82f,
                    animationSpec = tween(250),
                    label = "searchBorder"
                )
                val searchShape = RoundedCornerShape(26.dp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .drawBehind {
                            drawIntoCanvas { canvas ->
                                val searchGlow = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                                    style = android.graphics.Paint.Style.STROKE
                                    strokeWidth = 1.8.dp.toPx()
                                    color = Color(0x557C3AED).toArgb()
                                    maskFilter = android.graphics.BlurMaskFilter(
                                        7.dp.toPx(),
                                        android.graphics.BlurMaskFilter.Blur.NORMAL
                                    )
                                }
                                canvas.nativeCanvas.drawRoundRect(
                                    0f,
                                    0f,
                                    size.width,
                                    size.height,
                                    26.dp.toPx(),
                                    26.dp.toPx(),
                                    searchGlow
                                )
                            }
                        }
                        .clip(searchShape)
                        .background(
                            Brush.horizontalGradient(
                                listOf(Color(0xF50B061C), Color(0xF5090416), Color(0xF50F0824))
                            )
                        )
                        .border(
                            BorderStroke(
                                if (isSearchFocused) 1.5.dp else 1.2.dp,
                                Brush.linearGradient(
                                    listOf(
                                        Color(0xFFD8B4FE).copy(alpha = searchBorderAlpha),
                                        Color(0xFF6D28D9).copy(alpha = searchBorderAlpha * 0.85f),
                                        Color(0xFFA855F7).copy(alpha = searchBorderAlpha)
                                    )
                                )
                            ),
                            searchShape
                        )
                        .padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Ara",
                        tint = Color(0xFFF3E8FF),
                        modifier = Modifier.size(23.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Box(modifier = Modifier.weight(1f)) {
                        if (searchQuery.isEmpty()) {
                            Text(
                                text = "Bot veya evren ara...",
                                fontFamily = InterFontFamily,
                                color = Color(0xFFA396CE).copy(alpha = 0.90f),
                                fontSize = 15.sp
                            )
                        }
                        androidx.compose.foundation.text.BasicTextField(
                            value = searchQuery,
                            onValueChange = onSearchQueryChange,
                            singleLine = true,
                            textStyle = androidx.compose.ui.text.TextStyle(
                                fontFamily = InterFontFamily,
                                color = Color.White,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("search_bot_field")
                                .onFocusChanged { isSearchFocused = it.isFocused }
                        )
                    }
                    if (searchQuery.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0x35A855F7))
                                .border(1.dp, Color(0x80C084FC), RoundedCornerShape(12.dp))
                                .clickable { onSearchQueryChange("") },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Temizle",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(RoundedCornerShape(11.dp))
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color(0xFF1F1042), Color(0xFF110726))
                                    )
                                )
                                .border(
                                    BorderStroke(
                                        1.2.dp,
                                        Brush.linearGradient(
                                            listOf(Color(0xFFD8B4FE), Color(0xFF7C3AED))
                                        )
                                    ),
                                    RoundedCornerShape(11.dp)
                                )
                                .clickable { onNewBot?.invoke() },
                            contentAlignment = Alignment.Center
                        ) {
                            Canvas(modifier = Modifier.size(17.dp)) {
                                val stroke = Stroke(
                                    width = 1.8.dp.toPx(),
                                    cap = androidx.compose.ui.graphics.StrokeCap.Round,
                                    join = androidx.compose.ui.graphics.StrokeJoin.Round
                                )
                                val w = size.width
                                val h = size.height
                                drawRoundRect(
                                    color = Color.White,
                                    topLeft = Offset(w * 0.08f, h * 0.18f),
                                    size = Size(w * 0.72f, h * 0.72f),
                                    cornerRadius = CornerRadius(3.5.dp.toPx()),
                                    style = stroke
                                )
                                drawLine(
                                    color = Color.White,
                                    start = Offset(w * 0.44f, h * 0.56f),
                                    end = Offset(w * 0.92f, h * 0.08f),
                                    strokeWidth = 2.dp.toPx(),
                                    cap = androidx.compose.ui.graphics.StrokeCap.Round
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Filters Row with refined STROKE neon rim
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val filters = listOf(
                        Triple("all", "Tümü (${chatsOnlyList.size})", Icons.Default.GridView),
                        Triple("personal", "Karakterler (${chatsOnlyList.count { it.mode == "personal" }})", Icons.Default.Person),
                        Triple("universe", "Evrenler (${chatsOnlyList.count { it.mode == "universe" }})", Icons.Default.Public)
                    )

                    filters.forEach { (key, label, icon) ->
                        val isSelected = selectedFilter == key
                        val shape = RoundedCornerShape(22.dp)

                        val chipScale by androidx.compose.animation.core.animateFloatAsState(
                            targetValue = if (isSelected) 1.02f else 1f,
                            animationSpec = tween(180, easing = FastOutSlowInEasing),
                            label = "chipScale_$key"
                        )

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .graphicsLayer { scaleX = chipScale; scaleY = chipScale }
                                .then(
                                    if (isSelected) {
                                        Modifier.drawBehind {
                                            drawIntoCanvas { canvas ->
                                                val outerBloom = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                                                    style = android.graphics.Paint.Style.STROKE
                                                    strokeWidth = 2.2.dp.toPx()
                                                    color = Color(0xAA8B5CF6).toArgb()
                                                    maskFilter = android.graphics.BlurMaskFilter(
                                                        8.dp.toPx(),
                                                        android.graphics.BlurMaskFilter.Blur.NORMAL
                                                    )
                                                }
                                                canvas.nativeCanvas.drawRoundRect(
                                                    0f,
                                                    0f,
                                                    size.width,
                                                    size.height,
                                                    22.dp.toPx(),
                                                    22.dp.toPx(),
                                                    outerBloom
                                                )
                                            }
                                        }
                                    } else Modifier
                                )
                                .clip(shape)
                                .background(
                                    if (isSelected) {
                                        Brush.linearGradient(
                                            colors = listOf(
                                                Color(0xFFA855F7),
                                                Color(0xFF7C3AED),
                                                Color(0xFF5B21B6),
                                                Color(0xFF6D28D9)
                                            ),
                                            start = Offset(0f, 0f),
                                            end = Offset(220f, 120f)
                                        )
                                    } else {
                                        Brush.verticalGradient(
                                            listOf(Color(0xF0110927), Color(0xF00B061A))
                                        )
                                    }
                                )
                                .then(
                                    if (isSelected) {
                                        Modifier.drawBehind {
                                            // Glossy top-left white-lavender specular bloom inside chip
                                            drawCircle(
                                                brush = Brush.radialGradient(
                                                    colors = listOf(
                                                        Color(0xAAFFFFFF),
                                                        Color(0x55E9D5FF),
                                                        Color.Transparent
                                                    ),
                                                    center = Offset(size.width * 0.24f, 0f),
                                                    radius = size.width * 0.65f
                                                ),
                                                center = Offset(size.width * 0.24f, 0f),
                                                radius = size.width * 0.65f
                                            )
                                        }
                                    } else Modifier
                                )
                                .border(
                                    BorderStroke(
                                        width = if (isSelected) 1.8.dp else 1.2.dp,
                                        brush = if (isSelected) {
                                            Brush.linearGradient(
                                                listOf(
                                                    Color.White,
                                                    Color(0xFFF3E8FF),
                                                    Color(0xFFC084FC),
                                                    Color(0xFFA855F7)
                                                )
                                            )
                                        } else {
                                            Brush.linearGradient(
                                                listOf(Color(0x775B21B6), Color(0x443B0764))
                                            )
                                        }
                                    ),
                                    shape = shape
                                )
                                .clickable { onFilterChange(key) }
                                .padding(horizontal = 8.dp, vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                if (key == "all") {
                                    // Custom crisp 2x2 grid of 4 hollow rounded squares matching reference
                                    Canvas(modifier = Modifier.size(18.dp)) {
                                        val stroke = Stroke(width = 1.9.dp.toPx())
                                        val tintColor = if (isSelected) Color.White else Color(0xFFC4B5FD)
                                        val cell = size.width * 0.38f
                                        val gap = size.width * 0.14f
                                        val start = size.width * 0.05f
                                        val cr = CornerRadius(1.8.dp.toPx())
                                        drawRoundRect(tintColor, Offset(start, start), Size(cell, cell), cr, stroke)
                                        drawRoundRect(tintColor, Offset(start + cell + gap, start), Size(cell, cell), cr, stroke)
                                        drawRoundRect(tintColor, Offset(start, start + cell + gap), Size(cell, cell), cr, stroke)
                                        drawRoundRect(tintColor, Offset(start + cell + gap, start + cell + gap), Size(cell, cell), cr, stroke)
                                    }
                                } else {
                                    Icon(
                                        imageVector = icon,
                                        contentDescription = null,
                                        tint = if (isSelected) Color.White else Color(0xFFB5A4E0),
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = label,
                                    color = if (isSelected) Color.White else Color(0xFFE2D9F3).copy(alpha = 0.90f),
                                    fontSize = 13.sp,
                                    fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.SemiBold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }

        // 3. Bot items or empty state
        if (botList.isEmpty()) {
            item(key = "empty_no_bots") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(24.dp))
                            .background(
                                Brush.linearGradient(
                                    listOf(Color(0xFF130B2E), Color(0xFF0C0720))
                                )
                            )
                            .border(
                                BorderStroke(
                                    1.5.dp,
                                    Brush.linearGradient(
                                        listOf(Color(0xFF9333EA), Color(0xFF4C1D95), Color(0xFF7C3AED))
                                    )
                                ),
                                RoundedCornerShape(24.dp)
                            )
                            .padding(28.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape)
                                    .background(
                                        Brush.radialGradient(
                                            listOf(Color(0xFF7C3AED), Color(0xFF3B1070), Color(0xFF1A0840))
                                        )
                                    )
                                    .border(
                                        BorderStroke(
                                            2.dp,
                                            Brush.sweepGradient(
                                                listOf(Color(0xFFA855F7), Color(0xFF6D28D9), Color(0xFFC084FC), Color(0xFFA855F7))
                                            )
                                        ),
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                OrbView(hue = 275f, size = 56.dp)
                            }
                            Text(
                                text = "Henüz hiç karakter yok",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = "Aşağıdaki (+) butonuna dokun, harika bir karakter veya evren yarat.",
                                color = AppColors.TextMuted,
                                fontSize = 12.5.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }
        } else if (filteredBots.isEmpty()) {
            item(key = "empty_search") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFF130B28))
                            .border(
                                BorderStroke(
                                    1.dp,
                                    Brush.linearGradient(
                                        listOf(Color(0x60A855F7), Color(0x306D28D9))
                                    )
                                ),
                                RoundedCornerShape(16.dp)
                            )
                            .padding(20.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("🔍", fontSize = 28.sp)
                            Text(
                                text = "Aramanıza uygun sonuç bulunamadı.",
                                color = Color(0xFFCDA8FF),
                                fontSize = 13.5.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        } else {
            items(filteredBots, key = { it.id }) { bot ->
                BotCardItem(
                    bot = bot,
                    confirmDeleteId = confirmDeleteId,
                    onConfirmDeleteChange = onConfirmDeleteChange,
                    onOpenBot = onOpenBot,
                    onDeleteBot = onDeleteBot,
                    onTogglePrivacy = onTogglePrivacy,
                    onCloneBot = onCloneBot
                )
            }
        }
    }
}

@Composable
fun DiscoverTabContent(
    botList: List<BotEntity>, // PUBLIC BOTS ONLY
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onOpenBot: (String) -> Unit,
    onOpenSettings: () -> Unit,
    lazyListState: androidx.compose.foundation.lazy.LazyListState = androidx.compose.foundation.lazy.rememberLazyListState(),
    onCloneBot: ((String) -> Unit)? = null
) {
    val filteredPublicBots = remember(botList, searchQuery) {
        botList.filter { bot ->
            val query = searchQuery.trim().lowercase()
            query.isEmpty() ||
                    bot.aiName.lowercase().contains(query) ||
                    bot.universeName.lowercase().contains(query) ||
                    bot.scenario.lowercase().contains(query)
        }
    }

    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item(key = "top_header") {
            TopHeader(
                activeTab = "discover",
                botCount = 0,
                onOpenSettings = onOpenSettings
            )
        }

        item(key = "discover_header") {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = "🌐 Velora Keşfet • Topluluk Botları",
                    color = EmochiTextPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Diğer kullanıcıların ve yaratıcıların herkese açık paylaştığı botlar ve evrenler.",
                    color = EmochiTextSecondary,
                    fontSize = 11.5.sp,
                    modifier = Modifier.padding(top = 2.dp, bottom = 8.dp)
                )

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = onSearchQueryChange,
                    placeholder = {
                        Text(
                            text = "Keşfette herkese açık bot veya evren ara...",
                            color = EmochiTextMuted,
                            fontSize = 13.sp
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = customTextFieldColors(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Ara",
                            tint = EmochiTextMuted,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                )
            }
        }

        if (filteredPublicBots.isEmpty()) {
            item(key = "empty_discover") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(24.dp)
                    ) {
                        OrbView(hue = 190f, size = 52.dp)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Henüz Keşfette herkese açık bot paylaşılmadı.",
                            color = EmochiTextPrimary,
                            fontSize = 14.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Ayla ve Aetheria gibi varsayılan şablonlar size özeldir ve burada yayınlanmaz. Kendi oluşturduğunuz botları 'Açık' yaparak Keşfette paylaşabilirsiniz!",
                            color = EmochiTextMuted,
                            fontSize = 12.sp,
                            lineHeight = 17.sp,
                            modifier = Modifier.padding(top = 6.dp)
                        )
                    }
                }
            }
        } else {
            items(filteredPublicBots, key = { it.id }) { bot ->
                BotCardItem(
                    bot = bot,
                    confirmDeleteId = null,
                    onConfirmDeleteChange = {},
                    onOpenBot = onOpenBot,
                    onDeleteBot = null,
                    onTogglePrivacy = null,
                    onCloneBot = onCloneBot
                )
            }
        }
    }
}


fun getAppPresets(isEnglish: Boolean): List<BotEntity> {
    return listOf(
        BotEntity(
            id = "preset_aria",
            mode = "personal",
            aiName = if (isEnglish) "Aria (Cyber Security)" else "Aria (Siber Güvenlik)",
            aiPersonality = if (isEnglish) "Intelligent, direct-spoken, mysterious, witty, and deeply loyal." else "Zeki, doğrudan konuşan, gizemli, alaycı ama içten içe sadık.",
            scenario = if (isEnglish) "The story of a detective working in the city of Neo-Siberia." else "Neo-Siberia şehrinde çalışan bir dedektifin hikayesi.",
            universeName = "",
            keyCharactersJson = "[]",
            userCharName = if (isEnglish) "Detective" else "Dedektif",
            userCharDesc = if (isEnglish) "Experienced digital forensics expert from Merge City." else "Merge Şehrinden gelen tecrübeli adli bilişim uzmanı.",
            openingMessage = if (isEnglish) "*Aria looks up from her terminal screen and narrows her eyes at you.* Finally you arrived. Clearing the digital traces you left on the servers took hours..." else "*Aria terminal ekranından başını kaldırır ve gözlerini kısarak sana bakar.* Nihayet geldin. Sunucularda bıraktığın dijital izleri temizlemem saatlerimi aldı...",
            writingStyle = "rp",
            intensity = "normal",
            isPublic = false,
            isTemplate = true,
            pinnedMemory = if (isEnglish) "MISSION ::: CYBER ::: Investigating data breach in Merge City." else "GÖREV ::: SİBER ::: Merge şehrindeki veri sızıntısını araştırıyoruz."
        ),
        BotEntity(
            id = "preset_eldoria",
            mode = "universe",
            aiName = if (isEnglish) "Kingdom of Eldoria" else "Eldoria Krallığı",
            aiPersonality = if (isEnglish) "Eldoria Realm Narrator." else "Eldoria anlatıcısı.",
            scenario = if (isEnglish) "A feudal fantasy world on the brink of war. Dragons, kingdoms, and rogue guilds." else "Savaşın eşiğindeki feodal bir fantezi dünyası. Ejderhalar, krallıklar ve loncalar.",
            universeName = if (isEnglish) "Kingdom of Eldoria" else "Eldoria Krallığı",
            keyCharactersJson = "[]",
            userCharName = if (isEnglish) "Warrior" else "Savaşçı",
            userCharDesc = if (isEnglish) "A wandering guild mercenary." else "Gezgin bir lonca paralı askeri.",
            openingMessage = if (isEnglish) "Night settles over Mist Valley at the eastern border of Eldoria. As the tavern door creaks open, a cold wind sweeps in..." else "Eldoria Krallığı'nın doğu sınırındaki Sisli Vadi'de gece çöküyor. Hanın kapısı gıcırdayarak açıldığında içeri soğuk bir rüzgar giriyor...",
            writingStyle = "rp",
            intensity = "normal",
            isPublic = false,
            isTemplate = true,
            pinnedMemory = if (isEnglish) "UNIVERSE ::: ELDORIA ::: Tensions rise between royal guards and rebel guilds." else "EVREN ::: ELDORİA ::: Kraliyet muhafızları ve asi loncaları arasında gerilim tırmanıyor."
        ),
        BotEntity(
            id = "preset_tokyo",
            mode = "universe",
            aiName = if (isEnglish) "Tokyo - Neon Future" else "Tokyo - Neon Gelecek",
            aiPersonality = if (isEnglish) "Tokyo Neon Narrator." else "Tokyo anlatıcısı.",
            scenario = if (isEnglish) "A futuristic sci-fi roleplay experience in 2077 Tokyo, focusing on the thin line between technology and humanity." else "2077 Tokyo'sunda teknoloji ve insanlık arasındaki ince çizgiye odaklanan fütüristik bir bilim kurgu rol yapma deneyimi.",
            universeName = if (isEnglish) "Tokyo - Neon Future" else "Tokyo - Neon Gelecek",
            keyCharactersJson = "[]",
            userCharName = if (isEnglish) "Runner" else "Runner",
            userCharDesc = if (isEnglish) "A mercenary from the cyber underworld." else "Siber suç dünyasından gelen bir paralı asker.",
            openingMessage = if (isEnglish) "As you glide through the neon-drenched streets of Shibuya under the acid rain..." else "Şibuya'nın asit yağmuru altındaki neon caddelerinde süzülürken siber-implantlarındaki sinyal bir anda kesilir...",
            writingStyle = "rp",
            intensity = "normal",
            isPublic = false,
            isTemplate = true,
            pinnedMemory = if (isEnglish) "UNIVERSE ::: TOKYO ::: 2077 futuristic technology city." else "EVREN ::: TOKYO ::: 2077 fütüristik teknoloji şehri."
        )
    )
}
