var phone;
var yzm;
var reg = /^1[3|4|5|7|8][0-9]\d{8}$/;
window.onload=function(){
    function stopBuble(ev) {
        if(ev&& ev.stopPropagation) {
            ev.stopPropagation();
        }
        else{
            window.event.cancelBubble=true;
        }
    }
    /*获取验证码*/
    function toDouble(num) {
        if (num < 10) {
            return '0' + num;
        }
        else {
            return '' + num;
        }
    }
    var oBtn = document.getElementById('getNumb');
    var set = false;
    oBtn.onclick = function (ev) {
        stopBuble(ev);
        phone = $('.num').val();
        if (!reg.test(phone)) {
            alert("手机号码格式不正确");
            return;
        }
        if (set) {
            return;
        }
        set = true;
        $.ajax({
            type:"GET",
            url:"getNum",
            data:{tel:phone},
            dataType:"json",
            success:function (rs) {
                alert(rs.message);
                yzm = rs.data;
            }
        });
        var num = 60;
        oBtn.innerHTML = num + 'S后重新获取';
        timer = setInterval(function () {
            num--;
            oBtn.innerHTML = toDouble(num) + 'S后重新获取';
            if (num == 0) {
                clearInterval(timer);
                set = false;
                oBtn.innerHTML = '获取验证码';
            }
        }, 1000);

    }

    /*点击立即抽奖*/
    $('#doRaffle').click(function(ev){
        var openid = $("#openid").val();
        var code = $("#code").val();
        var area = $("#area").val();
        
        if(code == yzm){
            stopBuble(ev);
            $('#raffle').hide();
            $('.tan').hide();
            $("#tel").val(phone);
            $("#zhizhen").trigger("click");
            /*$.ajax({
                type:"GET",
                url:"tologin",
                data:{tel:phone,openid:openid},
                dataType:"json",
                success:function (rs) {
                    if(rs.result == 'success'){
                        stopBuble(ev);
                        $('#raffle').hide();
                        $('.tan').hide();
                        $("#tel").val(phone);
                        $("#zhizhen").trigger("click");
                    }else{
                        alert("登记失败")
                    }
                }
            });*/

        }else{
            alert("验证码有误！");
        }

    });

    /*大转盘*/
    var onOff = true;

    var timeOut = function(){
        $("#zhizhen").rotate({
            angle:0,
            duration: 8000,
            animateTo: 2160,
            callback:function(){
                $('.get p:eq(1)').html(text);
                $('.tan').css('display','block');
                $('.tan').on('touchmove',function(ev){
                    ev.preventDefault();
                });
                onOff = true;
            }
        });

    };

    var rotateFunc = function(awards,angle,text){
        $('#zhizhen').stopRotate();
        $("#zhizhen").rotate({
            angle:0,
            duration: 4000,
            animateTo: angle+3600,
            callback:function(){
                if(text=='谢谢参与！'){
                    $('.tan .get1').css('display','block');
                }
                else{
                    $('.get p:eq(1)').html(text);
                    $('.tan .get').css('display','block');
                }
                $('.tan').css('display','block');
                $('.tan').on('touchmove',function(ev){
                    ev.preventDefault();
                });
                onOff = true;
            }
        });
    };


    var time =1;
    $("#zhizhen").rotate({
        bind:
        {
            click: function(){
                if(!onOff){
                    return;
                }
                onOff = false;
                if(time==0){
                    timeOut();
                }
                if(time==1){
                    var openid = $("#openid").val();
                    var tel = $("#tel").val();
                    if(tel == '' || tel == 'null'){
                        $.ajax({
                            type:"GET",
                            url:"tologin",
                            data:{tel:phone,openid:openid},
                            dataType:"json",
                            success:function (rs) {
                                if(rs.result == 'success'){
                                    $('.tan').show();
                                    $('#raffle').show();
                                }else if(rs.result == 'no-log'){
                                    alert("登记信息之后才能参与抽奖哦~");
                                }else {
                                    alert("参数错误");
                                }
                                onOff = true;
                            }
                         });
                    }else{
                        $.ajax({
                            type:"GET",
                            url:"raffle",
                            data:{openid:openid,phone:phone},
                            dataType:"json",
                            success:function (rs) {
                                if(rs.message == 1){
                                    rotateFunc(6,270,'5元话费！');
                                }else if(rs.message == 2){
                                    rotateFunc(8,340,'10元话费！');
                                }else if(rs.message == 3){
                                    rotateFunc(0,30,'20元话费！');
                                }else if(rs.message == 4){
                                    rotateFunc(1,70,'50元话费！');
                                }else if(rs.message == 5){
                                    rotateFunc(2,110,'50M流量！');
                                }else if(rs.message == 6){
                                    rotateFunc(3,150,'100M流量！');
                                }else if(rs.message == 7){
                                    rotateFunc(4,190,'200M流量！');
                                }else if(rs.message == 8){
                                    rotateFunc(5,230,"500M流量！");
                                }else if(rs.message == 9){
                                    rotateFunc(7,300,'谢谢参与！');
                                }else {
                                    alert("亲，您已经抽过奖了哦，每月我们还将抽取幸运奖，敬请关注！");
                                }
                            }
                        });
                        onOff = true;
                    }
                    /*var data = [0,1,2,3,4,5,6,7];
                    //var data=[5,5,5,5,5,5]
                    data = data[Math.floor(Math.random()*data.length)];
                    if(data==0){
                        rotateFunc(0,23,'500M流量！');
                    }
                    if(data==1){
                        rotateFunc(1,67,'1G流量！')
                    }
                    if(data==2){
                        rotateFunc(2,113,'1元流量！')
                    }
                    if(data==3){
                        rotateFunc(3,158,'2元话费！')
                    }
                    if(data==4){
                        rotateFunc(4,203,'5元话费！')
                    }
                    if(data==5){
                        rotateFunc(5,248,"谢谢参与！")
                    }
                    if(data==6){
                        rotateFunc(6,293,'50M流量！')
                    }
                    if(data==7){
                        rotateFunc(7,338,'100M流量！')
                    }*/
                }
            }
        }

    });
    $('.get').click(function(ev){
        ev.preventDefault();
        $('.tan').hide();
        $('.get').hide();
    });

    $('.get1').click(function(ev){
        ev.preventDefault();
        $('.tan').hide();
        $('.get1').hide();
    });

    /*var oDiv=document.getElementsByClassName('liu')[0];
    oDiv.innerHTML+=oDiv.innerHTML;
    setInterval(function(){
        oDiv.style.left=oDiv.offsetLeft-1+'px';
        if(oDiv.offsetLeft<-oDiv.offsetWidth/2-5){
            oDiv.style.left=0+'px';
        }
    },10)*/
    var oDiv=document.getElementsByClassName('liu')[0];
    oDiv.innerHTML+=oDiv.innerHTML;
    console.log(oDiv.offsetHeight);
    console.log(oDiv.offsetTop);
    var timer=setInterval(function(){
        if(oDiv.offsetTop<=(-oDiv.offsetHeight/2)){
            oDiv.style.top=0+'px';
        }
        else{
            oDiv.style.top=oDiv.offsetTop-1+'px';
        }
    },50)
}