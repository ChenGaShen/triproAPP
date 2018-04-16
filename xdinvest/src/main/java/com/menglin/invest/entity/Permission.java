package com.menglin.invest.entity;

public class Permission {
    private Integer id;

    private String permissionName;

    private String permissionUrl;

    private String parentid;

    private Integer sortstring;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl == null ? null : permissionUrl.trim();
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    public Integer getSortstring() {
        return sortstring;
    }

    public void setSortstring(Integer sortstring) {
        this.sortstring = sortstring;
    }
}