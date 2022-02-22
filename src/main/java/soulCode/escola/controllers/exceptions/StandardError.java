package soulCode.escola.controllers.exceptions;

public class StandardError {
	
	private String timestamp;
	private Integer status;
	private String msgErro;
	
	//constructor
	public StandardError(String timestamp, Integer status, String msgErro) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.msgErro = msgErro;
	}
	
	
	//getters
	public String getTimestamp() {
		return timestamp;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public String getMsgErro() {
		return msgErro;
	}
	
	//setters
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}
}
