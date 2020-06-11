package cn.jnx.service;

import java.util.List;

import cn.jnx.model.Edition;
import cn.jnx.model.Edition_detail;
import cn.jnx.model.ReturnJson;

public interface EditionService {
	
	public ReturnJson getEditions();

	public ReturnJson addEdition(Edition edition, List<Edition_detail> edition_detail);

	public ReturnJson delEdition(int id);

	public ReturnJson getEdition(int id);

	public ReturnJson editEdition(Edition edi, List<Edition_detail> edi_detail);
}
