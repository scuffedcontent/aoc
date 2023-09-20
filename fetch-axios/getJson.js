
async function getWithAxios() {
    const axios = require('axios');
    axios.get('http://randomuser.me/api')
        .then(function (response) {
            console.log(response)
        })
    .catch(function(error) {
            console.log(error)
    })
    .then(function() {

    });

}


async function getData(player) {
    const url = 'http://randomuser.me/api'
    const res = await fetch(url);
    const data = await res.json();
    console.log(data)
}

//getWithAxios()

getData();

async function getWithAxios() {
    const axios = require('axios');
    axios.get('http://randomuser.me/api')
        .then(function (response) {
            console.log(response)
        })
    .catch(function(error) {
            console.log(error)
    })
    .then(function() {

    });

}


async function getData() {
    const url = 'http://randomuser.me/api'
    const res = await fetch(url);
    const data = await res.json();
    console.log(data)
}

getWithAxios()

//getData();

<<<<<<< HEAD:fetch-axios/getJson.js
=======
async function getWithAxios() {
    const axios = require('axios');
    axios.get('http://randomuser.me/api')
        .then(function (response) {
            console.log(response)
        })
    .catch(function(error) {
            console.log(error)
    })
    .then(function() {

    });

}


async function getData() {
    const url = 'http://randomuser.me/api'
    const res = await fetch(url);
    const data = await res.json();
    console.log(data)
}

getWithAxios()

//getData();


>>>>>>> 12362e50a9cccc93485c126b395fa10403051f39
=======
>>>>>>> c0da68f490776a54af5dcce9f534d3c3dcbef716:axiosFetch/getJson.js
