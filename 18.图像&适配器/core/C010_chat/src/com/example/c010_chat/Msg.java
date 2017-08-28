package com.example.c010_chat;

//定义 一个消息类
public class Msg {
	
	//Fields 
	public static final int TYPE_RECEIVED = 0;
	public static final int TYPE_SENT = 1;
	private String content;
	private int type;
	

	public Msg(String content,int type) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	public int getType(){
		return type;
	}

}
