package com.commerce.controller.backend;


import com.commerce.common.ServerResponse;
import com.commerce.pojo.Product;
import com.commerce.pojo.User;
import com.commerce.service.FileService;
import com.commerce.service.ProductService;
import com.commerce.service.UserService;
import com.commerce.util.CookieUtil;
import com.commerce.util.JsonUtil;
import com.commerce.util.PropertiesUtil;
import com.commerce.util.RedisPoolUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private FileService fileService;

    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse productSave(HttpServletRequest httpServletRequest, Product product) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        String userJson = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObj(userJson, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");

        }
        if (userService.checkAdminRole(user).isSuccess()) {
            //填充我们增加产品的业务逻辑
            return productService.saveOrUpdateProduct(product);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServerResponse setSaleStatus(HttpServletRequest httpServletRequest, Integer productId, Integer status) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        String userJson = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObj(userJson, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");

        }
        if (userService.checkAdminRole(user).isSuccess()) {
            return productService.setSaleStatus(productId, status);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse getDetail(HttpServletRequest httpServletRequest, Integer productId) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        String userJson = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObj(userJson, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");

        }
        if (userService.checkAdminRole(user).isSuccess()) {
            //填充业务
            return productService.manageProductDetail(productId);

        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(HttpServletRequest httpServletRequest, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        String userJson = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObj(userJson, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");

        }
        if (userService.checkAdminRole(user).isSuccess()) {
            //填充业务
            return productService.getProductList(pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse productSearch(HttpServletRequest httpServletRequest, String productName, Integer productId, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        String userJson = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObj(userJson, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");

        }
        if (userService.checkAdminRole(user).isSuccess()) {
            //填充业务
            return productService.searchProduct(productName, productId, pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(HttpServletRequest httpServletRequest,
                                 @RequestParam(value = "upload_file", required = false) MultipartFile file,
                                 HttpServletRequest request) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        String userJson = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObj(userJson, User.class);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ServerResponse.ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");
        }
        if (userService.checkAdminRole(user).isSuccess()) {
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = fileService.upload(file, path);
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;

            Map fileMap = Maps.newHashMap();
            fileMap.put("uri", targetFileName);
            fileMap.put("url", url);
            return ServerResponse.createBySuccess(fileMap);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }


    @RequestMapping("richtext_img_upload.do")
    @ResponseBody
    public Map richtextImgUpload(HttpServletRequest httpServletRequest, @RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        Map resultMap = Maps.newHashMap();
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        String userJson = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.stringToObj(userJson, User.class);
        if (user == null) {
            resultMap.put("success", false);
            resultMap.put("msg", "请登录管理员");
            return resultMap;
        }
        //富文本中对于返回值有自己的要求,我们使用是simditor所以按照simditor的要求进行返回
//        {
//            "success": true/false,
//                "msg": "error message", # optional
//            "file_path": "[real file path]"
//        }
        if (userService.checkAdminRole(user).isSuccess()) {
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = fileService.upload(file, path);
            if (StringUtils.isBlank(targetFileName)) {
                resultMap.put("success", false);
                resultMap.put("msg", "上传失败");
                return resultMap;
            }
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
            resultMap.put("success", true);
            resultMap.put("msg", "上传成功");
            resultMap.put("file_path", url);
            response.addHeader("Access-Control-Allow-Headers", "X-File-Name");
            return resultMap;
        } else {
            resultMap.put("success", false);
            resultMap.put("msg", "无权限操作");
            return resultMap;
        }
    }


}
