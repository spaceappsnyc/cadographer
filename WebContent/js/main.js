if (!Detector.webgl) {
	Detector.addGetWebGLMessage();
}

var container, stats, camera, controls, scene, renderer, solarSystem;

Global = {};

Global.init = function () {
	var self = this;
	camera = new THREE.PerspectiveCamera(10, window.innerWidth / window.innerHeight, 19, 6000000);
	camera.position.set(300000, 60000, 500);
	camera.rotation.set(Math.PI/4, 0, 0);
	scene = new THREE.Scene();

	renderer = new THREE.WebGLRenderer({ antialias: true });
	renderer.setSize(window.innerWidth, window.innerHeight);
	renderer.domElement.style.position = 'absolute';
	renderer.domElement.style.top = '0';
	container = document.getElementById('container');
	container.appendChild(renderer.domElement);

	window.addEventListener('resize', self.onWindowResize, false );

	Tools.addCoordinateAxes(scene, 150);
	Tools.trackballControl(scene);
	self.animate();
	self.generateMap();
};

Global.generateMap = function () {
	solarSystem = new System({
		name: 'Sol',
		radius: 10000000,
		scene: scene,
		matrice: true,
		star: {
			radius: 69634.2,
			rotation_time: 25,
			propagation: {
				enabled: true,
				speed: 70,
				max: 14000,
				min: 700
			},
			satellites: [
				{
					name: 'Mercury',
					radius: 2440,
					project: true,
					coordinates: [57909050, 550, 0],
					type: 'vividEarth',
					revolution_time: 88,
					rotation_time: 58,
					satellites: []
				},
				{
					name: 'Venus',
					radius: 6051,
					project: true,
					coordinates: [108208000, 550, 0],
					type: 'vividEarth',
					revolution_time: 224,
					rotation_time: 243,
					satellites: []
				},
				{
					name: 'Earth',
					radius: 6371,
					project: true,
					coordinates: [149598261, 550, 0],
					type: 'vividEarth',
					revolution_time: 365,
					rotation_time: 1,
					satellites: [
						{
							name: 'Lune',
							radius: 1737,
							coordinates: [1300, 0, 0],
							type: 'Moon',
							revolution_time: 27,
							rotation_time: 27
						}
					]
				},
				{
					name: 'Mars',
					radius: 3390,
					project: true,
					coordinates: [227939100, 550, 0],
					type: 'vividEarth',
					revolution_time: 686,
					rotation_time: 1,
					satellites: [
						{
							name: 'Phobos',
							radius: 11,
							coordinates: [1300, 0, 0],
							type: 'Moon',
							revolution_time: 0.3,
							rotation_time: 0.3
						},
						{
							name: 'Deimos',
							radius: 6.2,
							coordinates: [23463, 0, 0],
							type: 'Moon',
							revolution_time: 1.3,
							rotation_time: 1.3
						}
					]
				},
				{
					name: 'Jupiter',
					radius: 69911,
					project: true,
					coordinates: [778547200, 550, 0],
					type: 'vividEarth',
					revolution_time: 4332,
					rotation_time: 0.416,
					satellites: [
						{
							name: 'Io',
							radius: 1821,
							coordinates: [421700, 0, 0],
							type: 'Moon',
							revolution_time: 1.8,
							rotation_time: 1.8
						},
						{
							name: 'Europa',
							radius: 1560,
							coordinates: [670900, 0, 0],
							type: 'Moon',
							revolution_time: 3.5,
							rotation_time: 3.5
						},
						{
							name: 'Ganymede',
							radius: 2634,
							coordinates: [1070400, 0, 0],
							type: 'Moon',
							revolution_time: 7.1,
							rotation_time: 7.1
						},
						{
							name: 'Callisto',
							radius: 2410,
							coordinates: [1882700, 0, 0],
							type: 'Moon',
							revolution_time: 16.7,
							rotation_time: 16.7
						}
					]
				},
				{
					name: 'Saturn',
					radius: 58232,
					project: true,
					coordinates: [1433449370, 550, 0],
					type: 'vividEarth',
					revolution_time: 10759,
					rotation_time: 0.416,
					satellites: [
						{
							name: 'Titan',
							radius: 2576,
							coordinates: [1221870, 0, 0],
							type: 'Moon',
							revolution_time: 15,
							rotation_time: 15
						},
						{
							name: 'Europa',
							radius: 763.8,
							coordinates: [527108, 0, 0],
							type: 'Moon',
							revolution_time: 4.5,
							rotation_time: 4.5
						}
					]
				},
				{
					name: 'Uranus',
					radius: 25362,
					project: true,
					coordinates: [2870671400, 550, 0],
					type: 'vividEarth',
					revolution_time: 30687,
					rotation_time: 0.71,
					satellites: [
						{
							name: 'Mirando',
							radius: 235.8,
							coordinates: [129390, 0, 0],
							type: 'Moon',
							revolution_time: 1.4,
							rotation_time: 1.4
						},
						{
							name: 'Ariel',
							radius: 578.9,
							coordinates: [191020, 0, 0],
							type: 'Moon',
							revolution_time: 2.5,
							rotation_time: 2.5
						},
						{
							name: 'Umbriel',
							radius: 584.7,
							coordinates: [266000, 0, 0],
							type: 'Moon',
							revolution_time: 4.1,
							rotation_time: 4.1
						},
						{
							name: 'Titania',
							radius: 788.4,
							coordinates: [435910, 0, 0],
							type: 'Moon',
							revolution_time: 8.7,
							rotation_time: 8.7
						},
						{
							name: 'Oberon',
							radius: 761.4,
							coordinates: [583520, 0, 0],
							type: 'Moon',
							revolution_time: 13.46,
							rotation_time: 13.46
						}
					]
				},
				{
					name: 'Neptune',
					radius: 24622,
					project: true,
					coordinates: [4498542600, 550, 0],
					type: 'vividEarth',
					revolution_time: 60190,
					rotation_time: 0.67,
					satellites: [
						{
							name: 'Triton',
							radius: 1353.4,
							coordinates: [354759, 0, 0],
							type: 'Moon',
							revolution_time: 5.9,
							rotation_time: 5.9
						}
					]
				}
			]
		}
	});
}

Global.onWindowResize = function () {
	camera.aspect = window.innerWidth / window.innerHeight;
	camera.updateProjectionMatrix();

	renderer.setSize(window.innerWidth, window.innerHeight);

	if (controls) {
		controls.handleResize();
	}
};

Global.animate = function () {
	requestAnimationFrame(Global.animate);

	if (solarSystem) {
		solarSystem.parent.traverse(function (child) {
			if (child.animate) {
				child.animate();
			}
		});
	}

	if (controls) {
		controls.update();

		if (stats) {
			stats.update();
		}

		renderer.render(scene, camera);
	}
};

Global.init();
