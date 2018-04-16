package com.menglin.invest.service.impl;

import java.awt.print.Pageable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.menglin.invest.dao.UserDao;
import com.menglin.invest.entity.User;
import com.menglin.invest.service.IUserService;
import com.menglin.invest.util.CheckData;
import com.menglin.invest.util.PageBean;


@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	
	@Autowired
	private UserDao userDao;
	@PersistenceContext
	EntityManager em;
	

	@Override
	public List<User> findAll() {
//		return userDao.findAll();
		return null;
	}



	@Override
	public void saveUser(User book) {
//		userDao.save(book);
		
	}

	@Override
	public void delete(int id) {
//		userDao.delete(id);
		
	}

	@Override
	public User findOne(int id) {
//		User user=userDao.findOne(id);
		return null; 
	}

	
	/**
	 * 多条件分页查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageBean<User> findPageBycondition(int page,int size, User model) {
		
		//list 数据
		StringBuilder sql=new StringBuilder();
		sql.append("select t.* from t_user t where 1=1 ");
		if(CheckData.isNotEmpty(model.getUserName())){
			sql.append(" and t.user_name like '%"+model.getUserName()+"%'");
		}
		if(CheckData.isNotEmpty(model.getUserPhone())){
			sql.append(" and t.user_phone like '%"+model.getUserPhone()+"%'");
		}
		if(CheckData.isNotEmpty(model.getUserPhone())){
			sql.append(" and t.id_card like '%"+model.getIdCard()+"%'");
		}
		if(CheckData.isNotEmpty(model.getOnState().toString())){
			sql.append(" and t.on_state = '"+model.getOnState()+"'");
		}
		//根据条件拼凑 查询总记录数
		StringBuilder sql1=new StringBuilder();
		sql1.append("select COUNT(*) from ("+sql+")t ;");
		
		// 条件分页查询拼接末尾
		sql.append(" order by t.add_time desc limit ?,?;");
		Query query =em.createNativeQuery(sql.toString(),User.class);
		if(page<=0){
			page=1;
		}
		query.setParameter(1, (page-1)*size);
		query.setParameter(2, size);
		List<User> pageList=query.getResultList();
		
		
		//数据的总记录数
		
		Query query1 =em.createNativeQuery(sql1.toString());
		List<String> countlist=query1.getResultList();
		Object ob = countlist.get(0);
		int count =Integer.parseInt(ob.toString());
		System.out.println(ob.toString());
//		PageBean pageBean=new PageBean<User>(String.valueOf(page),size, count, pageList);
		return null;
	}
	

}
