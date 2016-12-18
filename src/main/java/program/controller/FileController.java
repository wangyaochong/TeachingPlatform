package program.controller;

import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import program.controller.util.FileUtil;
import program.controller.util.ResponseFlag;
import program.controller.util.ResponseInfo;
import program.dao.GenericDao;
import program.entity.FileEntity;
import program.entity.ItemEntity;
import program.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by【王耀冲】on 【2016/12/17】 at 【21:54】.
 */
@Component
@RequestMapping("/File")
public class FileController {
    @Resource
    UserService userService;
    @Resource
    FileUtil fileUtil;
    @Resource(name = "GenericDao")
    GenericDao genericDao;

    @RequestMapping("/download")
    public void download(@RequestParam String id, @RequestParam(defaultValue = "true", required = false) Boolean isAttachment, HttpServletResponse response) {
        FileEntity fileEntity = genericDao.getSession().get(FileEntity.class, id);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        response.setContentLength(Math.toIntExact(fileEntity.getFileSize()));
        try {
            bis = new BufferedInputStream(new FileInputStream(fileEntity.getFilePath()));
            try {
                bos = new BufferedOutputStream(response.getOutputStream());
                byte[] buff = new byte[2048];
                while (bis.read(buff) != -1) {
                    bos.write(buff);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @RequestMapping("/uploadListFile")//针对一个form表单，有一个input file multiple类型
    @ResponseBody
    public ResponseInfo uploadList(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartRequest.getFiles("files");
        for (MultipartFile f : files) {
            String originalFilename = f.getOriginalFilename();
            long size = f.getSize();
            FileEntity fileEntity =
                    new FileEntity(userService.getCurrentUser(),
                            fileUtil.getCompleteRealFilePath(),
                            fileUtil.getCompleteContextFilePath(),
                            size, originalFilename, new Date().getTime());
            String saveId = (String) genericDao.getSession().save(fileEntity);
            fileEntity.setFilePath(fileEntity.getFilePath() + saveId + fileEntity.getOriginName());
            fileEntity.setHtmlAccessPath(fileEntity.getHtmlAccessPath() + saveId + fileEntity.getOriginName());
            genericDao.getSession().update(fileEntity);
            File file = new File(fileEntity.getFilePath());
            if (file.exists() == false) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                f.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseInfo(ResponseFlag.STATUS_OK, null, null);
    }

    @RequestMapping("/uploadMapFile")//这个是针对一个form表单中有多个input file类型
    @ResponseBody
    public ResponseInfo uploadMap(HttpServletRequest servletRequest) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) servletRequest;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> e : fileMap.entrySet()) {
            MultipartFile file = e.getValue();
            System.out.println("fileKey:" + e.getKey());
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            File uploadFile = new File("");
        }
        return new ResponseInfo(ResponseFlag.STATUS_OK, null, null);
    }

}
