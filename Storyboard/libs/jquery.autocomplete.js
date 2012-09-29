(function( $ ) {
	$.widget( "ui.combobox", {
		_create: function() {
			var self = this,
				select = this.element.hide(),
				selected = select.children( ":selected" ),
				value = selected.val() ? selected.text() : "";

			var input = this.input = $( "<input>" )
				.val( value )
				.autocomplete({
					delay: 0,
					minLength: 0,
					source: function( request, response ) {
						var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
						response( select.children( "option" ).map(function() {
							var text = $( this ).text();
							if ( this.value && ( !request.term || matcher.test(text) ) )
								return {
									label: text.replace(
										new RegExp(
											"(?![^&;]+;)(?!<[^<>]*)(" +
												$.ui.autocomplete.escapeRegex(request.term) +
												")(?![^<>]*>)(?![^&;]+;)", "gi"
										), "<strong>$1</strong>" ),
									value: text,
									option: this
								};
						}) );
					},
					select: function( event, ui ) {
						ui.item.option.selected = true;
						self._trigger( "selected", event, {
							item: ui.item.option
						});
					},
					change: function( event, ui ) {
						if ( !ui.item ) {
							var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( $(this).val() ) + "$", "i" ),
								valid = false;
							select.children( "option" ).each(function() {
								if ( $( this ).text().match( matcher ) ) {
									this.selected = valid = true;
									return false;
								}
							});
							if ( !valid ) {
								// remove invalid value, as it didn't match anything
								select.val( "" );
								if(!self.options.restrict){
									self._trigger( "changed", event, {
										text: $(this).val()
									});
								}else{
									$( this ).val( "" );
									input.data( "autocomplete" ).term = "";
								}
								return false;
							}

						}
					}
				});

			input.data( "autocomplete" )._renderItem = function( ul, item ) {
				return $( "<li></li>" )
					.data( "item.autocomplete", item )
					.append( "<a>" + item.label + "</a>" )
					.appendTo( ul );
			};
			var btn = $("<span class='add-on'><i class='icon-arrow-down'></i></span>")
				.css('cursor','pointer')
				.click(function() {
					// close if already visible
					if ( input.autocomplete( "widget" ).is( ":visible" ) ) {
						input.autocomplete( "close" );
						return;
					}

					// work around a bug (likely same cause as #5265)
					$( this ).blur();

					// pass empty string as value to search for, displaying all results
					input.autocomplete( "search", "" );
					input.focus();
				});

			var div = $("<div></div>")
				.addClass('input-append')
				.insertAfter(select)
				.append(this.input)
				.append(btn);

			// cleanup
			$(input).removeClass('ui-autocomplete-input');
			// set width
			$(input).css('width',$(select).css('width'));

			this.update = function(text){
				$(input).val(text);
			};
		},
		destroy: function() {
			this.input.remove();
			this.button.remove();
			this.element.show();
			$.Widget.prototype.destroy.call( this );
		}
	});
})( jQuery );