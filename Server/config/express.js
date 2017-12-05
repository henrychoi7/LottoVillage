/*
* 2017.08.17
* Package.json - Dependencies List
* morgan = 단순한 HTTP 요청 로거 미들웨어
* compression = gzip/deflate 를 사용해 응답 데이터 압축 미들웨어
* body-parser = 요청 내용을 해석하는 미들웨어로 다양한 요청타입을 지원하는 미들웨어
* method-override = HTTP 동사 기본 지원 기능 제공 (DELETE, PUT ..) 하는 미들웨어
* express.static() = 정적 파일을 서비스하는 미들웨어
* cookie-parser = req.cookies 객체를 채우기 위한 쿠키 해석용 미들웨어
* express-session = 영속적인 세션을 지원하기 위해 사용하는 미들웨어
*/
var express = require('express'),
    //morgan = require('morgan'),
    compression = require('compression'),
    bodyParser = require('body-parser'),
    methodOverride = require('method-override');
    /*expressSession = require('express-session'),
    cookieParser = require('cookie-parser');*/

module.exports = function () {
    var app = express();

    /*if (process.env.NODE_DEV === 'development'){
        app.use(morgan('dev'));
    } else if (process.env.NODE_DEV === 'production'){
        app.use(compression());
    }*/

    app.use(compression());

    app.use(bodyParser.urlencoded({
        extended: true
    }));

    app.use(bodyParser.json());

    app.use(methodOverride());

    /*app.use(cookieParser());
    app.use(expressSession({
        cookie:{
            maxAge:600*1000
        },
        saveUninitialized: true,
        resave: false,
        secret: 'developmentSessionSecret'
    }));*/

    app.set('views', process.cwd() + '/app/web/views');
    app.set('view engine', 'ejs');

    // Mobile
    require(process.cwd() + '/app/mobile/routes/algorithm.server.route')(app);
    require(process.cwd() + '/app/mobile/routes/index.server.route')(app);
    require(process.cwd() + '/app/mobile/routes/participation.server.route')(app);
    require(process.cwd() + '/app/mobile/routes/product.server.route')(app);
    require(process.cwd() + '/app/mobile/routes/user.server.route')(app);

    // Web
    require(process.cwd() + '/app/web/routes/index.server.route')(app);
    require(process.cwd() + '/app/web/routes/main.server.route')(app);
    require(process.cwd() + '/app/web/routes/product.server.route')(app);
    require(process.cwd() + '/app/web/routes/user.server.route')(app);

    app.use(express.static(process.cwd() + '/public'));

    return app;
};