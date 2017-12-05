$(window).ready(function() {
    reward.init();
});

// 테스트용
var myJson = {"isSuccess":true,"results":[{"PRODUCT_CODE":1,"PRODUCT_NAME":"기프티콘","PRODUCT_PRICE":9000,"PRODUCT_STATUS":"Y","PRODUCT_CONTENTS":"카카오 무지"},{"PRODUCT_CODE":2,"PRODUCT_NAME":"초콜릿","PRODUCT_PRICE":2000,"PRODUCT_STATUS":"N","PRODUCT_CONTENTS":"노브랜드"},{"PRODUCT_CODE":4,"PRODUCT_NAME":"테스트3","PRODUCT_PRICE":3000,"PRODUCT_STATUS":"Y","PRODUCT_CONTENTS":"테스트3-설명"},{"PRODUCT_CODE":5,"PRODUCT_NAME":"테스트4","PRODUCT_PRICE":4000,"PRODUCT_STATUS":"N","PRODUCT_CONTENTS":"테스트4-설명"},{"PRODUCT_CODE":6,"PRODUCT_NAME":"테스트5","PRODUCT_PRICE":5000,"PRODUCT_STATUS":"Y","PRODUCT_CONTENTS":"테스트5-설명"},{"PRODUCT_CODE":7,"PRODUCT_NAME":"테스트6","PRODUCT_PRICE":6000,"PRODUCT_STATUS":"N","PRODUCT_CONTENTS":"테스트6-설명"}]};
// var url = "http://13.124.207.144/";
var url = "http://192.9.44.53:65004/";

var reward = {
    init: function() {
        this.drawProductTable(myJson.results);
        // this.getProductList();
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

            var viewProduct = $("<div id=\"product-delete\" class=\"col-sm-6 col-lg-3\">");
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
        $(this).parents('#product-delete').hide();
        // var product_code = $(this).closest("#product_code").text();
        //
        // $.ajax({
        //     type: "POST",
        //     url: url + 'delete_product',
        //     dataType: "json",
        //     data: {
        //         product_code : parseInt(product_code.val())
        //     },
        //     success: function(resData) {
        //         if (resData.isSuccess == true){
        //             window.location.href = '/Client_web/lottoVillage_rewardAdmin.html';
        //         }
        //     },
        //     error: function(request,status,error){
        //         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        //     }
        // });
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
