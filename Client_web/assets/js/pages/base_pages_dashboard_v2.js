/*
 *  Document   : base_pages_dashboard_v2.js
 *  Author     : pixelcave
 *  Description: Custom JS code used in Dashboard v2 Page
 */

// var url = "http://13.124.207.144/";
var url = "http://203.249.127.32:65004/";

// var myJson = {"isSuccess":true,"results":{"week_lv_date":["12/05","12/04","12/03","12/02","12/01","11/30","11/29"],"week_lv_one_cnt":[3,1,2,3,1,1,1],"week_lv_six_cnt":[1,2,3,1,1,1,1],"week_lv_twelve_cnt":[1,1,1,1,1,1,1]}};
// console.log(myJson.results.week_lv_date);
var weekDate = ['12/03', '12/04', '12/15', '12/16', '12/17', '12/17', '12/18'];
var week1 = [600, 350, 1100, 420, 750, 1050, 670];
var week6 = [490, 430, 560, 790, 1200, 950, 1500];
var week12 = [520, 410, 120, 230, 420, 800, 1000];


var BasePagesDashboardv2 = function() {
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


    // Chart.js Chart, for more examples you can check out http://www.chartjs.org/docs
    var initDashv2ChartJS = function(){
        // Get Chart Container
        var $dashChartEarningsCon = jQuery('.js-dash-chartjs-earnings')[0].getContext('2d');
        var $dashChartSalesCon    = jQuery('.js-dash-chartjs-sales')[0].getContext('2d');

        // Earnings Chart Data
        var $dashChartEarningsData = {
            // labels: ['12/03', '12/04', '12/15', '12/16', '12/17', '12/17', '12/18'],
            labels: weekDate,
            datasets: [
                {
                    label: '1시간',
                    fillColor: 'rgba(68, 180, 166, .07)',
                    strokeColor: 'rgba(68, 180, 166, .25)',
                    pointColor: 'rgba(68, 180, 166, .25)',
                    pointStrokeColor: '#fff',
                    pointHighlightFill: '#fff',
                    pointHighlightStroke: 'rgba(68, 180, 166, 1)',
                    data: week1
                },
                {
                    label: '6시간',
                    fillColor: 'rgba(68, 180, 166, .25)',
                    strokeColor: 'rgba(68, 180, 166, .55)',
                    pointColor: 'rgba(68, 180, 166, .55)',
                    pointStrokeColor: '#fff',
                    pointHighlightFill: '#fff',
                    pointHighlightStroke: 'rgba(68, 180, 166, 1)',
                    data: week6
                },
                {
                    label: '12시간',
                    fillColor: 'rgba(68, 180, 166, .25)',
                    strokeColor: 'rgba(68, 180, 166, .75)',
                    pointColor: 'rgba(68, 180, 166, .75)',
                    pointStrokeColor: '#fff',
                    pointHighlightFill: '#fff',
                    pointHighlightStroke: 'rgba(68, 180, 166, 1)',
                    data: week12
                }
            ]
        };

        // Sales Chart Data
        var $dashChartSalesData = {
            labels: ['MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN'],
            datasets: [
                {
                    label: 'Last Week',
                    fillColor: 'rgba(164, 138, 212, .07)',
                    strokeColor: 'rgba(164, 138, 212, .25)',
                    pointColor: 'rgba(164, 138, 212, .25)',
                    pointStrokeColor: '#fff',
                    pointHighlightFill: '#fff',
                    pointHighlightStroke: 'rgba(164, 138, 212, 1)',
                    data: [60, 40, 90, 35, 85, 65, 77]
                },
                {
                    label: 'This Week',
                    fillColor: 'rgba(164, 138, 212, .25)',
                    strokeColor: 'rgba(164, 138, 212, .55)',
                    pointColor: 'rgba(164, 138, 212, .55)',
                    pointStrokeColor: '#fff',
                    pointHighlightFill: '#fff',
                    pointHighlightStroke: 'rgba(164, 138, 212, 1)',
                    data: [50, 33, 25, 82, 120, 95, 150]
                }
            ]
        };

        // Init Earnings Chart
        var $dashChartEarnings = new Chart($dashChartEarningsCon).Line($dashChartEarningsData, {
            scaleFontFamily: "'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif",
            scaleFontColor: '#999',
            scaleFontStyle: '600',
            tooltipTitleFontFamily: "'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif",
            tooltipCornerRadius: 3,
            maintainAspectRatio: false,
            responsive: true
        });

        // Init Sales Chart
        var $dashChartSales = new Chart($dashChartSalesCon).Line($dashChartSalesData, {
            scaleFontFamily: "'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif",
            scaleFontColor: '#999',
            scaleFontStyle: '600',
            tooltipTitleFontFamily: "'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif",
            tooltipCornerRadius: 3,
            maintainAspectRatio: false,
            responsive: true
        });
    };

    return {
        init: function () {
            // Init ChartJS charts
            initDashv2ChartJS();
            // getWeekChart();
        }
    };
}();

// Initialize when page loads
jQuery(function(){ BasePagesDashboardv2.init(); });