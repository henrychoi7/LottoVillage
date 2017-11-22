module.exports = function(app){
	var product = require(process.cwd() + '/app/mobile/controllers/product.server.controller');
	app.get('/', product.render);
};