package com.xqxls.repository.member;

import com.xqxls.convert.member.UmsMemberReceiveAddressConvert;
import com.xqxls.domain.member.model.vo.UmsMemberReceiveAddressVO;
import com.xqxls.domain.member.repository.IUmsMemberReceiveAddressRepository;
import com.xqxls.mapper.UmsMemberReceiveAddressMapper;
import com.xqxls.model.UmsMember;
import com.xqxls.model.UmsMemberReceiveAddress;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: xqxls
 * @CreateTime: 2024/1/2 15:29
 */
@Repository
public class UmsMemberReceiveAddressRepository implements IUmsMemberReceiveAddressRepository {

    @Resource
    private UmsMemberRepository umsMemberRepository;
    @Resource
    private UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;

    @Override
    public int add(UmsMemberReceiveAddressVO umsMemberReceiveAddressVO) {
        UmsMember currentMember = umsMemberRepository.getCurrentMember();
        UmsMemberReceiveAddress address = UmsMemberReceiveAddressConvert.INSTANCE.convertVOToEntity(umsMemberReceiveAddressVO);
        address.setMemberId(currentMember.getId());
        return umsMemberReceiveAddressMapper.insert(address);
    }

    @Override
    public int delete(Long id) {
        UmsMember currentMember = umsMemberRepository.getCurrentMember();
        Example example = new Example(UmsMemberReceiveAddress.class);
        example.createCriteria().andEqualTo("memberId",currentMember.getId()).andEqualTo("id",id);
        return umsMemberReceiveAddressMapper.deleteByExample(example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Long id, UmsMemberReceiveAddressVO umsMemberReceiveAddressVO) {
        UmsMemberReceiveAddress address = UmsMemberReceiveAddressConvert.INSTANCE.convertVOToEntity(umsMemberReceiveAddressVO);
        address.setId(null);
        UmsMember currentMember = umsMemberRepository.getCurrentMember();
        Example example = new Example(UmsMemberReceiveAddress.class);
        example.createCriteria().andEqualTo("memberId",currentMember.getId()).andEqualTo("id",id);
        if(address.getDefaultStatus()==1){
            //先将原来的默认地址去除
            UmsMemberReceiveAddress record= new UmsMemberReceiveAddress();
            record.setDefaultStatus(0);
            Example updateExample = new Example(UmsMemberReceiveAddress.class);
            updateExample.createCriteria()
                    .andEqualTo("memberId",currentMember.getId())
                    .andEqualTo("defaultStatus",1);
            umsMemberReceiveAddressMapper.updateByExampleSelective(record,updateExample);
        }
        return umsMemberReceiveAddressMapper.updateByExampleSelective(address,example);
    }

    @Override
    public List<UmsMemberReceiveAddressVO> list() {
        UmsMember currentMember = umsMemberRepository.getCurrentMember();
        Example example = new Example(UmsMemberReceiveAddress.class);
        example.createCriteria().andEqualTo("memberId",currentMember.getId());
        return UmsMemberReceiveAddressConvert.INSTANCE.convertEntityToVOList(umsMemberReceiveAddressMapper.selectByExample(example));
    }

    @Override
    public UmsMemberReceiveAddressVO getItem(Long id) {
        UmsMember currentMember = umsMemberRepository.getCurrentMember();
        Example example = new Example(UmsMemberReceiveAddress.class);
        example.createCriteria().andEqualTo("memberId",currentMember.getId()).andEqualTo("id",id);
        List<UmsMemberReceiveAddress> addressList = umsMemberReceiveAddressMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(addressList)){
            return UmsMemberReceiveAddressConvert.INSTANCE.convertEntityToVO(addressList.get(0));
        }
        return null;
    }
}
