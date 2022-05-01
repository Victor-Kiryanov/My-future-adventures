const selectElem = document.getElementById('select');
const registrationImage = document.getElementById('planet-img');

selectElem.addEventListener('change', function() {
switch(true) {
	case this.value === 'Venus' : registrationImage.src = "./Images/Venus-animation.svg";
    break;
	case this.value === 'Earth' : registrationImage.src = "./Images/Earth-animation.svg";
    break;
	case this.value === 'Mars' : registrationImage.src = "./Images/Mars-animation.svg";
    break;
    case this.value === 'Mercury' : registrationImage.src = "./Images/Mercury-animation.svg";
    break;
	case this.value === 'Neptune' : registrationImage.src = "./Images/Neptune-animation.svg";
    break;
    case this.value === 'Uranus' : registrationImage.src = "./Images/Planet (Uran-animation).svg";
    break;
}
})

function animationPlanet() {
    let parametr = {
		radius: 150, 
		speed: 45}

	let angleSum = 0;
	let angle = 2 * Math.PI / 180;

	setInterval(function() {
		angleSum += angle;
		registrationImage.style.left = 100 + parametr.radius * Math.sin(angleSum)  + 'px';
		registrationImage.style.top =  100 + parametr.radius * Math.cos(angleSum) + 'px';
	}, parametr.speed)
}
