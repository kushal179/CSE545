var selectedUser;

// *********************
// admin-user specific
function loadbody(roleid) {
	var obj = document.getElementById(roleid);
	obj.setAttribute("class", "active");

	$("#itemSelected").hide();
}

function selectApprovedUsersRow(id) {
	selectedApprovedUser = id;
	document.getElementById("itemname").innerHTML = id
			.getElementsByTagName('td')[1].innerHTML;
	document.getElementById("removebtn").value = id.getElementsByTagName('td')[2].innerHTML;

	var table = document.getElementById("contentsTable");
	for ( var i = 0, row; row = table.rows[i]; i++) {
		row.style.backgroundColor = '#ffffff';
	}
	id.style.backgroundColor = '#E2E6A8';

	$("#selectItem").hide();
	$("#itemSelected").show();
}

// *************************
function onheaderBarClicked() {
	var table = document.getElementById("contentsTable");
	for ( var i = 0, row; row = table.rows[i]; i++) {
		row.style.backgroundColor = '#ffffff';
	}
	$("#itemSelected").hide();
	$("#selectItem").show();
}

function onDashboardItemselected(id) {
	$("#selectItem").hide();
	$("#itemSelected").show();
}

// ****************************
// admin specific

function adminBodyLoad() {
	$("#itemSelected").hide();
}

function selectPendingUsersRow(id) {
	selectedUser = id;

	selectedApprovedUser = id;
	document.getElementById("itemname").innerHTML = id
			.getElementsByTagName('td')[1].innerHTML;
	document.getElementById("approvebtn").value = id.getElementsByTagName('td')[2].innerHTML;
	document.getElementById("rejectbtn").value = id.getElementsByTagName('td')[2].innerHTML;

	var table = document.getElementById("contentsTable");
	for ( var i = 0, row; row = table.rows[i]; i++) {
		row.style.backgroundColor = '#ffffff';
	}
	id.style.backgroundColor = '#E2E6A8';

	$("#selectItem").hide();
	$("#itemSelected").show();
}

function modifySelected() {
	var userName = selectedUser.getElementsByTagName('td')[1].innerHTML;
	var userId = selectedUser.getElementsByTagName('td')[2].innerHTML;
	document.getElementById("userId").value = userId;
	var deptsStr = selectedUser.getElementsByTagName('td')[5].innerHTML;
	var roleId = selectedUser.getElementsByTagName('td')[6].innerHTML;
	var origdeptList = selectedUser.getElementsByTagName('td')[7].innerHTML;

	document.getElementById("userName").innerHTML = userName;
	var roleSelectTag = document.getElementById("role");
	roleSelectTag.options[roleId - 1].selected = "1";

	// Clear the check boxes
	var origdepts = origdeptList.split(",");
	var origdeptArray = new Array();
	for ( var i = 0; i < (origdepts.length - 1); i++) {
		origdeptArray[i] = trimBoth(origdepts[i]);
	}
	for (i = 0; i < origdeptArray.length; i++) {
		var deptOptionTag = document.getElementById("dept_" + origdeptArray[i]);
		deptOptionTag.checked = false;
	}

	// Set the check box for this user depts
	var depts = deptsStr.split(",");
	var deptArray = new Array();
	for ( var i = 0; i < (depts.length - 1); i++) {
		deptArray[i] = trimBoth(depts[i]);
	}
	for (i = 0; i < deptArray.length; i++) {
		var deptOptionTag = document.getElementById("dept_" + deptArray[i]);
		deptOptionTag.checked = true;
	}
}

function trimBoth(str) {
	return trimRight(trimLeft(str));
}

function trimLeft(str) {
	var ListOfWhiteSpaceChars = " \f\n\r\t";

	var k = 0;
	while (k < str.length) {
		if (ListOfWhiteSpaceChars.indexOf(str.charAt(k)) == -1) {
			return str.substring(k, str.length);
		} else {
			k++;
		}
	}
}

function trimRight(str) {
	var ListOfWhiteSpaceChars = " \f\n\r\t";

	var k = str.length - 1;
	while (k >= 0) {
		if (ListOfWhiteSpaceChars.indexOf(str.charAt(k)) == -1) {
			return str.substring(0, k + 1);
		} else {
			k--;
		}
	}
}

function selectLogFilesRow(id) {
	var actionbar = document.getElementById("actionbar");

	actionbar.innerHTML = '<table><tr><td width = "55%">'
			+ id.getElementsByTagName('td')[1].innerText
			+ '</td><td width = "15%"><form action="admin/download" method = "post"><button class="btn-link" name="fileName" type="submit" value='
			+ id.getElementsByTagName('td')[1].innerText
			+ '>Download</button></form></td><td width = "15%"><form action="admin/upload" method = "get"><button class="btn-link" name="fileName" type="submit"'
			+ '>Upload</button></form></td></tr></table>';
	var table = document.getElementById("contentsTable");
	for ( var i = 0, row; row = table.rows[i]; i++) {
		row.style.backgroundColor = '#ffffff';
	}
	id.style.backgroundColor = '#E2E6A8';
}

function modifyCheckboxSelected() {
	var count = 0;
	for ( var i = 1; i <= 7; i++) {
		var deptid = "#dept_" + i;
		if ($(deptid)[0].checked == true) {
			count++;
		}
	}
	if (count == 0 && (($("#role").val() == 2) || ($("#role").val() == 1))) {
		return true;
		
	} else if (count == 0) {
		$("#multipleSelectionError").hide();
		$("#guestDeptSelError").hide();
		$("#atleastOneSelection").show();
		return false;

	}else if((($("#role").val() == 2) || ($("#role").val() == 1)) && count>0){
		$("#multipleSelectionError").hide();
		$("#atleastOneSelection").hide();
		$("#guestDeptSelError").show();
		return false;
	}
	else if (count > 1 && ($("#role").val() < 5)) {
		$("#atleastOneSelection").hide();
		$("#guestDeptSelError").hide();
		$("#multipleSelectionError").show();
		return false;
	}
	return true;
}

// ****************************
