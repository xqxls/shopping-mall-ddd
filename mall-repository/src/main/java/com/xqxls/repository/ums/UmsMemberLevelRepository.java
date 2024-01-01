package com.xqxls.repository.ums;

import com.xqxls.convert.ums.UmsMemberLevelConvert;
import com.xqxls.mapper.UmsMemberLevelMapper;
import com.xqxls.model.UmsMemberLevel;
import com.xqxls.ums.model.vo.UmsMemberLevelVO;
import com.xqxls.ums.repository.IUmsMemberLevelRepository;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

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
        Example example = new Example(UmsMemberLevel.class);
        example.createCriteria().andEqualTo("defaultStatus",defaultStatus);
        return UmsMemberLevelConvert.INSTANCE.convertMemberLevel(memberLevelMapper.selectByExample(example));
    }
}
