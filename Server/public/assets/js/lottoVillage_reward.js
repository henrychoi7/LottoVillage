$(window).ready(function() {
    reward.init();
});

// 테스트용
// var myJson = {"isSuccess":true,"results":[{"PHONE_NUMBER":"010-8759-6912","USER_NAME":"Admin","USER_STATUS":"0","USER_TOT_POINT":100,"ONE_TOT_CNT":14,"SIX_TOT_CNT":10,"TWELVE_TOT_CNT":11,"CURRENT_DATETIME":"2017-12-04 19:59:45","REGISTER_DATETIME":"2017-09-02 13:32:23"},{"PHONE_NUMBER":"010-2576-1110","USER_NAME":"김길동","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":1,"SIX_TOT_CNT":2,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-04 19:08:45","REGISTER_DATETIME":"2017-11-28 15:49:36"},{"PHONE_NUMBER":"010-1234-5678","USER_NAME":"김길동1","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:44","REGISTER_DATETIME":"2017-12-03 20:41:44"},{"PHONE_NUMBER":"010-1234-5677","USER_NAME":"김길동10","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5611","USER_NAME":"김길동11","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5612","USER_NAME":"김길동12","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5613","USER_NAME":"김길동13","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5614","USER_NAME":"김길동14","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5615","USER_NAME":"김길동15","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5616","USER_NAME":"김길동16","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5617","USER_NAME":"김길동17","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5618","USER_NAME":"김길동18","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5619","USER_NAME":"김길동19","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5670","USER_NAME":"김길동2","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:44","REGISTER_DATETIME":"2017-12-03 20:41:44"},{"PHONE_NUMBER":"010-1234-5620","USER_NAME":"김길동20","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5621","USER_NAME":"김길동21","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5679","USER_NAME":"김길동3","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:44","REGISTER_DATETIME":"2017-12-03 20:41:44"},{"PHONE_NUMBER":"010-1234-5671","USER_NAME":"김길동4","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:44","REGISTER_DATETIME":"2017-12-03 20:41:44"},{"PHONE_NUMBER":"010-1234-5672","USER_NAME":"김길동5","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:44","REGISTER_DATETIME":"2017-12-03 20:41:44"},{"PHONE_NUMBER":"010-1234-5673","USER_NAME":"김길동6","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:44","REGISTER_DATETIME":"2017-12-03 20:41:44"},{"PHONE_NUMBER":"010-1234-5674","USER_NAME":"김길동7","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5675","USER_NAME":"김길동8","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-1234-5676","USER_NAME":"김길동9","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":0,"SIX_TOT_CNT":0,"TWELVE_TOT_CNT":0,"CURRENT_DATETIME":"2017-12-03 20:41:45","REGISTER_DATETIME":"2017-12-03 20:41:45"},{"PHONE_NUMBER":"010-4749-9622","USER_NAME":"정제리테스트","USER_STATUS":"0","USER_TOT_POINT":0,"ONE_TOT_CNT":11,"SIX_TOT_CNT":4,"TWELVE_TOT_CNT":4,"CURRENT_DATETIME":"2017-12-05 02:03:21","REGISTER_DATETIME":"2017-11-07 18:59:57"}]}

// var url = "http://13.124.207.144/";
var url = "http://203.249.127.32:65004/";

