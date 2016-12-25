package program.service;

import javafx.util.Pair;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import program.dao.GenericDao;
import program.service.bean.PageBean;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
@Repository
public class PageListService<T> {
    PageBean<T> pageBean = new PageBean();//分页的保存项
    @Resource(name = "GenericDao")
    GenericDao genericDao;//用来获取分页信息的session
    T condition;//查询条件

    public PageListService() {
    }

    //构造函数的公共方法
    //由于这个方法有局部变量，所以要使用synchronized关键字
    public synchronized PageBean<T> getPageBean(T condition, Integer pageCurrentIndex, Integer pageRowSize, String orderBy, Boolean orderAsc) {
        this.condition = condition;
        setPageBean(pageCurrentIndex, pageRowSize, orderBy, orderAsc);
        return this.pageBean;
    }
    //从查询条件获取键值对，用于查询
    private List<Pair<String, Object>> getKeyValueList() {
        if (condition == null) return new ArrayList<Pair<String, Object>>();
        Field[] declaredFields = condition.getClass().getDeclaredFields();
        List<Pair<String, Object>> pairList = new ArrayList<Pair<String, Object>>();
        for (int i = 0; i < declaredFields.length; i++) {
            declaredFields[i].setAccessible(true);
            try {
                if (declaredFields[i].get(condition) != null) {//当属性不为空，则为过滤条件
                    pairList.add(new Pair<String, Object>(declaredFields[i].getName(), declaredFields[i].get(condition)));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return pairList;
    }
    //设置pageBean，也就是从入参构造一页列表
    private void setPageBean(Integer pageCurrentIndex, Integer pageRowSize, String orderBy, Boolean orderAsc) {
        this.pageBean.setPageCurrentIndex(pageCurrentIndex);
        this.pageBean.setPageRowSize(pageRowSize);
        this.pageBean.setOrderBy(orderBy);
        this.pageBean.setOrderAsc(orderAsc);

        //先根据条件获取总条数
        Session session = genericDao.getSession();
        Criteria criteria =session.createCriteria(condition.getClass());
        List<Pair<String, Object>> conditionKeyValueList = getKeyValueList();
        for (int i = 0; i < conditionKeyValueList.size(); i++) {//加入查询条件
            criteria.add(Restrictions.eq(conditionKeyValueList.get(i).getKey(), conditionKeyValueList.get(i).getValue()));
        }
        //查出总个数，设置总页面数，使用投影效率更高，不用将所有数据都取出来
        criteria.setProjection(Projections.rowCount());
//        Object o = criteria.uniqueResult();
        this.pageBean.setTotalRowCount((Long) criteria.uniqueResult());
        this.pageBean.setPageTotalCount((Long) criteria.uniqueResult() / this.pageBean.getPageRowSize() + 1);
//        this.pageBean.setPageTotalCount (new Long(criteria.list().size()/this.pageBean.getPageRowSize()+1));


//根据条件获取结果
        criteria = session.createCriteria(condition.getClass());
        for (int i = 0; i < conditionKeyValueList.size(); i++) {//加入查询条件
            criteria.add(Restrictions.eq(conditionKeyValueList.get(i).getKey(), conditionKeyValueList.get(i).getValue()));
        }
        //进行排序设置
        if (this.pageBean.getOrderBy() != null) {//不是空就进行排序
            if (this.pageBean.getOrderAsc() == null) {//不是空就升序排序
                criteria.addOrder(Order.desc(this.pageBean.getOrderBy()));
            } else {
                criteria.addOrder(Order.asc(this.pageBean.getOrderBy()));
            }
        }
        //进行分页设置
        criteria.setMaxResults(this.pageBean.getPageRowSize());
        criteria.setFirstResult(this.pageBean.getPageRowSize() * (this.pageBean.getPageCurrentIndex() - 1));//如果页码是1，那么从第0项开始获取，获取一页

        this.pageBean.setPageList(criteria.list());
    }
}
