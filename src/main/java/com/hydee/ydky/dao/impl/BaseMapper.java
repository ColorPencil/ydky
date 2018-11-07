package com.hydee.ydky.dao.impl;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class BaseMapper {
	// 批处理一次最大执行数量
	@Value("${mybatis.batch-commitcount}")
	private Integer MAX_BATCH_NUM;
	
	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;
	
    /**
     * 批量插入数据
     * @param namespace		：对应映射文件命名空间
     * @param method		：调用方法
     * @param list			：待插入数据集合
     * @return
     */
	public <T> Integer batchInsert(String namespace, String method, List<T> list) {
		if(list == null || list.isEmpty()) return 0;
    	int addCount = 0;
    	SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
    	String statement = namespace + "." + method;
		try{
			for (int i = 0; i < list.size(); i++) {
				Object obj = list.get(i);
				addCount += sqlSessionTemplate.insert(statement, obj);
				if(i !=0 && i % MAX_BATCH_NUM == 0) session.commit();
			}
			session.flushStatements();
			session.commit();
			//清理缓存，防止溢出
			session.clearCache();
		} finally {
			session.close();
		}
		return addCount;
    }
	
	/**
	 * 通过mapper批量入库数据
	 * @param batchCount
	 * @param namespace
	 * @param method
	 * @param list
	 * @return
	 */
	public <T> Integer batchInsertEx(String namespace, String method, List<T> list) {
		if(list == null || list.isEmpty()) return 0;
    	int addCount = 0;
    	SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
    	String statement = namespace + "." + method;
		try{
			// 避免集合数据量过大导致Mybatis报参数异常,从而分批处理数据
			int maxNum = list.size()%MAX_BATCH_NUM == 0 ? list.size()/2000 : (list.size()/2000+1);
			for(int i=0;i<maxNum;i++) {
				int dataSize = list.size() > MAX_BATCH_NUM*(i+1) ? MAX_BATCH_NUM*(i+1) : list.size();
				addCount += sqlSessionTemplate.insert(statement, list.subList(MAX_BATCH_NUM*i, dataSize));
				// 提交事务
				session.flushStatements();
				session.commit();
			}
			//清理缓存，防止溢出
			session.clearCache();
		} finally {
			session.close();
		}
		return addCount;
	}

    /**
     * 添加
     * @param str
     * @param obj
     * @return
     * @throws RuntimeException
     */
    public Integer save(String str,Object obj) throws RuntimeException{
        return sqlSessionTemplate.insert(str,obj);
    }

    /**
     * 更新
     * @param str
     * @param obj
     * @return
     * @throws RuntimeException
     */
    public Integer update(String str,Object obj) throws RuntimeException{
        return sqlSessionTemplate.update(str, obj);
    }

    /**
     * 批量更新
     * @return
     * @throws Exception
     */
    public <T> Integer batchUpdate(String str, List<T> list) throws RuntimeException
    {
    	int count = 0;
        SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
        // 批量执行器
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try
        {
        	for (int i = 0; i < list.size(); i++) {
				Object obj = list.get(i);
            	count += sqlSessionTemplate.update(str, obj);
            	if(i !=0 && i % MAX_BATCH_NUM == 0) session.commit();
            }
        	session.flushStatements();
        	session.commit();
        	session.clearCache();
        } catch(Exception e) {
			session.rollback();
        } finally {
        	session.close();
        }
        return count;
    }


    /**
     * 批量删除
     * @param str
     * @param objs
     * @return
     * @throws RuntimeException
     */
    public <T> Integer batchDelete(String str,List<T> list) throws RuntimeException{
    	int delCount = 0;
    	SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		try{
			for (int i = 0; i < list.size(); i++) {
				Object obj = list.get(i);
				delCount += sqlSessionTemplate.delete(str, obj);
            	if(i !=0 && i % MAX_BATCH_NUM == 0) session.commit();
            }
			session.flushStatements();
			session.commit();
			session.clearCache();
		} catch(Exception e) {
			session.rollback();
		} finally {
			session.close();
		}
        return delCount;
    }

    /**
     * 删除
     * @param str
     * @param obj
     * @return
     * @throws RuntimeException
     */
    public Integer delete(String str,Object obj) throws RuntimeException{
        return sqlSessionTemplate.delete(str,obj);
    }

    /**
     * 查询出一个对象
     * @param str
     * @param obj
     * @return
     * @throws RuntimeException
     */
    public Object findForObject(String str,Object obj) throws RuntimeException{
        return sqlSessionTemplate.selectOne(str,obj);
    }

    /**
     * 查询出一个对象
     * @param str
     * @return
     * @throws RuntimeException
     */
    public Object findForObject(String str) throws RuntimeException{
        return sqlSessionTemplate.selectOne(str);
    }

    /**
     * 查询多个对象
     * @param str
     * @param obj
     * @return
     * @throws RuntimeException
     */
    public Object findForList(String str,Object obj) throws RuntimeException{
        return sqlSessionTemplate.selectList(str,obj);
    }

    /**
     * 查询多个
     * @param str
     * @return
     * @throws RuntimeException
     */
    public Object findForList(String str) throws RuntimeException{
        return sqlSessionTemplate.selectList(str);
    }
}