var reward = {
    init: function() {
        // this.drawUserTable(myJson.results);
        this.getProductList();
        this.initEvent();
    },

    initEvent: function() {
        var _this = this;

        $(document).on('click touchend','#rewardRegistBtn', this.postProductRegister);
        $(document).on('click touchend','#deleteProduct', this.postProductDelete);
        $(document).on('click touchend','#productUpdateBtn', this.postProductUpdate);
    },

    drawProductTable : function(productData){
        var viewTable = $('#productList');
        viewTable.html('');

        for (var id in productData) {
            var product = productData[id];

            var viewProduct = $("<div class=\"col-sm-6 col-lg-3\">");
            viewProduct
                .html(
                    "              <a class=\"block block-link-hover3 text-center\" href=\"javascript:void(0)\">\n" +
                    "                <div class=\"block-header\">\n" +
                    "                  <h3 class=\"block-title\">" + product.PRODUCT_NAME + "</h3>\n" +
                    "                </div>\n" +
                    "                <div class=\"block-content block-content-full bg-gray-lighter\">\n" +
                    "                  <div class=\"h1 font-w700 push-10\">" + product.PRODUCT_PRICE + "</div>\n" +
                    "                </div>\n" +
                    "                <div class=\"block-content\">\n" +
                    "                  <table class=\"table table-borderless table-condensed\">\n" +
                    "                    <tbody>\n" +
                    "                      <tr>\n" +
                    "                        <td>No. <strong id=\"product_code\">" + product.PRODUCT_CODE + "</strong></td>\n" +
                    "                      </tr>\n" +
                    "                      <tr>\n" +
                    "                        <td>Company <strong>오리온</strong></td>\n" +
                    "                      </tr>\n" +
                    "                      <tr>\n" +
                    "                        <td>Description <strong>이 상품은 ...</strong></td>\n" +
                    "                      </tr>\n" +
                    "                    </tbody>\n" +
                    "                  </table>\n" +
                    "                </div>\n" +
                    "                <div class=\"block-content block-content-mini block-content-full bg-gray-lighter\">\n" +
                    "                  <span class=\"btn btn-default\" data-toggle=\"modal\" data-target=\"#modifyModal\">수정</span>\n" +
                    "                  <span id=\"deleteProduct\" class=\"btn btn-default\" data-toggle=\"modal\" data-target=\"#deleteModal\">삭제</span>\n" +
                    "                </div>\n" +
                    "              </a>\n" +
                    "\n" +
                    "            </div>");
            viewTable.append(viewProduct);
        }
    },

    getProductList: function() {
        $.ajax({
            type: "GET",
            url: url + 'product_list',
            dataType: "json",
            success: function(resData) {
                if (resData.isSuccess == true){
                    reward.drawProductTable(resData.results);
                }
            },
            error: function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    },

    postProductRegister: function() {
        var $form = $('form[name=reward-regist]');
        var $code = $form.find('input[name=product_code]');
        var $name = $form.find('input[name=product_name]');
        var $price = $form.find('input[name=product_price]');
        var $category = $form.find('input[name=product_category]');
        var $contents = $form.find('input[name=product_contents]');

        $.ajax({
            type: "POST",
            url: url + 'register_product',
            dataType: "json",
            data: {
                product_code : parseInt($code.val()),
                product_name : $name.val(),
                product_price : parseInt($price.val()),
                product_contents : $category.val(),
                product_category : parseInt($contents.val())
            },
            success: function(resData) {
                if (resData.isSuccess == true){
                    window.location.href = '/Client_web/lottoVillage_rewardAdmin.html';
                }
            },
            error: function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    },

    postProductDelete: function() {
        var product_code = $(this).closest("#product_code").text();

        $.ajax({
            type: "POST",
            url: url + 'delete_product',
            dataType: "json",
            data: {
                product_code : parseInt(product_code.val())
            },
            success: function(resData) {
                if (resData.isSuccess == true){
                    window.location.href = '/Client_web/lottoVillage_rewardAdmin.html';
                }
            },
            error: function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    },

    postProductUpdate: function() {
        var $form = $('form[name=reward-update]');
        var $code = $form.find('input[name=product_code]');
        var $name = $form.find('input[name=product_name]');
        var $price = $form.find('input[name=product_price]');
        var $category = $form.find('input[name=product_category]');
        var $contents = $form.find('input[name=product_contents]');

        $.ajax({
            type: "POST",
            url: url + 'update_product',
            dataType: "json",
            data: {
                product_code : parseInt($code.val()),
                product_name : $name.val(),
                product_price : parseInt($price.val()),
                product_contents : $category.val(),
                product_category : parseInt($contents.val())
            },
            success: function(resData) {
                if (resData.isSuccess == true){
                    window.location.href = '/Client_web/lottoVillage_rewardAdmin.html';
                }
            },
            error: function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    }
};
