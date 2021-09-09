package com.bxw.util;

public class PageUtil {
    private int page;
    private int rows;
    private int index;
    private int countRows;
    private int countPage;
    private int prevPage;
    private int nextPage;
    private Object list;

    public PageUtil(String page,int rows,int countRows){
        this.rows = rows;
        this.countRows = countRows;
        init_page(page);
        init_index();
        init_countPage();
        init_prevPage();
        init_nextPage();
    }

    //页码的初始化
    private void init_page(String page){
        if(page == null || "".equals(page)){
            this.page = 1;
        }else{
            this.page = Integer.parseInt(page);
        }
    }

    //初始化偏移量
    private void init_index(){
        this.index = (this.page - 1) * this.rows;
    }

    //初始化总页数
    private void init_countPage(){
        int mod = this.countRows % this.rows;
        if(mod == 0){
            this.countPage = this.countRows / this.rows;
        }else{
            this.countPage = this.countRows / this.rows + 1;
        }
    }

    //初始化当前页的上一页页码
    private void init_prevPage(){
        if(this.page == 1){
            this.prevPage = 1;
        }else{
            this.prevPage = this.page - 1;
        }
    }

    //初始化当前页的下一页页码
    private void init_nextPage(){
        if(this.page == this.countPage){
            this.nextPage = this.countPage;
        }else{
            this.nextPage = this.page + 1;
        }
    }

    public int getPage() {
        return page;
    }

    public int getRows() {
        return rows;
    }

    public int getIndex() {
        return index;
    }

    public int getCountRows() {
        return countRows;
    }

    public int getCountPage() {
        return countPage;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }
}
