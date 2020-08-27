'use strict';

const functions = require('firebase-functions');
const admin = require('firebase-admin');

admin.initializeApp({
    credential: admin.credential.applicationDefault(),
});


const XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;

const runtimeOptions = {
    timeoutSeconds: 313,
}

exports.sendNewestPostNotification = functions.runWith(runtimeOptions).https.onRequest((req, res) => {

    const postColor = req.query.postColor;

    var xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open('GET', 'https://abanabsalan.com/wp-json/wp/v2/posts?page=1&per_page=1&orderby=date', true);
    xmlHttpRequest.setRequestHeader('accept', 'application/json');
    xmlHttpRequest.setRequestHeader('Content-Type', 'application/json');
    xmlHttpRequest.onreadystatechange = function () {
        if (this.readyState == 4) {

        } else {

        }
    };
    xmlHttpRequest.onprogress = function () {

    };
    xmlHttpRequest.onload = function () {
        //xmlHttpRequest.responseText

        var jsonParserResponseText = JSON.parse(xmlHttpRequest.responseText);

        var newestPostJson = jsonParserResponseText[0];

        var postTitle = newestPostJson['title'].rendered;
        var postSummary = newestPostJson['excerpt'].rendered;

        var message = {
            notification: {
              title: postTitle,
              body: postSummary
            },
            android: {
              notification: {
                color: postColor
              }
            },
            data: {
                title: postTitle,
                summary: postSummary
            },
            topic: 'BETA',
          };
          
          admin.messaging().send(message)
            .then((response) => {
              
                res.status(200).send('Title: ' + postTitle + '<br/>' + 'Summary: ' + postSummary + '<br/>' + 'Color: ' + postColor);

            })
            .catch((error) => {

                res.status(200).send('Error: ' + error);
                
            });
          
    };
    xmlHttpRequest.send();

});