package cn.day.test;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

/**
 * @author ZhengChangBing
 * @Date 2023/1/13 10:58
 * @Description
 */
public class FileTest {

    public HttpServletResponse download(String path, HttpServletResponse response) {

        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    public void downloadLocal(HttpServletResponse response) throws FileNotFoundException {

        // 下载本地文件
        String fileName = "Operator.doc".toString(); // 文件的默认保存名
        // 读到流中
        InputStream inStream = new FileInputStream("c:/Operator.doc");// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downloadNet(HttpServletResponse response) throws MalformedURLException {

        // 下载网络文件
        int bytesum = 0;
        int byteread = 0;
        URL url = new URL("windine.blogdriver.com/logo.gif");
        try {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream("c:/abc.gif");
            byte[] buffer = new byte[1204];
            int length;
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                System.out.println(bytesum);
                fs.write(buffer, 0, byteread);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downLoadFile(HttpServletResponse response, String httpUrl) {
        ServletOutputStream out = null;
        try {
            //与服务器建立连接
            URL url = new URL(httpUrl);
            URLConnection conn = url.openConnection();
            InputStream inputStream = conn.getInputStream();
            try {
                //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
                response.setContentType("multipart/form-data");
            } catch (Exception e){
                e.printStackTrace();
            }
            out = response.getOutputStream();
            // 读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
            out.close();
            inputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void downLoad(String filePath, HttpServletResponse response, boolean isOnLine) throws Exception {

        File f = new File(filePath);
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;
        response.reset(); // 非常重要
        if (isOnLine) { // 在线打开方式
            URL u = new URL("file:///" + filePath);
            response.setContentType(u.openConnection().getContentType());
            response.setHeader("Content-Disposition", "inline; filename=" + f.getName());
            // 文件名应该编码成UTF-8
        } else { // 纯下载方式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
        }
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
    }

    /**
     * 将文件转为base64
     */
    public static String getBase64FromFile(File file) throws IOException {
        FileInputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            int read = 0;
            byte[] buffer = new byte[1024];
            while ((read = in.read(buffer, 0, 1024)) != -1) {
                out.write(buffer, 0, read);
            }

            return Base64.getEncoder().encodeToString(out.toByteArray());
        } catch (IOException e) {
            throw e;
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null){
                out.close();
            }
        }
    }


    /**
     * 将MultipartFile转换为File
     */
    public static File MultipartFileToFile(MultipartFile multiFile) throws IOException {
        String fileName = multiFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        InputStream in = null;
        OutputStream out = null;
        try {
            File file = File.createTempFile(fileName, prefix);
            out = new FileOutputStream(file);
            in = multiFile.getInputStream();
            int read = 0;
            byte[] buffer = new byte[1024];
            while ((read = in.read(buffer, 0, 1024)) != -1) {
                out.write(buffer, 0, read);
            }

            return file;
        } catch (Exception e) {
            throw e;
        }finally {
            if (in != null){
                in.close();
            }
            if (out != null){
                out.close();
            }
        }
    }


}
