package review.dao;

import org.apache.ibatis.annotations.Mapper;
import review.domain.VipTypeDO;

import java.util.List;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2022/4/9 11:34
 */
@Mapper
public interface VipTypeMapper {

    List<VipTypeDO> selectList();
}
