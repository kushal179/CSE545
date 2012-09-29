define("ace/mode/arden-highlight-rules",["require","exports","module","ace/lib/oop","ace/lib/lang","ace/mode/text_highlight_rules","ace/mode/doc_comment_highlight_rules"],
		function(require,exports,module,oop,lang,textHL,docHL){

	var TextHighlightRules = textHL.TextHighlightRules;

	var ArdenHighlightRules = function() {

		var identifier = "[$A-Za-z_\\x7f-\\uffff][$\\w\\x7f-\\uffff]*";
        var stringfill = {
            token : "string",
            merge : true,
            regex : ".+"
        };

        var keywords = lang.arrayToMap((
            "conclude if endif elseif then let else OBJECT NEW READ AS MESSAGE EVENT DESTINATION WRITE where is in not present now occurs after AND OR be within past return include less than greater equal occurred to at from before ago latest exist").split(" ")
        );
        
        var langConstant = lang.arrayToMap((
            "true|false|null|undefined").split("|")
        );
        
        var illegal = lang.arrayToMap((
            "case|const|default|function|var|void|with|enum|export|implements|data:|" +
            "interface|let|package|private|protected|public|static|yield|" +
            "__hasProp|extends|slice|bind|indexOf").split("|")
        );
        
        var supportClass = lang.arrayToMap((
            "maintenance library end call knowledge").split(" ")
        );
        
        var supportFunction = lang.arrayToMap((
            "title mlmname arden version institution author specialist date validation purpose explanation keywords citations links urgency SSF_Parameter_Definition evoke logic action type data").split(" ")
        );

        this.$rules = {
            start : [
                {
                    token : "identifier",
                    regex : "(?:(?:\\.|::)\\s*)" + identifier
                },  {
                    token: function(value) {
                        if (keywords.hasOwnProperty(value))
                            return "keyword";
                        else if (langConstant.hasOwnProperty(value))
                            return "constant.language";
                        else if (illegal.hasOwnProperty(value))
                            return "invalid.illegal";
                        else if (supportClass.hasOwnProperty(value))
                            return "language.support.class";
                        else if (supportFunction.hasOwnProperty(value))
                            return "language.support.function";
                        else
                            return "identifier";
                    },
                    regex : identifier
                }, {
                    token : "constant.numeric",
                    regex : "(?:0x[\\da-fA-F]+|(?:\\d+(?:\\.\\d+)?|\\.\\d+)(?:[eE][+-]?\\d+)?)"
                }, {
                    token : "string",
                    merge : true,
                    regex : "'''",
                    next : "qdoc"
                }, {
                    token : "string",
                    merge : true,
                    regex : '"""',
                    next : "qqdoc"
                }, {
                    token : "string",
                    merge : true,
                    regex : "'",
                    next : "qstring"
                }, {
                    token : "string",
                    merge : true,
                    regex : '"',
                    next : "qqstring"
                }, {
                    token : "string",
                    merge : true,
                    regex : "`",
                    next : "js"
                }, {
                    token : "string.regex",
                    merge : true,
                    regex : "///",
                    next : "heregex"
                }, {
                    token : "string.regex",
                    regex : "/(?!\\s)[^[/\\n\\\\]*(?: (?:\\\\.|\\[[^\\]\\n\\\\]*(?:\\\\.[^\\]\\n\\\\]*)*\\])[^[/\\n\\\\]*)*/[imgy]{0,4}(?!\\w)"
                }, {
                    token : "comment",
                    merge : true,
                    regex : "###(?!#)",
                    next : "comment"
                }, {
                    token : "comment",
                    regex : "#.*"
                }, {
                    token : "punctuation.operator",
                    regex : "\\?|\\:|\\,|\\."
                }, {
                    token : "keyword.operator",
                    regex : "(?:[\\-=]>|[-+*/%<>&|^!?=]=|>>>=?|\\-\\-|\\+\\+|::|&&=|\\|\\|=|<<=|>>=|\\?\\.|\\.{2,3}|\\!)"
                }, {
                    token : "paren.lparen",
                    regex : "[({[]"
                }, {
                    token : "paren.rparen",
                    regex : "[\\]})]"
                }, {
                    token : "text",
                    regex : "\\s+"
                }],
            
            qdoc : [{
                token : "string",
                regex : ".*?'''",
                next : "start"
            }, stringfill],
            
            qqdoc : [{
                token : "string",
                regex : '.*?"""',
                next : "start"
            }, stringfill],
            
            qstring : [{
                token : "string",
                regex : "[^\\\\']*(?:\\\\.[^\\\\']*)*'",
                merge : true,
                next : "start"
            }, stringfill],
            
            qqstring : [{
                token : "string",
                regex : '[^\\\\"]*(?:\\\\.[^\\\\"]*)*"',
                merge : true,
                next : "start"
            }, stringfill],
            
            js : [{
                token : "string",
                merge : true,
                regex : "[^\\\\`]*(?:\\\\.[^\\\\`]*)*`",
                next : "start"
            }, stringfill],
            
            heregex : [{
                token : "string.regex",
                regex : '.*?///[imgy]{0,4}',
                next : "start"
            }, {
                token : "comment.regex",
                regex : "\\s+(?:#.*)?"
            }, {
                token : "string.regex",
                merge : true,
                regex : "\\S+"
            }],
            
            comment : [{
                token : "comment",
                regex : '.*?###',
                next : "start"
            }, {
                token : "comment",
                merge : true,
                regex : ".+"
            }]
        };
    };

	oop.inherits(ArdenHighlightRules, TextHighlightRules);

	exports.ArdenHighlightRules = ArdenHighlightRules;
});