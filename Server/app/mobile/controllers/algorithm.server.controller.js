var logger = require(process.cwd() + '/config/winston'),
    tokenCheck = require(process.cwd() + '/app/mobile/controllers/token.server.controller'),
    randomIntArray = require('random-int-array');

var pythonScriptPath = '/config/algorithm/lotto.py',
    pythonShell = require('python-shell');

exports.details_of_algorithm_info = function (req, res) {
    var requestAlgorithmType = req.query.algorithm_type,
        isValidatedToken = tokenCheck.check(req),
        requestPhoneNumber;

    if (isValidatedToken) {
        var tokenData = jwt.verify(req.headers["x-access-token"], 'developmentTokenSecret');
        requestPhoneNumber = tokenData.phone_number;
    } else {
        return res.json({isSuccess: false, errorMessage: "토큰이 만료되었습니다."});
    }

    if (!requestAlgorithmType) return res.json({isSuccess: false, errorMessage: "사용할 로또 알고리즘을 골라주세요."});

    requestAlgorithmType = requestAlgorithmType.replace(/(\s*)/g, "");
    requestPhoneNumber = requestPhoneNumber.replace(/(\s*)/g, "");

    switch (requestAlgorithmType) {
        case '1':
            pythonShell.run(pythonScriptPath, function (err, results) {
                if (err) throw err;
                console.log('results: %j', results);
                requestPredictionNumber1 = Number(results[0]);
                requestPredictionNumber2 = Number(results[1]);
                requestPredictionNumber3 = Number(results[2]);
                requestPredictionNumber4 = Number(results[3]);
                requestPredictionNumber5 = Number(results[4]);
                requestPredictionNumber6 = Number(results[5]);
                requestBonusNumber = Number(results[6]);

                console.log('1 번째 추천 번호 : ' + requestPredictionNumber1 + '\n2 번째 추천 번호 : ' + requestPredictionNumber2 + '\n3 번째 추천 번호 : ' + requestPredictionNumber3 +
                    '\n4 번째 추천 번호 : ' + requestPredictionNumber4 + '\n5 번째 추천 번호 : ' + requestPredictionNumber5 + '\n6 번째 추천 번호 : ' + requestPredictionNumber6 +
                    '\n보너스 번호 : ' + requestBonusNumber);
            });
            break;
        case '2':
            var lottoPredictionNumbers = randomIntArray({count: 7, min: 1, max: 45, unique: true});
            lottoPredictionNumbers.sort(function (a, b) {
                return a - b;
            });
            var requestPredictionNumber1 = lottoPredictionNumbers[0],
                requestPredictionNumber2 = lottoPredictionNumbers[1],
                requestPredictionNumber3 = lottoPredictionNumbers[2],
                requestPredictionNumber4 = lottoPredictionNumbers[3],
                requestPredictionNumber5 = lottoPredictionNumbers[4],
                requestPredictionNumber6 = lottoPredictionNumbers[5],
                requestBonusNumber = lottoPredictionNumbers[6];
            break;
        default:
            return res.json({isSuccess: false, errorMessage: "알고리즘 선택 값이 잘못되었습니다."});
    }

    pool.getConnection(function (err, connection) {
        connection.query({
                sql: 'UPDATE ALGORITHM_TYPE \
                FROM USER_INFO \
                WHERE ALGORITHM_TYPE = ? \
                AND PHONE_NUMBER = ?',
                timeout: 10000
            },
            [requestAlgorithmType, requestPhoneNumber],
            function (error, results, columns) {
                connection.release();

                if (error) {
                    logger().info('로또 추천 조회 - 에러코드 : ' + error.code + ', 에러내용 : ' + error.sqlMessage);
                    return res.json({
                        isSuccess: false, errorMessage: "데이터베이스 오류 : " + error.sqlMessage,
                        results: [{
                            ALGORITHM_TYPE: requestAlgorithmType
                        }]
                    });
                }

                if (!results.length) {
                    return res.json({
                        isSuccess: false, errorMessage: "선택한 알고리즘이 존재하지 않습니다.",
                        results: [{
                            ALGORITHM_TYPE: requestAlgorithmType
                        }]
                    });
                }
                logger().info('로또 추천 번호 조회 - 알고리즘 : ' + requestAlgorithmType);
                /*
                console.log('1 번째 추천 번호 : ' + requestPredictionNumber1 + '\n2 번째 추천 번호 : ' + requestPredictionNumber2 + '\n3 번째 추천 번호 : ' + requestPredictionNumber3 +
                    '\n4 번째 추천 번호 : ' + requestPredictionNumber4 + '\n5 번째 추천 번호 : ' + requestPredictionNumber5 + '\n6 번째 추천 번호 : ' + requestPredictionNumber6 +
                    '\n보너스 번호 : ' + requestBonusNumber);
                */
                res.json({isSuccess: true, errorMessage: ""});

                return res.json({isSuccess: true, errorMessage: "", results: results});
            });
    });
};