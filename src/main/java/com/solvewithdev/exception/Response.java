package com.solvewithdev.exception;

import java.io.Serializable;

/**
 * @author dpatar
 *
 */
public class Response implements Serializable {

	private static final long serialVersionUID = -2555688075063560324L;
	
	private ResponseMetaData responseMetaData;

	public ResponseMetaData getResponseMetaData() {
		return responseMetaData;
	}

	public void setResponseMetaData(ResponseMetaData responseMetaData) {
		this.responseMetaData = responseMetaData;
	}

	@Override
	public String toString() {
		return "Response [responseMetaData=" + responseMetaData + "]";
	}

}
