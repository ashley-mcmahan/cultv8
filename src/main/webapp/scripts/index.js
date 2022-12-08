const app = angular.module("cultv8App", ["ngRoute"]);

app.config(function($routeProvider) {
  $routeProvider
  .when("/", {
      templateUrl: "./views/crop-table/crop-table.html",
      controller: "cropTableController"
  })
  .when("/crop-wizard", {
      templateUrl: "./views/crop-wizard/crop-wizard.html",
      controller: "cropWizardController"
  })
  .when("/crop-wizard/:id", {
    templateUrl: "./views/crop-wizard/crop-wizard.html",
    controller: "cropWizardController"
  })
  .when("/crop-info/:id", {
      templateUrl: "./views/crop-info/crop-info.html",
      controller: "cropInfoController"
  })
  .when("/home", {
      templateUrl: "./views/home/home.html"
  });
});

app.controller("cultv8Controller", function ($scope, $route, $rootScope) {
  $scope.toastText = "";
  $scope.toastError = false;
  $scope.toastTimestamp = Date.now();

  $scope.$on("ToastEvent", function(evt, data) {
    $scope.toastText = data.message || "";
    $scope.toastError = data.error || false;
    $scope.toastTimestamp = Date.now();
  })
});
