package com.films.baservice;

import java.io.Serializable;
import java.util.List;

public interface IBaseService {

	//ͨ��һ��hql,�ʹ���Ĳ��������Ӧ�Ĳ�ѯ����
	public List getResult(String hql,Object [] parameters);
	//����һ������
	public void save(Object obj);
	//ɾ��һ������
	public void delete(Object obj);
	//����һ������
	public void update(Object obj);
	//���� id����һ������
	public Object findById(Class clazz,Serializable id);
	//�ṩһ��ͳһ�Ĳ�ѯ����(����ҳ) hql ��ʽ from ��  where ����=? ..
	public List executeQueryByPage(String hql,String [] parameters,int pageSize,int pageNow);
	
	public Object uniqueQuery(String hql, Object[] parameters);
	
	public int queryPageCount(String hql, Object[] parameters, int pageSize);
	
}
