package cn.jnx.common.shiro.realms;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import cn.jnx.model.Permission;
import cn.jnx.model.Role;
import cn.jnx.model.User;
import cn.jnx.model.dto.User_dto;
import cn.jnx.service.UserService;

/**
 * 
 * @ClassName: RealmUsernamePwd
 * @Description 用户名密码登录realm
 * @version
 * @author jh
 * @date 2020年4月14日 上午10:44:18
 */
public class RealmUsernamePwd extends ParentRealm {
	@Lazy
	@Autowired
	UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO 权限验证，从数据库读取权限写入该subject
		System.out.println("进入自定义权限设置方法！");
		String username = (String) principals.getPrimaryPrincipal();
		// 从数据库或换村中获取用户角色信息
		User_dto user = userService.selectByUserName(username);
		List<Role> rolesList = user.getRoles();
		// 获取用户角色
		Set<String> roles = new HashSet<String>();
		// 获取用户权限
		Set<String> permissions = new HashSet<String>();
		// 给予登录用户权限和角色属性
		for (Role role : rolesList) {
			roles.add(role.getCode());
			for (Permission permissionrole : role.getPermission()) {
				permissions.add(permissionrole.getCode());
			}
		}

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		// 设置权限
		simpleAuthorizationInfo.setStringPermissions(permissions);
		// 设置角色
		simpleAuthorizationInfo.setRoles(roles);

		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("进入RealmUsername自定义登录验证方法！");
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String username = usernamePasswordToken.getUsername();// 用户输入用户名
		User_dto user = userService.selectByUserName(username);// 根据用户输入用户名查询该用户
		if (user == null) {
			throw new UnknownAccountException();// 用户不存在
		}
		if (2 == user.getState()) {
			throw new LockedAccountException();// 用户被禁用
		}
		String password = user.getPassword();// 数据库获取的密码
		// 主要的（用户名，也可以是用户对象（最好不放对象）），资格证书(数据库获取的密码)，区域名称（当前realm名称）
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
		// 加盐，对比的时候会使用该参数对用户输入的密码按照密码比较器指定规则加盐，加密，再去对比数据库密文
		simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
		return simpleAuthenticationInfo;
	}

}
