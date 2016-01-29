// app.js
var routerApp = angular.module('routerApp', [ 'ui.router','ui.bootstrap']);

routerApp
		.config(function($stateProvider, $urlRouterProvider) {

			$urlRouterProvider.otherwise('/add');

			$stateProvider
					.state(
							'add',
							{
								url : '/add',
								templateUrl : 'employeeadd.html',
								controller : 'employeeadd'
							})
					.state(
							'view',
							{
								url : '/view',
								templateUrl : 'employeeview.html',
								controller : 'employeeview'
							});

		});
