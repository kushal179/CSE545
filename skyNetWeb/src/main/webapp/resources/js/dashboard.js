function bodyload() {
	$("#itemSelected").hide();
}

function onDashboardItemselected(id) {
	document.getElementById("itemname").innerHTML = id
			.getElementsByTagName('td')[1].innerHTML;
	document.getElementById("fileName").innerHTML = id
			.getElementsByTagName('td')[1].innerHTML;

	var fileId = id.getElementsByTagName('td')[5].innerHTML;
	document.getElementById("itemId").value = fileId;

	var table = document.getElementById("fileslist");
	for ( var i = 0, row; row = table.rows[i]; i++) {
		row.style.backgroundColor = '#ffffff';
	}
	id.style.backgroundColor = '#E2E6A8';

	$("#lock-file-id").val(fileId);
	$("#unlock-file-id").val(fileId);
	$("#delete-file-id").val(fileId);
	$("#version-file-id").val(fileId);
	$("#update-file-id").val(fileId);

	$("#selectItem").hide();
	$("#itemSelected").show();

	var updateAllowed = id.getElementsByTagName('td')[6].innerHTML == "true";
	var lockAllowed = id.getElementsByTagName('td')[7].innerHTML == "true";
	var isLocked = id.getElementsByTagName('td')[8].innerHTML == "true";
	var isDir = id.getElementsByTagName('td')[9].innerHTML == "true";

	$("#update-button").hide();
	$("#version-button").hide();
	$("#lock-button").hide();
	$("#unlock-button").hide();
	$("#delete-button").hide();

	if (!isDir) {
		$("#version-button").show();

		if (updateAllowed) {
			$("#update-button").show();
		}
	}

	if (lockAllowed) {
		if (isLocked) {
			$("#unlock-button").show();
		} else {
			$("#lock-button").show();
			$("#delete-button").hide();
		}

	} else {
		$("#lock-button").hide();
	}

}

function onSharedToItemselected(id) {
	document.getElementById("itemname").innerHTML = id
			.getElementsByTagName('td')[1].innerHTML;
	/*document.getElementById("fileName").innerHTML = id
			.getElementsByTagName('td')[1].innerHTML;*/

	var fileId = id.getElementsByTagName('td')[5].innerHTML;
	document.getElementById("itemId").value = fileId;

	var table = document.getElementById("fileslist");
	for ( var i = 0, row; row = table.rows[i]; i++) {
		row.style.backgroundColor = '#ffffff';
	}
	id.style.backgroundColor = '#E2E6A8';

	$("#lock-file-id").val(fileId);
	$("#unlock-file-id").val(fileId);
	$("#version-file-id").val(fileId);
	$("#update-file-id").val(fileId);

	$("#selectItem").hide();
	$("#itemSelected").show();

	var updateAllowed = id.getElementsByTagName('td')[6].innerHTML == "true";
	var lockAllowed = id.getElementsByTagName('td')[7].innerHTML == "true";
	var isLocked = id.getElementsByTagName('td')[8].innerHTML == "true";
	var isDir = id.getElementsByTagName('td')[9].innerHTML == "true";

	$("#update-button").hide();
	$("#version-button").hide();
	$("#lock-button").hide();
	$("#unlock-button").hide();

	if (!isDir) {
		$("#version-button").show();
		if (updateAllowed) {
			$("#update-button").show();
		}
	}

	if (lockAllowed) {
		if (isLocked) {
			$("#unlock-button").show();
			$("#delete-button").show();
		} else {
			$("#lock-button").show();
		}
	} else {
		$("#lock-button").hide();
	}

}

function selectSharedByFileRow(id) {
	document.getElementById("itemname").innerHTML = id
			.getElementsByTagName('td')[1].innerHTML;

	var fileId = id.getElementsByTagName('td')[5].innerHTML;
	document.getElementById("itemId").value = fileId;

	var table = document.getElementById("fileslist");
	for ( var i = 0, row; row = table.rows[i]; i++) {
		row.style.backgroundColor = '#ffffff';
	}
	id.style.backgroundColor = '#E2E6A8';
	
	$("#unshare-file-id").val(fileId);

	$("#selectItem").hide();
	$("#itemSelected").show();
}

function onheaderBarClicked() {
	var table = document.getElementById("fileslist");
	for ( var i = 0, row; row = table.rows[i]; i++) {
		row.style.backgroundColor = '#ffffff';
	}
	$("#itemSelected").hide();
	// $("#upload-bar").hide();
	$("#selectItem").show();
}

$("#upload-button").click(function() {
	$("#upload-bar").show();
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
