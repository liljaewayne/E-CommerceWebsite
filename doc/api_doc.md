# 模板

## 模板

/REPLACE/.do

###request

>template

###response

success

    template

fail

    template

________________________________________



#用户接口

##1.后台管理员登录

/manage/user/login.do

###request

>String username,
String password

####response

success

    {
         "status": 0,
         "data": {
             "id": 12,
             "username": "aaa",
             "email": "aaa@163.com",
             "phone": null,
             "role": 0,
             "createTime": 1479048325000,
             "updateTime": 1479048325000
         }
    }


fail

    {
        "status": 1,
        "msg": "密码错误"
    }



##2.用户列表

/manage/user/list.do

###request

>pageSize(default=10)
pageNum(default=1)

###response

success

    {
        "status": 0,
        "data": {
            "pageNum": 1,
            "pageSize": 3,
            "size": 3,
            "orderBy": null,
            "startRow": 1,
            "endRow": 3,
            "total": 16,
            "pages": 6,
            "list": [
                {
                    "id":1,
                    "username":"ljw",
                    "password":"",
                    "email":"asdf@commerce.com",
                    "phone":"15011111111",
                    "question":"qqqqq",
                    "answer":"qqqq",
                    "role":0,
                    "createTime":1489719093000,
                    "updateTime":1513682138000
                },
                {
                    "id":2,
                    "username":"zxcv",
                    "password":"",
                    "email":"zxcv@commerce.com",
                    "phone":"1502222",
                    "question":"wwww",
                    "answer":"wwwww",
                    "role":0,
                    "createTime":1489719093000,
                    "updateTime":1513682138000
                }
            ],
            "firstPage": 1,
            "prePage": 0,
            "nextPage": 2,
            "lastPage": 6,
            "isFirstPage": true,
            "isLastPage": false,
            "hasPreviousPage": false,
            "hasNextPage": true,
            "navigatePages": 8,
            "navigatepageNums": [
              1,
              2,
              3,
              4,
              5,
              6
            ]
        }
    }

fail


    {
      "status": 10,
      "msg": "用户未登录,请登录"
    }


或

    {
      "status": 1,
      "msg": "没有权限"
    }

________________________________________________________________________________

#后台_产品接口

##1.产品list

/manage/product/list.do

###request

>pageNum(default=1)
pageSize(default=10)

###response

success

    {
        "status": 0,
        "data": {
            "pageNum": 1,
            "pageSize": 10,
            "size": 2,
            "orderBy": null,
            "startRow": 1,
            "endRow": 2,
            "total": 2,
            "pages": 1,
            "list": [
                {
                    "id": 1,
                    "categoryId": 3,
                    "name": "iphone8",
                    "subtitle": "双十一促销",
                    "mainImage": "mainimage.jpg",
                    "status":1,
                    "price": 7199.22
                },
                {
                    "id": 2,
                    "categoryId": 2,
                    "name": "oppo R8",
                    "subtitle": "oppo促销进行中",
                    "mainImage": "mainimage.jpg",
                    "status":1,
                    "price": 2999.11
                }
            ],
            "firstPage": 1,
            "prePage": 0,
            "nextPage": 0,
            "lastPage": 1,
            "isFirstPage": true,
            "isLastPage": true,
            "hasPreviousPage": false,
            "hasNextPage": false,
            "navigatePages": 8,
            "navigatepageNums": [
                1
            ]
        }
    }

fail

    {
        "status": 10,
        "msg": "用户未登录,请登录"
    }


##2.产品搜索

/manage/product/search.do

###request

>productName
productId
pageNum(default=1)
pageSize(default=10)


###response

success

    {
        "status": 0,
        "data": {
            "pageNum": 1,
            "pageSize": 10,
            "size": 1,
            "orderBy": null,
            "startRow": 1,
            "endRow": 1,
            "total": 1,
            "pages": 1,
            "list": [
                {
                    "id": 1,
                    "categoryId": 3,
                    "name": "iphone7",
                    "subtitle": "双十一促销",
                    "mainImage": "mainimage.jpg",
                    "price": 7199.22
                }
            ],
            "firstPage": 1,
            "prePage": 0,
            "nextPage": 0,
            "lastPage": 1,
            "isFirstPage": true,
            "isLastPage": true,
            "hasPreviousPage": false,
            "hasNextPage": false,
            "navigatePages": 8,
            "navigatepageNums": [
                1
            ]
        }
    }
