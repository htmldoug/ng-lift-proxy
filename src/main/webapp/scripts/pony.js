angular.module('pony', ['lift.pony'])
    .controller('PonyCtrl', function ($scope, ponyService) {
        $scope.isRevealed = false;

        $scope.onClick = function () {
            ponyService.getBestPony().then(function(pony) {
                $scope.isRevealed = true;
                $scope.pony = pony;
            });
        };
    });
