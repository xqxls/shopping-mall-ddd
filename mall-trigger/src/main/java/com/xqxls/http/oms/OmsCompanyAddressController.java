package com.xqxls.http.oms;

import com.xqxls.api.CommonResult;
import com.xqxls.oms.model.vo.OmsCompanyAddressVO;
import com.xqxls.oms.service.OmsCompanyAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收货地址管理Controller
 * Created by xqxls on 2018/10/18.
 */
@Controller
@Api(tags = "OmsCompanyAddressController", description = "收货地址管理")
@RequestMapping("/companyAddress")
public class OmsCompanyAddressController {
    @Resource
    private OmsCompanyAddressService companyAddressService;

    @ApiOperation("获取所有收货地址")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<OmsCompanyAddressVO>> list() {
        List<OmsCompanyAddressVO> companyAddressVOList = companyAddressService.list();
        return CommonResult.success(companyAddressVOList);
    }
}
