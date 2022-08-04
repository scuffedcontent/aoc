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


