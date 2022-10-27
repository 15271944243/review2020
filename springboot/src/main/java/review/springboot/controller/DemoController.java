package review.springboot.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import review.springboot.domain.VipTypeDO;
import review.springboot.service.VipTypeService;
import review.springboot.vo.DemoReqVO;

import javax.validation.Valid;
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

    @RequestMapping("/validated")
    public String testValidated(@RequestBody @Validated DemoReqVO reqVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

        }
        String result = JSON.toJSONString(reqVO);
        return result;
    }

    @RequestMapping("/valid")
    public String testValid(@RequestBody @Valid DemoReqVO reqVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

        }
        String result = JSON.toJSONString(reqVO);
        return result;
    }
}
