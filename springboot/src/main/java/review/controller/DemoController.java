package review.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import review.domain.VipTypeDO;
import review.service.VipTypeService;

import java.util.List;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2022/4/9 11:34
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private VipTypeService vipTypeService;

    @RequestMapping("/vipType")
    public String queryAllVipType() {
        List<VipTypeDO> vipTypeDOList = vipTypeService.queryAllVipType();
        String result = JSON.toJSONString(vipTypeDOList);
        return result;
    }
}
