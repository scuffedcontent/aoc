import fetch from 'node-fetch';

async function getDate() {
    const rootURL = "https://api.chess.com/pub/player/"
    const playerGame = "yankeebastid/games/2022/05";
    const response = await fetch(rootURL+playerGame);
    const data = await response.json();
    const pgnArray = data.games[0].pgn.split('\n');
    const date = pgnArray[2].split('[Date "')[1].split('"]')[0];
    console.log(date);
}

async function getPlayerData(player) {
    const rootURL = `https://api.chess.com/pub/player/${player}`
    const response = await fetch(rootURL);
    const data = await response.json();
    console.log(data);
}
// insert the player you want to return as a string
// getPlayerData('')
// getDate();

// Returns formatted : 2022.05.02
