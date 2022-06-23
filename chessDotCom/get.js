import fetch from 'node-fetch';

async function getData() {
    
    const url = 'https://api.chess.com/pub/player/';
    const response = await fetch(url);
    const data = await response.json();
    const pgnArray = data.games[0].pgn.split('\n');
    const date = pgnArray[2].split('[Date "')[1].split('"]')[0];
    console.log(date);
}
getData();

// Returns formatted : 2022.05.02