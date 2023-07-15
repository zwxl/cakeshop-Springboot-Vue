package com.cakeshopspringboot.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Author 张伟雄
 * @Date 2022/12/27
 **/
public class uploadUtils {
    //MultipartFile转换File
    public static File MultipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 若须要防止生成的临时文件重复,能够在文件名后添加随机码
        try {
            File file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            //返回的File文件
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
