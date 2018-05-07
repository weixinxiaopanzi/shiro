package com.yunlianhui.shiro.entity;

import java.io.Serializable;

/**
 * <p>
 * Copyright (c) 广东云联国骥投资管理有限公司
 * </p>
 *
 * <p>
 * Title:用户实体
 * </p>
 *
 * @author Maopz
 * @date 2018-04-25 15:32:56
 * @version V1.0
 */
public class SysUser implements Serializable{
	
	private static final long serialVersionUID = 7962131071548920882L;
	
	/**用户状态 : 1-正常,2-禁用*/
	public static final String status_1 = "1";
	/**用户状态 : 2-禁用*/
	public static final String status_2 = "2";

	private Long id;			//主键
	private String userName;	//用户名
	private String realName;	//真实名
	private String password;	//密码
	private String status;		//状态
	private String mobile;		//手机
	private String email;		//邮箱
	private String idCard;		//身份证
	private Long roleId;		//角色id
	private Long companyId;		//公司id
	private String position;	//职位
	private Long operatorId;	//操作人id
	private String lastLoginTime;	//最后登录时间yyyy-MM-dd HH:mm:ss
	private String updateTime;		//更新时间yyyy-MM-dd HH:mm:ss
	private String createTime;		//创建时间yyyy-MM-dd HH:mm:ss
	private String remark;			//备注
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
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

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

}
