package com.example.sdui_engine.presentation.renderer

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.mylibrary.ds.button.DsButton
import com.example.mylibrary.ds.input.DsInput
import com.example.sdui_engine.data.model.SduiComponent
import com.example.sdui_engine.data.model.SduiTextStyle
import com.example.sdui_engine.utils.SduiFontCache
import com.example.sdui_engine.utils.dp
import javax.inject.Inject

class SduiRendererImpl @Inject constructor() : SduiRenderer {

    private val viewRegistry = mutableMapOf<String, View>()



    override fun findView(id: String): View? = viewRegistry[id]

    override fun getAllInputData(): Map<String, String> {
        return viewRegistry.filterValues { it is DsInput }
            .mapValues { (it.value as DsInput).text.toString() }
    }

    override fun clearAllErrors() {
        viewRegistry.clear()
    }


    override suspend fun render(
        components: List<SduiComponent>,
        container: ViewGroup,
        onAction: (String) -> Unit
    ) {
        container.removeAllViews()
        components.forEach { component ->
            val view = when (component) {
                is SduiComponent.Text -> createTextView(container, component, onAction)
                is SduiComponent.Input -> createInputView(container, component, onAction)
                is SduiComponent.Button -> createButtonView(container, component, onAction)
                is SduiComponent.CompoundText -> createCompoundTextView(
                    container,
                    component,
                    onAction
                )

                is SduiComponent.Icon -> createIconView(container, component, onAction)
            }
            container.addView(view)
        }
    }

    private fun createIconView(
        container: ViewGroup,
        component: SduiComponent.Icon,
        onAction: (String) -> Unit
    ): View {
        val p = component.props
        val context = container.context

        return ImageButton(context).apply {

            val appPackage = context.applicationContext.packageName
            val resId = context.resources.getIdentifier(
                p.iconRes,
                "drawable",
                appPackage
            )

            if (resId != 0) {
                setImageResource(resId)
            } else {
                Log.e("SDUI_ICON", "Drawable não encontrado: ${p.iconRes}")
            }

            if (p.iconColor.isNotEmpty()) {
                imageTintList =
                    ColorStateList.valueOf(parseSafeColor(p.iconColor))
            }

            scaleType = ImageView.ScaleType.CENTER_INSIDE
            adjustViewBounds = true

            val outValue = TypedValue()
            context.theme.resolveAttribute(
                android.R.attr.selectableItemBackgroundBorderless,
                outValue,
                true
            )
            setBackgroundResource(outValue.resourceId)

            contentDescription = p.accessibilityText

            layoutParams = LinearLayout.LayoutParams(
                p.size.dp(context),
                p.size.dp(context)
            ).apply {
                setMargins(
                    p.marginLeft.dp(context),
                    p.marginTop.dp(context),
                    p.marginRight.dp(context),
                    p.marginBottom.dp(context)
                )
            }

            setOnClickListener {
                onAction(p.id)
            }
        }
    }

    private fun createTextView(
        container: ViewGroup,
        component: SduiComponent.Text,
        onAction: (String) -> Unit
    ): View {
        return TextView(container.context).apply {
            val p = component.props
            text = p.description
            textSize = p.textSize
            setTextColor(parseSafeColor(p.textColor))
            gravity = parseGravity(p.gravity)

            val mainFont = SduiFontCache.getPoppins(context)
            val style = SduiTextStyle.Companion.fromString(p.textStyle)
            typeface = Typeface.create(mainFont, style.nativeStyle)

            layoutParams = createLayoutParams(
                p.marginLeft.dp(context),
                p.marginTop.dp(context),
                p.marginRight.dp(context),
                p.marginBottom.dp(context)
            )

            setOnClickListener {
                onAction(p.id)
            }
        }
    }

    private fun createInputView(
        container: ViewGroup,
        component: SduiComponent.Input,
        onAction: (String) -> Unit
    ): View {
        return DsInput(container.context).apply {
            val p = component.props
            val normalizedType = p.inputKeyboardType?.lowercase() ?: "text"

            id = View.generateViewId()
            hint = p.hint

            val typeEnum = when (normalizedType) {
                "cpf" -> DsInput.KeyboardType.CPF
                "number" -> DsInput.KeyboardType.NUMBER
                "phone" -> DsInput.KeyboardType.PHONE
                "email" -> DsInput.KeyboardType.EMAIL
                "password" -> DsInput.KeyboardType.PASSWORD
                "date" -> DsInput.KeyboardType.DATE
                else -> DsInput.KeyboardType.TEXT
            }
            setKeyboardType(typeEnum)

            if (typeEnum == DsInput.KeyboardType.PASSWORD) {
                if (hint.isNullOrEmpty()) hint = p.accessibilityText
            } else {
                contentDescription = p.accessibilityText
            }

            layoutParams = createLayoutParams(
                p.marginLeft.dp(context),
                p.marginTop.dp(context),
                p.marginRight.dp(context),
                p.marginBottom.dp(context)
            )

            setTag(p.id)

        }
    }

    private fun createButtonView(
        container: ViewGroup,
        component: SduiComponent.Button,
        onAction: (String) -> Unit
    ): View {
        return DsButton(container.context).apply {
            val p = component.props

            id = View.generateViewId()
            setDsText(p.text)
            contentDescription = p.accessibilityText

            if (p.backgroundColor.isNotEmpty()) {
                setDsBackgroundColor(parseSafeColor(p.backgroundColor))
            }

            layoutParams = createLayoutParams(
                p.marginLeft.dp(context),
                p.marginTop.dp(context),
                p.marginRight.dp(context),
                p.marginBottom.dp(context)
            )


            setOnClickListener {
                onAction(p.id)
            }
        }
    }


    private fun createCompoundTextView(
        container: ViewGroup,
        component: SduiComponent.CompoundText,
        onAction: (String) -> Unit
    ): View {
        return TextView(container.context).apply {
            val p = component.props

            val firstPart = "${p.description} "
            val secondPart = p.descriptionBold

            val builder = SpannableStringBuilder().apply {

                append(firstPart)
                append(secondPart)

                val start = firstPart.length
                val end = firstPart.length + secondPart.length

                setSpan(
                    StyleSpan(Typeface.BOLD),
                    start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                setSpan(
                    ForegroundColorSpan(parseSafeColor(p.colorBold)),
                    start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            text = builder
            textSize = p.textSize
            setTextColor(parseSafeColor(p.textColor))
            gravity = parseGravity(p.gravity)

            layoutParams = createLayoutParams(
                p.marginLeft.dp(context),
                p.marginTop.dp(context),
                p.marginRight.dp(context),
                p.marginBottom.dp(context)
            )

            isClickable = true
            isFocusable = true
            setOnClickListener {
                onAction(p.id)
            }

            contentDescription = p.accessibilityText
        }
    }


    // --- Helpers ---
    private fun createLayoutParams(l: Int, t: Int, r: Int, b: Int): LinearLayout.LayoutParams {
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(l, t, r, b)

        return params
    }

    private fun parseSafeColor(colorStr: String): Int {
        return try {
            if (colorStr.isEmpty()) Color.BLACK else Color.parseColor(colorStr)
        } catch (e: Exception) {
            Color.BLACK
        }
    }

    private fun parseGravity(gravityStr: String): Int {
        return when (gravityStr.lowercase()) {
            "center" -> Gravity.CENTER
            "right" -> Gravity.END
            else -> Gravity.START
        }
    }
}