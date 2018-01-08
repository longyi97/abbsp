/*
 * 版权所有：深圳源中瑞科技有限公司
 * 网 址：www.ruiec.com
 * 电 话：0755-33581131
 */
package com.ruiec.web.util;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * file util class
 *
 * @author 张威斌
 * Version：1.0
 * Date：2017年7月28日
 */
public class FileUploadUtil {

    protected static final Logger LOGGER = Logger.getLogger(FileUploadUtil.class);

    /**
     * 保存上传的File,返回保存后的fileUrl
     * @author 张威斌
     * Date：2017年8月4日
     */
    public String saveFile(MultipartFile file, String savePath, String fileName, HttpServletRequest request) {
        String fileUrl = "";
        //if file is null return empty string
        if (file.isEmpty()) {
            return fileUrl;
        }
        //get save directory's real path
        String realPath = request.getSession().getServletContext().getRealPath(savePath);
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        fileName = fileName + suffix;
        try {
            // create save folder
            File targetFile = new File(realPath, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
            fileUrl = savePath + "/" + fileName;
        } catch (IOException e) {
            LOGGER.error("save file error!");
            e.printStackTrace();
        }
        return fileUrl;
    }

    /**
     * 保存上传的File,返回保存后的fileUrl
     * @author 张威斌
     * Date：2017年8月4日
     */
    public String saveFile(MultipartFile file, String savePath, HttpServletRequest request) {
        String fileName = UUID.randomUUID().toString();
        return saveFile(file, savePath, fileName, request);
    }
}
