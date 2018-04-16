package com.menglin.invest.util;

import java.io.Serializable;

import javax.management.loading.PrivateClassLoader;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author CGS
 * @time 2018年2月1日下午4:32:27
 */
public class file implements Serializable {

	public static final long serialVersionUID = 1L;
	private CommonsMultipartFile headFile;
	private CommonsMultipartFile file;
	private CommonsMultipartFile file1;
	private CommonsMultipartFile file2;
	private CommonsMultipartFile file3;
	
	

	public CommonsMultipartFile getHeadFile() {
		return headFile;
	}
	public void setHeadFile(CommonsMultipartFile headFile) {
		this.headFile = headFile;
	}
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	public CommonsMultipartFile getFile1() {
		return file1;
	}
	public void setFile1(CommonsMultipartFile file1) {
		this.file1 = file1;
	}
	public CommonsMultipartFile getFile2() {
		return file2;
	}
	public void setFile2(CommonsMultipartFile file2) {
		this.file2 = file2;
	}
	public CommonsMultipartFile getFile3() {
		return file3;
	}
	public void setFile3(CommonsMultipartFile file3) {
		this.file3 = file3;
	}

	
	

}
