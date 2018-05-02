package com.yunlianhui.shiro.mapper;

import java.util.List;

import com.yunlianhui.shiro.entity.SysMenu;

/**
 * <p>Copyright (c) 广东云联国骥投资管理有限公司</p>
 *
 * <p>Title: </p>
 *
 * @author Maopz
 * @date 2018-04-25 15:27:02
 * @version V1.0
 */
public interface SysMenuExtMapper {

	/**
	 * @param id
	 * @return
	 */
	List<SysMenu> selectByUserId(Long id);


}
