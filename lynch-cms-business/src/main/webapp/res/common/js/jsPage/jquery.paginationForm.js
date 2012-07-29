$(document).ready(function() {
    $("#Pagination").pagination( $("#totalPage").val(), {
		items_per_page: $("#pageSize").val(),
		num_display_entries: 5,
		current_page : $("#currentPage").val(),
	    num_edge_entries: 2,
		prev_text: "上一页",
		next_text: "下一页",
	    callback: pageselectCallback
	});
});

function pageselectCallback(page_index, jq){
	$("#page").val(page_index);
    var formId = $("#formId").val();
	$("#"+formId).submit();
    return false; 
}