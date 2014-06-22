describe("pony", function(){
  // Service mock
  var ponyService = {};

  // Handle to the rootScope
  var rootScope = {};

  // Handle to the scope
  var scope = {};

  // Set up the pony module
  beforeEach(function(){module('pony');});

  // Mock out the service
  beforeEach(function() {
    angular.mock.module(function($provide) {
      $provide.value('lift.pony', ponyService);
    });
  });

  // Build the mock with a $q promise
  beforeEach(inject(function($q) {
    ponyService.defer = $q.defer();
    ponyService.getBestPony = function() {
      return this.defer.promise;
    };
  }));

  // Create a controller for each test
  beforeEach(inject(function($rootScope, $controller) {
    rootScope = $rootScope;
    scope = $rootScope.$new();
    $controller('PonyCtrl', {
      $scope: scope,
      ponyService: ponyService   // Specifying our service explicitly is optional
    });
  }));

  // Write a test
  it('should call the service when onClick is called', function() {
    // Before onClick, the pony will be undefined.
    expect(scope.pony).toBeUndefined();

    // Provide a pony to be returned
    var pony = {
      name: 'Doug',
      img: 'doug.jpg'
    };
    ponyService.defer.resolve(pony);

    // Simulate the click
    scope.onClick();

    // This call lets the $q callback happen
    rootScope.$digest();

    // Expect that pony has now been set.
    expect(scope.pony).toEqual(pony);
  });
});

