(function () {
  $('#submit-button').click(function () {
    var name = $('#name-input').val();
    var nameTokens = name.trim().split(/\s+/);
    var firstName = nameTokens[0];
    var lastName = nameTokens[1];
    var email = $('#email-input').val();
      
    app.ParticipantModel.create(firstName, lastName, email)
      .done(function (response) {
        
      })
      .always(function () {
        location.pathname = '/';
      });
  });
})();
