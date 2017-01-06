<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% request.getSession().setAttribute("count","1");%>
<html>
<jsp:include page="../common/common.jsp"></jsp:include>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<head lang="en">
<meta http-equiv="Content-type" content="text/html;charset=utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name = "format-detection" content = "telephone=no">
<meta name="msapplication-tap-highlight" content="no"/>

<title>梅里记忆</title>
<script src="${ctx }/common/js/jquery-1.7.2.js"></script>
<script src="${ctx }/common/js/jquery-form.js"></script>
<script>
var s = document.documentElement.clientWidth;
document.documentElement.style.fontSize = s/25+ "px";
</script>
<style>
input {-webkit-appearance:none; /*去除input默认样式*/}
input[type="submit"],
input[type="reset"],
input[type="button"],
input{-webkit-appearance:none;}
.file {
	width:80%;
	margin-left:10%;
    position: relative;
    display: inline-block;
    background: #2D9928;
/*     border: 1px solid #99D3F5; */
	borer:none;
    border-radius: 4px;
/*     padding: 4; */
    overflow: hidden;
/*     //color: #1E88C7; */
	color:#fff;
    text-decoration: none;
    text-indent: 0;
    height:2.5rem;
    line-height: 2.5rem;
    text-align:center;
    
}
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background: #FFF;
    border-color: #2D9928;
    color: #2D9928;
    text-decoration: none;
}
.active{
	background: #FFF;
    border-color: #2D9928;
    color: #2D9928;
    text-decoration: none;
}

.textarea {
	width:77.5%;
	margin-left:10%;
	border-radius:4px;
	border:1px solid #2D9928;
	padding:0.4rem 1%;
/* 	font-size:1rem ; */
	font-size:1rem;
	color:#977A5c;
}

.tel{
	width:77.5%;
	margin-left:10%;
	border:1px solid #2D9928;
	border-radius:4px;
	height:2.5rem;
    line-height: 2.5rem;
    text-align:center;
	font-size:1rem;
	color:#977A5c;
}
.yzm{
	width:80%;
	margin-left:18%;
	border:1px solid #2D9928;
	border-radius:4px;
	height:2.5rem;
    line-height: 2.5rem;
    text-align:center;
    font-size:1rem;
	color:#977A5c;
	
}
.getCode{
	width:40%;
	margin-left:5%;
	background:#2D9928;
	color:#fff;
	height:2.7rem;
	line-height:2.7rem;
	padding:0.6rem 0.5rem;
}

a { 
    display: block; 
    height: 2.7rem; 
    width: 68%; 
    line-height: 2.7rem; 
    text-align: center; 
    background: #2D9928; 
    color: #fff; 
    font-size: 14px; 
    font-weight: bold; 
    text-decoration: none; 
/*     padding-top: 3px;  */
}
a:hover { 
	background: #FFF;
    border-color: #2D9928;
    color: #2D9928;
}

.alert1,.alert2,.alert3,.alert4,.alert5,.alert6,.alert7,.alert8,.alert9,.alert10,.alert11,.alert12{line-height:2.1875rem;color:#fff;background:rgba(0,0,0,0.7);font-size:0.8125rem;
    position:absolute;left:50%;top:60%;text-align: center;border-radius:0.3rem;display:none;z-index:11;}
.alert1{width:16rem;margin-left:-8rem;}
.alert2,.alert6,.alert7,.alert8,.alert9,.alert10,.alert11,.alert12{width:10rem;margin-left:-5rem;}
.alert3{width:7rem;margin-left:-3.5rem;}
.alert4{width:16rem;margin-left:-8rem;}
.alert5{width:14rem;margin-left:-7rem;}
</style>
</head>

<body style="margin: 0 auto;font-family:'微软雅黑'">
    <img style="width:100%" src="http://oss.188jielan.net/jielanwx/jx/meili-memory/bg.jpg" />
	<form name="form" id="form" action="#" method="post" >
    <div style="position:absolute;top:34rem;width:100%;">
    	<a href="javascript:;" class="file" ><span id="tip">选择图片（大小不能超过5M）</span>
    		<input type="file" name="file" id="file" accept="image/*">
		</a>
    </div>
    <div style="position:absolute;top:38rem;width:100%;">
    	<textarea class="textarea" rows="4" name="descriptor"  placeholder="请用文字描述一下您的照片" ></textarea>
    </div>
    <div style="position:absolute;top:44.9rem;width:100%;">
    	<input class="tel" name="phone" id="phone" placeholder="请输入手机号码" />
    </div>
    
    <div style="position:absolute;top:49rem;width:100%;">
    	<div style="width:55%;float: left;">
    		<input class="yzm" name="code" placeholder="请输入验证码" />
    	</div>
    	<div style="width:45%;float: left;">
    		<a style="margin-left:1rem" id="getCode"> 获取验证码</a>
    	</div>
    </div>
    <div style="position:absolute;top:53rem;width:100%;">
    	<input type="submit" style="border:none;width:60%;margin-left:20%;height:2.5rem;line-height:2.5rem;background:#2D9928;color:#fff;font-size: 14px; border-radius:5px; font-weight: bold; font-family:'微软雅黑'" id="queren" value="确认提交"/>
    </div>
    <div style="position:absolute;top:57rem;width:100%;">
    	<a style="border:none;width:60%;margin-left:20%;height:2.5rem;line-height:2.5rem;background:#ea5118;color:#fff;font-size: 14px; border-radius:5px; font-weight: bold;" href="rule">参与须知</a>
    </div>
	</form>
    <p class="alert1">正在上传，请耐心等待...</p>
    <p class="alert3">上传成功</p>
