package cn.jnx.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.jnx.common.Exception.CustomException;
import cn.jnx.mapper.UserMapper;
import cn.jnx.mapper.User_roleMapper;
import cn.jnx.model.PageUtil;
import cn.jnx.model.ReturnJson;
import cn.jnx.model.User;
import cn.jnx.model.User_role;
import cn.jnx.model.dto.User_dto;
import cn.jnx.service.UserService;
import cn.jnx.util.tools.GlobalTools;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private User_roleMapper user_roleMapper;

	@Override
	public JSONObject getUserList(Integer page, Integer size, User user, String[] beginDateScope) {
		PageHelper.startPage(page, size);
		List<User> userList = userMapper.getUserListByParams(user, beginDateScope);
		// 得到分页的结果对象
		PageInfo<User> PageInfo = new PageInfo<>(userList);
		// 将结果存入map进行传送
		JSONObject pageInfo = PageUtil.formatPage(PageInfo);
		return pageInfo;
	}

	@Override
	public int deleteUserById(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public User_dto selectByUserName(String username) {
		return userMapper.selectByUserName(username);
	}

	@Override
	public User selectByPhone(String phone) {
		return userMapper.selectByPhone(phone);
	}

	@Override
	@Transactional
	public boolean addUser(User user, Integer r_id) {

		int i = userMapper.insertSelective(user);
		if (i <= 0) {
			throw new CustomException("操作失败！");
		}
		int user_id = user.getId();
		User_role ur = new User_role();
		ur.setR_id(r_id);
		ur.setU_id(user_id);
		int x = user_roleMapper.insert(ur);
		if (x <= 0) {
			throw new CustomException("操作失败！");
		}
		return true;
	}

	@Override
	public Object queryByDate(String[] date) {
		String date1 = date[0];
		String date2 = date[1];
		int num = calcBetweenDate(date1, date2);
		String[] countArr = new String[num];
		System.out.println(num);
		return userMapper.queryByDate(date1, countArr);
	}

	public int calcBetweenDate(String start, String end) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = df.parse(start);
			endDate = df.parse(end);
		} catch (Exception e) {
			System.out.println("日期转换出错");
		}
		int count = (int) ((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000));
		return count;
	}


	@Override
	public ReturnJson modifyPwd(Integer id, String oldpwd, String newPwd) {
		User user = userMapper.selectByPrimaryKey(id);
		//对比旧密码
		String encryptionOldPwd = GlobalTools.md5SimpleHash(oldpwd, ByteSource.Util.bytes(user.getSalt()).toString());
		if(!encryptionOldPwd.equals(user.getPassword())){
			return new ReturnJson().fail().message("原始密码输入错误！");
		}
		String encryptionNewPwd = GlobalTools.md5SimpleHash(newPwd, ByteSource.Util.bytes(user.getSalt()).toString());
		user.setPassword(encryptionNewPwd);
		int refectRows = userMapper.updateByPrimaryKey(user);
		if(refectRows<=0) {
			return new ReturnJson().fail().message("密码修改失败");
		}
		return new ReturnJson().ok();
	}
}
