package com.onakaumi.core.base.web.action.support;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.onakaumi.util.JsonUtil;
import com.onakaumi.util.pagination.Pager;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author onakaumi
 * 
 * @param <T>
 */
public abstract class DataSupportAction<T> extends PageSupportAction<T>
		implements ModelDriven<T>, SessionAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;

	protected static final String JSON = "json";

	protected static final String JSON_ARRAY = "jsonArray";

	protected JSONObject json = new JSONObject();

	protected JSONArray jsonArray;

	protected List<T> lists;

	protected T model;

	public JSONObject getJson() {
		return json;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public List<T> getLists() {
		return lists;
	}

	@Override
	public T getModel() {
		return model;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public void setLists(List<T> lists) {
		this.lists = lists;
	}

	public void setModel(T model) {
		this.model = model;
	}

	protected Map<String, Object> session;
	protected HttpServletRequest request;

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 把pager 转为 jsonObject，jsonObject在里面初始化了。
	 * 
	 * pager
	 */
	protected void pager2Json(@SuppressWarnings("rawtypes") Pager pager) {
		json.put("total", pager.getTotalItems());
		String listStr = JsonUtil.list2json(pager.getList());
		json.put("rows", listStr);
	}
}
