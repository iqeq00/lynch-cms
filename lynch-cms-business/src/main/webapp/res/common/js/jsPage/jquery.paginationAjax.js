$(document).ready(function() {
	
	var totalPage = $("#totalPage").val();
	var pageSize = $("#pageSize").val();
	var currentPage = $("#currentPage").val();
    $("#Pagination").pagination( totalPage, {
		items_per_page: pageSize,
		num_display_entries: 5,
		current_page : currentPage - 1,
	    num_edge_entries: 2,
		prev_text: "上一页",
		next_text: "下一页",
	    callback: pageselectCallback
	});
});

function pageselectCallback(page_index, jq){
	
	$("#currentPage").val(page_index+1);
	paginationAjax(page_index+1);
	return false;
}



