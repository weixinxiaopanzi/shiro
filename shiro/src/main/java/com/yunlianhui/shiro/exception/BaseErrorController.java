package com.yunlianhui.shiro.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Copyright (c) 广东云联国骥投资管理有限公司</p>
 *
 * <p>Title: 统一错误页面</p>
 *
 * @author Maopz
 * @date 2018-05-03 10:46:31
 * @version V1.0
 */
@Controller
@RequestMapping(value = "error")
public class BaseErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "nofind";
    }

    @RequestMapping
    public String error() {
        return getErrorPath();
    }

}
