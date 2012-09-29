define("ace/mode/drools-highlight-rules",["require","exports","module","ace/lib/oop","ace/lib/lang","ace/mode/text_highlight_rules","ace/mode/doc_comment_highlight_rules"],
		function(require,exports,module,oop,lang,textHL,docHL){

	var DocCommentHighlightRules = docHL.DocCommentHighlightRules;
	var TextHighlightRules = textHL.TextHighlightRules;

	var DroolsHighlightRules = function() {

	    // taken from http://download.oracle.com/javase/tutorial/java/nutsandbolts/_keywords.html
	    var keywords = lang.arrayToMap(
	    ("abstract|continue|for|new|switch|" +
	    "assert|default|goto|package|synchronized|" +
	    "boolean|do|if|private|this|" +
	    "break|double|implements|protected|throw|" +
	    "byte|else|import|public|throws|global|" +
	    "case|enum|instanceof|return|transient|" +
	    "catch|if|then|when|rule|extends|int|short|try|" +
	    "char|final|interface|static|void|end|" +
	    "class|finally|long|strictfp|volatile|" +
	    "const|float|native|super|while").split("|")
	    );

	    var buildinConstants = lang.arrayToMap(
	        ("null|Infinity|NaN|undefined").split("|")
	    );

	    var langClasses = lang.arrayToMap(
	        ("AbstractMethodError|AssertionError|ClassCircularityError|"+
	        "ClassFormatError|Deprecated|EnumConstantNotPresentException|"+
	        "ExceptionInInitializerError|IllegalAccessError|"+
	        "IllegalThreadStateException|InstantiationError|InternalError|"+
	        "NegativeArraySizeException|NoSuchFieldError|Override|Process|"+
	        "ProcessBuilder|SecurityManager|StringIndexOutOfBoundsException|"+
	        "SuppressWarnings|TypeNotPresentException|UnknownError|"+
	        "UnsatisfiedLinkError|UnsupportedClassVersionError|VerifyError|"+
	        "InstantiationException|IndexOutOfBoundsException|"+
	        "ArrayIndexOutOfBoundsException|CloneNotSupportedException|"+
	        "NoSuchFieldException|IllegalArgumentException|NumberFormatException|"+
	        "SecurityException|Void|InheritableThreadLocal|IllegalStateException|"+
	        "InterruptedException|NoSuchMethodException|IllegalAccessException|"+
	        "UnsupportedOperationException|Enum|StrictMath|Package|Compiler|"+
	        "Readable|Runtime|StringBuilder|Math|IncompatibleClassChangeError|"+
	        "NoSuchMethodError|ThreadLocal|RuntimePermission|ArithmeticException|"+
	        "NullPointerException|Long|Integer|Short|Byte|Double|Number|Float|"+
	        "Character|Boolean|StackTraceElement|Appendable|StringBuffer|"+
	        "Iterable|ThreadGroup|Runnable|Thread|IllegalMonitorStateException|"+
	        "StackOverflowError|OutOfMemoryError|VirtualMachineError|"+
	        "ArrayStoreException|ClassCastException|LinkageError|"+
	        "NoClassDefFoundError|ClassNotFoundException|RuntimeException|"+
	        "Exception|ThreadDeath|Error|Throwable|System|ClassLoader|"+
	        "Cloneable|Class|CharSequence|Comparable|String|Object").split("|")
	    );
	    
	    var importClasses = lang.arrayToMap(
	        ("").split("|")
	    );
	    // regexp must not have capturing parentheses. Use (?:) instead.
	    // regexps are ordered -> the first match is used

	    this.$rules = {
	        "start" : [
	            {
	                token : "comment",
	                regex : "\\/\\/.*$"
	            },
	            new DocCommentHighlightRules().getStartRule("doc-start"),
	            {
	                token : "comment", // multi line comment
	                merge : true,
	                regex : "\\/\\*",
	                next : "comment"
	            }, {
	                token : "string.regexp",
	                regex : "[/](?:(?:\\[(?:\\\\]|[^\\]])+\\])|(?:\\\\/|[^\\]/]))*[/]\\w*\\s*(?=[).,;]|$)"
	            }, {
	                token : "string", // single line
	                regex : '["](?:(?:\\\\.)|(?:[^"\\\\]))*?["]'
	            }, {
	                token : "string", // single line
	                regex : "['](?:(?:\\\\.)|(?:[^'\\\\]))*?[']"
	            }, {
	                token : "constant.numeric", // hex
	                regex : "0[xX][0-9a-fA-F]+\\b"
	            }, {
	                token : "constant.numeric", // float
	                regex : "[+-]?\\d+(?:(?:\\.\\d*)?(?:[eE][+-]?\\d+)?)?\\b"
	            }, {
	                token : "constant.language.boolean",
	                regex : "(?:true|false)\\b"
	            }, {
	                token : function(value) {
	                    if (value == "this")
	                        return "variable.language";
	                    else if (keywords.hasOwnProperty(value))
	                        return "keyword";
	                    else if (langClasses.hasOwnProperty(value))
	                        return "support.function";
	                    else if (importClasses.hasOwnProperty(value))
	                        return "support.function";
	                    else if (buildinConstants.hasOwnProperty(value))
	                        return "constant.language";
	                    else
	                        return "identifier";
	                },
	                // TODO: Unicode escape sequences
	                // TODO: Unicode identifiers
	                regex : "[a-zA-Z_$][a-zA-Z0-9_$]*\\b"
	            }, {
	                token : "keyword.operator",
	                regex : "!|\\$|%|&|\\*|\\-\\-|\\-|\\+\\+|\\+|~|===|==|=|!=|!==|<=|>=|<<=|>>=|>>>=|<>|<|>|!|&&|\\|\\||\\?\\:|\\*=|%=|\\+=|\\-=|&=|\\^=|\\b(?:in|instanceof|new|delete|typeof|void)"
	            }, {
	                token : "lparen",
	                regex : "[[({]"
	            }, {
	                token : "rparen",
	                regex : "[\\])}]"
	            }, {
	                token : "text",
	                regex : "\\s+"
	            }
	        ],
	        "comment" : [
	            {
	                token : "comment", // closing comment
	                regex : ".*?\\*\\/",
	                next : "start"
	            }, {
	                token : "comment", // comment spanning whole line
	                merge : true,
	                regex : ".+"
	            }
	        ]
	    };
	    
	    this.embedRules(DocCommentHighlightRules, "doc-",
	        [ new DocCommentHighlightRules().getEndRule("start") ]);
	};

	oop.inherits(DroolsHighlightRules, TextHighlightRules);

	exports.DroolsHighlightRules = DroolsHighlightRules;
});