'use strict';

(function () {
  var nameRegex = /^([a-zA-Z\u0430-\u044F\u0410-\u042F-]+)\s+([a-zA-Z\u0430-\u044F\u0410-\u042F-]+)$/;
  var emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

  function updateSubmitButton() {
    var hasValidName = $('#name-input').parent().hasClass('has-success');
    var hasValidEmail = $('#email-input').parent().hasClass('has-success');
    var hasAgreedTerms = $('#terms-and-conditions').is(":checked");

    if (hasValidName && hasValidEmail && hasAgreedTerms) {
      $('#submit-button').prop('disabled', false);
    } else {
      $('#submit-button').prop('disabled', true);
    }
  }

  $('#name-input').change(function () {
    var value = this.value.trim();
    if (nameRegex.test(value)) {
      $(this).parent().removeClass('has-error');
      $(this).parent().addClass('has-success');

      $('#name-help').hide();
      $('#name-success-icon').show();
      $('#name-error-icon').hide();
    } else {
    $(this).parent().removeClass('has-success');
      $(this).parent().addClass('has-error');
      
      $('#name-help').show();
      $('#name-success-icon').hide();
      $('#name-error-icon').show();
    }

    updateSubmitButton();
  });

  $('#email-input').change(function () {
    var value = this.value.trim();
    if (emailRegex.test(value)) {
      $(this).parent().removeClass('has-error');
      $(this).parent().addClass('has-success');

      $('#email-success-icon').show();
      $('#email-error-icon').hide();
    } else {
      $(this).parent().removeClass('has-success');
      $(this).parent().addClass('has-error');

      $('#email-success-icon').hide();
      $('#email-error-icon').show();
    }

    updateSubmitButton();
  });

  $("#terms-and-conditions").change(function() {
    updateSubmitButton();
  });
})();
