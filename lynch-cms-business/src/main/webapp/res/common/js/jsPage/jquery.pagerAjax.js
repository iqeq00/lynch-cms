$(document).ready(function() {
    $("#pager").pager({ 
        pagenumber: $("#currentPage").val(), 
        pagecount: $("#totalPage").val(), 
        buttonClickCallback: PageClick 
     });
    $("#result").html("当前第" + $("#currentPage").val() + "页");
});

PageClick = function(pageclickednumber) {
	
	pagerAjax(pageclickednumber);
    $("#pager").pager({ 
    	pagenumber: pageclickednumber, 
        pagecount: $("#totalPage").val(), 
        buttonClickCallback: PageClick 
     });
    $("#result").html("当前第" + pageclickednumber + "页");
}


