routerApp
		.controller(
				'employeeview',
				function($scope, $http,$uibModal,$log,sharedProperty) {
					$http
							.get(
									"http://localhost:8080/EmployeeDetails/rest/employees/listEmployees")
							.success(function(data) {
								$scope.employees = data;
							});
					$scope.edit = function(e) {
						e.edit = true;
					}
					$scope.save = function(e) {
						e.edit = false;
						$http.get(
								"http://localhost:8080/EmployeeDetails/rest/employees/updateEmployee/"
										+ e.emp_id + "/" + e.name + "/"
										+ e.salary + "/" + e.departmentName)
								.success(function() {

								});
					}
					$scope.remove = function(e) {
						sharedProperty.setRem(e.emp_id);
						$uibModal.open({
						      templateUrl: 'dialogtemplate.html',
						      controller:'dialogCtrl'
						    });
						
					}
				});

routerApp.controller('dialogCtrl',function($http,$scope,$uibModalInstance,sharedProperty){
	$scope.ok = function(){
		$http.get(
				"http://localhost:8080/EmployeeDetails/rest/employees/deleteEmployee/"
						+ sharedProperty.getRem());
		$uibModalInstance.dismiss('Ok');
		$http
		.get(
				"http://localhost:8080/EmployeeDetails/rest/employees/listEmployees").success(function(data) {
					$scope.employees = data;
					
				}).error(function(){});
		
	};
	
	
	$scope.cancel = function(){
		$uibModalInstance.dismiss('Cancel');
	};
});