fail

    {
        "status": 10,
        "msg": "用户未登录,请登录"
    }

##3.图片上传

/manage/product/upload.do

###request

>upload_file(multipart/form-data)


###response

success

    {
        "status": 0,
        "data": {
            "uri": "e6604558-c0ff-41b9-b6e1-30787a1e3412.jpg",
            "url": "http://img.commerce.com/e6604558-c0ff-41b9-b6e1-30787a1e3412.jpg"
        }
    }

fail

status!=0的时候

###4.产品详情

/manage/product/detail.do

###request

>productId

###response

success

    {
        "status": 0,
        "data": {
            "id": 2,
            "categoryId": 2,
            "parentCategoryId":1,
            "name": "oppo R8",
            "subtitle": "oppo促销进行中",
            "imageHost": "http://img.commerce.com/",
            "mainImage": "mainimage.jpg",
            "subImages": "[\"commerce/aa.jpg\",\"commerce/bb.jpg\",\"commerce/cc.jpg\",\"commerce/dd.jpg\",\"commerce/ee.jpg\"]",
            "detail": "richtext",
            "price": 2999.11,
            "stock": 71,
            "status": 1,
            "createTime": "2016-11-20 14:21:53",
            "updateTime": "2016-11-20 14:21:53"
        }
    }

fail

    {
        "status": 1,
        "msg": "没有权限"
    }

####5.产品上下架

/manage/product/set_sale_status.do

###request

>productId
status

###response

success

    {
        "status": 0,
        "data": "修改产品状态成功"
    }

fail

    {
        "status": 1,
        "data": "修改产品状态失败"
    }
    

####6.新增OR更新产品

新增 
http://localhost:8080/manage/product/save.do?categoryId=1&name=三星洗衣机&subtitle=三星大促销&subImages=test.jpg,11.jpg,2.jpg,3.jpg&detail=detailtext&price=1000&stock=100&status=1

更新 
http://localhost:8080/manage/product/save.do?categoryId=1&name=三星洗衣机&subtitle=三星大促销&subImages=test.jpg&detail=detailtext&price=1000&stock=100&status=1&id=3

/manage/product/save.do

###request

>categoryId=1
name=三星洗衣机
subtitle=三星大促销
&mainImage=sss.jpg
subImages=test.jpg
detail=detailtext
price=1000
stock=100
status=1
id=3


###response

success

    {
        "status": 0,
        "data": "更新产品成功"
    }

或


    {
        "status": 0,
        "data": "新增产品成功"
    }

fail

    {
        "status": 1,
        "data": "更新产品失败"
    }
####7.富文本上传图片

/manage/product/richtext_img_upload.do

###request

>upload_file(multipart/form-data)

###response

success

    {
        "file_path": "http://img.commerce.com/5fb239f2-0007-40c1-b8e6-0dc11b22779c.jpg",
        "msg": "上传成功",
        "success": true
    }

fail

    {
        "success": false,
        "msg": "error message",
        "file_path": "[real file path]"
    }

______________________________________

#后台_品类接口

##1.获取品类子节点(平级)

/manage/category/get_category.do

###request

>categoryId(default=0)

###response

success

    {
        "status": 0,
        "data": [
            {
                "id": 2,
                "parentId": 1,
                "name": "手机",
                "status": true,
                "sortOrder": 3,
                "createTime": 1479622913000,
                "updateTime": 1479622913000
            },
            {
                "id": 4,
                "parentId": 1,
                "name": "移动座机",
                "status": true,
                "sortOrder": 5,
                "createTime": 1480059936000,
                "updateTime": 1480491941000
            }
        ]
    }


fail

    {
        "status": 10,
        "msg": "用户未登录,请登录"
    }
或

    {
        "status": 1,
        "msg": "未找到该品类"
    }


##2.增加节点

/manage/category/add_category.do

###request

>parentId(default=0)
categoryName

###response

success

    {
        "status": 0,
        "msg": "添加品类成功"
    }
fail

    {
        "status":1,
        "msg": "添加品类失败"
    }

##3.修改品类名字

/manage/category/set_category_name.do

###request

>categoryId
categoryName

###response

success

    {
        "status": 0,
        "msg": "更新品类名字成功"
    }
fail

    {
        "status": 1,
        "msg": "更新品类名字失败"
    }
