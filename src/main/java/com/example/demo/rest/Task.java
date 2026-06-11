package com.example.demo.rest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//JSONの記述順を決めておく
@JsonPropertyOrder({
	"id",
	"name",
	"completed"
})

public class Task {
	//項目定義
	private Long id; //ID
	
	@NotBlank
	@Size(max = 255)
	private String name; //名前
	
	@NotNull
	private Boolean completed;
	
	//コンストラクタ
	public Task() {
	}
	
	//ゲッターセッター
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getCompleted() {
		return completed;
	}
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	
}
