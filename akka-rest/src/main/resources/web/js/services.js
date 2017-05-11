
movieApp
.factory('MovieService', ['$http', function ($http) {

    var urlBase = 'http://localhost:8897';
    var MovieService = {};

    MovieService.listMovies = function () {
        return $http.get(urlBase+'/movies');
    };


    MovieService.getMovieDetails = function (firstval) {
        return $http.get(urlBase+'/movie', {params: {movieId: firstval} });
    };

    return MovieService;
}]);