package cn.jnx.model.dto;

import java.util.List;

import cn.jnx.model.Edition;
import cn.jnx.model.Edition_detail;

public class Edition_dto extends Edition{
	private List<Edition_detail> details;

	public List<Edition_detail> getDetails() {
		return details;
	}

	public void setDetails(List<Edition_detail> details) {
		this.details = details;
	}

}
