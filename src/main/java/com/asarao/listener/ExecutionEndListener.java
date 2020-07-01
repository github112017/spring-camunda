package com.asarao.listener;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.runtime.VariableInstance;
import org.springframework.stereotype.Component;

/*
 * @ClassName: ExecutionEndListener
 * @Description:
 * @Author: Asarao
 * @Date: 2020/6/29 20:10
 * @Version: 1.0
 **/
@Component
@Slf4j
public class ExecutionEndListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        log.info("Execution end listener 开始执行；监听事件：{}",execution.getEventName());
        String processInstanceId = execution.getProcessInstanceId();
        RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
        VariableInstance nrOfInstances = runtimeService.createVariableInstanceQuery()
                .variableName("nrOfInstances")
                .processInstanceIdIn(processInstanceId)
                .singleResult();
        log.info("多实列的个数：{}",nrOfInstances.getValue());
        VariableInstance nrOfCompletedInstances = runtimeService.createVariableInstanceQuery()
                .variableName("nrOfCompletedInstances")
                .processInstanceIdIn(processInstanceId)
                .singleResult();
        log.info("完成实列的个数：{}",nrOfCompletedInstances.getValue());
    }
}
