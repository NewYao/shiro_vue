package cn.jnx.model;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName:  MultipartFileParam   
 * @Description 大文件分片入参实体
 * @version 
 * @author Administrator
 * @date 2020年4月23日 下午3:32:11
 */
@ApiModel("大文件分片入参实体")
public class MultipartFileParam {
    @ApiModelProperty("文件传输任务ID")
    private String taskId;

    @ApiModelProperty("当前为第几分片")
    private int chunk;

    @ApiModelProperty("每个分块的大小")
    private long size;


    @ApiModelProperty("分片总数")
    private int chunkTotal;

    @ApiModelProperty("主体类型--这个字段是我项目中的其他业务逻辑可以忽略")
    private int objectType;

    @ApiModelProperty("分块文件传输对象")
    private MultipartFile file;

    @Override
    public String toString() {
        return "MultipartFileParam [taskId=" + taskId + ", chunk=" + chunk + ", size=" + size + ", chunkTotal="
                + chunkTotal + ", objectType=" + objectType + ", file=" + file + "]";
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public int getChunk() {
        return chunk;
    }

    public void setChunk(int chunk) {
        this.chunk = chunk;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getChunkTotal() {
        return chunkTotal;
    }

    public void setChunkTotal(int chunkTotal) {
        this.chunkTotal = chunkTotal;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    
}
