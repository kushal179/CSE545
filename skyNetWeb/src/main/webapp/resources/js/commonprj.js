function selectUsersRow(id){
	var actionbar = document.getElementById("actionbar");
	actionbar.innerHTML =  '<table><tr><td width = "55%">'+id.getElementsByTagName('td')[1].innerText+'</td><td width = "15%"><button class="btn-link" type="button"><i class="icon-search icon-ok"></i>Aprove</button></td><td width = "15%"><button class="btn-link" type="button"><i class="icon-search icon-remove"></i>Reject</button></td><td width = "15%"><button class="btn-link" type="button"><i class="icon-search icon-edit"></i>Modify</button></td></tr>';
	var table = document.getElementById("contentsTable");
	for (var i = 0, row; row = table.rows[i]; i++) {
		row.style.backgroundColor = '#ffffff';
	}
	id.style.backgroundColor = '#E2E6A8';
}