"use strict";

$(document).keyup(function(event){
    console.log(event.keyCode);
});

document.addEventListener('keydown', function(e) {
    if (e.keyCode == 71) {
        document.getElementById('audio').play();
    }
});

// let konamiCode = "ArrowUpArrowUpArrowDownArrowDownArrowLeftArrowRightArrowLeftArrowRightbaEnter"
// let konamiCode = "g";
// let konamiCheck = "";
// $(document).keyup(function(event){
//     konamiCheck = konamiCheck + event.key;
//
//     if (konamiCheck === konamiCode) {
//
//         document.getElementById('audio').play();
//         // music.play();
//
//     }
// });