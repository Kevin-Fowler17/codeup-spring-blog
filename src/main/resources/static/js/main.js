"use strict";

$(document).keyup(function(event){
    console.log(event.keyCode);
});

document.addEventListener('keydown', function(e) {
    if (e.keyCode == 71) {
        document.getElementById('audio').play();
    }
});