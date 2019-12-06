package edu.xd.bdilab.iotplatform.controller.rule;

import edu.xd.bdilab.iotplatform.controller.response.ResponseResult;
import edu.xd.bdilab.iotplatform.dao.rule.Rule;
import edu.xd.bdilab.iotplatform.service.rule.RuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName RuleController
 * @Description TODO
 * @Author tuantuan
 * @Date 2019/12/6 下午10:56
 * @Version 1.0
 * @Attention Copyright (C), 2004-2019, BDILab, XiDian University
 **/
@RestController
public class RuleController {
    @Autowired
    RuleService ruleService;

    @GetMapping(value = "rule/getAllRule")
    @ApiOperation(value = "查看所有规则")
    public ResponseResult getAllRule(){
        return new ResponseResult(true,"001","查看规则成功",ruleService.checkAllRules());
    }

    @PostMapping(value = "rule/addRules")
    @ApiOperation(value = "添加规则")
    public ResponseResult addRules(@RequestBody Map<Integer, Rule> ruleMap){
        return null;
    }

}
