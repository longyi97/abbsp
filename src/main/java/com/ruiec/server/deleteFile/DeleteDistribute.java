package com.ruiec.server.deleteFile;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class DeleteDistribute {
	private static final Logger logger = Logger.getLogger(DeleteDistribute.class);
	@PostConstruct
	public void deleteFile(){
		try{
			String path="C:/Users/asus/Desktop/eclipse";
			File f=new File(path);
			if(f.isDirectory()){
				for(String s:f.list()){
					if(s.startsWith("tmlog") || s.startsWith("com.atomikos.spring.jdbc.")){
						System.out.println(s);
						File f1=new File(path+"/"+s);
						f1.delete();
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@PreDestroy
	public void deleteFile1(){
		try{
			String path="C:/Users/asus/Desktop/eclipse";
			File f=new File(path);
			if(f.isDirectory()){
				for(String s:f.list()){
					
					if(s.startsWith("tmlog") || s.startsWith("com.atomikos.spring.jdbc.")){
						System.out.println(s);
						File f1=new File(path+"/"+s);
						f1.delete();
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void test(){
		System.out.println("ttttt");
	}
}
