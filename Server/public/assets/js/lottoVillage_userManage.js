$(window).ready(function() {
    userAdmin.init();
    // @TODO: table paging (datatables plugins)
    // $('#example').DataTable( {
    //     "ajax": '../ajax/data/arrays.txt'
    // } );

});

// 테스트용
// var myJson = {"isSuccess":true,"results":[{"PHONE_NUMBER":"010-8759-6912","USER_NAME":"Admin","USER_STATUS":"0","USER_TOT_POINT":100,"ONE_TOT_CNT":14,"SIX_TOT_CNT":10,"TWELVE_TOT_CNT":11,"CURRENT_DATETIME":"2017-12-04 19:59:45","REGISTER_DATETIME":"2017-09-02 13:32:23"},{"PHONE_NUMBER":"010-2576-1110","USER_NAME":"김길동","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":1,"SIX_TOT_CNT":2,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-04 19:08:45","REGISTER_DATETIME":"2017-11-28 15:49:36"},{"PHONE_NUMBER":"010-1234-5678","USER_NAME":"김길동1","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:44","REGISTER_DATETIME":"2017-12-03 20:41:44"},{"PHONE_NUMBER":"010-1234-5677","USER_NAME":"김길동10","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5611","USER_NAME":"김길동11","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5612","USER_NAME":"김길동12","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5613","USER_NAME":"김길동13","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5614","USER_NAME":"김길동14","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5615","USER_NAME":"김길동15","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5616","USER_NAME":"김길동16","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5617","USER_NAME":"김길동17","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5618","USER_NAME":"김길동18","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5619","USER_NAME":"김길동19","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5670","USER_NAME":"김길동2","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:44","REGISTER_DATETIME":"2017-12-03 20:41:44"},{"PHONE_NUMBER":"010-1234-5620","USER_NAME":"김길동20","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5621","USER_NAME":"김길동21","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5679","USER_NAME":"김길동3","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:44","REGISTER_DATETIME":"2017-12-03 20:41:44"},{"PHONE_NUMBER":"010-1234-5671","USER_NAME":"김길동4","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:44","REGISTER_DATETIME":"2017-12-03 20:41:44"},{"PHONE_NUMBER":"010-1234-5672","USER_NAME":"김길동5","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:44","REGISTER_DATETIME":"2017-12-03 20:41:44"},{"PHONE_NUMBER":"010-1234-5673","USER_NAME":"김길동6","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:44","REGISTER_DATETIME":"2017-12-03 20:41:44"},{"PHONE_NUMBER":"010-1234-5674","USER_NAME":"김길동7","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5675","USER_NAME":"김길동8","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5676","USER_NAME":"김길동9","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-4749-9622","USER_NAME":"정제리테스트","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":11,"SIX_TOT_CNT":4,"TWELVE_TOT_CNT":4,"CURRENT_DATETIME":"2017-12-05 02:03:21","REGISTER_DATETIME":"2017-11-07 18:59:57"}]}

// var url = "http://13.124.207.144/";
var url = "http://localhost:65004/";


var userAdmin = {
    init: function() {
        // this.drawUserTable(myJson.results);
        this.getUserList();
        this.initEvent();
    },

    initEvent: function() {
        var _this = this;

        $(document).on('click touchend','.ban-btn', this.banUser);
        $(document).on('click touchend','.details-btn', this.detailUser);
    },

    drawUserTable : function(userData){
        var wrapper = $('#userListTB');
        var viewTable = wrapper.find('tbody');
        viewTable.html('');

        for (var id in userData) {
            var user = userData[id];
            var userState = '';
            if (user.USER_STATUS == 0){
                userState = "<td class=\"hidden-xs text-left \">\n" +
                            "    <span class=\"label label-success\">일반</span>\n" +
                            "    <button class=\"ban-btn btn btn-xs btn-default\" type=\"button\" data-toggle=\"tooltip\" title=\"영구 정지\"><i class=\"fa fa-close\"></i></button>\n" +
                            "</td>";
            } else {
                userState = "<td class=\"hidden-xs text-left\">\n" +
                            "    <span class=\"label label-danger\">정지</span>\n" +
                            "</td>";
            }

            var viewUser = $("<tr>");
            viewUser
                .html(
                    "        <td class=\"font-w600 text-center sorting_1\">" + user.USER_NAME + "</td>\n" +
                    "            <td class=\"user-phone text-center\">" + user.PHONE_NUMBER + "</td>\n" +
                    "            <td class=\"hidden-xs text-right\">" + user.USER_TOT_POINT + " p</td>\n" +
                    "        <td class=\"hidden-xs text-center\">" + user.REGISTER_DATETIME + "</td>\n" +
                    "        <td class=\"hidden-xs text-center\">" + user.CURRENT_DATETIME + "</td>\n" + userState +
                    "            <td class=\"text-center\">\n" +
                    "            <div class=\"btn-group\">\n" +
                    "            <button class=\"details-btn btn btn-xs btn-default\" type=\"button\" data-toggle=\"tooltip\" title=\"내역 보기\">자세히 보기 <i class=\"fa fa-angle-right\"></i></button>\n" +
                    "        </div>\n" +
                    "        </td>\n" +
                    "        </tr>");
            viewTable.append(viewUser);
        }
    },

    getUserList: function() {
        $.ajax({
            type: "GET",
            url: url + 'user_list',
            dataType: "json",
            success: function(resData) {
                if (resData.isSuccess == true){
                    userAdmin.drawUserTable(resData.results);
                }
            },
            error: function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    },

    banUser: function() {
        var phone = $(this).closest("tr")
            .find(".user-phone")
            .text();

        $.ajax({
            type: "POST",
            url: url + 'user_block',
            dataType: "json",
            data: {
                phone_number : phone
            },
            success: function(resData) {
                if (resData.isSuccess == true){
                    alert('영구정지 했습니다!');
                    window.location.href = '/lottoVillage_userManage.html';
                }
            },
            error: function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                window.location.href = '/lottoVillage_userManage.html';
            }
        });
    },

    detailUser: function(){
        var phone = $(this).closest("tr")
            .find(".user-phone")
            .text();

        window.location.href = '/lottoVillage_userDetails.html?index=' + phone;
    }
};
