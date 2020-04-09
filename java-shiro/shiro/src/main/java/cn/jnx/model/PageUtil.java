package cn.jnx.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

public class PageUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static JSONObject page = null;

	// 信息总条数
	private static final String total = "total";
	// 信息总页数
	private static final String pages = "pages";
	// 一页显示个数
	private static final String pageSize = "pageSize";
	// 当前页码
	private static final String pageNum = "pageNum";
	// 数据
	private static final String rows = "rows";

	public static JSONObject formatPage(PageInfo<?> pageInfo) {
		page = new JSONObject();
		page.put(total, pageInfo.getTotal());
		page.put(pages, pageInfo.getPages());
		page.put(pageSize, pageInfo.getSize());
		page.put(pageNum, pageInfo.getPageNum());
		page.put(rows, pageInfo.getList());
		return page;
	}

	private PageUtil() {

	}

}
