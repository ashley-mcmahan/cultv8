angular.module("cultv8App").controller("cropWizardController", function ($scope, $http, $routeParams, $location) {
  // console.log("Crop Wizard Loaded...");
  $scope.isUpdating = false;

  $scope.cropInit = {
    cropName: "",
    scientificName: "",
    cropLink: "",
    cropImage: "",
    medianDaysToFirstHarvest: 0,
    medianDaysToLastHarvest: 0,
    medianLifespan: 0,
    perennial: false,
  }; 

  if ($routeParams.id) {
    $http.get(`http://localhost:8080/cultv8/webapi/crop/${$routeParams.id}`)
    .then(successCallback, errorCallback);

    function successCallback(response) {
      // $scope.$emit("ToastEvent", { message: `Successfully retrieved ${response.data.cropName}.` });
      $scope.isUpdating = true;

      $scope.crop = {
        cropName: response.data.cropName,
        scientificName: response.data.scientificName,
        cropLink: response.data.cropLink,
        cropImage: response.data.cropImage,
        medianDaysToFirstHarvest: response.data.medianDaysToFirstHarvest,
        medianDaysToLastHarvest: response.data.medianDaysToLastHarvest,
        medianLifespan: response.data.medianLifespan,
        perennial: response.data.perennial,
      };
    }
    function errorCallback(error) {
      $scope.$emit("ToastEvent", { message: "Error retreiving crop.", error: true });
      $location.path("/");
    }
  } else {
    $scope.crop = { ...$scope.cropInit };
  }

  $scope.submitCrop = () => {
    if ($scope.isUpdating) {
      updateCrop();
    } else {
      createCrop();
    }
  };

  function createCrop() {
    $http
      .post("http://localhost:8080/cultv8/webapi/crop", $scope.crop)
      .then(successCallback, errorCallback);

    function successCallback(response) {
      $scope.$emit("ToastEvent", { message: "Successfully created crop!" });
      $location.path("/");
    }
    function errorCallback(error) {
      $scope.$emit("ToastEvent", { message: "Error creating crop!", error: true });
    }
  }

  function updateCrop() {
    $scope.crop.id = +$routeParams.id;

    $http
      .put("http://localhost:8080/cultv8/webapi/crop", $scope.crop)
      .then(successCallback, errorCallback);

    function successCallback(response) {
      $scope.$emit("ToastEvent", { message: "Successfully updated crop!" });
      $location.path(`/crop-info/${$routeParams.id}`);
    }
    function errorCallback(error) {
      $scope.$emit("ToastEvent", { message: "Error updating crop!", error: true });
    }
  }
});
