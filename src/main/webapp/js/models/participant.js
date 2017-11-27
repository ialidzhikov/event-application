'use strict';

var app = app || {};

app.ParticipantModel = (function () {

  function getAll() {
    return $.ajax({
      method: 'GET',
      url: 'api/participants',
      dataType: 'json'
    });
  }

  function create(firstName, lastName, email) {
    return $.ajax({
        method: 'POST',
        url: 'api/participants',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            firstName: firstName,
            lastName: lastName,
            email: email
        })
      });
  }
  return {
    getAll: getAll,
    create: create
  }
}());