##4.获取当前分类id及递归子节点categoryId

/manage/category/get_deep_category.do

###request

>categoryId

###response

success

    {
        "status": 0,
        "data": [
            100009,
            100010,
            100001,
            100006,
            100007,
            100008
        ]
    }
fail

    {
        "status": 1,
        "msg": "无权限"
    }


_________________________________________

#门户_产品接口

##1.产品搜索及动态排序List

/product/list.do

###request

>categoryId
keyword
pageNum(default=1)
pageSize(default=10)
orderBy(default="")：排序参数：例如price_desc，price_asc

###response

success

    {
        "status": 0,
        "data": {
            "pageNum": 1,
            "pageSize": 10,
            "size": 2,
            "orderBy": null,
            "startRow": 1,
            "endRow": 2,
            "total": 2,
            "pages": 1,
            "list": [
                {
                    "id": 1,
                    "categoryId": 3,
                    "name": "iphone7",
                    "subtitle": "双十一促销",
                    "mainImage": "mainimage.jpg",
                    "status":1,
                    "price": 7199.22
                },
                {
                    "id": 2,
                    "categoryId": 2,
                    "name": "oppo R8",
                    "subtitle": "oppo促销进行中",
                    "mainImage": "mainimage.jpg",
                    "status":1,
                    "price": 2999.11
                }
            ],
            "firstPage": 1,
            "prePage": 0,
            "nextPage": 0,
            "lastPage": 1,
            "isFirstPage": true,
            "isLastPage": true,
            "hasPreviousPage": false,
            "hasNextPage": false,
            "navigatePages": 8,
            "navigatepageNums": [
                1
            ]
        }
    }
fail

    {
        "status": 1,
        "msg": "参数错误"
    }
    
##2.产品detail

/product/detail.do


###request

>productId
response

###success

    {
      "status": 0,
      "data": {
        "id": 2,
        "categoryId": 2,
        "name": "oppo R8",
        "subtitle": "oppo促销进行中",
        "mainImage": "mainimage.jpg",
        "subImages": "[\"commerce/aa.jpg\",\"commerce/bb.jpg\",\"commerce/cc.jpg\",\"commerce/dd.jpg\",\"commerce/ee.jpg\"]",
        "detail": "richtext",
        "price": 2999.11,
        "stock": 71,
        "status": 1,
        "createTime": "2016-11-20 14:21:53",
        "updateTime": "2016-11-20 14:21:53"
      }
    }

fail

    {
        "status": 1,
        "msg": "该商品已下架或删除"
    }


_______________________________________

#门户_支付接口

##1.支付

/order/pay.do


###request

>orderNo
response

success

    {
        "status": 0,
        "data": {
            "orderNo": "1485158676346",
            "qrPath": "http://img.commerce.com/qr-1492329044075.png"
        }
    }
fail

    {
        "status": 1,
        "msg": "支付宝生成订单失败"
    }

##2.查询订单支付状态

/order/query_order_pay_status.do

###request

>orderNo
response

success

    {
        "status": 0,
        "data": true
    }


fail

    {
        "status": 1,
        "msg": "该用户并没有该订单,查询无效"
    }
##3.支付宝回调

>支付宝回调文档： https://support.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.mFogPC&treeId=193&articleId=103296&docType=1

/order/alipay_callback.do

###request

>HttpServletRequest
response

success

    success
    fail

failed




________________________________________

##1.添加地址

/shipping/add.do


###request

>userId=1
receiverName=geely
receiverPhone=010
receiverMobile=18688888888
receiverProvince=北京
receiverCity=北京市
receiverAddress=中关村
receiverZip=100000

###response

success

    {
        "status": 0,
        "msg": "新建地址成功",
        "data": {
            "shippingId": 28
        }
    }
fail

    {
        "status": 1,
        "msg": "新建地址失败"
    }

##2.删除地址

/shipping/del.do

###request

>shippingId
response

success

    {
        "status": 0,
        "msg": "删除地址成功"
    }
fail

    {
        "status": 1,
        "msg": "删除地址失败"
    }


##3.登录状态更新地址

/shipping/update.do

###request

>id=1
receiverName=geely
receiverPhone=010
receiverMobile=18688888888
receiverProvince=北京
receiverCity=北京市
receiverAddress=中关村
receiverZip=100000
response

success

    {
        "status": 0,
        "msg": "更新地址成功"
    }