</body>
<script type="text/javascript">
    var reg = /^1[3|4|5|7|8][0-9]\d{8}$/;

    /* 提示弹窗渐入渐出 */
    function alertFade(el){
        el.fadeIn('300');
        setTimeout(function(){
            el.fadeOut('300');
        },2000);
    }

	$(function(){
		$("#file").change(function() {
				$("#tip").text("图片已选择");
				$(".file").addClass("active");
		});

        var flag = true;
        $("#getCode").click(function(){
            if(flag){
                flag = false;
                var phone = $("#phone").val();
                if(!reg.test(phone)){
                    alert("请输入正确的手机号码！");
                    flag = true;
                    return;
                }
                resetCode();
                $.ajax({
                    type:'post',
                    url:'sendSms',
                    data:{phone:phone},
                    dataType:'json',
                    success:function (rs) {
                        alert(rs.message);
                    }
                });
            }

        });


        function resetCode(){
            //$('.phone_bd .get').addClass('hide');
            $("#getCode").html('60s');
            //$('.get_again').removeClass('hide');
            var second = 60;
            var timer = null;
            timer = setInterval(function(){
                second -= 1;
                if(second >0){
                    $("#getCode").html(second+"s");
                }else{
                    clearInterval(timer);
                    $("#getCode").html("获取验证码");
                    //$('.phone_bd .get').removeClass('hide');
                    //$('.get_again').addClass('hide');
                }
            },1000);
        }

        $("#form").click(function () {
            var options = {
                url:"upLoad",
                dataType:"json",
                beforeSubmit:validated,
                success:function(data) {
                    $('.alert12').hide();
                    if (data.result == 'phone-error'){
                        $(".alert3").text("请输入正确的手机号码");
                        alertFade($('.alert3'));
                    }else if(data.result == 'describe-empty'){
                        $(".alert3").text("描述不能为空");
                        alertFade($('.alert3'));
                    }else if(data.result == 'file-empty'){
                        $(".alert3").text("请输入正确的手机号码");
                        alertFade($('.alert3'));
                    }else if(data.result == 'code-empty'){
                        $(".alert3").text("验证码不能为空");
                        alertFade($('.alert3'));
                    }else if(data.result == 'code-overtime'){
                        $(".alert3").text("验证码超时，请重新获取");
                        alertFade($('.alert3'));
                    }else if(data.result == 'code-error'){
                        $(".alert3").text("验证码有误");
                        alertFade($('.alert3'));
                    }else if(data.result == 'over'){
                        $(".alert3").text("每人只能上传两次哦");
                        alertFade($('.alert3'));
                    }else if(data.result == 'ex'){
                        $(".alert3").text("服务器开小差了");
                        alertFade($('.alert3'));
                    }else if(data.result == 'success'){
                        $(".alert3").text("上传成功");
                        alertFade($('.alert3'));
                    }else if(data.result == 'failed'){
                        $(".alert3").text("上传失败");
                        alertFade($('.alert3'));
                    }
                },
                error:function () {
                    alert("图片大小不能超过5M");
                    //$('.alert12').hide();
                }

            };
            $("#form").ajaxForm(options);
        });


        function validated(formData,jqForm,options){
            // var queryString = $.param(formData);
            var form=jqForm[0];
            if(form.file.value == ''){
                alert("您还未选择图片！");
                return false;
            }
            if(form.descriptor.value == ''){
                alert("描述不能为空！");
                return false;
            }
            if(!reg.test(form.phone.value)){
                alert("手机号码不能为空！");
                return false;
            }
            if(form.code.value == ''){
                alert("验证码不能为空！");
                return false;
            }
            $('.alert1').show();
            return true;
        }

	});
</script>
</html>