angular.module("cultv8App").controller("cropTableController", function ($scope, $http) {
  $http
    .get("http://localhost:8080/cultv8/webapi/crop/getCrops")
    .then(successCallback, errorCallback);

  function successCallback(response) {
    $scope.crops = response.data;
  }
  function errorCallback(error) {
    $scope.$emit("ToastEvent", { message: "Error fetching crops.", error: true });
  }
});
