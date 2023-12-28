package com.xqxls.convert;

import com.xqxls.model.UmsAdmin;
import com.xqxls.model.UmsResource;
import com.xqxls.model.UmsRole;
import com.xqxls.ums.model.vo.UmsAdminVO;
import com.xqxls.ums.model.vo.UmsResourceVO;
import com.xqxls.ums.model.vo.UmsRoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
