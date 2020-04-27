package cn.jnx.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import cn.jnx.model.MultipartFileParam;
import cn.jnx.model.ReturnJson;
import cn.jnx.service.UploadService;
import io.swagger.annotations.ApiOperation;

/**
 * 文件上传controller
 * @ClassName:  UploadController   
 * @Description TODO
 * @version 
 * @author jh
 * @date 2020年4月23日 下午3:28:38
 */
@Controller
@RequestMapping("/sys")
public class UploadController {
    
    @Autowired
    private UploadService uploadService;
    
    @ApiOperation("大文件分片上传")
    @PostMapping("/chunkUpload")
    public ReturnJson fileChunkUpload(MultipartFileParam param, HttpServletResponse response, HttpServletRequest request)throws IOException{
        /**
         * 判断前端Form表单格式是否支持文件上传
         */
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if(!isMultipart){
            return new ReturnJson().fail().message("不支持的表单格式");
        }
        System.out.println("上传文件 start...");
        try {
            String taskId = uploadService.chunkUploadByMappedByteBuffer(param);
        } catch (IOException e) {
            System.out.println("文件上传失败。{}"+param.toString());
        }
        System.out.println("上传文件结束");
        return new ReturnJson().ok();
    }
    
    
    public ReturnJson uploadFile(MultipartFile[] file) {
        return new ReturnJson().ok();
    }
}
