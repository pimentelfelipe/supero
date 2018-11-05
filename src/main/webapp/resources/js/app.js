//function startMapNewLocation() {
//		if (navigator.geolocation) {
//			var locationNewLocation = new google.maps.LatLng(-15.794172, -47.882982);
//    		var latlngboundsNewLocation = new google.maps.LatLngBounds();
//	        var mapCanvasNewLocation = document.getElementById('mapNewLocationView');
//	        var mapOptionsNewLocation = {center: locationNewLocation, zoom: 10, panControl: true, scrollwheel: true, mapTypeControl: true, mapTypeId: google.maps.MapTypeId.ROADMAP};
//	        var mapNewLocation = new google.maps.Map(mapCanvasNewLocation, mapOptionsNewLocation);
//	        var markerImageNewLocation = 'images/marker.png';
//	        var markerImageYouNewLocation = 'images/marker-you.png';
//	        //var markerNewLocation = new google.maps.Marker({position: locationNewLocation, map: mapNewLocation, icon: markerImageYouNewLocation});
//	        var addressYouNewLocation = "";
//	        //latlngboundsNewLocation.extend(markerNewLocation.position);
//	        
//	        //var stylesNewLocation = [{"featureType": "landscape", "stylers": [{"saturation": -100}, {"lightness": 65}, {"visibility": "on"}]}, {"featureType": "poi", "stylers": [{"saturation": -100}, {"lightness": 51}, {"visibility": "simplified"}]}, {"featureType": "road.highway", "stylers": [{"saturation": -100}, {"visibility": "simplified"}]}, {"featureType": "road.arterial", "stylers": [{"saturation": -100}, {"lightness": 30}, {"visibility": "on"}]}, {"featureType": "road.local", "stylers": [{"saturation": -100}, {"lightness": 40}, {"visibility": "on"}]}, {"featureType": "transit", "stylers": [{"saturation": -100}, {"visibility": "simplified"}]}, {"featureType": "administrative.province", "stylers": [{"visibility": "off"}]}, {"featureType": "water", "elementType": "labels", "stylers": [{"visibility": "on"}, {"lightness": -25}, {"saturation": -100}]}, {"featureType": "water", "elementType": "geometry", "stylers": [{"hue": "#ffff00"}, {"lightness": -25}, {"saturation": -97}]}];
//	        //mapNewLocation.set('styles', stylesNewLocation);
//	        
//	        //mapNewLocation.fitBounds(latlngboundsNewLocation);
//	        
//	        
//	        google.maps.event.addListener(mapNewLocation, 'click', function(event) {
//	        	markerNewLocation = new google.maps.Marker({position: event.latLng, map: mapNewLocation});
//	        	$scope.newLocationView.txLat = event.latLng.lat();
//	        	$scope.newLocationView.txLng = event.latLng.lng();
//	        	
//	        	$scope.newLocationViewTemp = {};
//	        	$scope.newLocationViewTemp.tbDistrict = {};
//	        	
//	        	$http.get("http://maps.google.com/maps/api/geocode/json?latlng=" + $scope.newLocationView.txLat + "," + $scope.newLocationView.txLng + "&sensor=false")
//				.success(function (response) {
//					for(var i = 0; i < response.results.length; i++){
//					    var resource = response.results[i];
//					    if(resource.geometry.location_type == "ROOFTOP"){
//					    	for(var j = 0; j < resource.address_components.length; j++){
//							    var subresource = resource.address_components[j];
//							    if (subresource.types.indexOf("sublocality_level_1") > 0) {
//							    	
//							    	$scope.newLocationViewTemp.tbDistrict.txDistrictName = subresource.short_name;
//							    	$scope.newLocationViewTemp.txName = subresource.short_name;
//							    	
//							    	$http.post(serviceURIBase + "service/district/GetOneByDistrictName.php?id=" + $scope.register.id + "&key=" + $scope.register.key + "&lang=" + getCookie(langCookie),
//							    			$scope.newLocationViewTemp.tbDistrict, { headers: {'Content-Type': 'application/json'} })
//								    .success(function (subresponse) {
//								    	$scope.newLocationViewTemp.tbDistrict = subresponse;
//								    	
//								    	$scope.newLocationView.idDistrict = $scope.newLocationViewTemp.tbDistrict.idDistrict;  
//								    	$scope.newLocationView.idCity = $scope.newLocationViewTemp.tbDistrict.idCity;
//								    	$scope.newLocationView.idState = $scope.newLocationViewTemp.tbDistrict.idState;
//								    	$scope.newLocationView.idCountry = $scope.newLocationViewTemp.tbDistrict.idCountry;
//								    	
//								    	$scope.getStatesOfCountryByLocation($scope.newLocationView.idCountry);
//								    	$scope.getCitiesOfStateByLocation($scope.newLocationView.idState);
//								    	$scope.getDistrictsOfCityByLocation($scope.newLocationView.idCity);
//								    	
//								    	document.getElementById("txLocationName").focus();
//								    });
//							    }
//					    	}
//					    }
//					}
//				});
//	        });
//    	}
//	}