fail

    {
        "status": 1,
        "msg": "更新地址失败"
    }

##4.选中查看具体的地址

/shipping/select.do

###request

>shippingId
response

success

    {
        "status": 0,
        "data": {
            "id": 4,
            "userId": 13,
            "receiverName": "geely",
            "receiverPhone": "010",
            "receiverMobile": "18688888888",
            "receiverProvince": "北京",
            "receiverCity": "北京市",
            "receiverAddress": "中关村",
            "receiverZip": "100000",
            "createTime": 1485066385000,
            "updateTime": 1485066385000
        }
    }
fail

    {
        "status": 1,
        "msg": "请登录之后查询"
    }

##5.地址列表

/shipping/list.do


###request

>pageNum(默认1),pageSize(默认10)

###response

success

    {
        "status": 0,
        "data": {
            "pageNum": 1,
            "pageSize": 10,
            "size": 2,
            "orderBy": null,
            "startRow": 1,
            "endRow": 2,
            "total": 2,
            "pages": 1,
            "list": [
                {
                    "id": 4,
                    "userId": 13,
                    "receiverName": "geely",
                    "receiverPhone": "010",
                    "receiverMobile": "18688888888",
                    "receiverProvince": "北京",
                    "receiverCity": "北京市",
                    "receiverAddress": "中关村",
                    "receiverZip": "100000",
                    "createTime": 1485066385000,
                    "updateTime": 1485066385000
                },
                {
                    "id": 5,
                    "userId": 13,
                    "receiverName": "AAA",
                    "receiverPhone": "010",
                    "receiverMobile": "18688888888",
                    "receiverProvince": "北京",
                    "receiverCity": "北京市",
                    "receiverAddress": "中关村",
                    "receiverZip": "100000",
                    "createTime": 1485066392000,
                    "updateTime": 1485075875000
                }
            ],
            "firstPage": 1,
            "prePage": 0,
            "nextPage": 0,
            "lastPage": 1,
            "isFirstPage": true,
            "isLastPage": true,
            "hasPreviousPage": false,
            "hasNextPage": false,
            "navigatePages": 8,
            "navigatepageNums": [
                1
            ]
        }
    }
    
fail

    {
        "status": 1,
        "msg": "请登录之后查询"
    }


________________________________________

#门户_用户接口

##1.登录

/user/login.do post,开放get，方便调试

###request

>username,password

###response

success

    {
        "status": 0,
        "data": {
            "id": 12,
            "username": "aaa",
            "email": "aaa@163.com",
            "phone": null,
            "role": 0,
            "createTime": 1479048325000,
            "updateTime": 1479048325000
        }
    }

fail

    {
        "status": 1,
        "msg": "密码错误"
    }

##2.注册 

/user/register.do

###request

>username,password,email,phone,question,answer
response

success

    {
        "status": 0,
        "msg": "校验成功"
    }
    
fail

    {
        "status": 1,
        "msg": "用户已存在"
    }


##3.检查用户名是否有效

/user/check_valid.do

###request

>str,type
str可以是用户名也可以是email。对应的type是username和email

###response

success

    {
        "status": 0,
        "msg": "校验成功"
    }

fail

    {
        "status": 1,
        "msg": "用户已存在"
    }

##4.获取登录用户信息 

/user/get_user_info.do

###request

无参数

###response

success

    {
        "status": 0,
        "data": {
            "id": 12,
            "username": "aaa",
            "email": "aaa@163.com",
            "phone": null,
            "role": 0,
            "createTime": 1479048325000,
            "updateTime": 1479048325000
        }
    }
fail

    {
        "status": 1,
        "msg": "用户未登录,无法获取当前用户信息"
    }

##5.忘记密码 
/user/forget_get_question.do

###request

>username
response

success

    {
        "status": 0,
        "data": "这里是问题"
    }
fail

    {
        "status": 1,
        "msg": "该用户未设置找回密码问题"
    }


##6.提交问题答案 

/user/forget_check_answer.do

###request

>username,question,answer

###response

    正确的返回值里面有一个token，修改密码的时候需要用这个。传递给下一个接口

success

    {
        "status": 0,
        "data": "531ef4b4-9663-4e6d-9a20-fb56367446a5"
    }
fail

    {
        "status": 1,
        "msg": "问题答案错误"
    }

##7.忘记密码的重设密码 
/user/forget_reset_password.do

