$(window).ready(function() {
    login.init();
});

// var url = "http://13.124.207.144/";
var url = "http://203.249.127.32:65004/";


var login = {
    init: function() {
        this.initEvent();
    },

    initEvent: function() {
        var _this = this;

        $(document).on('click touchend','.login-btn', this.postLogin);
    },

    postLogin: function() {
        var $form = $('form[name=admin-login]');
        var $name = $form.find('input[name=login-username]');
        var $password = $form.find('input[name=login-password]');

        $.ajax({
            type: "POST",
            url: url + 'login_web',
            dataType: "json",
            data: {
                phone_number : $name.val(),
                password : $password.val()
            },
            success: function(resData) {
                if (resData.isSuccess == true){
                    window.location.href = '/Client_web/lottoVillage_main.html';
                } else {
                    alert('로그인에 실패하셨습니다. 다시 로그인해주세요!');
                }
            },
            error: function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    }
};
