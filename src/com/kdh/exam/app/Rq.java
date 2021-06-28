package com.kdh.exam.app;

import java.util.HashMap;
import java.util.Map;

import com.kdh.exam.app.container.Container;
import com.kdh.exam.app.dto.Member;
import com.kdh.exam.app.session.Session;

public class Rq {
	private Map<String, String> params;
	private String command;
	private String controllerTypeCode;
	private String controllerName;
	private String actionMethodName;
	private String queryString = "";
	public boolean isValid = true;

	public Rq() {

	}

	public void setCommand(String command) {
		params = new HashMap<>();

		String[] commandBits = command.split("\\?", 2);

		if (commandBits.length == 2) {
			queryString = commandBits[1];

			String[] queryStringBits = queryString.split("&");

			for (String queryStringBit : queryStringBits) {
				String[] queryStringBitBits = queryStringBit.split("=", 2);
				String paramName = queryStringBitBits[0];
				String paramValue = queryStringBitBits[1];

				params.put(paramName, paramValue);
			}
		}

		commandBits = commandBits[0].split("/", 4);

		if (commandBits.length != 4) {
			isValid = false;
			return;
		}

		controllerTypeCode = commandBits[1];
		controllerName = commandBits[2];
		actionMethodName = commandBits[3];

	}

	public Object getActionPath() {
		return "/" + controllerTypeCode + "/" + controllerName + "/" + actionMethodName;
	}

	public int getIntParam(String paramName, int defaultValue) {
		if (params.containsKey(paramName) == false) {
			return defaultValue;
		}

		try {
			return Integer.parseInt(params.get(paramName));
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	public String getControllerTypeCode() {
		return controllerTypeCode;
	}

	public String getControllerName() {
		return controllerName;
	}

	public String getActionMethodName() {
		return actionMethodName;
	}

	public void setSessionAttr(String key, Object value) {
		Session session = Container.getSession();

		session.setAttribute(key, value);
	}

	public void removeSessionAttr(String key) {
		Session session = Container.getSession();

		session.removeAttribute(key);
	}

	public Object getSessionAttr(String key) {
		Session session = Container.getSession();

		return session.getAttribute(key);
	}

	private boolean hasSessionAttr(String key) {
		Session session = Container.getSession();

		return session.hasAttribute(key);
	}

	public boolean isLogined() {
		return hasSessionAttr("loginedMember"); 	
	}

	public Member getLoginedMember() {
		return (Member)getSessionAttr("loginedMember");
	}

	public void logout() {
		removeSessionAttr("loginedMember");
	}

	public void login(Member member) {
		setSessionAttr("loginedMember", member);
	}
}
