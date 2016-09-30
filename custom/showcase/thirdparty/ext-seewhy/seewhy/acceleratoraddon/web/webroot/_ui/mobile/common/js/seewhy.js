;var com_seewhy_addon_f = function() {

	var common = {
		testStorage : function() {
			if (typeof(Storage) === "undefined") {
				throw new UserException("no storage");
			}
		}
	};

	var seewhy = {

		constants : {
			VersionValue : 'hc/1',
			SIDcookie : '__cy_d',
			PageTypeProduct : 'PRODUCT',
			PageTypeOrderConfirmation : 'ORDERCONFIRMATION',
			PageTypeProductSearch : 'PRODUCTSEARCH',
			PageTypeCategory : 'CATEGORY',
			PageTypeCart : 'CART',
			Path : '/abandonment2/WE/seewhy.nogif/'
		},

		addBrowseProduct : function(o) {

				var itemID = ACC.addons.seewhy[o+'.ItemID'];
				var itemMasterID = ACC.addons.seewhy[o+'.ItemMasterID'];
				var itemName = ACC.addons.seewhy[o+'.ItemName'];
				var itemDesc = ACC.addons.seewhy[o+'.ItemDesc'];
				var itemPrice = ACC.addons.seewhy[o+'.ItemPrice'];
				var itemPageURL = ACC.addons.seewhy[o+'.ItemPageURL'];
				var itemImageURL = ACC.addons.seewhy[o+'.ItemImageURL'];
				var itemCategory = ACC.addons.seewhy[o+'.ItemCategory'];
				var itemReviewScore = ACC.addons.seewhy[o+'.ItemReviewScore'] || '';
				var itemReviewSummary = ACC.addons.seewhy[o+'.ItemReviewSummary'] || '';
				var itemReviewLink = ACC.addons.seewhy[o+'.ItemReviewLink'] || '';

				browse.addProduct(itemID, itemName, itemDesc, itemImageURL, itemPageURL, itemPrice, itemCategory, itemReviewScore, itemReviewSummary, itemReviewLink, itemMasterID);
		},

		productBrowsed : function() {

			if (window.ACC && ACC.addons && ACC.addons.seewhy && window.com_seewhy_addon_global) {
				var itemID = ACC.addons.seewhy['cyActionProductBrowsed.ItemID'];
				var itemMasterID = ACC.addons.seewhy['cyActionProductBrowsed.ItemMasterID'];

				seewhy.addBrowseProduct('cyActionProductBrowsed');

				if (itemMasterID && itemID !== itemMasterID) {
					browse.productVariantSelect(itemID);
				}
			}
		},

		productReviewed : function() {

			var itemID = ACC.addons.seewhy['cyActionProductReviewed.ItemID'];

			if (!browse.hasRecord('p', itemID)) {
				seewhy.addBrowseProduct('cyActionProductReviewed');
            }
            // User's rating is available in cyActionProductReviewed.ReviewScore. productReviewed function does not accept that
            // as a parameter so no point retrieving it. All reviews, regardless of score, are regarded as positive.
			browse.productReviewed(itemID);
		},

		bindBrowseListeners : function() {
			$(".a2a_i").on("click", function(event) {
				var itemID = ACC.addons.seewhy['cyActionProductBrowsed.ItemID'];
				if (itemID) {
					browse.productSocialLike(itemID);
				}
			});
		},

		getCookie : function(cn) {
			var name = cn + "=";
			var ca = document.cookie.split(';');
			for (var i = 0; i < ca.length; ++i) {
				var c = ca[i];
				while (c.charAt(0)==' ') {
					c = c.substring(1);
				}
				if (c.indexOf(name) == 0) {
					return decodeURIComponent(c.substring(name.length));
				}
			}
			return "";
		},

		setCookie : function(cn, cv, ma) {
			var mas = "";
			var ds = "";
			var domain = com_seewhy_addon_global.cyDomain;
			if (domain) {
				ds = ";domain=" +domain;
			}
			if (ma) {
				//'expires' is obsolete, but IE does not support max-age and all browsers support expires
				var date = new Date();
				date.setTime(date.getTime()+(ma*1000));
				mas = ";expires=" +date.toUTCString();
			}
			document.cookie = cn+'='+encodeURIComponent(cv)+";path=/"+ds+mas;
		},

		generateUUID : function() {
			// Generate an RFC 4122 compliant version 4 UUID
			return 'NNNNNNNN-NNNN-4NNN-XNNN-NNNNNNNNNNNN'.replace(/[NX]/g, function(c) {
				var rn = Math.floor(Math.random()*16);
				if (c == 'N') {
					v = rn;
				}
				else {
					// 0 <= rn < 16, but RFC 4122 requires that the 2 most sig bits be 1 and 0
					v = (rn&0x3|0x8);
				}
				return v.toString(16);
			}).toUpperCase();
		},

		getSessionID : function() {
			var sid = seewhy.getCookie(seewhy.constants.SIDcookie);
			if (sid === "") {
				sid = seewhy.generateUUID();
			}
			seewhy.setCookie(seewhy.constants.SIDcookie, sid, (60*60*24*365*2));
			return sid;
		},

		getTimeAndTZ : function() {
			var d = new Date();
			var t = d.getTime();
			var tz = d.getTimezoneOffset();
			return t+"~"+tz;
		},

		sendEvent : function(p) {
			var method;
			var qs;
			var url = com_seewhy_addon_global.cyTargetURL;
			var sc = com_seewhy_addon_global.cyServiceCode;
			var sed1 = com_seewhy_addon_global.cyStaticEventData01;
			var sed2 = com_seewhy_addon_global.cyStaticEventData02;
			var uid = ACC.addons.seewhy['cyActionEmailCaptured.EmailAddress'] || '';
			var fn = ACC.addons.seewhy['cyActionEmailCaptured.FirstName'];

			var sid = seewhy.getSessionID();
			if (url && sc) {
				if (uid) {
					p['UserID'] = uid;
				}
				if (fn) {
					p['Custom1'] = fn;
				}
				method = window.location.protocol.toLowerCase().indexOf('https') >= 0 ? "https://" : (p['UserID'] ? 'https://' : 'http://');
				if (url.match(/^\/\//)) {
					url = url.replace(/^\/\/(.*)/, method+'$1');
				}
				else if (!url.match(/^(http:\/\/|https:\/\/)/)) {
					url = method+url;
				}
				else {
					url = url.replace(/^http:\/\/(.*)/, method+'$1');
				}
				qs = '?Event=WebEvent&CustomerCode=' +sc+ '&SessionID=' +encodeURIComponent(sid);
				qs += '&Version='+encodeURIComponent(seewhy.constants.VersionValue);
				qs += '&ClientTimeAndTZ='+seewhy.getTimeAndTZ();
				qs += '&DefaultPageName='+encodeURIComponent(window.location.pathname);
				qs += '&Referrer='+encodeURIComponent(window.document.referrer);
				qs += '&Server='+encodeURIComponent(window.document.domain);
				if (sed1) {
					qs += '&sed1='+encodeURIComponent(sed1);
				}
				if (sed2) {
					qs += '&sed2='+encodeURIComponent(sed2);
				}

				for (var n in p) {
					if (p.hasOwnProperty(n)) {
						qs += '&' + n + '=' + encodeURIComponent(p[n]);
					}
				}

				var i = document.createElement("img");
				i.width=1; i.height=1; i.border=0;
				var s = document.getElementsByTagName('script')[0];
				s.parentNode.insertBefore(i, s);
				i.src = url + seewhy.constants.Path + Math.random().toString().slice(2) + qs;
				i.style.cssText = 'display:none;';
			}
		},

		formatBasketLine : function(i, l) {
			var s = i.toString();
			if (s.length < l) {
				s = ('00' + s).slice(-l);
			}
			return 'CYBK' + s;
		},

		getBasketAppend : function() {
			var ba = '1';
			try {
				common.testStorage();
				var m = sessionStorage.getItem(browse.constants.labelOpCode);
				if (!m) {
					ba = '0';
				}
		    }
		    catch(err){}
			return ba;
		},

		addToCart : function(seewhyCart) {
		
			var ba = seewhy.getBasketAppend();
			browse.terminate();

			var atc = seewhyCart['cyActionAddToCart'];
			if (atc && atc === 'true') {
				var itemID = seewhyCart['cyActionAddToCart.ItemID'];
				if (itemID) {
					var p = {};
					var s = seewhy.formatBasketLine(1, 3);

					p[s+'ItemID'] = itemID;
					p[s+'ItemMasterID'] = seewhyCart['cyActionAddToCart.ItemMasterID'] || itemID;
					p[s+'ItemName'] = seewhyCart['cyActionAddToCart.ItemName'];
					p[s+'ItemDesc'] = seewhyCart['cyActionAddToCart.ItemDesc'];
					p[s+'ItemPrice'] = seewhyCart['cyActionAddToCart.ItemPrice'];
					p[s+'ItemPriceCurrency'] = seewhyCart['cyActionAddToCart.ItemPriceCurrency'];
					p[s+'ItemQuantity'] = seewhyCart['cyActionAddToCart.ItemQuantity'];
					p[s+'ItemPageURL'] = seewhyCart['cyActionAddToCart.ItemPageURL'];
					p[s+'ItemImageURL'] = seewhyCart['cyActionAddToCart.ItemImageURL'];
					p[s+'ItemCategory'] = seewhyCart['cyActionAddToCart.ItemCategory'];
					p[s+'ItemReviewScore'] = seewhyCart['cyActionAddToCart.ItemReviewScore'];
					p['Custom9'] = 'cart';
					p['FunnelLevel'] = '4';
					p['Value'] = seewhyCart['cyActionAddToCart.CartValue'];
					p['ReturnToLink'] = seewhyCart['cyActionAddToCart.ReturnToLink'];
					p['BasketAppend'] = ba;

					seewhy.sendEvent(p);
				}
			}
		},

		orderPlaced : function() {

			browse.terminate();

			var p = {};
			p['Custom9'] = 'cart';
			p['FunnelLevel'] = '7';

			var on = ACC.addons.seewhy['cyActionOrderPlaced.OrderNumber'];
			var ov = ACC.addons.seewhy['cyActionOrderPlaced.OrderValue'];

			p['OrderNumber'] = on;
			p['Value'] = ov;

			seewhy.sendEvent(p);
		},

		emailCapture : function() {
			var qs = location.search ? location.search : '';
			if (qs.charAt(0) == '?') {
				qs = qs.substring(1);
			}

			if (qs.length > 0) {
				qs = qs.replace(/\+/g, ' ');
				var qsp = qs.split(/[&;]/g);

				for (var i = 0; i < qsp.length; i++) {
					var kvp = qsp[i].split('=');
					var v = kvp.length > 1 ? decodeURIComponent(kvp[1]) : '';
					if (v.match(/\S+@\S+\.\S+/)) {
						var p = {};
						p['Custom1'] = 'Guest';
						p['UserID'] = v;
						p['FunnelLevel'] = '0';
						seewhy.sendEvent(p);
						break;
					}
				}
			}
		},

		emailLoginCapture : function() {
			try {
				common.testStorage();
				var p = {};
				var sid = ACC.addons.seewhy['cyActionGeneral.ServerContext'];
				if (sid) {
					var k = 'com.seewhy.h.sid_'+sid;
					var s = sessionStorage.getItem(k);
					if (!s || s === '0') {
						p['FunnelLevel'] = '0';
						seewhy.sendEvent(p);
						sessionStorage.setItem(k, '1');
					}
				}
			}
			catch(err){}
		},

		isSeewhyEnabled: function() {
            return window.ACC && ACC.addons && ACC.addons.seewhy && window.com_seewhy_addon_global;
        },
		
		bindToAddToCartForm: function () {
			var addToCartForm = $('.add_to_cart_form');
			if (addToCartForm.length) {
				addToCartForm.ajaxForm({
					success: function(cartResult, statusText, xhr, formElement) {
					    if(seewhy.isSeewhyEnabled()) {
                            seewhy.addToCart(cartResult.seewhyCart);
                        }
						ACC.product.displayAddToCartPopup(cartResult.storefrontCart, statusText, xhr, formElement);
					}
				});
			}
		},

		bindToAddToCartStorePickUpForm: function () {
			var addToCartStorePickUpForm = $("#pickup_store_results .add_to_cart_storepickup_form");
			if (addToCartStorePickUpForm.length)
			{
				addToCartStorePickUpForm.ajaxForm({
					success: function(cartResult, statusText, xhr, formElement) {
					    if(seewhy.isSeewhyEnabled()) {
                            seewhy.addToCart(cartResult.seewhyCart);
                        }
						ACC.product.displayAddToCartPopup(cartResult.storefrontCart, statusText, xhr, formElement);
					}
				});
			}
		}
	};

	var browse = {

		_cysku : null,
		constants : {
		    "topFocusItems":"com.seewhy.b.tfi",
		    "currentsku":"com.seewhy.b.cysku",
		    "topFocusCategories":"tfc",
		    "sku":"sku",
		    "productname":"name",
		    "productMasterId":"productMasterId",
		    "productdescription":"description",
		    "productURL":"productURL",
		    "imageURL":"imageURL",
		    "price":"price",
		    "recommendations":"recommendations",
		    "isVariant":"isVariant",
		    "masterSKU":"masterSKU",
		    "category":"com.seewhy.b.category",
		    "reviewScore":"reviewScore",
		    "reviewSummary":"reviewSummary",
		    "reviewLink":"reviewLink",
		    "averageEngagementTimeSecs":18,
		    "score":"score",
		    "scoreThreshold":6.0,
		    "ditchThreshold":0.0005,
		    "scorematrix":"com.seewhy.b.sm",
		    "activityCount":"count",
		    "activityDwell":"dwell",
		    "activityLastViewed":"lastViewed",
		    "activityBookmark":"bookmark",
		    "activitySocialMark":"social",
		    "activityFacebookShare":"share",
		    "activityPrint":"print",
		    "activityEmail":"email",
		    "activityReview":"review",
		    "activityTweet":"tweet",
		    "activityVariantSelect":"variant",
		    "labelOpCode":"com.seewhy.b.opCode",
		    "activityDwellSF":10,
		    "maximumInterestPeriod":86400,      //one day
		    "skus":"com.seewhy.b.skus",
		    "categories":"com.seewhy.b.categories",
		    "category_property":"category"
		},

		weights : {
		    "count":1,
		    "dwell":1,
		    "bookmark":3,
		    "social":5,
		    "share":5,
		    "tweet" : 5,
		    "email" : 10,
		    "print" : 15,
		    "review" : 10,
		    "variant":2
		},

		addRecommendation : function(sku, recommendationSKU, name, imageURL, productURL) {
		    try {
		        common.testStorage();
		        if (browse.hasRecord('p', sku))
		        {
		            var record = browse._getRecord('p', sku);
		            var recommend = record[browse.constants.recommendations];
		            if (!recommend.hasOwnProperty(recommendationSKU))
		            {
		                var item = {};
		                item[browse.constants.productname] = name;
		                item[browse.constants.imageURL] = imageURL;
		                item[browse.constants.productURL] = productURL;
		                recommend[recommendationSKU] = item;
		                browse._writeField('p', sku, record);
		            }
		        }
		    }
		    catch(err){}
		},

		_checkCategory : function(category) {
		    //check that category is in list
		    var categories;
		    categories_s = localStorage.getItem(browse.constants.category);
		    if (!categories_s) {
		        categories = {};
		    }
		    else {
		        categories = JSON.parse(categories_s);
		    }

		    if (!categories.hasOwnProperty(category)) {
		        categories[category] = 1;
		    }
		    else {
		        categories[category]++;
		    }

		    localStorage.setItem(browse.constants.category, JSON.stringify(categories));
		},

		addProduct : function(sku, name, description, imageURL, productURL, indicativePrice, category, reviewScore, reviewSummary, reviewLink, productMasterId) {
		    try {
		        common.testStorage();

				var pmid = sku;
				if (productMasterId) {
					pmid = productMasterId;
				}

		        if (!browse.hasRecord('p', sku)) {
		            browse._createRecord(sku, name, description, imageURL, productURL, indicativePrice, category, reviewScore, reviewSummary, reviewLink, pmid);
		        }

		        var scorematrix = {};
		        var scorematrix_s = localStorage.getItem(browse.constants.scorematrix);
		        if (scorematrix_s) {
		            scorematrix = JSON.parse(scorematrix_s);
		        }

		        var d = new Date();

		        if (scorematrix[sku]) {
		            var scorecard = scorematrix[sku];

		            scorecard[browse.constants.activityCount] = scorecard[browse.constants.activityCount]+1;
		            scorecard[browse.constants.activityLastViewed] = d.getTime();
		        }
		        else {
		            var scorecard = {};
		            scorecard[browse.constants.activityCount] = 1;
		            scorecard[browse.constants.activityDwell] = 0;
		            scorecard[browse.constants.activityBookmark] = 0;
		            scorecard[browse.constants.activitySocialMark] = 0;
		            scorecard[browse.constants.activityFacebookShare] = 0;
		            scorecard[browse.constants.activityTweet] = 0;
		            scorecard[browse.constants.activityEmail] = 0;
		            scorecard[browse.constants.activityPrint] = 0;
		            scorecard[browse.constants.activityReview] = 0;
		            scorecard[browse.constants.activityLastViewed] = d.getTime();
		            scorecard[browse.constants.activityVariantSelect] = 0;
		            scorecard[browse.constants.score] = 0;

		            scorematrix[sku] = scorecard;
		        }

		        localStorage.setItem(browse.constants.scorematrix, JSON.stringify(scorematrix));

		        browse._cysku = sku;
		        localStorage.setItem(browse.constants.currentsku, sku);

		        browse._checkCategory(category);
		    }
		    catch(err){}
		},

		productVariantSelect : function(sku) {
		    try {
		    	common.testStorage();
		        browse._process(sku, browse.constants.activityVariantSelect);
		    }
		    catch(err){}
		},

		productSocialLike : function(sku) {
		    try {
		    	common.testStorage();
		        browse._process(sku, browse.constants.activitySocialMark);
		    }
		    catch(err){}
		},

		productReviewed : function(sku) {
		    try {
		    	common.testStorage();
		        browse._process(sku, browse.constants.activityReview);
		    }
		    catch(err){}
		},

		_productPageDwell : function(sku, dwell) {
	        var scorematrix_s = localStorage.getItem(browse.constants.scorematrix);
	        if (scorematrix_s) {
	            var scorematrix = JSON.parse(scorematrix_s);
	            var scorecard = scorematrix[sku];
	            if (scorecard) {
	                var os = scorecard[browse.constants.activityDwell];
	                var ns = browse._dwellScore(dwell);
	                if (ns > os) {
	                    scorecard[browse.constants.activityDwell] = ns;
	                }
	                browse._propagateScores(scorematrix, scorecard);
	            }
	        }
		},

		_process : function(sku, field) {
	    	var scorematrix_s = localStorage.getItem(browse.constants.scorematrix);
	    	if (scorematrix_s) {
	        	var scorematrix = JSON.parse(scorematrix_s);
	        	var scorecard = scorematrix[sku];
	        	if (scorecard) {
	            	scorecard[field] = 1;
	            	browse._propagateScores(scorematrix, scorecard);
	        	}
	    	}
		},

//		_teststorage : function() {
//		    if (typeof(Storage) === "undefined") {
//		        throw new UserException("no storage");
//		    }
//		},

		_propagateScores : function(scorematrix, scorecard) {
		    var d = new Date();
		    var t = d.getTime();
		    browse._rescore(scorematrix, t);

		    browse._extractCurrentFocusProducts(scorematrix);
		    scorecard[browse.constants.activityLastViewed] = t;
		    localStorage.setItem(browse.constants.scorematrix, JSON.stringify(scorematrix));
		},

		_rescore : function(scorematrix, t) {
		    try
		    {
		        for (var sku in scorematrix) {
		            if (scorematrix.hasOwnProperty(sku)) {
		                var scorecard = scorematrix[sku];
		            	var secs = (t - scorecard[browse.constants.activityLastViewed]) / 1000;
		            	var td = browse._timedecayScore(secs);
		            	var ws = browse._weightedScore(scorecard,secs);
						//high ws should dominate the score.  However, if the majority of ws is made of
						//dwell, then this domination will diminish faster than td
						var s = td * ws;
		                if (scorecard[browse.constants.score]==0) {
		                    if (s > browse.constants.scoreThreshold) {
								scorecard[browse.constants.score] = s;
							}
		            	}
						else {
		            		if (s < browse.constants.ditchThreshold) {
					        	delete scorematrix[sku];
							}
		            		else {
						    	scorecard[browse.constants.score] = s;
		            		}
						}
		            }
		        }
		    }
			catch(err){}
		},

		_weightedScore : function(scorecard,secs) {
		    var score = 1;
		    score = score + scorecard[browse.constants.activityCount];
		    score = score + browse.weights.bookmark * scorecard[browse.constants.activityBookmark];
		    score = score + browse.weights.social * scorecard[browse.constants.activitySocialMark];
		    score = score + browse.weights.share * scorecard[browse.constants.activityFacebookShare];
		    score = score + browse.weights.tweet * scorecard[browse.constants.activityTweet];
		    score = score + browse.weights.email * scorecard[browse.constants.activityEmail];
		    score = score + browse.weights.print * scorecard[browse.constants.activityPrint];
		    score = score + browse.weights.review * scorecard[browse.constants.activityReview];
		    score = score + browse.weights.variant * scorecard[browse.constants.activityVariantSelect];

		    var activityScore = Math.log(score * 10.0) / Math.log(2);
		    var dwellScore = browse._dwellScore(secs);
		    return Math.ceil(activityScore*dwellScore);
		},

		_dwellScore : function(dwell) {
		    if (dwell >= browse.constants.averageEngagementTimeSecs) {
		        return 6.0*Math.exp(-dwell/10000);
		    }
		    else {
		        var v = Math.exp(dwell * 0.15 / 5.0);
		        return v;
		    }
		},

		_timedecayScore : function(secs) {
		    var dd = secs/86400;
		    var s = Math.exp(-0.005*dd*dd);
		    return s;
		},

		_sendEvent : function() {
			var m = sessionStorage.getItem(browse.constants.labelOpCode);
		    if (m === "cart") {
		    	return;
		    }

		    var records = JSON.parse(localStorage.getItem(browse.constants.topFocusItems));
		    var categories_s = localStorage.getItem(browse.constants.category);
		    var categories = JSON.parse(categories_s);

		    var scorematrix;
		    var scorematrix_s = localStorage.getItem(browse.constants.scorematrix);
		    if (scorematrix_s) {
		        scorematrix = JSON.parse(scorematrix_s);
		    }

			var key;
		    var catcount = 0;
		    for (key in categories) {
		        if (categories.hasOwnProperty(key)) {
		            catcount++;
		        }
		    }
			var scores = {};
		    var prods = {};
		    var prodcount = 0;
		    if (scorematrix && records) {
		        for (key in records) {
		            prods[key] = browse._getRecord('p', key);
				    var sc = scorematrix[key];
				    scores[key] = sc['score'];
		            prodcount++;
		        }
		    }

		    browse._sendBrowseEvent(prodcount, prods, scores, catcount, categories);
		},

		_extractCurrentFocusProducts : function(scorematrix) {
		    var records = {};
		    var oldrecords = {};
		    var upload = false;

		    if (localStorage.getItem(browse.constants.topFocusItems)) {
		        oldrecords = JSON.parse(localStorage.getItem(browse.constants.topFocusItems));
		    }

		    for (key in scorematrix) {
		        browse._processScoreCard(key, scorematrix[key], records);
		    }

		    localStorage.setItem(browse.constants.topFocusItems, JSON.stringify(records));
		    localStorage.setItem(browse.constants.scorematrix, JSON.stringify(scorematrix));

		    for (key in records)
		    {
		        if (!oldrecords.hasOwnProperty(key)) {
		            upload = true;
		            break;
		        }
		    }

		    if (upload) {
				browse._sendEvent();
		    }
		},

		_processScoreCard : function(key, scorecard, records) {
		    var upload = false;

		    if (browse._objectSize(records) > 0) {
		        if (browse._objectSize(records) < 3) {
		            records[key] = scorecard.score;
		            if (scorecard.score > browse.constants.scoreThreshold) {
		                upload = true;
		            }
		        }
		        else if (records.hasOwnProperty(key)) {
		            if (records[key] < scorecard.score) {
		                records[key] = scorecard.score;
		            }
		        }
		        else {
		            var smallestkey = "none";
		            var smallestvalue = 1000;

		            for (var i in records) {
		                if (records.hasOwnProperty(i)) {
		                    if (records[i] < scorecard.score) {
		                        if (records[i] < smallestvalue) {
		                            smallestkey = i;
		                            smallestvalue = records[i];
		                        }
		                    }
		                }
		            }

		            //if candidate found, replace it here
		            if (smallestkey !== "none") {
		                delete records[smallestkey];
		                records[key] = scorecard.score;
		                upload = true;
		            }
		        }
		    }
		    else {
		        if(scorecard.score > browse.constants.scoreThreshold ) {
		            records[key] = scorecard.score;
		            upload = true;
		        }
		    }

		    return upload;
		},

		_objectSize : function(obj) {
		    var size = 0, key;
		    for (key in obj) {
		        if (obj.hasOwnProperty(key)) size++;
		    }
		    return size;
		},

		hasRecord : function(type, item)
		{
		    var rb = false;
		    try
		    {
		    	common.testStorage();
			    var lsType;
			    if (type == 'p') {
			        lsType = browse.constants.skus;
			    }
			    else if (type == 'c') {
			        lsType = browse.constants.categories;
			    }
			    var i = localStorage.getItem(lsType);
			    if (i) {
			        var items = JSON.parse(i);
			        var r = items[item];
			        if (r) {
			            rb = true;
			        }
			    }
		    }
		    catch(err) {}

		    return rb;
		},

		_createRecord : function(sku, name, description, imageURL, productURL, indicativePrice, category, reviewScore, reviewSummary, reviewLink, productMasterId) {
		    var record = {};
		    record[browse.constants.productname] = name;
		    record[browse.constants.productdescription] = description;
		    record[browse.constants.imageURL] = imageURL;
		    record[browse.constants.productURL] = productURL;
		    record[browse.constants.price] = indicativePrice;
		    record[browse.constants.reviewScore] = reviewScore;
		    record[browse.constants.reviewSummary] = reviewSummary;
		    record[browse.constants.reviewLink] = reviewLink;
		    record[browse.constants.category_property] = category;
		    record[browse.constants.recommendations] = {};
		    record[browse.constants.productMasterId] = productMasterId;

		    browse._writeField('p', sku, record);
		},

		terminate : function() {
		    try {
		        common.testStorage();

		        localStorage.removeItem(browse.constants.skus);
		        localStorage.removeItem(browse.constants.categories);
		        localStorage.removeItem(browse.constants.category);
		        localStorage.removeItem(browse.constants.topFocusItems);
		        localStorage.removeItem(browse.constants.scorematrix);
		        localStorage.removeItem('com.seewhy.b.edc');
		        localStorage.removeItem('com.seewhy.b.lastsku');

		        sessionStorage.setItem(browse.constants.labelOpCode, "cart");
		     }
		    catch(err){}
		},

		_sendBrowseEvent : function(numproducts, products, scores, numcategories, categories) {
		    try
		    {
		        var top=true;
		        var count = 0;
		        var p = {};
		        var record;
		        var s;
		        for (var key in products) {
		        	if (products.hasOwnProperty(key)) {
						++count;
						record = products[key];
						s = seewhy.formatBasketLine(count, 3);

						p[s+'ITEMID'] = key;
		            	p[s+'ITEMPRICE'] = record['price'];
		            	p[s+'ITEMNAME'] = record['name'];
		            	p[s+'ITEMIMAGEURL'] = record['imageURL'];
		            	p[s+'ITEMDESC'] = record['description'];
		            	p[s+'ITEMPAGEURL'] = record['productURL'];
		            	p[s+'ITEMCATEGORY'] = record['category'];
		            	p[s+'ITEMMASTERID'] = record['productMasterId'];
						p[s+'ITEMBROWSESCORE'] = scores[key];
		            	if (top) {
		                	p[s+'ITEMREVIEWSCORE'] = record['reviewScore'];
		                	p[s+'ITEMREVIEWSUMMARY'] = record['reviewSummary'];
		                	p[s+'ITEMLink'] = record['reviewLink'];
		                	top = false;
		            	}
		            	var recommendations = record['recommendations'];

		            	var append = 0;
		            	for (var rkey in recommendations) {
		            		if (recommendations.hasOwnProperty(rkey)) {
		                		var r = recommendations[rkey];
		                		prepend = 'r' + append++;
		                		p[s+prepend+'ITEMID'] = rkey;
		                		p[s+prepend+'ITEMNAME'] = r[browse.constants.productname];
		                		p[s+prepend+'ITEMIMAGEURL'] = r[browse.constants.imageURL];
		                		p[s+prepend+'ITEMPAGEURL'] = r[browse.constants.productURL];
		                	}
		            	}
		            }
		        }

		        p['Custom14'] = numcategories;
		        p['Custom13'] = numproducts;
		        p['Custom9'] = "Browse";
		        p['FunnelLevel'] = '3';
		        p['BasketAppend'] = '0';

		        seewhy.sendEvent(p);
		    }
		    catch(err){}
		},

		_getRecord : function(type, item)
		{
		    var rs = {};
		    var i;
		    if (type == 'c') {
		        i = localStorage.getItem(browse.constants.categories);
		    }
		    else if (type == 'p') {
		        i = localStorage.getItem(browse.constants.skus);
		    }
		    if (i) {
		        var items = JSON.parse(i);
		        rs = items[item];
		    }
		    return rs;
		},

		_writeField : function(type, key, val)
		{
			var items;
			var lsKey;
			if (type == 'p') {
				lsKey = browse.constants.skus;
			}
			else if (type == 'c') {
				lsKey = browse.constants.categories;
			}
			var items_s = localStorage.getItem(lsKey);
			if (!items_s) {
				items = {};
			}
			else {
				items = JSON.parse(items_s);
			}
			items[key] = val;
			localStorage.setItem(lsKey, JSON.stringify(items));
		},

		// Start EDC Additions
		cyDwellStart : function() {
			try {
				common.testStorage();
			    browse._cypageOpen = new Date().getTime();
			    var t = localStorage.getItem("com.seewhy.b.edc");
			    var s = localStorage.getItem("com.seewhy.b.lastsku");
			    if (s) {
			        browse._productPageDwell(s, t);
			    }
		    }
		    catch(err){}
		},

		_cyDwellTime : function() {
			browse._cypageClose = new Date().getTime();
			browse._cytime = browse._cypageClose - browse._cypageOpen;
		},

		_unload : function() {
			try {
			    browse._cyDwellTime();
			    browse._cysku = localStorage.getItem(browse.constants.currentsku);
			    if (browse._cysku) {
			        localStorage.setItem('com.seewhy.b.edc', (browse._cytime / 1000));
			        localStorage.setItem('com.seewhy.b.lastsku', browse._cysku);
			    }
			    localStorage.removeItem(browse.constants.currentsku);
		    }
		    catch(err){}
		}
		// End EDC Additions
	};

    if(typeof ACC.product != "undefined") {

        ACC.product.bindToAddToCartStorePickUpForm = function() {
            seewhy.bindToAddToCartStorePickUpForm();
        };

    };
	
	$(document).ready(function ()
	{
	    
        seewhy.bindToAddToCartForm();
        
		if (window.ACC && ACC.addons && ACC.addons.seewhy && window.com_seewhy_addon_global) {

			browse.cyDwellStart();	//EDC

			if (!ACC.addons.seewhy['cyActionEmailCaptured'] && com_seewhy_addon_global.cyUseURLEmail === 'true') {
				seewhy.emailCapture();
			}

			if (ACC.addons.seewhy['cyActionProductBrowsed'] && ACC.addons.seewhy['cyActionProductBrowsed'] === 'true') {
				seewhy.productBrowsed();
			}
			if (ACC.addons.seewhy['cyActionProductReviewed'] && ACC.addons.seewhy['cyActionProductReviewed'] === 'true') {
				seewhy.productReviewed();
			}

			if (ACC.addons.seewhy['cyActionOrderPlaced'] && ACC.addons.seewhy['cyActionOrderPlaced'] === 'true') {
				seewhy.orderPlaced();
			}
			else if (ACC.addons.seewhy['cyActionEmailCaptured']) {
				seewhy.emailLoginCapture();
			}

			seewhy.bindBrowseListeners();

			// EDC
			if (window.addEventListener) {
				window.addEventListener('beforeunload', browse._unload, false);
			}
			else if (window.attachEvent) {
				window.attachEvent('onbeforeunload', browse._unload);
			}
		}
	});

	return {
		common: common,
		seewhy: seewhy,
		browse: browse
	};
}();