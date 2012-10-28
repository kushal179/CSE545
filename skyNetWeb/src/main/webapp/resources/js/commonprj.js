function selectUsersRow(id){
	var actionbar = document.getElementById("actionbar");
	actionbar.innerHTML =  '<table><tr><td width = "55%">'+id.getElementsByTagName('td')[1].innerText+'</td><td width = "15%"><form action="admin/approve" method = "post"><button class="btn-link" name="userid" type="submit" value=' + id.getElementsByTagName('td')[2].innerText+ '><i class="icon-search icon-ok"></i>Aprove</button></form></td><td width = "15%"><form action="admin/reject" method = "post"><button class="btn-link" name="userid" type="submit" value=' + id.getElementsByTagName('td')[2].innerText+ '><i class="icon-search icon-remove"></i>Reject</button></form></td><td width = "15%"><form action="admin/modify" method = "post"><button class="btn-link" name="userid" type="submit" value=' + id.getElementsByTagName('td')[2].innerText+ '><i class="icon-search icon-edit"></i>Modify</button></form></td></tr></table>';
	var table = document.getElementById("contentsTable");
	for (var i = 0, row; row = table.rows[i]; i++) {
		row.style.backgroundColor = '#ffffff';
	}
	id.style.backgroundColor = '#E2E6A8';
}