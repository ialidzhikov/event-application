(function () {
  
  function clearForm() {
    $('#registration-form').find('input[type=text], input[type=email]').val('');
    $('.has-success').removeClass('has-success');
    $('.glyphicon-ok').hide();
    $('input:checkbox').prop('checked', false);
    $('#submit-button').prop('disabled', true);
  }
  
  $('#submit-button').click(function () {
    var name = $('#name-input').val();
    var nameTokens = name.trim().split(/\s+/);
    var firstName = nameTokens[0];
    var lastName = nameTokens[1];
    var email = $('#email-input').val();

    app.ParticipantModel.create(firstName, lastName, email)
      .done(function (response) {
        $('#success-alert').show(500).delay(4000).fadeOut();
      })
      .error(function () {
        $('#error-alert').show(500).delay(4000).fadeOut();
      })
      .always(function () {
        clearForm();
      });
  });
  
  var checkbox = $('#terms-and-conditions');
  $('#btn-decline').click(function () {
    $('#terms-and-conditions').prop('checked', false);
    checkbox.change();
  });

  $('#btn-accept').click(function () {
    $('#terms-and-conditions').prop('checked', true);
    checkbox.change();
  });
})();
