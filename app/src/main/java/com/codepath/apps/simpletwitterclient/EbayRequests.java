package com.codepath.apps.simpletwitterclient;

/**
 * Created by sophiataskova on 3/8/15.
 */
public class EbayRequests {

//    http://www.developer.ebay.com/DevZone/finding/CallRef/findItemsByKeywords.html#Request.keywords

    public static final String sSearchByKeywordUrl = "http://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsByKeywords&SERVICE-VERSION=1.12.0&SECURITY-APPNAME=SofiyaTa-ce9c-4caf-9267-359dc21130ef&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&keywords=";
    public static final String sPaginationUrl = "&paginationInput.entriesPerPage=";
    public static final String sPageNumberUrl = "&paginationInput.pageNumber=";


}
