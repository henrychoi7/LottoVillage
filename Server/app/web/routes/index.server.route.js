module.exports = function(app){
	var index = require(process.cwd() + '/app/web/controllers/index.server.controller');
	app.get('/', index.render);
    app.get('/lottoVillage_counselAmin', index.counselAmin);
    app.get('/lottoVillage_login', index.login);
    app.get('/lottoVillage_main', index.main);
    app.get('/lottoVillage_rewardAdmin', index.rewardAdmin);
    app.get('/lottoVillage_rewardModify', index.rewardModify);
    app.get('/lottoVillage_rewardRegist', index.rewardRegist);
    app.get('/lottoVillage_userAdmin', index.userAdmin);
    app.get('/lottoVillage_userDetails', index.userDetails);
};