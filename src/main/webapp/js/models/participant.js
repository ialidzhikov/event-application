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
      contentType: 'application/json',
      data: JSON.stringify({
        firstName: firstName,
        lastName: lastName,
        email: email
      })
    });
  }

  function remove(id) {
    return $.ajax({
      method: 'DELETE',
      url: 'api/participants/' + id
    });
  }
  
  return {
    getAll: getAll,
    create: create,
    remove: remove
  };
}());
