package com.xqxls.sms.model.vo;

import lombok.Data;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 19:45
 */
@Data
public class SmsHomeRecommendSubjectVO {

    private Long id;

    private Long subjectId;

    private String subjectName;

    private Integer recommendStatus;

    private Integer sort;
}
