var idCookie = 'superoAppId';
var keyCookie = 'superoAppKey';
var serviceURIBase = 'http://localhost:8080/supero/ws/';






function checkUser($scope, $http) {
	$scope.register = {id: "", key: ""};
	$scope.verifyUserKey = function() {
		$scope.register.id = getCookie(idCookie);
		$scope.register.key = getCookie(keyCookie)
		if ($scope.register.id != "" && $scope.register.key != "" && 
				$scope.register.id != null && $scope.register.key != null &&
				$scope.register.id != "null" && $scope.register.key != "null") {
			$http.get(serviceURIBase + "TbUser/validateToken/" + $scope.register.id + "/" + $scope.register.key)
		    .success(function (response) {
		    	$scope.register.success = response.success;
		    	if ($scope.register.success == true) {
		    		window.open("indexin.html","_self")
		    	}
		    });
		}
	};
}

function submitLogin($scope, $http) {
	$scope.register = {txUserEmail: "", txPassword: "" };
	$scope.registerToSend = {txUserName: "", txPassword: ""};
	$scope.submitLogin = function() {
		if ($scope.register.txUserEmail != "" && $scope.register.txPassword != "") {
			$('#loadingSubmitLogin').show();
			$scope.registerToSend.txUserName = $scope.register.txUserEmail;
			var jsShaTxPassword = new jsSHA($scope.register.txPassword);
			$scope.registerToSend.txPassword = jsShaTxPassword.getHash("SHA-512", "HEX");
			$http.post(serviceURIBase + "TbUser/authenticate", $scope.registerToSend, { headers: {'Content-Type': 'application/json'} })
		    .success(function (response) {
		    	if (response.success == true) {
		    		setCookie(keyCookie, response.entity.txKey, 24);
		    		setCookie(idCookie, response.entity.id, 24);
		    		$('#userLoginFail').hide();
		    		
		    		window.open("indexin.html","_self")
		    	} else {
		    		$('#userLoginFail').show();
		    	}
		    	$('#loadingSubmitLogin').hide();
		    });
		}
	};
}


angular.module('Index', [])
.controller('checkUser', checkUser)
.controller('submitLogin', submitLogin);
















function getUser($scope, $http) {
	$scope.register = {id: "", key: "", txUserName: "", txPersonName: "", txUserEmail: "", txUserDesc: "", byPhoto: ""};
	$scope.register.id = getCookie(idCookie);
	$scope.register.key = getCookie(keyCookie)
	$scope.tbTaskList = [];
	$scope.tbTaskListStarted = [];
	$scope.tbTaskListFinished = [];
	$scope.tbTaskListDeleted = [];
	$scope.getUser = function() {
		$scope.register.id = getCookie(idCookie);
		$scope.register.key = getCookie(keyCookie);
		$scope.tbTaskList = [];
		$scope.tbTaskListStarted = [];
		$scope.tbTaskListFinished = [];
		$scope.tbTaskListDeleted = [];
		if ($scope.register.id != "" && $scope.register.key != "" && 
				$scope.register.id != null && $scope.register.key != null &&
				$scope.register.id != "null" && $scope.register.key != "null") {
			
			$http.get(serviceURIBase + "TbUser/validateToken/" + $scope.register.id + "/" + $scope.register.key)
		    .success(function (response) {
		    	if (response.success == true) {
		    		$http.get(serviceURIBase + "TbTask/")
				    .success(function (response) {
				    	if (response.success == true) {
				    		$scope.tbTaskList = response.collection;
				    		for (i = 0; i < $scope.tbTaskList.length; i++) {
				    			if ($scope.tbTaskList[i].txStatus.toLowerCase() == 'started') {
				    				$scope.tbTaskListStarted.push($scope.tbTaskList[i]);
				    			} else if ($scope.tbTaskList[i].txStatus.toLowerCase() == 'finished') {
				    				$scope.tbTaskListFinished.push($scope.tbTaskList[i]);
				    			} else if ($scope.tbTaskList[i].txStatus.toLowerCase() == 'deleted') {
				    				$scope.tbTaskListDeleted.push($scope.tbTaskList[i]);
				    			}
				    		}
				    	}
				    });
		    		
		    	} else {
		    		window.open("index.html","_self");
		    	}
		    });
		} else {
			window.open("index.html","_self");
		}
	};
}



function logout($scope, $http) {
	$scope.register = {id: "", key: ""};
	
	$scope.logout = function() {
		$scope.register.id = getCookie(idCookie);
		$scope.register.key = getCookie(keyCookie)
		if ($scope.register.id != "" && $scope.register.key != "") {
			$('#loadingLogout').show();
			$http.get(serviceURIBase + "TbUser/finalizeToken/" + $scope.register.id + "/" + $scope.register.key)
		    .success(function (response) {
		    	$scope.register.success = response.success;
		    	if ($scope.register.success == true) {
		    		setCookie(keyCookie, '', 24);
		    		setCookie(idCookie, '', 24);
		    		window.open("index.html","_self")
		    	}
		    	$('#loadingLogout').hide();
		    });
		}
	};
}


angular.module('Indexin', [])
.controller('getUser', getUser)
.controller('logout', logout);

















function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
    }
    return "";
}