package com.xqxls.repository.ums;

import com.xqxls.convert.ums.UmsMemberLevelConvert;
import com.xqxls.mapper.UmsMemberLevelMapper;
import com.xqxls.model.UmsMemberLevelExample;
import com.xqxls.ums.model.vo.UmsMemberLevelVO;
import com.xqxls.ums.repository.IUmsMemberLevelRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/27 17:00
 */
@Repository
public class UmsMemberLevelRepository implements IUmsMemberLevelRepository {

    @Resource
    private UmsMemberLevelMapper memberLevelMapper;

    @Override
    public List<UmsMemberLevelVO> list(Integer defaultStatus) {
        UmsMemberLevelExample example = new UmsMemberLevelExample();
        example.createCriteria().andDefaultStatusEqualTo(defaultStatus);
        return UmsMemberLevelConvert.INSTANCE.convertMemberLevel(memberLevelMapper.selectByExample(example));
    }
}
