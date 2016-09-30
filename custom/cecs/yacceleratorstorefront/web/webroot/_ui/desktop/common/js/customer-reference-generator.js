/**
 * Generates a random string on click
 */

$('#customer-reference-generator-button').click(function(e) {
   //Logic comes here
   
   e.preventDefault();
   
   field = '#customer-reference-field';
   randomNumber = Math.floor((Math.random() * 10000) + 1);
   
   $(field).val('SPTEL-' + randomNumber);
});