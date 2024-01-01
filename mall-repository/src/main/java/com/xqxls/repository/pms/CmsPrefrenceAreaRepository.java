package com.xqxls.repository.pms;

import com.xqxls.convert.pms.CmsPrefrenceAreaConvert;
import com.xqxls.mapper.CmsPrefrenceAreaMapper;
import com.xqxls.model.CmsPrefrenceArea;
import com.xqxls.pms.model.vo.CmsPrefrenceAreaVO;
import com.xqxls.pms.repository.ICmsPrefrenceAreaRepository;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/30 20:09
 */
@Repository
public class CmsPrefrenceAreaRepository implements ICmsPrefrenceAreaRepository {

    @Resource
    private CmsPrefrenceAreaMapper cmsPrefrenceAreaMapper;

    @Override
    public List<CmsPrefrenceAreaVO> listAll() {
        return CmsPrefrenceAreaConvert.INSTANCE.cmsPrefrenceAreaEntityToVOList(cmsPrefrenceAreaMapper.selectByExample(new Example(CmsPrefrenceArea.class)));
    }
}
