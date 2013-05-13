package com.films.service.inter;

import java.util.List;

import com.films.baservice.IBaseService;
import com.films.domain.Users;

public interface IUserService extends IBaseService {

	//ע����֤����
	public boolean checkEmail(String email);
	
	//��½��֤�û�
	public Users checkUser(Users user);
	
	public int getPageCount(int pageSize);
	
	public List showUser(int pageSize, int pageNow);
}
