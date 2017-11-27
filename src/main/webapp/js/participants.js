(function () {
  app.ParticipantModel.getAll()
   .done(function (participants) {
     app.Renderer.render('#main-container', 'templates/participants.html', {participants: participants});
   });
})();
