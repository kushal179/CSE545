var selectedUser;
function selectUsersRow(id) {
	selectedUser = id;
	var actionbar = document.getElementById("actionbar");
	actionbar.innerHTML = '<table><tr><td width = "55%" style="vertical-align: top;">'
			+ id.getElementsByTagName('td')[1].innerText
			+ '</td><td width = "15%"><form action="admin/approve" method = "post"><button class="btn-link" name="userid" type="submit" value='
			+ id.getElementsByTagName('td')[2].innerText
			+ '><i class="icon-search icon-ok"></i>Approve</button></form></td><td width = "15%"><form action="admin/reject" method = "post"><button class="btn-link" name="userid" type="submit" value='
			+ id.getElementsByTagName('td')[2].innerText
			+ '><i class="icon-search icon-remove"></i>Reject</button></form></td><td width = "15%" style="vertical-align: top;"><a href="#modifyModal" class="btn-link" data-toggle="modal" onClick = modifySelected()><i class="icon-search icon-edit"></i>Modify</a></td></tr></table>';

	var table = document.getElementById("contentsTable");
	for ( var i = 0, row; row = table.rows[i]; i++) {
		row.style.backgroundColor = '#ffffff';
	}
	id.style.backgroundColor = '#E2E6A8';
}

function modifySelected() {
	var userName = selectedUser.getElementsByTagName('td')[1].innerText;
	var userId = selectedUser.getElementsByTagName('td')[2].innerText;
	document.getElementById("userId").value = userId;
	var deptsStr = selectedUser.getElementsByTagName('td')[5].innerText;
	var roleId = selectedUser.getElementsByTagName('td')[6].innerText;
	var origdeptList = selectedUser.getElementsByTagName('td')[7].innerText;

	document.getElementById("userName").innerText = userName;
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
