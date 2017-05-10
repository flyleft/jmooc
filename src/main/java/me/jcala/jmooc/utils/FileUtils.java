package me.jcala.jmooc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Iterator;

import static org.springframework.util.StreamUtils.copy;


public class FileUtils {

    private static final Logger logger= LoggerFactory.getLogger(FileUtils.class);

    public static String uploadFile(HttpServletRequest request,FileType type,long crsId)
            {
        MultipartHttpServletRequest multipartRequest =
                (MultipartHttpServletRequest) request;
        Iterator<String> fileNames = multipartRequest.getFileNames();
        if (!fileNames.hasNext()){
            return null;
        }
        MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());

        //设置图片名称为currentTimeMillis+文件后缀
        String fileName = String.valueOf(System.currentTimeMillis()) + "." +
                getSuffix(multipartFile.getOriginalFilename());

        //图片存储路径为根路径/年月。比如user/jcala/xmarket/201608
        File path = new File(type.getHome()+ crsId);
        if (!path.exists()) {
            path.mkdirs();
        }

        //合成图片在服务器上的物理绝对路径
        File targetFile = new File(type.getHome() + crsId + File.separatorChar + fileName);
        //保存图片
                try {
                    multipartFile.transferTo(targetFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.warn("上传文件或者视频出错"+e.getMessage());
                }
                return type.getUrl() + crsId + "/" + fileName;
    }

    /**
     * 获取文件后缀
     */
    public   static String getSuffix(String fileName){
        String[] token = fileName.split("\\.");
        if (token.length>0){
            return token[token.length-1];
        }
        else {
            return "";
        }
    }

    /**
     * 读取文件为字节数组
     */
    public static byte[] readFileToByteArray(final File file) throws IOException {
        InputStream in = openInputStream(file);
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(in, output);
        return output.toByteArray();
    }
    private static FileInputStream openInputStream(final File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canRead()) {
                throw new IOException("File '" + file + "' cannot be read");
            }
        } else {
            throw new RuntimeException("File '" + file + "' does not exist");
        }
        return new FileInputStream(file);
    }

}
