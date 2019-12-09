package edu.xd.bdilab.iotplatform.controller.rule;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import edu.xd.bdilab.iotplatform.controller.response.ResponseResult;
import edu.xd.bdilab.iotplatform.dao.rule.DeviceRuleRelation;
import edu.xd.bdilab.iotplatform.dao.rule.DeviceStateRule;
import edu.xd.bdilab.iotplatform.dao.rule.Rule;
import edu.xd.bdilab.iotplatform.netty.util.StringUtil;
import edu.xd.bdilab.iotplatform.service.rule.RuleService;
import edu.xd.bdilab.iotplatform.vo.DeviceRuleRelationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping(value = "rule/deleteRules")
    @ApiOperation(value = "删除规则")
    public ResponseResult deleteRules(@RequestParam int id){
        return new ResponseResult();
    }

    @PostMapping(value = "rule/addRulesToDevice")
    @ApiOperation(value = "向设备添加规则")
    public ResponseResult addRulesToDevice(@RequestParam List<DeviceRuleRelationVO> deviceRuleRelationVOList ){
        DeviceRuleRelation deviceRuleRelation;
        for (DeviceRuleRelationVO deviceRuleRelationVO : deviceRuleRelationVOList){
            deviceRuleRelation = new DeviceRuleRelation();

            deviceRuleRelation.setFkDeviceId(deviceRuleRelationVO.getFkDeviceId());
            deviceRuleRelation.setFkRuleId(deviceRuleRelationVO.getFkRuleId());
            deviceRuleRelation.setRuleClassification(deviceRuleRelation.getRuleClassification());

            ruleService.addDeviceRuleRelation(deviceRuleRelation);

        }
        return new ResponseResult(true,"001","设备添加规则成功");
    }

    @GetMapping(value = "rule/getAllDeviceRuleRelation")
    @ApiOperation(value = "查看所有设备规则信息")
    public ResponseResult getAllDeviceRuleRelation(){
        return new ResponseResult(true,"001","查询所有关系成功",ruleService.selectAllRelation());
    }

}
