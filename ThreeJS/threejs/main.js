import './style.css';
import * as THREE from 'https://unpkg.com/three@0.126.1/build/three.module.js';


const scene = new THREE.Scene();

const camera = new THREE.PerspectiveCamera(75, innerWidth / innerHeight, 0.1, 1000);

const renderer = new THREE.WebGLRenderer();

renderer.setSize(innerWidth, innerHeight);

renderer.setPixelRatio(devicePixelRatio);

document.body.appendChild(renderer.domElement);

camera.position.z = 5;

const planeGeometry = new THREE.PlaneGeometry( 5, 5, 10, 10 );

const planeMaterial = new THREE.MeshPhongMaterial( { color:0x013220, side: THREE.DoubleSide, flatShading: THREE.FlatShading } );

const planeMesh = new THREE.Mesh( planeGeometry, planeMaterial );

scene.add(planeMesh)

const {array} = planeMesh.geometry.attributes.position

    for (let i = 0; i < array.length; i+=3)
{
        const x = array[i]
        const y = array[i+1]
        const z = array[i+2]
        array[i + 2] = z + Math.random()
}


const light = new THREE.DirectionalLight(0xffffff, 1)
function animate() {
    requestAnimationFrame(animate)
    renderer.render(scene, camera)
    //mesh.rotation.x += 0.01
    //mesh.rotation.y += 0.01
    //planeMesh.rotation.x += 0.00
    //planeMesh.rotation.y += 0.01
}
light.position.set(0,0, 1)
scene.add(light)
animate()
console.log(planeMesh)
