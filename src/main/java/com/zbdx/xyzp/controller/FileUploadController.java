package com.zbdx.xyzp.controller;

import com.zbdx.xyzp.model.entity.JianLi;
import com.zbdx.xyzp.service.JianliService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
@Controller
@RequestMapping("/file")
@CrossOrigin("http://localhost:8081")
public class FileUploadController {

    @Autowired
    private JianliService jianliService;

    @PostMapping("/upload")
    @ResponseBody
    public String uploadFile(MultipartFile file, HttpServletRequest request) {
        try {
            String fileDir = "F:" + File.separator + "upload";
            System.out.println("------->>" + fileDir);
            File dir = new File(fileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = file.getOriginalFilename();
            File upload_file = new File(fileDir + File.separator + fileName);

            file.transferTo(upload_file);
            JianLi jianLi = new JianLi();
            jianLi.setFileUrl(fileDir);
            jianLi.setFileName(fileName);
            jianliService.save(jianLi);
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }

    @GetMapping("/download")
    @ApiOperation("下载文件")
    public void downloadFile(String fileName , HttpServletResponse  response) throws UnsupportedEncodingException {
        String filePath =  "F:" + File.separator + "upload";
        File file = new File(filePath + "/" + fileName);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName,"UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download---" + fileName);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
