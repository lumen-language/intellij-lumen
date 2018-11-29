package io.sweetiebird.idea.lumen.psi

import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.ILeafElementType
import com.intellij.util.containers.JBIterable
import io.sweetiebird.idea.lumen.LumenConstants
import io.sweetiebird.idea.lumen.lang.LumenLanguage

enum class Dialect(val coreNs: String) {
    L(LumenConstants.LUMEN_CORE),
}

enum class Role {
    NONE, DEF, NS, NAME,
    RCOND, RCOND_S,
    PROTOTYPE,
    ARG_VEC, BND_VEC, FIELD_VEC, BODY,
    ARG, BND, FIELD // currently not set
}

const val FLAG_COMMENTED = 0x1

interface CElement : NavigatablePsiElement {
    val role: Role
    val def: IDef?
    val resolvedNs: String?
    val flags: Int
}

interface LumenElementType
class LumenTokenType(name: String) : IElementType(name, LumenLanguage), ILeafElementType {
    override fun createLeafNode(leafText: CharSequence) = CToken(this, leafText)
}
class LumenNodeType(name: String) : IElementType(name, LumenLanguage), LumenElementType
class CToken(tokenType: LumenTokenType, text: CharSequence) : LeafPsiElement(tokenType, text)

interface CFile : PsiFile {
//    val namespace: String

//    fun defs(dialect: Dialect = Dialect.L): JBIterable<CList>
}

interface IDef {
    val type: String
    val name: String
    val namespace: String
}

data class SymKey(
        override val name: String,
        override val namespace: String,
        override val type: String
) : IDef {
    constructor(def : IDef): this(def.name, def.namespace, def.type)
}

class Def(
        val key: SymKey,
        val protos: List<Prototype>,
        val meta: Map<String, String>
) : IDef by key

class Prototype(val args: List<String>, val typeHint: String?)

