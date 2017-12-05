exports.render = function(req, res){
	/*if (req.session.visit){
		req.session.lastVisit = req.session.visit;
		req.session.visit = new Date();
	}

	req.session.visit = new Date();
	console.log('접속시간 : ' + req.session.visit);

	if (req.session.lastVisit !== undefined) {
		console.log('마지막접속시간 : ' + req.session.lastVisit)
	}*/

	res.render('index', {
		title: 'Hello EJS World'
	})
};

exports.counselAmin = function(req, res){
    res.counselAmin('lottoVillage_counselAmin.html');
};

exports.login = function(req, res){
    res.login('lottoVillage_login.html');
};

exports.main = function(req, res){
    res.main('lottoVillage_main.html');
};

exports.rewardAdmin = function(req, res){
    res.rewardAdmin('lottoVillage_rewardAdmin.html');
};

exports.rewardModify = function(req, res){
    res.rewardModify('lottoVillage_rewardModify.html');
};

exports.rewardRegist = function(req, res){
    res.rewardRegist('lottoVillage_rewardRegist.html');
};

exports.userAdmin = function(req, res){
    res.userAdmin('lottoVillage_userAdmin.html');
};

exports.userDetails = function(req, res){
    res.userDetails('lottoVillage_userDetails.html');
};