package cn.jnx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jnx.model.ReturnJson;
import cn.jnx.service.EditionService;

@RestController
@RequestMapping("/edition")
public class EditionController {
	@Autowired
	private EditionService editionService;

	@RequestMapping("/")
	public ReturnJson getEditions() {
		return editionService.getEditions();
	}
}
