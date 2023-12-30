package com.xqxls.sms.model.vo;

import lombok.Data;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 17:45
 */
@Data
public class SmsHomeNewProductVO {

    private Long id;

    private Long productId;

    private String productName;

    private Integer recommendStatus;

    private Integer sort;
}
