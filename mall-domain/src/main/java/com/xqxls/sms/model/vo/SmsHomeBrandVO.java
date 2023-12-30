package com.xqxls.sms.model.vo;

import lombok.Data;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 17:36
 */
@Data
public class SmsHomeBrandVO {

    private Long id;

    private Long brandId;

    private String brandName;

    private Integer recommendStatus;

    private Integer sort;
}
