var app = angular.module("Knjizara", ["ngRoute"]);

app.controller("ctrl", function($scope){
	$scope.message("POCETNA");
});

app.controller("knjigeCtrl", function($scope, $http, $location){
	
	var baseUrl = "/api/knjige";
	var baseUrlIzd = "/api/izdavaci";
	$scope.knjige = [];
	$scope.izdavaci = [];
	
	$scope.knjiga = {};
	$scope.knjiga.naziv = "";
	$scope.knjiga.pisac = "";
	$scope.knjiga.isbn = "";
	$scope.knjiga.cena = "";
	$scope.knjiga.kolicina = "";
	$scope.knjiga.izdavacId = "";
	$scope.pageNum = 0;
	$scope.totalPages = 1;

	$scope.pretraga = {};
	$scope.pretraga.naziv = "";
	$scope.pretraga.pisac = "";
	$scope.pretraga.kolicina = "";
	
	$scope.novaKupovina = {};
	$scope.novaKupovina.brojKnjiga = "";
	$scope.novaKupovina.ukupnaCena = 0;
	
	
	var getKnjige = function(){
	var config = { params : {}};

		if ($scope.pretraga.naziv != "") {
			config.params.naziv = $scope.pretraga.naziv;
		}
		if ($scope.pretraga.pisac != "") {
			config.params.pisac = $scope.pretraga.pisac;
		}
		if ($scope.pretraga.kolicina != "") {
			config.params.kolicina = $scope.pretraga.kolicina;
		}

		config.params.pageNum = $scope.pageNum;
	 
		var promise = $http.get(baseUrl, config);
		promise.then(
			function success(res){
				//console.log(res);
				$scope.knjige = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
	}
	getKnjige();
	
	$scope.pretraga = function(){
		$scope.pageNum = 0;
		getKnjige();
		
	}
	
	var getIzdavaci = function() {
		$http.get(baseUrlIzd).then(function success(res) {
			$scope.izdavaci = res.data;
		}, function error(res) {
			alert("Something went wrong");
		});
	}

	getIzdavaci();
	
	$scope.add = function(){
		var promise = $http.post(baseUrl, $scope.novaKnjiga);
		promise.then(
			function success(){
				getKnjige();
				$scope.novaKnjiga.naziv = "";
				$scope.novaKnjiga.pisac = "";
				$scope.novaKnjiga.isbn = "";
				$scope.novaKnjiga.cena = "";
				$scope.novaKnjiga.kolicina = "";
				$scope.novaKnjiga.izdavacId = "";
			},
			function error(){
				console.log("Could not create");
			}
		);
	}
	
	$scope.delete = function(id){
		var promise = $http.delete(baseUrl + "/" + id);
		promise.then(
			function success(){
				getKnjige();
			},
			function error(){
				alert("Could not delete knjiga.");
			}
		);
	}
	$scope.goToEdit = function(id){
		$location.path("/knjige/edit/" + id);
	}
	$scope.go = function(direction) {
		$scope.pageNum = $scope.pageNum + direction;
		getKnjige();
	}
	
});

app.controller("editKnjigaCtrl", function($scope, $routeParams, $http, $location){
	
	//console.log($routeParams);
	var knjigaId = $routeParams.aid;
	var baseUrl = "/api/knjige";
	
	$scope.knjiga = {};
	$scope.knjiga.naziv = "";
	$scope.knjiga.pisac = "";
	$scope.knjiga.isbn = "";
	$scope.knjiga.cena = "";
	$scope.knjiga.kolicina = "";
	$scope.knjiga.izdavacId = "";
	
	
	// trazenje pojedinacne
	var getKnjiga = function(){
		
		$http.get(baseUrl + "/" + knjigaId).then(
			function success(res){
				$scope.knjiga = res.data;
			},
			function error(res){
				console.log("Something went wrong!");
			}	
		);
	}
	
	getKnjiga();
	
	$scope.edit = function(){
		$http.put(baseUrl + "/" + knjigaId, $scope.knjiga).then(
			function success(res){
				//alert("Uspeh");
				$location.path("/knjige");
			},
			function error(res){
				alert("Something went wrong");
			}
		);
	}
	
	
});


app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/partial/home.html',
			controller: 'ctrl'
		})
		.when('/knjige', {
			templateUrl : '/app/html/partial/knjige.html',
			controller: 'knjigeCtrl'
		})
		.when('/knjige/edit/:aid', {
			templateUrl : '/app/html/partial/edit-knjiga.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);