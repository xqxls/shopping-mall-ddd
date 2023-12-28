package com.xqxls.repository.pms;

import com.xqxls.convert.pms.PmsSkuStockConvert;
import com.xqxls.dao.PmsSkuStockDao;
import com.xqxls.mapper.PmsSkuStockMapper;
import com.xqxls.model.PmsSkuStock;
import com.xqxls.model.PmsSkuStockExample;
import com.xqxls.pms.model.vo.PmsSkuStockVO;
import com.xqxls.pms.repository.IPmsSkuStockRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/28 15:26
 */
@Repository
public class PmsSkuStockRepository implements IPmsSkuStockRepository {

    @Resource
    private PmsSkuStockMapper skuStockMapper;
    @Resource
    private PmsSkuStockDao skuStockDao;

    @Override
    public List<PmsSkuStockVO> getList(Long pid, String keyword) {
        PmsSkuStockExample example = new PmsSkuStockExample();
        PmsSkuStockExample.Criteria criteria = example.createCriteria().andProductIdEqualTo(pid);
        if (StringUtils.hasText(keyword)) {
            criteria.andSkuCodeLike("%" + keyword + "%");
        }
        return PmsSkuStockConvert.INSTANCE.pmsSkuStockEntityToVOList(skuStockMapper.selectByExample(example));
    }

    @Override
    public int update(Long pid, List<PmsSkuStockVO> skuStockVOList) {
        List<PmsSkuStock> pmsSkuStockList = PmsSkuStockConvert.INSTANCE.pmsSkuStockVOToEntityList(skuStockVOList);
        return skuStockDao.replaceList(pmsSkuStockList);
    }
}
