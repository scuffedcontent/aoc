const express = require('express');
const app = express();
app.listen(222, () => console.log('listening at 222'));
app.use(express.static('public'));
