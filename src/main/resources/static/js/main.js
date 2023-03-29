"use strict";

$(document).keyup(function(event){
    console.log(event.keyCode);
});

document.addEventListener('keydown', function(e) {
    if (e.keyCode == 71) {
        document.getElementById('audio').play();
    }
});


document.addEventListener('keydown', function (e) {

// for starting the confetti
    const start = () => {
        alert("test")
        setTimeout(function() {
            confetti.start()
        }, 1000); // 1000 is time that after 1 second start the confetti ( 1000 = 1 sec)
    };

//  for stopping the confetti
    const stop = () => {
        setTimeout(function() {
            confetti.stop()
        }, 5000); // 5000 is time that after 5 second stop the confetti ( 5000 = 5 sec)
    };

// after this here we are calling both the function so it works
    if (e.keyCode == 71) {
        console.log("Before")
        document.getElementById('postSubmit').start();
        // start();
        // stop();
        console.log("After")
    }

})