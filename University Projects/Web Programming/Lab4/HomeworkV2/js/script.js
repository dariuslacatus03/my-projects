var pieces = [];
var emptyPosition = 15;

function init() {
    var gameContainer = document.getElementById("game");
    for (var row = 0; row < 4; ++row) {
        for (var col = 0; col < 4; ++col) {
            piece = new Image();
            piece.src = piece.src = "./img/" + row + '-' + col + ".jpg";
            piece.className = "piece";
            piece.id = 4 * row + col;
            piece.innerText = piece.id;
            piece.onclick = function() {
                pieceClick(this);
            };
            gameContainer.appendChild(piece);
            pieces.push(piece);
        }
    }

    pieces[15].src = "./img/white.png";
    randomize(pieces);
    showGame();
}

function randomize(pieces) {
    for (var i = pieces.length; i > 0; i--) {
        var j = Math.floor(Math.random() * 15);
        var aux = pieces[i - 1];
        pieces[i - 1] = pieces[j];
        pieces[j] = aux;
    }
    emptyPosition = findEmptyPosition(pieces);
}

function findEmptyPosition(pieces) {
    for (var i = 0; i < pieces.length; ++i) {
        if (pieces[i].id == 15) {
            return i;
        }
    }
}

function showGame() {
    var isFinished = true;
    var gameContainer = document.getElementById("game");
    gameContainer.innerHTML = "";
    for (var i = 0; i < pieces.length; ++i) {
        if (pieces[i].id != i) {
            isFinished = false;
        }
        gameContainer.appendChild(pieces[i]);
    }

    if (isFinished) {
        document.getElementById("status").innerText = "The game is finished!";
    }
}

function pieceClick(piece) {
    var piecePosition = pieces.indexOf(piece);
    var aux = pieces[piecePosition];
    pieces[piecePosition] = pieces[emptyPosition];
    pieces[emptyPosition] = aux;
    emptyPosition = piecePosition;
    showGame();
}
