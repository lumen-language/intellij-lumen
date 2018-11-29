package io.sweetiebird.idea.lumen.lang

import com.intellij.lang.BracePair
import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.FileTypeConsumer
import com.intellij.openapi.fileTypes.FileTypeFactory
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.fileTypes.WildcardFileNameMatcher
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import io.sweetiebird.idea.lumen.LumenConstants
import io.sweetiebird.idea.lumen.LumenIcons
import io.sweetiebird.idea.lumen.psi.LumenTypes.*

class LumenFileTypeFactory : FileTypeFactory() {
    override fun createFileTypes(consumer: FileTypeConsumer) {
        consumer.consume(LumenFileType, "${LumenConstants.L}")
//        consumer.consume(LumenFileType, "${LumenConstants.L};${LumenConstants.LS};${LumenConstants.LC}")
//        consumer.consume(LumenFileType, WildcardFileNameMatcher(LumenConstants.BOOT_CONFIG))
    }
}

object LumenFileType : LanguageFileType(LumenLanguage) {
    override fun getIcon() = LumenIcons.FILE
    override fun getName() = "Lumen"
    override fun getDefaultExtension() = LumenConstants.L
    override fun getDescription() = "Lumen Lisp"
}

object LumenLanguage : Language("Lumen")

object LumenTokens {
    @JvmField val L_FILE_TYPE = IFileElementType("LUMEN_FILE", LumenLanguage)
//    @JvmField val LS_FILE_TYPE = IFileElementType("LUMEN_SCRIPT_FILE", LumenScriptLanguage)

    @JvmField val LINE_COMMENT = IElementType("C_LINE_COMMENT", LumenLanguage)

    @JvmField val WHITESPACES = TokenSet.create(C_COMMA, TokenType.WHITE_SPACE)
    @JvmField val COMMENTS = TokenSet.create(LINE_COMMENT)
    @JvmField val STRINGS = TokenSet.create(C_STRING, C_STRING_UNCLOSED)
    @JvmField val LITERALS = TokenSet.create(C_BOOL, C_CHAR, C_HEXNUM, C_NIL, C_NUMBER, C_RATIO, C_RDXNUM, C_STRING, C_SYM)

    @JvmField val SHARPS = TokenSet.create(C_SHARP, C_SHARP_COMMENT, C_SHARP_QMARK, C_SHARP_QMARK_AT, C_SHARP_EQ, C_SHARP_HAT,
            C_SHARP_QUOTE, C_SHARP_NS, C_SHARP_SYM)
    @JvmField val MACROS = TokenSet.create(C_AT, C_COLON, C_COLONCOLON, C_HAT, C_QUOTE, C_SYNTAX_QUOTE, C_TILDE, C_COMMA_AT, C_TILDE_AT)

    @JvmField val PAREN1_ALIKE = TokenSet.create(C_PAREN1, C_BRACE1, C_BRACKET1)
    @JvmField val PAREN2_ALIKE = TokenSet.create(C_PAREN2, C_BRACE2, C_BRACKET2)
    @JvmField val PAREN_ALIKE = TokenSet.orSet(PAREN1_ALIKE, PAREN2_ALIKE)
    @JvmField val LIST_ALIKE = TokenSet.create(C_FUN, C_LIST, C_MAP, C_SET, C_VEC)

    @JvmField val FORMS = TokenSet.create(C_CONSTRUCTOR, C_FORM, C_FUN, C_KEYWORD,
            C_LIST, C_LITERAL, C_MAP, C_REGEXP,
            C_SET, C_SYMBOL, C_VEC)

    @JvmField val BRACE_PAIRS = listOf(
            BracePair(C_PAREN1, C_PAREN2, false),
            BracePair(C_BRACE1, C_BRACE2, false),
            BracePair(C_BRACKET1, C_BRACKET2, false))
}
