package com.shell.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
public class JsonResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> jsonRespone = new HashMap<String, Object>();

	private static final String STATUS_CODE = "statusCode";	//狀態碼
	private static final String MESSAGE = "message";	//狀態訊息

	
	public JsonResponse(int statusCode, String message) {
		jsonRespone.put(STATUS_CODE, statusCode);
		jsonRespone.put(MESSAGE, message);
	}
	
	public Map<String,Object> setResponse(Map<String, Object> respone) {
		for (Map.Entry entry : respone.entrySet()) {
			jsonRespone.put( entry.getKey().toString(), entry.getValue());
		}
		return respone;
	}
	
	/**
	 * return json map object
	 * @return
	 */
	public Map<String, Object> returnJsonResponse() {
		return jsonRespone;
	}
}
