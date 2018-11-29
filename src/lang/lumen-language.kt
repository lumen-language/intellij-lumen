package io.sweetiebird.idea.lumen.lang

import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.FileTypeConsumer
import com.intellij.openapi.fileTypes.FileTypeFactory
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.fileTypes.WildcardFileNameMatcher
import io.sweetiebird.idea.lumen.LumenConstants
import io.sweetiebird.idea.lumen.LumenIcons

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
