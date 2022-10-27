package review.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import review.springboot.dao.VipTypeMapper;
import review.springboot.domain.VipTypeDO;

import java.util.List;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2022/4/9 11:34
 */
@Service
public class VipTypeService {

    @Autowired
    private VipTypeMapper vipTypeMapper;

    public List<VipTypeDO> queryAllVipType() {
        return vipTypeMapper.selectList();
    }
}
