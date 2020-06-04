package cn.jnx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jnx.model.ReturnJson;
import cn.jnx.service.AuthorityService;

/**
 * 提供权限和角色相关接口
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/pub/authority")
public class AuthorityController {
	@Autowired
	private AuthorityService authorityService;
	
	@RequestMapping("/roles")
	public ReturnJson roles() {
		return authorityService.roles();
	}
	
	@RequestMapping("/permissions")
	public ReturnJson permissions() {
		return new ReturnJson().ok().data("permission");
	}
}
