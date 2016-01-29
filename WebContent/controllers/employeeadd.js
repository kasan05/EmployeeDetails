angular
		.module('routerApp')
		.controller(
				'employeeadd',
				function($scope, $http) {
					$scope.addEmployee = function() {
						$http
								.get('http://localhost:8080/EmployeeDetails/rest/employees/'
										+ $scope.name
										+ "/"
										+ $scope.salary
										+ "/"
										+ $scope.depName
										+ "/"
										+ $scope.managerId
										+ "/"
										+ $scope.managerName).success(function(){
											$scope.successsubmit= true;
										}).error(function(){
											
										});

					}
				});