'use strict';

const functions = require('firebase-functions');
const admin = require('firebase-admin');

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

        /*var dataMessage = {

            android: {
                ttl: (3600 * 1000) * (3), // 3 Hours in Milliseconds

                priority: 'high',
            },

            data: {
                "PostTile": postTile,
                "PostSummary": postSummary,
                "PostColor": postColor
            },

            topic: 'BETA',
        };*/

        /*admin.messaging().send(dataMessage).then((response) => {
            console.log('Successfully Sent', response);

            

        }).catch((error) => {
            console.log('Error Sending Message', error);
        });*/

        res.status(200).send('Title: ' + postTitle + '<br/>' + 'Summary: ' + postSummary + '<br/>' + 'Color: ' + postColor);
    };
    xmlHttpRequest.send();

});