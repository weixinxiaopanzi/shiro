package com.yunlianhui.shiro.entity;

import java.io.Serializable;

/**
 * <p>Copyright (c) 广东云联国骥投资管理有限公司</p>
 *
 * <p>Title:角色实体 </p>
 *
 * @author Maopz
 * @date 2018-04-25 15:33:44
 * @version V1.0
 */
public class SysRole implements Serializable{
	
	private static final long serialVersionUID = 4488781678172023534L;
	
	/**用户角色状态 : 1-正常,2-禁用*/
	public static final String status_1 = "1";
	/**用户角色状态 : 2-禁用*/
	public static final String status_2 = "2";

	private Long id;		//id
	private String roleName;//角色名
	private Long operateId;	//操作人id
	private String status;	//状态
	private String createTime;	//创建时间yyyy-MM-dd HH:mm:ss
	private String updateTime;	//更新时间yyyy-MM-dd HH:mm:ss
	private String remark;		//备注
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getOperateId() {
		return operateId;
	}
	public void setOperateId(Long operateId) {
		this.operateId = operateId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
