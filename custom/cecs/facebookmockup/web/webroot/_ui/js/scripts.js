/*global document, console, $, jQuery*/

var FB = FB || {};

FB.stream = {
    jsonData:{},
    readData: function () {
        var _this = this;

        $.ajax({
            url: 'sampledata/posts.json',
            dataType: 'text',
            success: function (data) {
                FB.stream.jsonData = $.parseJSON(data);
                if ( FB.customer != undefined ) {
                    FB.stream.jsonData.name = FB.customer;
                    FB.stream.jsonData.surname = '';
                    FB.stream.jsonData.avatar = FB.avatar;
                }
                _this.bulidStream(FB.stream.jsonData);
                _this.updatePersonData(FB.stream.jsonData);
            },
            error: function (xhr, status, error) {
                console.log("ERROR | " + xhr.responseText);
            }
        });
    },

    bulidStream: function (data) {
        var content,
            i,
            random;

        for (i = 0; i < data.posts.length; i++) {
            random = Math.floor((Math.random() * 16) + 3);

            if (data.posts[i].date === null) { data.posts[i].date = "Sponsored"; }

            content = '<li class="post js-post"><div class="post-header"><img src="sampledata/' + data.posts[i].avatar + '" class="post-avatar" alt="">';
            content += '<div class="post-title"><a href="" class="author">' + data.posts[i].author + '</a>';

            if (data.posts[i].image_amount !== null) { content += ' <span class="title-activity">added <a href="">' + data.posts[i].image_amount + ' new photos</a>.</span>'; }

            content += '</div><span class="post-date">' + data.posts[i].date + ' · <img src="_ui/images/ico-public-post.png" alt=""></span></div>';
            content += '<div class="post-content"><p>' + data.posts[i].content + '</p>';

            if (data.posts[i].image !== null) { content += '<img src="sampledata/' + data.posts[i].image + '" alt="">'; }

            if (data.posts[i].advert !== null) {
                content += '<div class="sponsored-box"><a href="' + data.posts[i].advert[0].url + '" target="_blank"><img src="sampledata/' + data.posts[i].advert[0].image + '" alt="">';
                content += '<h1 class="sponsored-title">' + data.posts[i].advert[0].title + '</h1><p class="sponsored-content">' + data.posts[i].advert[0].content + '</p><p class="url js-url">' + data.posts[i].advert[0].url + '</p></a></div>';
            }

            content += '<span class="post-stats">' + random + ' Likes&nbsp;&nbsp;&nbsp;&nbsp;' + Math.floor(random / 3) + ' Comments&nbsp;&nbsp;&nbsp;&nbsp;' + Math.floor(random / 5) + ' Shares</span></div>';
            content += '<div class="post-footer"><a class="js-like" href="/facebook/like/' + data.posts[i].interest + '"><img class="not-liked" src="_ui/images/post-footer.png" alt=""><img class="liked" src="_ui/images/post-footer-liked.jpg" alt=""></a></div>';

            if (data.posts[i].comments === true) { content += '<div class="post-reactions"><a href="">Ann Smith</a> and <a href="">' + (random - 1) + ' others</a> like this.<ul class="post-comments"> <li class="comment"><img src="sampledata/avatar-11.jpg" class="comment-avatar" alt=""><div class="comment-wrapper"><a class="comment-author" href="">Silvia Cobb </a><p class="comment-content">Awesome :)</p> <ul class="comment-footer"><li><a href="">Like</a></li><li><a href="">Reply</a></li><li><a href=""><img src="_ui/images/ico-like.png" alt=""> 1</a></li> <li>14 hrs</li> </ul> </div> </li> <li class="comment"> <img src="sampledata/avatar-3.jpg" class="comment-avatar" alt=""> <div class="comment-wrapper"> <a class="comment-author" href="">Natalie Nunez</a> <p class="comment-content">I wolud like to see this!</p> <ul class="comment-footer"> <li><a href="">Like</a></li> <li><a href="">Reply</a></li> <li><a href=""><img src="_ui/images/ico-like.png" alt=""> 3</a></li> <li>14 hrs</li> </ul> </div> </li> <li class="comment comment-publish-box"> <img src="sampledata/avatar.jpg" class="comment-avatar" alt=""> <div class="comment-wrapper"> <input type="text" name="comment-publish-content" class="comment-publish-content" placeholder="Write a comment..."/> <img src="_ui/images/comment-publish-box-icons.png" alt="" class="comment-publish-icons" /> </div> </li> </ul> </div>'; }

            content += '</li>';

            $('<div/>').html(content).contents().appendTo('.js-posts');
        }

        FB.linkReplacer();
    },

    updatePersonData: function (data) {
        if (data.name.length) {
            $('.js-name').text(data.name);

            if (data.surname.length) {
                $('.js-full-name').text(data.name + " " + data.surname);
            }
        }
    },

    createPost: function($_input){
        var content,
            text = $_input.val();

            if(text.length>0){
                // js-like not active here - only on preloaded posts

                content = '<li class="post"><div class="post-header"><img src="' + FB.stream.jsonData.avatar + '" class="post-avatar" alt="">';
                content += '<div class="post-title"><a href="" class="author js-full-name"></a>';


                content += '</div><span class="post-date">just now · <img src="_ui/images/ico-public-post.png" alt=""></span></div>';
                content += '<div class="post-content"><p>' + text + '</p>';

                content += '<div class="post-footer"><a href=""><img src="_ui/images/post-footer.png" alt=""></a></div>';

                content += '</li>';
                $('<div/>').html(content).contents().prependTo('.js-posts');
                FB.stream.updatePersonData(FB.stream.jsonData);
                $_input.val("");
            }

    }
};

