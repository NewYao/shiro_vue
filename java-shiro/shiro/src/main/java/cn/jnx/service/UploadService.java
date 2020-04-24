package cn.jnx.service;

import java.io.IOException;

import cn.jnx.model.MultipartFileParam;

public interface UploadService {

    String chunkUploadByMappedByteBuffer(MultipartFileParam param) throws IOException;

}
