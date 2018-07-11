package com.ydm.util;

import java.io.Serializable;
import java.util.List;
@SuppressWarnings("rawtypes")
public class Page implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public final static int DEFAULT_PAGE_SIZE=10; //默认一页10条
	
    private int total;		//总条数
    private int pageIndex;	//第几页
    private int pageSize;	//每页条数
    private int pageCount;	//多少页
	private List items;   	//分页集合
    
	public Page(){}
    public Page(int pageIndex, int pageSize) {
        if(pageIndex<1)
            pageIndex = 1;
        if(pageSize<1)
            pageSize = 1;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }
    /**
     * api接口分页处理
     * @param items
     * @param totalCount
     * @param pageSize
     * @param pageIndex
     */
    public Page(List items,int totalCount,int pageSize,int pageIndex){
        if(pageIndex<1)
            pageIndex = 1;
        if(pageSize<1)
            pageSize = 1;
        this.total = totalCount;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.items=items;
        if(pageSize==0){
        	pageSize=DEFAULT_PAGE_SIZE;
        }
        pageCount = total / pageSize + (total%pageSize==0 ? 0 : 1);
        if(total==0) {
            if(pageIndex!=1)
                throw new IndexOutOfBoundsException("Page index out of range.");
        }
        else {
            if(pageIndex>pageCount)
                throw new IndexOutOfBoundsException("Page index out of range.");
        }
    }
    
    public void setTotal(int total) {
        this.total = total;
        if(pageSize==0){
        	pageSize=DEFAULT_PAGE_SIZE;
        }
        pageCount = total / pageSize + (total%pageSize==0 ? 0 : 1);
        if(total==0) {
            if(pageIndex!=1)
                throw new IndexOutOfBoundsException("Page index out of range.");
        }
        else {
            if(pageIndex>pageCount)
                throw new IndexOutOfBoundsException("Page index out of range.");
        }
    }
    
    public int getPageIndex() {
		return pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}
    
	public int getTotal() {
		return total;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	
}
