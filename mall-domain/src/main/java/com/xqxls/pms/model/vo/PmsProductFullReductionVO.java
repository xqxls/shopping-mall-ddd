package com.xqxls.pms.model.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 16:07
 */
@Data
public class PmsProductFullReductionVO {

    private Long id;

    private Long productId;

    private BigDecimal fullPrice;

    private BigDecimal reducePrice;
}
