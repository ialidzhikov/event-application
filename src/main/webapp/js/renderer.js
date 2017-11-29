'use strict';

var app = app || {};

app.Renderer = (function () {

  function render(selector, path, data) {
    return $.get(path, function (template) {
      var html = Mustache.render(template, data);
      $(selector).append(html);
    });
  }

  return {
    render: render
  };
}());
