package com.xqxls.http.pms;

import com.xqxls.api.CommonResult;
import com.xqxls.pms.model.vo.PmsSkuStockVO;
import com.xqxls.pms.service.PmsSkuStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * sku库存Controller
 * Created by xqxls on 2018/4/27.
 */
@Controller
@Api(tags = "PmsSkuStockController", description = "sku商品库存管理")
@RequestMapping("/sku")
public class PmsSkuStockController {
    @Resource
    private PmsSkuStockService skuStockService;

    @ApiOperation("根据商品编号及编号模糊搜索sku库存")
    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsSkuStockVO>> getList(@PathVariable Long pid, @RequestParam(value = "keyword",required = false) String keyword) {
        List<PmsSkuStockVO> skuStockList = skuStockService.getList(pid, keyword);
        return CommonResult.success(skuStockList);
    }
    @ApiOperation("批量更新库存信息")
    @RequestMapping(value ="/update/{pid}",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> update(@PathVariable Long pid,@RequestBody List<PmsSkuStockVO> skuStockVOList){
        int count = skuStockService.update(pid,skuStockVOList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
