(function () {

  function clearForm() {
    $('#registration-form').find('input[type=text], input[type=email]').val('');
    $('.has-success').removeClass('has-success');
    $('.glyphicon-ok').hide();

    $('#university-input').val('sofiaUniversity').trigger('change');
    $('#year-of-education-input').val('1');

    $('#internship-input').prop('checked', true);
    $('#job-input').prop('checked', false);
    
    $('input:checkbox').prop('checked', false);
    $('#submit-button').prop('disabled', true);
  }

  function fillProgramOptions(options) {
    var programInput = $("#program-input");
      programInput.empty();
      $.each(options, function(index, value) {
        programInput.append($("<option></option>").attr("value", index).text(value));
      });
  }
  
  $('#submit-button').click(function () {
    var name = $('#name-input').val();
    var nameTokens = name.trim().split(/\s+/);
    var firstName = nameTokens[0];
    var lastName = nameTokens[1];
    var email = $('#email-input').val();
    var university = $('#university-input option:selected').text();
    var program = $('#program-input option:selected').text();
    var yearOfEducation = parseInt($('#year-of-education-input').val());
    var preferredLanguages = $('input[name="preferredLanguages"]:checked').map(function() {
        return this.value;
    }).get();
    var interestedAbout = $('input:radio[name="interestedAbout"]:checked').val();

    app.ParticipantModel.create(firstName, lastName, email, university, program,
                                yearOfEducation, preferredLanguages, interestedAbout)
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

  $('#university-input').change(function () {
    var university = $('#university-input option:selected').val();

    if (university === 'sofiaUniversity') {
      var options = ['Компютърни науки', 'Софтуерно инженерство', 'Информационни системи', 'Информатика', 'Приложна математика', 'Математика', 'Статистика', 'Друга'];
      fillProgramOptions(options);
    } else if (university === 'technicalUniversity') {
      var options = ['Компютърни системи и технологии', 'Компютърно и софтуерно инженерство', 'Информатика и софтуерни науки', 'Друга'];
      fillProgramOptions(options);
    } else if (university === 'other') {
      var options = ['Друга'];
      fillProgramOptions(options);
    }
  });
})();
