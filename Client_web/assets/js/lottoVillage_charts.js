/*
 *  Document   : base_comp_charts.js
 *  Author     : pixelcave
 *  Description: Custom JS code used in Charts Page
 */

var BaseCompCharts = function() {

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