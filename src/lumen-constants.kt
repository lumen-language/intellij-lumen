package io.sweetiebird.idea.lumen

import com.intellij.icons.AllIcons
import com.intellij.openapi.util.IconLoader

object LumenConstants {

    @JvmStatic val L_CORE_PATH = "/lumen/core.l"
    @JvmStatic val L_SPEC_PATH = "/lumen/spec/alpha.l"
    @JvmStatic val LS_CORE_PATH = "/ls/core.ls"

    @JvmStatic val LS = "ls"
    @JvmStatic val L = "l"
    @JvmStatic val LC = "lc"

    @JvmStatic val NS_USER = "user"
    @JvmStatic val NS_SPEC = "lumen.spec"
    @JvmStatic val NS_SPEC_ALPHA = "lumen.spec.alpha"

    @JvmStatic val LUMEN_CORE = "lumen.core"
    @JvmStatic val LS_CORE = "ls.core"
    @JvmStatic val CORE_NAMESPACES = hashSetOf(LUMEN_CORE, LS_CORE)

    @JvmStatic val SYMBOLIC_VALUES = hashSetOf("Inf", "-Inf", "NaN")

    @JvmStatic val SPECIAL_FORMS = "\\s+".toRegex().split("""
    def mac if do quote var
    recur throw
    try catch finally
    monitor-enter monitor-exit
    . new set!
     fn* let* loop* letfn* case* import* reify* deftype*
    in-ns load-file
    """.trim()).toSet()

    @JvmStatic val CONTROL_SYMBOLS = "\\s+".toRegex().split("""
    fn if do let loop
    try catch finally

    iflet whenlet
    let-if let-when
    aif awhen

    with withs

    step each down forlen

    and or when when-not when-let when-first if-not if-let cond condp case when-some if-some
    for doseq dotimes while
    .. doto -> ->> as-> cond-> cond->> some-> some->>
    binding locking time with-in-str with-local-vars with-open with-out-str with-precision with-redefs with-redefs-fn
    lazy-cat lazy-seq delay
    assert comment doc
    """.trim()).toSet()

    @JvmStatic val DEF_ALIKE_SYMBOLS = "\\s+".toRegex().split("""
    def defn defn- defmacro defonce deftype defrecord defstruct defmulti defprotocol
    def-aset definline definterface
    define define-global define-export define-local define-macro define-symbol define-special
    defcurried deftype* defrecord* create-ns
    mac defopl define-expander define-setter define-getter define-transformer define-macro define-special define-symbol define define-global
    defop-raw defopr-raw defop defopr testop
    %global-function %local-function %local
    defopt defopg defope defopa adop edop newscache
    define-test
    rfn defset defmemo defcache defhook xdef
    defun
    """.trim()).toSet()

    @JvmStatic val FN_ALIKE_SYMBOLS = "\\s+".toRegex().split("""fn fn* rfn afn lambda""").toSet()

    @JvmStatic val LET_ALIKE_SYMBOLS = "\\s+".toRegex().split("""
    with withs
    iflet whenlet
    let-if let-when let-macro let-symbol

    step each down forlen

    let let* loop when-let when-some
    if-let if-some with-open when-first with-redefs
    for doseq dotimes
    with-local-vars
    """.trim()).toSet()

    @JvmStatic val NS_ALIKE_SYMBOLS = "\\s+".toRegex().split("""
    ns in-ns import require require-macros use refer refer-lumen alias
    """.trim()).toSet()

    @JvmStatic val TYPE_META_ALIASES = "\\s+".toRegex().split("""
    int ints long longs float floats double doubles void short shorts
     boolean booleans byte bytes char chars objects
    """.trim()).toSet()

    @JvmStatic val OO_ALIKE_SYMBOLS = "\\s+".toRegex().split("""
    defprotocol definterface deftype defrecord extend-protocol extend-type proxy reify
    define-method define-type define-struct define-record define-protocol define-proxy define-class define-interface
    """.trim()).toSet()

    @JvmStatic val LEIN_CONFIG = "project.l"
    @JvmStatic val BOOT_CONFIG = "build.boot"
    @JvmStatic val DEPS_CONFIG = "deps.edn"

    @JvmStatic val LEIN_VM_OPTS = "lumen.kit.lein.vm.opts"

    // lumenscript-specific
    @JvmStatic val JS_OBJ = "#js"
    @JvmStatic val JS_NAMESPACES = hashSetOf("js", "Math", "goog")

    //core.ls/special-symbol?
    @JvmStatic val LS_SPECIAL_FORMS = "\\s+".toRegex().split("""
    if def fn* do let* loop* letfn* throw try catch finally
    recur new set! ns deftype* defrecord* . js* quote var

    set get guard

    Infinity -Infinity
    """.trim()).toSet()

    @JvmStatic val LS_TYPES = "\\s+".toRegex().split("""
    default nil object boolean number string array function undefined
    """.trim()).toSet()

    // java specific
    @JvmStatic val J_OBJECT = "java.lang.Object"
    @JvmStatic val J_CLASS = "java.lang.Class"
    @JvmStatic val J_READER = "java.io.Reader"
    @JvmStatic val J_WRITER = "java.io.Writer"
    @JvmStatic val C_VAR = "lumen.lang.Var"
    @JvmStatic val C_NAMESPACE = "lumen.lang.Namespace"

}

object LumenIcons {
    @JvmStatic val LUMEN_ICON = IconLoader.getIcon("/icons/lumen.png")
    @JvmStatic val FILE = IconLoader.getIcon("/icons/lumenFile.svg")
    @JvmStatic val NAMESPACE = IconLoader.getIcon("/icons/namespace.svg")
    @JvmStatic val SYMBOL = IconLoader.getIcon("/icons/symbol.svg")
    @JvmStatic val DEFN = AllIcons.Nodes.Function
    @JvmStatic val MACRO = AllIcons.Nodes.AbstractMethod
    @JvmStatic val FIELD = AllIcons.Nodes.Field
    @JvmStatic val METHOD = AllIcons.Nodes.Method
}