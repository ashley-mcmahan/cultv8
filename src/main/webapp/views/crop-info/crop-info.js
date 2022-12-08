angular.module("cultv8App").controller("cropInfoController", function ($scope, $http, $routeParams, $location) {
  // console.log("Crop Info Loaded...");  
  //  console.log("Params: ", $routeParams);

  $http.get(`http://localhost:8080/cultv8/webapi/crop/${$routeParams.id}`)
  .then(successCallback, errorCallback);

  function successCallback({ data }) {
    if(data) {
      $scope.crop = data;
    } else {
      $location.path("/");
    }
  }
  function errorCallback(error) {
    $scope.$emit("ToastEvent", { message: "Error retreiving crop.", error: true });
    $location.path("/");
  }
});