angular.module("cultv8App").component("toastContainer", {
  bindings: {
    toastText: "<",
    toastError: "<",
    toastTimestamp: "<",
  },
  templateUrl: "./components/toast-container/toast-container.html",
  controller: function ToastContainerController($scope, $attrs) {
    $scope.textToShow = "";
    $scope.isError = false;

    this.$onChanges = function (changes) {
        // This is triggered because toastTimestamp is updated everytime by the event in index.js
        // This allows the toast to redisplay the same message/error if the contents are the same
        $scope.textToShow = this.toastText || "";
        $scope.isError = this.toastError || false;

        if ($scope.textToShow?.length > 0) {
            const toastLiveExample = document.getElementById('liveToast');
            const toast = new bootstrap.Toast(toastLiveExample);
            toast.show();
        }
    }
  },
});