FB.linkReplacer = function () {
    $.each($('.js-posts a'), function () {
        var $self = $(this),
            link = $self.attr('href');

        link && $self.attr('href', replaceLink(link));
    });

    $.each($('.js-url'), function () {
        var $self = $(this),
            link = $self.text();

        link && $self.text(replaceLink(link));
    });

    function replaceLink(link) {
        var host = window.location.host;

        if (host.indexOf('local') === -1) {
            return link.replace(':9001', '').replace(':9002', '').replace('local', host);
        }

        return link;
    }
};

FB.interactions = {
    errorHandler: function(jqXHR, textStatus, errorThrown) {
        console.log("error: " + textStatus + ":" + errorThrown);
        alert('Unable to complete request: ' + textStatus);
    },

    postSubmitCallback: function(e) {
        e.preventDefault();

        var $textArea = $('.js-post-text');
        var messagePost = $textArea.val();
        var interestItem = $('#interestItem').val();

        $.post("/facebook/post", {messagePost: messagePost, interestItem: interestItem}).fail(FB.interactions.errorHandler);

        FB.stream.createPost($textArea);
    },

    likeCallback: function(e) {
        e.preventDefault();
        var self = $(this);
        var title = self.data('title');
        var content = self.data('content');
        self.addClass('js-liked');
        if( title == undefined ) {
            var $post = self.parents('.js-post');
            title = $post.find('.author').html();
            content = $post.find('.post-content p').html();
        }

        var action = self.attr('href');
        $.post(action, {contentTitle: title, contentData: content}).fail(FB.interactions.errorHandler);
    }
};

jQuery(document).ready(function () {
    FB.stream.readData();

    $('.js-submit-post').on('click', FB.interactions.postSubmitCallback);
    $(document).on('click', '.js-like', FB.interactions.likeCallback);

    $('.js-clock-icon').on('click',function(e){
        $('.time-switcher-component').addClass('active');
    });

    $('.js-close-timeswitcher').on('click',function(){
        $('.time-switcher-component').removeClass('active');
    });
});
