$(document).ready(function() {
    $("#Pagination").pagination( $("#totalPage").val(), {
		items_per_page: $("#pageSize").val(),
		num_display_entries: 5,
		current_page : ($("#currentPage").val() - 1),
	    num_edge_entries: 2,
		prev_text: "上一页",
		next_text: "下一页",
	    callback: pageselectCallback
	});
});

function pageselectCallback(page_index, jq){
	var url = $("#url").val();
	var hasParam = $("#hasParam").val();
	if(hasParam == null || hasParam == ""){
		window.location.href = url + "?page=" + page_index;
	}else {
		if(hasParam === "true"){
			window.location.href = url + "&page=" + page_index;
		}
		if(hasParam === "false"){
			window.location.href = url + "?page=" + page_index;
		}
	}
}