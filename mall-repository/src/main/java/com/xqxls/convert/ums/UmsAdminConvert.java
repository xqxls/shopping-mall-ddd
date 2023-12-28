package com.xqxls.convert.ums;

import com.xqxls.model.UmsAdmin;
import com.xqxls.ums.model.vo.UmsAdminVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2023/12/27 15:38
 */
@Mapper
public interface UmsAdminConvert {

    UmsAdminConvert INSTANCE = Mappers.getMapper(UmsAdminConvert.class);

    UmsAdminVO convertAdmin(UmsAdmin umsAdmin);

    List<UmsAdminVO> convertAdminList(List<UmsAdmin> list);

}
