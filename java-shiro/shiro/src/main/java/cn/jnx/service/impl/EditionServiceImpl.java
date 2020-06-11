package cn.jnx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jnx.common.Exception.CustomException;
import cn.jnx.mapper.EditionMapper;
import cn.jnx.mapper.Edition_detailMapper;
import cn.jnx.model.Edition;
import cn.jnx.model.Edition_detail;
import cn.jnx.model.ReturnJson;
import cn.jnx.model.dto.Edition_dto;
import cn.jnx.service.EditionService;

@Service
public class EditionServiceImpl implements EditionService {

	@Autowired
	private EditionMapper editionMapper;
	@Autowired
	private Edition_detailMapper detailMapper;

	@Override
	public ReturnJson getEditions() {
		return new ReturnJson().ok().data(editionMapper.selectAll());
	}

	@Override
	@Transactional
	public ReturnJson addEdition(Edition edition, List<Edition_detail> edition_detail) {
		int userRow = editionMapper.insert(edition);
		if (userRow <= 0) {
			throw new CustomException("新增失败！");
		}
		if (edition_detail.size() == 0) {
			return new ReturnJson().ok();
		}
		int edition_id = edition.getId();
		for (Edition_detail details : edition_detail) {
			details.setEdition_id(edition_id);
		}
		int rows = detailMapper.insertList(edition_detail);
		if (rows < edition_detail.size()) {
			throw new CustomException("新增失败！");
		}
		return new ReturnJson().ok();
	}

	@Override
	@Transactional
	public ReturnJson delEdition(int id) {
		int row = editionMapper.deleteByPrimaryKey(id);
		if (row <= 0) {
			return new ReturnJson().fail();
		}
		return new ReturnJson().ok();
	}

	@Override
	public ReturnJson getEdition(int id) {
		Edition_dto edo = editionMapper.selectByPrimaryKey(id);
		return new ReturnJson().ok().data(edo);
	}

	@Override
	@Transactional
	public ReturnJson editEdition(Edition edi, List<Edition_detail> edi_detail) {
		int userRow = editionMapper.updateByPrimaryKey(edi);
		if (userRow <= 0) {
			throw new CustomException("修改失败！");
		}
		if (edi_detail.size() == 0) {
			return new ReturnJson().ok();
		}
		int edition_id = edi.getId();
		// 删除全部
		int delRows = detailMapper.deleteByEditionId(edition_id);
		if (delRows <= 0) {
			throw new CustomException("修改失败！");
		}
		// 后新增
		for (Edition_detail details : edi_detail) {
			details.setEdition_id(edition_id);
		}
		int rows = detailMapper.insertList(edi_detail);
		if (rows < edi_detail.size()) {
			throw new CustomException("新增失败！");
		}
		return new ReturnJson().ok();
	}

}
