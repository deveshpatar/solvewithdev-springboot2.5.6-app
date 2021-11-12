package com.solvewithdev.exception;

import java.io.Serializable;
import java.util.List;

/**
 * @author dpatar
 *
 */
public class ResponseMetaData implements Serializable {

	private static final long serialVersionUID = 6302950693299406368L;

	private String respCode;

	private List<String> respMessages;

	public ResponseMetaData() {
		super();
	}

	/**
	 * @param respCode
	 * @param correlationId
	 * @param respMessage
	 */
	public ResponseMetaData(String respCode, List<String> respMessages) {
		super();
		this.respCode = respCode;
		this.respMessages = respMessages;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public List<String> getRespMessages() {
		return respMessages;
	}

	public void setRespMessages(List<String> respMessages) {
		this.respMessages = respMessages;
	}

	@Override
	public String toString() {
		return "ResponseMetaData [respCode=" + respCode + ", respMessage=" + respMessages + "]";
	}

}