###request

>username,passwordNew,forgetToken

response

success

    {
        "status": 0,
        "msg": "修改密码成功"
    }
fail

    {
        "status": 1,
        "msg": "修改密码操作失效"
    }
或

    {
        "status": 1,
        "msg": "token已经失效"
    }

##8.登录中状态重置密码 

/user/reset_password.do

###request

>passwordOld,passwordNew

###response

success

    {
        "status": 0,
        "msg": "修改密码成功"
    }
fail

    {
        "status": 1,
        "msg": "旧密码输入错误"
    }


##9.登录状态更新个人信息 

/user/update_information.do

###request

>email,phone,question,answer

###response

success

    {
        "status": 0,
        "msg": "更新个人信息成功"
    }
fail

    {
        "status": 1,
        "msg": "用户未登录"
    }


##10.获取当前登录用户的详细信息，并强制登录 

/user/get_information.do

###request

>无参数

###response

success

    {
        "status": 0,
        "data": {
            "id": 1,
            "username": "admin",
            "password": "",
            "email": "admin@163.com",
            "phone": "13800138000",
            "question": "question",
            "answer": "answer",
            "role": 1,
            "createTime": 1478422605000,
            "updateTime": 1491305256000
        }
    }
fail

    {
        "status": 10,
        "msg": "用户未登录,无法获取当前用户信息,status=10,强制登录"
    }


##11.退出登录 

/user/logout.do

###request

>无

response

success

    {
        "status": 0,
        "msg": "退出成功"
    }
fail

    {
        "status": 1,
        "msg": "服务端异常"
    }

______________________________________

##1.创建订单

/order/create.do

###request

>shippingId

###response

success

    {
        "status": 0,
        "data": {
            "orderNo": 1485158223095,
            "payment": 2999.11,
            "paymentType": 1,
            "postage": 0,
            "status": 10,
            "paymentTime": null,
            "sendTime": null,
            "endTime": null,
            "closeTime": null,
            "createTime": 1485158223095,
            "orderItemVoList": [
                {
                    "orderNo": 1485158223095,
                    "productId": 2,
                    "productName": "oppo R8",
                    "productImage": "mainimage.jpg",
                    "currentUnitPrice": 2999.11,
                    "quantity": 1,
                    "totalPrice": 2999.11,
                    "createTime": null
                }
            ],
            "shippingId": 5,
            "shippingVo": null
        }
    }
fail

    {
        "status": 1,
        "msg": "创建订单失败"
    }


##2.获取订单的商品信息

/order/get_order_cart_product.do

###request

>无

###response

success

    {
        "status": 0,
        "data": {
            "orderItemVoList": [
                {
                    "orderNo": null,
                    "productId": 1,
                    "productName": "iphone7",
                    "productImage": "commerce/aa.jpg",
                    "currentUnitPrice": 7999,
                    "quantity": 10,
                    "totalPrice": 79990,
                    "createTime": ""
                }
            ],
            "imageHost": "http://img.commerce.com/",
            "productTotalPrice": 79990
        }
    }
fail

    {
        "status": 1,
        "msg": "用户未登录"
    }
####3.订单List

/order/list.do

###request

>pageSize(default=10)
pageNum(default=1)

###response

