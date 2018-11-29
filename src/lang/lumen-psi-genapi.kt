package io.sweetiebird.idea.lumen.psi.impl

import com.intellij.psi.impl.source.tree.CompositePsiElement
import com.intellij.psi.tree.IElementType
import io.sweetiebird.idea.lumen.psi.CElement
import io.sweetiebird.idea.lumen.psi.IDef
import io.sweetiebird.idea.lumen.psi.Role
import sun.tools.java.Imports

open class CComposite(tokenType: IElementType) : CompositePsiElement(tokenType), CElement {
    override val role: Role get() = role(data)
    override val flags: Int get() = role.run { flagsImpl }
    override val def: IDef? get() = data as? IDef
    override val resolvedNs: String? get() = data as? String

    @JvmField internal var dataImpl: Any? = null
    @JvmField internal var flagsImpl: Int = 0

    internal val roleImpl: Role get() = role(dataImpl)
//    internal val data: Any get() = dataImpl ?: (containingFile as CFileImpl).checkState().let {
//        dataImpl ?: Role.NONE.also { dataImpl = it }
//    }
    internal val data: Any get() = null?:1

    private fun role(data: Any?): Role = when (data) {
        is Role -> data
        is Imports, is NSDef -> Role.NS
        is IDef -> Role.DEF
        else -> Role.NONE
    }
}

