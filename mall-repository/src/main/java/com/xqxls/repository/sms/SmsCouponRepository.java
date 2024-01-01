package com.xqxls.repository.sms;

import com.github.pagehelper.PageHelper;
import com.xqxls.convert.sms.SmsCouponConvert;
import com.xqxls.convert.sms.SmsCouponProductCategoryRelationConvert;
import com.xqxls.convert.sms.SmsCouponProductRelationConvert;
import com.xqxls.dao.SmsCouponDao;
import com.xqxls.dao.SmsCouponProductCategoryRelationDao;
import com.xqxls.dao.SmsCouponProductRelationDao;
import com.xqxls.dto.SmsCouponParam;
import com.xqxls.mapper.SmsCouponMapper;
import com.xqxls.mapper.SmsCouponProductCategoryRelationMapper;
import com.xqxls.mapper.SmsCouponProductRelationMapper;
import com.xqxls.model.*;
import com.xqxls.sms.model.aggregates.SmsCouponRich;
import com.xqxls.sms.model.vo.SmsCouponVO;
import com.xqxls.sms.repository.ISmsCouponRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 16:00
 */
@Repository
public class SmsCouponRepository implements ISmsCouponRepository {

    @Resource
    private SmsCouponMapper couponMapper;
    @Resource
    private SmsCouponProductRelationMapper productRelationMapper;
    @Resource
    private SmsCouponProductCategoryRelationMapper productCategoryRelationMapper;
    @Resource
    private SmsCouponProductRelationDao productRelationDao;
    @Resource
    private SmsCouponProductCategoryRelationDao productCategoryRelationDao;
    @Resource
    private SmsCouponDao couponDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(SmsCouponRich couponRich) {
        SmsCoupon couponParam = SmsCouponConvert.INSTANCE.smsCouponVOToEntity(couponRich.getSmsCouponVO());
        couponParam.setCount(couponParam.getPublishCount());
        couponParam.setUseCount(0);
        couponParam.setReceiveCount(0);
        //插入优惠券表
        int count = couponMapper.insert(couponParam);
        //插入优惠券和商品关系表
        if(couponParam.getUseType().equals(2)){
            List<SmsCouponProductRelation> smsCouponProductRelationList = SmsCouponProductRelationConvert.INSTANCE.smsCouponProductRelationVOToEntityList(couponRich.getProductRelationVOList());
            for(SmsCouponProductRelation productRelation:smsCouponProductRelationList){
                productRelation.setCouponId(couponParam.getId());
            }
            productRelationDao.insertList(smsCouponProductRelationList);
        }
        //插入优惠券和商品分类关系表
        if(couponParam.getUseType().equals(1)){
            List<SmsCouponProductCategoryRelation> smsCouponProductCategoryRelationList = SmsCouponProductCategoryRelationConvert.INSTANCE.smsCouponProductCategoryRelationVOToEntityList(couponRich.getProductCategoryRelationVOList());
            for (SmsCouponProductCategoryRelation couponProductCategoryRelation : smsCouponProductCategoryRelationList) {
                couponProductCategoryRelation.setCouponId(couponParam.getId());
            }
            productCategoryRelationDao.insertList(smsCouponProductCategoryRelationList);
        }
        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        //删除优惠券
        int count = couponMapper.deleteByPrimaryKey(id);
        //删除商品关联
        deleteProductRelation(id);
        //删除商品分类关联
        deleteProductCategoryRelation(id);
        return count;
    }

    private void deleteProductCategoryRelation(Long id) {
        Example productCategoryRelationExample = new Example(SmsCouponProductCategoryRelation.class);
        productCategoryRelationExample.createCriteria().andEqualTo("couponId",id);
        productCategoryRelationMapper.deleteByExample(productCategoryRelationExample);
    }

    private void deleteProductRelation(Long id) {
        Example productRelationExample = new Example(SmsCouponProductRelation.class);
        productRelationExample.createCriteria().andEqualTo("couponId",id);
        productRelationMapper.deleteByExample(productRelationExample);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Long id, SmsCouponRich couponRich) {
        SmsCoupon couponParam = SmsCouponConvert.INSTANCE.smsCouponVOToEntity(couponRich.getSmsCouponVO());
        couponParam.setId(id);
        int count =couponMapper.updateByPrimaryKey(couponParam);
        //删除后插入优惠券和商品关系表
        if(couponParam.getUseType().equals(2)){
            List<SmsCouponProductRelation> smsCouponProductRelationList = SmsCouponProductRelationConvert.INSTANCE.smsCouponProductRelationVOToEntityList(couponRich.getProductRelationVOList());
            for(SmsCouponProductRelation productRelation:smsCouponProductRelationList){
                productRelation.setCouponId(couponParam.getId());
            }
            deleteProductRelation(id);
            productRelationDao.insertList(smsCouponProductRelationList);
        }
        //删除后插入优惠券和商品分类关系表
        if(couponParam.getUseType().equals(1)){
            List<SmsCouponProductCategoryRelation> smsCouponProductCategoryRelationList = SmsCouponProductCategoryRelationConvert.INSTANCE.smsCouponProductCategoryRelationVOToEntityList(couponRich.getProductCategoryRelationVOList());
            for (SmsCouponProductCategoryRelation couponProductCategoryRelation : smsCouponProductCategoryRelationList) {
                couponProductCategoryRelation.setCouponId(couponParam.getId());
            }
            deleteProductCategoryRelation(id);
            productCategoryRelationDao.insertList(smsCouponProductCategoryRelationList);
        }
        return count;
    }

    @Override
    public List<SmsCouponVO> list(String name, Integer type, Integer pageSize, Integer pageNum) {
        Example example = new Example(SmsCoupon.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.hasText(name)){
            criteria.andLike("name","%"+name+"%");
        }
        if(type!=null){
            criteria.andEqualTo("type",type);
        }
        PageHelper.startPage(pageNum,pageSize);
        return SmsCouponConvert.INSTANCE.smsCouponEntityToVOList(couponMapper.selectByExample(example));
    }

    @Override
    public SmsCouponRich getItem(Long id) {
        SmsCouponParam smsCouponParam = couponDao.getItem(id);
        SmsCouponRich smsCouponRich = new SmsCouponRich();
        smsCouponRich.setSmsCouponVO(SmsCouponConvert.INSTANCE.smsCouponEntityToVO(smsCouponParam));
        smsCouponRich.setProductRelationVOList(SmsCouponProductRelationConvert.INSTANCE.smsCouponProductRelationEntityToVOList(smsCouponParam.getProductRelationList()));
        smsCouponRich.setProductCategoryRelationVOList(SmsCouponProductCategoryRelationConvert.INSTANCE.smsCouponProductCategoryRelationEntityToVOList(smsCouponParam.getProductCategoryRelationList()));
        return smsCouponRich;
    }
}
