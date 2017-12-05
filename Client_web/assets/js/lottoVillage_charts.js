/*
 *  Document   : base_comp_charts.js
 *  Author     : pixelcave
 *  Description: Custom JS code used in Charts Page
 */

// var url = "http://13.124.207.144/";
var url = "http://192.9.44.53:65004/";

// var myJson = {"isSuccess":true,"results":{"week_lv_date":["12/05","12/04","12/03","12/02","12/01","11/30","11/29"],"week_lv_one_cnt":[3,1,2,3,1,1,1],"week_lv_six_cnt":[1,2,3,1,1,1,1],"week_lv_twelve_cnt":[1,1,1,1,1,1,1]}};
// console.log(myJson.results.week_lv_date);
var weekDate = [];
var week1 = [];
var week6 = [];
var week12 = [];

var BaseCompCharts = function() {

    var getAnaylzeUser = function() {
        $.ajax({
            type: "GET",
            url: url + 'anaylze_user',
            dataType: "json",
            success: function(resData) {
                if (resData.isSuccess == true){
                    $('#USER_TOT_TODAY').data('to', resData.results.USER_TOT_TODAY);
                    $('#USER_TOT').data('to', resData.results.USER_TOT);
                    $('#USER_RATE_LOGIN').data('to', resData.results.USER_RATE_LOGIN);
                }
            },
            error: function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    };

    var getWeekChart = function() {
        $.ajax({
            type: "GET",
            url: url + 'week_lv',
            dataType: "json",
            success: function(resData) {
                if (resData.isSuccess == true){
                    weekDate = resData.results.week_lv_date;
                    week1 = resData.results.week_lv_one_cnt;
                    week6 = resData.results.week_lv_six_cnt;
                    week12 = resData.results.week_lv_twelve_cnt;
                }
            },
            error: function(request,status,error){
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    };

    // jQuery Sparkline Charts, for more examples you can check out http://omnipotent.net/jquery.sparkline/#s-docs
    var initChartsSparkline = function(){
        var ranking = 1;
        // Bar Charts
        var $barOptions = {
            type: 'bar',
            barWidth: 8,
            barSpacing: 6,
            height: '70px',
            barColor: '#fadb7d',
            tooltipPrefix: '',
            tooltipSuffix: '명 당첨!',
            tooltipFormat: '{{prefix}}{{value}}{{suffix}}'
        };
        jQuery('.js-slc-bar1').sparkline([1,2,4,27,3,2], $barOptions);

        $barOptions['barColor']         = '#abe37d';
        // $barOptions['tooltipPrefix']    = '';
        // $barOptions['tooltipSuffix']    = '';
        jQuery('.js-slc-bar2').sparkline([1,7,2,1,3,2], $barOptions);

        $barOptions['barColor']         = '#faad7d';
        // $barOptions['tooltipPrefix']    = '';
        // $barOptions['tooltipSuffix']    = ' Sales';
        jQuery('.js-slc-bar3').sparkline([3,2,4,5,3,2], $barOptions);

        // Pie Charts
        var $pieCharts = {
            type: 'pie',
            width: '50px',
            height: '50px',
            sliceColors: ['#fadb7d','#faad7d', '#a886fb', '#75b0eb','#abe37d'],
            tooltipPrefix: '',
            tooltipSuffix: ' 명',
            tooltipFormat: '{{prefix}}{{value}}{{suffix}}'
        };
        jQuery('.js-slc-pie1').sparkline([1,2,4,27,3,2], $pieCharts);

        $pieCharts['tooltipPrefix'] = '$ ';
        $pieCharts['tooltipSuffix'] = '';
        jQuery('.js-slc-pie2').sparkline([1,7,2,1,3,2], $pieCharts);

        $pieCharts['tooltipPrefix'] = '';
        $pieCharts['tooltipSuffix'] = ' Sales';
        jQuery('.js-slc-pie3').sparkline([3,2,4,5,3,2], $pieCharts);
    };

    return {
        init: function () {
            // Init all charts
            initChartsSparkline();

        }
    };
}();

// Initialize when page loads
jQuery(function(){ BaseCompCharts.init(); });