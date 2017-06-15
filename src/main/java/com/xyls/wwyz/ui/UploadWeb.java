package com.xyls.wwyz.ui;

import com.xyls.wwyz.responseModel.base.JsonResult;
import com.xyls.wwyz.responseModel.base.JsonResultData;
import com.xyls.wwyz.responseModel.base.JsonResultError;
import com.xyls.wwyz.responseModel.goods.FileUploadVM;
import com.xyls.wwyz.utils.EmailSendUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Lee on 2017/6/12.
 */
@Api(value = "文件上传",description = "文件上传接口")
@Controller
@RequestMapping("uploadweb")
public class UploadWeb extends BaseWeb {

    @Autowired
    private EmailSendUtil emailSendUtil;

    @Value("${project.static.server}")
    private String fileServer;

    public boolean validFile(String fileSuf){
        String regStr = ".bmp.jpg.jpeg.gif.png";
        if(regStr.indexOf(fileSuf) != -1){
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping("upload")
    @ResponseBody
    public JsonResult upload(@RequestParam("filename") MultipartFile filename) {

        boolean flag = false;

        String tfileName = getFilename();

        //获得后缀名
        String oriFileName = filename.getOriginalFilename();
        String suffix = oriFileName.substring(oriFileName.lastIndexOf("."));

        //校验文件格式
        flag = validFile(suffix);
        if(!flag){
            return new JsonResultError("文件格式不正确");
        }

//        String realPath = fileServer+"/image/"+tfileName + suffix;
        //文件的绝对路径
        String realPath = "D:"+ File.separator + "image" + File.separator + tfileName + suffix;
        String relativePath = "/image/"+tfileName + suffix;

        File file = new File(realPath);
        //检验合法性
        BufferedImage image = null;
        try {
            filename.transferTo(file);
            image = ImageIO.read(file);
            flag = true;
        } catch (IOException e) {
        }

        if (!flag) {
            // 不合法，将磁盘上的文件删除
            file.delete();
            return new JsonResultError("文件非法，请重试");
        }

        return new JsonResultData<FileUploadVM>(new FileUploadVM(realPath, fileServer+relativePath));

    }

    public String getFilename(){
        //获得当前时间的最小精度
        String tfileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        Random random = new Random();
        for(int i = 0; i < 3; i++){
            tfileName = tfileName + random.nextInt(10);
        }
        return tfileName;
    }

    @RequestMapping("sendMsg")
    public void sendMsg(){
        String[] to = {"675785015@qq.com"};

        emailSendUtil.sender(to,"测试","收到一封信",false);
    }
}
