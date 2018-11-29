package io.sweetiebird.idea.lumen.parser

import com.intellij.lang.*
import com.intellij.lang.parser.GeneratedParserUtilBase
import com.intellij.lexer.FlexAdapter
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.impl.source.tree.CompositeElement
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import io.sweetiebird.idea.lumen.lang.LumenTokens
import io.sweetiebird.idea.lumen.parser._LumenLexer
import io.sweetiebird.idea.lumen.psi.LumenTypes
import io.sweetiebird.idea.lumen.psi.LumenTypes.*
import io.sweetiebird.idea.lumen.psi.impl.CFileImpl
import io.sweetiebird.idea.lumen.util.wsOrComment

class LumenLexer(language: Language) : FlexAdapter(_LumenLexer(language))

class LumenParserDefinition : LumenParserDefinitionBase() {
    override fun getFileNodeType() = LumenTokens.L_FILE_TYPE
}

//class LumenScriptParserDefinition : LumenParserDefinitionBase() {
//    override fun getFileNodeType() = LumenTokens.LS_FILE_TYPE
//}

class LumenASTFactory : ASTFactory() {
    override fun createComposite(type: IElementType): CompositeElement? = LumenTypes.Factory.createElement(type)
}

abstract class LumenParserDefinitionBase : ParserDefinition {

    override fun createLexer(project: Project?) = LumenLexer(fileNodeType.language)
    override fun createParser(project: Project?) = LumenParser()
    override fun createFile(viewProvider: FileViewProvider?) = CFileImpl(viewProvider!!, fileNodeType.language)
    override fun createElement(node: ASTNode?) = throw UnsupportedOperationException(
            "$node" + (node?.elementType?.language ?: fileNodeType.language).let {
                "; ASTFactory(${it.id})=${LanguageASTFactory.INSTANCE.forLanguage(it)}"
            })

    override fun getStringLiteralElements() = LumenTokens.STRINGS
    override fun getWhitespaceTokens() = LumenTokens.WHITESPACES
    override fun getCommentTokens() = LumenTokens.COMMENTS

    override fun spaceExistanceTypeBetweenTokens(left: ASTNode, right: ASTNode): ParserDefinition.SpaceRequirements {
        val lt = left.elementType
        val rt = right.elementType
        if (rt == C_COMMA || LumenTokens.MACROS.contains(lt) || LumenTokens.SHARPS.contains(lt)) {
            return ParserDefinition.SpaceRequirements.MUST_NOT
        }
        if (lt == C_DOT || lt == C_DOTDASH ||
                lt == C_SLASH && rt == C_SYM ||
                lt == C_SYM && rt == C_SLASH) {
            return ParserDefinition.SpaceRequirements.MUST_NOT
        }
        for (p in LumenTokens.BRACE_PAIRS) {
            if (lt == p.leftBraceType || rt == p.rightBraceType) {
                return ParserDefinition.SpaceRequirements.MAY
            }
        }
        return ParserDefinition.SpaceRequirements.MUST
    }
}

class LumenParserUtil {
    @Suppress("UNUSED_PARAMETER")
    companion object {
        @JvmStatic fun adapt_builder_(root: IElementType, builder: PsiBuilder, parser: PsiParser, extendsSets: Array<TokenSet>?): PsiBuilder =
                GeneratedParserUtilBase.adapt_builder_(root, builder, parser, extendsSets).apply {
                    (this as? GeneratedParserUtilBase.Builder)?.state?.braces = null
                }

        @JvmStatic fun parseTree(b: PsiBuilder, l: Int, p: GeneratedParserUtilBase.Parser) =
                GeneratedParserUtilBase.parseAsTree(GeneratedParserUtilBase.ErrorState.get(b), b, l,
                        GeneratedParserUtilBase.DUMMY_BLOCK, false, p, GeneratedParserUtilBase.TRUE_CONDITION)

        @JvmStatic fun nospace(b: PsiBuilder, l: Int): Boolean {
            if (space(b, l)) {
                b.mark().apply { b.tokenType; error("no <whitespace> allowed") }
                        .setCustomEdgeTokenBinders(WhitespacesBinders.GREEDY_LEFT_BINDER, WhitespacesBinders.GREEDY_RIGHT_BINDER)
            }
            return true
        }

        @JvmStatic fun space(b: PsiBuilder, l: Int): Boolean {
            return b.rawLookup(0).wsOrComment() || b.rawLookup(-1).wsOrComment()
        }

        private val RECOVER_SET = TokenSet.orSet(
                LumenTokens.SHARPS, LumenTokens.MACROS, LumenTokens.PAREN_ALIKE, LumenTokens.LITERALS,
                TokenSet.create(C_DOT, C_DOTDASH))

        @JvmStatic fun formRecover(b: PsiBuilder, l: Int): Boolean {
            return !RECOVER_SET.contains(b.tokenType)
        }

        @JvmStatic fun rootFormRecover(b: PsiBuilder, l: Int): Boolean {
            val type = b.tokenType
            return LumenTokens.PAREN2_ALIKE.contains(type) || !RECOVER_SET.contains(type)
        }
    }
}

