define("ace/mode/arden",["require","exports","module","ace/lib/oop","ace/mode/javascript","ace/tokenizer","ace/mode/arden-highlight-rules"],
		function(require, exports, module,oop,jscript,tokenizer,arden_highlight_rules) {
"use strict";

var JavaScriptMode = jscript.Mode;
var ArdenHighlightRules = arden_highlight_rules.ArdenHighlightRules;
var Tokenizer=tokenizer.Tokenizer;

var Mode = function() {
    this.$tokenizer = new Tokenizer(new ArdenHighlightRules().getRules());
};
oop.inherits(Mode, JavaScriptMode);

(function() {
    
    this.createWorker = function(session) {
        return null;
    };

}).call(Mode.prototype);

exports.Mode = Mode;
});