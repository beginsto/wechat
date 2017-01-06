var phone;
var reg = /^1[3|4|5|8|7]\d{9}$/;
/********** 点击灰色背景 *********/
var oMask = $('.mask');
/*oMask.click(function(){
    if(event.target==this){
        oMask.hide();
    }
});*/

$('.know').click(function(){
   oMask.hide();
});
$('.close').click(function(){
    oMask.hide();
});

$("#login_close").click(function () {
    oMask.children('.login').hide();
    oMask.hide();
});

$(".old").click(function () {
    oMask.children('.old').hide();
    oMask.hide();
});

$(".succ").click(function () {
    window.location.href=updateUrl("index?openid="+$("#openid").val());
});

/********* 好友打开页面 **********/
$('.liuliang_f').click(function(){
    oMask.show();
});
$('.lingqu_f').click(function(){
    oMask.show();
});

/********* 点击推荐好友 **********/


var reg = /^1[3|4|5|7|8][0-9]\d{8}$/;
/********* 新用户领取100M流量 **********/
$('.btn_2').click(function(){
    var isbind = 0 ;
    oMask.show().children().hide();
    var num = $("#num").val();
   if(reg.test(num)){
       isbind = 1;
   }
    if(isbind){ //已绑定时弹出老用户窗口
        oMask.children('.old').show();
    }else{      //未绑定时弹出绑定窗口
        oMask.children('.login').show();
    }

});

$("#share").click(function () {
    var num = $("#num").val();
    if(num == null || num== '' || num == 'null'){
        alert("您还未绑定手机号码，请先绑定再来邀请好友！");
    }else{
        window.location.href="share?openid="+$("#openid").val();
    }
});


/********* 活动规则 **********/
$('.btn_4').click(function(){
    oMask.show().children().hide();
    oMask.children('.rules').show();
});

var flag = true;
/********* 登录点击提交 **********/
$('#send').click(function(){
    if(flag){
        flag = false;
        if(!reg.test(phone)){
            alert("请先获取验证码！");
            flag=true;
            return;
        }
        var code = $("#yzm").val();
        if( code == ''){
            alert("验证码不能为空！");
            flag=true;
            return;
        }
        if(code.length != 6){
            alert("验证码必须是6位数字");
            flag = true;
            return;
        }
        $.ajax({
            type:'post',
            url:'bind',
            data:{phone:phone,code:code,openid:$("#openid").val()},
            dataType:'json',
            success:function (rs) {
                if(rs.result == 'success'){
                    oMask.children('.login').hide();
                    oMask.children('.succ').show();
                }else if(rs.result == 'ex'){
                    alert("服务器开小差了！");
                }else if(rs.result == 'time-over'){
                    alert("验证码超时，请重新获取！");
                }else if(rs.result == 'code-error'){
                    alert("验证码有误！");
                }
                flag = true;
            }
        });
    }
});


/********* 载入后判断头像的位置 *******/
var invited = $("#invited").val();;    //该账号已邀请人数
var lingqu = 0 ;    //是否已领取
(function(){

    $('.num span').html(invited);
    if( invited >= 5 && invited<8 ){
        $('.lingqu').addClass('keling').html('已获得500MB');
    }else if(invited >= 8){
        $('.lingqu').addClass('keling').html('已获得1GB');
    }else{
        $('.lingqu').addClass('keling').html('加油哦');
    }

    if(!invited){   //等于0时停留在GO
        return false;
    }else if( invited >= 8 ){
        $('.avatar_box').fadeOut();
        $('.avatar_box').eq(8).fadeIn();
    }else{
        $('.avatar_box').fadeOut();
        $('.avatar_box').eq(invited).fadeIn();
        //console.log(invited);
    }

})();

/******** 底部领取话费按钮 *******/
$('.keling').click(function(){
 //   $('.lingqu').html('已领取').removeClass('keling');
});

/********* 点击获取验证码 *******/
$("#huoqu").click(function(){
    if($(this).css('color') == 'rgb(221, 221, 221)'){//使倒计时失效
        return;
    }
    phone = $("#phone").val();
    //发送验证码,第一步验证手机号码
    if (!reg.test(phone)) {
        alert("手机号码格式不正确");
        return false;
    }else {
        /**发送验证码 **/
        $.ajax({
            type:'post',
            url:'sms',
            data:{phone:phone},
            dataType:'json',
            success:function (rs) {
                switch(rs.result){
                    case 'success':
                        countDown();
                        break;
                    case 'ex':
                        alert("短信服务异常！");
                        break;
                    case 'failed':
                        alert("短信发送失败！");
                        break;
                }
            }
        });
    }
});


function countDown() {
    alert("验证码发送成功");
    var count = 60;
    var countdown = setInterval(CountDown, 1000);
    function CountDown() {
        $("#huoqu").attr("disabled", true).css('color','#ddd');

        $("#huoqu").text( count + "秒后重发");
        if (count == 0) {
            $("#huoqu").text("重新获取").removeAttr("disabled").css('color','#fff');
            clearInterval(countdown);
        }
        count--;
    }
}


function updateUrl(url,key) {
    var key = (key || 't') + '=';  //默认是"t"
    var reg = new RegExp(key + '\\d+');  //正则：t=1472286066028
    var timestamp = +new Date();
    if (url.indexOf(key) > -1) { //有时间戳，直接更新
        return url.replace(reg, key + timestamp);
    } else {  //没有时间戳，加上时间戳
        if (url.indexOf('\?') > -1) {
            var urlArr = url.split('\?');
            if (urlArr[1]) {
                return urlArr[0] + '?' + key + timestamp + '&' + urlArr[1];
            } else {
                return urlArr[0] + '?' + key + timestamp;
            }
        } else {
            if (url.indexOf('#') > -1) {
                return url.split('#')[0] + '?' + key + timestamp + location.hash;
            } else {
                return url + '?' + key + timestamp;
            }
        }
    }
}
