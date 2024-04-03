var values = [];
var emptyPosition = 15;

function init() {
    var gameContainer = document.getElementById("game");
    for (var i = 0; i < 4; ++i) {
        for (var j = 0; j < 4; ++j) {
            piece = new Image();
            piece.src = piece.src = "./img/" + i + '-' + j + ".jpg";
            piece.className = "piece";
            piece.id = 4 * i + j;
            piece.innerText = piece.id;
            piece.onclick = function() {
                pieceClick(this);
            };
            gameContainer.appendChild(piece);
            values.push(piece);
        }
    }

    values[15].src = "./img/white.png";
    randomize(values);
    showGame();
}

function randomize(values) {
    for (var i = values.length; i > 0; i--) {
        var j = Math.floor(Math.random() * 15);
        var aux = values[i - 1];
        values[i - 1] = values[j];
        values[j] = aux;
    }
    emptyPosition = findEmptyPosition(values);
}

function findEmptyPosition(values) {
    for (var i = 0; i < values.length; ++i) {
        if (values[i].id == 15) {
            return i;
        }
    }
}

function showGame() {
    var isFinished = true;
    var gameContainer = document.getElementById("game");
    gameContainer.innerHTML = "";
    for (var i = 0; i < values.length; ++i) {
        if (values[i].id != i) {
            isFinished = false;
        }
        gameContainer.appendChild(values[i]);
    }

    if (isFinished) {
        document.getElementById("status").innerText = "The game is finished!";
    }
}

function pieceClick(piece) {
    var piecePosition = values.indexOf(piece);
    var aux = values[piecePosition];
    values[piecePosition] = values[emptyPosition];
    values[emptyPosition] = aux;
    emptyPosition = piecePosition;
    showGame();
}
