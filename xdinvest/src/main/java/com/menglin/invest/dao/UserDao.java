package com.menglin.invest.dao;

import java.util.List;


public interface UserDao  {
	
	//自定义查询方法 ，原生SQl
/*    @Query(value ="select t.* from  t_user  t where t.user_name =?1 And t.user_phone=?2  ORDER BY ?#{#pageable}" ,
    	   countQuery="select count(t.*) from  t_user  t where t.user_name =?1 And t.user_phone=?2 ",
    	   nativeQuery=true)
    Page<User> findByNameAndPhone(@Param("userName") String userName, @Param("userPhone") String userPhone,Pageable pageable);*/
}
