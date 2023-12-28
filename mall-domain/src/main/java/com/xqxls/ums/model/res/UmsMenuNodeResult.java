package com.xqxls.ums.model.res;

import com.xqxls.ums.model.vo.UmsMenuVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/27 20:08
 */
@Getter
@Setter
public class UmsMenuNodeResult extends UmsMenuVO {

    @ApiModelProperty(value = "子级菜单")
    private List<UmsMenuNodeResult> children;
}
