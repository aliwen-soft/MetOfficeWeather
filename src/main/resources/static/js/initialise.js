var locations = rawLocationData.Locations.Location.map(locationObj => locationObj.name);
var numLocations = locations.length;

var locationInput = document.getElementById('location-input');
var locationDropdownMenu = document.getElementById('location-dropdown-menu');

var maximumMatchesDisplayed = 5;
var cutOffScore = 0.5;

var tempAlerts = document.querySelectorAll('.alert-temporary');

setTimeout(removeTemporaryAlerts, 3000);

locationInput.addEventListener('focus', removeTemporaryAlerts);

locationInput.addEventListener('input', function() {
    if (locationInput.value.length > 1) {
        var bestMatches = findMatchesOrderedBySuitability(locationInput.value);
        if (bestMatches.length > 0) {
            updateDropdownOptions(bestMatches);
            locationDropdownMenu.classList.add('show');
            return;
        }
    }
    locationDropdownMenu.classList.remove('show');
});

locationInput.addEventListener('blur', function() {
    setTimeout(function() {
        if (!locationDropdownMenu.contains(document.activeElement))
            locationDropdownMenu.classList.remove('show');
    }, 100);
});

function removeTemporaryAlerts() {
    tempAlerts.forEach(tempAlert => {
        tempAlert.style.opacity = 0;
    });
}

function updateDropdownOptions(bestMatchingLocations) {
    var newHTML = bestMatchingLocations
        .map(location => `<a class="dropdown-item" href="?location=${location.replace(/\s+/g, '+')}#forecast">${location}</a>`)
        .join('\n');
    locationDropdownMenu.innerHTML = newHTML;
}

function findMatchesOrderedBySuitability(inputText) {
    var locationScores = locations.map(location => stringSimilarity.compareTwoStrings(inputText, location));
    var locationScoresSorted = [...locationScores]
        .sort()
        .slice(numLocations - maximumMatchesDisplayed)
        .reverse()
        .filter((score, index) => (score >= cutOffScore || maximumMatchesDisplayed + 1 - index > inputText.length));
    var bestMatchIndices = getIndicesOf(locationScores, locationScoresSorted);
    return [...new Set(bestMatchIndices.map(matchIndex => locations[matchIndex]))].slice(0, maximumMatchesDisplayed);
}

function getIndicesOf(array, subset) {
    var uniqueSubset = [...new Set(subset)];
    var indices = [];
    uniqueSubset.forEach(item => {
        var currentIndex = array.indexOf(item);
        do {
            indices.push(currentIndex);
            currentIndex = array.indexOf(item, currentIndex + 1);
        } while (currentIndex > -1);
    });
    return indices;
}
