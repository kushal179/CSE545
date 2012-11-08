function bodyload() {
	$("#itemSelected").hide();
}

function onDashboardItemselected(id) {
	document.getElementById("itemname").innerText = id
			.getElementsByTagName('td')[1].innerText;
	document.getElementById("fileName").innerText = id
			.getElementsByTagName('td')[1].innerText;
	document.getElementById("itemId").value = id.getElementsByTagName('td')[5].innerText;

	var table = document.getElementById("fileslist");
	for ( var i = 0, row; row = table.rows[i]; i++) {
		row.style.backgroundColor = '#ffffff';
	}
	id.style.backgroundColor = '#E2E6A8';

	$("#selectItem").hide();
	$("#itemSelected").show();
	var updateAllowed = id.getElementsByTagName('td')[6].innerText == "true";
	
	/*var lockAllowed = id.getElementsByTagName('td')[7].innerText == "true";
	if (lockAllowed) {
		$("#lock-button").show();
		$("#update-button").hide();
	} else{
		$("#lock-button").show();
		$("#update-button").show();
	}*/
	
	var isLocked = id.getElementsByTagName('td')[8].innerText == "true";
	/*if (isLocked) {
		$("#lock-button").hide();
	} else{
		$("#update-button").show();
	}*/

	
		

	$("#version-button").attr("href",
			"DocumentVersioning?fileId=" + $("#selectedfileid").text());
}

function onheaderBarClicked() {
	var table = document.getElementById("fileslist");
	for ( var i = 0, row; row = table.rows[i]; i++) {
		row.style.backgroundColor = '#ffffff';
	}
	$("#itemSelected").hide();
	$("#upload-bar").hide();
	$("#selectItem").show();
}

$("#upload-button").click(function() {
	$("#upload-bar").toggle("slow");
});

$("#version-button").click(function() {

});

$("#upload-submit").submit(function() {
	var fileName = $("#file-upload").val();
	return ValidateFile(fileName);
});

$("#enable-encryption").change(function() {
	if ($('#enable-encryption').is(':checked')) {
		$('#password-field').removeAttr('disabled');
	} else {
		$('#password-field').attr('disabled', true);
	}
});

/*
 * function getGETParam(paramName){ var prmstr =
 * window.location.search.substr(1); var prmarr = prmstr.split ("&"); var params =
 * {};
 * 
 * for ( var i = 0; i < prmarr.length; i++) { var tmparr = prmarr[i].split("=");
 * if(tmparr[0]===paramName) return tmparr[1]; } return null; }
 */

function ValidateFile(sFileName) {

	var _validFileExtensions = [ ".jpg", ".jpeg", ".bmp", ".gif", ".png",
			".doc", ".xls", ".docx", ".ppt", ".pptx", ".pdf", ".txt", ".TXT" ];

	if (sFileName != "") {
		var blnValid = false;
		for ( var j = 0; j < _validFileExtensions.length; j++) {
			var sCurExtension = _validFileExtensions[j];
			if (sFileName.substr(sFileName.length - sCurExtension.length,
					sCurExtension.length).toLowerCase() == sCurExtension
					.toLowerCase()) {
				blnValid = true;
				break;
			}
		}

		if (!blnValid) {
			alert("Sorry, " + sFileName
					+ " is invalid, allowed extensions are: "
					+ _validFileExtensions.join(", "));
			return false;
		}

		return true;
	}

	return false;
}

function selectFileRow(row) {

}
