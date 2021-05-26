package com.sunny.activiti.controller;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sunny.activiti.common.entity.ResponseResult;
import com.sunny.activiti.common.entity.ResponseTableResult;
import com.sunny.activiti.common.entity.ResponseUtil;
import com.sunny.activiti.common.entity.ResultCode;
import com.sunny.activiti.common.flow.cmd.HistoryProcessInstanceDiagramCmd;
import com.sunny.activiti.entity.FlowDef;
import com.sunny.activiti.service.IFlowInfoService;
import com.sunny.activiti.service.IProcesService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @ClassName: ActivitiModelController
 * @Description: 工作流引擎控制器
 * @Author: sunt
 * @Date: 2019/7/211:07
 **/
@Controller
@RequestMapping("model")
@Slf4j
public class ActivitiModelController {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private IProcesService procesService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private IFlowInfoService flowInfoService;
    @Autowired
    private ManagementService managementService;

    /**
     * 新建流程
     * @param request
     * @param response
     */
    @RequestMapping("/createModel")
    public void createModel(HttpServletRequest request, HttpServletResponse response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            Model modelData = repositoryService.newModel();

            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, "name");
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, "description");
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName("name");
            modelData.setKey(StringUtils.defaultString("key"));

            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));

            request.setAttribute("modelId", modelData.getId());

            response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelData.getId());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 流程列表查询
     * @return
     */
    @RequestMapping("queryModelList")
    @ResponseBody
    public ResponseTableResult<List<Model>> queryModelList(HttpServletRequest request) {
        try {
            int pageNo = Integer.valueOf(request.getParameter("page"));
            int pageSize = Integer.valueOf(request.getParameter("limit"));
            int firstResult = (pageNo-1)*pageSize;
            long count = repositoryService.createModelQuery().count();
            List<Model> list = repositoryService.createModelQuery().orderByCreateTime().desc().listPage(firstResult,pageSize);
            return ResponseUtil.makeTableRsp(0,count,list);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ResponseUtil.makeTableRsp(ResultCode.FAIL.code,0,null);
        }
    }

    /**
     * @Author sunt
     * @Description 保存流程
     * @Date 11:42 2019/7/2
     * @Param [modelId, name, json_xml, svg_xml, description]
     * @return void
     **/
    @PutMapping(value = { "/{modelId}/save" })
    @ResponseStatus(HttpStatus.OK)
    public void saveModel(@PathVariable String modelId, @RequestParam("name") String name,
                          @RequestParam("json_xml") String json_xml, @RequestParam("svg_xml") String svg_xml,
                          @RequestParam("description") String description) {
        try {
            Model model = this.repositoryService.getModel(modelId);

            ObjectNode modelJson = (ObjectNode) this.objectMapper.readTree(model.getMetaInfo());

            modelJson.put("name", name);
            modelJson.put("description", description);
            model.setMetaInfo(modelJson.toString());
            model.setName(name);

            this.repositoryService.saveModel(model);

            this.repositoryService.addModelEditorSource(model.getId(),json_xml.getBytes("utf-8"));

            InputStream svgStream = new ByteArrayInputStream(svg_xml.getBytes("utf-8"));
            TranscoderInput input = new TranscoderInput(svgStream);

            PNGTranscoder transcoder = new PNGTranscoder();

            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput(outStream);

            transcoder.transcode(input, output);
            byte[] result = outStream.toByteArray();
            this.repositoryService.addModelEditorSourceExtra(model.getId(), result);
            outStream.close();
        } catch (Exception e) {
            throw new ActivitiException("Error saving model", e);
        }
    }


    /**
     * 部署流程
     * @param request
     * @param redirectAttributes
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "deployModel")
    @ResponseBody
    public ResponseResult<String> deployModel(HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
        try {
            String modelId = request.getParameter("modelId");
            if (StringUtils.isNoneBlank(modelId)) {
                Model modelData = this.repositoryService.getModel(modelId);
                ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
                byte[] bpmnBytes = null;

                BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
                bpmnBytes = new BpmnXMLConverter().convertToXML(model);
                String processName = modelData.getName() + ".bpmn20.xml";
                Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes,"utf-8")).deploy();
                modelData.setDeploymentId(deployment.getId());
                repositoryService.saveModel(modelData);
                redirectAttributes.addFlashAttribute("message", "部署成功，部署ID=" + deployment.getId());
                //向流程定义表保存数据
                FlowDef flowDef = new FlowDef();
                List<Process> processes = model.getProcesses();
                for (Process process : processes) {
                    flowDef.setFlowCode(process.getId());
                    flowDef.setFlowName(process.getName());
                }
                flowInfoService.insertFlowDef(flowDef);

                return ResponseUtil.makeOKRsp("部署成功");
            }
            return ResponseUtil.makeErrRsp(ResultCode.NOT_FOUND.code,"系统异常,流程ID不存在");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseUtil.makeErrRsp(ResultCode.FAIL.code,"系统异常:" + ExceptionUtil.getMessage(e));
        }
    }

    /**
     * 删除流程
     * @param request
     * @return
     */
    @RequestMapping("delModel")
    @ResponseBody
    public ResponseResult<String> delModel(HttpServletRequest request) {
        try {
            String modelId = request.getParameter("modelId");
            if(StrUtil.isBlank(modelId)) {
                return ResponseUtil.makeErrRsp(ResultCode.NOT_FOUND.code,"流程ID不存在!");
            }
            repositoryService.deleteModel(modelId);
            return ResponseUtil.makeOKRsp("删除流程成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.makeErrRsp(ResultCode.FAIL.code,"系统异常:" + ExceptionUtil.getMessage(e));
        }
    }

    /**
     * 复制流程
     * @param modelId
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("copyModel")
    public void copyModel(String modelId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Model modelData = repositoryService.newModel();
        Model oldModel = repositoryService.getModel(modelId);
        modelData.setName(oldModel.getName() + "-复制");
        modelData.setKey(oldModel.getKey());
        modelData.setMetaInfo(oldModel.getMetaInfo());
        repositoryService.saveModel(modelData);
        repositoryService.addModelEditorSource(modelData.getId(), this.repositoryService.getModelEditorSource(oldModel.getId()));
        repositoryService.addModelEditorSourceExtra(modelData.getId(), this.repositoryService.getModelEditorSourceExtra(oldModel.getId()));
        response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelData.getId());
    }

    /**
     * 启动流程
     * @param request
     * @return
     */
    @RequestMapping("startProcess")
    @ResponseBody
    public ResponseResult<String> startProcess(HttpServletRequest request) {
        String vacationId = request.getParameter("vacationId");
        flowInfoService.resolve(Long.valueOf(vacationId),null);
        return ResponseUtil.makeOKRsp();
    }

    /**
     * 流程图查询
     * @param request
     */
    @RequestMapping("queryFlowImg")
    public void queryFlowImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String flowId = request.getParameter("flowId");
        InputStream inputStream = null;
        if(StrUtil.isBlank(flowId) || StrUtil.equals("null",flowId)) {
            inputStream = this.getClass().getClassLoader().getResourceAsStream("static/images/no_flowInfo.png");
        }else {
            Command<InputStream> cmd = new HistoryProcessInstanceDiagramCmd(flowId);
            inputStream = managementService.executeCommand(cmd);
        }
        BufferedOutputStream bout = new BufferedOutputStream(response.getOutputStream());
        try {
            if (null == inputStream) {
                inputStream = this.getClass().getResourceAsStream("/images/no_flowInfo.png");
            }
            byte b[] = new byte[1024];
            int len = inputStream.read(b);
            while (len > 0) {
                bout.write(b, 0, len);
                len = inputStream.read(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bout.close();
            if (null != inputStream) {
                inputStream.close();
            }
        }
    }

}
