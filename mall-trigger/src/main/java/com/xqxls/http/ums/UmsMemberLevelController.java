package com.xqxls.http.ums;

import com.xqxls.api.CommonResult;
import com.xqxls.ums.model.vo.UmsMemberLevelVO;
import com.xqxls.ums.service.UmsMemberLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 会员等级管理Controller
 * Created by xqxls on 2018/4/26.
 */
@Controller
@Api(tags = "UmsMemberLevelController", description = "会员等级管理")
@RequestMapping("/memberLevel")
public class UmsMemberLevelController {

    @Resource
    private UmsMemberLevelService memberLevelService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation("查询所有会员等级")
    @ResponseBody
    public CommonResult<List<UmsMemberLevelVO>> list(@RequestParam("defaultStatus") Integer defaultStatus) {
        List<UmsMemberLevelVO> umsMemberLevelVOList = memberLevelService.list(defaultStatus);
        return CommonResult.success(umsMemberLevelVOList);
    }
}
