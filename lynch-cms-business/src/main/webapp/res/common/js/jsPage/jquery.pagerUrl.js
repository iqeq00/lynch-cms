$(document).ready(function() {
	$("#pager").pager( {
		pagenumber : $("#currentPage").val(),
		pagecount : $("#totalPage").val(),
		buttonClickCallback : PageClick
	});
	$("#result").html("当前第" + $("#currentPage").val() + "页");
});

PageClick = function(pageclickednumber) {

	var url = $("#url").val();
	var hasParam = $("#hasParam").val();
	if(hasParam == null || hasParam == ""){
		window.location.href = url + "?page=" + pageclickednumber;
	}else {
		if(hasParam === "true"){
			window.location.href = url + "&page=" + pageclickednumber;
		}
		if(hasParam === "false"){
			window.location.href = url + "?page=" + pageclickednumber;
		}
	}

}