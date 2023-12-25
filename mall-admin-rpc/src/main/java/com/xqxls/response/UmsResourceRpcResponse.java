package com.xqxls.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 胡卓
 * @create 2023-05-05 18:06
 * @Description
 */
@Data
public class UmsResourceRpcResponse implements Serializable {

    private static final long serialVersionUID = 0L;

    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源URL
     */
    private String url;

    /**
     * 描述
     */
    private String description;

    /**
     * 资源分类ID
     */
    private Long categoryId;
}
