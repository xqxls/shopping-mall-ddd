package com.xqxls.domain.member.model.vo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 14:43
 */
@Data
@Document
public class MemberBrandAttentionVO {

    @Id
    private String id;
    @Indexed
    private Long memberId;
    private String memberNickname;
    private String memberIcon;
    @Indexed
    private Long brandId;
    private String brandName;
    private String brandLogo;
    private String brandCity;
    private Date createTime;
}
