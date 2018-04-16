package com.menglin.invest.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

public class ImgPressThread extends Thread {
	
	private File localFile ;
	
	public ImgPressThread(File localFile){
		    System.out.println("图片路径：" + localFile+"--------------");  
	        this.localFile = localFile; 
	}
	public void run() {
		
			try {
				Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	

}
