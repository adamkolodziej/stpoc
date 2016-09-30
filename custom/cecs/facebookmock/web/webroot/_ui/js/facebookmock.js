
onDocumentReady = function ()
{
    likes = document.getElementsByClassName('like-link');
    for(i=0; i<likes.length; i++) {
        likes[i].addEventListener('click', like, false);
    }
};

like = function (e) {
    e.preventDefault();
    var xmlhttp = new XMLHttpRequest();
    this.parentNode.className = this.parentNode.className + "Liked";
    var wasLiked = this.dataset.wasliked;
    xmlhttp.open("GET","/facebookmock/like/" + wasLiked,true);
    xmlhttp.send();
};

document.addEventListener('DOMContentLoaded', onDocumentReady, false);