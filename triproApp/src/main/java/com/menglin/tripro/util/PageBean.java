package com.menglin.tripro.util;

import java.util.List;

public class PageBean<T> {
	 
	 	private int page;    //当前页 传递过来的 
	    private int pageSize=0; //分页数量  0
	    private int count=0;        //总数据数  
	    private int allpage=0;  //总页数  
	    private int currentPage=0;//当前页  0
	    private int nextpage=0; //下一页  0
	    private int prevpage=0; //上一页  0
	    private int lastpage=0; //尾页  0
	    private List<T> pageList;  //每页对应的集合泛型 0
	     
	    
	    
	    public PageBean( int count) {  
	        super();  
	  
	        this.count = count;  
	       
	    }  
	      
	    public PageBean(int currentPage, int pageSize, int count) {  
	        super();  
	        this.page = currentPage;  
	        this.pageSize = pageSize;  
	        this.count = count;  
	        initAllpage();  //初始化总页数  
	        initPageIndex();//初始化当前页  
	        initPrevpage(); //初始化上一页  
	        initNextpage(); //初始化下一页  
	        initEndpage();  //初始化尾页  
	    }  
	      
	    private void initPageIndex() {  
	        if(page!=0){
	        	currentPage = page;  
	        }else{  
	        	currentPage = 1;  
	        }  
	    }  
	  
	    private void initEndpage() {  
	        lastpage = allpage;  
	    }  
	  
	  
	  
	    private void initNextpage() {  
	        //如果当前页是尾页，则下一页也为尾页，其余都为当前页+1  
	        if(currentPage>=allpage){  
	            nextpage = allpage;  
	        }else{  
	            nextpage = currentPage+1;  
	        }  
	    }  
	  
	    private void initPrevpage() {  
	        //如果当前页为1，则上一页也为1，其余都为当前页-1  
	        if(currentPage>1){  
	            prevpage = currentPage-1;  
	        }else{  
	            prevpage = 1;  
	        }  
	          
	    }  
	  
	    private void initAllpage() {  
	        if(count%pageSize==0){  
	            allpage = count/pageSize;  
	        }else{  
	            allpage = count/pageSize+1;  
	        }  
	    }  
	  
	  
	    public int getCurrentPage() {  
	        return currentPage;  
	    }  
	  
	    public void getCurrentPage(int currentPage) {  
	        this.currentPage = currentPage;  
	    }  
	  
	    public int getPageSize() {  
	        return pageSize;  
	    }  
	  
	    public void setPageSize(int pageSize) {  
	        this.pageSize = pageSize;  
	    }  
	  
	  
	    public int getNextpage() {  
	        return nextpage;  
	    }  
	  
	    public void setNextpage(int nextpage) {  
	        this.nextpage = nextpage;  
	    }  
	  
	    public int getPrevpage() {  
	        return prevpage;  
	    }  
	  
	    public void setPrevpage(int prevpage) {  
	        this.prevpage = prevpage;  
	    }  
	      
	    public int getLastpage() {  
	        return lastpage;  
	    }  
	      
	    public void setLastpage(int lastpage) {  
	        this.lastpage = lastpage;  
	    }  
	    
	    public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public int getAllpage() {
			return allpage;
		}

		public void setAllpage(int allpage) {
			this.allpage = allpage;
		}

		public List<T> getPageList() {
	        return pageList;
	    }
	    public void setPageList(List<T> pageList) {
	        this.pageList = pageList;
	    }
}
