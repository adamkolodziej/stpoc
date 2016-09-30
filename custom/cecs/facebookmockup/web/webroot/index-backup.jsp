<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>[y] Facebook</title>
        <link rel="shortcut icon" href="_ui/images/favicon.ico" />
        <link rel="stylesheet" href="_ui/css/style.css?v=1.0">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    </head>

    <body>
        <header class="header-main">
            <div class="container">
                <a href="" class="header-logo"><img src="_ui/images/logo-fb.png" alt="facebook"></a>
                <div class="header-search">
                    <input type="text" placeholder="Search Facebook" class="header-search-input">
                    <button type="submit" class="header-search-btn" value=""><i class="header-search-icon"></i></button>
                </div>
                <ul class="header-menu">
                    <li>
                        <a href=""><img src="sampledata/avatar.jpg" alt="Bill" class="avatar"><span class="js-name"></span></a>
                        <span class="separator"></span>
                    </li>
                    <li><a href="" class="text-link">Home</a></li>
                    <li><a href="" class="links"><img src="_ui/images/ico-main-menu-links.png" alt=""></a></li>
                    <li><span class="separator"></span></li>
                    <li><a href="" class="link link-privacy"></a></li>
                    <li><a href="" class="link link-more"></a></li>
                </ul>
            </div>
        </header>

        <main class="content-main">
            <div class="container">
                <aside class="sidebar">
                    <ul class="sidebar-items-group">
                        <li class="sidebar-item">
                            <a href="">
                                <img src="sampledata/avatar.jpg" alt="" class="aside-item-img">
                                <span class="sidebar-item-title js-full-name"></span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a href="">
                                <img src="_ui/images/ico-edit-profile.png" alt="" class="aside-item-img">
                                <span class="sidebar-item-title">Edit profile</span>
                            </a>
                        </li>
                    </ul>
                    <ul class="sidebar-items-group">
                        <li>
                            <h1 class="sidebar-items-title">Favorites</h1>
                        </li>
                        <li class="sidebar-item">
                            <a href="">
                                <img src="_ui/images/ico-newsfeed.png" alt="" class="aside-item-img">
                                <span class="sidebar-item-title">News Feed</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a href="">
                                <img src="_ui/images/ico-messages.png" alt="" class="aside-item-img">
                                <span class="sidebar-item-title">Messages</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a href="">
                                <img src="_ui/images/ico-event.png" alt="" class="aside-item-img">
                                <span class="sidebar-item-title">Events</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a href="">
                                <img src="_ui/images/ico-sale-groups.png" alt="" class="aside-item-img">
                                <span class="sidebar-item-title">Sale Groups</span>
                            </a>
                        </li>
                    </ul>
                    <ul class="sidebar-items-group">
                        <li>
                            <h1 class="sidebar-items-title">Apps</h1>
                        </li>
                        <li class="sidebar-item">
                            <a href="">
                                <img src="_ui/images/ico-on-this-day.png" alt="" class="aside-item-img">
                                <span class="sidebar-item-title">On This Day</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a href="">
                                <img src="_ui/images/ico-suggest-edits.png" alt="" class="aside-item-img">
                                <span class="sidebar-item-title">Suggest Edits</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a href="">
                                <img src="_ui/images/ico-pokes.png" alt="" class="aside-item-img">
                                <span class="sidebar-item-title">Pokes</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a href="">
                                <img src="_ui/images/ico-photos.png" alt="" class="aside-item-img">
                                <span class="sidebar-item-title">Photos</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a href="">
                                <img src="_ui/images/ico-saved.png" alt="" class="aside-item-img">
                                <span class="sidebar-item-title">Saved</span>
                            </a>
                        </li>
                    </ul>
                    <ul class="sidebar-items-group">
                        <li>
                            <h1 class="sidebar-items-title">Pages</h1>
                        </li>
                        <li class="sidebar-item">
                            <a href="">
                                <img src="_ui/images/ico-like-pages.png" alt="" class="aside-item-img">
                                <span class="sidebar-item-title">Like Pages</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a href="">
                                <img src="_ui/images/ico-pages-feed.png" alt="" class="aside-item-img">
                                <span class="sidebar-item-title">Pages Feed</span>
                                <span class="sidebar-item-value">10</span>
                            </a>
                        </li>
                    </ul>
                    <ul class="sidebar-items-group">
                        <li>
                            <h1 class="sidebar-items-title">Friends</h1>
                        </li>
                        <li class="sidebar-item">
                            <a href="">
                                <img src="_ui/images/ico-close-friends.png" alt="" class="aside-item-img">
                                <span class="sidebar-item-title">Close Friends</span>
                                <span class="sidebar-item-value">2</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a href="">
                                <img src="_ui/images/ico-family.png" alt="" class="aside-item-img">
                                <span class="sidebar-item-title">Family</span>
                                <span class="sidebar-item-value">20+</span>
                            </a>
                        </li>
                    </ul>
                </aside>

                <div class="feed">
                    <div class="post-publish-box">
                        <form>
                            <a href=""><img src="_ui/images/publish-box-options.png" alt="" class="post-publish-box-options"></a>
                            <div class="post-publish-box-content-wrapper">
                                <a href=""><img src="sampledata/avatar.jpg" class="post-publish-box-avatar" alt=""></a>
                                <textarea name="post-text" class="post-publish-box-text" placeholder="What's on your mind?"></textarea>
                            </div>
                            <a href=""><img src="_ui/images/publish-box-icons.png" alt="" class="post-publish-box-icons"></a>
                            <button type="submit" name="submit-btn" class="post-publish-box-submit-btn">Post</button>
                            <a href=""><img src="_ui/images/button-post-visibility.png" alt="" class="post-publish-box-visibility-btn"></a>
                        </form>
                    </div>
                    <ul class="posts js-posts">
                    </ul>
                </div>
                <aside class="suggestions">
                    <ul class="suggestions-group">
                        <li class="suggestions-item">
                            <a href="">
                                <img src="_ui/images/ico-friends.png" alt="" class="aside-item-img">
                                <span class="suggestions-item-title">7 friend requests</span>
                            </a>
                        </li>
                        <li class="suggestions-item">
                            <a href="">
                                <img src="_ui/images/ico-event.png" alt="" class="aside-item-img">
                                <span class="suggestions-item-title">2 event invites</span>
                            </a>
                        </li>
                    </ul>
                    <footer>
                        <ul class="links">
                            <li><a href="">English</a></li>
                            <li><a href="">Privacy</a></li>
                            <li><a href="">Terms</a></li>
                            <li><a href="">Cookies</a></li>
                            <li><a href="">Advertising</a></li>
                        </ul>
                        <span class="copyright">2016 &copy; Facebook</span>
                    </footer>
                </aside>
            </div>
        </main>

        <aside class="contacts">
            <ul class="contacts-list">
                <li class="contact">
                    <img src="_ui/images/avatar-1.jpg" alt="" class="contact-image">
                    <span class="contact-name">Mario Park</span>
                    <span class="contact-status online"></span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-2.jpg" alt="" class="contact-image">
                    <span class="contact-name">Kenny Hayes</span>
                    <span class="contact-status offline">1h</span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-3.jpg" alt="" class="contact-image">
                    <span class="contact-name">Natalie Nunez</span>
                    <span class="contact-status">30min</span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-4.jpg" alt="" class="contact-image">
                    <span class="contact-name">Mitchell Francis</span>
                    <span class="contact-status online"></span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-5.jpg" alt="" class="contact-image">
                    <span class="contact-name">Leonard Ortiz</span>
                    <span class="contact-status online"></span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-6.jpg" alt="" class="contact-image">
                    <span class="contact-name">Antonio Burgess</span>
                    <span class="contact-status offline">6h</span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-7.jpg" alt="" class="contact-image">
                    <span class="contact-name">Shannon Warner</span>
                    <span class="contact-status offline">48min</span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-8.jpg" alt="" class="contact-image">
                    <span class="contact-name">Laura Pratt</span>
                    <span class="contact-status">3h</span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-9.jpg" alt="" class="contact-image">
                    <span class="contact-name">Samantha Ross</span>
                    <span class="contact-status online"></span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-10.jpg" alt="" class="contact-image">
                    <span class="contact-name">Emmett Barton</span>
                    <span class="contact-status">21h</span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-11.jpg" alt="" class="contact-image">
                    <span class="contact-name">Silvia Cobb</span>
                    <span class="contact-status">5min</span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-12.jpg" alt="" class="contact-image">
                    <span class="contact-name">Daniel Spencer</span>
                    <span class="contact-status online"></span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-13.jpg" alt="" class="contact-image">
                    <span class="contact-name">Eleanor Yates</span>
                    <span class="contact-status offline">10h</span>
                </li>
            </ul>
        </aside>

        <div class="chat-hidden"></div>

        <script src="_ui/js/scripts.js"></script>
    </body>
</html>