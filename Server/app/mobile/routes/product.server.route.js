module.exports = function(app){
    var reward = require(process.cwd() + '/app/mobile/controllers/product.server.controller');
    app.get('/', reward.render);
    app.get('/reward/list', reward.reward_list_info);
    app.route('/reward/list')
        .post(reward.reward_exchange_info);
};