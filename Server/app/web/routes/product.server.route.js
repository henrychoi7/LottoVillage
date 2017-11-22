module.exports = function(app){
	var product = require(process.cwd() + '/app/web/controllers/product.server.controller');
	app.get('/', product.render);
};