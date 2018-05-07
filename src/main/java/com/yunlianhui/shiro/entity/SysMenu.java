package com.yunlianhui.shiro.entity;

import java.io.Serializable;

/**
 * <p>Copyright (c) 广东云联国骥投资管理有限公司</p>
 *
 * <p>Title:菜单 </p>
 *
 * @author Maopz
 * @date 2018-04-25 15:36:03
 * @version V1.0
 */
public class SysMenu implements Serializable{

	private static final long serialVersionUID = -3547541839244585208L;
	
	/**级别 1-一级,2-二级,3-三级(三级是按钮)*/
	public static final String level_1 = "1";
	/**级别 1-一级,2-二级,3-三级(三级是按钮)*/
	public static final String level_2 = "2";
	/**级别 1-一级,2-二级,3-三级(三级是按钮)*/
	public static final String level_3 = "3";
	
	/**类型 1-菜单,2-按钮*/
	public static final String type_1 = "1";
	/**类型 1-菜单,2-按钮*/
	public static final String type_2 = "2";
	
	private Long id;		//id
	private Long roleId;	//角色id
	private String menuId;	//菜单id
	private String menuName;//菜单名称
	private String menuUrl;	//菜单地址
	private String per;		//菜单权限标识符
	private String level;		//级别 1-一级,2-二级,3-三级(三级是按钮)
	private String type;	//类型 1-菜单,2-按钮
	private String updateTime;		//更新时间yyyy-MM-dd HH:mm:ss
	private String createTime;		//创建时间yyyy-MM-dd HH:mm:ss
	private String remark;			//备注
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getPer() {
		return per;
	}
	public void setPer(String per) {
		this.per = per;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
