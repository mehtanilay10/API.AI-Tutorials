const express = require('express');
const app = express();
var bodyParser = require('body-parser');

// Enable request body parsing
app.use(bodyParser.urlencoded({
    extended: true,
    limit: '100kb'
}));

// Enable request body parsing in JSON format
app.use(bodyParser.json({
    limit: '100kb'
}));

app.post('/calc', function(req, res) {
    var parameters = req.body.result.parameters;
    if (parameters.num1 !== undefined && parameters.num2 !== undefined) {
        var speech = `Sum of ${parameters.num1} and ${parameters.num2} is ${parseInt(parameters.num1) + parseInt(parameters.num2)}`;
        res.status(200).send({
            speech: speech,
            displayText: speech
        }).end();
    } else {
        res.status(200).send({
            message: "Error in Input"
        }).end();
    }
});

// Listen app
const PORT = process.env.PORT || 8080;
app.listen(PORT, function() {
    console.log('App listening on Port: ' + PORT);
    console.log('Press Ctrl+C to quit.');
});
