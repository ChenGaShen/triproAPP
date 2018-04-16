package com.menglin.invest.entity;

import java.io.Serializable;

public class Admin implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String adminName;

    private String adminPass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass == null ? null : adminPass.trim();
    }
}