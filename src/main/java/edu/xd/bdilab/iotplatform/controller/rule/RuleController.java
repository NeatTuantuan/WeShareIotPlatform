package edu.xd.bdilab.iotplatform.controller.rule;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import edu.xd.bdilab.iotplatform.controller.response.ResponseResult;
import edu.xd.bdilab.iotplatform.dao.rule.DeviceStateRule;
import edu.xd.bdilab.iotplatform.dao.rule.Rule;
import edu.xd.bdilab.iotplatform.netty.util.StringUtil;
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
@Api(tags = {"规则相关功能"})
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
    public ResponseResult addRules(@RequestParam Map<String,Object> ruleMap){

        return new ResponseResult(true,"001","添加规则成功",ruleService.addRules(ruleMap));
    }

}
