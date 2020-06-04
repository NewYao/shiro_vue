package cn.jnx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jnx.mapper.RoleMapper;
import cn.jnx.model.ReturnJson;
import cn.jnx.service.AuthorityService;
@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public ReturnJson roles() {
		return new ReturnJson().ok().data(roleMapper.selectAllRoles());
	}
	
	
	
	
}
