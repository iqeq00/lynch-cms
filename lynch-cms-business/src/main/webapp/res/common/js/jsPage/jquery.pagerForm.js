$(document).ready(function() {
    $("#pager").pager({ 
        pagenumber: $("#currentPage").val(), 
        pagecount: $("#totalPage").val(), 
        buttonClickCallback: PageClick 
     });
    $("#result").html("当前第" + $("#currentPage").val() + "页");
});

PageClick = function(pageclickednumber) {
	
    $("#page").val(pageclickednumber);
    var formId = $("#formId").val();
	$("#"+formId).submit();
    return false; 
}