package io.sweetiebird.idea.lumen.psi.impl

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.lang.Language
import com.intellij.psi.FileViewProvider
import io.sweetiebird.idea.lumen.lang.LumenFileType
import io.sweetiebird.idea.lumen.psi.CFile
import io.sweetiebird.idea.lumen.psi.IDef
import io.sweetiebird.idea.lumen.psi.SymKey
import sun.tools.java.Imports

open class CFileImpl(viewProvider: FileViewProvider, language: Language) :
        PsiFileBase(viewProvider, language), CFile {

    override fun getFileType() = LumenFileType
    override fun toString() = "${javaClass.simpleName}:$name"

}

    internal class NSDef(
        val key: SymKey,
        val imports: List<Imports>
) : IDef by key
