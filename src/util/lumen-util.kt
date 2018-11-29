package io.sweetiebird.idea.lumen.util

import com.intellij.psi.tree.IElementType
import io.sweetiebird.idea.lumen.lang.LumenTokens

fun IElementType?.wsOrComment() = this != null && (LumenTokens.WHITESPACES.contains(this) || LumenTokens.COMMENTS.contains(this))
