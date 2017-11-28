var winston = require('winston');
require('winston-daily-rotate-file');

module.exports = function () {
    return new winston.Logger({
        transports: [
            new winston.transports.Console({
                level: 'info',
                json: false,
                timestamp: function () {
                    return getTime('Y-m-d H:i:s');
                },
                formatter: function (options) {
                    return options.timestamp() + ' ' + options.level.toUpperCase() + ' ' + (undefined !== options.message ? options.message : '') +
                        (options.meta && Object.keys(options.meta).length ? '\n\t' + JSON.stringify(options.meta) : '' );
                }
            }),
            new (require('winston-daily-rotate-file'))({
                level: 'info',
                json: false,
                filename: process.cwd() + '/logs/' + '.log', // file path
                timestamp: function () {
                    return getTime('Y-m-d H:i:s');
                },
                datePattern: 'yyyy-MM-dd',
                prepend: true,
                localTime: true,
                formatter: function (options) {
                    return options.timestamp() + ' ' + options.level.toUpperCase() + ' ' + (undefined !== options.message ? options.message : '') +
                        (options.meta && Object.keys(options.meta).length ? '\n\t' + JSON.stringify(options.meta) : '' );
                }
            })
        ]
    })
};

function fix2num(n){
    return [0, n].join('').slice(-2);
}
function getTime(format){
    var curdate = new Date();
    if( format === undefined ) return curdate;
    format = format.replace(/Y/i, curdate.getFullYear());
    format = format.replace(/m/i, fix2num(curdate.getMonth() + 1));
    format = format.replace(/d/i, fix2num(curdate.getDate()));
    format = format.replace(/H/i, fix2num(curdate.getHours()));
    format = format.replace(/i/i, fix2num(curdate.getMinutes()));
    format = format.replace(/s/i, fix2num(curdate.getSeconds()));
    format = format.replace(/ms/i, curdate.getMilliseconds());
    return format;
}
