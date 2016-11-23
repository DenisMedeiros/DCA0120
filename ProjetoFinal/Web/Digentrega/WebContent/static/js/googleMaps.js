// This example adds a search box to a map, using the Google Place Autocomplete
// feature. People can enter geographical searches. The search box will return a
// pick list containing a mix of places and predicted search terms.

var map; //Will contain map object.
var marker = false; ////Has the user plotted their location marker? 

function initAutocomplete() {
  var map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: -5.8418547, lng: -35.2108532},
    zoom: 13,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  });

  // Create the search box and link it to the UI element.
  var input = document.getElementById('pac-input');
  var searchBox = new google.maps.places.SearchBox(input);
  map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

  // Bias the SearchBox results towards current map's viewport.
  map.addListener('bounds_changed', function() {
    searchBox.setBounds(map.getBounds());
  });

  var markers = [];
  // Listen for the event fired when the user selects a prediction and retrieve
  // more details for that place.
  searchBox.addListener('places_changed', function() {
    var places = searchBox.getPlaces();

    if (places.length == 0) {
      return;
    }

    // Clear out the old markers.
    markers.forEach(function(marker) {
      marker.setMap(null);
    });
    markers = [];

    // For each place, get the icon, name and location.
    var bounds = new google.maps.LatLngBounds();
    places.forEach(function(place) {
      var icon = {
        url: place.icon,
        size: new google.maps.Size(71, 71),
        origin: new google.maps.Point(0, 0),
        anchor: new google.maps.Point(17, 34),
        scaledSize: new google.maps.Size(25, 25)
      };

      // Create a marker for each place.
      markers.push(new google.maps.Marker({
        map: map,
        icon: icon,
        title: place.name,
        position: place.geometry.location
      }));

      if (place.geometry.viewport) {
        // Only geocodes have viewport.
        bounds.union(place.geometry.viewport);
      } else {
        bounds.extend(place.geometry.location);
      }
    });
    map.fitBounds(bounds);
  });

  //Listen for any clicks on the map.
  google.maps.event.addListener(map, 'click', function(event) {                
      //Get the location that the user clicked.
      var clickedLocation = event.latLng;
      //If the marker hasn't been added.
      if(marker === false){
          //Create the marker.
          marker = new google.maps.Marker({
              position: clickedLocation,
              map: map,
              draggable: true //make it draggable
          });
          //Listen for drag events!
          google.maps.event.addListener(marker, 'dragend', function(event){
              markerLocation();
          });
      } else{
          //Marker has already been added, so just change its location.
          marker.setPosition(clickedLocation);
      }
      //Get the marker's location.
      markerLocation();
  });
  
  function markerLocation(){
	    //Get location.
	    var currentLocation = marker.getPosition();
	    //Add lat and lng values to a field that we can save.
	    document.getElementById('latitude').value = currentLocation.lat(); //latitude
	    document.getElementById('longitude').value = currentLocation.lng(); //longitude
	}
  
  
  
  
}
