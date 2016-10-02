/**
 * Adds a new input field
 */

//Contact number counter
var contactNumberCounter = 1;

$('#add-new-contact-number-button').click(function() {
    //Increasing the contact number counter
    contactNumberCounter++;
    
    //Appending the new HTML part
    $('#manage-organisation-phone-numbers').append('<!-- Phone number '+contactNumberCounter+' --><div class="row"><div class="col-xs-4"><div class="form-group"><label for="organization-phone-number-'+contactNumberCounter+'">Phone number '+contactNumberCounter+'</label><input type="text" class="form-control" id="organization-phone-number-'+contactNumberCounter+'" placeholder=""><p class="margin-top-10 margin-bottom-30 color-light-gray">Please provide contact phone number</p></div></div></div>');
});