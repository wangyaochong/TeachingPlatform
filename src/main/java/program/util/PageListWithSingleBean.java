package program.util;

import javafx.util.Pair;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PageListWithSingleBean<T> {
    PageBean<T> pageBean=new PageBean();
    Session session;//用来获取分页信息的session
    T condition;

    private void init(Session session, T condition,Integer pageCurrentIndex, Integer pageRowSize, String orderBy, Boolean orderAsc){
        this.condition=condition;
        this.session = session;
        setPageBean(pageCurrentIndex,  pageRowSize,  orderBy,  orderAsc);
    }
    //传入的orderBy不是null，就默认降序排序，传入的orderAsc只要不是null，就升序排序
    public PageListWithSingleBean(Session session, T condition, Integer pageCurrentIndex, Integer pageRowSize, String orderBy, Boolean orderAsc) {
        init(session,condition,pageCurrentIndex,pageRowSize,orderBy,orderAsc);
    }
    //
    public PageListWithSingleBean(Session session, T condition, Integer pageCurrentIndex, Integer pageRowSize, String orderBy) {
        init(session,condition,pageCurrentIndex,pageRowSize,orderBy,null);
    }
    //不传入orderBy就不排序
    public PageListWithSingleBean(Session session,T condition, Integer pageCurrentIndex, Integer pageRowSize){
        init(session,condition,pageCurrentIndex,pageRowSize,null,null);
    }

    private List<Pair<String,Object>> getKeyValueList(){
        if(condition==null)return new ArrayList<Pair<String, Object>>();
        Field[] declaredFields = condition.getClass().getDeclaredFields();
        List<Pair<String,Object>> pairList=new ArrayList<Pair<String, Object>>();
        for(int i=0;i<declaredFields.length;i++){
            declaredFields[i].setAccessible(true);
            try {
                if(declaredFields[i].get(condition)!=null){//当属性不为空，则为过滤条件
                    pairList.add(new Pair<String, Object>(declaredFields[i].getName(),declaredFields[i].get(condition)));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return pairList;
    }

    public PageBean<T> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<T> pageBean) {
        this.pageBean = pageBean;
    }

    public void setPageBean(Integer pageCurrentIndex, Integer pageRowSize, String orderBy, Boolean orderAsc){
        this.pageBean.setPageCurrentIndex(pageCurrentIndex);
        this.pageBean.setPageRowSize(pageRowSize);
        this.pageBean.setOrderBy(orderBy);
        this.pageBean.setOrderAsc(orderAsc);

        Criteria criteria = session.createCriteria(condition.getClass());
        List<Pair<String, Object>> conditionKeyValueList = getKeyValueList();
        for(int i=0;i<conditionKeyValueList.size();i++){//加入查询条件
            criteria.add(Restrictions.eq(conditionKeyValueList.get(i).getKey(),conditionKeyValueList.get(i).getValue()));
        }
        //查出总个数，设置总页面数
        this.pageBean.setPageTotalCount (criteria.list().size()/this.pageBean.getPageRowSize()+1);

        //进行排序设置
        if(this.pageBean.getOrderBy()!=null){//不是空就进行排序
            if(this.pageBean.getOrderAsc()==null){//不是空就升序排序
                criteria.addOrder(Order.desc(this.pageBean.getOrderBy()));
            }else{
                criteria.addOrder(Order.asc(this.pageBean.getOrderBy()));
            }
        }
        //进行分页设置
        criteria.setMaxResults(this.pageBean.getPageRowSize());
        criteria.setFirstResult(this.pageBean.getPageRowSize()*(this.pageBean.getPageCurrentIndex()-1));//如果页码是1，那么从第0项开始获取，获取一页

        this.pageBean.setPageList(criteria.list());
    }
}
