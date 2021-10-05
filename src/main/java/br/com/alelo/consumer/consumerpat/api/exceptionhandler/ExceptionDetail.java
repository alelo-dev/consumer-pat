package br.com.alelo.consumer.consumerpat.api.exceptionhandler;

import lombok.Builder;

@Builder
public class ExceptionDetail {

	private Integer status;
	private String type;
	private String title;
	private String detail;


	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