success

    {
      "status": 0,
      "data": {
        "pageNum": 1,
        "pageSize": 3,
        "size": 3,
        "orderBy": null,
        "startRow": 1,
        "endRow": 3,
        "total": 16,
        "pages": 6,
        "list": [
          {
            "orderNo": 1485158676346,
            "payment": 2999.11,
            "paymentType": 1,
            "paymentTypeDesc": "在线支付",
            "postage": 0,
            "status": 10,
            "statusDesc": "未支付",
            "paymentTime": "2017-02-11 12:27:18",
            "sendTime": "2017-02-11 12:27:18",
            "endTime": "2017-02-11 12:27:18",
            "closeTime": "2017-02-11 12:27:18",
            "createTime": "2017-01-23 16:04:36",
            "orderItemVoList": [
              {
                "orderNo": 1485158676346,
                "productId": 2,
                "productName": "oppo R8",
                "productImage": "mainimage.jpg",
                "currentUnitPrice": 2999.11,
                "quantity": 1,
                "totalPrice": 2999.11,
                "createTime": "2017-01-23 16:04:36"
              }
            ],
            "imageHost": "http://img.commerce.com/",
            "shippingId": 5,
            "receiverName": "geely",
            "shippingVo": null
          },
          {
            "orderNo": 1485158675516,
            "payment": 2999.11,
            "paymentType": 1,
            "paymentTypeDesc": "在线支付",
            "postage": 0,
            "status": 10,
            "statusDesc": "未支付",
            "paymentTime": "2017-02-11 12:27:18",
            "sendTime": "2017-02-11 12:27:18",
            "endTime": "2017-02-11 12:27:18",
            "closeTime": "2017-02-11 12:27:18",
            "createTime": "2017-01-23 16:04:35",
            "orderItemVoList": [
              {
                "orderNo": 1485158675516,
                "productId": 2,
                "productName": "oppo R8",
                "productImage": "mainimage.jpg",
                "currentUnitPrice": 2999.11,
                "quantity": 1,
                "totalPrice": 2999.11,
                "createTime": "2017-01-23 16:04:35"
              }
            ],
            "imageHost": "http://img.commerce.com/",
            "shippingId": 5,
            "receiverName": "geely",
            "shippingVo": null
          },
          {
            "orderNo": 1485158675316,
            "payment": 2999.11,
            "paymentType": 1,
            "paymentTypeDesc": "在线支付",
            "postage": 0,
            "status": 10,
            "statusDesc": "未支付",
            "paymentTime": "2017-02-11 12:27:18",
            "sendTime": "2017-02-11 12:27:18",
            "endTime": "2017-02-11 12:27:18",
            "closeTime": "2017-02-11 12:27:18",
            "createTime": "2017-01-23 16:04:35",
            "orderItemVoList": [
              {
                "orderNo": 1485158675316,
                "productId": 2,
                "productName": "oppo R8",
                "productImage": "mainimage.jpg",
                "currentUnitPrice": 2999.11,
                "quantity": 1,
                "totalPrice": 2999.11,
                "createTime": "2017-01-23 16:04:35"
              }
            ],
            "imageHost": "http://img.commerce.com/",
            "shippingId": 5,
            "receiverName": "geely",
            "shippingVo": null
          }
        ],
        "firstPage": 1,
        "prePage": 0,
        "nextPage": 2,
        "lastPage": 6,
        "isFirstPage": true,
        "isLastPage": false,
        "hasPreviousPage": false,
        "hasNextPage": true,
        "navigatePages": 8,
        "navigatepageNums": [
          1,
          2,
          3,
          4,
          5,
          6
        ]
      }
    }
fail

    {
      "status": 10,
      "msg": "用户未登录,请登录"
    }


或

    {
      "status": 1,
      "msg": "没有权限"
    }



##4.订单详情detail


/order/detail.do

###request

>orderNo

###response

success

    {
      "status": 0,
      "data": {
        "orderNo": 1480515829406,
        "payment": 30000.00,
        "paymentType": 1,
        "paymentTypeDesc": "在线支付",
        "postage": 0,
        "status": 10,
        "statusDesc": "未支付",
        "paymentTime": "",
        "sendTime": "",
        "endTime": "",
        "closeTime": "",
        "createTime": "2016-11-30 22:23:49",
        "orderItemVoList": [
          {
            "orderNo": 1480515829406,
            "productId": 1,
            "productName": "iphone7",
            "productImage": "mainimage.jpg",
            "currentUnitPrice": 10000.00,
            "quantity": 1,
            "totalPrice": 10000.00,
            "createTime": "2016-11-30 22:23:49"
          },
          {
            "orderNo": 1480515829406,
            "productId": 2,
            "productName": "oppo R8",
            "productImage": "mainimage.jpg",
            "currentUnitPrice": 20000.00,
            "quantity": 1,
            "totalPrice": 20000.00,
            "createTime": "2016-11-30 22:23:49"
          }
        ],
        "imageHost": "http://img.commerce.com/",
        "shippingId": 3,
        "receiverName": "geely",
        "shippingVo": {
          "receiverName": "geely",
          "receiverPhone": "0100",
          "receiverMobile": "186",
          "receiverProvince": "北京",
          "receiverCity": "北京",
          "receiverDistrict": "昌平区",
          "receiverAddress": "矩阵小区",
          "receiverZip": "100000"
        }
      }
    }

fail

    {
      "status": 1,
      "msg": "没有找到订单"
    }

