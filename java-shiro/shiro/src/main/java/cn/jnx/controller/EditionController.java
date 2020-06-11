package cn.jnx.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.jnx.model.Edition;
import cn.jnx.model.Edition_detail;
import cn.jnx.model.ReturnJson;
import cn.jnx.service.EditionService;

@RestController
@RequestMapping("/edition")
public class EditionController {
	@Autowired
	private EditionService editionService;

	@PostMapping("/editions")
	public ReturnJson getEditions() {
		return editionService.getEditions();
	}

	@PostMapping("/edition")
	public ReturnJson getEdition(int id) {
		return editionService.getEdition(id);
	}

	@PostMapping("/delEdition")
	@RequiresRoles("admin")
	public ReturnJson delEdition(int id) {
		return editionService.delEdition(id);
	}
	@PostMapping("/editEdition")
	@RequiresRoles("admin")
	public ReturnJson editEdition(String edition, String details) {
		Edition edi = JSONObject.parseObject(edition, Edition.class);
		List<Edition_detail> edi_detail = JSONArray.parseArray(details, Edition_detail.class);
		return editionService.editEdition(edi, edi_detail);
	}
	
	@PostMapping("/addEdition")
	@RequiresRoles("admin")
	public ReturnJson addEdition(String edition, String details) {
		Edition edi = JSONObject.parseObject(edition, Edition.class);
		List<Edition_detail> edi_detail = JSONArray.parseArray(details, Edition_detail.class);
		return editionService.addEdition(edi, edi_detail);
	}
	
}
