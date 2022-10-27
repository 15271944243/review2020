package review.springboot.dao;

import review.springboot.domain.VipTypeDO;

import java.util.List;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2022/4/9 11:34
 */
public interface VipTypeMapper {

    List<VipTypeDO> selectList();
}