##5.取消订单


/order/cancel.do

###request

>orderNo

###response

success

    {
      "status": 0
    }

fail

    {
      "status": 1,
      "msg": "该用户没有此订单"
    }
或

    {
        "status": 1,
        "msg": "此订单已付款，无法被取消"
    }


______________

#门户_购物车接口


##1.购物车List列表

/cart/list.do


###request

>无参数,需要登录状态

###response

success

    {
        "status": 0,
        "data": {
            "cartProductVoList": [
                {
                    "id": 1,
                    "userId": 13,
                    "productId": 1,
                    "quantity": 1,
                    "productName": "iphone7",
                    "productSubtitle": "双十一促销",
                    "productMainImage": "mainimage.jpg",
                    "productPrice": 7199.22,
                    "productStatus": 1,
                    "productTotalPrice": 7199.22,
                    "productStock": 86,
                    "productChecked": 1,
                    "limitQuantity": "LIMIT_NUM_SUCCESS"
                },
                {
                    "id": 2,
                    "userId": 13,
                    "productId": 2,
                    "quantity": 1,
                    "productName": "oppo R8",
                    "productSubtitle": "oppo促销进行中",
                    "productMainImage": "mainimage.jpg",
                    "productPrice": 2999.11,
                    "productStatus": 1,
                    "productTotalPrice": 2999.11,
                    "productStock": 86,
                    "productChecked": 1,
                    "limitQuantity": "LIMIT_NUM_SUCCESS"
                }
            ],
            "allChecked": true,
            "cartTotalPrice": 10198.33
        }
    }

fail

    {
        "status": 10,
        "msg": "用户未登录,请登录"
    }
    
##2.购物车添加商品

/cart/add.do

###request

>productId,count

###response

success

    {
        "status": 0,
        "data": {
            "cartProductVoList": [
                {
                    "id": 1,
                    "userId": 13,
                    "productId": 1,
                    "quantity": 12,
                    "productName": "iphone7",
                    "productSubtitle": "双十一促销",
                    "productMainImage": "mainimage.jpg",
                    "productPrice": 7199.22,
                    "productStatus": 1,
                    "productTotalPrice": 86390.64,
                    "productStock": 86,
                    "productChecked": 1,
                    "limitQuantity": "LIMIT_NUM_SUCCESS"
                },
                {
                    "id": 2,
                    "userId": 13,
                    "productId": 2,
                    "quantity": 1,
                    "productName": "oppo R8",
                    "productSubtitle": "oppo促销进行中",
                    "productMainImage": "mainimage.jpg",
                    "productPrice": 2999.11,
                    "productStatus": 1,
                    "productTotalPrice": 2999.11,
                    "productStock": 86,
                    "productChecked": 1,
                    "limitQuantity": "LIMIT_NUM_SUCCESS"
                }
            ],
            "allChecked": true,
            "cartTotalPrice": 89389.75
        }
    }

fail

    {
        "status": 10,
        "msg": "用户未登录,请登录"
    }

##3.更新购物车某个产品数量

/cart/update.do

###request

>productId,count

###response

success

    {
        "status": 0,
        "data": {
            "cartProductVoList": [
                {
                    "id": 1,
                    "userId": 13,
                    "productId": 1,
                    "quantity": 12,
                    "productName": "iphone7",
                    "productSubtitle": "双十一促销",
                    "productMainImage": "mainimage.jpg",
                    "productPrice": 7199.22,
                    "productStatus": 1,
                    "productTotalPrice": 86390.64,
                    "productStock": 86,
                    "productChecked": 1,
                    "limitQuantity": "LIMIT_NUM_SUCCESS"
                },
                {
                    "id": 2,
                    "userId": 13,
                    "productId": 2,
                    "quantity": 1,
                    "productName": "oppo R8",
                    "productSubtitle": "oppo促销进行中",
                    "productMainImage": "mainimage.jpg",
                    "productPrice": 2999.11,
                    "productStatus": 1,
                    "productTotalPrice": 2999.11,
                    "productStock": 86,
                    "productChecked": 1,
                    "limitQuantity": "LIMIT_NUM_SUCCESS"
                }
            ],
            "allChecked": true,
            "cartTotalPrice": 89389.75
        }
    }
fail

    {
        "status": 10,
        "msg": "用户未登录,请登录"
    }

##4.移除购物车某个产品

/cart/delete_product.do


