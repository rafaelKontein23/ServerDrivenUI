package com.example.sdui_engine.presentation.renderer

import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
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

    override fun render(
        components: List<SduiComponent>,
        container: ViewGroup,
        onAction: (String) -> Unit
    ) {
        container.removeAllViews()
        components.forEach { component ->
            val view = when (component) {
                is SduiComponent.Text -> createTextView(container, component, onAction)
                is SduiComponent.Input -> createInputView(container, component)
                is SduiComponent.Button -> createButtonView(container, component, onAction)
                is SduiComponent.CompoundText -> createCompoundTextView(container, component, onAction)
            }
            container.addView(view)
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

    private fun createInputView(container: ViewGroup, component: SduiComponent.Input): View {
        return DsInput(container.context).apply {
            val p = component.props

            Log.d("SDUI_INPUT_RAW", "Input ID: ${p.id}")
            Log.d("SDUI_INPUT_RAW", "inputKeyboardType cru do JSON: '${p.inputKeyboardType}' (type: ${p.inputKeyboardType?.javaClass?.simpleName ?: "null"})")
            Log.d("SDUI_INPUT_RAW", "inputType fallback (se existir): '${p.inputKeyboardType ?: "não tem"}'")
            Log.d("SDUI_INPUT_RAW", "margin_top: '${p.marginTop}' (type: ${p.marginTop?.javaClass?.simpleName})")
            Log.d("SDUI_INPUT_RAW", "margin_left: '${p.marginLeft}', margin_right: '${p.marginRight}'")

            val normalizedType = p.inputKeyboardType?.lowercase() ?: p.inputKeyboardType?.lowercase() ?: "desconhecido"
            Log.d("SDUI_INPUT", "Tipo normalizado usado no when: '$normalizedType'")

            id = View.generateViewId()
            hint = p.hint
            contentDescription = p.accessibilityText

            Log.d("SDUI_INPUT", "InputType atual ANTES do set: ${inputType}")

            when (normalizedType) {
                "email" -> {
                    Log.d("SDUI_INPUT", "Entrou no branch EMAIL")
                    setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
                }
                "password" -> {
                    Log.d("SDUI_INPUT", "Entrou no branch PASSWORD → setando TYPE_TEXT_VARIATION_PASSWORD")
                    setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                }
                else -> {
                    Log.d("SDUI_INPUT", "Entrou no branch DEFAULT (TEXT)")
                    setInputType(InputType.TYPE_CLASS_TEXT)
                }
            }

            Log.d("SDUI_INPUT", "InputType FINAL após set: ${inputType}")

            layoutParams = createLayoutParams(
                p.marginLeft.dp(context),
                p.marginTop.dp(context),
                p.marginRight.dp(context),
                p.marginBottom.dp(context)
            )

            Log.d("SDUI_INPUT", "Input ${p.id} margins px → top: ${(layoutParams as LinearLayout.LayoutParams).topMargin}")
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