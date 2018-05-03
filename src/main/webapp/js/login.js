$(function () {
    //校验输入用户名为50位英文或数字,中文结合
    $(".user_name").on("blur", function () {
        var nameTest = /^[\u4e00-\u9fa5_a-zA-Z0-9]{1,50}$/;
        var userName = $(".user_name").val();
        var password = $(".password").val();
        if (!nameTest.test(userName)) {
            $(".tips").css("display", "block");
            $(".tips").text("请输入正确的用户名");
            return;
        } else {
            $(".tips").css("display", "none");
        }
        //点击登录按钮
        $(".submit").off().on("click", function () {
            var userName = $(".user_name").val();
            var password = $(".password").val();
            if (!userName) {
                $(".tips").css("display", "block").text("用户名不能为空");
                return;
            } else if (!password) {
                $(".tips").css("display", "block").text("密码不能为空");
                return;
            } else {
                $(".tips").css("display", "none")
            }
    
            //校验用户信息
            $.ajax({
                url: '/user/login.do',
                type: 'post',
                dataType: "json",
                xhrFields: {
                    withCredentials: true
                },
                crossDomain: true,
                data: {
                    'userName': userName,
                    'password': password
                },
                success: function (res) {
                    if (res.code == '0000') {
                    	  window.localStorage.setItem("userInfo", JSON.stringify(res.data));
                          window.location = './index.html';
                    } else {
                    	alert("登录失败!");
                    }
                }
            })
        })
    });
    //监控密码输入框
    $(".password").on("keydown",function(e){
    	var userName=$(".user_name").val();
    	var password=$(".password").val();
    	console.log(e.keyCode);
    	if(e.keyCode==13){
    		if(userName&&password){
    			$.ajax({
    				url: '/user/login.do',
                    type: 'post',
                    dataType: "json",
                    xhrFields: {
                        withCredentials: true
                    },
                    crossDomain: true,
                    data: {
                        'userName': userName,
                        'password': password
                    },
    	                success:function(res){
    	                	if (res.code == '0000') {
    	                    	  window.localStorage.setItem("userInfo", JSON.stringify(res.data));
    	                          window.location = './index.html';
    	                    } else {
    	                        alert("登录失败!");
    	                    }
    	                }
    			})
    			
    		}
    	}
    })
});