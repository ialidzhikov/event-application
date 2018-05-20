(function () {
  app.ParticipantModel.getAll()
    .done(function (participants) {
      participants.forEach(function (participant){
        participant.preferredLanguagesAsString = participant.preferredLanguages.join(", ");
      });

      app.Renderer.render('#main-container', 'templates/participants.html', {participants: participants});
    });

  $('#delete-modal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget),
        name = button.data('name'),
        id = button.data('id'),
        modal = $(this);

    modal.find('.participant-name').text(name);

    modal.find('#btn-delete').click(function () {
      app.ParticipantModel.remove(id)
        .done(function () {
          // TODO
        })
        .always(function () {
          modal.modal('toggle');
          location.pathname = '/participants';
        });
    });
  });
})();
