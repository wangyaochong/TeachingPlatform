package program.util.bean;

import java.util.List;

public class PageBean<T>{
    private Integer pageCurrentIndex;//当前页码
    private Integer pageRowSize;//每页显示条数
    private Long pageTotalCount;//在当前页面大小的前提下，总共有多少页
    private Long totalRowCount;//总共有多少行
    private String orderBy;//按照哪个字段排序
    private Boolean orderAsc;//是否升序排序
    private List<T> pageList;//页面包含的列表

    public PageBean() {
    }

    public PageBean(Integer pageCurrentIndex, Integer pageRowSize, Long pageTotalCount, String orderBy, Boolean orderAsc, List<T> pageList) {
        this.pageCurrentIndex = pageCurrentIndex;
        this.pageRowSize = pageRowSize;
        this.pageTotalCount = pageTotalCount;
        this.orderBy = orderBy;
        this.orderAsc = orderAsc;
        this.pageList = pageList;
        this.pageTotalCount=pageTotalCount;
    }


    @Override
    public String toString() {
        return "PageBean{" +
                "pageCurrentIndex=" + pageCurrentIndex +
                ", pageRowSize=" + pageRowSize +
                ", pageTotalCount=" + pageTotalCount +
                ", totalRowCount=" + totalRowCount +
                ", orderBy='" + orderBy + '\'' +
                ", orderAsc=" + orderAsc +
                ", pageList=" + pageList +
                '}';
    }
    public Long getTotalRowCount() {
        return totalRowCount;
    }

    public void setTotalRowCount(Long totalRowCount) {
        this.totalRowCount = totalRowCount;
    }

    public Integer getPageCurrentIndex() {
        return pageCurrentIndex;
    }

    public void setPageCurrentIndex(Integer pageCurrentIndex) {
        this.pageCurrentIndex = pageCurrentIndex;
    }

    public Integer getPageRowSize() {
        return pageRowSize;
    }

    public void setPageRowSize(Integer pageRowSize) {
        this.pageRowSize = pageRowSize;
    }

    public Long getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Long pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Boolean getOrderAsc() {
        return orderAsc;
    }

    public void setOrderAsc(Boolean orderAsc) {
        this.orderAsc = orderAsc;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }
}
