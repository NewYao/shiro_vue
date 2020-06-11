package cn.jnx.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.jnx.common.Exception.CustomException;
import cn.jnx.model.ReturnJson;
import cn.jnx.model.User;
import cn.jnx.service.UserService;
import cn.jnx.util.rsa.RSAUtil;
import cn.jnx.util.tools.GlobalTools;
import cn.jnx.util.verification_code.Validate;
import cn.jnx.util.verification_code.ValidateCodeUtil;

@RestController
@RequestMapping("/user/base")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ReturnJson getUserList(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer size, User user, String[] dateScope) {
		JSONObject userList = userService.getUserList(page, size, user, dateScope);
		return new ReturnJson().ok().data(userList);
	}

	@PostMapping("/delete")
	@RequiresRoles(value= {"admin","user"},logical=Logical.OR)
	@RequiresPermissions(value = { "user:delete" })
	public ReturnJson deleteUserById(Integer id) {
		if (userService.deleteUserById(id) == 1) {
			return new ReturnJson().ok().message("删除成功！");
		}
		return new ReturnJson().fail().message("删除失败！");
	}

	@PostMapping("/update")
	@RequiresRoles(value= {"admin","user"},logical=Logical.OR)
	public ReturnJson updateUserInfo(User user) {
		if (userService.updateUser(user) == 1) {
			return new ReturnJson().ok().message("更改成功！");
		}
		return new ReturnJson().fail().message("更改失败！");
	}

	@PostMapping("/add")
	@RequiresRoles(value= {"admin","user"},logical=Logical.OR)
	@RequiresPermissions(value = { "user:add" })
	public ReturnJson addUser(User user, @RequestParam(defaultValue = "1") Integer r_id) throws Exception {
		// 根据公钥，解密rsa的秘钥
		String inputPwd = RSAUtil.getTrueStr(user.getPassword());// 解密用户输入的密码成明文
		// 处理密码，生成盐值
		Validate v = ValidateCodeUtil.getRandomCode();
		String salt = v.getValue();
		String encryptionPwd = GlobalTools.md5SimpleHash(inputPwd, ByteSource.Util.bytes(salt).toString());
		user.setSalt(salt);
		user.setPassword(encryptionPwd);
		if (userService.addUser(user, r_id)) {
			return new ReturnJson().ok().message("新增成功！");
		}
		return new ReturnJson().fail().message("新增失败！");
	}

	@PostMapping("/queryByDate")
	public ReturnJson queryByDate(String[] date) {
		return new ReturnJson().ok().data(userService.queryByDate(date));
	}

	@PostMapping("/modifyPwd")
	@RequiresRoles(value= {"admin","user"},logical=Logical.OR)
	public ReturnJson modifyPwd(Integer id, String oldPwd, String newPwd) {
		if(id==6) {
			return new ReturnJson().fail().message("别改我的默认账号!");
		}
		//解密使用rsa加密的密码
		String _oldPwd = "";
		String _newPwd = "";
		try {
			_oldPwd = RSAUtil.getTrueStr(oldPwd);
			_newPwd = RSAUtil.getTrueStr(newPwd);
		} catch (Exception e) {
			throw new CustomException("密文解析失败！");
		}
		return userService.modifyPwd(id, _oldPwd, _newPwd);
	}

}