###request

>productIds

###response

success

    {
        "status": 0,
        "data": {
            "cartProductVoList": [
                {
                    "id": 2,
                    "userId": 13,
                    "productId": 2,
                    "quantity": 1,
                    "productName": "oppo R8",
                    "productSubtitle": "oppo促销进行中",
                    "productMainImage": "mainimage.jpg",
                    "productPrice": 2999.11,
                    "productStatus": 1,
                    "productTotalPrice": 2999.11,
                    "productStock": 86,
                    "productChecked": 1,
                    "limitQuantity": "LIMIT_NUM_SUCCESS"
                }
            ],
            "allChecked": true,
            "cartTotalPrice": 2999.11
        }
    }
fail

    {
        "status": 10,
        "msg": "用户未登录,请登录"
    }

##5.购物车选中某个商品

/cart/select.do

###request

>productId

###response

success

    {
        "status": 0,
        "data": {
            "cartProductVoList": [
                {
                    "id": 2,
                    "userId": 13,
                    "productId": 2,
                    "quantity": 1,
                    "productName": "oppo R8",
                    "productSubtitle": "oppo促销进行中",
                    "productMainImage": "mainimage.jpg",
                    "productPrice": 2999.11,
                    "productStatus": 1,
                    "productTotalPrice": 2999.11,
                    "productStock": 86,
                    "productChecked": 1,
                    "limitQuantity": "LIMIT_NUM_SUCCESS"
                }
            ],
            "allChecked": true,
            "cartTotalPrice": 2999.11
        }
    }
fail

    {
        "status": 10,
        "msg": "用户未登录,请登录"
    }

##6.购物车取消选中某个商品

/cart/un_select.do

###request

>productId

###response

success

    {
        "status": 0,
        "data": {
            "cartProductVoList": [
                {
                    "id": 2,
                    "userId": 13,
                    "productId": 2,
                    "quantity": 1,
                    "productName": "oppo R8",
                    "productSubtitle": "oppo促销进行中",
                    "productMainImage": "mainimage.jpg",
                    "productPrice": 2999.11,
                    "productStatus": 1,
                    "productTotalPrice": 2999.11,
                    "productStock": 86,
                    "productChecked": 0,
                    "limitQuantity": "LIMIT_NUM_SUCCESS"
                }
            ],
            "allChecked": true,
            "cartTotalPrice": 0
        }
    }
fail

    {
        "status": 10,
        "msg": "用户未登录,请登录"
    }

##7.查询在购物车里的产品数量

/cart/get_cart_product_count.do


###request

>无

###response

success

    {
        "status": 0,
        "data": 0
        
    }
fail

    {
        "status": 10,
        "msg": "出现异常"
    }

##8.购物车全选

/cart/select_all.do


###request

>无

###response

success

    {
        "status": 0,
        "data": {
            "cartProductVoList": [
                {
                    "id": 2,
                    "userId": 13,
                    "productId": 2,
                    "quantity": 1,
                    "productName": "oppo R8",
                    "productSubtitle": "oppo促销进行中",
                    "productMainImage": "mainimage.jpg",
                    "productPrice": 2999.11,
                    "productStatus": 1,
                    "productTotalPrice": 2999.11,
                    "productStock": 86,
                    "productChecked": 0,
                    "limitQuantity": "LIMIT_NUM_SUCCESS"
                }
            ],
            "allChecked": true,
            "cartTotalPrice": 2999.11
        }
    }
fail

    {
        "status": 10,
        "msg": "用户未登录,请登录"
    }

##9.购物车取消全选

/cart/un_select_all.do


###request

>无

###response

success

    {
        "status": 0,
        "data": {
            "cartProductVoList": [
                {
                    "id": 2,
                    "userId": 13,
                    "productId": 2,
                    "quantity": 1,
                    "productName": "oppo R8",
                    "productSubtitle": "oppo促销进行中",
                    "productMainImage": "mainimage.jpg",
                    "productPrice": 2999.11,
                    "productStatus": 1,
                    "productTotalPrice": 2999.11,
                    "productStock": 86,
                    "productChecked": 0,
                    "limitQuantity": "LIMIT_NUM_SUCCESS"
                }
            ],
            "allChecked": true,
            "cartTotalPrice": 0
        }
    }
    
fail

    {
        "status": 10,
        "msg": "用户未登录,请登录"
    }








