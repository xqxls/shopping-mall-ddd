package com.xqxls.controller;

import com.xqxls.api.CommonPage;
import com.xqxls.api.CommonResult;
import com.xqxls.domain.member.model.vo.MemberBrandAttentionVO;
import com.xqxls.domain.member.service.MemberAttentionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 会员关注品牌管理Controller
 * Created by macro on 2018/8/2.
 */
@Controller
@Api(tags = "MemberAttentionController", description = "会员关注品牌管理")
@RequestMapping("/member/attention")
public class MemberAttentionController {
    @Resource
    private MemberAttentionService memberAttentionService;
    @ApiOperation("添加品牌关注")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> add(@RequestBody MemberBrandAttentionVO memberBrandAttentionVO) {
        int count = memberAttentionService.add(memberBrandAttentionVO);
        if(count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("取消关注")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> delete(Long brandId) {
        int count = memberAttentionService.delete(brandId);
        if(count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("显示关注列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<MemberBrandAttentionVO>> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<MemberBrandAttentionVO> page = memberAttentionService.list(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("显示关注品牌详情")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<MemberBrandAttentionVO> detail(@RequestParam Long brandId) {
        MemberBrandAttentionVO memberBrandAttentionVO = memberAttentionService.detail(brandId);
        return CommonResult.success(memberBrandAttentionVO);
    }

    @ApiOperation("清空关注列表")
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> clear() {
        memberAttentionService.clear();
        return CommonResult.success(null);
    }
}
