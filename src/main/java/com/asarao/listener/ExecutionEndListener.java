package com.asarao.listener;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.el.FixedValue;
import org.camunda.bpm.model.bpmn.instance.FlowElement;
import org.camunda.bpm.model.xml.type.ModelElementType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * @ClassName: ExecutionEndListener
 * @Description: 所以说只能在上一届点添加执行完成监听
 * @Author: Asarao
 * @Date: 2020/6/29 20:10
 * @Version: 1.0
 **/
@Component
@Slf4j
public class ExecutionEndListener implements ExecutionListener {

    private FixedValue assigneeList;

    @Override
    public void notify(DelegateExecution execution) throws Exception {

        log.info("Execution end listener 开始执行；监听事件：{}",execution.getEventName());
        String expressionText = assigneeList.getExpressionText();
        Map<String,Object> variables = new HashMap<>(0);
        variables.put("assigneeList", Arrays.asList(expressionText.split(",")));
        // 设置变量
//        execution.setVariablesLocal(variables);
    }

    public FixedValue getAssigneeList() {
        return assigneeList;
    }

    public void setAssigneeList(FixedValue assigneeList) {
        this.assigneeList = assigneeList;
    }
}
