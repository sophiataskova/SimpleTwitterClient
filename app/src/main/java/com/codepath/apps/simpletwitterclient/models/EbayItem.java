package com.codepath.apps.simpletwitterclient.models;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sophiataskova on 3/9/15.
 */
public class EbayItem {


    private String url;
    private String imageUrl;
    private String title;

    public EbayItem(JSONObject jsonObject){
        try {
            String escapedImageURL = jsonObject.getString("galleryURL");
            escapedImageURL = new JSONArray(escapedImageURL).getString(0);
            String escapedURL = jsonObject.getString("viewItemURL");
            escapedURL = new JSONArray(escapedURL).getString(0);
            this.imageUrl = StringEscapeUtils.unescapeJava(escapedImageURL);
            this.url = StringEscapeUtils.unescapeJava(escapedURL);
            this.title = (new JSONArray(jsonObject.getString("title"))).getString(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


//    public static EbayItem fromJSON(JSONObject jsonObject) {
//        EbayItem ebayItem = new EbayItem();
//        try {
//            ebayItem.url = jsonObject.getString("viewItemURL");
//            ebayItem.title = jsonObject.getString("title");
//            ebayItem.imageUrl = jsonObject.getString("galleryURL");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return ebayItem;
//    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public static ArrayList<EbayItem> fromJsonArray(JSONArray jsonArray) {
        ArrayList<EbayItem> results = new ArrayList();
        for (int i = 0; i< jsonArray.length(); i++) {
            try {
                results.add(new EbayItem(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public static Collection<? extends EbayItem> fromJSONArray(JSONArray response) {
        ArrayList<EbayItem> results = new ArrayList();
        for (int i = 0; i< response.length(); i++) {
            try {
                results.add(new EbayItem(response.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}

/*
sample response:
        {
        "findItemsByKeywordsResponse": [
        {
        "ack": [
        "Success"
        ],
        "version": [
        "1.13.0"
        ],
        "timestamp": [
        "2015-03-09T06:26:55.851Z"
        ],
        "searchResult": [
        {
        "@count": "12",
        "item": [
        {
        "itemId": [
        "261802563201"
        ],
        "title": [
        "Wooden Magic Wand with Phoenix Feather 2-tone Harry Potter Wizard style FREEPOST"
        ],
        "globalId": [
        "EBAY-GB"
        ],
        "primaryCategory": [
        {
        "categoryId": [
        "29798"
        ],
        "categoryName": [
        "Harry Potter"
        ]
        }
        ],
        "galleryURL": [
        "http:\/\/thumbs2.ebaystatic.com\/m\/mqEeSCGTOhZmHGsFJhaywoQ\/140.jpg"
        ],
        "viewItemURL": [
        "http:\/\/www.ebay.com\/itm\/Wooden-Magic-Wand-Phoenix-Feather-2-tone-Harry-Potter-Wizard-style-FREEPOST-\/261802563201?pt=LH_DefaultDomain_3"
        ],
        "paymentMethod": [
        "PayPal"
        ],
        "autoPay": [
        "false"
        ],
        "postalCode": [
        "SM71RD"
        ],
        "location": [
        "United Kingdom"
        ],
        "country": [
        "GB"
        ],
        "shippingInfo": [
        {
        "shippingServiceCost": [
        {
        "@currencyId": "USD",
        "__value__": "12.88"
        }
        ],
        "shippingType": [
        "Flat"
        ],
        "shipToLocations": [
        "Worldwide"
        ],
        "expeditedShipping": [
        "false"
        ],
        "oneDayShippingAvailable": [
        "true"
        ],
        "handlingTime": [
        "1"
        ]
        }
        ],
        "sellingStatus": [
        {
        "currentPrice": [
        {
        "@currencyId": "GBP",
        "__value__": "10.0"
        }
        ],
        "convertedCurrentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "15.06"
        }
        ],
        "bidCount": [
        "2"
        ],
        "sellingState": [
        "Active"
        ],
        "timeLeft": [
        "P0DT1H46M26S"
        ]
        }
        ],
        "listingInfo": [
        {
        "bestOfferEnabled": [
        "false"
        ],
        "buyItNowAvailable": [
        "false"
        ],
        "startTime": [
        "2015-03-06T08:13:21.000Z"
        ],
        "endTime": [
        "2015-03-09T08:13:21.000Z"
        ],
        "listingType": [
        "Auction"
        ],
        "gift": [
        "false"
        ]
        }
        ],
        "returnsAccepted": [
        "true"
        ],
        "condition": [
        {
        "conditionId": [
        "1000"
        ],
        "conditionDisplayName": [
        "New"
        ]
        }
        ],
        "isMultiVariationListing": [
        "false"
        ],
        "topRatedListing": [
        "false"
        ]
        },
        {
        "itemId": [
        "251665679959"
        ],
        "title": [
        "Harry Potter and the Order of the Phoenix (Book 5)"
        ],
        "globalId": [
        "EBAY-US"
        ],
        "primaryCategory": [
        {
        "categoryId": [
        "279"
        ],
        "categoryName": [
        "Children & Young Adults"
        ]
        }
        ],
        "galleryURL": [
        "http:\/\/thumbs4.ebaystatic.com\/m\/ma85pZE1ecXBl_muOBV9bUg\/140.jpg"
        ],
        "viewItemURL": [
        "http:\/\/www.ebay.com\/itm\/Harry-Potter-and-Order-Phoenix-Book-5-\/251665679959?pt=LH_DefaultDomain_0"
        ],
        "productId": [
        {
        "@type": "ReferenceID",
        "__value__": "2327833"
        }
        ],
        "paymentMethod": [
        "PayPal"
        ],
        "autoPay": [
        "true"
        ],
        "location": [
        "USA"
        ],
        "country": [
        "US"
        ],
        "shippingInfo": [
        {
        "shippingServiceCost": [
        {
        "@currencyId": "USD",
        "__value__": "0.0"
        }
        ],
        "shippingType": [
        "Free"
        ],
        "shipToLocations": [
        "US"
        ],
        "expeditedShipping": [
        "true"
        ],
        "oneDayShippingAvailable": [
        "false"
        ],
        "handlingTime": [
        "2"
        ]
        }
        ],
        "sellingStatus": [
        {
        "currentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "3.97"
        }
        ],
        "convertedCurrentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "3.97"
        }
        ],
        "sellingState": [
        "Active"
        ],
        "timeLeft": [
        "P22DT14H51M18S"
        ]
        }
        ],
        "listingInfo": [
        {
        "bestOfferEnabled": [
        "false"
        ],
        "buyItNowAvailable": [
        "false"
        ],
        "startTime": [
        "2014-10-02T21:13:13.000Z"
        ],
        "endTime": [
        "2015-03-31T21:18:13.000Z"
        ],
        "listingType": [
        "FixedPrice"
        ],
        "gift": [
        "false"
        ]
        }
        ],
        "returnsAccepted": [
        "true"
        ],
        "condition": [
        {
        "conditionId": [
        "5000"
        ],
        "conditionDisplayName": [
        "Good"
        ]
        }
        ],
        "isMultiVariationListing": [
        "false"
        ],
        "topRatedListing": [
        "false"
        ]
        },
        {
        "itemId": [
        "141591419163"
        ],
        "title": [
        "Harry Potter and the Order of the Phoenix  (Sony PlayStation 2, 2007)"
        ],
        "globalId": [
        "EBAY-US"
        ],
        "primaryCategory": [
        {
        "categoryId": [
        "139973"
        ],
        "categoryName": [
        "Video Games"
        ]
        }
        ],
        "galleryURL": [
        "http:\/\/thumbs4.ebaystatic.com\/m\/mYNogwK0cjKFqxTo0WL9oLw\/140.jpg"
        ],
        "viewItemURL": [
        "http:\/\/www.ebay.com\/itm\/Harry-Potter-and-Order-Phoenix-Sony-PlayStation-2-2007-\/141591419163?pt=LH_DefaultDomain_0"
        ],
        "productId": [
        {
        "@type": "ReferenceID",
        "__value__": "56248034"
        }
        ],
        "paymentMethod": [
        "PayPal"
        ],
        "autoPay": [
        "false"
        ],
        "postalCode": [
        "34747"
        ],
        "location": [
        "Kissimmee,FL,USA"
        ],
        "country": [
        "US"
        ],
        "shippingInfo": [
        {
        "shippingType": [
        "Calculated"
        ],
        "shipToLocations": [
        "US",
        "CA",
        "GB",
        "AU",
        "MX"
        ],
        "expeditedShipping": [
        "false"
        ],
        "oneDayShippingAvailable": [
        "false"
        ],
        "handlingTime": [
        "2"
        ]
        }
        ],
        "sellingStatus": [
        {
        "currentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "0.99"
        }
        ],
        "convertedCurrentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "0.99"
        }
        ],
        "bidCount": [
        "0"
        ],
        "sellingState": [
        "Active"
        ],
        "timeLeft": [
        "P0DT12H5M40S"
        ]
        }
        ],
        "listingInfo": [
        {
        "bestOfferEnabled": [
        "false"
        ],
        "buyItNowAvailable": [
        "false"
        ],
        "startTime": [
        "2015-03-02T18:32:35.000Z"
        ],
        "endTime": [
        "2015-03-09T18:32:35.000Z"
        ],
        "listingType": [
        "Auction"
        ],
        "gift": [
        "false"
        ]
        }
        ],
        "returnsAccepted": [
        "false"
        ],
        "condition": [
        {
        "conditionId": [
        "5000"
        ],
        "conditionDisplayName": [
        "Good"
        ]
        }
        ],
        "isMultiVariationListing": [
        "false"
        ],
        "topRatedListing": [
        "false"
        ]
        },
        {
        "itemId": [
        "141591587468"
        ],
        "title": [
        "harry potter and the order of the phoenix Cassettes tapes"
        ],
        "globalId": [
        "EBAY-US"
        ],
        "primaryCategory": [
        {
        "categoryId": [
        "617"
        ],
        "categoryName": [
        "DVDs & Blu-ray Discs"
        ]
        }
        ],
        "galleryURL": [
        "http:\/\/thumbs1.ebaystatic.com\/m\/mJJSME0ffr1MpOk-X9NeBfA\/140.jpg"
        ],
        "viewItemURL": [
        "http:\/\/www.ebay.com\/itm\/harry-potter-and-order-phoenix-Cassettes-tapes-\/141591587468?pt=LH_DefaultDomain_0"
        ],
        "paymentMethod": [
        "PayPal"
        ],
        "autoPay": [
        "false"
        ],
        "postalCode": [
        "64102"
        ],
        "location": [
        "Kansas City,MO,USA"
        ],
        "country": [
        "US"
        ],
        "shippingInfo": [
        {
        "shippingServiceCost": [
        {
        "@currencyId": "USD",
        "__value__": "0.0"
        }
        ],
        "shippingType": [
        "Free"
        ],
        "shipToLocations": [
        "US",
        "CA",
        "GB",
        "AU",
        "AT",
        "BE",
        "FR",
        "DE",
        "IT",
        "JP",
        "ES",
        "TW",
        "NL",
        "CN",
        "HK",
        "MX"
        ],
        "expeditedShipping": [
        "false"
        ],
        "oneDayShippingAvailable": [
        "false"
        ],
        "handlingTime": [
        "2"
        ]
        }
        ],
        "sellingStatus": [
        {
        "currentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "2.5"
        }
        ],
        "convertedCurrentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "2.5"
        }
        ],
        "bidCount": [
        "0"
        ],
        "sellingState": [
        "Active"
        ],
        "timeLeft": [
        "P0DT15H4M39S"
        ]
        }
        ],
        "listingInfo": [
        {
        "bestOfferEnabled": [
        "false"
        ],
        "buyItNowAvailable": [
        "true"
        ],
        "buyItNowPrice": [
        {
        "@currencyId": "USD",
        "__value__": "19.95"
        }
        ],
        "convertedBuyItNowPrice": [
        {
        "@currencyId": "USD",
        "__value__": "19.95"
        }
        ],
        "startTime": [
        "2015-03-02T21:31:34.000Z"
        ],
        "endTime": [
        "2015-03-09T21:31:34.000Z"
        ],
        "listingType": [
        "AuctionWithBIN"
        ],
        "gift": [
        "false"
        ]
        }
        ],
        "returnsAccepted": [
        "true"
        ],
        "condition": [
        {
        "conditionId": [
        "1000"
        ],
        "conditionDisplayName": [
        "Brand New"
        ]
        }
        ],
        "isMultiVariationListing": [
        "false"
        ],
        "topRatedListing": [
        "false"
        ]
        },
        {
        "itemId": [
        "141597834735"
        ],
        "title": [
        "Harry Potter and the Order of the Phoenix 5 by J. K. Rowling (2003, Hardcover)"
        ],
        "globalId": [
        "EBAY-US"
        ],
        "primaryCategory": [
        {
        "categoryId": [
        "2228"
        ],
        "categoryName": [
        "Textbooks, Education"
        ]
        }
        ],
        "galleryURL": [
        "http:\/\/thumbs4.ebaystatic.com\/m\/ma85pZE1ecXBl_muOBV9bUg\/140.jpg"
        ],
        "viewItemURL": [
        "http:\/\/www.ebay.com\/itm\/Harry-Potter-and-Order-Phoenix-5-J-K-Rowling-2003-Hardcover-\/141597834735?pt=LH_DefaultDomain_0"
        ],
        "productId": [
        {
        "@type": "ReferenceID",
        "__value__": "2327833"
        }
        ],
        "paymentMethod": [
        "PayPal"
        ],
        "autoPay": [
        "false"
        ],
        "postalCode": [
        "21043"
        ],
        "location": [
        "Ellicott City,MD,USA"
        ],
        "country": [
        "US"
        ],
        "shippingInfo": [
        {
        "shippingType": [
        "Calculated"
        ],
        "shipToLocations": [
        "US"
        ],
        "expeditedShipping": [
        "true"
        ],
        "oneDayShippingAvailable": [
        "false"
        ],
        "handlingTime": [
        "1"
        ]
        }
        ],
        "sellingStatus": [
        {
        "currentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "7.99"
        }
        ],
        "convertedCurrentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "7.99"
        }
        ],
        "sellingState": [
        "Active"
        ],
        "timeLeft": [
        "P29DT8H20M33S"
        ]
        }
        ],
        "listingInfo": [
        {
        "bestOfferEnabled": [
        "true"
        ],
        "buyItNowAvailable": [
        "false"
        ],
        "startTime": [
        "2015-03-09T02:50:46.000Z"
        ],
        "endTime": [
        "2015-04-07T14:47:28.000Z"
        ],
        "listingType": [
        "FixedPrice"
        ],
        "gift": [
        "false"
        ]
        }
        ],
        "returnsAccepted": [
        "false"
        ],
        "condition": [
        {
        "conditionId": [
        "5000"
        ],
        "conditionDisplayName": [
        "Good"
        ]
        }
        ],
        "isMultiVariationListing": [
        "false"
        ],
        "topRatedListing": [
        "false"
        ]
        },
        {
        "itemId": [
        "121585301056"
        ],
        "title": [
        "Harry Potter and the Order of the Phoenix  (Microsoft Xbox 360, 2007)"
        ],
        "globalId": [
        "EBAY-US"
        ],
        "primaryCategory": [
        {
        "categoryId": [
        "139973"
        ],
        "categoryName": [
        "Video Games"
        ]
        }
        ],
        "galleryURL": [
        "http:\/\/thumbs1.ebaystatic.com\/m\/mZl9q1014U_UVZ7QslKuo1Q\/140.jpg"
        ],
        "viewItemURL": [
        "http:\/\/www.ebay.com\/itm\/Harry-Potter-and-Order-Phoenix-Microsoft-Xbox-360-2007-\/121585301056?pt=LH_DefaultDomain_0"
        ],
        "productId": [
        {
        "@type": "ReferenceID",
        "__value__": "56274325"
        }
        ],
        "paymentMethod": [
        "PayPal"
        ],
        "autoPay": [
        "true"
        ],
        "postalCode": [
        "24055"
        ],
        "location": [
        "Bassett,VA,USA"
        ],
        "country": [
        "US"
        ],
        "shippingInfo": [
        {
        "shippingType": [
        "Calculated"
        ],
        "shipToLocations": [
        "US",
        "CA",
        "GB",
        "AU",
        "MX"
        ],
        "expeditedShipping": [
        "false"
        ],
        "oneDayShippingAvailable": [
        "false"
        ],
        "handlingTime": [
        "2"
        ]
        }
        ],
        "sellingStatus": [
        {
        "currentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "4.99"
        }
        ],
        "convertedCurrentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "4.99"
        }
        ],
        "bidCount": [
        "2"
        ],
        "sellingState": [
        "Active"
        ],
        "timeLeft": [
        "P0DT15H6M52S"
        ]
        }
        ],
        "listingInfo": [
        {
        "bestOfferEnabled": [
        "false"
        ],
        "buyItNowAvailable": [
        "false"
        ],
        "startTime": [
        "2015-03-02T21:33:47.000Z"
        ],
        "endTime": [
        "2015-03-09T21:33:47.000Z"
        ],
        "listingType": [
        "Auction"
        ],
        "gift": [
        "false"
        ]
        }
        ],
        "returnsAccepted": [
        "false"
        ],
        "condition": [
        {
        "conditionId": [
        "6000"
        ],
        "conditionDisplayName": [
        "Acceptable"
        ]
        }
        ],
        "isMultiVariationListing": [
        "false"
        ],
        "topRatedListing": [
        "false"
        ]
        },
        {
        "itemId": [
        "331495013481"
        ],
        "title": [
        "Lego HOGWARTS CASTLE 5378 Harry Potter & The Order Of The Phoenix NEW & SEALED"
        ],
        "globalId": [
        "EBAY-US"
        ],
        "primaryCategory": [
        {
        "categoryId": [
        "19006"
        ],
        "categoryName": [
        "Sets"
        ]
        }
        ],
        "galleryURL": [
        "http:\/\/thumbs2.ebaystatic.com\/m\/mQ12O4C6xhreiCAPI_e31Ng\/140.jpg"
        ],
        "viewItemURL": [
        "http:\/\/www.ebay.com\/itm\/Lego-HOGWARTS-CASTLE-5378-Harry-Potter-Order-Phoenix-NEW-SEALED-\/331495013481?pt=LH_DefaultDomain_0"
        ],
        "productId": [
        {
        "@type": "ReferenceID",
        "__value__": "79847744"
        }
        ],
        "paymentMethod": [
        "PayPal"
        ],
        "autoPay": [
        "true"
        ],
        "postalCode": [
        "22152"
        ],
        "location": [
        "Springfield,VA,USA"
        ],
        "country": [
        "US"
        ],
        "shippingInfo": [
        {
        "shippingType": [
        "Calculated"
        ],
        "shipToLocations": [
        "US"
        ],
        "expeditedShipping": [
        "true"
        ],
        "oneDayShippingAvailable": [
        "false"
        ],
        "handlingTime": [
        "2"
        ]
        }
        ],
        "sellingStatus": [
        {
        "currentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "455.0"
        }
        ],
        "convertedCurrentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "455.0"
        }
        ],
        "bidCount": [
        "2"
        ],
        "sellingState": [
        "Active"
        ],
        "timeLeft": [
        "P0DT16H36M27S"
        ]
        }
        ],
        "listingInfo": [
        {
        "bestOfferEnabled": [
        "false"
        ],
        "buyItNowAvailable": [
        "false"
        ],
        "startTime": [
        "2015-03-02T23:03:22.000Z"
        ],
        "endTime": [
        "2015-03-09T23:03:22.000Z"
        ],
        "listingType": [
        "Auction"
        ],
        "gift": [
        "false"
        ]
        }
        ],
        "returnsAccepted": [
        "false"
        ],
        "condition": [
        {
        "conditionId": [
        "1000"
        ],
        "conditionDisplayName": [
        "New"
        ]
        }
        ],
        "isMultiVariationListing": [
        "false"
        ],
        "topRatedListing": [
        "false"
        ]
        },
        {
        "itemId": [
        "221578205234"
        ],
        "title": [
        "Harry Potter and the Order of the Phoenix (Book 5)"
        ],
        "globalId": [
        "EBAY-US"
        ],
        "primaryCategory": [
        {
        "categoryId": [
        "279"
        ],
        "categoryName": [
        "Children & Young Adults"
        ]
        }
        ],
        "galleryURL": [
        "http:\/\/thumbs3.ebaystatic.com\/m\/ma85pZE1ecXBl_muOBV9bUg\/140.jpg"
        ],
        "viewItemURL": [
        "http:\/\/www.ebay.com\/itm\/Harry-Potter-and-Order-Phoenix-Book-5-\/221578205234?pt=LH_DefaultDomain_0"
        ],
        "productId": [
        {
        "@type": "ReferenceID",
        "__value__": "2327833"
        }
        ],
        "paymentMethod": [
        "PayPal"
        ],
        "autoPay": [
        "true"
        ],
        "location": [
        "USA"
        ],
        "country": [
        "US"
        ],
        "shippingInfo": [
        {
        "shippingServiceCost": [
        {
        "@currencyId": "USD",
        "__value__": "0.0"
        }
        ],
        "shippingType": [
        "Free"
        ],
        "shipToLocations": [
        "US"
        ],
        "expeditedShipping": [
        "true"
        ],
        "oneDayShippingAvailable": [
        "false"
        ],
        "handlingTime": [
        "2"
        ]
        }
        ],
        "sellingStatus": [
        {
        "currentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "4.21"
        }
        ],
        "convertedCurrentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "4.21"
        }
        ],
        "sellingState": [
        "Active"
        ],
        "timeLeft": [
        "P6DT22H19M25S"
        ]
        }
        ],
        "listingInfo": [
        {
        "bestOfferEnabled": [
        "false"
        ],
        "buyItNowAvailable": [
        "false"
        ],
        "startTime": [
        "2014-10-17T04:41:20.000Z"
        ],
        "endTime": [
        "2015-03-16T04:46:20.000Z"
        ],
        "listingType": [
        "FixedPrice"
        ],
        "gift": [
        "false"
        ]
        }
        ],
        "returnsAccepted": [
        "true"
        ],
        "condition": [
        {
        "conditionId": [
        "6000"
        ],
        "conditionDisplayName": [
        "Acceptable"
        ]
        }
        ],
        "isMultiVariationListing": [
        "false"
        ],
        "topRatedListing": [
        "false"
        ]
        },
        {
        "itemId": [
        "191529858935"
        ],
        "title": [
        "Harry Potter and the Order of the Phoenix  (Nintendo Wii) "
        ],
        "globalId": [
        "EBAY-US"
        ],
        "primaryCategory": [
        {
        "categoryId": [
        "139973"
        ],
        "categoryName": [
        "Video Games"
        ]
        }
        ],
        "galleryURL": [
        "http:\/\/thumbs4.ebaystatic.com\/m\/mRGDHzTztmlTHKcwi0O5cKg\/140.jpg"
        ],
        "viewItemURL": [
        "http:\/\/www.ebay.com\/itm\/Harry-Potter-and-Order-Phoenix-Nintendo-Wii-\/191529858935?pt=LH_DefaultDomain_0"
        ],
        "productId": [
        {
        "@type": "ReferenceID",
        "__value__": "56277256"
        }
        ],
        "paymentMethod": [
        "PayPal"
        ],
        "autoPay": [
        "false"
        ],
        "postalCode": [
        "04330"
        ],
        "location": [
        "Augusta,ME,USA"
        ],
        "country": [
        "US"
        ],
        "shippingInfo": [
        {
        "shippingType": [
        "Calculated"
        ],
        "shipToLocations": [
        "US",
        "CA",
        "GB",
        "AU",
        "AT",
        "BE",
        "FR",
        "DE",
        "IT",
        "JP",
        "ES",
        "TW",
        "NL",
        "CN",
        "HK",
        "MX"
        ],
        "expeditedShipping": [
        "false"
        ],
        "oneDayShippingAvailable": [
        "false"
        ],
        "handlingTime": [
        "2"
        ]
        }
        ],
        "sellingStatus": [
        {
        "currentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "6.0"
        }
        ],
        "convertedCurrentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "6.0"
        }
        ],
        "bidCount": [
        "0"
        ],
        "sellingState": [
        "Active"
        ],
        "timeLeft": [
        "P0DT18H9M53S"
        ]
        }
        ],
        "listingInfo": [
        {
        "bestOfferEnabled": [
        "false"
        ],
        "buyItNowAvailable": [
        "false"
        ],
        "startTime": [
        "2015-03-07T00:36:48.000Z"
        ],
        "endTime": [
        "2015-03-10T00:36:48.000Z"
        ],
        "listingType": [
        "Auction"
        ],
        "gift": [
        "false"
        ]
        }
        ],
        "returnsAccepted": [
        "true"
        ],
        "condition": [
        {
        "conditionId": [
        "2750"
        ],
        "conditionDisplayName": [
        "Like New"
        ]
        }
        ],
        "isMultiVariationListing": [
        "false"
        ],
        "topRatedListing": [
        "false"
        ]
        },
        {
        "itemId": [
        "281617163160"
        ],
        "title": [
        "Harry Potter and the Order of the Phoenix 5 - by J. K. Rowling (2003, Hardcover)"
        ],
        "globalId": [
        "EBAY-US"
        ],
        "primaryCategory": [
        {
        "categoryId": [
        "377"
        ],
        "categoryName": [
        "Fiction & Literature"
        ]
        }
        ],
        "galleryURL": [
        "http:\/\/thumbs1.ebaystatic.com\/m\/maLt4wWOrpWXsR6w4zHd_bQ\/140.jpg"
        ],
        "viewItemURL": [
        "http:\/\/www.ebay.com\/itm\/Harry-Potter-and-Order-Phoenix-5-J-K-Rowling-2003-Hardcover-\/281617163160?pt=LH_DefaultDomain_0"
        ],
        "productId": [
        {
        "@type": "ReferenceID",
        "__value__": "2327833"
        }
        ],
        "paymentMethod": [
        "PayPal"
        ],
        "autoPay": [
        "false"
        ],
        "postalCode": [
        "48138"
        ],
        "location": [
        "Grosse Ile,MI,USA"
        ],
        "country": [
        "US"
        ],
        "shippingInfo": [
        {
        "shippingType": [
        "Calculated"
        ],
        "shipToLocations": [
        "US",
        "CA",
        "GB",
        "AU",
        "AT",
        "BE",
        "FR",
        "DE",
        "IT",
        "JP",
        "ES",
        "TW",
        "NL",
        "CN",
        "HK",
        "MX"
        ],
        "expeditedShipping": [
        "false"
        ],
        "oneDayShippingAvailable": [
        "false"
        ],
        "handlingTime": [
        "2"
        ]
        }
        ],
        "sellingStatus": [
        {
        "currentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "0.01"
        }
        ],
        "convertedCurrentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "0.01"
        }
        ],
        "bidCount": [
        "0"
        ],
        "sellingState": [
        "Active"
        ],
        "timeLeft": [
        "P0DT23H28M29S"
        ]
        }
        ],
        "listingInfo": [
        {
        "bestOfferEnabled": [
        "false"
        ],
        "buyItNowAvailable": [
        "false"
        ],
        "startTime": [
        "2015-03-03T05:55:24.000Z"
        ],
        "endTime": [
        "2015-03-10T05:55:24.000Z"
        ],
        "listingType": [
        "Auction"
        ],
        "gift": [
        "false"
        ]
        }
        ],
        "returnsAccepted": [
        "false"
        ],
        "condition": [
        {
        "conditionId": [
        "5000"
        ],
        "conditionDisplayName": [
        "Good"
        ]
        }
        ],
        "isMultiVariationListing": [
        "false"
        ],
        "topRatedListing": [
        "false"
        ]
        },
        {
        "itemId": [
        "280667917644"
        ],
        "title": [
        "NEW - Harry Potter and the Order of the Phoenix"
        ],
        "globalId": [
        "EBAY-US"
        ],
        "primaryCategory": [
        {
        "categoryId": [
        "617"
        ],
        "categoryName": [
        "DVDs & Blu-ray Discs"
        ]
        }
        ],
        "galleryURL": [
        "http:\/\/thumbs1.ebaystatic.com\/m\/m7JGXe93aJHowyKYffREVfw\/140.jpg"
        ],
        "viewItemURL": [
        "http:\/\/www.ebay.com\/itm\/NEW-Harry-Potter-and-Order-Phoenix-\/280667917644?pt=LH_DefaultDomain_0"
        ],
        "productId": [
        {
        "@type": "ReferenceID",
        "__value__": "62940250"
        }
        ],
        "paymentMethod": [
        "PayPal"
        ],
        "autoPay": [
        "true"
        ],
        "location": [
        "USA"
        ],
        "country": [
        "US"
        ],
        "shippingInfo": [
        {
        "shippingServiceCost": [
        {
        "@currencyId": "USD",
        "__value__": "3.0"
        }
        ],
        "shippingType": [
        "Flat"
        ],
        "shipToLocations": [
        "Worldwide"
        ],
        "expeditedShipping": [
        "true"
        ],
        "oneDayShippingAvailable": [
        "false"
        ],
        "handlingTime": [
        "1"
        ]
        }
        ],
        "sellingStatus": [
        {
        "currentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "3.35"
        }
        ],
        "convertedCurrentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "3.35"
        }
        ],
        "sellingState": [
        "Active"
        ],
        "timeLeft": [
        "P0DT12H27M40S"
        ]
        }
        ],
        "listingInfo": [
        {
        "bestOfferEnabled": [
        "false"
        ],
        "buyItNowAvailable": [
        "false"
        ],
        "startTime": [
        "2011-04-29T18:49:35.000Z"
        ],
        "endTime": [
        "2015-03-09T18:54:35.000Z"
        ],
        "listingType": [
        "FixedPrice"
        ],
        "gift": [
        "false"
        ]
        }
        ],
        "returnsAccepted": [
        "true"
        ],
        "condition": [
        {
        "conditionId": [
        "1000"
        ],
        "conditionDisplayName": [
        "Brand New"
        ]
        }
        ],
        "isMultiVariationListing": [
        "false"
        ],
        "topRatedListing": [
        "true"
        ]
        },
        {
        "itemId": [
        "321685498580"
        ],
        "title": [
        "Harry Potter HD DVDs DVD 5 Movies Sorcerers Stone Chamber Phoenix Goblet Azkaban"
        ],
        "globalId": [
        "EBAY-US"
        ],
        "primaryCategory": [
        {
        "categoryId": [
        "617"
        ],
        "categoryName": [
        "DVDs & Blu-ray Discs"
        ]
        }
        ],
        "galleryURL": [
        "http:\/\/thumbs1.ebaystatic.com\/m\/mFr-t6iGN93uDH7Wskz8VLg\/140.jpg"
        ],
        "viewItemURL": [
        "http:\/\/www.ebay.com\/itm\/Harry-Potter-HD-DVDs-DVD-5-Movies-Sorcerers-Stone-Chamber-Phoenix-Goblet-Azkaban-\/321685498580?pt=LH_DefaultDomain_0"
        ],
        "paymentMethod": [
        "PayPal"
        ],
        "autoPay": [
        "false"
        ],
        "postalCode": [
        "90048"
        ],
        "location": [
        "Los Angeles,CA,USA"
        ],
        "country": [
        "US"
        ],
        "shippingInfo": [
        {
        "shippingType": [
        "Calculated"
        ],
        "shipToLocations": [
        "US",
        "CA",
        "GB",
        "AU",
        "AT",
        "BE",
        "FR",
        "DE",
        "IT",
        "JP",
        "ES",
        "TW",
        "NL",
        "CN",
        "HK",
        "MX"
        ],
        "expeditedShipping": [
        "false"
        ],
        "oneDayShippingAvailable": [
        "false"
        ],
        "handlingTime": [
        "2"
        ]
        }
        ],
        "sellingStatus": [
        {
        "currentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "12.0"
        }
        ],
        "convertedCurrentPrice": [
        {
        "@currencyId": "USD",
        "__value__": "12.0"
        }
        ],
        "bidCount": [
        "0"
        ],
        "sellingState": [
        "Active"
        ],
        "timeLeft": [
        "P1DT12H20M34S"
        ]
        }
        ],
        "listingInfo": [
        {
        "bestOfferEnabled": [
        "false"
        ],
        "buyItNowAvailable": [
        "false"
        ],
        "startTime": [
        "2015-03-03T18:47:29.000Z"
        ],
        "endTime": [
        "2015-03-10T18:47:29.000Z"
        ],
        "listingType": [
        "Auction"
        ],
        "gift": [
        "false"
        ]
        }
        ],
        "returnsAccepted": [
        "false"
        ],
        "condition": [
        {
        "conditionId": [
        "2750"
        ],
        "conditionDisplayName": [
        "Like New"
        ]
        }
        ],
        "isMultiVariationListing": [
        "false"
        ],
        "topRatedListing": [
        "false"
        ]
        }
        ]
        }
        ],
        "paginationOutput": [
        {
        "pageNumber": [
        "1"
        ],
        "entriesPerPage": [
        "12"
        ],
        "totalPages": [
        "228"
        ],
        "totalEntries": [
        "2730"
        ]
        }
        ],
        "itemSearchURL": [
        "http:\/\/www.ebay.com\/sch\/i.html?_nkw=harry+potter+phoenix&_ddo=1&_ipg=12&_pgn=1"
        ]
        }
        ]
        }
*/