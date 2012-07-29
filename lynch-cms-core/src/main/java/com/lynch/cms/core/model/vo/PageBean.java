package com.lynch.cms.core.model.vo;

import java.util.List;

/**
 * 分页vo 服务器端分页
 * 
 * @author Lynch
 * @version 1.0
 */
public class PageBean<T> {
	
	private static int LENGTH = 10;	// 数字显示(长度控制)

	private List<T> list;     		// 在所在页面要显示的内容
	private String url;				// 请求的url

	private int allRow; 			// 内容的总条数
	private int pageSize; 			// 一个页面要显示的内容条数
	private int totalPage; 			// 总页数
	private int currentPage; 		// 当前页
	private int first; 				// 当前页数据的第一条数据，在数据库所在的位置
	private int startPage;			// 数字显示(开始)
	private int endPage;			// 数字显示(结束)

	private boolean isFirstPage; 	// 是否当前页为第一页
	private boolean hasBackPage; 	// 是否还有上一页
	private boolean hasNextPage; 	// 是否还有下一页
	private boolean isLastPage; 	// 是否当前页为最后一页
	
	/**
	 * 操作方法 
	 */
	public boolean isFirstPage(){
		return currentPage==1 ;
	}
	public boolean hasBackPage(){
		return currentPage!=1 ;
	}
	public boolean hasNextPage(){
		return currentPage!=totalPage ;
	}
	public boolean isLastPage(){
		return totalPage==currentPage ;
	}
	
	/**必要时的初始化*/
	public void init(){
		this.isFirstPage = isFirstPage() ;
		this.isLastPage = isLastPage() ;
		this.hasBackPage = hasBackPage() ;
		this.hasNextPage = hasNextPage() ;
	}
	
	/**计算总页数*/
	public int countTotalPage(int pageSize,int allRow){
		totalPage = allRow%pageSize==0?allRow/pageSize:allRow/pageSize+1 ;
		return totalPage ;
	} 
	/**计算first记录*/
	public int countFirst(int pageSize,int page){
		first = pageSize*(page-1) ;
		return first ;	
	}
	/**计算当前页数*/
	public int countCurrentPage(int page){
		int curPage = page<=0?1:page>=totalPage?totalPage:page ;
		return curPage ;
	}
	/**设置开始页(页面上数字的开始)*/
	public void setStartPage(){
		this.startPage = currentPage-LENGTH<=0?1:currentPage-LENGTH;
	}
	/**设置结束页(页面上数字的结束)*/
	public void setEndPage(){
		this.endPage = currentPage+LENGTH>=this.totalPage?this.totalPage:currentPage+LENGTH;
	}
	
    /**
     * set、get方法 
     */
	public String getUrl() {
		return url;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getAllRow() {
		return allRow;
	}
	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	

}
