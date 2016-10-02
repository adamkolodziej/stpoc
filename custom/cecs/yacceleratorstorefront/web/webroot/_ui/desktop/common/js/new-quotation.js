/**
 * New quotation
 */

//Card on click
$('.new-quotation-card').click(function () {
    $('.new-quotation-card').removeClass('selected');
    $(this).addClass('selected');
});

//Gray box on click
$('.new-quotation-gray-box').click(function () {
    $('.new-quotation-gray-box').removeClass('selected');
    $(this).addClass('selected');
});

//Blue box on click
$('.new-quotation-blue-box').click(function () {
    if ($(this).hasClass('selected')) {
        $(this).removeClass('selected');
    } else {
        $(this).addClass('selected');
    }
});