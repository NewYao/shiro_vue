package cn.jnx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jnx.mapper.EditionMapper;
import cn.jnx.model.ReturnJson;
import cn.jnx.service.EditionService;

@Service
public class EditionServiceImpl implements EditionService {

	@Autowired
	private EditionMapper editionMapper;

	@Override
	public ReturnJson getEditions() {
		return new ReturnJson().ok().data(editionMapper.selectAll());
	}

}
