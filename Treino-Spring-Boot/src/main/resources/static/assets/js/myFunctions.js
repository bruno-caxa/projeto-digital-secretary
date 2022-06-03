function formatDateBD(date) {
	var regex = /Date\((\d+)([-+]\d{4})?\)/;
	var match = regex.exec('/Date(' + date + ')/').slice(1, 3);
	var dateFormated = new Date(parseInt(match[0]));

	var year = dateFormated.getFullYear();
	var month = dateFormated.getMonth();
	var day = dateFormated.getDate();

	if (day < 10) {
		day = '0' + day;
	}
	if (month < 10) {
		month = '0' + (month + 1);
		if (month == '010') {
			month = '10';
		}

	}
	return day + '/' + month + '/' + year;
}