define("ace/mode/drools",["require","exports","module","ace/lib/oop","ace/mode/javascript","ace/tokenizer","ace/mode/drools-highlight-rules"],
		function(require, exports, module,oop,jscript,tokenizer,drools_highlight_rules) {
"use strict";

var JavaScriptMode = jscript.Mode;
var DroolsHighlightRules = drools_highlight_rules.DroolsHighlightRules;
var Tokenizer=tokenizer.Tokenizer;

var Mode = function() {
    JavaScriptMode.call(this);
    
    this.$tokenizer = new Tokenizer(new DroolsHighlightRules().getRules());
};
oop.inherits(Mode, JavaScriptMode);

(function() {
    
    this.createWorker = function(session) {
        return null;
    };

}).call(Mode.prototype);

exports.Mode = Mode;
});