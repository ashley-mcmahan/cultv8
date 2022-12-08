angular.module("cultv8App").component("deleteModal", {
    templateUrl: "./components/delete-modal/delete-modal.html",
    controller: function DeleteModalController($scope, $http, $location, $routeParams) {
        $scope.onDelete = () => {
            $http.delete(`http://localhost:8080/cultv8/webapi/crop/${$routeParams.id}`)
            .then(successCallback, errorCallback);
        
            function successCallback(response) {
              $scope.$emit("ToastEvent", { message: "Successfully deleted crop!" });
              finishAndRedirect();
            }
            function errorCallback(error) {
              $scope.$emit("ToastEvent", { message: "Error deleting crop.", error: true });
              finishAndRedirect();
            }
          }

          function finishAndRedirect() {
            const deleteModal = bootstrap.Modal.getInstance(document.getElementById('delete-modal'));
            deleteModal.hide();
            $location.path("/");
          }
    }
})