package com.xqxls.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 胡卓
 * @create 2023-05-05 17:55
 * @Description
 */
@Data
public class UmsAdminRpcResponse implements Serializable {

    private static final long serialVersionUID = 0L;

    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String icon;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 备注信息
     */
    private String note;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后登录时间
     */
    private Date loginTime;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    private Integer status;
}
