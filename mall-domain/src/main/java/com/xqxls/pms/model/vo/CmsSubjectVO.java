package com.xqxls.pms.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 19:58
 */
@Data
public class CmsSubjectVO {

    private Long id;

    private Long categoryId;

    private String title;

    @ApiModelProperty(value = "专题主图")
    private String pic;

    @ApiModelProperty(value = "关联产品数量")
    private Integer productCount;

    private Integer recommendStatus;

    private Date createTime;

    private Integer collectCount;

    private Integer readCount;

    private Integer commentCount;

    @ApiModelProperty(value = "画册图片用逗号分割")
    private String albumPics;

    private String description;

    @ApiModelProperty(value = "显示状态：0->不显示；1->显示")
    private Integer showStatus;

    @ApiModelProperty(value = "转发数")
    private Integer forwardCount;

    @ApiModelProperty(value = "专题分类名称")
    private String categoryName;

    private String content;
}
